package com.apollographql.apollo

import com.apollographql.apollo.api.CustomScalarAdapters
import com.apollographql.apollo.api.Input.Companion.fromNullable
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.parse
import com.apollographql.apollo.cache.CacheHeaders
import com.apollographql.apollo.cache.normalized.CacheKey.Companion.from
import com.apollographql.apollo.cache.normalized.CacheReference
import com.apollographql.apollo.cache.normalized.MemoryCacheFactory
import com.apollographql.apollo.cache.normalized.NormalizedCache
import com.apollographql.apollo.cache.normalized.Record
import com.apollographql.apollo.cache.normalized.internal.normalize
import com.apollographql.apollo.integration.httpcache.AllPlanetsQuery
import com.apollographql.apollo.integration.normalizer.EpisodeHeroNameQuery
import com.apollographql.apollo.integration.normalizer.HeroAndFriendsNamesQuery
import com.apollographql.apollo.integration.normalizer.HeroAndFriendsNamesWithIDForParentOnlyQuery
import com.apollographql.apollo.integration.normalizer.HeroAndFriendsNamesWithIDsQuery
import com.apollographql.apollo.integration.normalizer.HeroAppearsInQuery
import com.apollographql.apollo.integration.normalizer.HeroNameQuery
import com.apollographql.apollo.integration.normalizer.HeroParentTypeDependentFieldQuery
import com.apollographql.apollo.integration.normalizer.HeroTypeDependentAliasedFieldQuery
import com.apollographql.apollo.integration.normalizer.SameHeroTwiceQuery
import com.apollographql.apollo.integration.normalizer.type.Episode
import com.google.common.truth.Truth
import com.google.common.truth.Truth.*
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal
import java.util.Arrays

class ResponseNormalizationTest {
  private lateinit var normalizedCache: NormalizedCache

  private val QUERY_ROOT_KEY = "QUERY_ROOT"

  @Before
  fun setUp() {
    normalizedCache = MemoryCacheFactory(maxSizeBytes = Int.MAX_VALUE).create()
  }

  @Test
  @Throws(Exception::class)
  fun testHeroName() {
    val records = records(HeroNameQuery(), "HeroNameResponse.json")
    val record = records.get(QUERY_ROOT_KEY)
    val reference = record!!["hero"] as CacheReference?
    assertThat(reference).isEqualTo(CacheReference("hero"))
    val heroRecord = records.get(reference!!.key)
    assertThat(heroRecord!!["name"]).isEqualTo("R2-D2")
  }

  @Test
  @Throws(Exception::class)
  fun testMergeNull() {
    val record = Record(
        key = "Key",
        fields = mapOf("field1" to "value1"),
    )
    normalizedCache.merge(listOf(record), CacheHeaders.NONE)

    val newRecord = Record(
        key = "Key",
        fields = mapOf("field2" to null),
    )

    normalizedCache.merge(listOf(newRecord), CacheHeaders.NONE)
    val finalRecord = normalizedCache.loadRecord(record.key, CacheHeaders.NONE)
    assertThat(finalRecord!!.containsKey("field2")).isTrue()
    normalizedCache.remove(from(record.key), false)
  }

  @Test
  @Throws(Exception::class)
  fun testHeroNameWithVariable() {
    val records = records(EpisodeHeroNameQuery(fromNullable(Episode.JEDI)), "EpisodeHeroNameResponse.json")
    val record = records[QUERY_ROOT_KEY]
    val reference = record!![TEST_FIELD_KEY_JEDI] as CacheReference?
    assertThat(reference).isEqualTo(CacheReference(TEST_FIELD_KEY_JEDI))
    val heroRecord = records.get(reference!!.key)
    assertThat(heroRecord!!["name"]).isEqualTo("R2-D2")
  }

  @Test
  @Throws(Exception::class)
  fun testHeroAppearsInQuery() {
    val records = records(HeroAppearsInQuery(), "HeroAppearsInResponse.json")

    val rootRecord = records.get(QUERY_ROOT_KEY)!!

    val heroReference = rootRecord["hero"] as CacheReference?
    assertThat(heroReference).isEqualTo(CacheReference("hero"))

    val hero = records.get(heroReference!!.key)
    assertThat(hero?.get("appearsIn")).isEqualTo(listOf("NEWHOPE", "EMPIRE", "JEDI"))
  }

  @Test
  @Throws(Exception::class)
  fun testHeroAndFriendsNamesQueryWithoutIDs() {
    val records = records(HeroAndFriendsNamesQuery(fromNullable(Episode.JEDI)), "HeroAndFriendsNameResponse.json")
    val record = records.get(QUERY_ROOT_KEY)
    val heroReference = record!![TEST_FIELD_KEY_JEDI] as CacheReference?
    assertThat(heroReference).isEqualTo(CacheReference(TEST_FIELD_KEY_JEDI))
    val heroRecord = records.get(heroReference!!.key)
    assertThat(heroRecord!!["name"]).isEqualTo("R2-D2")
    assertThat(heroRecord["friends"]).isEqualTo(Arrays.asList(
        CacheReference("$TEST_FIELD_KEY_JEDI.friends.0"),
        CacheReference("$TEST_FIELD_KEY_JEDI.friends.1"),
        CacheReference("$TEST_FIELD_KEY_JEDI.friends.2")
    ))
    val luke = records.get("$TEST_FIELD_KEY_JEDI.friends.0")
    assertThat(luke!!["name"]).isEqualTo("Luke Skywalker")
  }

  @Test
  @Throws(Exception::class)
  fun testHeroAndFriendsNamesQueryWithIDs() {
    val records = records(HeroAndFriendsNamesWithIDsQuery(fromNullable(Episode.JEDI)), "HeroAndFriendsNameWithIdsResponse.json")
    val record = records.get(QUERY_ROOT_KEY)
    val heroReference = record!![TEST_FIELD_KEY_JEDI] as CacheReference?
    assertThat(heroReference).isEqualTo(CacheReference("2001"))
    val heroRecord = records.get(heroReference!!.key)
    assertThat(heroRecord!!["name"]).isEqualTo("R2-D2")
    assertThat(heroRecord["friends"]).isEqualTo(Arrays.asList(
        CacheReference("1000"),
        CacheReference("1002"),
        CacheReference("1003")
    ))
    val luke = records.get("1000")
    assertThat(luke!!["name"]).isEqualTo("Luke Skywalker")
  }

  @Test
  @Throws(Exception::class)
  fun testHeroAndFriendsNamesWithIDForParentOnly() {
    val records = records(HeroAndFriendsNamesWithIDForParentOnlyQuery(fromNullable(Episode.JEDI)), "HeroAndFriendsNameWithIdsParentOnlyResponse.json")
    val record = records[QUERY_ROOT_KEY]
    val heroReference = record!![TEST_FIELD_KEY_JEDI] as CacheReference?
    assertThat(heroReference).isEqualTo(CacheReference("2001"))
    val heroRecord = records.get(heroReference!!.key)
    assertThat(heroRecord!!["name"]).isEqualTo("R2-D2")
    assertThat(heroRecord["friends"]).isEqualTo(Arrays.asList(
        CacheReference("2001.friends.0"),
        CacheReference("2001.friends.1"),
        CacheReference("2001.friends.2")
    ))
    val luke = records.get("2001.friends.0")
    assertThat(luke!!["name"]).isEqualTo("Luke Skywalker")
  }

  @Test
  @Throws(Exception::class)
  fun testSameHeroTwiceQuery() {
    val records = records(SameHeroTwiceQuery(), "SameHeroTwiceResponse.json")
    val record = records.get(QUERY_ROOT_KEY)
    val heroReference = record!!["hero"] as CacheReference?
    val hero = records.get(heroReference!!.key)

    assertThat(hero!!["name"]).isEqualTo("R2-D2")
    assertThat(hero["appearsIn"]).isEqualTo(Arrays.asList("NEWHOPE", "EMPIRE", "JEDI"))
  }

  @Test
  @Throws(Exception::class)
  fun testHeroTypeDependentAliasedFieldQueryDroid() {
    val records = records(HeroTypeDependentAliasedFieldQuery(fromNullable(Episode.JEDI)), "HeroTypeDependentAliasedFieldResponse.json")
    val record = records.get(QUERY_ROOT_KEY)
    val heroReference = record!![TEST_FIELD_KEY_JEDI] as CacheReference?
    val hero = records.get(heroReference!!.key)
    assertThat(hero!!["primaryFunction"]).isEqualTo("Astromech")
    assertThat(hero["__typename"]).isEqualTo("Droid")
  }

  @Test
  @Throws(Exception::class)
  fun testHeroTypeDependentAliasedFieldQueryHuman() {
    val records = records(HeroTypeDependentAliasedFieldQuery(fromNullable(Episode.EMPIRE)), "HeroTypeDependentAliasedFieldResponseHuman.json")
    val record = records.get(QUERY_ROOT_KEY)
    val heroReference = record!![TEST_FIELD_KEY_EMPIRE] as CacheReference?
    val hero = records.get(heroReference!!.key)
    assertThat(hero!!["homePlanet"]).isEqualTo("Tatooine")
    assertThat(hero["__typename"]).isEqualTo("Human")
  }

  @Test
  @Throws(Exception::class)
  fun testHeroParentTypeDependentAliasedFieldQueryHuman() {
    val records = records(HeroTypeDependentAliasedFieldQuery(fromNullable(Episode.EMPIRE)), "HeroTypeDependentAliasedFieldResponseHuman.json")
    val record = records.get(QUERY_ROOT_KEY)
    val heroReference = record!![TEST_FIELD_KEY_EMPIRE] as CacheReference?
    val hero = records.get(heroReference!!.key)
    assertThat(hero!!["homePlanet"]).isEqualTo("Tatooine")
    assertThat(hero["__typename"]).isEqualTo("Human")
  }

  @Test
  @Throws(Exception::class)
  fun testHeroParentTypeDependentFieldDroid() {
    val records = records(HeroParentTypeDependentFieldQuery(fromNullable(Episode.JEDI)), "HeroParentTypeDependentFieldDroidResponse.json")
    val lukeRecord = records.get(TEST_FIELD_KEY_JEDI + ".friends.0")
    assertThat(lukeRecord!!["name"]).isEqualTo("Luke Skywalker")
    assertThat(lukeRecord["height({\"unit\":\"METER\"})"]).isEqualTo(1.72)
    val friends = records.get(TEST_FIELD_KEY_JEDI)!!["friends"] as List<Any>?
    assertThat(friends!![0]).isEqualTo(CacheReference("$TEST_FIELD_KEY_JEDI.friends.0"))
    assertThat(friends[1]).isEqualTo(CacheReference("$TEST_FIELD_KEY_JEDI.friends.1"))
    assertThat(friends[2]).isEqualTo(CacheReference("$TEST_FIELD_KEY_JEDI.friends.2"))
  }

  @Test
  @Throws(Exception::class)
  fun testHeroParentTypeDependentFieldHuman() {
    val records = records(HeroParentTypeDependentFieldQuery(fromNullable(Episode.EMPIRE)), "HeroParentTypeDependentFieldHumanResponse.json")

    val lukeRecord = records.get("$TEST_FIELD_KEY_EMPIRE.friends.0")
    assertThat(lukeRecord!!["name"]).isEqualTo("Han Solo")
    assertThat(lukeRecord["height({\"unit\":\"FOOT\"})"]).isEqualTo(5.905512)
  }

  @Test
  fun list_of_objects_with_null_object() {
    val records = records(AllPlanetsQuery(), "AllPlanetsListOfObjectWithNullObject.json")
    val fieldKey = "allPlanets({\"first\":300})"

    var record: Record? = records["$fieldKey.planets.0"]
    assertThat(record?.get("filmConnection")).isNull()

    record = records["$fieldKey.planets.0.filmConnection"]
    assertThat(record).isNull()

    record = records["$fieldKey.planets.1.filmConnection"]
    assertThat(record).isNotNull()
  }

  companion object {
    private fun <D : Operation.Data> records(operation: Operation<D>, name: String): Map<String, Record> {
      val data = operation.parse(Utils.readResource(name))
      return operation.normalize(data = data.data!!, CustomScalarAdapters.DEFAULT, IdFieldCacheKeyResolver())
    }

    private const val TEST_FIELD_KEY_JEDI = "hero({\"episode\":\"JEDI\"})"
    const val TEST_FIELD_KEY_EMPIRE = "hero({\"episode\":\"EMPIRE\"})"
  }
}
