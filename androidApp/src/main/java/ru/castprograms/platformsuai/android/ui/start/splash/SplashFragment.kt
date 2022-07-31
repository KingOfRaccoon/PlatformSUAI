package ru.castprograms.platformsuai.android.ui.start.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.castprograms.platformsuai.android.R
import ru.castprograms.platformsuai.android.databinding.FragmentSplashBinding

class SplashFragment: Fragment(R.layout.fragment_splash) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSplashBinding.bind(view)
    }
}