package com.example.taskview_assigment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class EditPost:AppCompatActivity() {

    private lateinit var name : String
    private lateinit var caption : String


    private lateinit var image1 : ImageView
    private lateinit var image2 : ImageView
    private lateinit var image3 : ImageView
    private lateinit var editedName : EditText
    private lateinit var editedCaption : EditText
    private lateinit var btnUpdate : Button

    private lateinit var updatedName : String
    private lateinit var updatedCaption : String
    private var selectedImage : Int =0

    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.edit_post)

        initData()
        initListener()

        name = intent.getStringExtra("name").toString()
        caption = intent.getStringExtra("caption").toString()

        editedName.setText(name)
        editedCaption.setText(caption)


        btnUpdate.setOnClickListener{
            updatedName = editedName.text.toString()
            updatedCaption = editedCaption.text.toString()


            val intent = Intent()
            intent.putExtra("position", position)
            intent.putExtra("name", updatedName)
            intent.putExtra("caption", updatedCaption)
            intent.putExtra("selectedImage", selectedImage)

            setResult(3, intent)
            finish()
        }
    }
    private fun initData(){

        image1 = findViewById(R.id.image1)
        image2 = findViewById(R.id.image2)
        image3 = findViewById(R.id.image3)
        editedName = findViewById(R.id.editedName)
        editedCaption = findViewById(R.id.editedCaption)
        btnUpdate = findViewById(R.id.btnUpdate)
    }

    private fun initListener(){
        image1.setOnClickListener {
            selectedImage = R.drawable.whatsapp
        }
        image2.setOnClickListener {
            selectedImage = R.drawable.instagram
        }
        image3.setOnClickListener {
            selectedImage = R.drawable.faceook
        }
    }

}