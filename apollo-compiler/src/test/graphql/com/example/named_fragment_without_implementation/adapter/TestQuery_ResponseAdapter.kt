// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.named_fragment_without_implementation.adapter

import com.apollographql.apollo.api.CustomScalarAdapters
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.NullableResponseAdapter
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.doubleResponseAdapter
import com.apollographql.apollo.api.internal.json.JsonReader
import com.apollographql.apollo.api.internal.json.JsonWriter
import com.apollographql.apollo.api.internal.stringResponseAdapter
import com.apollographql.apollo.exception.UnexpectedNullValue
import com.example.named_fragment_without_implementation.TestQuery
import kotlin.Array
import kotlin.Double
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class TestQuery_ResponseAdapter(
  customScalarAdapters: CustomScalarAdapters
) : ResponseAdapter<TestQuery.Data> {
  val heroAdapter: ResponseAdapter<TestQuery.Data.Hero?> =
      NullableResponseAdapter(Hero(customScalarAdapters))

  override fun fromResponse(reader: JsonReader, __typename: String?): TestQuery.Data {
    var hero: TestQuery.Data.Hero? = null
    reader.beginObject()
    while(true) {
      when (reader.selectName(RESPONSE_NAMES)) {
        0 -> hero = heroAdapter.fromResponse(reader)
        else -> break
      }
    }
    reader.endObject()
    return TestQuery.Data(
      hero = hero
    )
  }

  override fun toResponse(writer: JsonWriter, value: TestQuery.Data) {
    heroAdapter.toResponse(writer, value.hero)
  }

  companion object {
    val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField(
        type = ResponseField.Type.Named.Object("Character"),
        responseName = "hero",
        fieldName = "hero",
        arguments = emptyMap(),
        conditions = emptyList(),
        fieldSets = listOf(
          ResponseField.FieldSet("Human", Hero.HumanHero.RESPONSE_FIELDS),
          ResponseField.FieldSet("Droid", Hero.DroidHero.RESPONSE_FIELDS),
          ResponseField.FieldSet(null, Hero.OtherHero.RESPONSE_FIELDS),
        ),
      )
    )

    val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
  }

  class Hero(
    customScalarAdapters: CustomScalarAdapters
  ) : ResponseAdapter<TestQuery.Data.Hero> {
    val humanHeroAdapter: HumanHero =
        com.example.named_fragment_without_implementation.adapter.TestQuery_ResponseAdapter.Hero.HumanHero(customScalarAdapters)

    val droidHeroAdapter: DroidHero =
        com.example.named_fragment_without_implementation.adapter.TestQuery_ResponseAdapter.Hero.DroidHero(customScalarAdapters)

    val otherHeroAdapter: OtherHero =
        com.example.named_fragment_without_implementation.adapter.TestQuery_ResponseAdapter.Hero.OtherHero(customScalarAdapters)

    override fun fromResponse(reader: JsonReader, __typename: String?): TestQuery.Data.Hero {
      reader.beginObject()
      check(reader.nextName() == "__typename")
      val typename = reader.nextString()

      return when(typename) {
        "Human" -> humanHeroAdapter.fromResponse(reader, typename)
        "Droid" -> droidHeroAdapter.fromResponse(reader, typename)
        else -> otherHeroAdapter.fromResponse(reader, typename)
      }
      .also { reader.endObject() }
    }

    override fun toResponse(writer: JsonWriter, value: TestQuery.Data.Hero) {
      when(value) {
        is TestQuery.Data.Hero.HumanHero -> humanHeroAdapter.toResponse(writer, value)
        is TestQuery.Data.Hero.DroidHero -> droidHeroAdapter.toResponse(writer, value)
        is TestQuery.Data.Hero.OtherHero -> otherHeroAdapter.toResponse(writer, value)
      }
    }

    class HumanHero(
      customScalarAdapters: CustomScalarAdapters
    ) : ResponseAdapter<TestQuery.Data.Hero.HumanHero> {
      val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

      val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

      val heightAdapter: ResponseAdapter<Double?> = NullableResponseAdapter(doubleResponseAdapter)

      override fun fromResponse(reader: JsonReader, __typename: String?):
          TestQuery.Data.Hero.HumanHero {
        var __typename: String? = __typename
        var name: String? = null
        var height: Double? = null
        reader.beginObject()
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                UnexpectedNullValue("__typename")
            1 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
            2 -> height = heightAdapter.fromResponse(reader)
            else -> break
          }
        }
        reader.endObject()
        return TestQuery.Data.Hero.HumanHero(
          __typename = __typename!!,
          name = name!!,
          height = height
        )
      }

      override fun toResponse(writer: JsonWriter, value: TestQuery.Data.Hero.HumanHero) {
        __typenameAdapter.toResponse(writer, value.__typename)
        nameAdapter.toResponse(writer, value.name)
        heightAdapter.toResponse(writer, value.height)
      }

      companion object {
        val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField(
            type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
            responseName = "__typename",
            fieldName = "__typename",
            arguments = emptyMap(),
            conditions = emptyList(),
            fieldSets = emptyList(),
          ),
          ResponseField(
            type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
            responseName = "name",
            fieldName = "name",
            arguments = emptyMap(),
            conditions = emptyList(),
            fieldSets = emptyList(),
          ),
          ResponseField(
            type = ResponseField.Type.Named.Other("Float"),
            responseName = "height",
            fieldName = "height",
            arguments = emptyMap(),
            conditions = emptyList(),
            fieldSets = emptyList(),
          )
        )

        val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
      }
    }

    class DroidHero(
      customScalarAdapters: CustomScalarAdapters
    ) : ResponseAdapter<TestQuery.Data.Hero.DroidHero> {
      val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

      val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

      val primaryFunctionAdapter: ResponseAdapter<String?> =
          NullableResponseAdapter(stringResponseAdapter)

      override fun fromResponse(reader: JsonReader, __typename: String?):
          TestQuery.Data.Hero.DroidHero {
        var __typename: String? = __typename
        var name: String? = null
        var primaryFunction: String? = null
        reader.beginObject()
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                UnexpectedNullValue("__typename")
            1 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
            2 -> primaryFunction = primaryFunctionAdapter.fromResponse(reader)
            else -> break
          }
        }
        reader.endObject()
        return TestQuery.Data.Hero.DroidHero(
          __typename = __typename!!,
          name = name!!,
          primaryFunction = primaryFunction
        )
      }

      override fun toResponse(writer: JsonWriter, value: TestQuery.Data.Hero.DroidHero) {
        __typenameAdapter.toResponse(writer, value.__typename)
        nameAdapter.toResponse(writer, value.name)
        primaryFunctionAdapter.toResponse(writer, value.primaryFunction)
      }

      companion object {
        val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField(
            type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
            responseName = "__typename",
            fieldName = "__typename",
            arguments = emptyMap(),
            conditions = emptyList(),
            fieldSets = emptyList(),
          ),
          ResponseField(
            type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
            responseName = "name",
            fieldName = "name",
            arguments = emptyMap(),
            conditions = emptyList(),
            fieldSets = emptyList(),
          ),
          ResponseField(
            type = ResponseField.Type.Named.Other("String"),
            responseName = "primaryFunction",
            fieldName = "primaryFunction",
            arguments = emptyMap(),
            conditions = emptyList(),
            fieldSets = emptyList(),
          )
        )

        val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
      }
    }

    class OtherHero(
      customScalarAdapters: CustomScalarAdapters
    ) : ResponseAdapter<TestQuery.Data.Hero.OtherHero> {
      val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

      val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

      override fun fromResponse(reader: JsonReader, __typename: String?):
          TestQuery.Data.Hero.OtherHero {
        var __typename: String? = __typename
        var name: String? = null
        reader.beginObject()
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                UnexpectedNullValue("__typename")
            1 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
            else -> break
          }
        }
        reader.endObject()
        return TestQuery.Data.Hero.OtherHero(
          __typename = __typename!!,
          name = name!!
        )
      }

      override fun toResponse(writer: JsonWriter, value: TestQuery.Data.Hero.OtherHero) {
        __typenameAdapter.toResponse(writer, value.__typename)
        nameAdapter.toResponse(writer, value.name)
      }

      companion object {
        val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField(
            type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
            responseName = "__typename",
            fieldName = "__typename",
            arguments = emptyMap(),
            conditions = emptyList(),
            fieldSets = emptyList(),
          ),
          ResponseField(
            type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
            responseName = "name",
            fieldName = "name",
            arguments = emptyMap(),
            conditions = emptyList(),
            fieldSets = emptyList(),
          )
        )

        val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
      }
    }
  }
}
