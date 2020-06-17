package com.sundaydavid.fastBite.adapter

import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation

import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.sundaydavid.fastBite.R
import com.sundaydavid.fastBite.model.MealAlphabet

class AlphabetAdapter( private val alphabetList: ArrayList<MealAlphabet>) :
RecyclerView.Adapter<AlphabetAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val  v = LayoutInflater.from(parent.context).inflate(R.layout.az_list_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
       return alphabetList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val az = alphabetList[position]
        holder.alphabet.text = az.alphabet
        holder.alphaDetail.text = az.detail
        holder.alphabet.setTextColor(Color.parseColor(az.color))
    }

   inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val  alphabet: TextView = itemView.findViewById(R.id.alphabet_text)
        val alphaDetail: TextView = itemView.findViewById(R.id.alphabet_detail)

        var alphaPosition = 1
        init {
            itemView.setOnClickListener {
//                Snackbar.make(it, alphabetList[adapterPosition].alphabet, Snackbar.LENGTH_SHORT).show()

                if (isOnline(itemView.context)) {
                    val bundle = Bundle()
                    bundle.putString("meal", alphabetList[adapterPosition].alphabet)
                    Navigation.findNavController(itemView).navigate(R.id.navigation_alphabet_list, bundle)
                }else {
                    Navigation.findNavController(itemView).navigate(R.id.navigation_network)
                }
            }
        }

       fun isOnline(context: Context): Boolean {
           val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                   as ConnectivityManager
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
               val capabilities =  connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
               if (capabilities != null) {
                   when {
                       capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                           return true
                       }
                       capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)-> {
                           return true
                       }
                       capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                           return true
                       }
                   }
               }
           } else {
               val activeNetworkInfo = connectivityManager.activeNetworkInfo
               if (activeNetworkInfo != null && activeNetworkInfo.isConnected){
                   return true
               }
           }

           return false
       }
    }
}