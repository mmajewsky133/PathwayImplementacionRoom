package ni.edu.uca.pathwayandroidroom.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ni.edu.uca.pathwayandroidroom.data.database.entities.Word
import ni.edu.uca.pathwayandroidroom.databinding.WordListItemBinding

class WordListAdapter() : ListAdapter<Word, WordListAdapter.WordViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val binding = WordListItemBinding.inflate(
            LayoutInflater.from( parent.context )
        )
        return WordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }


    class WordViewHolder(private val binding: WordListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(Word: Word) {
            binding.tvPalabra.text = Word.word
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Word>() {
            override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}