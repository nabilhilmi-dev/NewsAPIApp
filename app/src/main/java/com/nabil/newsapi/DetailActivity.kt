package com.nabil.newsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.api.load
import coil.size.Scale
import com.nabil.newsapi.databinding.ActivityDetailBinding
import com.nabil.newsapi.model.ArticlesItem
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.view.*
import kotlinx.android.synthetic.main.cdv_news_headline.*

class DetailActivity : AppCompatActivity() {

    //variable untuk menangkap data yang dikirimkan oleh MainActivity
    companion object{
        const val DETAIL_NEWS = "DETAIL_NEWS"
    }

    private lateinit var binding: ActivityDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)


        val data = intent.getParcelableExtra(DETAIL_NEWS) as ArticlesItem

        binding.run {
            setContentView(root)

            setSupportActionBar(toolBar)

            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            imgToolbar.apply {
                load(data.urlToImage) {
                    scale(Scale.FILL)
                }
                contentDescription = data.description

            }
            txtContent.text = data.content

            txtDate.text = data.publishedAt


        }


    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}