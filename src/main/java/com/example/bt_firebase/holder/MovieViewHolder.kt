package com.example.bt_firebase.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bt_firebase.R
import com.example.bt_firebase.model.Movie

class MovieViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
    val txtName: TextView = itemView.findViewById(R.id.txtName)
    val txtDuration: TextView = itemView.findViewById(R.id.txtDuration)
    fun bind(movie: Movie) {
            txtDuration.text = movie.duration.toString()
            txtName.text = movie.name
    }
}
