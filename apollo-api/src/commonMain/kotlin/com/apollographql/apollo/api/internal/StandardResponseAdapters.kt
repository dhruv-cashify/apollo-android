package com.apollographql.apollo.api.internal

import com.apollographql.apollo.api.CustomScalarAdapters
import com.apollographql.apollo.api.internal.json.JsonReader
import com.apollographql.apollo.api.internal.json.JsonWriter

class ListResponseAdapter<T>(private val wrappedAdapter: ResponseAdapter<T>): ResponseAdapter<List<T>> {
  override fun fromResponse(reader: JsonReader, __typename: String?): List<T> {
    reader.beginArray()
    val list = mutableListOf<T>()
    while(reader.hasNext()) {
      list.add(wrappedAdapter.fromResponse(reader))
    }
    reader.endArray()
    return list
  }

  override fun toResponse(writer: JsonWriter, value: List<T>) {
    value.forEach {
      wrappedAdapter.toResponse(writer, it)
    }
  }
}

class NullableResponseAdapter<T:Any>(private val wrappedAdapter: ResponseAdapter<T>): ResponseAdapter<T?> {
  override fun fromResponse(reader: JsonReader, __typename: String?): T? {
    return if (reader.peek() == JsonReader.Token.NULL) {
      null
    } else {
      wrappedAdapter.fromResponse(reader)
    }
  }

  override fun toResponse(writer: JsonWriter, value: T?) {
    if (value == null) {
      writer.nullValue()
    } else {
      wrappedAdapter.toResponse(writer, value)
    }
  }
}

object stringResponseAdapter: ResponseAdapter<String> {
  override fun fromResponse(reader: JsonReader, __typename: String?): String {
    return reader.nextString()!!
  }

  override fun toResponse(writer: JsonWriter, value: String) {
    writer.value(value)
  }
}

object intResponseAdapter: ResponseAdapter<Int> {
  override fun fromResponse(reader: JsonReader, __typename: String?): Int {
    return reader.nextInt()
  }

  override fun toResponse(writer: JsonWriter, value: Int) {
    writer.value(value)
  }
}

object doubleResponseAdapter: ResponseAdapter<Double> {
  override fun fromResponse(reader: JsonReader, __typename: String?): Double {
    return reader.nextDouble()
  }

  override fun toResponse(writer: JsonWriter, value: Double) {
    writer.value(value)
  }
}

object booleanResponseAdapter: ResponseAdapter<Boolean> {
  override fun fromResponse(reader: JsonReader, __typename: String?): Boolean {
    return reader.nextBoolean()
  }

  override fun toResponse(writer: JsonWriter, value: Boolean) {
    writer.value(value)
  }
}
