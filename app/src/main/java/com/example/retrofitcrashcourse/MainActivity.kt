package com.example.retrofitcrashcourse

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitcrashcourse.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private var todos: List<Todo> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        CoroutineScope(Dispatchers.Main).launch {
            Log.d("TAG","Coroutine launched")
            binding.pbLoading.isVisible = true
            val response = try {
                RetrofitHandler.api.getTodos()
            }
            catch (e: Exception) {
                Log.d("TAG",e.message!!)
                binding.pbLoading.isVisible = true
                return@launch
            }
            if(response.isSuccessful){
                todos = response.body()!!
            }
            binding.pbLoading.isVisible = false
            Log.d("TAG","Coroutine ended")
            showRecylcerView(todos)
        }
        Log.d("TAG","Outside Coroutine")
    }

    fun showRecylcerView(todos : List<Todo>){
        recyclerViewAdapter = RecyclerViewAdapter(todos)

        binding.rvTodos.apply {
            adapter = recyclerViewAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}