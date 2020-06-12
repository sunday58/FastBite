package com.sundaydavid.fastBite.ui.alphabetDetails

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.LinearLayout
import android.widget.TextClock
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.squareup.picasso.Picasso
import com.sundaydavid.fastBite.R
import com.sundaydavid.fastBite.adapter.SearchMealAdapter
import com.sundaydavid.fastBite.model.*
import com.sundaydavid.fastBite.ui.search.SearchFragment
import com.sundaydavid.fastBite.utility.CellClickListener
import java.lang.StringBuilder


/**
 * A simple [Fragment] subclass.
 */
class AlphabetDetails : Fragment() {

    lateinit var sheetBehavior: BottomSheetBehavior<View>
    lateinit var bottomSheet: LinearLayout
    lateinit var mealImage: PorterShapeImageView

    lateinit var MealsDetal: AlphabetData
    lateinit var searchMeal: SearchData
    private lateinit var mealTitle: TextView
    private lateinit var ingredientDetail: TextView
    private lateinit var directionDetail: TextView
    private lateinit var playVideo: YouTubePlayerView
    private lateinit var category: TextView

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_alphabet_details, container, false)


        //setting bottom sheet
        bottomSheet = root.findViewById(R.id.bottom_sheet)
        sheetBehavior = BottomSheetBehavior.from(bottomSheet)

        val btnBottomSheet = root.findViewById<FloatingActionButton>(R.id.show_video)
        btnBottomSheet.setOnClickListener {
            if (sheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            } else {
                sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }
        mealImage = root.findViewById(R.id.meal_image)
        mealTitle = root.findViewById(R.id.meal_title)
        ingredientDetail = root.findViewById(R.id.ingredient_detail)
        directionDetail = root.findViewById(R.id.direction_detail)
        playVideo = root.findViewById(R.id.play_video)
        category = root.findViewById(R.id.type)
        parentFragment!!.activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT


        //receiving bundle
        if (arguments != null && arguments!!.containsKey("meals")) {
            MealsDetal = arguments!!.getSerializable("meals") as AlphabetData


            getImage(MealsDetal.alphabetModel.meals[MealsDetal.position].strMealThumb)
            mealTitle.setText(MealsDetal.alphabetModel.meals[MealsDetal.position].strMeal)
            category.setText(MealsDetal.alphabetModel.meals[MealsDetal.position].strCategory)
            directionDetail.setText(MealsDetal.alphabetModel.meals[MealsDetal.position].strInstructions)

            //setting up video
            playVideo.getPlayerUiController().showFullscreenButton(false)
            playVideo.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    youTubePlayer.cueVideo(MealsDetal.alphabetModel.meals[MealsDetal.position].strYoutube, 0f)
                }
            })

            val list: MutableList<String?> = ArrayList()

            list.add(
                MealsDetal.alphabetModel.meals[MealsDetal.position].strIngredient1 + ", " + MealsDetal.alphabetModel.meals[MealsDetal.position].strIngredient2
                        + ", " + MealsDetal.alphabetModel.meals[MealsDetal.position] + ", " + MealsDetal.alphabetModel.meals[MealsDetal.position].strIngredient4
                        + ", " + MealsDetal.alphabetModel.meals[MealsDetal.position] + ", " + MealsDetal.alphabetModel.meals[MealsDetal.position].strIngredient6
                        + ", " + MealsDetal.alphabetModel.meals[MealsDetal.position] + ", " + MealsDetal.alphabetModel.meals[MealsDetal.position].strIngredient8
                        + ", " + MealsDetal.alphabetModel.meals[MealsDetal.position] + ", " + MealsDetal.alphabetModel.meals[MealsDetal.position].strIngredient10
                        + ", " + MealsDetal.alphabetModel.meals[MealsDetal.position] + ", " + MealsDetal.alphabetModel.meals[MealsDetal.position].strIngredient12
                        + ", " + MealsDetal.alphabetModel.meals[MealsDetal.position] + ", " + MealsDetal.alphabetModel.meals[MealsDetal.position].strIngredient14
                        + ", " + MealsDetal.alphabetModel.meals[MealsDetal.position] + ", " + MealsDetal.alphabetModel.meals[MealsDetal.position].strIngredient16
                        + ", " + MealsDetal.alphabetModel.meals[MealsDetal.position] + ", " + MealsDetal.alphabetModel.meals[MealsDetal.position].strIngredient18
                        + ", " + MealsDetal.alphabetModel.meals[MealsDetal.position] + ", " + MealsDetal.alphabetModel.meals[MealsDetal.position].strIngredient20
            )


            val builder = StringBuilder()

            for (value in list) {
                builder.append(value)
            }

            val formattedString = builder.toString()
                .replace("[", "")
                .replace("]", "")
                .replace("null", "")
                .trim()


            ingredientDetail.text = formattedString


        } else {
            //for search
            searchMeal = arguments!!.getSerializable("searchMeals") as SearchData


            getImage(searchMeal.searchModel.meals[searchMeal.position].strMealThumb)
            mealTitle.text = searchMeal.searchModel.meals[searchMeal.position].strMeal
            category.text = searchMeal.searchModel.meals[searchMeal.position].strCategory
            directionDetail.text = searchMeal.searchModel.meals[searchMeal.position].strInstructions

            //setting up video
            playVideo.getPlayerUiController().showFullscreenButton(false)
            playVideo.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    youTubePlayer.cueVideo(searchMeal.searchModel.meals[searchMeal.position].strYoutube, 0f)
                }
            })

            val list2: MutableList<String?> = ArrayList()


            list2.add(
                searchMeal.searchModel.meals[searchMeal.position].strIngredient1 + ", " + searchMeal.searchModel.meals[searchMeal.position].strIngredient2
                        + ", " + searchMeal.searchModel.meals[searchMeal.position].strIngredient3 + ", " + searchMeal.searchModel.meals[searchMeal.position].strIngredient4
                        + ", " + searchMeal.searchModel.meals[searchMeal.position].strIngredient5 + ", " + searchMeal.searchModel.meals[searchMeal.position].strIngredient6
                        + ", " + searchMeal.searchModel.meals[searchMeal.position].strIngredient7 + ", " + searchMeal.searchModel.meals[searchMeal.position].strIngredient8
                        + ", " + searchMeal.searchModel.meals[searchMeal.position].strIngredient9 + ", " + searchMeal.searchModel.meals[searchMeal.position].strIngredient10
                        + ", " + searchMeal.searchModel.meals[searchMeal.position].strIngredient11 + ", " + searchMeal.searchModel.meals[searchMeal.position].strIngredient12
                        + ", " + searchMeal.searchModel.meals[searchMeal.position].strIngredient13 + ", " + searchMeal.searchModel.meals[searchMeal.position].strIngredient14
                        + ", " + searchMeal.searchModel.meals[searchMeal.position].strIngredient15 + ", " + searchMeal.searchModel.meals[searchMeal.position].strIngredient16
                        + ", " + searchMeal.searchModel.meals[searchMeal.position].strIngredient17 + ", " + searchMeal.searchModel.meals[searchMeal.position].strIngredient18
                        + ", " + searchMeal.searchModel.meals[searchMeal.position].strIngredient19 + ", " + searchMeal.searchModel.meals[searchMeal.position].strIngredient20
            )

            val builder2 = StringBuilder()

            for (value in list2) {
                builder2.append(value)
            }

            val formattedString2 = builder2.toString()
                .replace("[", "")
                .replace("]", "")
                .replace("null", "")
                .trim()

            ingredientDetail.text = formattedString2
        }

        setState()
//        initYouTube()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        parentFragment!!.activity?.requestedOrientation =
            ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    }

    private fun setState() {
        sheetBehavior.setBottomSheetCallback(object : BottomSheetCallback() {
            override fun onStateChanged(view: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {

                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {

                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                    }
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                        TODO()
                    }
                }
            }

            override fun onSlide(view: View, v: Float) {}
        })
    }

    private fun getImage(url: String) {
        Picasso.get()
            .load(url)
            .fit()
            .into(mealImage)
    }
}
