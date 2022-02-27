package com.example.toyapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.toyapp.R
import com.example.toyapp.model.ApiUser
import kotlinx.android.synthetic.main.each_row.view.*

class DataAdapter(private var users: ArrayList<ApiUser>)
    : RecyclerView.Adapter<DataAdapter.DataViewHoler>() {

    class DataViewHoler(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: ApiUser){
            itemView.textViewUserId.text = "Id = " + user.id.toString()
            itemView.textViewUserName.text = "Name = "+ user.name
            itemView.textViewUserHobby.text = "Hobby = " + user.hobby
            itemView.textViewUserPhone.text = "Phone = " + user.phone
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHoler(
            LayoutInflater.from(parent.context).inflate(
                R.layout.each_row, parent,
                false
            )
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHoler, position: Int) {
        holder.bind(users[position])
    }

    fun addData(list: List<ApiUser>){
        users.addAll(list)
    }
}