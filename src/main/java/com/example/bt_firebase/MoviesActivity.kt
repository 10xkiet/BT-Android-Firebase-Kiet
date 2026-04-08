package com.example.bt_firebase

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bt_firebase.adapter.MovieAdapter
import com.example.bt_firebase.model.Movie
import com.google.firebase.firestore.FirebaseFirestore

class MoviesActivity : AppCompatActivity() {

    private lateinit var rvMovies: RecyclerView
    private lateinit var adapter: MovieAdapter
    private lateinit var movies: MutableList<Movie>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_movies)

        rvMovies = findViewById(R.id.rvMovies)

        // ✅ fix lỗi
        movies = mutableListOf()

        adapter = MovieAdapter(movies)
        rvMovies.layoutManager = GridLayoutManager(this,2)
        rvMovies.adapter = adapter

        loadMovies()
    }

    private fun loadMovies() {
        val db = FirebaseFirestore.getInstance()

        db.collection("movies")
            .get()
            .addOnSuccessListener { result ->
                movies.clear()
                for (doc in result) {
                    val movie = doc.toObject(Movie::class.java)
                    movies.add(movie)
                }
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Load failed!", Toast.LENGTH_SHORT).show()
            }
    }
}