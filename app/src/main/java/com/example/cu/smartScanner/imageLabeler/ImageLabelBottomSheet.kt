package com.example.cu.smartScanner.imageLabeler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.cu.smartScanner.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.w3c.dom.Text

class ImageLabelBottomSheet : BottomSheetDialogFragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.bottom_sheet_labeler, container, false)

//    fun updateText(string: String){
//        view?.apply {
//            findViewById<TextView>(R.id.object_detected_label).text = string
//        }
//    }
    fun updateText(str:String){
    view?.apply {
        findViewById<TextView>(R.id.object_detected_label).text = str
    }
    }


}