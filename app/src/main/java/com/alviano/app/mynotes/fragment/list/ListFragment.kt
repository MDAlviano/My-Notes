package com.alviano.app.mynotes.fragment.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alviano.app.mynotes.R
import com.alviano.app.mynotes.viewmodel.NoteViewModel

class listFragment : Fragment() {

    private lateinit var mNoteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val fab = view.findViewById<View>(R.id.floatingActionButton2)

        // Recyclerview
        val adapter = ListAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // UserViewModel
        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        mNoteViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })


        fab.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteAllNotes()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllNotes() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _, _ ->
            mNoteViewModel.deleteAllNotes()
            Toast.makeText(requireContext(), "Successfully removed everything", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No"){ _, _ -> }
        builder.setTitle("Deleter everything?")
        builder.setMessage("Are you sure you want to delete everything?")
        builder.create().show()
    }

}