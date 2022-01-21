package com.example.toyapp.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.toyapp.R
import com.example.toyapp.viewModel.RegisterViewModel
import com.example.toyapp.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings

@AndroidEntryPoint
@WithFragmentBindings
class RegisterFragment : Fragment() {
    companion object{
        private const val TAG = "[Register]"
    }

    private var _binding: FragmentRegisterBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel : RegisterViewModel by viewModels()

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
                    binding.tvEmailValid.text = getString(R.string.Id_empty)
                    binding.tvPasswordValid.text = getString(R.string.Pass_empty)
                }
                2 -> {
                    binding.tvEmailValid.text = getString(R.string.Id_empty)
                    binding.tvPasswordValid.text = ""
                }
                3 -> {
                    binding.tvEmailValid.text = ""
                    binding.tvPasswordValid.text = getString(R.string.Pass_empty)
                }
                4 -> {
                    binding.tvEmailValid.text = getString(R.string.Overlap)
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