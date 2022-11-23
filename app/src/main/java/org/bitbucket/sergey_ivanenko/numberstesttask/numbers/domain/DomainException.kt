package org.bitbucket.sergey_ivanenko.numberstesttask.numbers.domain

abstract class DomainException : IllegalStateException()

class NoInternetConnectionException : DomainException()

class ServiceUnavailableException : DomainException()