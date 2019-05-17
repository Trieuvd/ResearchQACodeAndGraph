package com.example.researchqacodeandgraph

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.Result
import kotlinx.android.synthetic.main.qr_code_acitivity.*
import me.dm7.barcodescanner.zxing.ZXingScannerView

class QRCodeActivity : AppCompatActivity(), ZXingScannerView.ResultHandler,
    BottomSheetQRCode.BottomSheetQRCodeListener {

    private lateinit var bottomSheetQRCode: BottomSheetQRCode

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qr_code_acitivity)

        zsvCamera.setResultHandler(this)
    }

    override fun onResume() {
        zsvCamera.startCamera()
        super.onResume()
    }

    override fun handleResult(p0: Result?) {
        bottomSheetQRCode = BottomSheetQRCode(this, p0?.text)
        bottomSheetQRCode.show(supportFragmentManager, "qr code")
    }

    override fun onDecline() {

    }

    override fun onCheckIn() {

    }

    override fun onDissmiss() {
        zsvCamera.resumeCameraPreview(this)
    }

    override fun onPause() {
        zsvCamera.stopCamera()
        super.onPause()
    }
}