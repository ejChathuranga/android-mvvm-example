package com.github.ejchathuranga.kotlin_mvvm_android.views.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.ejchathuranga.kotlin_mvvm_android.R
import com.github.ejchathuranga.kotlin_mvvm_android.models.TemperJob

class JobsAdapter: RecyclerView.Adapter<JobsAdapter.JobViewHolder>() {

    private var dataList: ArrayList<TemperJob> = ArrayList<TemperJob>()

    fun setDataList(dataList: ArrayList<TemperJob>){
        val oldSize = this.dataList.size
        this.dataList = dataList;
        notifyItemRangeChanged(oldSize, dataList.size)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.data_list_item, parent, false)
        return JobViewHolder(view)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return this.dataList.size
    }

    class JobViewHolder constructor(private var view: View): RecyclerView.ViewHolder(view) {
        val image = view.findViewById<ImageView>(R.id.imageView)
        val name = view.findViewById<TextView>(R.id.tvName)
        val desc = view.findViewById<TextView>(R.id.tvDesc)

        fun bind(data: TemperJob) {
            name.text = data.job.project.client.name
            desc.text = data.earnings_per_hour.currency

            val url = data.job.project.client.links.hero_image;
            Glide.with(view)
                .load(url)
                .centerCrop()
                .into(image)

        }
    }
}