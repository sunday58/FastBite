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
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fevziomurtekin.customprogress.Dialog
import com.fevziomurtekin.customprogress.Type

import com.sundaydavid.fastBite.R
import com.sundaydavid.fastBite.adapter.AlphabetListAdapter
import com.sundaydavid.fastBite.remoteDatabase.ApiClient
import com.sundaydavid.fastBite.model.AlphabetModel
import com.sundaydavid.fastBite.model.Meal
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class AlphabetList : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AlphabetListAdapter
    private lateinit var progress: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_alphabet_list, container, false)

        progress = root.findViewById(R.id.progress_bar)
        progress.settype(Type.WEDGES)
        progress.setdurationTime(100)
        progress.show()

        //goback
        val goback = root.findViewById<ImageView>(R.id.go_back)
        goback.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(R.id.navigation_az)
        }
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

                val dataList = ArrayList<Meal>()
//                response.body()?.meals.let { dataList.add(it) }
                dataList += (response.body()!!.meals)
                Log.d("Meals ", response.body().toString())

                recyclerView.adapter = AlphabetListAdapter(dataList)
                recyclerView.adapter?.notifyDataSetChanged()
                progress.isVisible = false
            }

            override fun onFailure(call: Call<AlphabetModel>, t: Throwable) {
                System.out.println("Error " + t.localizedMessage)
                Toast.makeText(activity, t.localizedMessage, Toast.LENGTH_LONG).show()
                progress.isVisible = false
            }
        })


        }

    }



