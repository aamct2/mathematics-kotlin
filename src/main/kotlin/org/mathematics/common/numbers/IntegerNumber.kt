package org.mathematics.common.numbers

import org.mathematics.common.interfaces.Addable
import org.mathematics.common.interfaces.MultiplicativeIdentity

open class IntegerNumber(value: Int) : Addable<IntegerNumber>, Comparable<IntegerNumber>, MultiplicativeIdentity<IntegerNumber> {
    var value = value

    override val multiplicativeIdentity: IntegerNumber
        get() = IntegerNumber(1)

    public override fun add(rhs: IntegerNumber): IntegerNumber {
        return IntegerNumber(this.value + rhs.value)
    }

    override operator fun compareTo(other: IntegerNumber): Int {
        if (this.value < other.value) {
            return -1
        } else if (this.value == other.value) {
            return 0
        }

        return 1
    }

    override fun equals(other: Any?): Boolean {
        if (other is IntegerNumber) {
            return this.compareTo(other) == 0
        }

        return false
    }

    override fun toString(): String {
        return this.value.toString()
    }
}
