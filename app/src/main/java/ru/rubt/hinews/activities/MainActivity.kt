package ru.rubt.hinews.activities

import android.app.SearchManager
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import ru.rubt.hinews.App
import ru.rubt.hinews.R

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var navNews: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navNews = findViewById(R.id.nav_view)
        navNews.setNavigationItemSelectedListener(this)

    }

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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        val headerView = navNews.getHeaderView(0)

        val ivNewsThemeImage =
            headerView.findViewById<ImageView>(R.id.iv_news_theme_image)
        val tvNewsThemeTitle =
            headerView.findViewById<TextView>(R.id.tv_news_theme_title)

        when (item.itemId) {
            R.id.item_mystery_of_space -> {
                ivNewsThemeImage.setImageResource(R.drawable.mystery_of_space)
                tvNewsThemeTitle.text = getString(R.string.mystery_of_space_title)
            }
            R.id.item_art_intel -> {
                ivNewsThemeImage.setImageResource(R.drawable.art_intel)
                tvNewsThemeTitle.text = getString(R.string.art_intel_title)
            }
            R.id.item_medicina -> {
                ivNewsThemeImage.setImageResource(R.drawable.medicina)
                tvNewsThemeTitle.text = getString(R.string.medicina_title)
            }
            R.id.item_health -> {
                ivNewsThemeImage.setImageResource(R.drawable.healthy)
                tvNewsThemeTitle.text = getString(R.string.healph_title)
            }
            R.id.item_musk -> {
                ivNewsThemeImage.setImageResource(R.drawable.ilon_musk)
                tvNewsThemeTitle.text = getString(R.string.ilon_mask_title)
            }
            R.id.item_tesla -> {
                ivNewsThemeImage.setImageResource(R.drawable.tesla)
                tvNewsThemeTitle.text = getString(R.string.tesla_title)
            }
            R.id.item_mars -> {
                ivNewsThemeImage.setImageResource(R.drawable.mars)
                tvNewsThemeTitle.text = getString(R.string.mars_title)
            }
            R.id.item_roboto_tech -> {
                ivNewsThemeImage.setImageResource(R.drawable.roboto_tech)
                tvNewsThemeTitle.text = getString(R.string.roboto_tech_title)
            }
            R.id.item_universe -> {
                ivNewsThemeImage.setImageResource(R.drawable.universe)
                tvNewsThemeTitle.text = getString(R.string.universe_title)
            }
        }
        return true
    }



}