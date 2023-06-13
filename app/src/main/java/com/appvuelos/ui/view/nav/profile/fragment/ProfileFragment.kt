package com.appvuelos.ui.view.nav.profile.fragment

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.appvuelos.R
import com.appvuelos.databinding.FragmentProfileBinding
import com.appvuelos.ui.view.login.MainActivity.Companion.conn
import com.appvuelos.ui.view.login.MainActivity.Companion.correoUser
import com.appvuelos.ui.view.nav.NavActivity

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    var nombre: String? = null
    var apellidos: String? = null
    var telefono: String? = null
    var direccion: String? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            nombre = it.getString("NOMBRE")
            apellidos = it.getString("APELLIDOS")
            telefono = it.getString("TELEFONO")
            direccion = it.getString("DIRECCION")
        }


        binding.pbProfile.isVisible = true
        initUI()
        postValues()

    }

    private fun initUI() {
        binding.btnLogout.setOnClickListener {
            conn.peticionDesconexion()
            val act = activity as NavActivity
            act.navigateToLogin()
        }
        binding.btnActualizar.setOnClickListener {
            var nomb = binding.tvNombre.text.toString()
            if (nomb.isBlank()){
                nomb = "Sin resolver"
            }
            var apell = binding.tvApellidos.text.toString()
            if (apell.isBlank()){
                apell = "Sin resolver"
            }
            var tel = binding.tvTelefono.text.toString()
            if (tel.isBlank()){
                tel = "Sin resolver"
            }
            var dir = binding.tvDireccion.text.toString()
            if (dir.isBlank()){
                dir = "Sin resolver"
            }
            conn.actualizaDatos(nomb, apell, tel, dir)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    fun postValues() {
        binding.tvCorreo.text = correoUser
        binding.tvNombre.setText(nombre)
        binding.tvApellidos.setText(apellidos)
        binding.tvTelefono.setText(telefono)
        binding.tvDireccion.setText(direccion)
        binding.pbProfile.isVisible = false
    }

}