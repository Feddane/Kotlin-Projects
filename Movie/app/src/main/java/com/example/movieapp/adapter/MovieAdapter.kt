package com.example.movieapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movieapp.R
import com.example.movieapp.databinding.ItemRowBinding
import com.example.movieapp.response.MoviesListResponse
import com.example.movieapp.uii.DetailesMovieActivity
import com.example.movieapp.utils.Constants.POSTER_BASE_URL


class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private lateinit var binding : ItemRowBinding
    private lateinit var context : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRowBinding.inflate(inflater, parent, false)
        context = parent.context
        return ViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
        holder.setIsRecyclable(false) //pour eviter que les elements se repetent dans recyclerview
    }


    inner class ViewHolder(private val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MoviesListResponse.Result) {
            binding.apply {
                tvMovieName.text = item.title
                tvMovieDateRelease.text = item.releaseDate
                tvRate.text = item.voteAverage.toString()
                val moviePosterURL = POSTER_BASE_URL + item?.posterPath
                ImgMovie.load(moviePosterURL) {
                    crossfade(true)
                    placeholder(R.drawable.poster_placeholder)
                }
                tvLang.text = item.originalLanguage

                //when you click on you go to the second activity "DetailesMovieActivity"
                root.setOnClickListener {
                    val intent = Intent(context, DetailesMovieActivity::class.java)
                    intent.putExtra("id", item?.id)
                    context.startActivity(intent)
                }
            }
        }
    }

/* calculer les différences et de mettre à jour la RecyclerView en conséquence,
    ce qui améliore les performances et l'efficacité de votre application. */
    private val differCallback = object : DiffUtil.ItemCallback<MoviesListResponse.Result>() {
        override fun areItemsTheSame(oldItem: MoviesListResponse.Result, newItem: MoviesListResponse.Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MoviesListResponse.Result, newItem: MoviesListResponse.Result): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

}