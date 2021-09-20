package com.example.notesapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesapp.Adapter.NotesAdapter
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentHomeBinding
import com.example.notesapp.model.NotesViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    val viewModel :NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{

        binding= FragmentHomeBinding.inflate(layoutInflater,container,false)

        viewModel.getNotes().observe(viewLifecycleOwner ,{NotesList ->
                 binding.rvHome.layoutManager=StaggeredGridLayoutManager( 2,LinearLayoutManager.VERTICAL)
                binding.rvHome.adapter=NotesAdapter(requireContext(),NotesList)
        }
        )

        binding.fbAdd.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createFragment)
        }

        binding.ivAllNotes.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner ,{NotesList ->
                binding.rvHome.layoutManager=StaggeredGridLayoutManager( 2,LinearLayoutManager.VERTICAL)
                binding.rvHome.adapter=NotesAdapter(requireContext(),NotesList)
            }
            )
        }
        binding.tvHigh.setOnClickListener {
            viewModel.getHighNotes().observe(viewLifecycleOwner ,{NotesList ->
                binding.rvHome.layoutManager=StaggeredGridLayoutManager( 2,LinearLayoutManager.VERTICAL)
                binding.rvHome.adapter=NotesAdapter(requireContext(),NotesList)
            }
            )
        }

        binding.tvMedium.setOnClickListener {
            viewModel.getMediumNotes().observe(viewLifecycleOwner ,{NotesList ->
                binding.rvHome.layoutManager=StaggeredGridLayoutManager( 2,LinearLayoutManager.VERTICAL)
                binding.rvHome.adapter=NotesAdapter(requireContext(),NotesList)
            }
            )
        }

        binding.tvLow.setOnClickListener {
            viewModel.getLowNotes().observe(viewLifecycleOwner ,{NotesList ->
                binding.rvHome.layoutManager=StaggeredGridLayoutManager( 2,LinearLayoutManager.VERTICAL)
                binding.rvHome.adapter=NotesAdapter(requireContext(),NotesList)
            }
            )

        }
        return binding.root
    }

}