package org.mathematics.common.sets

open class FiniteSet<T>(elements: ArrayList<T> = ArrayList<T>()) {
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
     *  Creates a clone of this set.
     */
    fun clone(): FiniteSet<T> {
        return FiniteSet<T>(ArrayList(this.elements))
    }

    /**
     *  Determines whether this set contains a given [element].
     */
    fun contains(element: T): Boolean {
        return this.elements.contains(element)
    }

    /**
     *  Returns the set-theoretic difference of this set minus another set.
     *
     *  @param rhs The set whose elements are to be removed from this one.
     */
    fun difference(rhs: FiniteSet<T>): FiniteSet<T> {
        val result = FiniteSet<T>()

        for (lhsItem in this.elements) {
            var found = false

            innerloop@ for (rhsItem in rhs.elements) {
                if (lhsItem?.equals(rhsItem) ?: false) {
                    found = true
                    break@innerloop
                }
            }

            if (!found) {
                result.addElement(lhsItem)
            }
        }

        return result
    }

    override fun equals(other: Any?): Boolean {
        if (other is FiniteSet<*>) {
            if (this.cardinality != other.cardinality) {
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

    /**
     *  Returns in the index of a given [element] in the set.
     */
    fun indexOf(element: T): Int {
        return this.elements.indexOf(element)
    }

    /**
     *  The subset of elements which exist both in this set and another.
     *
     *  @param other The other set with which to intersect.
     */
    fun intersection(other: FiniteSet<T>): FiniteSet<T> {
        // Don't waste time cranking the intersection if one of the sets is the null set
        if (this.cardinality == 0 || other.cardinality == 0) {
            return this.nullSet
        }

        val result = FiniteSet<T>()

        this.elements.forEach {
            if (other.indexOf(it) > -1) {
                result.addElement(it)
            }
        }

        return result
    }

    /**
     *  Determines whether this set is a subset of a given set.
     *
     *  @param other The potential superset.
     */
    fun isSubsetOf(other: FiniteSet<T>): Boolean {
        for (item in this.elements) {
            if (!other.contains(item)) {
                return false
            }
        }

        return true
    }

    /**
     *  Returns the powerset of this set.
     */
    fun powerSet(): FiniteSet<FiniteSet<T>> {
        if (this.equals(this.nullSet)) {
            return FiniteSet(arrayListOf(this.nullSet))
        }

        val baseElementSet = FiniteSet<T>()
        baseElementSet.addElement(this[0])
        val differenceSet = this.difference(baseElementSet)

        val family = this.familyPlusElement(this[0], differenceSet.powerSet())
        val result = differenceSet.powerSet().union(family)

        return result
    }

    override fun toString(): String {
        return this.elements.joinToString(prefix = "{", postfix = "}", separator = ", ")
    }

    /**
     *  Returns the union of this set with another set.
     *
     *  @param rhs The other set with which to combine.
     */
    fun union(rhs: FiniteSet<T>): FiniteSet<T> {
        // Don't waste time cranking the union if one of the sets is the null set
        if (this.equals(this.nullSet)) {
            return rhs.clone()
        } else if (rhs.equals(this.nullSet)) {
            return this.clone()
        }

        val result = FiniteSet<T>()

        for (item in this.elements) {
            result.addElement(item)
        }

        for (item in rhs.elements) {
            result.addElement(item)
        }

        return result
    }

    private fun addElementWithoutCheck(element: T) {
        this.elements.add(element)
    }

    private fun familyPlusElement(element: T, family: FiniteSet<FiniteSet<T>>): FiniteSet<FiniteSet<T>> {
        val result = FiniteSet<FiniteSet<T>>()
        val elementSet = FiniteSet<T>(arrayListOf(element))

        for (item in family.elements) {
            result.addElementWithoutCheck(item.union(elementSet))
        }

        return result
    }
}
