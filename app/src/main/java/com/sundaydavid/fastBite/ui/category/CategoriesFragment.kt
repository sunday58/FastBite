package com.sundaydavid.fastBite.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sundaydavid.fastBite.R

class CategoriesFragment : Fragment() {

    private lateinit var categoriesModel: CategoriesModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        categoriesModel =
                ViewModelProviders.of(this).get(CategoriesModel::class.java)
        val root = inflater.inflate(R.layout.fragment_categories, container, false)


        return root
    }
}
