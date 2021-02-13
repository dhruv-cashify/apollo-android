// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.fragment_used_twice.fragment.adapter

import com.apollographql.apollo.api.CustomScalarAdapters
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.json.JsonReader
import com.apollographql.apollo.api.internal.json.JsonWriter
import com.apollographql.apollo.api.internal.stringResponseAdapter
import com.apollographql.apollo.exception.UnexpectedNullValue
import com.example.fragment_used_twice.fragment.HumanDetailsImpl
import kotlin.Any
import kotlin.Array
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class HumanDetailsImpl_ResponseAdapter(
  customScalarAdapters: CustomScalarAdapters
) : ResponseAdapter<HumanDetailsImpl.Data> {
  val characterDataAdapter: CharacterData =
      com.example.fragment_used_twice.fragment.adapter.HumanDetailsImpl_ResponseAdapter.CharacterData(customScalarAdapters)

  val otherDataAdapter: OtherData =
      com.example.fragment_used_twice.fragment.adapter.HumanDetailsImpl_ResponseAdapter.OtherData(customScalarAdapters)

  override fun fromResponse(reader: JsonReader, __typename: String?): HumanDetailsImpl.Data {
    reader.beginObject()
    check(reader.nextName() == "__typename")
    val typename = reader.nextString()

    return when(typename) {
      "Human" -> characterDataAdapter.fromResponse(reader, typename)
      else -> otherDataAdapter.fromResponse(reader, typename)
    }
    .also { reader.endObject() }
  }

  override fun toResponse(writer: JsonWriter, value: HumanDetailsImpl.Data) {
    when(value) {
      is HumanDetailsImpl.Data.CharacterData -> characterDataAdapter.toResponse(writer, value)
      is HumanDetailsImpl.Data.OtherData -> otherDataAdapter.toResponse(writer, value)
    }
  }

  class CharacterData(
    customScalarAdapters: CustomScalarAdapters
  ) : ResponseAdapter<HumanDetailsImpl.Data.CharacterData> {
    val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

    val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

    val birthDateAdapter: ResponseAdapter<Any> =
        customScalarAdapters.responseAdapterFor<Any>("Date")

    override fun fromResponse(reader: JsonReader, __typename: String?):
        HumanDetailsImpl.Data.CharacterData {
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
      return HumanDetailsImpl.Data.CharacterData(
        __typename = __typename!!,
        name = name!!,
        birthDate = birthDate!!
      )
    }

    override fun toResponse(writer: JsonWriter, value: HumanDetailsImpl.Data.CharacterData) {
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

  class OtherData(
    customScalarAdapters: CustomScalarAdapters
  ) : ResponseAdapter<HumanDetailsImpl.Data.OtherData> {
    val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

    val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

    override fun fromResponse(reader: JsonReader, __typename: String?):
        HumanDetailsImpl.Data.OtherData {
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
      return HumanDetailsImpl.Data.OtherData(
        __typename = __typename!!,
        name = name!!
      )
    }

    override fun toResponse(writer: JsonWriter, value: HumanDetailsImpl.Data.OtherData) {
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
