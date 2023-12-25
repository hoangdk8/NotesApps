package com.example.notesapp.note.view

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.databinding.ActivityEditNoteBinding
import com.example.notesapp.databinding.CustomDialogLayoutBinding
import com.example.notesapp.databinding.DialogEditBinding
import com.example.notesapp.note.data.model.dataroom.Note
import com.example.notesapp.note.viewmodel.NoteViewModel

class EditNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditNoteBinding
    private lateinit var dialogBinding: DialogEditBinding
    private val viewModel: NoteViewModel by viewModels()
    private lateinit var adapter: NoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
        subscribeUI()
    }

    private fun setupViews() {
        binding.buttonBack.setOnClickListener {
            finish()
        }
        binding.buttonSave.setOnClickListener {
            showEditConfirmationDialog()
        }

    }

    private fun showEditConfirmationDialog() {
        // Inflate layout custom và ánh xạ ViewBinding
        dialogBinding = DialogEditBinding.inflate(layoutInflater)

        // Tạo AlertDialog
        val alertDialog = AlertDialog.Builder(this).apply {
            setView(dialogBinding.root)
        }.create()

        dialogBinding.buttonComfirmYes.setOnClickListener {
            updateNote()
            alertDialog.dismiss()
            finish()
            Toast.makeText(this, "Sửa thành công", Toast.LENGTH_LONG).show()
        }
        dialogBinding.buttonComfirmNo.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.show()

    }

    private fun subscribeUI() {
        val title = intent.getStringExtra(NoteDetailActivity.EXTRA_NOTE_TITLE)
        val content = intent.getStringExtra(NoteDetailActivity.EXTRA_NOTE_CONTENT)

        binding.editTextTitle.setText(title)
        binding.editTextContent.setText(content)
    }

    private fun updateNote() {
        val id = intent.getLongExtra(EXTRA_NOTE_ID, 0)
        val title = binding.editTextTitle.text.toString()
        val content = binding.editTextContent.text.toString()
        val updatedNote = Note(id, title, content)
        viewModel.updateNote(updatedNote)
    }

    companion object {
        const val EXTRA_NOTE_ID = "extra_note_id"
        const val EXTRA_NOTE_TITLE = "extra_note_title"
        const val EXTRA_NOTE_CONTENT = "extra_note_content"

    }
}