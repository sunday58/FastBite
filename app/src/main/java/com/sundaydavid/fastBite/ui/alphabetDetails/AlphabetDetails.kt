package com.sundaydavid.fastBite.ui.alphabetDetails

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sundaydavid.fastBite.R

/**
 * A simple [Fragment] subclass.
 */
class AlphabetDetails : Fragment() {

    lateinit var sheetBehavior: BottomSheetBehavior<View>
    lateinit var bottomSheet: LinearLayout

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

        parentFragment!!.activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        return  root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        parentFragment!!.activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    }

}
