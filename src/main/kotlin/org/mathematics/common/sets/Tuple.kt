package org.mathematics.common.sets

/**
 *  An ordered collection of elements (not neccessarily of the same type).
 */
open class Tuple(elements: ArrayList<Any> = ArrayList<Any>()) {
    private var elements = elements

    /**
     *  The number of elements in the tuple.
     */
    val size
        get() = this.elements.size

    operator fun get(index: Int): Any {
        return elements[index]
    }

    operator fun set(index: Int, value: Any) {
        elements[index] = value
    }

    override fun equals(other: Any?): Boolean {
        if (other is Tuple) {
            if (this.size != other.size) {
                return false
            }

            lhsLoop@ for (lhsItem in this.elements) {
                rhsloop@ for (rhsItem in other.elements) {
                    if (lhsItem != null && lhsItem.equals(rhsItem)) {
                        continue@lhsLoop
                    }
                }

                return false
            }

            return true
        }

        return false
    }

    override fun toString(): String {
        return this.elements.joinToString(prefix = "(", postfix = ")", separator = ", ")
    }
}
