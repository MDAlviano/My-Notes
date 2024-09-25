package com.alviano.app.mynotes.fragment.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.alviano.app.mynotes.R
import com.alviano.app.mynotes.model.Note
import com.alviano.app.mynotes.viewmodel.NoteViewModel

class addFragment : Fragment() {

    private lateinit var mNoteViewModel: NoteViewModel
    private lateinit var addTitle: EditText
    private lateinit var addNote: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        val fab = view.findViewById<View>(R.id.addBtn)

        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        addTitle = view.findViewById(R.id.addTitle)
        addNote = view.findViewById(R.id.addNote)

        fab.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val title = addTitle.text.toString()
        val description = addNote.text.toString()

        if (inputCheck(title, description)){
            // Create user model
            val note = Note(0, title, description)
            // Add data to database
            mNoteViewModel.addNote(note)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(title: String, note: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(note))
    }

}