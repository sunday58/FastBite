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

    lateinit var MealsDetal: Meal
    lateinit var searchMeal: Meal
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
            MealsDetal = arguments!!.getSerializable("meals") as Meal


            getImage(MealsDetal.strMealThumb)
            mealTitle.text = MealsDetal.strMeal
            category.setText(MealsDetal.strCategory)
            directionDetail.setText(MealsDetal.strInstructions)

            //setting up video
            playVideo.getPlayerUiController().showFullscreenButton(false)
            playVideo.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    youTubePlayer.cueVideo(MealsDetal.strYoutube, 0f)
                }
            })

            val list: MutableList<String?> = ArrayList()

            list.add(
                MealsDetal.strIngredient1 + ", " + MealsDetal.strIngredient2
                        + ", " + MealsDetal.strIngredient5 + ", " + MealsDetal.strIngredient4
                        + ", " + MealsDetal.strIngredient7 + ", " + MealsDetal.strIngredient6
                        + ", " + MealsDetal.strIngredient9 + ", " + MealsDetal.strIngredient8
                        + ", " + MealsDetal.strIngredient11 + ", " + MealsDetal.strIngredient10
                        + ", " + MealsDetal.strIngredient13 + ", " + MealsDetal.strIngredient12
                        + ", " + MealsDetal.strIngredient15 + ", " + MealsDetal.strIngredient14
                        + ", " + MealsDetal.strIngredient17 + ", " + MealsDetal.strIngredient16
                        + ", " + MealsDetal.strIngredient19 + ", " + MealsDetal.strIngredient18
                        + ", " + MealsDetal.strIngredient3 + ", " + MealsDetal.strIngredient20
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
            searchMeal = arguments!!.getSerializable("searchMeals") as Meal


            getImage(searchMeal.strMealThumb)
            mealTitle.text = searchMeal.strMeal
            category.text = searchMeal.strCategory
            directionDetail.text = searchMeal.strInstructions

            //setting up video
            playVideo.getPlayerUiController().showFullscreenButton(false)
            playVideo.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    youTubePlayer.cueVideo(
                        searchMeal.strYoutube,
                        0f
                    )
                }
            })

            val list2: MutableList<String?> = ArrayList()


            list2.add(
                searchMeal.strIngredient1 + ", " + searchMeal.strIngredient2
                        + ", " + searchMeal.strIngredient3 + ", " + searchMeal.strIngredient4
                        + ", " + searchMeal.strIngredient5 + ", " + searchMeal.strIngredient6
                        + ", " + searchMeal.strIngredient7 + ", " + searchMeal.strIngredient8
                        + ", " + searchMeal.strIngredient9 + ", " + searchMeal.strIngredient10
                        + ", " + searchMeal.strIngredient11 + ", " + searchMeal.strIngredient12
                        + ", " + searchMeal.strIngredient13 + ", " + searchMeal.strIngredient14
                        + ", " + searchMeal.strIngredient15 + ", " + searchMeal.strIngredient16
                        + ", " + searchMeal.strIngredient17 + ", " + searchMeal.strIngredient18
                        + ", " + searchMeal.strIngredient19 + ", " + searchMeal.strIngredient20
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


