package com.example.toyapp.screen

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.toyapp.App
import com.example.toyapp.MainActivity
import com.example.toyapp.R
import com.example.toyapp.viewModel.LoginViewModel
import com.example.toyapp.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private val TAG:String = "[Login]"

    private var _binding: FragmentLoginBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel = LoginViewModel()

    lateinit var mainActivity:MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

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
                    // Todo : context parameter만 해결하면 된다.
                    Toast.makeText(mainActivity, "아이디 & 비밀번호가 비어있습니다",Toast.LENGTH_SHORT).show()
                    Log.e(TAG, "아이디 & 비밀번호가 비어있다");
                }
                2 -> {
                    Toast.makeText(mainActivity, "아이디가 비어있습니다",Toast.LENGTH_SHORT).show()
                    Log.e(TAG, "아이디가 비어있다");
                }
                3 -> {
                    Toast.makeText(mainActivity, "비밀번호가 비어있습니다",Toast.LENGTH_SHORT).show()
                    Log.e(TAG, "비밀번호가 비어있다");
                }
                4 -> {
                    Toast.makeText(mainActivity, "존재하지 않는 아이디거나 비밀번호가 옳지 않습니다.", Toast.LENGTH_SHORT).show()
                    Log.e(TAG, "존재하지 않는 아이디거나 비밀번호가 옳지 않습니다.")
                }
                5 -> {
                    Toast.makeText(mainActivity, "로그인 성공", Toast.LENGTH_SHORT).show()
                    Log.e(TAG, "로그인 성공")

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