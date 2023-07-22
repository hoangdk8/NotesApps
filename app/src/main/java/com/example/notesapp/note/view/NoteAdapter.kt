package com.example.notesapp.note.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.databinding.ItemViewsBinding
import com.example.notesapp.note.data.model.dataroom.Note

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private var notes: List<Note> = listOf()
    private var onItemClickListener: ((Note) -> Unit)? = null

    fun setOnItemClickListener(listener: (Note) -> Unit) {
        onItemClickListener = listener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNotes(newNotes: List<Note>) {
        notes = newNotes
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemViewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.bind(note)
        val colorList = listOf("#FD99FF", "#FF9E9E", "#91F48F", "#FFF599", "#9EFFFF", "#B69CFF")
        val color = colorList[position % colorList.size]
        holder.itemView.setBackgroundColor(Color.parseColor(color))

    }


    override fun getItemCount(): Int {
        return notes.size
    }

    inner class NoteViewHolder(private val binding: ItemViewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.textViewTitle.text = note.title

            binding.root.setOnClickListener {
                onItemClickListener?.invoke(note)
            }
        }

    }
}
