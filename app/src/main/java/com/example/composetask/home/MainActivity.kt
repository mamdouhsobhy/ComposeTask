package com.example.composetask.home

import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.composetask.core.presentation.common.SharedPrefs
import com.example.composetask.core.presentation.utilities.ConnectionReceiver
import com.example.composetask.home.presentation.homeNavigation.HomeNavigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity(),
    ConnectionReceiver.ReceiverListener {

    var isConnectedForMenu = false

    @Inject
    lateinit var sharedPrefs: SharedPrefs
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkConnection()
        setContent {
            HomeNavigation(sharedPrefs,isConnectedForMenu)
        }
    }

    private fun checkConnection() {

        // initialize intent filter
        val intentFilter = IntentFilter()

        // add action
        intentFilter.addAction("android.new.conn.CONNECTIVITY_CHANGE")

        // register receiver
        registerReceiver(ConnectionReceiver(), intentFilter)

        // Initialize listener
        ConnectionReceiver.Listener = this

        // Initialize connectivity manager
        val manager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // Initialize network info
        val networkInfo = manager.activeNetworkInfo

        // get connection status
        val isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting
        isConnectedForMenu = isConnected

    }


    override fun onResume() {
        super.onResume()
        // call method
        checkConnection()
    }

    override fun onPause() {
        super.onPause()
        // call method
        checkConnection()
    }

    override fun onNetworkChange(isConnected: Boolean) {
    }
}
