package com.sundaydavid.fastBite.ui.alphabet

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
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
        val root = inflater.inflate(R.layout.fragment_alphabet, container, false)

        recyclerView = root.findViewById(R.id.alphabet_recyclerView)

        //adding datas
        az = ArrayList()

        az.add(MealAlphabet("A", "#F35DAB", "Meals that begins with A"))
        az.add(MealAlphabet("B", "#D62232", "Meals that begins with B"))
        az.add(MealAlphabet("C", "#A09D33", "Meals that begins with C"))
        az.add(MealAlphabet("D", "#864665", "Meals that begins with D"))
        az.add(MealAlphabet("E", "#2686E0", "Meals that begins with E"))
        az.add(MealAlphabet("F", "#32CD32", "Meals that begins with F"))
        az.add(MealAlphabet("G", "#F35DAB", "Meals that begins with G"))
        az.add(MealAlphabet("H", "#D62232", "Meals that begins with H"))
        az.add(MealAlphabet("I", "#A09D33","Meals that begins with I"))
        az.add(MealAlphabet("J", "#864665", "Meals that begins with J"))
        az.add(MealAlphabet("K",  "#2686E0", "Meals that begins with K"))
        az.add(MealAlphabet("L", "#32CD32", "Meals that begins with L"))
        az.add(MealAlphabet("M", "#F35DAB", "Meals that begins with M"))
        az.add(MealAlphabet("N", "#D62232","Meals that begins with N"))
        az.add(MealAlphabet("O", "#A09D33","Meals that begins with O"))
        az.add(MealAlphabet("P", "#864665","Meals that begins with P"))
        az.add(MealAlphabet("R", "#32CD32", "Meals that begins with "))
        az.add(MealAlphabet("S", "#F35DAB",  "Meals that begins with S"))
        az.add(MealAlphabet("T", "#D62232", "Meals that begins with T"))
        az.add(MealAlphabet("V", "#A09D33", "Meals that begins with U"))
        az.add(MealAlphabet("W", "#864665", "Meals that begins with W"))
        az.add(MealAlphabet("Y", "#32CD32", "Meals that begins with Y"))

        setData()
        return root
    }

    fun setData(){

        recyclerView.layoutManager = GridLayoutManager( parentFragment?.context, 2)
        recyclerView.adapter = AlphabetAdapter(az)
    }
}
