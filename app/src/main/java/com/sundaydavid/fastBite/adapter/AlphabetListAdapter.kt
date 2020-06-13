package com.sundaydavid.fastBite.adapter

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.navigation.Navigation

import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import com.sundaydavid.fastBite.R
import com.sundaydavid.fastBite.model.AlphabetData
import com.sundaydavid.fastBite.model.AlphabetModel
import com.sundaydavid.fastBite.model.Meal
import com.sundaydavid.fastBite.model.MealAlphabet
import de.hdodenhof.circleimageview.CircleImageView
import java.util.ArrayList

class AlphabetListAdapter(private val alphabetList: ArrayList<Meal>) :
RecyclerView.Adapter<AlphabetListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val  v = LayoutInflater.from(parent.context).inflate(R.layout.az_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
       return alphabetList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val az = alphabetList[position]
        holder.alphabetTitle.text = az.strMeal
        holder.alphaMealType.text = az.strCategory
        holder.loadImage(az.strMealThumb)
        
    }


   inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val  alphabetTitle: TextView = itemView.findViewById(R.id.az_title)
        val alphaMealType: TextView = itemView.findViewById(R.id.az_meal_type)
        val alphaMealImage: CircleImageView = itemView.findViewById(R.id.az_image)

        init {
            itemView.setOnClickListener {
                Snackbar.make(it, alphabetList[adapterPosition].idMeal, Snackbar.LENGTH_SHORT).show()

//                val bundle = Bundle()
//                val data = AlphabetData(alphabetList[adapterPosition], adapterPosition)
//                bundle.putSerializable("meals", data)
//
//                Navigation.findNavController(itemView).navigate(R.id.navigation_alphabet_detail, bundle)
            }
        }


        fun loadImage( image: String){
           Picasso.get()
               .load(image)
               .fit()
               .into(alphaMealImage)
       }
    }

}