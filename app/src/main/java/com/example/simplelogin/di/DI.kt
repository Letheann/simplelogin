package com.example.simplelogin.di

import com.example.simplelogin.presentation.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object DI {
    private val presentation = module {
        viewModel { MainActivityViewModel() }
    }
    val modules = listOf(presentation)
}