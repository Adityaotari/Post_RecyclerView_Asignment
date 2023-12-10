package com.example.taskview_assigment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class CreatePost: AppCompatActivity() {

    private lateinit var img1 : ImageView
    private lateinit var img2 : ImageView
    private lateinit var img3 : ImageView
    private lateinit var edtName : EditText
    private lateinit var edtCaption : EditText
    private lateinit var btnMakePost : Button

    private var selectedImage : Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.post)

        initData()
        initListener()

        btnMakePost.setOnClickListener{
            val name = edtName.text.toString()
            val caption = edtCaption.text.toString()


            val intent = Intent()
            intent.putExtra("name", name)
            intent.putExtra("caption", caption)

            intent.putExtra("image", selectedImage)

            setResult(1, intent)
            finish()
        }
    }
    private fun initData(){

        img1 = findViewById(R.id.img1)
        img2 = findViewById(R.id.img2)
        img3 = findViewById(R.id.img3)
        edtName = findViewById(R.id.edtName)
        edtCaption = findViewById(R.id.edtCaption)

        btnMakePost = findViewById(R.id.btnMakePost)
    }

    private fun initListener(){
        img1.setOnClickListener {
            selectedImage = R.drawable.whatsapp
        }
        img2.setOnClickListener {
            selectedImage = R.drawable.instagram
        }
        img3.setOnClickListener {
            selectedImage = R.drawable.faceook
        }

}}