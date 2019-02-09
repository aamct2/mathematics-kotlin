package org.mathematics.common.interfaces

/**
 *  Defines a method for adding two elements together.
 */
interface Addable<T> {
    fun add(rhs: T): T
}
