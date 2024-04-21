package com.example.composetask.splash

import android.os.Bundle
import androidx.activity.compose.setContent
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
//                if (sharedPrefs.isOnBoardingShowing()) {
//                    val intent = Intent(this, MainActivity::class.java)
//                    startActivity(intent)
//                    finish()
//                } else {
//                    val intent = Intent(this, ActivityOnBoarding::class.java)
//                    startActivity(intent)
//                    finish()
//                }
            }
        }
    }
}


