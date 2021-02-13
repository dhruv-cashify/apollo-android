// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.fragment_used_twice.adapter

import com.apollographql.apollo.api.CustomScalarAdapters
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.NullableResponseAdapter
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.json.JsonReader
import com.apollographql.apollo.api.internal.json.JsonWriter
import com.apollographql.apollo.api.internal.stringResponseAdapter
import com.apollographql.apollo.exception.UnexpectedNullValue
import com.example.fragment_used_twice.TestQuery
import kotlin.Any
import kotlin.Array
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
          ResponseField.FieldSet("Droid", Hero.CharacterHero.RESPONSE_FIELDS),
          ResponseField.FieldSet("Human", Hero.CharacterHumanHero.RESPONSE_FIELDS),
          ResponseField.FieldSet(null, Hero.OtherHero.RESPONSE_FIELDS),
        ),
      )
    )

    val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
  }

  class Hero(
    customScalarAdapters: CustomScalarAdapters
  ) : ResponseAdapter<TestQuery.Data.Hero> {
    val characterHeroAdapter: CharacterHero =
        com.example.fragment_used_twice.adapter.TestQuery_ResponseAdapter.Hero.CharacterHero(customScalarAdapters)

    val characterHumanHeroAdapter: CharacterHumanHero =
        com.example.fragment_used_twice.adapter.TestQuery_ResponseAdapter.Hero.CharacterHumanHero(customScalarAdapters)

    val otherHeroAdapter: OtherHero =
        com.example.fragment_used_twice.adapter.TestQuery_ResponseAdapter.Hero.OtherHero(customScalarAdapters)

    override fun fromResponse(reader: JsonReader, __typename: String?): TestQuery.Data.Hero {
      reader.beginObject()
      check(reader.nextName() == "__typename")
      val typename = reader.nextString()

      return when(typename) {
        "Droid" -> characterHeroAdapter.fromResponse(reader, typename)
        "Human" -> characterHumanHeroAdapter.fromResponse(reader, typename)
        else -> otherHeroAdapter.fromResponse(reader, typename)
      }
      .also { reader.endObject() }
    }

    override fun toResponse(writer: JsonWriter, value: TestQuery.Data.Hero) {
      when(value) {
        is TestQuery.Data.Hero.CharacterHero -> characterHeroAdapter.toResponse(writer, value)
        is TestQuery.Data.Hero.CharacterHumanHero -> characterHumanHeroAdapter.toResponse(writer, value)
        is TestQuery.Data.Hero.OtherHero -> otherHeroAdapter.toResponse(writer, value)
      }
    }

    class CharacterHero(
      customScalarAdapters: CustomScalarAdapters
    ) : ResponseAdapter<TestQuery.Data.Hero.CharacterHero> {
      val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

      val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

      val birthDateAdapter: ResponseAdapter<Any> =
          customScalarAdapters.responseAdapterFor<Any>("Date")

      override fun fromResponse(reader: JsonReader, __typename: String?):
          TestQuery.Data.Hero.CharacterHero {
        var __typename: String? = __typename
        var name: String? = null
        var birthDate: Any? = null
        reader.beginObject()
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                UnexpectedNullValue("__typename")
            1 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
            2 -> birthDate = birthDateAdapter.fromResponse(reader) ?: throw
                UnexpectedNullValue("birthDate")
            else -> break
          }
        }
        reader.endObject()
        return TestQuery.Data.Hero.CharacterHero(
          __typename = __typename!!,
          name = name!!,
          birthDate = birthDate!!
        )
      }

      override fun toResponse(writer: JsonWriter, value: TestQuery.Data.Hero.CharacterHero) {
        __typenameAdapter.toResponse(writer, value.__typename)
        nameAdapter.toResponse(writer, value.name)
        birthDateAdapter.toResponse(writer, value.birthDate)
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
            type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("Date")),
            responseName = "birthDate",
            fieldName = "birthDate",
            arguments = emptyMap(),
            conditions = emptyList(),
            fieldSets = emptyList(),
          )
        )

        val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
      }
    }

    class CharacterHumanHero(
      customScalarAdapters: CustomScalarAdapters
    ) : ResponseAdapter<TestQuery.Data.Hero.CharacterHumanHero> {
      val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

      val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

      val birthDateAdapter: ResponseAdapter<Any> =
          customScalarAdapters.responseAdapterFor<Any>("Date")

      override fun fromResponse(reader: JsonReader, __typename: String?):
          TestQuery.Data.Hero.CharacterHumanHero {
        var __typename: String? = __typename
        var name: String? = null
        var birthDate: Any? = null
        reader.beginObject()
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                UnexpectedNullValue("__typename")
            1 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
            2 -> birthDate = birthDateAdapter.fromResponse(reader) ?: throw
                UnexpectedNullValue("birthDate")
            else -> break
          }
        }
        reader.endObject()
        return TestQuery.Data.Hero.CharacterHumanHero(
          __typename = __typename!!,
          name = name!!,
          birthDate = birthDate!!
        )
      }

      override fun toResponse(writer: JsonWriter, value: TestQuery.Data.Hero.CharacterHumanHero) {
        __typenameAdapter.toResponse(writer, value.__typename)
        nameAdapter.toResponse(writer, value.name)
        birthDateAdapter.toResponse(writer, value.birthDate)
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
            type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("Date")),
            responseName = "birthDate",
            fieldName = "birthDate",
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

      override fun fromResponse(reader: JsonReader, __typename: String?):
          TestQuery.Data.Hero.OtherHero {
        var __typename: String? = __typename
        reader.beginObject()
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                UnexpectedNullValue("__typename")
            else -> break
          }
        }
        reader.endObject()
        return TestQuery.Data.Hero.OtherHero(
          __typename = __typename!!
        )
      }

      override fun toResponse(writer: JsonWriter, value: TestQuery.Data.Hero.OtherHero) {
        __typenameAdapter.toResponse(writer, value.__typename)
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
          )
        )

        val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
      }
    }
  }
}
