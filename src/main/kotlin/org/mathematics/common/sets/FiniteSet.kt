package org.mathematics.common.sets

open class FiniteSet<T : Comparable<T>>(elements: ArrayList<T> = ArrayList<T>()) {
    private var elements = elements

    /**
     *  Returns the number of elements in this set.
     */
    val cardinality
        get() = this.elements.size

    /**
     *  Returns the null set (also known as the empty set).
     */
    val nullSet
        get() = FiniteSet<T>()

    operator fun get(index: Int): T {
        return elements[index]
    }

    operator fun set(index: Int, value: T) {
        elements[index] = value
    }

    /**
    *  Adds an [element] to this set.
    */
    fun addElement(element: T) {
        for (item in this.elements) {
            if (item == element) {
                return
            }
        }

        this.elements.add(element)
    }

    /**
     *  Determines whether this set contains a given [element].
     */
    fun contains(element: T): Boolean {
        return this.elements.contains(element)
    }

    override fun equals(other: Any?): Boolean {
        if (other is FiniteSet<*>) {
            lhsLoop@ for (lhsItem in this.elements) {
                rhsloop@ for (rhsItem in other.elements) {
                    if (lhsItem.equals(rhsItem)) {
                        continue@lhsLoop
                    }
                }

                return false
            }

            return true
        }

        return false
    }
}
