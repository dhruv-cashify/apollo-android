// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.subscriptions.adapter

import com.apollographql.apollo.api.CustomScalarAdapters
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.NullableResponseAdapter
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.intResponseAdapter
import com.apollographql.apollo.api.internal.json.JsonReader
import com.apollographql.apollo.api.internal.json.JsonWriter
import com.apollographql.apollo.api.internal.stringResponseAdapter
import com.apollographql.apollo.exception.UnexpectedNullValue
import com.example.subscriptions.TestSubscription
import kotlin.Array
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class TestSubscription_ResponseAdapter(
  customScalarAdapters: CustomScalarAdapters
) : ResponseAdapter<TestSubscription.Data> {
  val commentAddedAdapter: ResponseAdapter<TestSubscription.Data.CommentAdded?> =
      NullableResponseAdapter(CommentAdded(customScalarAdapters))

  override fun fromResponse(reader: JsonReader, __typename: String?): TestSubscription.Data {
    var commentAdded: TestSubscription.Data.CommentAdded? = null
    reader.beginObject()
    while(true) {
      when (reader.selectName(RESPONSE_NAMES)) {
        0 -> commentAdded = commentAddedAdapter.fromResponse(reader)
        else -> break
      }
    }
    reader.endObject()
    return TestSubscription.Data(
      commentAdded = commentAdded
    )
  }

  override fun toResponse(writer: JsonWriter, value: TestSubscription.Data) {
    commentAddedAdapter.toResponse(writer, value.commentAdded)
  }

  companion object {
    val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField(
        type = ResponseField.Type.Named.Object("Comment"),
        responseName = "commentAdded",
        fieldName = "commentAdded",
        arguments = mapOf<String, Any?>(
          "repoFullName" to mapOf<String, Any?>(
            "kind" to "Variable",
            "variableName" to "repo")),
        conditions = emptyList(),
        fieldSets = listOf(
          ResponseField.FieldSet(null, CommentAdded.RESPONSE_FIELDS)
        ),
      )
    )

    val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
  }

  class CommentAdded(
    customScalarAdapters: CustomScalarAdapters
  ) : ResponseAdapter<TestSubscription.Data.CommentAdded> {
    val idAdapter: ResponseAdapter<Int> = intResponseAdapter

    val contentAdapter: ResponseAdapter<String> = stringResponseAdapter

    override fun fromResponse(reader: JsonReader, __typename: String?):
        TestSubscription.Data.CommentAdded {
      var id: Int? = null
      var content: String? = null
      reader.beginObject()
      while(true) {
        when (reader.selectName(RESPONSE_NAMES)) {
          0 -> id = idAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("id")
          1 -> content = contentAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("content")
          else -> break
        }
      }
      reader.endObject()
      return TestSubscription.Data.CommentAdded(
        id = id!!,
        content = content!!
      )
    }

    override fun toResponse(writer: JsonWriter, value: TestSubscription.Data.CommentAdded) {
      idAdapter.toResponse(writer, value.id)
      contentAdapter.toResponse(writer, value.content)
    }

    companion object {
      val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField(
          type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("Int")),
          responseName = "id",
          fieldName = "id",
          arguments = emptyMap(),
          conditions = emptyList(),
          fieldSets = emptyList(),
        ),
        ResponseField(
          type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
          responseName = "content",
          fieldName = "content",
          arguments = emptyMap(),
          conditions = emptyList(),
          fieldSets = emptyList(),
        )
      )

      val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
    }
  }
}
