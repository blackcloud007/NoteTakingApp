package com.kowshik.notemakingapp

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ProgressBar
import androidx.core.os.postDelayed

@Suppress("DEPRECATION")
class SplashScreen : AppCompatActivity() {
    lateinit var progressBar: ProgressBar
    companion object{
        private const val SPLASH_OUT:Long=3000
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        supportActionBar?.hide(); // hide the title bar
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_splash_screen)
        progressBar=findViewById(R.id.progressbar)
        progressBar.visibility= View.VISIBLE
        Handler().postDelayed(Runnable {
            startActivity(Intent(applicationContext,MainActivity::class.java))
            progressBar.visibility= View.INVISIBLE
            finish()
        }, SPLASH_OUT)
    }
}