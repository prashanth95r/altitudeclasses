package com.classes.altitude;

import com.classes.altitude.adapter.TabsPagerAdapter;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

@SuppressLint("NewApi")
public class MainActivity extends FragmentActivity {
	
	
	
	
	 private ViewPager viewPager;
	    private TabsPagerAdapter mAdapter;
	    
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        ActionBar a=getActionBar();
	        a.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#33b5e5")));
	        // Initialisation
	        viewPager = (ViewPager) findViewById(R.id.pager);
	        PagerTabStrip strip = PagerTabStrip.class.cast(findViewById(R.id.pager_title_strip));
	        
	        strip.setTabIndicatorColor(Color.parseColor("#2991b7"));
	        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
	        
	        viewPager.setAdapter(mAdapter);
	      
	        
	    }
	    
	    
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {

	    	getMenuInflater().inflate(R.menu.main, menu);
	    	return true;
	    	
	    }
	    
	    
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        
	        switch (item.getItemId()) {
	        case R.id.action_settings:
	        	Intent i=new Intent(this.getApplicationContext(),CreditsPage.class);
	        	startActivity(i);
	        	
	        }
	        return true;
	    }
	    


}
