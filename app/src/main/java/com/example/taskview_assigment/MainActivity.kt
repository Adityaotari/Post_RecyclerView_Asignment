package com.example.taskview_assigment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerPost : RecyclerView
    private val posts = ArrayList<Post>()
    private lateinit var createPostAdapter: CreatePostAdapter

    private lateinit var name : String
    private lateinit var caption: String

    private var selectedImage : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initData()

        createPostAdapter = CreatePostAdapter(posts)
        createPostAdapter.onPostClickListener = MyPostClickListener()
        recyclerPost.adapter = createPostAdapter

        recyclerPost.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    inner class MyPostClickListener : CreatePostAdapter.OnPostClickListener{
        override fun onPostClick(position: Int, post: Post, view: View) {
            // Toast.makeText(this@MainActivity,"Clicked", Toast.LENGTH_SHORT).show()

            val intent = Intent(this@MainActivity, ViewPost::class.java)
            intent.putExtra("position", position)
            intent.putExtra("name", posts[position].name)
            intent.putExtra("caption", posts[position].caption)

            intent.putExtra("selectedImage", posts[position].imageApp)

            startActivityForResult(intent,2)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_activity_menu, menu)
        val addPost = menu?.findItem(R.id.addPost)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this, CreatePost::class.java)
        startActivityForResult(intent,1)
        return true
    }

    @Deprecated("Deprecated in Java")
    @SuppressLint("NotifyDataSetChanged")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == 1) {
            name = data?.getStringExtra("name").toString()
            caption = data?.getStringExtra("caption").toString()

            selectedImage = data?.getIntExtra("image", 0)!!.toInt()

            posts.add(Post(name, caption, selectedImage))
            createPostAdapter.notifyDataSetChanged()

        }
        if (requestCode == 2 && resultCode == 3) {
            val position = data?.getIntExtra("position", -1)
            if (position != -1) {
                name = data?.getStringExtra("name").toString()
                caption = data?.getStringExtra("caption").toString()

                selectedImage = data?.getIntExtra("selectedImage", 0).toString().toInt()


                if (position != null) {
                    posts[position] = Post(name, caption, selectedImage)
                    createPostAdapter.notifyItemChanged(position)
                }
            }
        }

        if(requestCode == 2 && resultCode == 4) {
            val position = data?.getIntExtra("position", -1)
            if (position != -1) {
                if (position != null) {
                    posts.removeAt(position)
                }
                if (position != null) {
                    createPostAdapter.notifyItemRemoved(position)
                }
            }
        }
    }

    private fun initData(){
        setContentView(R.layout.activity_main)
        recyclerPost = findViewById(R.id.recyclerPost)
    }
}