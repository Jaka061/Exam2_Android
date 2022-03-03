package com.kay.progayim

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView

class EmpAdapter(private val click: (id: Long) -> Unit) : RecyclerView.Adapter<EmpAdapter.ViewHolder>(){

    private var list = listOf<Character>()

    fun setData(list: List<Character>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycle, parent, false)
        return ViewHolder(item , click)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View, private val click: (id: Long) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(ch: Character) {
            val name = itemView.findViewById<AppCompatTextView>(R.id.rName)
//            val status = itemView.findViewById<AppCompatTextView>(R.id.rStatus)
//            val species = itemView.findViewById<AppCompatTextView>(R.id.rSpecies)
//            val location = itemView.findViewById<AppCompatTextView>(R.id.rLocation)
//            val episode = itemView.findViewById<AppCompatTextView>(R.id.episode)
            name.text = ch.name
//            status.text = ch.status
//            species.text = ch.species
//            location.text = ch.location.toString()
//            episode.text = ch.episode
            Log.e("TAG", "ID66")

            itemView.setOnClickListener {
                Log.e("TAG", "Adapter OK")
                click.invoke(ch.id!!)
            }
        }
    }
}
