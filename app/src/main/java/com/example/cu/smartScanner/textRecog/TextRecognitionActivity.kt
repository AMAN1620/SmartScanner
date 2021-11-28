package com.example.cu.smartScanner.textRecog

import androidx.core.content.ContextCompat
import com.example.cu.smartScanner.BaseLensActivity

class TextRecognitionActivity : BaseLensActivity() {
    override val imageAnalyzer = TextAnalyzer(supportFragmentManager)

    override fun startScanner() {
        startTextRecognition()
    }
    private fun startTextRecognition() {
        imageAnalysis.setAnalyzer(
            ContextCompat.getMainExecutor(this),
            imageAnalyzer
        )
    }
}