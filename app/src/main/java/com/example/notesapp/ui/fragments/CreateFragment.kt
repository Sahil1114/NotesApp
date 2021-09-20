package com.example.notesapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentCreateBinding
import com.example.notesapp.entity.Notes
import com.example.notesapp.model.NotesViewModel
import java.text.SimpleDateFormat
import java.util.*

class CreateFragment : Fragment() {

    private lateinit var binding: FragmentCreateBinding

    private var priority = "1"

    val viewModel :NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{

        binding= FragmentCreateBinding.inflate(layoutInflater,container,false)
        binding.iv3.setImageResource(R.drawable.ic_done_24_foreground)
        binding.iv1.setOnClickListener {
            priority="3"
            binding.iv1.setImageResource(R.drawable.ic_done_24_foreground)
            binding.iv2.setImageResource(0)
            binding.iv3.setImageResource(0)
        }
        binding.iv2.setOnClickListener {
            priority="2"
            binding.iv2.setImageResource(R.drawable.ic_done_24_foreground)
            binding.iv1.setImageResource(0)
            binding.iv3.setImageResource(0)
        }
        binding.iv3.setOnClickListener {
            priority="1"
            binding.iv3.setImageResource(R.drawable.ic_done_24_foreground)
            binding.iv2.setImageResource(0)
            binding.iv1.setImageResource(0)
        }

        binding.fbDone.setOnClickListener {
            createNotes(it)
        }

        return binding.root
    }

    private fun createNotes(it: View?) {
        val title = binding.etTitle.text.toString()
        val subtitle = binding.etSubTitle.text.toString()
        val notes = binding.etNotes.text.toString()

        val sdf = SimpleDateFormat("dd-MMM-yyy")
        val currentDate = sdf.format(Date()).toString()

        val data =Notes(
            id = null,
            title = title,
            subtitle=subtitle,
            notes=notes,
            date = currentDate,
            priority
        )
        viewModel.addNotes(data)
        Toast.makeText(requireContext(),"Notes Created Successfully",Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it !!).navigate(R.id.action_createFragment_to_homeFragment)


    }
}