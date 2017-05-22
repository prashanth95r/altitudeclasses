package com.classes.altitude.adapter;

import com.classes.altitude.Home;
import com.classes.altitude.Results;
import com.classes.altitude.TestSchedule;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter{

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int index) {
		
		switch(index){
		case 0:
			return new Home();
		case 1:
			return new TestSchedule();
		case 2:
			return new Results();
			
		
		
		}
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public CharSequence getPageTitle (int position) {
    	switch(position)
    	{
    	case 0:
    		return "HOME";
    	case 1:
            return "TEST SCHEDULE";
    	case 2:
            return "RESULTS";
    	}
		return null;
    }
}
