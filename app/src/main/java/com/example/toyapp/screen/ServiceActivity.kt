package com.example.toyapp.screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.toyapp.R
import com.example.toyapp.adapter.DataAdapter
import com.example.toyapp.model.ApiUser
import com.example.toyapp.util.Status
import com.example.toyapp.viewModel.crud.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_service.*

@AndroidEntryPoint
class ServiceActivity : AppCompatActivity() {

    private lateinit var adapter: DataAdapter
    private val viewModel : HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        recyclerview.layoutManager = LinearLayoutManager(this)
        adapter =
            DataAdapter(
                arrayListOf()
            )
        recyclerview.addItemDecoration(
            DividerItemDecoration(
                recyclerview.context,
                (recyclerview.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerview.adapter = adapter
    }

    private fun setupObserver() {
        viewModel.getUsers().observe(this, Observer{
            when(it.status){
                Status.SUCCESS -> {
                    progress_bar.visibility = View.GONE
                    it.data?.let{users -> renderList(users)}
                    recyclerview.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progress_bar.visibility = View.VISIBLE
                    recyclerview.visibility = View.GONE
                }
                Status.ERROR -> {
                    progress_bar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun renderList(users: List<ApiUser>){
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

}