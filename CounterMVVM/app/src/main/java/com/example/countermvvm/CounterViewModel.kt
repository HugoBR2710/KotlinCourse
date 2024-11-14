package com.example.countermvvm

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CounterViewModel() : ViewModel() {
    private val _repository: CounterRepository = CounterRepository()
    private val _count = mutableIntStateOf(_repository.getCounter().count)

    //Expose the count as an immutable state

    val count : MutableState<Int> = _count

    fun increment() {
        _repository.increment()
        _count.value = _repository.getCounter().count
    }

    fun decrement(){
        _repository.decrement()
        _count.value = _repository.getCounter().count
    }
}