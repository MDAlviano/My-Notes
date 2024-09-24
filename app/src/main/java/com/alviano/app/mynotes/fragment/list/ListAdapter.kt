package com.alviano.app.mynotes.fragment.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.alviano.app.mynotes.R
import com.alviano.app.mynotes.data.Note
import com.alviano.app.mynotes.databinding.CustomRowBinding

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var noteList = emptyList<Note>()
    private lateinit var context: Context

    class MyViewHolder(val binding: CustomRowBinding): RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        val binding = CustomRowBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = noteList[position]
        holder.binding.idTxt.text = currentItem.id.toString()
        holder.binding.titleTxt.text = currentItem.title
        holder.binding.noteTxt.text = currentItem.note
    }

    fun setData(note: List<Note>){
        this.noteList = note
        notifyDataSetChanged()
    }

}