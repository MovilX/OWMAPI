package com.kryptopass.common.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

// NOTE: base template across app (common properties/functions across all viewModels)
abstract class MviViewModel<T : Any, S : UiState<T>, A : UiAction, E : UiSingleEvent> : ViewModel() {

    private val _uiStateFlow: MutableStateFlow<S> by lazy {
        MutableStateFlow(initState())
    }
    val uiStateFlow: StateFlow<S> = _uiStateFlow

    private val actionFlow: MutableSharedFlow<A> = MutableSharedFlow()

    private val _singleEventFlow = Channel<E>()
    val singleEventFlow = _singleEventFlow.receiveAsFlow()

    init {
        viewModelScope.launch {
            actionFlow.collect {
                handleAction(it)
            }
        }
    }

    // NOTE: MutableStateFlow requires an initial value, since we don't have concrete value to
    // initialize, we will provide initial value in abstract initState() function
    abstract fun initState(): S

    // NOTE: when new actions are submitted (i.e. user actions, screen load)
    abstract fun handleAction(action: A)

    // NOTE: notify viewmodel of any changes via emit
    fun submitAction(action: A) {
        viewModelScope.launch {
            actionFlow.emit(action)
        }
    }

    // NOTE: update state
    fun submitState(state: S) {
        viewModelScope.launch {
            _uiStateFlow.value = state
        }
    }

    // NOTE: send event
    fun submitSingleEvent(event: E) {
        viewModelScope.launch {
            _singleEventFlow.send(event)
        }
    }
}