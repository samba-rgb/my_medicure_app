package id.samai.mymedcure.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import id.samai.mymedcure.R
import jp.wasabeef.picasso.transformations.CropCircleTransformation

class about : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        val uri = "https://firebasestorage.googleapis.com/v0/b/fir-recyclerview-b8013.appspot.com/o/samba.jpeg?alt=media&token=ddbd77bd-1a7d-4494-86c8-94a9d915f594"
val uri2 = "https://firebasestorage.googleapis.com/v0/b/fir-recyclerview-b8013.appspot.com/o/ashish.jpeg?alt=media&token=63db5d07-6d11-4dfb-b57d-c27c67cce6b8"

        val imglogin1 = findViewById<ImageView>(R.id.image_profile_samba)
        Glide.with(this).load(uri).into(imglogin1)
        val imglogin2 = findViewById<ImageView>(R.id.image_profile_ashish)
        Picasso.get().load(uri2).transform(CropCircleTransformation()).into(imglogin2)

    }
}