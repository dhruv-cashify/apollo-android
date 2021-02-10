// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.root_query_inline_fragment

import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.QueryDocumentMinifier
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.example.root_query_inline_fragment.adapter.TestQuery_ResponseAdapter
import com.example.root_query_inline_fragment.type.Episode
import kotlin.Double
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
    ResponseField.FieldSet("Query", TestQuery_ResponseAdapter.QueryData.RESPONSE_FIELDS),
    ResponseField.FieldSet(null, TestQuery_ResponseAdapter.OtherData.RESPONSE_FIELDS),
  )
  /**
   * The query type, represents all of the entry points into our object graph
   */
  interface Data : Operation.Data {
    val __typename: String

    interface Query : Data {
      val hero: Hero?

      val droid: Droid?

      /**
       * A character from the Star Wars universe
       */
      interface Hero {
        val __typename: String

        /**
         * The name of the character
         */
        val name: String

        /**
         * The movies this character appears in
         */
        val appearsIn: List<Episode?>

        interface Human : Hero {
          /**
           * Height in the preferred unit, default is meters
           */
          val height: Double?
        }

        companion object {
          fun Hero.asHuman(): Human? = this as? Human
        }
      }

      /**
       * An autonomous mechanical character in the Star Wars universe
       */
      interface Droid {
        val __typename: String

        interface Droid : Query.Droid {
          /**
           * What others call this droid
           */
          val name: String

          /**
           * This droid's primary function
           */
          val primaryFunction: String?
        }

        companion object {
          fun Query.Droid.asDroid(): Droid? = this as? Droid
        }
      }
    }

    data class QueryData(
      override val __typename: String,
      override val hero: Hero?,
      override val droid: Droid?
    ) : Data, Query {
      /**
       * A character from the Star Wars universe
       */
      interface Hero : Query.Hero {
        data class HumanHero(
          override val __typename: String,
          /**
           * The name of the character
           */
          override val name: String,
          /**
           * The movies this character appears in
           */
          override val appearsIn: List<Episode?>,
          /**
           * Height in the preferred unit, default is meters
           */
          override val height: Double?
        ) : Query.Hero, Query.Hero.Human, Hero

        data class OtherHero(
          override val __typename: String,
          /**
           * The name of the character
           */
          override val name: String,
          /**
           * The movies this character appears in
           */
          override val appearsIn: List<Episode?>
        ) : Query.Hero, Hero
      }

      /**
       * An autonomous mechanical character in the Star Wars universe
       */
      interface Droid : Query.Droid {
        data class DroidDroid(
          override val __typename: String,
          /**
           * What others call this droid
           */
          override val name: String,
          /**
           * This droid's primary function
           */
          override val primaryFunction: String?
        ) : Query.Droid, Query.Droid.Droid, Droid

        data class OtherDroid(
          override val __typename: String
        ) : Query.Droid, Droid
      }
    }

    data class OtherData(
      override val __typename: String
    ) : Data

    companion object {
      fun Data.asQuery(): Query? = this as? Query
    }
  }

  companion object {
    const val OPERATION_ID: String =
        "25584d760eab0f41189b9f2bbdbba3c0ec491aced65ef23924ecdc8f41ffe78c"

    val QUERY_DOCUMENT: String = QueryDocumentMinifier.minify(
          """
          |query TestQuery {
          |  __typename
          |  ... on Query {
          |    __typename
          |    hero {
          |      __typename
          |      name
          |      appearsIn
          |      ... on Human {
          |        height
          |      }
          |    }
          |    droid(id: 1) {
          |      __typename
          |      ... on Droid {
          |        name
          |        primaryFunction
          |      }
          |    }
          |  }
          |}
          """.trimMargin()
        )

    val OPERATION_NAME: String = "TestQuery"
  }
}
