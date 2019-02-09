package org.mathematics.common.numbers

import org.mathematics.common.interfaces.Addable

open class RealNumber(value: Double) : Addable<RealNumber>, Comparable<RealNumber> {
    var value = value

    public override fun add(rhs: RealNumber): RealNumber {
        return RealNumber(this.value + rhs.value)
    }

    override operator fun compareTo(other: RealNumber): Int {
        if (this.value < other.value) {
            return -1
        } else if (this.value == other.value) {
            return 0
        }

        return 1
    }

    override fun equals(other: Any?): Boolean {
        if (other is RealNumber) {
            return this.compareTo(other) == 0
        }

        return false
    }

    override fun toString(): String {
        return this.value.toString()
    }
}
