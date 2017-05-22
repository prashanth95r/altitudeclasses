package com.classes.altitude;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

public class CreditsPage extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about2);
		ActionBar a=getActionBar();
        a.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#33b5e5")));
        
		TextView dev=(TextView) findViewById(R.id.dev);
		TextView col=(TextView) findViewById(R.id.textView0);
		TextView tv1=(TextView) findViewById(R.id.textView1);
		TextView tv2=(TextView) findViewById(R.id.textView2);
		TextView tv3=(TextView) findViewById(R.id.textView3);
		TextView tv5=(TextView) findViewById(R.id.textView5);
		TextView col1=(TextView) findViewById(R.id.textView6);

		Typeface tr=Typeface.createFromAsset(this.getAssets(),"TitilliumWebRegular.ttf");
		Typeface sb=Typeface.createFromAsset(this.getAssets(),"TitilliumWebSemiBold.ttf");
		col.setTypeface(tr);
		tv1.setTypeface(tr);
		tv2.setTypeface(tr);
		tv3.setTypeface(tr);
		tv5.setTypeface(tr);
		col1.setTypeface(tr);
		dev.setTypeface(sb);
		
	}

}
