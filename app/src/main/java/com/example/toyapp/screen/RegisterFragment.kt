package com.example.toyapp.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.toyapp.R
import com.example.toyapp.viewModel.RegisterViewModel
import com.example.toyapp.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel = RegisterViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_register,
            container,
            false
        )

        binding.lifecycleOwner = viewLifecycleOwner
        binding.registerViewModel = viewModel

        viewModel.registerState.observe(viewLifecycleOwner, Observer<Int>{  tag ->
            when(tag){
                1 -> {
                    binding.tvEmailValid.text = "아이디가 비어 있습니다"
                    binding.tvPasswordValid.text = "비밀번호가 비어 있습니다"
                }
                2 -> {
                    binding.tvEmailValid.text = "아이디가 비어 있습니다"
                    binding.tvPasswordValid.text = ""
                }
                3 -> {
                    binding.tvEmailValid.text = ""
                    binding.tvPasswordValid.text = "비밀번호가 비어 있습니다"
                }
                4 -> {
                    binding.tvEmailValid.text = "중복된 아이디입니다."
                    binding.tvPasswordValid.text = ""
                }
                5 -> {
                    binding.tvEmailValid.text = ""
                    binding.tvPasswordValid.text = ""

                    val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                    findNavController().navigate(action)
                }
            }

        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}