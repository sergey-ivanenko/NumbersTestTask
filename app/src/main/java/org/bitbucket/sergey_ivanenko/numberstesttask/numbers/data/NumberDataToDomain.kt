package org.bitbucket.sergey_ivanenko.numberstesttask.numbers.data

import org.bitbucket.sergey_ivanenko.numberstesttask.numbers.domain.NumberFact

class NumberDataToDomain : NumberData.Mapper<NumberFact> {

    override fun map(id: String, fact: String) = NumberFact(id, fact)
}