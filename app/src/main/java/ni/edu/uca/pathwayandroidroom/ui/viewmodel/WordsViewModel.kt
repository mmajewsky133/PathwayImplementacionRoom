package ni.edu.uca.pathwayandroidroom.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ni.edu.uca.pathwayandroidroom.data.database.dao.WordsDao
import ni.edu.uca.pathwayandroidroom.data.database.entities.Word

class WordsViewModel(private val wordsDao: WordsDao): ViewModel() {

    val allWords: LiveData<List<Word>> = wordsDao.getWords().asLiveData()

    fun agregarPalabra(palabra: String) {
        val nuevaPalabra = Word(
            word = palabra
        )
        insertWord(nuevaPalabra)
    }

    private fun insertWord(word: Word) {
        viewModelScope.launch {
            wordsDao.insert(word)
        }
    }

    fun entradasValidas(palabra: String): Boolean {
        if (palabra.isBlank()) {
            return false
        }
        return true
    }
}

class WordsViewModelFactory(private val wordsDao: WordsDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WordsViewModel(wordsDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}