package com.example.questapi_0188

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.questapi_0188.ui.theme.MyDataSiswaTheme
import com.example.questapi_0188.uicontroller.DataSiswaApp

class MainActivity : ComponentActivity() {

    private val izinLocalNetwork = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { diberikan ->
        if (diberikan) recreate()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mintaIzinLocalNetwork()
        enableEdgeToEdge()
        setContent {
            MyDataSiswaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DataSiswaApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    private fun mintaIzinLocalNetwork() {
        // Android 17 mewajibkan izin runtime untuk mengakses server di jaringan lokal
        // (mis. 10.0.2.2 saat aplikasi dijalankan di emulator).
        if (Build.VERSION.SDK_INT >= 37 &&
            checkSelfPermission(Manifest.permission.ACCESS_LOCAL_NETWORK) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            izinLocalNetwork.launch(Manifest.permission.ACCESS_LOCAL_NETWORK)
        }
    }
}
