// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.simple_fragment_with_inline_fragments.fragment

import kotlin.Double
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
interface HeroDetails {
  val __typename: String

  /**
   * The name of the character
   */
  val name: String

  /**
   * The friends of the character, or an empty list if they have none
   */
  val friends: List<Friends?>?

  /**
   * A character from the Star Wars universe
   */
  interface Friends {
    val __typename: String

    /**
     * The name of the character
     */
    val name: String

    interface Human : Friends {
      /**
       * Height in the preferred unit, default is meters
       */
      val height: Double?
    }

    interface Droid : Friends {
      /**
       * This droid's primary function
       */
      val primaryFunction: String?
    }

    companion object {
      fun Friends.asHuman(): Human? = this as? Human

      fun Friends.asDroid(): Droid? = this as? Droid
    }
  }

  companion object {
    val FRAGMENT_DEFINITION: String = """
        |fragment HeroDetails on Character {
        |  __typename
        |  name
        |  friends {
        |    __typename
        |    name
        |    ... on Human {
        |      height
        |    }
        |    ... on Droid {
        |      primaryFunction
        |    }
        |  }
        |}
        """.trimMargin()
  }
}
