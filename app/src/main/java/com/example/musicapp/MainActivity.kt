package com.example.musicapp

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.ui.theme.MusicAppTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : ComponentActivity() {

    lateinit var myRecyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myRecyclerView = findViewById(R.id.recyclerView)

        val retrofitBuilder = Retrofit.Builder().baseUrl("https://deezerdevs-deezer.p.rapidapi.com/").addConverterFactory(
            GsonConverterFactory.create()).build().create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData(query = "eminem")

        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(p0: Call<MyData?>, p1: Response<MyData?>) {
                //To be executed if this method is successful
                val dataList = p1.body()?.data!!
//                val textView = findViewById<TextView>(R.id.helloText)
//                textView.text = dataList.toString()
                myAdapter = MyAdapter(this@MainActivity, dataList)
                myRecyclerView.adapter = myAdapter
                myRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                Log.d("TAG: onResponse", "onResponse:" + p1.body())
            }

            override fun onFailure(p0: Call<MyData?>, p1: Throwable) {
                //To be executed if this method fails
                Log.d("TAG: onFailure", "onFailure:" + p1.message)
            }
        })
    }
}