// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.directive_with_fragment.fragment.adapter

import com.apollographql.apollo.api.CustomScalarAdapters
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.NullableResponseAdapter
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.json.JsonReader
import com.apollographql.apollo.api.internal.json.JsonWriter
import com.apollographql.apollo.api.internal.stringResponseAdapter
import com.apollographql.apollo.exception.UnexpectedNullValue
import com.example.directive_with_fragment.fragment.HumanDetailsImpl
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
  val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

  val homePlanetAdapter: ResponseAdapter<String?> = NullableResponseAdapter(stringResponseAdapter)

  override fun fromResponse(reader: JsonReader, __typename: String?): HumanDetailsImpl.Data {
    var __typename: String? = __typename
    var homePlanet: String? = null
    reader.beginObject()
    while(true) {
      when (reader.selectName(RESPONSE_NAMES)) {
        0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
            UnexpectedNullValue("__typename")
        1 -> homePlanet = homePlanetAdapter.fromResponse(reader)
        else -> break
      }
    }
    reader.endObject()
    return HumanDetailsImpl.Data(
      __typename = __typename!!,
      homePlanet = homePlanet
    )
  }

  override fun toResponse(writer: JsonWriter, value: HumanDetailsImpl.Data) {
    __typenameAdapter.toResponse(writer, value.__typename)
    homePlanetAdapter.toResponse(writer, value.homePlanet)
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
        type = ResponseField.Type.Named.Other("String"),
        responseName = "homePlanet",
        fieldName = "homePlanet",
        arguments = emptyMap(),
        conditions = emptyList(),
        fieldSets = emptyList(),
      )
    )

    val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
  }
}
