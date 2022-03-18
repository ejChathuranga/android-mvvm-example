package com.github.ejchathuranga.kotlin_mvvm_android.views.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.ejchathuranga.kotlin_mvvm_android.databinding.JobsViewHoldeBinding
import com.github.ejchathuranga.kotlin_mvvm_android.models.TemperJob
import com.squareup.picasso.Picasso

class JobsAdapter : RecyclerView.Adapter<JobsAdapter.JobViewHolder>() {

    private var dataList: ArrayList<TemperJob> = ArrayList<TemperJob>()

    fun setDataList(dataList: ArrayList<TemperJob>) {
        val oldSize = this.dataList.size
        this.dataList = dataList;
        notifyItemRangeChanged(oldSize, dataList.size)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = JobsViewHoldeBinding.inflate(inflater, parent, false)
        return JobViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return this.dataList.size
    }

    class JobViewHolder constructor(var binding: JobsViewHoldeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TemperJob) {
            binding.tvName.text = data.job.project.client.name
            binding.tvDesc.text = data.earnings_per_hour.currency

            val url = data.job.project.client.links.hero_image;

            Picasso.get()
                .load(url)
                .fit()
                .centerCrop()
                .into(binding.imageView)
        }
    }
}