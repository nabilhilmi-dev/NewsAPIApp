package com.nabil.newsapi

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.size.Scale
import com.nabil.newsapi.databinding.CdvNewsHeadlineBinding
import com.nabil.newsapi.model.ArticlesItem

class CdvNewsHeadlinesAdapter : RecyclerView.Adapter<CdvNewsHeadlinesVH>(){

    //untuk mengambil data di dalam model article item
    private val listData = ArrayList<ArticlesItem>()

    //fungsi ini berfungsi untuk add data ke dalam recyclerview
    fun addData(items: List<ArticlesItem>){
        listData.clear()
        listData.addAll(items)
        notifyDataSetChanged()
    }

    //berfungsi untuk menginflate atau menerapkan operasi yang di buat kedalam layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CdvNewsHeadlinesVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CdvNewsHeadlineBinding.inflate(layoutInflater,parent,false)
        return CdvNewsHeadlinesVH(binding)
    }
    //digunakan untuk mengetahui panjang/banyak data guna kebutuhan looping
    override fun getItemCount(): Int {
        return listData.size

    }
    //
    override fun onBindViewHolder(holder: CdvNewsHeadlinesVH, position: Int) {
        holder.bind(listData[position])

    }
}

class CdvNewsHeadlinesVH(private val binding: CdvNewsHeadlineBinding) :
    RecyclerView.ViewHolder(binding.root){
    fun bind(article: ArticlesItem){
        binding.run {
            txtTitle.text = cropText(article.title?: "tidak ada judul")
            txtSubtitle.text = article.publishedAt
            imgHeadline.apply {
                load(article.urlToImage){
                    scale(Scale.FILL)
                }
                contentDescription = article.description
            }

            root.setOnClickListener {
                val intent = Intent(it.context, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.DETAIL_NEWS, article)//detail_news berfungsi sebagai variable yang akan dikirimkan ke detail activity

                }
                it.context.startActivity(intent)
            }
        }

    }

    private fun cropText(text: String): String{
        return text.take(50) + if (text.length > 50)"..." else ""
    }

}
