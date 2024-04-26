package com.dicoding.asclepius.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dicoding.asclepius.R
import com.dicoding.asclepius.databinding.ActivityCropBinding
import com.dicoding.asclepius.databinding.ActivityResultBinding
import com.yalantis.ucrop.UCrop
import java.io.File
import java.util.UUID

class CropActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCropBinding
    private lateinit var croppedImageUri: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCropBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageUri = Uri.parse(intent.getStringExtra(IMAGE_TO_CROP))

        val destinationUri = StringBuilder(UUID.randomUUID().toString()).append(".jpg").toString()

        UCrop.of(imageUri, Uri.fromFile(File(cacheDir, destinationUri)))
            .withAspectRatio(1F, 1F)
            .withMaxResultSize(2000, 2000)
            .start(this);
    }
    companion object {
        const val IMAGE_TO_CROP = "image_to_crop"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            croppedImageUri = UCrop.getOutput(data!!).toString()
            val returnIntent = Intent()
            returnIntent.putExtra("CROPPED_IMAGE", croppedImageUri)
            setResult(-1, returnIntent)
            finish()
        } else if(resultCode==UCrop.RESULT_ERROR){
            val cropError = data?.let { UCrop.getError(it) };

        }
    }
}