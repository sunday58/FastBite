package com.sundaydavid.fastBite.ui.alphabet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sundaydavid.fastBite.R
import com.sundaydavid.fastBite.adapter.AlphabetAdapter
import com.sundaydavid.fastBite.model.MealAlphabet

class AlphabetFragment : Fragment() {

   private lateinit var recyclerView: RecyclerView
    private lateinit var az: ArrayList<MealAlphabet>
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Only inflate the layout for this fragment here
        // Always endeavour not to do anything else in this method
        // To ensure better performance, put your codes in other appropriate lifecycle methods
        return inflater.inflate(R.layout.fragment_alphabet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.alphabet_recyclerView)

        //adding data
        az = ArrayList()
        buildData()
        setData()
    }

    private fun buildData() {
        az.add(MealAlphabet("A", "#F35DAB", "Meals that begin with A"))
        az.add(MealAlphabet("B", "#D62232", "Meals that begin with B"))
        az.add(MealAlphabet("C", "#A09D33", "Meals that begin with C"))
        az.add(MealAlphabet("D", "#864665", "Meals that begin with D"))
        az.add(MealAlphabet("E", "#2686E0", "Meals that begin with E"))
        az.add(MealAlphabet("F", "#32CD32", "Meals that begin with F"))
        az.add(MealAlphabet("G", "#F35DAB", "Meals that begin with G"))
        az.add(MealAlphabet("H", "#D62232", "Meals that begin with H"))
        az.add(MealAlphabet("I", "#A09D33","Meals that begin with I"))
        az.add(MealAlphabet("J", "#864665", "Meals that begin with J"))
        az.add(MealAlphabet("K",  "#2686E0", "Meals that begin with K"))
        az.add(MealAlphabet("L", "#32CD32", "Meals that begin with L"))
        az.add(MealAlphabet("M", "#F35DAB", "Meals that begin with M"))
        az.add(MealAlphabet("N", "#D62232","Meals that begin with N"))
        az.add(MealAlphabet("O", "#A09D33","Meals that begin with O"))
        az.add(MealAlphabet("P", "#864665","Meals that begin with P"))
        az.add(MealAlphabet("R", "#32CD32", "Meals that begin with R"))
        az.add(MealAlphabet("S", "#F35DAB",  "Meals that begin with S"))
        az.add(MealAlphabet("T", "#D62232", "Meals that begin with T"))
        az.add(MealAlphabet("V", "#A09D33", "Meals that begin with V"))
        az.add(MealAlphabet("W", "#864665", "Meals that begin with W"))
        az.add(MealAlphabet("Y", "#32CD32", "Meals that begin with Y"))
    }

    private fun setData() {
        recyclerView.layoutManager = GridLayoutManager(parentFragment?.context, 2)
        recyclerView.adapter = AlphabetAdapter(az)
    }
}
