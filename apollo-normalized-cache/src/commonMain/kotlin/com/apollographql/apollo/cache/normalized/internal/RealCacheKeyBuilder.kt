package com.apollographql.apollo.cache.normalized.internal

import com.apollographql.apollo.api.CustomScalarAdapters
import com.apollographql.apollo.api.EnumValue
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.InputType
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.ResponseField.Companion.isArgumentValueVariableType
import com.apollographql.apollo.api.internal.anyResponseAdapter
import com.apollographql.apollo.api.internal.json.JsonWriter
import okio.Buffer
import okio.IOException

class RealCacheKeyBuilder : CacheKeyBuilder {

  override fun build(field: ResponseField, variables: Operation.Variables): String {
    if (field.arguments.isEmpty()) {
      return field.fieldName
    }
    val resolvedArguments = field.arguments.resolveVariables(variables)
    return try {
      val serializedArguments = Buffer().apply {
        anyResponseAdapter.toResponse(JsonWriter.of(this), resolvedArguments)
      }.readUtf8()
      "${field.fieldName}(${serializedArguments})"
    } catch (e: IOException) {
      throw RuntimeException(e)
    }
  }

  @Suppress("UNCHECKED_CAST")
  private fun Any?.resolveVariables(variables: Operation.Variables): Any? {
    return when (this) {
      null -> null
      is Map<*, *> -> {
        mapNotNull {
          val value = it.value.resolveVariableIfNeeded(variables)
          /**
           * filter out the absent inputs
           */
          (it.key as String) to if (value is Input<*>) {
            if (value.defined) {
              value.value
            } else {
              return@mapNotNull null
            }
          } else {
            value
          }
        }.map {
          it.first to it.second.resolveVariables(variables)
        }.sortedBy { it.first }
            .toMap()

      }
      is List<*> -> {
        map {
          it.resolveVariables(variables)
        }
      }
      else -> this.toJsonAny()
    }
  }

  private fun Any?.resolveVariableIfNeeded(variables: Operation.Variables): Any? {
    return if (this is Map<*, *> && isArgumentValueVariableType(this as Map<String, Any?>)) {
      val variableName = this[ResponseField.VARIABLE_NAME_KEY]
      return variables.valueMap()[variableName] ?: error("no '$variableName' variable found")
    } else {
      this
    }
  }

  /**
   * Takes an input type that comes either from default values or operation variables
   * and turn that into something that we can write as json
   */
  private fun Any?.toJsonAny(): Any? {
    return when (this) {
      is InputType<*> -> {
        // XXX fix custom scalars
        val writer = MapJsonWriter()
        (this as InputType<Any?>)
            .adapter(customScalarAdapters = CustomScalarAdapters(emptyMap()))
            .toResponse(writer, this)
        writer.root()
      }
      is Int -> this
      is Double -> this
      is String -> this
      is Boolean -> this
      is EnumValue -> this.rawValue
      else -> error("Cannot serialize argument $this")
    }
  }
}
