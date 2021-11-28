package com.example.cu.smartScanner.textRecog

import android.annotation.SuppressLint
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.fragment.app.FragmentManager
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import java.lang.StringBuilder

class TextAnalyzer(private val fragmentManager: FragmentManager)
    :ImageAnalysis.Analyzer {

    private var bottomsheet = TextResultBottomSheet()

    private var sb = StringBuilder()

    private val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

    @SuppressLint("UnsafeExperimentalUsageError")
    override fun analyze(imageProxy: ImageProxy) {

        Log.d("Text", "image analysed")

        imageProxy.image?.let { it ->
            val inputImage = InputImage.fromMediaImage(
                it,
                imageProxy.imageInfo.rotationDegrees
            )

            recognizer.process(inputImage)
                .addOnSuccessListener { text ->
                    text.textBlocks.forEach { block ->
                        Log.d(
                            "TEXT", """
                            LINES = ${block.lines.joinToString("\n") { it.text }}
                        """.trimIndent()
                        )
       //                 sb.append(block.lines.joinToString("\n"){it.text})
//                        Toast.makeText(this,sb.toString(),Toast.LENGTH_LONG).show()
//                        var Lines = block.lines.joinToString("\n"){it.text}
//                        try {
//
//                            if(!bottomsheet.isAdded){
//                                bottomsheet.textRecog(sb.toString())
//                                bottomsheet.show(fragmentManager,"")
//                            }
//                        }catch (e:Exception){
//
//                        }
                    }

                }
                ?.addOnFailureListener { ex ->
                    Log.e("TEXT", "Detection failed", ex)
                }
                ?.addOnCompleteListener {
                    imageProxy.close()
                }
        }
    }


}