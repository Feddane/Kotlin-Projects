package com.example.trelloclone.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import com.example.trelloclone.R

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

       val btn_sign_up_intro = findViewById<Button>(R.id.btn_sign_up_intro)
       val btn_sign_in_intro = findViewById<Button>(R.id.btn_sign_in_intro)

        // l'application occupe tout l'écran de l'appareil
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        btn_sign_up_intro.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        btn_sign_in_intro.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }
}