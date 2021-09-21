package com.example.notesapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentEditBinding
import com.example.notesapp.entity.Notes
import com.example.notesapp.model.NotesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.SimpleDateFormat
import java.util.*


class EditFragment : Fragment() {

    private lateinit var binding: FragmentEditBinding

    val oldNotes by navArgs<EditFragmentArgs>()

    private var priority = "1"

    val viewModel : NotesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentEditBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)

        binding.etTitle.setText(oldNotes.dataTransfer.title.toString())
        binding.etSubTitle.setText(oldNotes.dataTransfer.subtitle.toString())
        binding.etNotes.setText(oldNotes.dataTransfer.notes.toString())

        when(oldNotes.dataTransfer.priority){
            "3" ->{
                priority="3"
                binding.iv1.setImageResource(R.drawable.ic_done_24_foreground)
                binding.iv2.setImageResource(0)
                binding.iv3.setImageResource(0)
            }
            "2" ->{
                priority="2"
                binding.iv2.setImageResource(R.drawable.ic_done_24_foreground)
                binding.iv1.setImageResource(0)
                binding.iv3.setImageResource(0)
            }
            "1" ->{
                priority="1"
                binding.iv3.setImageResource(R.drawable.ic_done_24_foreground)
                binding.iv2.setImageResource(0)
                binding.iv1.setImageResource(0)
            }
        }

        binding.fbEditdone.setOnClickListener { 
            updateNotes(it)
        }
        return binding.root
    }

    private fun updateNotes(it: View?) {
        val title = binding.etTitle.text.toString()
        val subtitle = binding.etSubTitle.text.toString()
        val notes = binding.etNotes.text.toString()

        val sdf = SimpleDateFormat("dd-MMM-yyy")
        val currentDate = sdf.format(Date()).toString()

        val data = Notes(
            oldNotes.dataTransfer.id,
            title = title,
            subtitle=subtitle,
            notes=notes,
            date = currentDate,
            priority
        )
        viewModel.updateNotes(data)
        Toast.makeText(requireContext(),"Notes Updated Successfully", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it !!).navigate(R.id.action_editFragment_to_homeFragment)


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.delete_menu){
            val bottomSheet :BottomSheetDialog=BottomSheetDialog(requireContext(),R.style.AppBottomSheetDialogTheme)
            bottomSheet.setContentView(R.layout.dialog_delete)
            val textViewYes=bottomSheet.findViewById<TextView>(R.id.tvYes)
            val textViewNo=bottomSheet.findViewById<TextView>(R.id.tvNo)

            textViewYes?.setOnClickListener {
                viewModel.deleteNotes(oldNotes.dataTransfer.id !!)
                bottomSheet.dismiss()
                Navigation.findNavController(it !!).navigate(R.id.action_editFragment_to_homeFragment)
            }
            textViewNo?.setOnClickListener {
                bottomSheet.dismiss()
            }
            bottomSheet.show()
        }

        return super.onOptionsItemSelected(item)
    }

}