package com.example.notesapp.note.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.notesapp.R
import com.example.notesapp.databinding.ActivityAddNoteBinding
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.general.event.DataChangeEvent
import com.example.notesapp.note.data.model.dataroom.Note
import com.example.notesapp.note.viewmodel.NoteViewModel
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class AddNoteActivity : AppCompatActivity() {
    private val viewModel: NoteViewModel by viewModels()
    private lateinit var binding: ActivityAddNoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
    }

    private fun setupViews() {
        binding.buttonBack.setOnClickListener {
            finish()
        }
        binding.buttonSave.setOnClickListener {
            if(binding.editTextTitle.text.isBlank()){
                Toast.makeText(this, "Chưa điền thông tin", Toast.LENGTH_SHORT).show()
            }else{
                addNote()
                Toast.makeText(this, "Lưu thành công", Toast.LENGTH_SHORT).show()
                finish()
            }


        }
    }

    private fun addNote() {
        val note = Note(
            0,
            binding.editTextTitle.text.toString(),
            binding.editTextContent.text.toString()
        )
        viewModel.addNote(note)

    }


}
