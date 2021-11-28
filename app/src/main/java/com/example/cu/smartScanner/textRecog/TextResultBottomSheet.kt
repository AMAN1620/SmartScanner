package com.example.cu.smartScanner.textRecog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.cu.smartScanner.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TextResultBottomSheet : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.bottom_sheet_textrecognition, container, false)

    fun textRecog(text: String){
        view?.apply {
            findViewById<TextView>(R.id.text_recog)?.text = text.toString()
        }

    }

}