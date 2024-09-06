package com.bbeniful.hungarian_notation_linter.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.Issue

@Suppress("UnstableApiUsage")
class CustomIssueRegistry : IssueRegistry() {
    override val issues: List<Issue>
        get() = listOf(VariableNameDetector.ISSUE)

    override val api: Int
        get() = com.android.tools.lint.detector.api.CURRENT_API
}