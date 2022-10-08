package org.bitbucket.sergey_ivanenko.numberstesttask.main.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import org.bitbucket.sergey_ivanenko.numberstesttask.R
import org.bitbucket.sergey_ivanenko.numberstesttask.numbers.presentation.NumbersFragment

class MainActivity : AppCompatActivity(), ShowFragment {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            show(NumbersFragment(), false)
        }
    }

    override fun show(fragment: Fragment) {
        show(fragment, true)
    }

    private fun show(fragment: Fragment, add: Boolean) {
        // todo make OOP

        val transaction = supportFragmentManager.beginTransaction()
        val container = R.id.container

        if (add) {
            transaction.add(fragment, fragment.javaClass.simpleName)
                .addToBackStack(fragment.javaClass.simpleName)
        } else {
            transaction.replace(container, fragment)
        }
        transaction.commit()
    }
}

interface ShowFragment {

    fun show(fragment: Fragment)

    class Empty : ShowFragment {
        override fun show(fragment: Fragment) = Unit
    }
}