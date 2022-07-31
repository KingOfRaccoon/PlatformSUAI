package ru.castprograms.platformsuai.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import ru.castprograms.platformsuai.android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var navHostController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//        binding.fab.visibility = View.INVISIBLE
//        binding.bottomNavigationView.transform(binding.fab)
           navHostController =
               supportFragmentManager.findFragmentById(R.id.container)?.findNavController()!!
            val appBarConfiguration = AppBarConfiguration(navHostController.graph)
            setupActionBarWithNavController(navHostController, appBarConfiguration)
            binding.bottomNavigationView.setupWithNavController(navHostController)

            navHostController.addOnDestinationChangedListener { _, destination, _ ->
                val needHomeButton = arrayOf(R.id.detailNewsFragment)
                supportActionBar?.setDisplayHomeAsUpEnabled(
                    destination.id in needHomeButton
                )
            }
       // }
        binding.fab.setOnClickListener {
            centerBNVClick()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        return navHostController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun centerBNVClick() {
        (binding.bottomNavigationView.getChildAt(0) as BottomNavigationMenuView)
            .getChildAt(2).performClick()
    }


}
