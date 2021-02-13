// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.unique_type_name

import com.apollographql.apollo.api.CustomScalarAdapters
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.QueryDocumentMinifier
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.example.unique_type_name.adapter.HeroDetailQuery_ResponseAdapter
import com.example.unique_type_name.fragment.HeroDetails
import com.example.unique_type_name.type.Episode
import kotlin.Double
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class HeroDetailQuery : Query<HeroDetailQuery.Data> {
  override fun operationId(): String = OPERATION_ID

  override fun queryDocument(): String = QUERY_DOCUMENT

  override fun variables(): Operation.Variables = Operation.EMPTY_VARIABLES

  override fun name(): String = OPERATION_NAME

  override fun adapter(customScalarAdapters: CustomScalarAdapters): ResponseAdapter<Data> {
    val adapter = customScalarAdapters.getOperationAdapter(name()) {
      HeroDetailQuery_ResponseAdapter(customScalarAdapters)
    }
    return adapter
  }

  override fun responseFields(): List<ResponseField.FieldSet> = listOf(
    ResponseField.FieldSet(null, HeroDetailQuery_ResponseAdapter.RESPONSE_FIELDS)
  )
  /**
   * The query type, represents all of the entry points into our object graph
   */
  data class Data(
    val heroDetailQuery: HeroDetailQuery?
  ) : Operation.Data {
    /**
     * A character from the Star Wars universe
     */
    interface HeroDetailQuery {
      val __typename: String

      /**
       * The name of the character
       */
      val name: String

      /**
       * The friends of the character, or an empty list if they have none
       */
      val friends: List<Friend?>?

      /**
       * A character from the Star Wars universe
       */
      interface Friend {
        /**
         * The name of the character
         */
        val name: String
      }

      interface Human : HeroDetailQuery {
        override val __typename: String

        /**
         * The name of the character
         */
        override val name: String

        /**
         * The friends of the character, or an empty list if they have none
         */
        override val friends: List<Friend?>?

        /**
         * Height in the preferred unit, default is meters
         */
        val height: Double?

        /**
         * A character from the Star Wars universe
         */
        interface Friend : HeroDetailQuery.Friend {
          /**
           * The name of the character
           */
          override val name: String

          /**
           * The movies this character appears in
           */
          val appearsIn: List<Episode?>

          /**
           * The friends of the character, or an empty list if they have none
           */
          val friends: List<Friend?>?

          /**
           * A character from the Star Wars universe
           */
          interface Friend {
            val __typename: String

            interface Character : Friend, HeroDetails {
              override val __typename: String

              /**
               * The name of the character
               */
              override val name: String

              /**
               * The friends of the character exposed as a connection with edges
               */
              override val friendsConnection: FriendsConnection

              /**
               * A connection object for a character's friends
               */
              interface FriendsConnection : HeroDetails.FriendsConnection {
                /**
                 * The total number of friends
                 */
                override val totalCount: Int?

                /**
                 * The edges for each of the character's friends.
                 */
                override val edges: List<Edge?>?

                /**
                 * An edge object for a character's friends
                 */
                interface Edge : HeroDetails.FriendsConnection.Edge {
                  /**
                   * The character represented by this friendship edge
                   */
                  override val node: Node?

                  /**
                   * A character from the Star Wars universe
                   */
                  interface Node : HeroDetails.FriendsConnection.Edge.Node {
                    /**
                     * The name of the character
                     */
                    override val name: String
                  }
                }
              }
            }

            companion object {
              fun Friend.asCharacter(): Character? = this as? Character

              fun Friend.heroDetails(): HeroDetails? = this as? HeroDetails
            }
          }
        }
      }

      data class HumanHeroDetailQuery(
        override val __typename: String,
        /**
         * The name of the character
         */
        override val name: String,
        /**
         * The friends of the character, or an empty list if they have none
         */
        override val friends: List<Friend?>?,
        /**
         * Height in the preferred unit, default is meters
         */
        override val height: Double?
      ) : HeroDetailQuery, Human {
        /**
         * A character from the Star Wars universe
         */
        data class Friend(
          /**
           * The name of the character
           */
          override val name: String,
          /**
           * The movies this character appears in
           */
          override val appearsIn: List<Episode?>,
          /**
           * The friends of the character, or an empty list if they have none
           */
          override val friends: List<Friend?>?
        ) : HeroDetailQuery.Friend, Human.Friend {
          /**
           * A character from the Star Wars universe
           */
          interface Friend : Human.Friend.Friend {
            override val __typename: String

            data class CharacterFriend(
              override val __typename: String,
              /**
               * The name of the character
               */
              override val name: String,
              /**
               * The friends of the character exposed as a connection with edges
               */
              override val friendsConnection: FriendsConnection
            ) : Human.Friend.Friend, Human.Friend.Friend.Character, HeroDetails, Friend {
              /**
               * A connection object for a character's friends
               */
              data class FriendsConnection(
                /**
                 * The total number of friends
                 */
                override val totalCount: Int?,
                /**
                 * The edges for each of the character's friends.
                 */
                override val edges: List<Edge?>?
              ) : Human.Friend.Friend.Character.FriendsConnection, HeroDetails.FriendsConnection {
                /**
                 * An edge object for a character's friends
                 */
                data class Edge(
                  /**
                   * The character represented by this friendship edge
                   */
                  override val node: Node?
                ) : Human.Friend.Friend.Character.FriendsConnection.Edge,
                    HeroDetails.FriendsConnection.Edge {
                  /**
                   * A character from the Star Wars universe
                   */
                  data class Node(
                    /**
                     * The name of the character
                     */
                    override val name: String
                  ) : Human.Friend.Friend.Character.FriendsConnection.Edge.Node,
                      HeroDetails.FriendsConnection.Edge.Node
                }
              }
            }

            data class OtherFriend(
              override val __typename: String
            ) : Human.Friend.Friend, Friend
          }
        }
      }

      data class OtherHeroDetailQuery(
        override val __typename: String,
        /**
         * The name of the character
         */
        override val name: String,
        /**
         * The friends of the character, or an empty list if they have none
         */
        override val friends: List<Friend?>?
      ) : HeroDetailQuery {
        /**
         * A character from the Star Wars universe
         */
        data class Friend(
          /**
           * The name of the character
           */
          override val name: String
        ) : HeroDetailQuery.Friend
      }

      companion object {
        fun HeroDetailQuery.asHuman(): Human? = this as? Human
      }
    }
  }

  companion object {
    const val OPERATION_ID: String =
        "69ef2ada86ec094537d524f05f680155b2331dabf1b8420f7f63a84202ed34f7"

    val QUERY_DOCUMENT: String = QueryDocumentMinifier.minify(
          """
          |query HeroDetailQuery {
          |  heroDetailQuery {
          |    __typename
          |    name
          |    friends {
          |      name
          |    }
          |    ... on Human {
          |      height
          |      friends {
          |        appearsIn
          |        friends {
          |          __typename
          |          ...HeroDetails
          |        }
          |      }
          |    }
          |  }
          |}
          |fragment HeroDetails on Character {
          |  __typename
          |  name
          |  friendsConnection {
          |    totalCount
          |    edges {
          |      node {
          |        name
          |      }
          |    }
          |  }
          |}
          """.trimMargin()
        )

    val OPERATION_NAME: String = "HeroDetailQuery"
  }
}
