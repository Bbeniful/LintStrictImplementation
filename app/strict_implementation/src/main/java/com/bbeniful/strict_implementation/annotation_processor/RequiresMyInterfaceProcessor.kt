package com.bbeniful.strict_implementation.annotation_processor

import com.google.auto.service.AutoService
import javax.annotation.processing.Processor
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.ElementKind
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

@AutoService(Processor::class)
class RequiresMyInterfaceProcessor : AbstractProcessor() {

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(RequiresMyInterface::class.java.canonicalName)
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }

    override fun process(
        annotations: MutableSet<out TypeElement>,
        roundEnv: RoundEnvironment
    ): Boolean {
        val annotatedElements = roundEnv.getElementsAnnotatedWith(RequiresMyInterface::class.java)

        for (element in annotatedElements) {
            if (element.kind != ElementKind.CLASS) {
                processingEnv.messager.printMessage(
                    Diagnostic.Kind.ERROR,
                    "@RequiresMyInterface can only be applied to classes.",
                    element
                )
                return true
            }

            val classElement = element as TypeElement

            val implementsMyInterface = classElement.interfaces.any {
                it.toString() == MyInterface::class.java.canonicalName
            }

            if (!implementsMyInterface && element.kind == ElementKind.CLASS) {
                processingEnv.messager.printMessage(
                    Diagnostic.Kind.ERROR,
                    "Class ${classElement.simpleName} is annotated with @RequiresMyInterface but does not implement MyInterface.",
                    element
                )
            }

            if (classElement.modifiers.contains(Modifier.ABSTRACT)) {
                processingEnv.messager.printMessage(
                    Diagnostic.Kind.WARNING,
                    "Class ${classElement.simpleName} is abstract and cannot fully implement MyInterface.",
                    element
                )
            }
        }

        return true
    }
}