package com.example.cu.smartScanner.barcode

import android.media.Image
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.core.content.ContextCompat
import com.example.cu.smartScanner.BaseLensActivity
import com.google.mlkit.vision.common.InputImage

class BarcodeActivity: BaseLensActivity() {

    override val imageAnalyzer = BarcodeAnalyzer(supportFragmentManager)
    override fun startScanner() {
        scanBarcode()
    }

    private fun scanBarcode() {

        imageAnalysis.setAnalyzer(
            ContextCompat.getMainExecutor(this),
            imageAnalyzer
        )

    }
}