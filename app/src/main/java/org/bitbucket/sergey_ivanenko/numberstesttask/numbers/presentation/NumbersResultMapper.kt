package org.bitbucket.sergey_ivanenko.numberstesttask.numbers.presentation

import org.bitbucket.sergey_ivanenko.numberstesttask.numbers.domain.NumberFact
import org.bitbucket.sergey_ivanenko.numberstesttask.numbers.domain.NumbersResult

class NumbersResultMapper(
    private val communications: NumbersCommunications,
    private val mapper: NumberFact.Mapper<NumberUi>
) : NumbersResult.Mapper<Unit> {

    override fun map(list: List<NumberFact>, errorMessage: String) {
        communications.showState(
            if (errorMessage.isEmpty()) {
                if (list.isNotEmpty()) {
                    communications.showList(list.map { it.map(mapper) })
                }
                UiState.Success()
            } else {
                UiState.Error(errorMessage)
            }
        )
    }
}