package com.example.simplelogin.presentation

import androidx.lifecycle.viewModelScope
import com.example.simplelogin.compose.Credentials
import com.example.simplelogin.core.BaseMviViewModel
import com.example.simplelogin.core.ViewResource
import kotlinx.coroutines.launch

class MainActivityViewModel :
    BaseMviViewModel<ViewIntent, ViewState, ViewEffect>() {

    override fun initialState(): ViewState = ViewState()

    override fun intent(intent: ViewIntent) {
        viewModelScope.launch {
            when (intent) {
                is ViewIntent.DoLogin -> {
                    if (!intent.credentials.wrongPassword) {
                        setEffect { ViewEffect.OpenWelcomeActivity }
                    } else {
                        setEffect { ViewEffect.ShowToastItem }
                    }
                }
            }
        }
    }
}


sealed class ViewIntent : BaseMviViewModel.BaseViewIntent {
    data class DoLogin(val credentials: Credentials) : ViewIntent()
}

data class ViewState(val items: ViewResource<Unit>? = null) :
    BaseMviViewModel.BaseViewState

sealed class ViewEffect : BaseMviViewModel.BaseViewEffect {
    object ShowToastItem : ViewEffect()
    object OpenWelcomeActivity : ViewEffect()
}

