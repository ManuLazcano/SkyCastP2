package com.example.skycastp2.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.skycastp2.databinding.FragmentContactFormBinding

class ContactFormFragment : Fragment() {

    private var _binding: FragmentContactFormBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSubmit.setOnClickListener {
            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val message = binding.etMessage.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && message.isNotEmpty()) {
                Toast.makeText(requireContext(), "Mensaje enviado", Toast.LENGTH_SHORT).show()
                clearForm()
            } else {
                Toast.makeText(requireContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnBack.setOnClickListener {
            closeFragment()
        }

    }

    private fun clearForm() {
        binding.etName.text.clear()
        binding.etEmail.text.clear()
        binding.etMessage.text.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun closeFragment() {
        parentFragmentManager.popBackStack() // Elimina el fragment actual de la pila de retroceso
    }
}