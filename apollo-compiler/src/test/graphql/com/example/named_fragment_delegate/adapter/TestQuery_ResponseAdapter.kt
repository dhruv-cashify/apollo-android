// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.named_fragment_delegate.adapter

import com.apollographql.apollo.api.CustomScalarAdapters
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ListResponseAdapter
import com.apollographql.apollo.api.internal.NullableResponseAdapter
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.json.JsonReader
import com.apollographql.apollo.api.internal.json.JsonWriter
import com.apollographql.apollo.api.internal.stringResponseAdapter
import com.apollographql.apollo.exception.UnexpectedNullValue
import com.example.named_fragment_delegate.TestQuery
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
          ResponseField.FieldSet("Droid", Hero.DroidHero.RESPONSE_FIELDS),
          ResponseField.FieldSet("Human", Hero.HumanHero.RESPONSE_FIELDS),
          ResponseField.FieldSet(null, Hero.OtherHero.RESPONSE_FIELDS),
        ),
      )
    )

    val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
  }

  class Hero(
    customScalarAdapters: CustomScalarAdapters
  ) : ResponseAdapter<TestQuery.Data.Hero> {
    val droidHeroAdapter: DroidHero =
        com.example.named_fragment_delegate.adapter.TestQuery_ResponseAdapter.Hero.DroidHero(customScalarAdapters)

    val humanHeroAdapter: HumanHero =
        com.example.named_fragment_delegate.adapter.TestQuery_ResponseAdapter.Hero.HumanHero(customScalarAdapters)

    val otherHeroAdapter: OtherHero =
        com.example.named_fragment_delegate.adapter.TestQuery_ResponseAdapter.Hero.OtherHero(customScalarAdapters)

    override fun fromResponse(reader: JsonReader, __typename: String?): TestQuery.Data.Hero {
      reader.beginObject()
      check(reader.nextName() == "__typename")
      val typename = reader.nextString()

      return when(typename) {
        "Droid" -> droidHeroAdapter.fromResponse(reader, typename)
        "Human" -> humanHeroAdapter.fromResponse(reader, typename)
        else -> otherHeroAdapter.fromResponse(reader, typename)
      }
      .also { reader.endObject() }
    }

    override fun toResponse(writer: JsonWriter, value: TestQuery.Data.Hero) {
      when(value) {
        is TestQuery.Data.Hero.DroidHero -> droidHeroAdapter.toResponse(writer, value)
        is TestQuery.Data.Hero.HumanHero -> humanHeroAdapter.toResponse(writer, value)
        is TestQuery.Data.Hero.OtherHero -> otherHeroAdapter.toResponse(writer, value)
      }
    }

    class DroidHero(
      customScalarAdapters: CustomScalarAdapters
    ) : ResponseAdapter<TestQuery.Data.Hero.DroidHero> {
      val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

      val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

      val primaryFunctionAdapter: ResponseAdapter<String?> =
          NullableResponseAdapter(stringResponseAdapter)

      val friendsAdapter: ResponseAdapter<List<TestQuery.Data.Hero.DroidHero.Friend?>?> =
          NullableResponseAdapter(ListResponseAdapter(NullableResponseAdapter(Friend(customScalarAdapters))))

      override fun fromResponse(reader: JsonReader, __typename: String?):
          TestQuery.Data.Hero.DroidHero {
        var __typename: String? = __typename
        var name: String? = null
        var primaryFunction: String? = null
        var friends: List<TestQuery.Data.Hero.DroidHero.Friend?>? = null
        reader.beginObject()
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                UnexpectedNullValue("__typename")
            1 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
            2 -> primaryFunction = primaryFunctionAdapter.fromResponse(reader)
            3 -> friends = friendsAdapter.fromResponse(reader)
            else -> break
          }
        }
        reader.endObject()
        return TestQuery.Data.Hero.DroidHero(
          __typename = __typename!!,
          name = name!!,
          primaryFunction = primaryFunction,
          friends = friends
        )
      }

      override fun toResponse(writer: JsonWriter, value: TestQuery.Data.Hero.DroidHero) {
        __typenameAdapter.toResponse(writer, value.__typename)
        nameAdapter.toResponse(writer, value.name)
        primaryFunctionAdapter.toResponse(writer, value.primaryFunction)
        friendsAdapter.toResponse(writer, value.friends)
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
          ),
          ResponseField(
            type = ResponseField.Type.List(ResponseField.Type.Named.Object("Character")),
            responseName = "friends",
            fieldName = "friends",
            arguments = emptyMap(),
            conditions = emptyList(),
            fieldSets = listOf(
              ResponseField.FieldSet(null, Friend.RESPONSE_FIELDS)
            ),
          )
        )

        val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
      }

      class Friend(
        customScalarAdapters: CustomScalarAdapters
      ) : ResponseAdapter<TestQuery.Data.Hero.DroidHero.Friend> {
        val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

        override fun fromResponse(reader: JsonReader, __typename: String?):
            TestQuery.Data.Hero.DroidHero.Friend {
          var name: String? = null
          reader.beginObject()
          while(true) {
            when (reader.selectName(RESPONSE_NAMES)) {
              0 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
              else -> break
            }
          }
          reader.endObject()
          return TestQuery.Data.Hero.DroidHero.Friend(
            name = name!!
          )
        }

        override fun toResponse(writer: JsonWriter, value: TestQuery.Data.Hero.DroidHero.Friend) {
          nameAdapter.toResponse(writer, value.name)
        }

        companion object {
          val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
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

    class HumanHero(
      customScalarAdapters: CustomScalarAdapters
    ) : ResponseAdapter<TestQuery.Data.Hero.HumanHero> {
      val __typenameAdapter: ResponseAdapter<String> = stringResponseAdapter

      val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

      val profileLinkAdapter: ResponseAdapter<Any> =
          customScalarAdapters.responseAdapterFor<Any>("URL")

      val friendsConnectionAdapter: ResponseAdapter<TestQuery.Data.Hero.HumanHero.FriendsConnection>
          = FriendsConnection(customScalarAdapters)

      override fun fromResponse(reader: JsonReader, __typename: String?):
          TestQuery.Data.Hero.HumanHero {
        var __typename: String? = __typename
        var name: String? = null
        var profileLink: Any? = null
        var friendsConnection: TestQuery.Data.Hero.HumanHero.FriendsConnection? = null
        reader.beginObject()
        while(true) {
          when (reader.selectName(RESPONSE_NAMES)) {
            0 -> __typename = __typenameAdapter.fromResponse(reader) ?: throw
                UnexpectedNullValue("__typename")
            1 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
            2 -> profileLink = profileLinkAdapter.fromResponse(reader) ?: throw
                UnexpectedNullValue("profileLink")
            3 -> friendsConnection = friendsConnectionAdapter.fromResponse(reader) ?: throw
                UnexpectedNullValue("friendsConnection")
            else -> break
          }
        }
        reader.endObject()
        return TestQuery.Data.Hero.HumanHero(
          __typename = __typename!!,
          name = name!!,
          profileLink = profileLink!!,
          friendsConnection = friendsConnection!!
        )
      }

      override fun toResponse(writer: JsonWriter, value: TestQuery.Data.Hero.HumanHero) {
        __typenameAdapter.toResponse(writer, value.__typename)
        nameAdapter.toResponse(writer, value.name)
        profileLinkAdapter.toResponse(writer, value.profileLink)
        friendsConnectionAdapter.toResponse(writer, value.friendsConnection)
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
            type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("URL")),
            responseName = "profileLink",
            fieldName = "profileLink",
            arguments = emptyMap(),
            conditions = emptyList(),
            fieldSets = emptyList(),
          ),
          ResponseField(
            type = ResponseField.Type.NotNull(ResponseField.Type.Named.Object("FriendsConnection")),
            responseName = "friendsConnection",
            fieldName = "friendsConnection",
            arguments = emptyMap(),
            conditions = emptyList(),
            fieldSets = listOf(
              ResponseField.FieldSet(null, FriendsConnection.RESPONSE_FIELDS)
            ),
          )
        )

        val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
      }

      class FriendsConnection(
        customScalarAdapters: CustomScalarAdapters
      ) : ResponseAdapter<TestQuery.Data.Hero.HumanHero.FriendsConnection> {
        val edgesAdapter:
            ResponseAdapter<List<TestQuery.Data.Hero.HumanHero.FriendsConnection.Edge?>?> =
            NullableResponseAdapter(ListResponseAdapter(NullableResponseAdapter(Edge(customScalarAdapters))))

        override fun fromResponse(reader: JsonReader, __typename: String?):
            TestQuery.Data.Hero.HumanHero.FriendsConnection {
          var edges: List<TestQuery.Data.Hero.HumanHero.FriendsConnection.Edge?>? = null
          reader.beginObject()
          while(true) {
            when (reader.selectName(RESPONSE_NAMES)) {
              0 -> edges = edgesAdapter.fromResponse(reader)
              else -> break
            }
          }
          reader.endObject()
          return TestQuery.Data.Hero.HumanHero.FriendsConnection(
            edges = edges
          )
        }

        override fun toResponse(writer: JsonWriter,
            value: TestQuery.Data.Hero.HumanHero.FriendsConnection) {
          edgesAdapter.toResponse(writer, value.edges)
        }

        companion object {
          val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
            ResponseField(
              type = ResponseField.Type.List(ResponseField.Type.Named.Object("FriendsEdge")),
              responseName = "edges",
              fieldName = "edges",
              arguments = emptyMap(),
              conditions = emptyList(),
              fieldSets = listOf(
                ResponseField.FieldSet(null, Edge.RESPONSE_FIELDS)
              ),
            )
          )

          val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
        }

        class Edge(
          customScalarAdapters: CustomScalarAdapters
        ) : ResponseAdapter<TestQuery.Data.Hero.HumanHero.FriendsConnection.Edge> {
          val nodeAdapter:
              ResponseAdapter<TestQuery.Data.Hero.HumanHero.FriendsConnection.Edge.Node?> =
              NullableResponseAdapter(Node(customScalarAdapters))

          override fun fromResponse(reader: JsonReader, __typename: String?):
              TestQuery.Data.Hero.HumanHero.FriendsConnection.Edge {
            var node: TestQuery.Data.Hero.HumanHero.FriendsConnection.Edge.Node? = null
            reader.beginObject()
            while(true) {
              when (reader.selectName(RESPONSE_NAMES)) {
                0 -> node = nodeAdapter.fromResponse(reader)
                else -> break
              }
            }
            reader.endObject()
            return TestQuery.Data.Hero.HumanHero.FriendsConnection.Edge(
              node = node
            )
          }

          override fun toResponse(writer: JsonWriter,
              value: TestQuery.Data.Hero.HumanHero.FriendsConnection.Edge) {
            nodeAdapter.toResponse(writer, value.node)
          }

          companion object {
            val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
              ResponseField(
                type = ResponseField.Type.Named.Object("Character"),
                responseName = "node",
                fieldName = "node",
                arguments = emptyMap(),
                conditions = emptyList(),
                fieldSets = listOf(
                  ResponseField.FieldSet(null, Node.RESPONSE_FIELDS)
                ),
              )
            )

            val RESPONSE_NAMES: List<String> = RESPONSE_FIELDS.map { it.responseName }
          }

          class Node(
            customScalarAdapters: CustomScalarAdapters
          ) : ResponseAdapter<TestQuery.Data.Hero.HumanHero.FriendsConnection.Edge.Node> {
            val nameAdapter: ResponseAdapter<String> = stringResponseAdapter

            override fun fromResponse(reader: JsonReader, __typename: String?):
                TestQuery.Data.Hero.HumanHero.FriendsConnection.Edge.Node {
              var name: String? = null
              reader.beginObject()
              while(true) {
                when (reader.selectName(RESPONSE_NAMES)) {
                  0 -> name = nameAdapter.fromResponse(reader) ?: throw UnexpectedNullValue("name")
                  else -> break
                }
              }
              reader.endObject()
              return TestQuery.Data.Hero.HumanHero.FriendsConnection.Edge.Node(
                name = name!!
              )
            }

            override fun toResponse(writer: JsonWriter,
                value: TestQuery.Data.Hero.HumanHero.FriendsConnection.Edge.Node) {
              nameAdapter.toResponse(writer, value.name)
            }

            companion object {
              val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
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
