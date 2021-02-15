package com.apollographql.apollo.compiler.backend.codegen

import com.apollographql.apollo.api.CustomScalarAdapters
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.internal.json.JsonWriter
import com.apollographql.apollo.compiler.applyIf
import com.apollographql.apollo.compiler.backend.ast.CodeGenerationAst
import com.apollographql.apollo.compiler.escapeKotlinReservedWord
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.asTypeName
import com.squareup.kotlinpoet.joinToCode

private fun List<CodeGenerationAst.Field>.constructorFunSpec() = FunSpec
    .constructorBuilder()
    .addParameters(map { variable ->
      ParameterSpec
          .builder(
              name = variable.name.escapeKotlinReservedWord(),
              type = variable.type.asTypeName()
          )
          .applyIf(variable.type.nullable) { defaultValue("%T.absent()", Input::class.asClassName()) }
          .build()
    })
    .build()

private fun List<CodeGenerationAst.Field>.variablePropertySpec(enclosingClassName: String) = PropertySpec
    .builder("variables", Operation.Variables::class)
    .addModifiers(KModifier.PRIVATE)
    .addAnnotation(Transient::class)
    .initializer("%L", TypeSpec.anonymousClassBuilder()
        .addSuperinterface(Operation.Variables::class)
        .addFunction(variablesValueMapSpec(enclosingClassName))
        .addFunction(variablesToResponseSpec(enclosingClassName))
        .build()
    )
    .build()


private fun List<CodeGenerationAst.Field>.variablesValueMapSpec(enclosingClassName: String): FunSpec {
  return FunSpec
      .builder("valueMap")
      .addModifiers(KModifier.OVERRIDE)
      .returns(Map::class.asClassName().parameterizedBy(String::class.asClassName(), Any::class.asClassName().copy(nullable = true)))
      .beginControlFlow("return mutableMapOf<%T, %T>().apply", String::class, Any::class.asClassName().copy(nullable = true))
      .addCode(
          map { field ->
            if (field.type.nullable) {
              CodeBlock.builder()
                  .addStatement(
                      "if·(this@%L.%L.defined)·{",
                      enclosingClassName.escapeKotlinReservedWord(),
                      field.name.escapeKotlinReservedWord()
                  )
                  .indent()
                  .addStatement(
                      "this[%S]·=·this@%L.%L.value",
                      field.schemaName,
                      enclosingClassName.escapeKotlinReservedWord(),
                      field.name.escapeKotlinReservedWord()
                  )
                  .unindent()
                  .addStatement("}")
                  .build()
            } else {
              CodeBlock.of(
                  "this[%S]·=·this@%L.%L\n",
                  field.schemaName,
                  enclosingClassName.escapeKotlinReservedWord(),
                  field.name.escapeKotlinReservedWord()
              )
            }
          }.joinToCode(separator = "")
      )
      .endControlFlow()
      .build()
}

private fun List<CodeGenerationAst.Field>.variablesToResponseSpec(enclosingClassName: String): FunSpec {
  return FunSpec
      .builder("toResponse")
      .addModifiers(KModifier.OVERRIDE)
      .addParameter("writer", JsonWriter::class.asTypeName())
      .addParameter("customScalarAdapters", CustomScalarAdapters::class.asTypeName())
      .addCode(this.adapterVals())
      .addCode(this.writeCode("this@$enclosingClassName"))
      .build()
}
private fun List<CodeGenerationAst.Field>.adapterVals(): CodeBlock {
  val builder = CodeBlock.builder()
  forEach {
    builder.addStatement("val %L = %L", kotlinNameForAdapterField(it.type), adapterInitializer(it.type))
  }
  return builder.build()
}

internal fun List<CodeGenerationAst.Field>.variablesFunSpec() = FunSpec.builder("variables")
    .addModifiers(KModifier.OVERRIDE)
    .returns(Operation.Variables::class.asClassName())
    .apply {
      if (isNotEmpty()) {
        addStatement("return variables")
      } else {
        addStatement("return %T.EMPTY_VARIABLES", Operation::class)
      }
    }
    .build()

internal fun TypeSpec.Builder.addVariablesIfNeeded(variables: List<CodeGenerationAst.Field>, enclosingClassName: String) = applyIf(variables.isNotEmpty()) {
  addModifiers(KModifier.DATA)
  primaryConstructor(variables.constructorFunSpec())
  addProperties(variables.map { variable -> variable.asPropertySpec(CodeBlock.of(variable.name.escapeKotlinReservedWord())) })
  addProperty(variables.variablePropertySpec(enclosingClassName))
}
