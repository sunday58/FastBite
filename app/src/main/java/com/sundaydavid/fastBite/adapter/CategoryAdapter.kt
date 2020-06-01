package com.sundaydavid.fastBite.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.siyamed.shapeimageview.CircularImageView
import com.github.siyamed.shapeimageview.ShapeImageView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.squareup.picasso.Picasso
import com.sundaydavid.fastBite.R
import com.sundaydavid.fastBite.model.CategoryModel
import de.hdodenhof.circleimageview.CircleImageView

class CategoryAdapter(private val category: List<CategoryModel>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val  v = LayoutInflater.from(parent.context).inflate(R.layout.category_item_list,
            parent, false)
        return CategoryViewHolder(v)
    }

    override fun getItemCount(): Int {
        return category.count()
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val mealCategory = category[position]

        holder.loadCategoryImage(mealCategory.categories[position].strCategoryThumb)
        holder.categoryName.text = mealCategory.categories[position].strCategory

    }
    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val categoryImage: CircleImageView = itemView.findViewById(R.id.categoryImage)
        val categoryName: TextView = itemView.findViewById(R.id.categoryName)

        fun loadCategoryImage( image: String){
            Picasso.get()
                .load(image)
                .fit()
                .into(categoryImage)
        }
    }




}