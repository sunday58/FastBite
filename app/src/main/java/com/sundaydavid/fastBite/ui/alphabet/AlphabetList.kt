package com.sundaydavid.fastBite.ui.alphabet

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fevziomurtekin.customprogress.Dialog
import com.fevziomurtekin.customprogress.Type

import com.sundaydavid.fastBite.R
import com.sundaydavid.fastBite.adapter.AlphabetListAdapter
import com.sundaydavid.fastBite.remoteDatabase.ApiClient
import com.sundaydavid.fastBite.repository.MealRepository

/**
 * A simple [Fragment] subclass.
 */
class AlphabetList : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progress: Dialog
    private lateinit var goBack: ImageView
    private lateinit var alphabetListViewModel: AlphabetListViewModel
    private lateinit var alphabetListViewModelFactory: AlphabetListViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alphabet_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progress = view.findViewById(R.id.progress_bar)
        goBack = view.findViewById(R.id.go_back)
        recyclerView = view.findViewById(R.id.az_list_recyclerView)
        alphabetListViewModelFactory = AlphabetListViewModelFactory(MealRepository(ApiClient.getClient))
        alphabetListViewModel = ViewModelProvider(this, alphabetListViewModelFactory).get(AlphabetListViewModel::class.java)

        //Go Back
        goBack.setOnClickListener { v: View ->
            Navigation.findNavController(v).navigate(R.id.navigation_az)
        }

        if (arguments != null) {
            val mealName = arguments!!.getString("meal")
            alphabetListViewModel.setData(mealName!!)
        }

        setupProgressBar()
        observeMeal()
        observeProgress()
        setupRv()
    }

    private fun setupProgressBar() {
        progress.settype(Type.WEDGES)
        progress.setdurationTime(100)
        progress.show()
    }

    private fun setupRv() {
        recyclerView.adapter?.notifyDataSetChanged()
        recyclerView.layoutManager = LinearLayoutManager(parentFragment?.context, LinearLayoutManager.VERTICAL, false)
    }

    private fun observeMeal() {
        alphabetListViewModel.meal.observe(viewLifecycleOwner, Observer { meals ->
            if (meals != null) {
                recyclerView.adapter = AlphabetListAdapter(meals)
                Log.d("Meals ", meals.toString())
            } else {
                Toast.makeText(activity, "Check network connection", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun observeProgress() {
        alphabetListViewModel.showProgress.observe(viewLifecycleOwner, Observer { show ->
                progress.isVisible = show
        })
    }
}



