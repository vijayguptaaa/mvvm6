package com.example.mvvm6

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), INoteRVAdapter {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_main)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NotesRVAdapter(this,this)
        binding.recyclerView.adapter = adapter
        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
//        viewModel = ViewModelProvider(this,
//                    ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel)
        viewModel.allNotes.observe(this, Observer {list ->
            list?.let {
                adapter.updateList(it)
            }

        })

    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this,"${note.text} Deleted",Toast.LENGTH_SHORT).show()
    }

    fun submitData() {
        val input = binding.input
        val noteText = input.text.toString()
        if(noteText.isNotEmpty()){
            viewModel.insertNote(Note(noteText))
            Toast.makeText(this,"${noteText} inserted",Toast.LENGTH_SHORT).show()

        }
    }


}