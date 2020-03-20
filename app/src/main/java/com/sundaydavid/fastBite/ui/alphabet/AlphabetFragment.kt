package com.sundaydavid.fastBite.ui.alphabet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sundaydavid.fastBite.R

class AlphabetFragment : Fragment() {

    private lateinit var alphabetViewModel: AlphabetViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        alphabetViewModel =
                ViewModelProviders.of(this).get(AlphabetViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_alphabet, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        alphabetViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
