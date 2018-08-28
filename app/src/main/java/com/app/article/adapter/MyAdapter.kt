package com.app.article.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.article.R
import com.app.article.home.PostActivity
import com.app.article.model.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_layout.view.*


class MyAdapter(private val list: List<Article>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return list.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
       var urls:String=""
        val context:Context=itemView.context

        @SuppressLint("SetTextI18n")
        fun bindItem(model: Article) {

            itemView.setOnClickListener(this)
            itemView.tv_title.text = model.title
            itemView.tv_description.text = model.description
            itemView.tv_source.text = model.source!!.name
            itemView.tv_author.text = model.author
            urls= model.url!!

            val date=model.publishedAt!!.substring(1,10)
            val time= model.publishedAt.substring(11,16)


         //   val format = SimpleDateFormat("MM/dd/yyyy")
          //  val date = format.parse(datas)
            itemView.tv_publishedAt.text = "$date - $time"



                    Picasso.get()
                    .load(model.urlToImage)
                    .resize(50, 50)
                    .centerCrop()
                    .into(itemView.imageView)
        }

        override fun onClick(v: View?) {
             context.startActivity(Intent(context, PostActivity::class.java).putExtra("url",urls))
             //Toast.makeText(context,""+adapterPosition,Toast.LENGTH_SHORT).show()
        }
    }
}

