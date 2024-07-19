package com.example.postapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var clientPostAdapter: ClientPostAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchPosts()
        recyclerView=findViewById(R.id.wxRecycle)
        recyclerView.layoutManager=LinearLayoutManager(this)

    }
    fun fetchPosts(){
        val apiInterface =
            ApiClient.buildApiInterface(PostApiInterface::class.java)
        val request = apiInterface.fetchPosts()
        request.enqueue(object :Callback<List<Post>>{
            override fun onResponse(p0: Call<List<Post>>, p1: Response<List<Post>>) {
                if(p1.isSuccessful){
                    val posts = p1.body()
                    posts?.let{
                        clientPostAdapter=ClientPostAdapter(it)
                        recyclerView.adapter=clientPostAdapter
                    }
                    Toast.makeText(baseContext, "fetched ${posts!!.size} posts", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(baseContext,p1.errorBody()?.string(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(p0: Call<List<Post>>, p1: Throwable) {
                Toast.makeText(baseContext,p1.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}