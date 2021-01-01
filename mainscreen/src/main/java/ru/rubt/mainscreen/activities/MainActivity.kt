package ru.rubt.mainscreen.activities

import android.app.SearchManager
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import ru.rubt.data.remote.states.EmptyHiNewsState
import ru.rubt.data.remote.states.FailedUpdatedHiNewsState
import ru.rubt.data.remote.states.UpdatedHiNewsState
import ru.rubt.mainscreen.R
import ru.rubt.mainscreen.di.HiNewsActivityComponent
import ru.rubt.mainscreen.di.HiNewsActivityComponentProvider
import ru.rubt.newsfeature.di.HiNewsViewModelFactory
import ru.rubt.newsfeature.fragments.EmptyNewsFragment
import ru.rubt.newsfeature.fragments.HiNewsFragment
import ru.rubt.newsfeature.fragments.LoadingFragment
import ru.rubt.newsfeature.viewmodels.HiNewsViewModel
import javax.inject.Inject


//StatusErrorListener
class MainActivity : AppCompatActivity()  {

    @Inject
    lateinit var hiNewsViewModelFactory: HiNewsViewModelFactory

    private val hiNewsViewModel by viewModels<HiNewsViewModel> { hiNewsViewModelFactory }

    lateinit var hiNewsComponent: HiNewsActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {

        hiNewsComponent = (application as HiNewsActivityComponentProvider)
            .provideHiNewsActivityComponent()
        hiNewsComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))

        setupDrawer()
        setupNavigationView()

    }

    private fun updateHiNews(theme: String) {
        makeFragmentTransaction(LoadingFragment())
        hiNewsViewModel.updateHiNews(theme).observe(this, Observer {
            when (it) {
                is FailedUpdatedHiNewsState -> {
                    showFailedUpdate(theme)
                }
                is EmptyHiNewsState -> {
                    makeFragmentTransaction(EmptyNewsFragment())
                }
                is UpdatedHiNewsState -> {
                    makeFragmentTransaction(HiNewsFragment.newInstance(theme))
                }
            }
        })
    }

    private fun makeFragmentTransaction(fragment: Fragment) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragment_container_view, fragment)
        }
    }

//    override fun showStatus(statusError: StatusError) {
////        when (statusError) {
////            is EmptyHiNewsStatus -> {
////
////            }
////            is NetworkErrorStatus -> {
////
////            }
////        }
//        showError(statusError.message)
//    }

    override fun onBackPressed() {
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
        super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)

        val searchManager =
            getSystemService(Context.SEARCH_SERVICE) as SearchManager

        val searchView =
            menu?.findItem(R.id.item_search)?.actionView as SearchView
        val etSearchField =
            searchView.findViewById<EditText>(
                androidx.appcompat.R.id.search_src_text)
        etSearchField.setTextColor(Color.WHITE)
        etSearchField.setHintTextColor(Color.WHITE)
        etSearchField.hint = getString(R.string.search_view_hint)

        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setIconifiedByDefault(true)
            setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })
        }

        return true;
    }

    private fun setupNavigationView() {
        val navNews: NavigationView = findViewById(R.id.nav_view)

        val headerView = navNews.getHeaderView(0)

        val ivNewsThemeImage =
                headerView.findViewById<ImageView>(R.id.iv_news_theme_image)
        val tvNewsThemeTitle =
                headerView.findViewById<TextView>(R.id.tv_news_theme_title)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)

        navNews.setNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.item_mystery_of_space -> {
                    ivNewsThemeImage.setImageResource(R.drawable.mystery_of_space)
                    tvNewsThemeTitle.text = getString(R.string.mystery_of_space_title)
                    updateHiNews(getString(R.string.mystery_of_space_theme_id))
                }
                R.id.item_art_intel -> {
                    ivNewsThemeImage.setImageResource(R.drawable.art_intel)
                    tvNewsThemeTitle.text = getString(R.string.art_intel_title)
                    updateHiNews(getString(R.string.art_intel_theme_id))
                }
                R.id.item_medicina -> {
                    ivNewsThemeImage.setImageResource(R.drawable.medicina)
                    tvNewsThemeTitle.text = getString(R.string.medicina_title)
                    updateHiNews(getString(R.string.medicina_theme_id))
                }
                R.id.item_health -> {
                    ivNewsThemeImage.setImageResource(R.drawable.healthy)
                    tvNewsThemeTitle.text = getString(R.string.healph_title)
                    updateHiNews(getString(R.string.healph_theme_id))
                }
                R.id.item_musk -> {
                    ivNewsThemeImage.setImageResource(R.drawable.ilon_musk)
                    tvNewsThemeTitle.text = getString(R.string.ilon_mask_title)
                    updateHiNews(getString(R.string.ilon_mask_theme_id))
                }
                R.id.item_tesla -> {
                    ivNewsThemeImage.setImageResource(R.drawable.tesla)
                    tvNewsThemeTitle.text = getString(R.string.tesla_title)
                    updateHiNews(getString(R.string.tesla_theme_id))
                }
                R.id.item_mars -> {
                    ivNewsThemeImage.setImageResource(R.drawable.mars)
                    tvNewsThemeTitle.text = getString(R.string.mars_title)
                    updateHiNews(getString(R.string.mars_theme_id))
                }
                R.id.item_roboto_tech -> {
                    ivNewsThemeImage.setImageResource(R.drawable.roboto_tech)
                    tvNewsThemeTitle.text = getString(R.string.roboto_tech_title)
                    updateHiNews(getString(R.string.roboto_tech_theme_id))
                }
                R.id.item_universe -> {
                    ivNewsThemeImage.setImageResource(R.drawable.universe)
                    tvNewsThemeTitle.text = getString(R.string.universe_title)
                    updateHiNews(getString(R.string.universe_theme_id))
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)

            true
        }
    }

    private fun setupDrawer() {
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)

        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, findViewById(R.id.toolbar),
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun showFailedUpdate(theme: String) =
        Snackbar.make(findViewById(R.id.root_layout), R.string.update_failed, Snackbar.LENGTH_LONG)
            .setAction(R.string.update_snack_action) {
                updateHiNews(theme)
            }.show()

}