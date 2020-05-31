package com.janus.deprem.ui.common

import androidx.recyclerview.selection.ItemKeyProvider

class KeyProvider : ItemKeyProvider<Long>(SCOPE_MAPPED) {
    override fun getKey(position: Int) = position.toLong()
    override fun getPosition(key: Long) = key.toInt()
}