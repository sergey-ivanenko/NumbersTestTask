package org.bitbucket.sergey_ivanenko.numberstesttask.numbers.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import org.bitbucket.sergey_ivanenko.numberstesttask.R
import org.bitbucket.sergey_ivanenko.numberstesttask.details.presentation.DetailsFragment
import org.bitbucket.sergey_ivanenko.numberstesttask.main.presentation.ShowFragment

class NumbersFragment : Fragment() {

    private var showFragmentListener: ShowFragment = ShowFragment.Empty()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        showFragmentListener = context as ShowFragment
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_numbers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
        // todo refactor and remove hardcode
        view.findViewById<View>(R.id.getFactButton).setOnClickListener {
            val detailsFragment =
                DetailsFragment.newInstance("some information about the random number hardcoded")
            showFragmentListener.show(detailsFragment)
        }
    }

    override fun onDetach() {
        super.onDetach()

        showFragmentListener = ShowFragment.Empty()
    }
}