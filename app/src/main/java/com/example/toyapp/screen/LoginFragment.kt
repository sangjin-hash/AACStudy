package com.example.toyapp.screen


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.toyapp.R
import com.example.toyapp.viewModel.LoginViewModel
import com.example.toyapp.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings

@AndroidEntryPoint
@WithFragmentBindings
class LoginFragment : Fragment() {
    companion object{
        private const val TAG = "[Login]"
    }

    private var _binding: FragmentLoginBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel : LoginViewModel by viewModels()

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

        viewModel.loginState.observe(viewLifecycleOwner, Observer<Int>{ tag ->
            when(tag){
                1 -> {
                    Toast.makeText(activity, R.string.Id_Pass_empty,Toast.LENGTH_SHORT).show()
                }
                2 -> {
                    Toast.makeText(activity, R.string.Id_empty,Toast.LENGTH_SHORT).show()
                }
                3 -> {
                    Toast.makeText(activity, R.string.Pass_empty,Toast.LENGTH_SHORT).show()
                }
                4 -> {
                    Toast.makeText(activity, R.string.Pass_Error, Toast.LENGTH_SHORT).show()
                }
                5 -> {
                    Toast.makeText(activity, R.string.Success, Toast.LENGTH_SHORT).show()

                    val action = LoginFragmentDirections.actionLoginDestinationToServiceDestination()
                    findNavController().navigate(action)
                }
            }
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