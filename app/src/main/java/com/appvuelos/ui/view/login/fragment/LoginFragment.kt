package com.appvuelos.ui.view.login.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appvuelos.R
import com.appvuelos.data.Conexion
import com.appvuelos.databinding.FragmentLoginBinding
import com.appvuelos.ui.view.login.MainActivity
import com.appvuelos.ui.view.login.MainActivity.Companion.conn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()
    }

    private fun initComponents() {
        binding.btnLogin.setOnClickListener {
            val act = activity as MainActivity
            CoroutineScope(Dispatchers.IO).launch {
                conn = Conexion(act, binding.etEmail.text.toString(), binding.etPass.text.toString(), false, binding.etIp.text.toString())
                conn.run()

            }

        }

        binding.btnSignin.setOnClickListener {
            val activity = activity as MainActivity
            activity.navigateToSignIn()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
}