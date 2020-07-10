package com.leakyquill.bb84.UserPostActivites

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import android.widget.VideoView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leakyquill.bb84.R
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.engine.impl.GlideEngine
import com.zhihu.matisse.filter.Filter
import kotlinx.android.synthetic.main.activity_files_post.*

private const val REQUEST_CODE_CHOOSE = 23

class FilesPostActivity : AppCompatActivity() {


    private var mAdapter: UriAdapter? = null
    private var permissionArray: Array<String> = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_files_post)

        val recyclerView = findViewById<View>(R.id.recyclerview) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        recyclerView.adapter = UriAdapter().also { mAdapter = it }

        if (ContextCompat.checkSelfPermission(this@FilesPostActivity,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

            startAction()

        } else {
            requestStoragePermission();
        }

        back_button.setOnClickListener {
            this@FilesPostActivity.finish()
        }

    }

    private fun requestStoragePermission() {
        ActivityCompat.requestPermissions(this@FilesPostActivity, permissionArray, 2)
    }

    private fun startAction(){

        Matisse.from(this@FilesPostActivity)
            .choose(MimeType.ofAll(), false)
            .countable(false)
            .capture(false)
            .maxSelectable(1)
            .addFilter(GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
            .gridExpectedSize(
                resources.getDimensionPixelSize(R.dimen.grid_expected_size))
            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
            .thumbnailScale(0.85f)
            .imageEngine(GlideEngine())
            .showSingleMediaType(true)
            .originalEnable(true)
            .maxOriginalSize(10)
            .autoHideToolbarOnSingleTap(true)
            .forResult(REQUEST_CODE_CHOOSE)

        mAdapter?.setData(null, null)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data == null){
            this@FilesPostActivity.finish()
        }

        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == Activity.RESULT_OK) {

            mAdapter?.setData(Matisse.obtainResult(data), Matisse.obtainPathResult(data))
            Log.i("OnActivityResult ", Matisse.obtainOriginalState(data).toString())


        }
    }

    private class UriAdapter : RecyclerView.Adapter<UriAdapter.UriViewHolder>() {
        private var mUris: List<Uri>? = null
        private var mPaths: List<String>? = null
        private var context: Context? = null
        fun setData(uris: List<Uri>?, paths: List<String>?) {
            mUris = uris
            mPaths = paths

            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UriViewHolder {
            context = parent.context
            return UriViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.uri_item, parent, false))
        }

        override fun onBindViewHolder(holder: UriViewHolder, position: Int) {

//            holder.mUri.text = mUris!![position].toString()
//            holder.mPath.text = mPaths!![position]

            if (mUris!![position].toString().contains("images")) {
                holder.video.visibility = View.GONE
                holder.thumb.visibility = View.VISIBLE
                holder.thumb.setImageURI(mUris!![position])
            }
            else {
                holder.thumb.visibility = View.GONE
                holder.video.visibility = View.VISIBLE
                Log.i("file type---->", "Video file")
                holder.video.setVideoURI(mUris!![position])
                holder.video.start()
            }

        }

        override fun getItemCount(): Int {
            return if (mUris == null) 0 else mUris!!.size
        }

        internal class UriViewHolder(contentView: View) : RecyclerView.ViewHolder(contentView) {

            val thumb: ImageView
            val video: VideoView

            init {
                thumb = contentView.findViewById(R.id.thumbnail_image)
                video = contentView.findViewById(R.id.thumbnail_video)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 2) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();

                startAction()

            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }

    }

}
