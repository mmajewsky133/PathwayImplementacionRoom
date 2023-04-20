package ni.edu.uca.pathwayandroidroom.ui.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ni.edu.uca.pathwayandroidroom.R
import ni.edu.uca.pathwayandroidroom.WordsApplication
import ni.edu.uca.pathwayandroidroom.databinding.FragmentWordsBinding
import ni.edu.uca.pathwayandroidroom.ui.view.adapter.WordListAdapter
import ni.edu.uca.pathwayandroidroom.ui.viewmodel.WordsViewModel
import ni.edu.uca.pathwayandroidroom.ui.viewmodel.WordsViewModelFactory

class WordsFragment : Fragment() {

    private val viewModel: WordsViewModel by activityViewModels {
        WordsViewModelFactory(
            (activity?.application as WordsApplication).database
                .WordsDao()
        )
    }

    //el view binding
    private var _binding: FragmentWordsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWordsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = WordListAdapter();

        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerView.adapter = adapter

        binding.fabAgregar.setOnClickListener {
            it.findNavController().navigate(R.id.action_wordsFragment_to_addWordFragment)
        }

        viewModel.allWords.observe(this.viewLifecycleOwner) { words ->
            words.let {
                adapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}