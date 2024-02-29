package com.kryptopass.common.state

import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

@OptIn(ExperimentalCoroutinesApi::class)
class MviViewModelTest {

    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
    private lateinit var viewModel: MviViewModel<String, UiState<String>, UiAction, UiSingleEvent>

    @Before
    fun setUp() = runTest {
        Dispatchers.setMain(testDispatcher)
        viewModel = object : MviViewModel<String, UiState<String>, UiAction, UiSingleEvent>() {
            override fun initState(): UiState<String> = UiState.Loading

            override fun handleAction(action: UiAction) {
            }
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testSubmitAction() = runTest {
        val uiAction = mock<UiAction>()
        viewModel = object : MviViewModel<String, UiState<String>, UiAction, UiSingleEvent>() {
            override fun initState(): UiState<String> = UiState.Loading

            override fun handleAction(action: UiAction) {
                TestCase.assertEquals(uiAction, action)
            }
        }
        viewModel.submitAction(uiAction)
    }

    @Test
    fun testSubmitState() = runTest {
        val uiState = UiState.Success("test")
        viewModel.submitState(uiState)
        TestCase.assertEquals(uiState, viewModel.uiStateFlow.value)
    }

    @Test
    fun testSubmitSingleEvent() = runTest {
        val uiSingleEvent = mock<UiSingleEvent>()
        viewModel.submitSingleEvent(uiSingleEvent)
        TestCase.assertEquals(uiSingleEvent, viewModel.singleEventFlow.first())
    }
}