package com.classes.altitude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class TestSchedule extends Fragment {

	Database_News db;
	ListView lv;
	Activity activity1 = null;
	CustomAdapter ca;
	Intent in[];
	Button more;
	int last, j, flag = 0;
	ProgressBar pb;
	SwipeRefreshLayout swipeLayout;
	ConnectivityManager cm;
	DateConvert dc;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		cm = (ConnectivityManager) getActivity().getSystemService(
				Context.CONNECTIVITY_SERVICE);
		NetworkInfo activenet = cm.getActiveNetworkInfo();
		db = new Database_News(this.getActivity());
		dc=new DateConvert(this.getActivity());
		View rootView = inflater.inflate(R.layout.list_item, container, false);

		swipeLayout = (SwipeRefreshLayout) rootView
				.findViewById(R.id.swipe_container);
		pb = (ProgressBar) rootView.findViewById(R.id.pb);
		lv = (ListView) rootView.findViewById(R.id.listcontainer);
		more = (Button) rootView.findViewById(R.id.morebutton);
		Typeface tr=Typeface.createFromAsset(getActivity().getAssets(),"TitilliumWebRegular.ttf");
		more.setTypeface(tr);
		
		
		swipeLayout.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh() {
				NetworkInfo activenet = cm.getActiveNetworkInfo();
				if (activenet != null && activenet.isConnectedOrConnecting()) {
					flag = 0;
					db.resetTables();
					new newsload().execute(0);

				} else {
					swipeLayout.setRefreshing(false);
					Toast.makeText(getActivity(), "No Internet Connection",
							Toast.LENGTH_LONG).show();
				}
			}
		});
		swipeLayout.setColorScheme(android.R.color.holo_blue_bright,
				android.R.color.holo_green_light,
				android.R.color.holo_orange_light,
				android.R.color.holo_red_light);
/*		swipeLayout.setColorScheme(Color.parseColor("#33b5e5"),
				Color.parseColor("#99cc00"),
				Color.parseColor("#ffbb33"),
				Color.parseColor("#ff4444"));*/
		
		
		if (activenet != null && activenet.isConnectedOrConnecting()) {
			db.resetTables();
			new newsload().execute(0);

		} else {
			
			Toast.makeText(this.getActivity().getApplicationContext(),
					"No connection", Toast.LENGTH_LONG).show();
			display();
		}

		
		
		lv.setOnScrollListener(new AbsListView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				if (firstVisibleItem == 0) {
					swipeLayout.setEnabled(true);
				} else
					swipeLayout.setEnabled(false);
			}
		});

		
		
		
		more.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				flag = 1;
				NetworkInfo activenet = cm.getActiveNetworkInfo();
				if (activenet != null && activenet.isConnectedOrConnecting()) {
					db.resetTables();
					new newsload().execute(0);

				} else {
					
					Toast.makeText(getActivity(),"No connection", Toast.LENGTH_LONG).show();
					display();
				}

			}
		});
		return rootView;
	}

	private class newsload extends AsyncTask<Integer, Integer, Integer> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			lv.setVisibility(8);
			more.setVisibility(8);
			pb.setVisibility(0);

		}

		@Override
		protected Integer doInBackground(Integer... params1) {
			JSON_Data json_news = new JSON_Data();
			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
			params.add(new BasicNameValuePair("tag", "news"));
			if (flag == 0)
				params.add(new BasicNameValuePair("id", String.valueOf(db
						.getRowCount())));
			else if (flag == 1)
				params.add(new BasicNameValuePair("id", String.valueOf((last))));

			String jobj = json_news.getjsonarray(params);

			adddata(jobj);

			return null;

		}

		@Override
		protected void onPostExecute(Integer result) {

			display();
	}
		
	}
	
	
	void display()
	{
		ArrayList<ListModel> listContact = GetlistContact();
		lv.setVisibility(0);
		more.setVisibility(0);
		pb.setVisibility(8);
		lv.setAdapter(new CustomAdapter(getActivity()
				.getApplicationContext(), listContact));

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				startActivity(in[db.getRowCount() - position - 1]);

			}

		});

		swipeLayout.setRefreshing(false);


	}

	void adddata(String jobj) {

		JSONArray jArray = null;
		try {
			jArray = new JSONArray(jobj);

			for (int i = 0; i < jArray.length(); i++) {

				JSONObject json_Data = null;

				json_Data = jArray.getJSONObject(i);
				db.addNews(Integer.parseInt(json_Data.getString("id")),
						json_Data.getString("head"),
						json_Data.getString("desp"),
						dc.getDate(json_Data.getString("date")),
						json_Data.getString("bran"));

			}
			db.close();

		} catch (JSONException e) {

			new Thread() {
				@Override
				public void run() {
					getActivity().runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(
									getActivity().getApplicationContext(),
									"No News Updates.", Toast.LENGTH_SHORT)
									.show();
						}
					});
				}
			}.start();

		}

	}

	private ArrayList<ListModel> GetlistContact() {
		ArrayList<ListModel> contactlist = new ArrayList<ListModel>();
		HashMap<String, String> user;

		in = new Intent[db.getRowCount()];
		j = db.toprow_id();
		for (int i = db.getRowCount(); i > 0 && j > 0; i--, j--) {
			last = j;
			ListModel contact = new ListModel();
			user = db.getNews(j);
			in[i - 1] = new Intent(this.getActivity().getApplicationContext(),DetailView.class);
			in[i - 1].putExtra("head", user.get("headlines"));
			in[i - 1].putExtra("descr", user.get("description"));
			in[i - 1].putExtra("loc", user.get("branch"));
			in[i - 1].putExtra("date", user.get("date"));

			contact.setHeadlines(user.get("headlines"));
			contact.setLocation(user.get("branch"));
			contact.setDate(user.get("date"));
			contactlist.add(contact);

		}

		return contactlist;
	}

}
