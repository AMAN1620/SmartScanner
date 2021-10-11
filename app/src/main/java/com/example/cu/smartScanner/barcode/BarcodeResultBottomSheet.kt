package com.example.cu.smartScanner.barcode

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.cu.smartScanner.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet_barcode_data.view.*
import org.jsoup.Jsoup
import java.io.IOException
import java.util.concurrent.Executors

class BarcodeResultBottomSheet:BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.bottom_sheet_barcode_data, container, false)

    fun updateURL(url: String) {
        fetchUrlMetaData(url) { title ->
            view?.apply {
                findViewById<TextView>(R.id.text_view_title)?.text = title
                findViewById<TextView>(R.id.text_view_link)?.text = url
                findViewById<TextView>(R.id.text_view_visit_link).setOnClickListener { _ ->
                    Intent(Intent.ACTION_VIEW).also {
                        it.data = Uri.parse(url)
                        startActivity(it)
                    }
                }
            }
        }
    }

    private fun fetchUrlMetaData(
        url: String,
        callback: (title: String) -> Unit
    ) {

        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        executor.execute {
            //val doc = Jsoup.connect(url).get()
            try{
                val doc = Jsoup.connect(url).get()
                handler.post {
                    callback(doc.title())
                }
            }catch(e: IOException){
            }

        }
    }
}