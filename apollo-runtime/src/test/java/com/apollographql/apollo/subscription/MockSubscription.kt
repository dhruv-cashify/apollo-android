package com.apollographql.apollo.subscription

import com.apollographql.apollo.api.CustomScalarAdapters
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.Subscription
import com.apollographql.apollo.api.internal.InputFieldMarshaller
import com.apollographql.apollo.api.internal.anyResponseAdapter
import com.apollographql.apollo.api.internal.json.JsonWriter

class MockSubscription(
    private val queryDocument: String = "subscription{commentAdded{id  name}",
    private val variables: Map<String, Any?> = emptyMap(),
    private val name: String = "SomeSubscription",
    private val operationId: String = "someId"
) : Subscription<Operation.Data> {
  override fun queryDocument(): String = queryDocument

  override fun variables(): Operation.Variables = object : Operation.Variables {
    override fun valueMap(): Map<String, Any?> = variables

    override fun toResponse(writer: JsonWriter, customScalarAdapters: CustomScalarAdapters) {
      anyResponseAdapter.toResponse(writer, variables)
    }
  }

  override fun adapter(customScalarAdapters: CustomScalarAdapters) = throw UnsupportedOperationException()

  override fun name(): String = name

  override fun operationId(): String = operationId
  override fun responseFields(): List<ResponseField.FieldSet> {
    return emptyList()
  }
}
