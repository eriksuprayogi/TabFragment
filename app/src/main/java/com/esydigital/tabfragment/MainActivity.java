package com.esydigital.tabfragment;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.esydigital.tabfragment.fragment.TabFragment;


public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    public static final String SEARCH_ACTION_INTENT = BuildConfig.APPLICATION_ID+".SEARCH";
    public static final String SEARCH_TERM = "SEARCH_TERM";
    Toolbar toolbar;
    FragmentManager manager;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        manager = getSupportFragmentManager();
        setFragment(TabFragment.newInstance());
    }

    private void setFragment(Fragment fragment){
        manager.beginTransaction().replace(R.id.container, fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        Intent intent = new Intent(SEARCH_ACTION_INTENT);
        intent.putExtra(SEARCH_TERM, s);
        sendBroadcast(intent);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        Intent intent = new Intent(SEARCH_ACTION_INTENT);
        intent.putExtra(SEARCH_TERM, s);
        sendBroadcast(intent);
        return false;
    }
}
