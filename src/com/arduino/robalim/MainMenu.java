package com.arduino.robalim;


import com.arduino.robalim.arduino.ArduinoReceiver;
import com.arduino.robalim.arduino.ConnectionManager;
import com.arduino.robalim.arduino.RobAlimInterfaceIn;
import com.arduino.robalim.view.ConnectionFragmentView;
import com.arduino.robalim.view.IndicationsFragmentView;
import com.arduino.robalim.view.MoveFragmentView;
import com.arduino.robalim.view.StatutFragmentView;
import com.arduino.robalim.view.TestsFragmentView;
import com.example.robalim.R;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import at.abraxas.amarino.AmarinoIntent;

public class MainMenu extends Activity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private ConnectionManager connection_manager;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] menu_items;
    private Fragment[] fragments;
    private Menu menu_bar;
    private int current_menu = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle = mDrawerTitle = getTitle();
        menu_items = getResources().getStringArray(R.array.menu_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_list_item, menu_items));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close){
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
//                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
//                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        connection_manager=ConnectionManager.getInstance();
        
        fragments = new Fragment[5];
        fragments[0]= new ConnectionFragmentView();
        fragments[1]= new StatutFragmentView();
        fragments[2]= new MoveFragmentView();
        fragments[3]= new IndicationsFragmentView();
        fragments[4]= new TestsFragmentView();
        
        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        menu_bar=menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
//        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	return mDrawerToggle.onOptionsItemSelected(item);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
    	Fragment fragment;
    	if(current_menu!=position){
	        try{
	        	fragment = fragments[position];
	        	current_menu=position;
	            Bundle args = new Bundle();
	            fragment.setArguments(args);
	
	            FragmentManager fragmentManager = getFragmentManager();
	            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
	        }
	    	catch(ArrayIndexOutOfBoundsException e){
	    		Log.e("MainMenu","Select item: la position choisie excede les menus disponibles");
	    	}
    	}

        mDrawerList.setItemChecked(position, true);
        setTitle(menu_items[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    protected void onStart() {
    	super.onStart();
//    	connection_manager.registerReceiver(this);
    };
    
    @Override
    protected void onStop() {
    	super.onStop();
    	connection_manager.disconnectDevices(this);
//    	connection_manager.unregistererReceiver(this);
    };
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    
    public void updateConnection(boolean connected){
    	menu_bar.findItem(R.id.connection_led).setIcon(R.drawable.connection_on);
    }
}
