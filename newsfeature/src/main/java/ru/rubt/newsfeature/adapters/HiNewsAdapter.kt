package ru.rubt.newsfeature.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.rubt.data.db.entities.HiNewsEntity
import ru.rubt.newsfeature.R
import ru.rubt.newsfeature.databinding.LayoutHiNewsListItemBinding


class HiNewsAdapter(
    private val hiNews: List<HiNewsEntity>
): RecyclerView.Adapter<HiNewsAdapter.HiNewsHolder>() {

    override fun getItemCount(): Int = hiNews.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HiNewsHolder {
        val layoutHiNewsListItemBinding: LayoutHiNewsListItemBinding =
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.layout_hi_news_list_item,
                        parent, false
                )

        return HiNewsHolder(layoutHiNewsListItemBinding)
    }

    override fun onBindViewHolder(holder: HiNewsHolder, position: Int) {
        val newsEntity = hiNews[position]
        holder.bind(newsEntity)
    }

    class HiNewsHolder(
        val layoutHiNewsListItemBinding: LayoutHiNewsListItemBinding
    ): RecyclerView.ViewHolder(layoutHiNewsListItemBinding.root) {

        fun bind(hiNewsEntity: HiNewsEntity) {

            layoutHiNewsListItemBinding.tvNewsTitle.text = hiNewsEntity.title
            layoutHiNewsListItemBinding.tvNewsDesc.text = hiNewsEntity.desc
            layoutHiNewsListItemBinding.tvNewsAuthor.text = hiNewsEntity.author
            layoutHiNewsListItemBinding.tvNewsDate.text = hiNewsEntity.date

            Picasso.get()
                .load(hiNewsEntity.urlToImage)
                .placeholder(R.drawable.placeholder)
                .into(layoutHiNewsListItemBinding.ivNewsImage)
        }
    }

}