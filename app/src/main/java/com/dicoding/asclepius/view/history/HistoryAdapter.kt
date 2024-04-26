package com.dicoding.asclepius.view.history

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.asclepius.data.local.entity.DetectionHistory
import com.dicoding.asclepius.databinding.ItemDetectionBinding

class HistoryAdapter : ListAdapter<DetectionHistory, HistoryAdapter.MyViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemDetectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val aDetection = getItem(position)
        holder.bind(aDetection)
    }

    class MyViewHolder(val binding: ItemDetectionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(aDetectionHistory: DetectionHistory){
            val image = Uri.parse((aDetectionHistory.imageUri))
            binding.imgItemPhoto.setImageURI(image)
            val score = aDetectionHistory.score.toString() + "%"
            binding.tvScore.text = score
            binding.tvLabel.text = aDetectionHistory.label
        }
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DetectionHistory>() {
            override fun areItemsTheSame(oldItem: DetectionHistory, newItem: DetectionHistory): Boolean {
                return oldItem == newItem
            }
            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: DetectionHistory, newItem: DetectionHistory): Boolean {
                return oldItem == newItem
            }
        }
    }

}