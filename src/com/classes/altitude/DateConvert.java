package com.classes.altitude;

import java.util.StringTokenizer;

import android.content.Context;


public class DateConvert {
	String day,month,year,modifieddate="";
	String[] mon;
	
	Context context;

	public DateConvert(Context context) {
	    this.context = context;
	}
	
	public String getDate(String date)
	{
		StringTokenizer st = new StringTokenizer(date, "-");
		year = st.nextToken();
		month = st.nextToken();
		day = st.nextToken();
		
		mon= context.getResources().getStringArray(R.array.months);
		
		modifieddate=day+" "+mon[Integer.parseInt(month)-1]+", "+year;
		
		return modifieddate;
	}
	
	
	
	

}
