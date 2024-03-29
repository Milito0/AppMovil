package com.appvuelos.ui.view.login.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appvuelos.R
import com.appvuelos.data.Conexion
import com.appvuelos.databinding.FragmentSigninBinding
import com.appvuelos.ui.view.login.MainActivity
import com.appvuelos.ui.view.login.MainActivity.Companion.conn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SigninFragment : Fragment() {
    private var _binding: FragmentSigninBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() {
        binding.btnSignin.setOnClickListener {
            if (binding.etEmail.text.isNotEmpty() && checkPassword()) {

                CoroutineScope(Dispatchers.IO).launch {
                    val act = activity as MainActivity
                    conn = Conexion(act, binding.etEmail.text.toString(), binding.etPass.text.toString(), true, binding.etIp.text.toString())
                    conn.run()
                }
            }
        }
        binding.btnLogin.setOnClickListener {
            val act = activity as MainActivity
            act.navigateToLogIn()
        }
    }

    private fun checkPassword(): Boolean {
        if (binding.etPass.text.isEmpty()) return false
        if (binding.etPass.text.toString() != binding.etRepeatPass.text.toString()) {
            return false
        }
        return true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSigninBinding.inflate(inflater, container, false)
        return binding.root
    }

}