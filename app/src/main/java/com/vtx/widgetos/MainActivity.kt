package com.vtx.widgetos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vtx.widgetos.ui.VtxApp
import com.vtx.widgetos.ui.theme.VtxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VtxTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    VtxApp()
                }
            }
        }
    }
}
