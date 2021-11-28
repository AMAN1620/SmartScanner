package com.example.cu.smartScanner.imageLabeler

import androidx.camera.core.ImageAnalysis
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.*
import com.example.cu.smartScanner.BaseLensActivity

class ImageLabelingActivity : BaseLensActivity() {

    override val imageAnalyzer = ImageLabelAnalyzer(supportFragmentManager)

    override fun startScanner() {
        startImageLabeling()
    }

    private fun startImageLabeling() {
        imageAnalysis.setAnalyzer(
            ContextCompat.getMainExecutor(this),
            imageAnalyzer
        )
    }
}