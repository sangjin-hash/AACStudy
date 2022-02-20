package com.example.toyapp.screen.crud

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.toyapp.adapter.DataAdapter
import com.example.toyapp.databinding.FragmentHomeBinding
import com.example.toyapp.util.ApiState
import com.example.toyapp.viewModel.crud.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var dataAdapter : DataAdapter
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)
        initRecyclerView()

        homeViewModel.getData()

        lifecycleScope.launchWhenCreated {
            homeViewModel._dataStateFlow.collect{   it ->
                when(it){
                    is ApiState.Loading -> {
                        binding.recyclerview.isVisible = false
                        binding.progressBar.isVisible = true
                    }
                    is ApiState.Failure -> {
                        binding.recyclerview.isVisible = false
                        binding.progressBar.isVisible = false
                        Log.d("HOME", "onCreateView: ${it.msg}")
                    }
                    is ApiState.Success -> {
                        binding.recyclerview.isVisible = true
                        binding.progressBar.isVisible = false
                        dataAdapter.setData(it.body)
                    }
                    is ApiState.Empty -> {

                    }
                }
            }
        }

        return binding.root
    }

    private fun initRecyclerView() {
        dataAdapter = DataAdapter(ArrayList())
        binding.recyclerview.apply{
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@HomeFragment.context)
            adapter = dataAdapter
        }
    }
}