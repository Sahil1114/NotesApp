package com.example.notesapp.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesapp.Adapter.NotesAdapter
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentHomeBinding
import com.example.notesapp.entity.Notes
import com.example.notesapp.model.NotesViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    var searchNewsList = arrayListOf<Notes>()

    lateinit var adapter: NotesAdapter
    private val viewModel :NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{

        binding= FragmentHomeBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)

        viewModel.getNotes().observe(viewLifecycleOwner ,{NotesList ->
            searchNewsList=NotesList as ArrayList<Notes>
            binding.rvHome.layoutManager=StaggeredGridLayoutManager( 2,LinearLayoutManager.VERTICAL)
            adapter=NotesAdapter(requireContext(),NotesList)
            binding.rvHome.adapter=adapter
        }
        )

        binding.fbAdd.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createFragment)
        }

        binding.ivAllNotes.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner ,{NotesList ->
                searchNewsList=NotesList as ArrayList<Notes>
                binding.rvHome.layoutManager=StaggeredGridLayoutManager( 2,LinearLayoutManager.VERTICAL)
                adapter=NotesAdapter(requireContext(),NotesList)
                binding.rvHome.adapter=adapter
            }
            )
        }
        binding.tvHigh.setOnClickListener {
            viewModel.getHighNotes().observe(viewLifecycleOwner ,{NotesList ->
                searchNewsList=NotesList as ArrayList<Notes>
                binding.rvHome.layoutManager=StaggeredGridLayoutManager( 2,LinearLayoutManager.VERTICAL)
                binding.rvHome.adapter=NotesAdapter(requireContext(),NotesList)
            }
            )
        }

        binding.tvMedium.setOnClickListener {
            viewModel.getMediumNotes().observe(viewLifecycleOwner ,{NotesList ->
                searchNewsList=NotesList as ArrayList<Notes>
                binding.rvHome.layoutManager=StaggeredGridLayoutManager( 2,LinearLayoutManager.VERTICAL)
                adapter=NotesAdapter(requireContext(),NotesList)
                binding.rvHome.adapter=adapter
            }
            )
        }

        binding.tvLow.setOnClickListener {
            viewModel.getLowNotes().observe(viewLifecycleOwner ,{NotesList ->
                searchNewsList=NotesList as ArrayList<Notes>
                binding.rvHome.layoutManager=StaggeredGridLayoutManager( 2,LinearLayoutManager.VERTICAL)
                adapter=NotesAdapter(requireContext(),NotesList)
                binding.rvHome.adapter=adapter
            }
            )

        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.search_menu,menu)

        val item =menu.findItem(R.id.search_menu)
        val searchView =item?.actionView as SearchView
        searchView.queryHint="Enter Your Text Here ..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                onfiltering(p0)
                return true
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    fun onfiltering(p0: String?) {
         var filteredList= arrayListOf<Notes>()
        for(i in searchNewsList){
            if (i.title.contains("$p0")||i.subtitle.contains("$p0")){
                filteredList.add(i)
            }
        }
        adapter.filtering(filteredList)
    }

}