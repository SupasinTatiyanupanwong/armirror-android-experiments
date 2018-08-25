package me.tatiyanupanwong.supasin.android.apps.armirror

import android.Manifest.permission.CAMERA
import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG

private const val MIN_OPENGL_VERSION = 3.0
private const val REQ_CAMERA_PERMISSION = 0x123

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!isOpenGlSupported(this)) {
            Toast.makeText(this, "Sceneform requires OpenGL ES 3.0 or later", LENGTH_LONG).show()
            finish()
            return
        }

        setContentView(R.layout.activity_main)

        requestCameraPermission(this, REQ_CAMERA_PERMISSION)
    }

    override fun onRequestPermissionsResult(
            requestCode: Int, permissions: Array<String>, results: IntArray) {

        if (!hasCameraPermission(this)) {
            Toast.makeText(this, "Camera permission is needed to run this application", LENGTH_LONG)
                    .show()
            finish()
        }
    }

    private fun isOpenGlSupported(context: Context): Boolean {
        val openGlVersionString = (context.getSystemService(ACTIVITY_SERVICE) as ActivityManager)
                .deviceConfigurationInfo
                .glEsVersion

        return openGlVersionString.toDouble() >= MIN_OPENGL_VERSION
    }

    private fun requestCameraPermission(activity: Activity, requestCode: Int) {
        ActivityCompat.requestPermissions(activity, arrayOf(CAMERA), requestCode)
    }

    private fun hasCameraPermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(context, CAMERA) == PERMISSION_GRANTED
    }
}
