package org.mathematics.common.sets

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

import kotlin.test.junit5.JUnit5Asserter.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TupleTest {
    @Test
    fun `(0, 1) is not eqaul to (0, 1, 2)`() {
        val lhs = Tuple(arrayListOf(0, 1))
        val rhs = Tuple(arrayListOf(0, 1, 2))

        assertEquals("the tuples are not equal", false, lhs.equals(rhs))
    }

    @Test
    fun `(0, 1) is not eqaul to (a, b)`() {
        val lhs = Tuple(arrayListOf(0, 1))
        val rhs = Tuple(arrayListOf("a", "b"))

        assertEquals("the tuples are not equal", false, lhs.equals(rhs))
    }
}
