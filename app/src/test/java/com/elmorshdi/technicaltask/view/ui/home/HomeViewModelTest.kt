package com.elmorshdi.technicaltask.view.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.elmorshdi.technicaltask.repository.FakeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
@ExperimentalCoroutinesApi
class HomeViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val dispatcher = TestCoroutineDispatcher()

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        viewModel = HomeViewModel(FakeRepository())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `get all product`() {
        viewModel.getProductsList()
    }

    @Test
    fun getMainUiState() {
    }

    @Test
    fun getProductsList() {
    }
}