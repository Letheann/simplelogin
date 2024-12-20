package com.example.simplelogin.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.simplelogin.compose.LoginForm
import com.example.simplelogin.compose.theme.SimpleloginTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleloginTheme {
                LoginForm(viewModel) { credentials ->
                    viewModel.intent(ViewIntent.DoLogin(credentials))
                }
            }
        }
        handleViewEffect()
    }

    private fun handleViewEffect() = lifecycleScope.launch {
        viewModel.viewEffect.collect { effect ->
            when (effect) {
                is ViewEffect.ShowToastItem -> {
                    Toast.makeText(this@MainActivity, "Wrong Password!", Toast.LENGTH_SHORT).show()
                }

                is ViewEffect.OpenWelcomeActivity -> startActivity(
                    Intent(
                        this@MainActivity,
                        WelcomeActivity::class.java
                    )
                )
            }
        }
    }
}
