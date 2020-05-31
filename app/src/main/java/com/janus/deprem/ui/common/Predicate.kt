package com.janus.deprem.ui.common

import androidx.recyclerview.selection.SelectionTracker

class Predicate : SelectionTracker.SelectionPredicate<Long?>() {
    override fun canSetStateForKey(key: Long, nextState: Boolean) = true
    override fun canSetStateAtPosition(position: Int, nextState: Boolean) = true
    override fun canSelectMultiple() = true
}