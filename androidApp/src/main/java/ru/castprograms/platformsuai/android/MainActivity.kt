package ru.castprograms.platformsuai.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import ru.castprograms.platformsuai.android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//        binding.fab.visibility = View.INVISIBLE
//        binding.bottomNavigationView.transform(binding.fab)
        supportFragmentManager.findFragmentById(R.id.container)?.findNavController()!!.let {
            val appBarConfiguration = AppBarConfiguration(it.graph)
            setupActionBarWithNavController(it, appBarConfiguration)
            binding.bottomNavigationView.setupWithNavController(it)
        }

        binding.fab.setOnClickListener {
            centerBNVClick()
        }
    }

    private fun centerBNVClick() {
        (binding.bottomNavigationView.getChildAt(0) as BottomNavigationMenuView)
            .getChildAt(2).performClick()
    }
}
