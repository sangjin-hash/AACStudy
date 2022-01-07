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
import com.example.toyapp.viewModel.LoginViewModel
import com.example.toyapp.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel = LoginViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )

        binding.lifecycleOwner = viewLifecycleOwner
        binding.loginViewModel = viewModel

        viewModel.isRegister.observe(viewLifecycleOwner, Observer<Boolean>{ register ->
            if(register) nav_register()
        })

        return binding.root
    }

    private fun nav_register(){
        val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
        findNavController().navigate(action)
        viewModel.registerComplete()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}