package com.example.composetask.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.composetask.authentication.ActivityAuthentication
import com.example.composetask.core.presentation.base.BaseActivity
import com.example.composetask.core.presentation.common.SharedPrefs
import com.example.composetask.core.presentation.utilities.LocaleHelper
import com.example.composetask.splash.splashCompose.SplashScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ActivitySplash : BaseActivity() {

    @Inject
    lateinit var sharedPrefs: SharedPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocaleHelper.onAttach(this)
        setContent {
            SplashScreen {
                val intent = Intent(this, ActivityAuthentication::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}


