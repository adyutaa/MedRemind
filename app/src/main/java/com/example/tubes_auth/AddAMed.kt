package com.example.tubes_auth

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class AddAMed : AppCompatActivity(){
    private lateinit var selectedImageView: ImageView
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private var isBtnSelected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_amed)

        // Initialize views
        val med1 = findViewById<ImageView>(R.id.med1)
        val med2 = findViewById<ImageView>(R.id.med2)
        val med3 = findViewById<ImageView>(R.id.med3)
        val med4 = findViewById<ImageView>(R.id.med4)

        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        val btnNext = findViewById<Button>(R.id.btnNext)

        // Set click listeners for ImageViews
        med1.setOnClickListener { handleImageClick(it as ImageView) }
        med2.setOnClickListener { handleImageClick(it as ImageView) }
        med3.setOnClickListener { handleImageClick(it as ImageView) }
        med4.setOnClickListener { handleImageClick(it as ImageView) }

        // Set click listeners for Buttons
        btn1.setOnClickListener { handleButtonClick(btn1) }
        btn2.setOnClickListener { handleButtonClick(btn2) }
        btn3.setOnClickListener { handleButtonClick(btn3) }

        // Set click listener for Next Button
        btnNext.setOnClickListener { navigateToNextPage() }
    }

    private fun handleImageClick(imageView: ImageView) {
        // Reset background color for all ImageViews
        resetBackgroundColors()

        // Set background color to the selected ImageView for highlighting effect
        imageView.setBackgroundColor(resources.getColor(R.color.selected_image_color))

        selectedImageView = imageView
    }

    private fun resetBackgroundColors() {
        val med1 = findViewById<ImageView>(R.id.med1)
        val med2 = findViewById<ImageView>(R.id.med2)
        val med3 = findViewById<ImageView>(R.id.med3)
        val med4 = findViewById<ImageView>(R.id.med4)

        med1.setBackgroundColor(resources.getColor(android.R.color.transparent))
        med2.setBackgroundColor(resources.getColor(android.R.color.transparent))
        med3.setBackgroundColor(resources.getColor(android.R.color.transparent))
        med4.setBackgroundColor(resources.getColor(android.R.color.transparent))
    }

    private fun handleButtonClick(clickedButton: Button) {
        // Reset all Button colors
        btn1.setBackgroundColor(resources.getColor(R.color.light_gray))
        btn2.setBackgroundColor(resources.getColor(R.color.light_gray))
        btn3.setBackgroundColor(resources.getColor(R.color.light_gray))

        // Set the selected Button's color to #8391A1
        clickedButton.setBackgroundColor(resources.getColor(R.color.selected_button_color))

        // Set isBtnSelected flag to true
        isBtnSelected = true
    }

    private fun navigateToNextPage() {
        // Check conditions before navigating to the next page
        val nameMed = findViewById<EditText>(R.id.nameMed)
        val whatForMed = findViewById<EditText>(R.id.whatForMed)
        val doseMed = findViewById<EditText>(R.id.doseMed)

        if (isBtnSelected && selectedImageView != null &&
            nameMed.text.isNotEmpty() &&
            whatForMed.text.isNotEmpty() &&
            doseMed.text.isNotEmpty()
        ) {
            // All conditions met, navigate to next page
            // Add your code to navigate to the next activity here
            Toast.makeText(this, "Navigating to the next page...", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Please fill all the information!", Toast.LENGTH_SHORT).show()
        }
    }
}
