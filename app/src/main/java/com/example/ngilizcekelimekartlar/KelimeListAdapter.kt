package com.example.ngilizcekelimekartlar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class KelimeListAdapter(private val onItemDeleted: (Kelime) -> Unit) :
    ListAdapter<Kelime, KelimeListAdapter.KelimeViewHolder>(KelimelerComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KelimeViewHolder {
        return KelimeViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: KelimeViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.ingilizce, current.turkce)

        holder.deleteButton.setOnClickListener {
            onItemDeleted(current)
        }
    }

    class KelimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ingilizceTextView: TextView = itemView.findViewById(R.id.textViewIngilizce)
        private val turkceTextView: TextView = itemView.findViewById(R.id.textViewTurkce)
        val deleteButton: ImageView =
            itemView.findViewById(R.id.buttonDelete) // Silme butonu referansı eklendi


        fun bind(ingilizce: String, turkce: String) {
            ingilizceTextView.text = ingilizce
            turkceTextView.text = turkce.split(",").joinToString("\n") { it.trim() }


        }

        companion object {
            fun create(parent: ViewGroup): KelimeViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return KelimeViewHolder(view)
            }
        }
    }

    class KelimelerComparator : DiffUtil.ItemCallback<Kelime>() {
        override fun areItemsTheSame(oldItem: Kelime, newItem: Kelime): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Kelime, newItem: Kelime): Boolean {
            return oldItem.id == newItem.id
        }
    }
}
