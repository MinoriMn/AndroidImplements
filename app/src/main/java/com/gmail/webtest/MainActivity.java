package com.gmail.webtest;

import android.content.Intent;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.Fragment;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;

import com.gmail.webtest.fragment.ProcessingFragment;
import com.gmail.webtest.fragment.WebViewFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    private Fragment fragment;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        ((NavigationView)findViewById(R.id.nav_view)).setNavigationItemSelectedListener(this);

        fragment = new WebViewFragment();
        fragmentTransaction(fragment);
    }

    private void fragmentTransaction(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        drawerLayout.closeDrawer(GravityCompat.START);
        switch (menuItem.getItemId()){
            case R.id.navigation_web_view:
                fragment = new WebViewFragment();
                break;

            case R.id.navigation_processing:
                fragment = new ProcessingFragment();
                break;
        }
        fragmentTransaction(fragment);

        return true;
    }

    /**
     * Needed by ProcessingFragment
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        if(fragment instanceof ProcessingFragment){
            ((ProcessingFragment)fragment).onNewIntent(intent);
        }else {
            super.onNewIntent(intent);
        }
    }
}
