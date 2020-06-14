package com.sundaydavid.fastBite.ui.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

import com.sundaydavid.fastBite.R
import com.sundaydavid.fastBite.model.Category

/**
 * A simple [Fragment] subclass.
 */
class CategoryDetails : Fragment() {

    private lateinit var category: Category
    private lateinit var categoryTitle: TextView
    private lateinit var categoryDetails: TextView
    private lateinit var categoryImage: ImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_category_details, container, false)

        categoryTitle = root.findViewById(R.id.category_detail_title)
        categoryDetails = root.findViewById(R.id.category_detail)
        categoryImage = root.findViewById(R.id.category_detail_image)

        if (arguments != null){

            category = arguments!!.getSerializable("category") as Category

            categoryTitle.text = category.strCategory
            categoryDetails.text = category.strCategoryDescription
            displayImage(category.strCategoryThumb)
        }

        return root
    }

    private fun displayImage(url: String){
        Picasso.get()
            .load(url)
            .into(categoryImage)
    }

}
