// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.named_fragment_delegate

import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.QueryDocumentMinifier
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.example.named_fragment_delegate.adapter.TestQuery_ResponseAdapter
import com.example.named_fragment_delegate.fragment.DroidDetails
import com.example.named_fragment_delegate.fragment.HumanDetails
import kotlin.Any
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class TestQuery : Query<TestQuery.Data> {
  override fun operationId(): String = OPERATION_ID

  override fun queryDocument(): String = QUERY_DOCUMENT

  override fun variables(): Operation.Variables = Operation.EMPTY_VARIABLES

  override fun name(): String = OPERATION_NAME

  override fun adapter(): ResponseAdapter<Data> = TestQuery_ResponseAdapter
  override fun responseFields(): List<ResponseField.FieldSet> = listOf(
    ResponseField.FieldSet(null, TestQuery_ResponseAdapter.RESPONSE_FIELDS)
  )
  /**
   * The query type, represents all of the entry points into our object graph
   */
  data class Data(
    val hero: Hero?
  ) : Operation.Data {
    /**
     * A character from the Star Wars universe
     */
    interface Hero {
      val __typename: String

      interface Droid : Hero, DroidDetails {
        /**
         * This droid's friends, or an empty list if they have none
         */
        override val friends: List<Friends?>?

        /**
         * A character from the Star Wars universe
         */
        interface Friends : DroidDetails.Friends
      }

      interface Human : Hero, HumanDetails {
        /**
         * The friends of the human exposed as a connection with edges
         */
        override val friendsConnection: FriendsConnection

        /**
         * A connection object for a character's friends
         */
        interface FriendsConnection : HumanDetails.FriendsConnection {
          /**
           * The edges for each of the character's friends.
           */
          override val edges: List<Edges?>?

          /**
           * An edge object for a character's friends
           */
          interface Edges : HumanDetails.FriendsConnection.Edges {
            /**
             * The character represented by this friendship edge
             */
            override val node: Node?

            /**
             * A character from the Star Wars universe
             */
            interface Node : HumanDetails.FriendsConnection.Edges.Node
          }
        }
      }

      data class DroidHero(
        override val __typename: String,
        /**
         * What others call this droid
         */
        override val name: String,
        /**
         * This droid's primary function
         */
        override val primaryFunction: String?,
        /**
         * This droid's friends, or an empty list if they have none
         */
        override val friends: List<Friends?>?
      ) : Hero, Droid, DroidDetails {
        /**
         * A character from the Star Wars universe
         */
        data class Friends(
          /**
           * The name of the character
           */
          override val name: String
        ) : Droid.Friends, DroidDetails.Friends
      }

      data class HumanHero(
        override val __typename: String,
        /**
         * What this human calls themselves
         */
        override val name: String,
        /**
         * Profile link
         */
        override val profileLink: Any,
        /**
         * The friends of the human exposed as a connection with edges
         */
        override val friendsConnection: FriendsConnection
      ) : Hero, Human, HumanDetails {
        /**
         * A connection object for a character's friends
         */
        data class FriendsConnection(
          /**
           * The edges for each of the character's friends.
           */
          override val edges: List<Edges?>?
        ) : Human.FriendsConnection, HumanDetails.FriendsConnection {
          /**
           * An edge object for a character's friends
           */
          data class Edges(
            /**
             * The character represented by this friendship edge
             */
            override val node: Node?
          ) : Human.FriendsConnection.Edges, HumanDetails.FriendsConnection.Edges {
            /**
             * A character from the Star Wars universe
             */
            data class Node(
              /**
               * The name of the character
               */
              override val name: String
            ) : Human.FriendsConnection.Edges.Node, HumanDetails.FriendsConnection.Edges.Node
          }
        }
      }

      data class OtherHero(
        override val __typename: String
      ) : Hero

      companion object {
        fun Hero.asDroid(): Droid? = this as? Droid

        fun Hero.droidDetails(): DroidDetails? = this as? DroidDetails

        fun Hero.asHuman(): Human? = this as? Human

        fun Hero.humanDetails(): HumanDetails? = this as? HumanDetails
      }
    }
  }

  companion object {
    const val OPERATION_ID: String =
        "727bf302bb93ddf9e3e12bad4506044461b67069ac0121c4f54417b9a98e42d4"

    val QUERY_DOCUMENT: String = QueryDocumentMinifier.minify(
          """
          |query TestQuery {
          |  hero {
          |    __typename
          |    ...DroidDetails
          |    ...HumanDetails
          |  }
          |}
          |fragment DroidDetails on Droid {
          |  __typename
          |  name
          |  primaryFunction
          |  friends {
          |    name
          |  }
          |}
          |fragment HumanDetails on Human {
          |  __typename
          |  name
          |  profileLink
          |  friendsConnection {
          |    edges {
          |      node {
          |        name
          |      }
          |    }
          |  }
          |}
          """.trimMargin()
        )

    val OPERATION_NAME: String = "TestQuery"
  }
}
