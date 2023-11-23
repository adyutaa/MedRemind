package com.example.tubes_auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.tubes_auth.LoginActivity
import com.example.tubes_auth.R
import com.example.tubes_auth.SignupActivity
import com.example.tubes_auth.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class WelcomeLogin : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loginIntent =  findViewById<Button>(R.id.btnToLogin)
        loginIntent.setOnClickListener{
            val intentToLogin = Intent(this, LoginActivity::class.java)
            startActivity(intentToLogin)


        }

        val RegistrationIntent = findViewById<Button>(R.id.btnToRegistration)
        RegistrationIntent.setOnClickListener {
            val intentToRegistration = Intent(this, SignupActivity::class.java)
            startActivity(intentToRegistration)
        }

        firebaseAuth = FirebaseAuth.getInstance()


        binding.textView2.setOnClickListener{ task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "signInAnonymously:success")
                val user = firebaseAuth.currentUser
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "signInAnonymously:failure", task.exception)
                Toast.makeText(
                    baseContext,
                    "Authentication failed.",
                    Toast.LENGTH_SHORT,
                ).show()

            }
        }

        val currentUser = firebaseAuth.currentUser

        if(currentUser != null) {

            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }

    }



}