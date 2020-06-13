package com.sundaydavid.fastBite.ui.search

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.SearchView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fevziomurtekin.customprogress.Dialog
import com.fevziomurtekin.customprogress.Type
import com.sundaydavid.fastBite.R
import com.sundaydavid.fastBite.adapter.AlphabetListAdapter
import com.sundaydavid.fastBite.adapter.SearchMealAdapter
import com.sundaydavid.fastBite.model.Meal
import com.sundaydavid.fastBite.model.SearchModel
import com.sundaydavid.fastBite.remoteDatabase.ApiClient
import com.sundaydavid.fastBite.utility.CellClickListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchBaseRepository
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: androidx.appcompat.widget.SearchView
//    private lateinit var progress: Dialog
    val  dataList = ArrayList<Meal>()


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        searchViewModel =
                ViewModelProviders.of(this).get(SearchBaseRepository::class.java)
        val root = inflater.inflate(R.layout.fragment_search, container, false)
        recyclerView = root.findViewById(R.id.meal_search_recyclerView)
        searchView = root.findViewById(R.id.meal_search)

//        progress = root.findViewById(R.id.progress_bar)
//        progress.settype(Type.WEDGES)
//        progress.setdurationTime(100)
//        progress.show()

        recyclerView.layoutManager = LinearLayoutManager(parentFragment?.context,
            LinearLayoutManager.VERTICAL, false)


        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
               return false

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                getMealList(newText!!)
                return true
            }
        })

        return root
    }

    fun getMealList(meal: String) {

        ApiClient.getClient.SearchMeal(meal).enqueue(object : Callback<SearchModel> {
            override fun onResponse(call: Call<SearchModel>, response: Response<SearchModel>) {

                if (response.isSuccessful){
                    dataList += response.body()!!.meals

                    recyclerView.adapter = SearchMealAdapter(activity!!.applicationContext, dataList)
                    recyclerView.adapter?.notifyDataSetChanged()
//                    progress.isVisible = false
                }

                else
                    response.errorBody()
            }

            override fun onFailure(call: Call<SearchModel>, t: Throwable) {
                t.message
//                progress.isVisible = false
            }
        })
    }


}
