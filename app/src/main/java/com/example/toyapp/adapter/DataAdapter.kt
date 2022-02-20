package com.example.toyapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.toyapp.databinding.EachRowBinding
import com.example.toyapp.model.Data

class DataAdapter(private var dataList: List<Data>) : RecyclerView.Adapter<DataAdapter.DataViewHoler>() {
    class DataViewHoler(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    private lateinit var binding: EachRowBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHoler {
        binding = EachRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHoler(binding.root)
    }

    override fun onBindViewHolder(holder: DataViewHoler, position: Int) {
        binding.tasks.text = "id = "+dataList[position].id.toString() + "\n"+
                "name = " + dataList[position].name + "\n" +
                "hobby = " + dataList[position].hobby + "\n" +
                "phone = " + dataList[position].phone
    }

    override fun getItemCount(): Int = dataList.size

    fun setData(dataList : List<Data>)
    {
        this.dataList = dataList
        notifyDataSetChanged()
    }
}