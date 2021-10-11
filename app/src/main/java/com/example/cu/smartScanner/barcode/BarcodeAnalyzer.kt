package com.example.cu.smartScanner.barcode

import android.annotation.SuppressLint
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.fragment.app.FragmentManager
import com.google.mlkit.vision.barcode.Barcode
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage


class BarcodeAnalyzer(private val fragmentManager: FragmentManager)
    :ImageAnalysis.Analyzer {

    private var bottomSheet = BarcodeResultBottomSheet()

    val options = BarcodeScannerOptions.Builder()
        .setBarcodeFormats(
            Barcode.FORMAT_QR_CODE,
            Barcode.FORMAT_AZTEC
        )
        .build()

    private val scanner = BarcodeScanning.getClient(options)

    @SuppressLint("UnsafeExperimentalUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        Log.d("BARCODE", "image analysed")

        imageProxy.image?.let {
            val inputImage = InputImage.fromMediaImage(
                it,
                imageProxy.imageInfo.rotationDegrees
            )

            scanner.process(inputImage)
                .addOnSuccessListener{ barcodes ->
                    readerBarcodeData(barcodes)
                    }
                }
                ?.addOnFailureListener { ex ->
                    Log.e("BARCODE", "Detection failed", ex)
                }
                ?.addOnCompleteListener {
                    imageProxy.close()
                }

        }

    private fun readerBarcodeData(barcodes: List<Barcode>) {
        for (barcode in barcodes) {
            val bounds = barcode.boundingBox
            val corners = barcode.cornerPoints

            val rawValue = barcode.rawValue

            // See API reference for complete list of supported types
            when (barcode.valueType) {

                Barcode.TYPE_URL -> {
                    val title = barcode.url!!.title
                    val url = barcode.url!!.url

                    if(!bottomSheet.isAdded){
                        bottomSheet.show(fragmentManager,"")
                        bottomSheet.updateURL(barcode.url?.url.toString())
                    }
                }
            }
        }

    }

}
