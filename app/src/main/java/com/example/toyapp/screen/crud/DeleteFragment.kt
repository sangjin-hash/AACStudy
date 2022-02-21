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
import com.example.toyapp.databinding.FragmentDeleteBinding
import com.example.toyapp.util.ApiState
import com.example.toyapp.viewModel.crud.DeleteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class DeleteFragment : Fragment() {

    private var _binding : FragmentDeleteBinding?= null
    private val binding
        get() = _binding!!

    private val viewModel : DeleteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_delete,
            container,
            false
        )

        binding.lifecycleOwner = viewLifecycleOwner
        binding.deleteViewModel = viewModel

        lifecycleScope.launchWhenCreated {
            viewModel._dataStateFlow.collect{ it ->
                when(it){
                    is ApiState.Failure -> {
                        binding.tvResult.text = it.msg.toString()
                        Log.e("DELETE", "ERROR")
                    }
                    is ApiState.Success_delete -> {
                        binding.tvResult.text = "Success"
                        Log.d("DELETE", "SUCCESS")
                    }
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView(){
        super.onDestroyView()
        _binding = null
    }
}