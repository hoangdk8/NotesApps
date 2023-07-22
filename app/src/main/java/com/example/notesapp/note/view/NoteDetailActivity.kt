package com.example.notesapp.note.view

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.notesapp.R
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.databinding.ActivityNoteDetailBinding
import com.example.notesapp.databinding.CustomDialogLayoutBinding
import com.example.notesapp.note.viewmodel.NoteViewModel

class NoteDetailActivity : AppCompatActivity() {
    private val viewModel: NoteViewModel by viewModels()
    private lateinit var binding: ActivityNoteDetailBinding
    private lateinit var dialogBinding: CustomDialogLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
        subscribeUI()
    }

    private fun subscribeUI() {
        val title = intent.getStringExtra(EXTRA_NOTE_TITLE)
        val content = intent.getStringExtra(EXTRA_NOTE_CONTENT)

        binding.textViewDetailTitle.text = title
        binding.textViewDetailContent.text = content
    }

    private fun setupViews() {
        binding.buttonBack.setOnClickListener {
            finish()
        }
        binding.buttonDelete.setOnClickListener {
            showDeleteConfirmationDialog()
        }
        binding.buttonEdit.setOnClickListener {
            val id = intent.getLongExtra(EXTRA_NOTE_ID, 0)
            val title = binding.textViewDetailTitle.text.toString()
            val content = binding.textViewDetailContent.text.toString()
            updateNote(id, title, content)
            finish()
        }
    }

    private fun updateNote(id : Long,title: String, content: String) {
        val intent = Intent(this, EditNoteActivity::class.java).apply {
            putExtra(EditNoteActivity.EXTRA_NOTE_ID, id)
            putExtra(EditNoteActivity.EXTRA_NOTE_TITLE, title)
            putExtra(EditNoteActivity.EXTRA_NOTE_CONTENT, content)

        }
        startActivity(intent)
    }

    private fun showDeleteConfirmationDialog() {
        // Inflate layout custom và ánh xạ ViewBinding
        dialogBinding = CustomDialogLayoutBinding.inflate(layoutInflater)

        // Tạo AlertDialog
        val alertDialog = AlertDialog.Builder(this).apply {
            setView(dialogBinding.root)
        }.create()

        // Xử lý sự kiện khi bấm nút "Xóa"
        dialogBinding.buttonComfirmDelete.setOnClickListener {
            // Thực hiện hành động xóa ghi chú
            deleteNoteById()
            alertDialog.dismiss()
            finish()
        }

        // Xử lý sự kiện khi bấm nút "Hủy"
        dialogBinding.buttonComfirmCancel.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.show()


    }

    private fun deleteNoteById() {
        val id = intent.getLongExtra(EXTRA_NOTE_ID,0)
        viewModel.deleteNote(id)
    }

    companion object {
        const val EXTRA_NOTE_ID = "extra_note_id"
        const val EXTRA_NOTE_TITLE = "extra_note_title"
        const val EXTRA_NOTE_CONTENT = "extra_note_content"

    }
    override fun onResume() {
        super.onResume()
        val title = intent.getStringExtra(EXTRA_NOTE_TITLE)
        val content = intent.getStringExtra(EXTRA_NOTE_CONTENT)

        binding.textViewDetailTitle.text = title
        binding.textViewDetailContent.text = content
        viewModel.getListNote()
        Log.d("hoangtv", "onResumeấdasdasd: $title")
    }
}