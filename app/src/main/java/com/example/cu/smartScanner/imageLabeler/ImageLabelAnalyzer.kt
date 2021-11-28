package com.example.cu.smartScanner.imageLabeler

import android.annotation.SuppressLint
import android.renderscript.Sampler
import android.util.Log
import android.widget.TextView
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.fragment.app.FragmentManager
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabel
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions

class ImageLabelAnalyzer(private val fragmentManager: FragmentManager) : ImageAnalysis.Analyzer {

    private var bottomSheet = ImageLabelBottomSheet()

    private var resultText = StringBuilder()

    private val labeler = ImageLabeling.getClient(
        ImageLabelerOptions.Builder()
            .setConfidenceThreshold(0.9F)
            .build()
    )


    @SuppressLint("UnsafeExperimentalUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        Log.d("LABEL", "image analysed")

        imageProxy.image?.let {
            val inputImage = InputImage.fromMediaImage(
                it,
                imageProxy.imageInfo.rotationDegrees
            )

            labeler.process(inputImage)
                .addOnSuccessListener { labels ->
                    labels.forEach {

                        labelObjects(labels)
                    }
                }
                .addOnFailureListener { ex ->
                    Log.e("LABEL", "Detection failed", ex)
                }
                .addOnCompleteListener {
                    imageProxy.close()
                }

        } ?: imageProxy.close() // close if image not found either

    }

    private fun labelObjects(labels: List<ImageLabel>) {


        for (label in labels) {
            val text = label.text
            val confidence = label.confidence
            val index = label.index

            resultText.append(text)

            Log.d("text", resultText.toString().trim())


            if(!bottomSheet.isAdded){
                //bottomSheet.fragmentManager?.beginTransaction()
                bottomSheet.updateText(resultText.toString())
                bottomSheet.show(fragmentManager,"")
                bottomSheet.exitTransition
            }

//            if(bottomSheet.isAdded){
//                return
//            }


            //resultText.append(text).trimToSize()

//            when(resultText){
//
//                resultText ->{
//
//                    if(!bottomSheet.isAdded){
//
//                    bottomSheet.fragmentManager?.beginTransaction()
//
//                    bottomSheet.show(fragmentManager,"")
//
//                    bottomSheet.updateText(resultText.toString())
//
//                    //bottomSheet.exitTransition
//
//                }
//
//                }
//
//            }
        }
    }
}