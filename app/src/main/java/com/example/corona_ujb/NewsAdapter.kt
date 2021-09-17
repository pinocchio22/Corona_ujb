package com.example.corona_ujb

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * @author CHOI
 * @email vviian.2@gmail.com
 * @created 2021-09-17
 * @desc
 */
class NewsAdapter(viewModel: SearchViewModel) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    private val viewModel = viewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
        val binding: NewsItemviewBinding = NewsItemviewBinding.inflate()
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = viewModel.getNewsItem().size

    override fun onBindViewHolder(holder: NewsAdapter.ViewHolder, position: Int) {
        holder.bind(viewModel, position)
    }

    inner class ViewHolder(binding: NewsItemviewBinding) : RecyclerView.ViewHolder(binding.root) {
        val binding = binding

        fun bind(viewModel: SearchViewModel, pos: Int) {
            binding.pos = pos
            binding.SearchViewModel = viewModel
            binding.executePendingBindings()
        }
    }
}