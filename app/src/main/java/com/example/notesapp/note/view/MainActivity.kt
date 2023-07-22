package com.example.notesapp.note.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.note.viewmodel.NoteViewModel
import com.example.notesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel: NoteViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        setupRecyclerView()
        subscribeUI()
    }


    private fun subscribeUI() {
        viewModel.getListNote()
        viewModel.listNote.observe(this@MainActivity) {
            adapter.setNotes(it) // Cập nhật dữ liệu cho adapter
            if (it.isNotEmpty()) {
                binding.recyclerView.smoothScrollToPosition(it.size - 1)
            }
        }
    }

    private fun setupViews() {
        binding.buttonAdd.setOnClickListener {
            startActivity(Intent(this, AddNoteActivity::class.java))

        }

    }

    private fun setupRecyclerView() {
        adapter = NoteAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        adapter.setOnItemClickListener { note ->
            showNoteDetail(note.id,note.title, note.content)
            Log.d("hoangtv", "click: ")
        }
    }

    private fun showNoteDetail( id : Long,title: String, content: String) {
        // Tạo intent để mở NoteDetailActivity
        val intent = Intent(this, NoteDetailActivity::class.java).apply {
            putExtra(NoteDetailActivity.EXTRA_NOTE_ID, id)
            putExtra(NoteDetailActivity.EXTRA_NOTE_TITLE, title)
            putExtra(NoteDetailActivity.EXTRA_NOTE_CONTENT, content)

        }
        startActivity(intent)
    }


    override fun onResume() {
        super.onResume()
        viewModel.getListNote()
        Log.d("hoang", "onResume: ")
    }
}

