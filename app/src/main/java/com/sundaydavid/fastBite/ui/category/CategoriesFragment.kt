package com.sundaydavid.fastBite.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sundaydavid.fastBite.R
import com.sundaydavid.fastBite.adapter.CategoryAdapter
import com.sundaydavid.fastBite.model.CategoryModel
import java.util.ArrayList

class CategoriesFragment : Fragment() {

    private val categoriesModel: CategoriesModel by lazy {
        ViewModelProviders.of(this).get(CategoriesModel::class.java)
    }
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_categories, container, false)

        recyclerView = root.findViewById(R.id.categoryRecyclerView)

        categoriesModel.getCategoriesMealLocal().observe(viewLifecycleOwner, Observer { meals ->

            val adapter = CategoryAdapter(meals)
            recyclerView.layoutManager = GridLayoutManager(activity!!.applicationContext,2 )
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        })
        return root
    }
}
