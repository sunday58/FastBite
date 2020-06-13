package com.sundaydavid.fastBite.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import com.sundaydavid.fastBite.R
import com.sundaydavid.fastBite.model.Meal
import com.sundaydavid.fastBite.model.SearchData
import com.sundaydavid.fastBite.model.SearchModel
import com.sundaydavid.fastBite.utility.CellClickListener
import de.hdodenhof.circleimageview.CircleImageView

class SearchMealAdapter(private val context: Context,
                        private val list: ArrayList<Meal>)
    : RecyclerView.Adapter<SearchMealAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            val data = list[position]

                holder.loadSearchImage(data.strMealThumb)
                holder.searchTitle.text = data.strMeal
                holder.searchType.text = data.strCategory

                holder.itemView.setOnClickListener {

//                    val bundle = Bundle()
//                    val da = SearchData(list[holder.adapterPosition], holder.adapterPosition)
//                    bundle.putSerializable("searchMeals", da)
//
//                    Navigation.findNavController(holder.itemView)
//                        .navigate(R.id.navigation_alphabet_detail, bundle)
                }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.search_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val searchImage: CircleImageView = itemView.findViewById(R.id.search_image)
        val searchTitle: TextView = itemView.findViewById(R.id.search_meal_title)
        val searchType: TextView = itemView.findViewById(R.id.search_meal_type)

        fun loadSearchImage(image: String) {
            Picasso.get()
                .load(image)
                .fit()
                .into(searchImage)
        }

    }

    }
