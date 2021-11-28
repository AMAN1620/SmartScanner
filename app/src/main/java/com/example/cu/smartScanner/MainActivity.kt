package com.example.cu.smartScanner

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import com.example.cu.smartScanner.barcode.BarcodeActivity
import com.example.cu.smartScanner.imageLabeler.ImageLabelingActivity
import com.example.cu.smartScanner.textRecog.TextRecognitionActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        @JvmStatic
        val PHOTO_REQ_CODE = 234
        @JvmStatic
        val EXTRA_DATA = "data"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cameraXActivity.setOnClickListener {
            startActivity(Intent(this,CameraActivity::class.java))
        }
        btnBarcodeActivity.setOnClickListener {
            startActivity(Intent(this,BarcodeActivity::class.java))
        }
        btnImageLabelActivity.setOnClickListener {
            startActivity(Intent(this,ImageLabelingActivity::class.java))
        }
        btnTextRecogActivity.setOnClickListener {
            startActivity(Intent(this,TextRecognitionActivity::class.java))
        }


    }

}