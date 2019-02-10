package org.mathematics.common.functions

interface Map<T : Any, G : Any> {
    fun applyMap(input: T): G
}
