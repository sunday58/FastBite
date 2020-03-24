package com.sundaydavid.fastBite.ui.alphabetDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sundaydavid.fastBite.R

/**
 * A simple [Fragment] subclass.
 */
class AlphabetDetails : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val  root = inflater.inflate(R.layout.fragment_alphabet_details, container, false)



        return  root
    }

}
