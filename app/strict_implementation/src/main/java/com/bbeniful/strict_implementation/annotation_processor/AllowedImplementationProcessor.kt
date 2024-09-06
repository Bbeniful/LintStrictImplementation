package com.bbeniful.strict_implementation.annotation_processor

import com.google.auto.service.AutoService
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

@AutoService(Processor::class)
class AllowedImplementationProcessor : AbstractProcessor() {

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(AllowedImplementation::class.java.canonicalName)
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }

    override fun process(
        annotations: MutableSet<out TypeElement>,
        roundEnv: RoundEnvironment
    ): Boolean {
        val elements = roundEnv.rootElements.filter { it.kind == ElementKind.CLASS }

        elements.forEach { element ->
            val typeElement = element as TypeElement
            val interfaces = typeElement.interfaces

            interfaces.forEach { iface ->
                if (iface.toString() == MyInterface::class.java.canonicalName) {
                    val hasAnnotation = element.getAnnotation(AllowedImplementation::class.java) != null
                    if (!hasAnnotation) {
                        processingEnv.messager.printMessage(
                            Diagnostic.Kind.ERROR,
                            "${element.simpleName} implements MyInterface but is not annotated with @AllowedImplementation!"
                        )
                    }
                }
            }
        }

        return true
    }
}