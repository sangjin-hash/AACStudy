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
import com.example.toyapp.databinding.FragmentUpdateBinding
import com.example.toyapp.util.ApiState
import com.example.toyapp.viewModel.crud.UpdateViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class UpdateFragment : Fragment() {

    private var _binding : FragmentUpdateBinding?= null
    private val binding
        get() = _binding!!

    private val viewModel : UpdateViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_update,
            container,
            false
        )

        binding.lifecycleOwner = viewLifecycleOwner
        binding.updateViewModel = viewModel

        lifecycleScope.launchWhenCreated {
            viewModel._dataStateFlow.collect{   it ->
                when(it){
                    is ApiState.Failure -> {
                        binding.tvResult.text = it.msg.toString()
                        Log.e("UPDATE", "ERROR")
                    }
                    is ApiState.Success_String -> {
                        binding.tvResult.text = "Success"
                        Log.d("UPDATE", "SUCCESS")
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