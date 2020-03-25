package com.sundaydavid.fastBite.ui.alphabet

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.sundaydavid.fastBite.R
import com.sundaydavid.fastBite.adapter.AlphabetAdapter
import com.sundaydavid.fastBite.adapter.AlphabetListAdapter
import com.sundaydavid.fastBite.apiclient.ApiClient
import com.sundaydavid.fastBite.model.AlphabetModel
import com.sundaydavid.fastBite.model.Meal
import com.sundaydavid.fastBite.model.MealAlphabet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class AlphabetList : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: AlphabetListAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_alphabet_list, container, false)

        recyclerView = root.findViewById(R.id.az_list_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(parentFragment?.context, LinearLayoutManager.VERTICAL, false)

       if (arguments != null){
           var mealName = arguments!!.getString("meal")
           setData(mealName!!)
       }

        return root
    }

    fun setData(query: String){
    val call: Call<AlphabetModel> = ApiClient.getClient.meal(query)

        call.enqueue(object : Callback<AlphabetModel>{

            override fun onResponse(call: Call<AlphabetModel>, response: Response<AlphabetModel>) {

                val dataList = ArrayList<AlphabetModel>()
                response.body()?.let { dataList.add(it) }
                Log.d("Meals ", response.body().toString())

                recyclerView.adapter = AlphabetListAdapter(dataList)
                recyclerView.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<AlphabetModel>, t: Throwable) {
                System.out.println("Error " + t.localizedMessage)
                Toast.makeText(activity, t.localizedMessage, Toast.LENGTH_LONG).show()
            }
        })


        }

    }


