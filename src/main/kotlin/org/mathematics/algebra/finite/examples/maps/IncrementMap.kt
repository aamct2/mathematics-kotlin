package org.mathematics.algebra.finite.examples.maps

import org.mathematics.common.functions.Map
import org.mathematics.common.interfaces.Addable
import org.mathematics.common.interfaces.MultiplicativeIdentity

/**
 *  Increments the value (using addition) by its multiplicative identity.
 */
open class IncrementMap<T> : Map<T, T>
    where T : Addable<T>, T : MultiplicativeIdentity<T> {

    override fun applyMap(input: T): T {
        return input.add(input.multiplicativeIdentity)
    }
}
