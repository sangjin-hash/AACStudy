package com.example.toyapp.screen.crud

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.toyapp.R
import com.example.toyapp.databinding.FragmentInsertBinding
import com.example.toyapp.util.ApiState
import com.example.toyapp.viewModel.crud.InsertViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class InsertFragment : Fragment() {

    private var _binding: FragmentInsertBinding?= null
    private val binding
        get() = _binding!!

    private val viewModel : InsertViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_insert,
            container,
            false
        )

        binding.lifecycleOwner = viewLifecycleOwner
        binding.insertViewModel = viewModel


        lifecycleScope.launchWhenCreated {
            viewModel._dataStateFlow.collect{ it ->
                when(it){
                    is ApiState.Failure -> {
                        binding.tvResult.text = it.msg.toString()
                        Log.e("INSERT", "${it.msg}")
                    }
                    is ApiState.Success_insert -> {
                        Log.d("INSERT", "SUCCESS")
                        binding.tvResult.text = "Success = " + it.body.success + '\n' + "message" + it.body.message
                    }
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}