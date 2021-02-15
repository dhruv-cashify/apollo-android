package com.apollographql.apollo.compiler.backend.codegen

import com.apollographql.apollo.api.CustomScalarAdapters
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.json.JsonReader
import com.apollographql.apollo.compiler.applyIf
import com.apollographql.apollo.compiler.backend.ast.CodeGenerationAst
import com.apollographql.apollo.compiler.escapeKotlinReservedWord
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.asTypeName

internal fun CodeGenerationAst.InputType.typeSpec(generateAsInternal: Boolean = false) =
    TypeSpec
        .classBuilder(name.escapeKotlinReservedWord())
        .applyIf(description.isNotBlank()) { addKdoc("%L\n", description) }
        .applyIf(generateAsInternal) { addModifiers(KModifier.INTERNAL) }
        .addAnnotation(suppressWarningsAnnotation)
        .addModifiers(KModifier.DATA)
        .addSuperinterface(com.apollographql.apollo.api.InputType::class.asClassName().parameterizedBy(typeRef.asTypeName()))
        .primaryConstructor(primaryConstructorSpec)
        .addProperties(
            fields.map { field ->
              field.asPropertySpec(
                  initializer = CodeBlock.of(field.name.escapeKotlinReservedWord())
              )
            }
        )
        .addFunction(adapterFunSpec())
        .build()


private val CodeGenerationAst.InputType.primaryConstructorSpec: FunSpec
  get() {
    return FunSpec
        .constructorBuilder()
        .addParameters(fields.map { field -> field.parameterSpec() })
        .build()
  }

private fun CodeGenerationAst.Field.parameterSpec(): ParameterSpec {
  val defaultValue = defaultValue.toDefaultValueCodeBlock(fieldType = type)
  return ParameterSpec
      .builder(name = name.escapeKotlinReservedWord(), type = type.asTypeName())
      .applyIf(defaultValue != null) { defaultValue(defaultValue!!) }
      .build()
}

private fun CodeGenerationAst.TypeRef.asInputObjectAdapterTypeName(): TypeName {
  return ClassName("$packageName.adapter", kotlinNameForResponseAdapter(name))
}

private fun CodeGenerationAst.InputType.adapterFunSpec(): FunSpec {
  val body = CodeBlock.builder().apply {
    addStatement("val adapter = customScalarAdapters.getInputObjectAdapter(%S) {", name)
    indent()
    addStatement("%T(customScalarAdapters)", typeRef.asInputObjectAdapterTypeName())
    unindent()
    addStatement("}")
    addStatement("return adapter")
  }.build()

  return FunSpec.builder("adapter")
      .addModifiers(KModifier.OVERRIDE)
      .addParameter(ParameterSpec.builder("customScalarAdapters", CustomScalarAdapters::class.asTypeName()).build())
      .returns(ResponseAdapter::class.asClassName().parameterizedBy(typeRef.asTypeName()))
      .addCode(body)
      .build()

}

private fun CodeGenerationAst.InputType.readFromResponseFunSpec(): FunSpec {
  return FunSpec.builder("fromResponse")
      .returns(this.typeRef.asTypeName())
      .addParameter(ParameterSpec.builder("reader", JsonReader::class).build())
      .addModifiers(KModifier.OVERRIDE)
      .addCode("throw %T(%S)", UnsupportedOperationException::class.asTypeName(), "InputObject used in output position")
      .build()
}

internal fun CodeGenerationAst.InputType.responseAdapterTypeSpec(generateAsInternal: Boolean = false): TypeSpec {

  return TypeSpec.classBuilder(kotlinNameForResponseAdapter(this.graphqlName))
      .primaryConstructor(
          FunSpec.constructorBuilder()
              .addParameter(ParameterSpec.builder("customScalarAdapters", CustomScalarAdapters::class.asTypeName()).build())
              .build()
      )
      .addSuperinterface(ResponseAdapter::class.asTypeName().parameterizedBy(this@responseAdapterTypeSpec.typeRef.asTypeName()))
      .apply {
        if (fields.isNotEmpty()) {
          addProperties(fields.adapterPropertySpecs())
        }
      }
      .addFunction(readFromResponseFunSpec())
      .addFunction(fields.writeObjectToResponseFunSpec(true, typeRef))
      .applyIf(generateAsInternal) { addModifiers(KModifier.INTERNAL) }
      .build()
}