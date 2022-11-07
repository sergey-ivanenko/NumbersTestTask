package org.bitbucket.sergey_ivanenko.numberstesttask.numbers.domain

import org.bitbucket.sergey_ivanenko.numberstesttask.numbers.presentation.NumberUi

class NumberUiMapper : NumberFact.Mapper<NumberUi> {
    override fun map(id: String, fact: String): NumberUi = NumberUi(id, fact)
}