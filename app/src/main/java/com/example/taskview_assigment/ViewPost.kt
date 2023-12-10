package com.example.taskview_assigment

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class ViewPost: AppCompatActivity() {
    private lateinit var viewImgSender : ImageView
    private lateinit var viewTxtName : TextView
    private lateinit var viewTxtCaption : TextView


    private lateinit var name : String
    private lateinit var caption : String

    private  var imgRoom : Int = 0

    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_post)

        initViews()
        initIntent()
    }
    private fun initIntent() {
        val position = intent.getIntExtra("position", -1)
        if (position != -1) {
            name = intent.getStringExtra("name").toString()
            caption = intent.getStringExtra("caption").toString()
            imgRoom = intent.getIntExtra("selectedImage", 0).toString().toInt()

            viewTxtName.setText(name)
            viewTxtCaption.setText(caption)

            viewImgSender.setImageResource(imgRoom)
        } else {
            Toast.makeText(this, "Position not matched", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.view_post_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.editPost->{
                val intent = Intent(this@ViewPost, EditPost::class.java)
                intent.putExtra("position", position)
                intent.putExtra("name", name)
                intent.putExtra("caption", caption)
                intent.putExtra("selectedImage", imgRoom)
                startActivityForResult(intent, 3)
                return true
            }
            R.id.deletePost->{
                val builder = AlertDialog.Builder(this)
                builder.setMessage("Are you sure you want to delete this post?")
                    .setCancelable(false)
                    .setPositiveButton("Yes") {
                            dialog, id ->
                        val intent = Intent()
                        intent.putExtra("position", position)
                        setResult(4, intent)
                        finish()
                    }
                    .setNegativeButton("No") { dialog, id ->
                        dialog.dismiss()
                    }
                val alert = builder.create()
                alert.show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 3 && resultCode == 3) {

            position = data?.getIntExtra("position", -1)!!
            if (position != -1) {
                name = data?.getStringExtra("name").toString()
                caption = data?.getStringExtra("caption").toString()
                imgRoom = data?.getIntExtra("selectedImage", 0).toString().toInt()

                viewTxtName.setText(name)
                viewTxtCaption.setText(caption)

                viewImgSender.setImageResource(imgRoom)

                val intent = Intent()
                intent.putExtra("position", position)
                intent.putExtra("name", name)
                intent.putExtra("caption", caption)
                intent.putExtra("selectedImage", imgRoom)
                setResult(3, intent)
                finish()
            }
        }
    }

    private fun initViews(){
        viewTxtName = findViewById(R.id.viewTxtName)
        viewTxtCaption = findViewById(R.id.viewTxtCaption)
        viewImgSender = findViewById(R.id.viewImgSender)
    }
}