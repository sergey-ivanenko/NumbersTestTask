package org.bitbucket.sergey_ivanenko.numberstesttask.numbers.presentation

interface Mapper<R, S> {

    fun map(source: S): R

    interface Unit<S> : Mapper<kotlin.Unit, S>
}