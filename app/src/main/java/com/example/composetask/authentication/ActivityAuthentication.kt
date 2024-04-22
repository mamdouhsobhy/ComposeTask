package com.example.composetask.authentication

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.composetask.home.MainActivity
import com.example.composetask.core.presentation.base.BaseActivity
import com.example.composetask.core.presentation.common.SharedPrefs
import com.example.composetask.authentication.presentation.authNavigation.AuthNavigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ActivityAuthentication : BaseActivity() {
    @Inject
    lateinit var sharedPrefs: SharedPrefs

    var counter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AuthNavigation(sharedPrefs, finishAuthScreen = {
                counter++
                if (counter == 1) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            })
        }
    }

}
