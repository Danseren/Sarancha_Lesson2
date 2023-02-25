package ru.aston.sarancha_lesson2

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.fragment_main.*
import ru.aston.sarancha_lesson2.Utils.makeGone
import ru.aston.sarancha_lesson2.Utils.makeVisible
import ru.aston.sarancha_lesson2.contract.HasCustomTitle
import ru.aston.sarancha_lesson2.contract.Navigator
import ru.aston.sarancha_lesson2.databinding.ActivityMainBinding
import ru.aston.sarancha_lesson2.view.*

class MainActivity : AppCompatActivity(), Navigator {

    private lateinit var binding: ActivityMainBinding

    private val currentFragment: Fragment
        get() = supportFragmentManager.findFragmentById(R.id.container)!!

    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?
        ) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            updateUI()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, AuthorizationFragment.newInstance())
                .commitNow()
        }
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, false)
    }

    override fun showAboutScreen() {
        launchParentFragment(AboutFragment.newInstance(), R.id.fragmentContainer)
    }

    override fun showAuthorizationScreen() {
        launchParentFragment(AuthorizationFragment.newInstance(), R.id.container)
    }

    override fun showMainScreen() {
        launchParentFragment(MainFragment.newInstance(), R.id.container)
    }

    override fun showOfficeInfoScreen(officeAddress: OfficeAddress) {
        launchParentFragmentWithAddToBackStack(
            OfficeInfoFragment.newInstance(officeAddress),
            R.id.fragmentContainer
        )
    }

    override fun showOfficesScreen() {
        launchParentFragment(OfficesFragment.newInstance(), R.id.fragmentContainer)
    }

    override fun showVacanciesScreen() {
        launchParentFragment(VacanciesFragment.newInstance(), R.id.fragmentContainer)
    }

    private fun launchParentFragment(fragment: Fragment, container: Int) {
        supportFragmentManager
            .beginTransaction()
            .replace(container, fragment)
            .commit()
    }

    private fun launchParentFragmentWithAddToBackStack(fragment: Fragment, container: Int) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(container, fragment)
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun updateUI() {
        val fragment = currentFragment

        if (fragment is HasCustomTitle) {
            binding.toolbar.title = getString(fragment.getTitleRes())
        } else {
            binding.toolbar.title = getString(R.string.app_name)
        }

        if (supportFragmentManager.backStackEntryCount > 0) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            bottom_navigation.makeGone()
        } else {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setDisplayShowHomeEnabled(false)
            if (bottom_navigation != null) bottom_navigation.makeVisible()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentListener)
    }
}