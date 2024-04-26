package com.dicoding.asclepius.view

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.dicoding.asclepius.R
import com.dicoding.asclepius.databinding.ActivityResultBinding
import com.dicoding.asclepius.view.history.HistoryViewModel

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        val historyViewModel: HistoryViewModel by viewModels {
            factory
        }

        val imageUri = Uri.parse(intent.getStringExtra(EXTRA_IMAGE_URI))
        val label = intent.getStringExtra(EXTRA_RESULT_LABEL)
        val score = (intent.getFloatExtra(EXTRA_RESULT_SCORE, 0F) * 100).toInt()

        val resultText = "$label\n $score%"

        imageUri?.let {
            binding.resultImage.setImageURI(it)
            binding.resultText.text = resultText
            binding.addHistoryButton.setOnClickListener {
                historyViewModel.addHistory(imageUri.toString(), label, score)
            }
        }

    }

    companion object {
        const val EXTRA_IMAGE_URI = "extra_image_uri"
        const val EXTRA_RESULT_LABEL = "extra_result_label"
        const val EXTRA_RESULT_SCORE = "extra_result_score"
    }
}