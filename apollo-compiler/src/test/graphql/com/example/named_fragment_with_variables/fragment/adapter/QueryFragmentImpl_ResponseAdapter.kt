// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.named_fragment_with_variables.fragment.adapter

import com.apollographql.apollo.api.CustomScalarAdapters
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ListResponseAdapter
import com.apollographql.apollo.api.internal.NullableResponseAdapter
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.json.JsonReader
import com.apollographql.apollo.api.internal.json.JsonWriter
import com.apollographql.apollo.api.internal.stringResponseAdapter
import com.apollographql.apollo.exception.UnexpectedNullValue
import com.example.named_fragment_with_variables.fragment.QueryFragmentImpl
import kotlin.Array
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class QueryFragmentImpl_ResponseAdapter(
  customScalarAdapters: CustomScalarAdapters
) : ResponseAdapter<QueryFragmentImpl.Data> {
  val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

  val organizationAdapter: ResponseAdapter<QueryFragmentImpl.Data.Organization?> =
      NullableResponseAdapter(Organization(customScalarAdapters))

  override fun fromResponse(reader: JsonReader, __typename: String?): QueryFragmentImpl.Data {
    var __typename: String? = __typename
    var organization: QueryFragmentImpl.Data.Organization? = null
    reader.beginObject()
    while(true) {
      when (reader.selectName(RESPONSE_NAMES)) {
        0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
            UnexpectedNullValue("__typename")
        1 -> organization = organizationAdapter.fromResponse(reader)
        else -> break
      }
    }
    reader.endObject()
    return QueryFragmentImpl.Data(
      __typename = __typename!!,
      organization = organization
    )
  }

  override fun toResponse(writer: JsonWriter, value: QueryFragmentImpl.Data) {
    __typenameAdapter.toResponse(writer, value.__typename)
    organizationAdapter.toResponse(writer, value.organization)
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
        type = ResponseField.Type.Named.Object("Organization"),
        responseName = "organization",
        fieldName = "organization",
        arguments = mapOf<String, Any?>(
          "id" to mapOf<String, Any?>(
            "kind" to "Variable",
            "variableName" to "organizationId")),
        conditions = emptyList(),
        fieldSets = listOf(
          ResponseField.FieldSet(null, Organization.RESPONSE_FIELDS)
        ),
      )
    )

    val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
  }

  class Organization(
    customScalarAdapters: CustomScalarAdapters
  ) : ResponseAdapter<QueryFragmentImpl.Data.Organization> {
    val idAdapter: ResponseAdapter<String> = stringResponseAdapter

    val userAdapter: ResponseAdapter<List<QueryFragmentImpl.Data.Organization.User>> =
        ListResponseAdapter(User(customScalarAdapters))

    override fun fromResponse(reader: JsonReader, __typename: String?):
        QueryFragmentImpl.Data.Organization {
      var id: String? = null
      var user: List<QueryFragmentImpl.Data.Organization.User>? = null
      reader.beginObject()
      while(true) {
        when (reader.selectName(RESPONSE_NAMES)) {
          0 -> id = idAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("id")
          1 -> user = userAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("user")
          else -> break
        }
      }
      reader.endObject()
      return QueryFragmentImpl.Data.Organization(
        id = id!!,
        user = user!!
      )
    }

    override fun toResponse(writer: JsonWriter, value: QueryFragmentImpl.Data.Organization) {
      idAdapter.toResponse(writer, value.id)
      userAdapter.toResponse(writer, value.user)
    }

    companion object {
      val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField(
          type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
          responseName = "id",
          fieldName = "id",
          arguments = emptyMap(),
          conditions = emptyList(),
          fieldSets = emptyList(),
        ),
        ResponseField(
          type =
              ResponseField.Type.NotNull(ResponseField.Type.List(ResponseField.Type.NotNull(ResponseField.Type.Named.Object("User")))),
          responseName = "user",
          fieldName = "user",
          arguments = mapOf<String, Any?>(
            "query" to mapOf<String, Any?>(
              "kind" to "Variable",
              "variableName" to "query")),
          conditions = emptyList(),
          fieldSets = listOf(
            ResponseField.FieldSet("User", User.UserUser.RESPONSE_FIELDS),
            ResponseField.FieldSet(null, User.OtherUser.RESPONSE_FIELDS),
          ),
        )
      )

      val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
    }

    class User(
      customScalarAdapters: CustomScalarAdapters
    ) : ResponseAdapter<QueryFragmentImpl.Data.Organization.User> {
      val userUserAdapter: UserUser =
          com.example.named_fragment_with_variables.fragment.adapter.QueryFragmentImpl_ResponseAdapter.Organization.User.UserUser(customScalarAdapters)

      val otherUserAdapter: OtherUser =
          com.example.named_fragment_with_variables.fragment.adapter.QueryFragmentImpl_ResponseAdapter.Organization.User.OtherUser(customScalarAdapters)

      override fun fromResponse(reader: JsonReader, __typename: String?):
          QueryFragmentImpl.Data.Organization.User {
        reader.beginObject()
        check(reader.nextName() == "__typename")
        val typename = reader.nextString()

        return when(typename) {
          "User" -> userUserAdapter.fromResponse(reader, typename)
          else -> otherUserAdapter.fromResponse(reader, typename)
        }
        .also { reader.endObject() }
      }

      override fun toResponse(writer: JsonWriter, value: QueryFragmentImpl.Data.Organization.User) {
        when(value) {
          is QueryFragmentImpl.Data.Organization.User.UserUser -> userUserAdapter.toResponse(writer, value)
          is QueryFragmentImpl.Data.Organization.User.OtherUser -> otherUserAdapter.toResponse(writer, value)
        }
      }

      class UserUser(
        customScalarAdapters: CustomScalarAdapters
      ) : ResponseAdapter<QueryFragmentImpl.Data.Organization.User.UserUser> {
        val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

        val firstNameAdapter: ResponseAdapter<String> = stringResponseAdapter

        val lastNameAdapter: ResponseAdapter<String> = stringResponseAdapter

        val avatarAdapter: ResponseAdapter<String> = stringResponseAdapter

        override fun fromResponse(reader: JsonReader, __typename: String?):
            QueryFragmentImpl.Data.Organization.User.UserUser {
          var __typename: String? = __typename
          var firstName: String? = null
          var lastName: String? = null
          var avatar: String? = null
          reader.beginObject()
          while(true) {
            when (reader.selectName(RESPONSE_NAMES)) {
              0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                  UnexpectedNullValue("__typename")
              1 -> firstName = firstNameAdapter.fromResponse(reader) ?: throw
                  UnexpectedNullValue("firstName")
              2 -> lastName = lastNameAdapter.fromResponse(reader) ?: throw
                  UnexpectedNullValue("lastName")
              3 -> avatar = avatarAdapter.fromResponse(reader) ?: throw
                  UnexpectedNullValue("avatar")
              else -> break
            }
          }
          reader.endObject()
          return QueryFragmentImpl.Data.Organization.User.UserUser(
            __typename = __typename!!,
            firstName = firstName!!,
            lastName = lastName!!,
            avatar = avatar!!
          )
        }

        override fun toResponse(writer: JsonWriter,
            value: QueryFragmentImpl.Data.Organization.User.UserUser) {
          __typenameAdapter.toResponse(writer, value.__typename)
          firstNameAdapter.toResponse(writer, value.firstName)
          lastNameAdapter.toResponse(writer, value.lastName)
          avatarAdapter.toResponse(writer, value.avatar)
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
              responseName = "firstName",
              fieldName = "firstName",
              arguments = emptyMap(),
              conditions = emptyList(),
              fieldSets = emptyList(),
            ),
            ResponseField(
              type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
              responseName = "lastName",
              fieldName = "lastName",
              arguments = emptyMap(),
              conditions = emptyList(),
              fieldSets = emptyList(),
            ),
            ResponseField(
              type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
              responseName = "avatar",
              fieldName = "avatar",
              arguments = mapOf<String, Any?>(
                "size" to mapOf<String, Any?>(
                  "kind" to "Variable",
                  "variableName" to "size")),
              conditions = emptyList(),
              fieldSets = emptyList(),
            )
          )

          val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
        }
      }

      class OtherUser(
        customScalarAdapters: CustomScalarAdapters
      ) : ResponseAdapter<QueryFragmentImpl.Data.Organization.User.OtherUser> {
        val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

        override fun fromResponse(reader: JsonReader, __typename: String?):
            QueryFragmentImpl.Data.Organization.User.OtherUser {
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
          return QueryFragmentImpl.Data.Organization.User.OtherUser(
            __typename = __typename!!
          )
        }

        override fun toResponse(writer: JsonWriter,
            value: QueryFragmentImpl.Data.Organization.User.OtherUser) {
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
}
