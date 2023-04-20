package ni.edu.uca.pathwayandroidroom.ui.view

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ni.edu.uca.pathwayandroidroom.R
import ni.edu.uca.pathwayandroidroom.WordsApplication
import ni.edu.uca.pathwayandroidroom.databinding.FragmentAddWordBinding
import ni.edu.uca.pathwayandroidroom.ui.viewmodel.WordsViewModel
import ni.edu.uca.pathwayandroidroom.ui.viewmodel.WordsViewModelFactory

class AddWordFragment : Fragment() {

    private val viewModel: WordsViewModel by activityViewModels {
        WordsViewModelFactory(
            (activity?.application as WordsApplication).database
                .WordsDao()
        )
    }

    //el view binding
    private var _binding: FragmentAddWordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddWordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnAgregar.isEnabled = false

            btnAgregar.setOnClickListener {
                agregarNuevaPalabra()
                limpiar()

                it.findNavController().navigate(R.id.action_addWordFragment_to_wordsFragment)
            }

            etWord.addTextChangedListener(wordTextWatcher)
        }
    }

    private fun limpiar() {
        with (binding) {
            etWord.text = null
            etWord.requestFocus()
        }
    }

    //Funcion para validar que los Edit Texts no esten vacios
    private fun entradasValidas(): Boolean {
        return viewModel.entradasValidas(
            binding.etWord.text.toString()
        )
    }

    //Funcion para mandar a agregar una palabra
    private fun agregarNuevaPalabra() {
        if (entradasValidas()) {
            viewModel.agregarPalabra(
                binding.etWord.text.toString()
            )
        }
    }

    private val wordTextWatcher = object: TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            binding.btnAgregar.isEnabled = entradasValidas()
        }

        override fun afterTextChanged(p0: Editable?) {}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Hide keyboard.
        val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as
                InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
        _binding = null
    }
}