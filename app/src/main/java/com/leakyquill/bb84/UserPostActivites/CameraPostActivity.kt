package com.leakyquill.bb84.UserPostActivites

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.leakyquill.bb84.R
import java.io.File


private const val CAMERA_REQUEST_CODE = 100
private const val PERMISSION_REQUEST_CODE = 101
private const val FILE_NAME = "photo"

class CameraPostActivity : AppCompatActivity() {

    private lateinit var uploadImage: ImageView
    private lateinit var back: ImageView
    private lateinit var post: TextView
    private lateinit var photoFile: File
    private lateinit var pictureIntent: Intent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_post)

        uploadImage = findViewById(R.id.uploadImage)
        back = findViewById(R.id.back_button)
        post = findViewById(R.id.post_text_view)

        back.setOnClickListener {
            this@CameraPostActivity.finish()
        }

        post.setOnClickListener {
            Toast.makeText(this@CameraPostActivity, "image posted", Toast.LENGTH_SHORT).show()
            this@CameraPostActivity.finish()
        }

        pictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        photoFile = getPhotoFile(FILE_NAME)

        val fileProvider =
            FileProvider.getUriForFile(this, "com.leakyquill.bb84.fileprovider", photoFile)


        pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)

        if (checkPermission()) {
            startActivityForResult(pictureIntent, CAMERA_REQUEST_CODE)
        }
        else {
            requestPermission()
        }
    }

    private fun checkPermission(): Boolean {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return false
        }
        return false
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA),
            PERMISSION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startActivityForResult(pictureIntent, CAMERA_REQUEST_CODE)
            }
            else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED
                    ) {
                        Toast.makeText(
                            this@CameraPostActivity,
                            " You need to have this permission in order to use camera",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun getPhotoFile(fileName: String): File {
        val storageDirectory = this@CameraPostActivity.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        return File.createTempFile(fileName, ".jpg", storageDirectory)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CAMERA_REQUEST_CODE){


//                var bitmap: Bitmap = data?.extras?.get("data") as Bitmap
//                var uri : Uri? = data.data
//                Log.i("Uri is--->", uri.toString())
//            val takenImage = BitmapFactory.decodeFile(photoFile.absolutePath)

                var uri: Uri = Uri.fromFile(photoFile.absoluteFile)


                Log.i("image Uri----> ", uri.toString())
//                uploadImage.setImageBitmap(takenImage)
                uploadImage.setImageURI(uri)

        }

    }

    override fun onBackPressed() {

        this@CameraPostActivity.finish()

        super.onBackPressed()
    }
}