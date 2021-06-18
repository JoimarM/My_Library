 package com.example.roomdbapp

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class LibroViewModel(private val repository: LibroRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allWords: LiveData<List<Libro>> = repository.allBooks.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(libro: Libro) = viewModelScope.launch {
        repository.insert(libro)
    }


    fun delete(id: String) = viewModelScope.launch {
        repository.delete(id)
    }
}

class LibroViewModelFactory(private val repository: LibroRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LibroViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LibroViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
