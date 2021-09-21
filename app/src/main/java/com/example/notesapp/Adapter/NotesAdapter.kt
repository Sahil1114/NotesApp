package com.example.notesapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.databinding.LayoutNotesBinding
import com.example.notesapp.entity.Notes
import com.example.notesapp.ui.fragments.HomeFragmentDirections

class NotesAdapter(val requireContext: Context,var NotesList: List<Notes>) : RecyclerView.Adapter<NotesAdapter.notesViewHolder>() {
    inner class notesViewHolder( public val binding : LayoutNotesBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return notesViewHolder(
                LayoutNotesBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
        val data=NotesList[position]
        holder.binding.tvNotesTitle.text=data.title.toString()
        holder.binding.tvNotesSubTitle.text=data.subtitle.toString()
        holder.binding.tvDate.text=data.date.toString()

        when(data.priority){
            "1" ->{
                holder.binding.vCircle.setBackgroundResource(R.drawable.yellow_circle)
            }
            "2" ->{
                holder.binding.vCircle.setBackgroundResource(R.drawable.orange_circle)
            }
            "3" ->{
                holder.binding.vCircle.setBackgroundResource(R.drawable.red_circle)
            }
        }
        holder.binding.root.setOnClickListener {
            val action =HomeFragmentDirections.actionHomeFragmentToEditFragment(data)
            Navigation.findNavController(it).navigate(action)

        }
    }

    override fun getItemCount(): Int = NotesList.size
    fun filtering(filteredList: ArrayList<Notes>) {
        NotesList=filteredList
        this.notifyDataSetChanged()
    }


}