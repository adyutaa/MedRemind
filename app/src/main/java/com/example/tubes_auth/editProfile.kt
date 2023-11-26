package com.example.profiletubes

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class editProfile : AppCompatActivity() {

    private lateinit var autoCompleteTextViewCountry: AutoCompleteTextView
    private lateinit var textInputLayoutPhoneNumber: TextInputLayout
    private lateinit var editTextPhoneNumber: TextInputEditText
    private lateinit var editProfileImageView: ImageView
    private lateinit var editIcon: ImageView

    private val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageUri: Uri? = result.data?.data
            editProfileImageView.setImageURI(imageUri)
        }
    }

    private var selectedPhotoUri: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val saveButton: Button = findViewById(R.id.buttonSubmit)
        saveButton.setOnClickListener {
            val newFullName = findViewById<TextInputEditText>(R.id.etFullname).text.toString()
            val newEmail = findViewById<TextInputEditText>(R.id.etLabel).text.toString()

            selectedPhotoUri = "path/to/photo.jpg"

            // Intent untuk mengirim data kembali ke ProfileActivity
            val intent = Intent(this@editProfile, Profile::class.java)
            intent.putExtra("FULL_NAME", newFullName)
            intent.putExtra("EMAIL", newEmail)
            intent.putExtra("PHOTO_URI", selectedPhotoUri)

            // Mulai aktivitas ProfileActivity dengan membawa data baru
            startActivity(intent)
        }

        editProfileImageView = findViewById(R.id.editProfileImageView)
        editIcon = findViewById(R.id.editIcon)

        editIcon.setOnClickListener {
            openGallery()
        }

        autoCompleteTextViewCountry = findViewById(R.id.actvCountry)
        textInputLayoutPhoneNumber = findViewById(R.id.tiPhone)
        editTextPhoneNumber = findViewById(R.id.etPhone)

        // Inisialisasi Spinner dengan adapter negara
        val countries = arrayOf("Indonesia", "United States")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, countries)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        autoCompleteTextViewCountry.setAdapter(adapter)

        // Tambahkan listener untuk menanggapi perubahan pilihan
        autoCompleteTextViewCountry.setOnItemClickListener { _, _, position, _ ->
            val etPhone: TextInputEditText = findViewById(R.id.etPhone)

            if (position == 0) { // Jika Indonesia dipilih
                etPhone.setText("+62 ") // Kode negara Indonesia
                // Tambahkan ikon atau gambar lambang negara Indonesia jika diperlukan
            }
            else if (position == 1) { // Jika Indonesia dipilih
                etPhone.setText("+1 ") // Kode negara Indonesia
                // Tambahkan ikon atau gambar lambang negara Indonesia jika diperlukan
            }else {
                etPhone.setText("") // Kosongkan jika negara lain dipilih
                // Hapus ikon atau gambar lambang negara Indonesia jika diperlukan
            }
        }

        }
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        getContent.launch(intent)
    }
}


