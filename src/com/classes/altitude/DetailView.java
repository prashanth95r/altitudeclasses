package com.classes.altitude;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

public class DetailView extends Activity{
	TextView head,loc,descr,date;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle b=getIntent().getExtras();
		setContentView(R.layout.detail_view);
		ActionBar a=getActionBar();
        a.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#33b5e5")));
		head=(TextView)findViewById(R.id.head);
		loc=(TextView)findViewById(R.id.loc);
		descr=(TextView)findViewById(R.id.desp);
		date=(TextView)findViewById(R.id.date);
		 Typeface tp=Typeface.createFromAsset(this.getAssets(),"TitilliumWebRegular.ttf");
	        Typeface tb=Typeface.createFromAsset(this.getAssets(),"TitilliumWebSemiBold.ttf");
	        head.setTypeface(tb);
	        descr.setTypeface(tp);
		head.setText(b.getString("head"));
		descr.setText(b.getString("descr"));
		loc.setText(b.getString("loc"));
		date.setText(b.getString("date"));
		
		
	}

}
