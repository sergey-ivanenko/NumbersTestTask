package org.bitbucket.sergey_ivanenko.numberstesttask.numbers.presentation

import android.widget.TextView

data class NumberUi(private val id: String, private val fact: String) {

    fun map(head: TextView, subTitle: TextView) {
        head.text = id
        subTitle.text = fact
    }
}