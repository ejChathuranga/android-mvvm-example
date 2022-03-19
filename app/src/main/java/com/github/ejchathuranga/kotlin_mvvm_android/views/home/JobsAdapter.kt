package com.github.ejchathuranga.kotlin_mvvm_android.views.home

import android.annotation.SuppressLint
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
        private val TAG = "JobsAdapter"
        private val curr: String = "€ "
        private val dist: String = " KM"

        @SuppressLint("SetTextI18n")
        fun bind(data: TemperJob) {
            binding.tvClientName.text = data.job.project.client.name

            if (data.earnings_per_hour.currency.equals("EUR")) {
                binding.tvHourlyRate.text = curr + data.earnings_per_hour.amount
            } else {
                TODO()
            }

            binding.tvCategory.text = data.job.category.name
            binding.tvDistance.text = data.distance.toInt().toString() + dist

            /*
            * Date time conversion need to be done
            * */

            val url = data.job.project.client.links.hero_image;

            Picasso.get()
                .load(url)
                .fit()
                .centerCrop()
                .into(binding.imageView)
        }
    }
}