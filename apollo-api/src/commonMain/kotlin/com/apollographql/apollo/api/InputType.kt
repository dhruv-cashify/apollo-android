package com.apollographql.apollo.api

import com.apollographql.apollo.api.internal.InputFieldMarshaller
import com.apollographql.apollo.api.internal.ResponseAdapter

interface InputType<T> {

  fun adapter(customScalarAdapters: CustomScalarAdapters): ResponseAdapter<T>
}
