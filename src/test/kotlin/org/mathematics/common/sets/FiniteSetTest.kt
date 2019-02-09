package org.mathematics.common.sets

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.test.junit5.JUnit5Asserter.assertEquals
import kotlin.test.junit5.JUnit5Asserter.assertTrue

import org.mathematics.common.numbers.RealNumber

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FiniteSetTest {
    @Test
    fun `Adding the element 2 to the set {1} results in {1, 2}`() {
        val one = RealNumber(1.0)
        val set = FiniteSet<RealNumber>(arrayListOf(one))

        assertEquals("cadinality is initially 1", 1, set.cardinality)
        assertTrue("set contains the number 1", set.contains(RealNumber(1.0)))

        val two = RealNumber(2.0)
        set.addElement(two)

        assertEquals("cadinality of the set is now 2", 2, set.cardinality)
        assertTrue("set now contains the number 2", set.contains(RealNumber(2.0)))
    }

    @Test
    fun `Adding the element 1 to the set {1} results in {1}`() {
        val one = RealNumber(1.0)
        val set = FiniteSet<RealNumber>(arrayListOf(one))

        assertEquals("cadinality is initially 1", 1, set.cardinality)
        assertTrue("set contains the number 1", set.contains(RealNumber(1.0)))

        val anotherOne = RealNumber(1.0)
        set.addElement(anotherOne)

        assertEquals("cardinality of the set is still 1", 1, set.cardinality)
        assertTrue("set still contains the number 1", set.contains(RealNumber(1.0)))
    }

    @Nested
    inner class Given123 {
        val numbers = arrayOf(1.0, 2.0, 3.0).map { it -> RealNumber(it) }
        val numberSet = FiniteSet<RealNumber>(ArrayList(numbers))

        @Test
        fun `The cardinality of a set is the number of elements`() {
            assertEquals("cadinality of numberSet is 3", 3, numberSet.cardinality)
        }

        @Test
        fun `The cardinality of the null set is 0`() {
            assertEquals("cardinality is 0", 0, numberSet.nullSet.cardinality)
        }

        @Test
        fun `It is equal to itself`() {
            assertTrue("set is equal to itself", numberSet.equals(numberSet))
        }

        @Test
        fun `It is equal to {1, 2, 3} (not itself or a clone)`() {
            val otherNumbers = arrayOf(1.0, 2.0, 3.0).map { it -> RealNumber(it) }
            val otherSet = FiniteSet<RealNumber>(ArrayList(otherNumbers))

            assertTrue("set is equal to second instance", numberSet.equals(otherSet))
        }

        @Test
        fun `It is not equal to {1, 2}`() {
            val otherNumbers = arrayOf(1.0, 2.0).map { it -> RealNumber(it) }
            val otherSet = FiniteSet<RealNumber>(ArrayList(otherNumbers))

            assertEquals("sets are not equal", false, numberSet.equals(otherSet))
        }

        @Test
        fun `It is not equal to {2, 3, 4}`() {
            val otherNumbers = arrayOf(2.0, 3.0, 4.0).map { it -> RealNumber(it) }
            val otherSet = FiniteSet<RealNumber>(ArrayList(otherNumbers))

            assertEquals("sets are not equal", false, numberSet.equals(otherSet))
        }

        @Test
        fun `Contains the element 1`() {
            assertTrue("contains 1", numberSet.contains(RealNumber(1.0)))
        }

        @Test
        fun `Does not contain the element 4`() {
            assertEquals("sets are not equal", false, numberSet.contains(RealNumber(4.0)))
        }

        @Test
        fun `Can be written as {1, 2, 3} using toString())`() {
            assertEquals("toString is as expected", "{1.0, 2.0, 3.0}", numberSet.toString())
        }

        @Test
        fun `The intersection with {2, 4} is {2}`() {
            val otherNumbers = arrayOf(2.0, 4.0).map { it -> RealNumber(it) }
            val otherSet = FiniteSet<RealNumber>(ArrayList(otherNumbers))

            assertEquals("intersection is as expected",
                FiniteSet(arrayListOf(RealNumber(2.0))),
                numberSet.intersection(otherSet))
        }

        @Test
        fun `the intersection with the empty set is the empty set`() {
            assertEquals("null set intersection is null",
                numberSet.nullSet,
                numberSet.intersection(numberSet.nullSet))
        }
    }
}
