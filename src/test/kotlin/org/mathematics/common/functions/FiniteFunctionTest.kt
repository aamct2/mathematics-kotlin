package org.mathematics.common.functions

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

import org.mathematics.algebra.finite.examples.maps.IncrementMap
import org.mathematics.common.numbers.IntegerNumber
import org.mathematics.common.sets.FiniteSet

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FiniteFunctionTest {
    @Nested
    inner class FiniteFunctionTest_GivenZmod2 {
        val Zmod2Numbers = arrayOf(0, 1).map { it -> IntegerNumber(it) }
        val Zmod2Set = FiniteSet(ArrayList(Zmod2Numbers))

        @Test
        fun `The increment map with Zmod2 as both domain and codomain is not a function`() {
            val exception = Assertions.assertThrows(Exception::class.java) {
                FiniteFunction(Zmod2Set, Zmod2Set, IncrementMap<IntegerNumber>())
            }
        }

        @Test
        fun `The increment map with Zmod2 as domain and Zmod3 codomain is a function`() {
            val Zmod3Numbers = arrayOf(0, 1, 2).map { it -> IntegerNumber(it) }
            val Zmod3Set = FiniteSet(ArrayList(Zmod3Numbers))

            val function = FiniteFunction(Zmod2Set, Zmod3Set, IncrementMap<IntegerNumber>())

            Assertions.assertNotNull(function)
        }
    }
}
