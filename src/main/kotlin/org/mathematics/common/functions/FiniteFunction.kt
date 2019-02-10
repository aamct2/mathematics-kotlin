package org.mathematics.common.functions

import org.mathematics.common.sets.FiniteSet

open class FiniteFunction<T : Any, G : Any> {
    val domain: FiniteSet<T>
    val codomain: FiniteSet<G>
    val relation: Map<T, G>

    constructor(domain: FiniteSet<T>, codomain: FiniteSet<G>, relation: Map<T, G>) {
        this.domain = domain
        this.codomain = codomain

        for (domainElement in domain) {
            val mappedElement = relation.applyMap(domainElement)

            if (!codomain.contains(mappedElement)) {
                throw Exception("The codomain does not contain the output element for the domain element $domainElement")
            }
        }

        this.relation = relation
    }
}
