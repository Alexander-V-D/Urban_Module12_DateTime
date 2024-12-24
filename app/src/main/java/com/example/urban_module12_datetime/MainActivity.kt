package com.example.urban_module12_datetime

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

const val GALLERY_REQUEST = 145

class MainActivity : AppCompatActivity() {

    private lateinit var pictureIV: ImageView
    private lateinit var nameET: EditText
    private lateinit var surnameET: EditText
    private lateinit var dayET: EditText
    private lateinit var monthET: EditText
    private lateinit var yearET: EditText
    private lateinit var saveBTN: Button
    private var pictureUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pictureIV = findViewById(R.id.pictureIV)
        nameET = findViewById(R.id.nameET)
        surnameET = findViewById(R.id.surnameET)
        dayET = findViewById(R.id.dayET)
        monthET = findViewById(R.id.monthET)
        yearET = findViewById(R.id.yearET)
        saveBTN = findViewById(R.id.saveBTN)

        pictureIV.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, GALLERY_REQUEST)
        }

        saveBTN.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            val name = nameET.text.toString()
            val surname = surnameET.text.toString()
            val day = dayET.text.toString()
            val month = monthET.text.toString()
            val year = yearET.text.toString()
            val picture = pictureUri.toString()
            val person = Person(name, surname, "$day.$month.$year", picture)
            intent.putExtra("person", person)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        pictureIV = findViewById(R.id.pictureIV)
        when(requestCode) {
            GALLERY_REQUEST -> {
                pictureUri = data?.data
                pictureIV.setImageURI(pictureUri)
            }
        }
    }
}