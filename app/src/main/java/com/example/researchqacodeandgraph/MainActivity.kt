package com.example.researchqacodeandgraph

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions

class MainActivity : AppCompatActivity(), View.OnClickListener,
    EasyPermissions.PermissionCallbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGraph.setOnClickListener(this)
        btnQRCode.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            btnGraph.id -> startActivity(Intent(this, GraphActivity::class.java))

            btnQRCode.id -> checkPermissionCamera()
        }
    }

    @AfterPermissionGranted(RC_CAMERA)
    private fun checkPermissionCamera() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)) {
            startActivity(Intent(this, QRCodeActivity::class.java))
        } else {
            EasyPermissions.requestPermissions(
                this,
                getString(R.string.rationale_camera),
                RC_CAMERA, Manifest.permission.CAMERA
            )
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {}

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        Log.d("permission", "ok")
        startActivity(Intent(this, QRCodeActivity::class.java))
    }

    companion object {
        private const val RC_CAMERA = 1000
    }
}
