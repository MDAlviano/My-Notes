package com.alviano.app.mynotes.fragment.update

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.alviano.app.mynotes.R
import com.alviano.app.mynotes.databinding.FragmentUpdateBinding
import com.alviano.app.mynotes.viewmodel.NoteViewModel
import com.alviano.app.mynotes.model.Note

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    lateinit var binding: FragmentUpdateBinding
    private lateinit var view: View
    private lateinit var updateTitle: EditText
    private lateinit var updateNote: EditText
    private lateinit var updateBtn: Button

    private lateinit var mNoteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_update, container, false)

        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        updateTitle = view.findViewById<EditText>(R.id.updateTitle_et)
        updateNote = view.findViewById<EditText>(R.id.updateNote_et)
        updateBtn = view.findViewById<Button>(R.id.update_btn)

        updateTitle.setText(args.currentNote.title)
        updateNote.setText(args.currentNote.note)

        updateBtn.setOnClickListener {
            updateItem()
        }

        return view
    }

    private fun updateItem() {
        val title = updateTitle.text.toString()
        val note = updateNote.text.toString()

        if (inputCheck(title, note)) {
            // create note object
            val updatedNote = Note(args.currentNote.id, title, note)
            // update current note
            mNoteViewModel.updateNote(updatedNote)
            Toast.makeText(requireContext(), "Berhasil mengubah data!", Toast.LENGTH_LONG).show()
            // navigate back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Mohon isi kolom yg tersedia!", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(title: String, note: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(note))
    }


}