package com.classes.altitude;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ResultsPage extends Activity {
	String roll;
	int branch;
	String sub1,sub2,sub3,rank,code;
	String name,date;
	ProgressBar pb2;
	ListView lv;
	List<String> list = new ArrayList<String>();
	ConnectivityManager cm;
	DateConvert dc;
	String[] dat;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dates_list);
		ActionBar a=getActionBar();
        a.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#33b5e5")));
		dc=new DateConvert(this);
		Bundle bundle = getIntent().getExtras();
		String dates[] = bundle.getStringArray("dates");
		roll=bundle.getString("roll");
		branch=Integer.parseInt(bundle.getString("branch"));
		dat=new String[dates.length];
		
		for (int i = 0; i < dates.length; i++) {
			list.add(dates[i]);
			dat[i]=dc.getDate(dates[i]);
		}
		
		
		pb2=(ProgressBar)findViewById(R.id.probar2);
		ResultsAdapter datesAdapter = new ResultsAdapter(this, dat);
	//	ArrayAdapter<String> datesAdapter = new ArrayAdapter<String>(this, R.layout.dates_list_item, dates);
		lv=(ListView)findViewById(R.id.datescontainer);
		lv.setAdapter(datesAdapter);
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				cm = (ConnectivityManager) getSystemService(
						Context.CONNECTIVITY_SERVICE);
				NetworkInfo activenet = cm.getActiveNetworkInfo();
				
				if (activenet != null && activenet.isConnectedOrConnecting()) {
					pb2.setVisibility(0);
					lv.setVisibility(8);
					
				JSON_Data json_results=new JSON_Data();
				List<BasicNameValuePair> params=new ArrayList<BasicNameValuePair>();
				params.add(new BasicNameValuePair("tag", "result"));
				params.add(new BasicNameValuePair("date",list.get(position)));
				params.add(new BasicNameValuePair("branch",String.valueOf(branch)));
				params.add(new BasicNameValuePair("rollno", roll));
				JSONObject jobj=json_results.getjson(params);
				try
				{
				sub1=jobj.getString("s1");
				sub2=jobj.getString("s2");
				sub3=jobj.getString("s3");
				rank=jobj.getString("rank");
				code=jobj.getString("testcode");
				name=jobj.getString("name");
				date=jobj.getString("date");
				}
				catch(Exception e)
				{
					Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
				}
				
				Intent i=new Intent(getApplicationContext(),Display.class);
				i.putExtra("branch", String.valueOf(branch));
				i.putExtra("name", name);
				i.putExtra("roll", roll);
				i.putExtra("code", code);
				if(branch==1 || branch==3)
				{
					i.putExtra("branch",1);
				}
				else if(branch==2 || branch==4)
				{
					i.putExtra("branch",2);
				}
				i.putExtra("date", date);
				i.putExtra("sub1", sub1);
				i.putExtra("sub2", sub2);
				i.putExtra("sub3", sub3);
				i.putExtra("rank", rank);
				
				pb2.setVisibility(8);
				lv.setVisibility(0);
				startActivity(i);
				
				}
			}
		});
	}

}
