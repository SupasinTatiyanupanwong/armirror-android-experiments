package me.tatiyanupanwong.supasin.android.apps.armirror

import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG

private const val MIN_OPENGL_VERSION = 3.0

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!isOpenGlSupported(this)) {
            Toast.makeText(this, "Sceneform requires OpenGL ES 3.0 or later", LENGTH_LONG).show()
            finish()
            return
        }

        setContentView(R.layout.activity_main)
    }

    private fun isOpenGlSupported(context: Context): Boolean {
        val openGlVersionString = (context.getSystemService(ACTIVITY_SERVICE) as ActivityManager)
                .deviceConfigurationInfo
                .glEsVersion

        return openGlVersionString.toDouble() >= MIN_OPENGL_VERSION
    }
}
