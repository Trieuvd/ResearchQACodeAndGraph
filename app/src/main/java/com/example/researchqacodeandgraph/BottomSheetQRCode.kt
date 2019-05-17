package com.example.researchqacodeandgraph

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet_qrcode.*

class BottomSheetQRCode(private val listener: BottomSheetQRCodeListener, private var result: String?) :
    BottomSheetDialogFragment(),
    View.OnClickListener {

    interface BottomSheetQRCodeListener {
        fun onDecline()

        fun onCheckIn()

        fun onDissmiss()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.bottom_sheet_qrcode, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        ivCancel.setOnClickListener(this)
        btnDecline.setOnClickListener(this)
        btnCheckIn.setOnClickListener(this)

        tvResult.text = result
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            ivCancel.id -> dismiss()

            btnDecline.id -> dismiss()

            btnCheckIn.id -> listener.onCheckIn()
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        listener.onDissmiss()

    }
}