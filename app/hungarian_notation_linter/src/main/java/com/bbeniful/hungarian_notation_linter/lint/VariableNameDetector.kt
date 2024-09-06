package com.bbeniful.hungarian_notation_linter.lint

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import org.jetbrains.uast.UElement
import org.jetbrains.uast.UField
import org.jetbrains.uast.UVariable

class VariableNameDetector : Detector(), SourceCodeScanner {

    companion object {
        val ISSUE: Issue = Issue.create(
            id = "InvalidVariableName",
            briefDescription = "Invalid variable name",
            explanation = "Variable names should not start with 'm' followed by an uppercase letter.",
            category = Category.CORRECTNESS,
            priority = 6,
            severity = Severity.WARNING,
            implementation = Implementation(
                VariableNameDetector::class.java,
                Scope.JAVA_FILE_SCOPE
            )
        )
    }

    override fun getApplicableUastTypes(): List<Class<out UElement>> {
        return listOf(UField::class.java, UVariable::class.java)
    }

    override fun createUastHandler(context: JavaContext): UElementHandler {
        return object : UElementHandler() {
            override fun visitField(node: UField) {
                checkVariableName(context, node)
            }

            override fun visitVariable(node: UVariable) {
                checkVariableName(context, node)
            }
        }
    }

    private fun checkVariableName(context: JavaContext, variable: UVariable) {
        val variableName = variable.name ?: return
        if (variableName.matches(Regex("^m[A-Z].*"))) {
            context.report(
                ISSUE,
                variable as UElement,
                context.getNameLocation(variable),
                "Variable names should not start with 'm' followed by an uppercase letter."
            )
        }
    }
}