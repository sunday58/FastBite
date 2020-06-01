package com.sundaydavid.fastBite.ui.alphabetDetails

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.squareup.picasso.Picasso
import com.sundaydavid.fastBite.R
import com.sundaydavid.fastBite.adapter.SearchMealAdapter
import com.sundaydavid.fastBite.model.AlphabetModel
import com.sundaydavid.fastBite.model.SearchModel
import java.lang.StringBuilder


/**
 * A simple [Fragment] subclass.
 */
class AlphabetDetails : Fragment() {

    lateinit var sheetBehavior: BottomSheetBehavior<View>
    lateinit var bottomSheet: LinearLayout
    lateinit var mealImage: PorterShapeImageView

   lateinit var MealsDetal: AlphabetModel
    lateinit var searchMeal: SearchModel


    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val  root = inflater.inflate(R.layout.fragment_alphabet_details, container, false)


        //setting bottom sheet
        bottomSheet = root.findViewById(R.id.bottom_sheet)
        sheetBehavior = BottomSheetBehavior.from(bottomSheet)

        val btnBottomSheet = root.findViewById<FloatingActionButton>(R.id.show_video)
        btnBottomSheet.setOnClickListener {
            if (sheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED){
                sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }else {
             sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }
         mealImage = root.findViewById(R.id.meal_image)
        val mealTitle = root.findViewById<TextView>(R.id.meal_title)
        val ingredientDetail = root.findViewById<TextView>(R.id.ingredient_detail)
        val directionDetail = root.findViewById<TextView>(R.id.direction_detail)
       val  playVideo = root.findViewById<YouTubePlayerView>(R.id.play_video)
        val category = root.findViewById<TextView>(R.id.type)
        parentFragment!!.activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT


        //receiving bundle
        if (arguments != null && arguments!!.containsKey("meals")){
             MealsDetal = arguments!!.getSerializable("meals") as AlphabetModel


            getImage(MealsDetal.meals[0].strMealThumb)
            mealTitle.setText(MealsDetal.meals[0].strMeal)
            category.setText(MealsDetal.meals[0].strCategory)
            directionDetail.setText(MealsDetal.meals[0].strInstructions)

            //setting up video
            playVideo.getPlayerUiController().showFullscreenButton(false)
            playVideo.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
                override fun onReady(youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    youTubePlayer.cueVideo(MealsDetal.meals[0].strYoutube, 0f)
                }
            })

            val list: MutableList<String?> = ArrayList()


            list.add(MealsDetal.meals[0].strIngredient1 + ", " + MealsDetal.meals[0].strIngredient2
                    + ", " + MealsDetal.meals[0].strIngredient3 + ", " + MealsDetal.meals[0].strIngredient4
                    + ", " + MealsDetal.meals[0].strIngredient5 + ", " + MealsDetal.meals[0].strIngredient6
                    + ", " + MealsDetal.meals[0].strIngredient7 + ", " + MealsDetal.meals[0].strIngredient8
                    + ", " + MealsDetal.meals[0].strIngredient9 + ", " + MealsDetal.meals[0].strIngredient10
                    + ", " + MealsDetal.meals[0].strIngredient11 + ", " + MealsDetal.meals[0].strIngredient12
                    + ", " + MealsDetal.meals[0].strIngredient13 + ", " + MealsDetal.meals[0].strIngredient14
                    + ", " + MealsDetal.meals[0].strIngredient15 + ", " + MealsDetal.meals[0].strIngredient16
                    + ", " + MealsDetal.meals[0].strIngredient17 + ", " + MealsDetal.meals[0].strIngredient18
                    + ", " + MealsDetal.meals[0].strIngredient19 + ", " + MealsDetal.meals[0].strIngredient20)

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


        }
        else {
            //for search
            searchMeal = arguments!!.getSerializable("searchMeals") as SearchModel


            getImage(searchMeal.meals[0].strMealThumb)
            mealTitle.setText(searchMeal.meals[0].strMeal)
            category.setText(searchMeal.meals[0].strCategory)
            directionDetail.setText(searchMeal.meals[0].strInstructions)

            //setting up video
            playVideo.getPlayerUiController().showFullscreenButton(false)
            playVideo.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
                override fun onReady(youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    youTubePlayer.cueVideo(searchMeal.meals[0].strYoutube, 0f)
                }
            })

            val list2: MutableList<String?> = ArrayList()


            list2.add(searchMeal.meals[0].strIngredient1 + ", " + searchMeal.meals[0].strIngredient2
                    + ", " + searchMeal.meals[0].strIngredient3 + ", " + searchMeal.meals[0].strIngredient4
                    + ", " + searchMeal.meals[0].strIngredient5 + ", " + searchMeal.meals[0].strIngredient6
                    + ", " + searchMeal.meals[0].strIngredient7 + ", " + searchMeal.meals[0].strIngredient8
                    + ", " + searchMeal.meals[0].strIngredient9 + ", " + searchMeal.meals[0].strIngredient10
                    + ", " + searchMeal.meals[0].strIngredient11 + ", " + searchMeal.meals[0].strIngredient12
                    + ", " + searchMeal.meals[0].strIngredient13 + ", " + searchMeal.meals[0].strIngredient14
                    + ", " + searchMeal.meals[0].strIngredient15 + ", " + searchMeal.meals[0].strIngredient16
                    + ", " + searchMeal.meals[0].strIngredient17 + ", " + searchMeal.meals[0].strIngredient18
                    + ", " + searchMeal.meals[0].strIngredient19 + ", " + searchMeal.meals[0].strIngredient20)

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
        return  root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        parentFragment!!.activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
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

    private fun getImage(url: String){
        Picasso.get()
            .load(url)
            .fit()
            .into(mealImage)
    }
}
