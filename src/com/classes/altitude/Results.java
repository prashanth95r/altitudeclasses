package com.classes.altitude;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Results extends Fragment {
	Spinner s;
	EditText rollno;
	Button submit;
	ConnectivityManager cm;
	RelativeLayout rel, rel1;
	String excep;
	DateConvert dc;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_results, container,
				false);

		StrictMode.enableDefaults();
		dc=new DateConvert(this.getActivity());
		rollno = (EditText) rootView.findViewById(R.id.roll);
		submit = (Button) rootView.findViewById(R.id.buttonsubmit);
		rel = (RelativeLayout) rootView.findViewById(R.id.rel1);
		rel1 = (RelativeLayout) rootView.findViewById(R.id.rel2);

		final List<String> list = new ArrayList<String>();
		list.add("Senior IIT-JEE");
		list.add("Senior Medical");
		list.add("Junior IIT-JEE");
		list.add("Junior Medical");

		s = (Spinner) rootView.findViewById(R.id.spinner1);
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
				getActivity(), android.R.layout.simple_spinner_item, list) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View v = super.getView(position, convertView, parent);

				Typeface externalFont = Typeface.createFromAsset(getActivity()
						.getAssets(), "TitilliumWebRegular.ttf");
				((TextView) v).setTypeface(externalFont);

				return v;
			}

			@Override
			public View getDropDownView(int position, View convertView,
					ViewGroup parent) {
				View v = super.getDropDownView(position, convertView, parent);

				Typeface externalFont = Typeface.createFromAsset(getActivity()
						.getAssets(), "TitilliumWebRegular.ttf");
				((TextView) v).setTypeface(externalFont);

				return v;
			}

		};
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		s.setAdapter(dataAdapter);

		Typeface tr=Typeface.createFromAsset(getActivity().getAssets(),"TitilliumWebRegular.ttf");
		rollno.setTypeface(tr);
		submit.setTypeface(tr);
		submit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				cm = (ConnectivityManager) getActivity().getSystemService(
						Context.CONNECTIVITY_SERVICE);
				NetworkInfo activenet = cm.getActiveNetworkInfo();

				int branch = s.getSelectedItemPosition() + 1;
				String roll = rollno.getText().toString();
				if (!roll.equals("")) {

					if (activenet != null
							&& activenet.isConnectedOrConnecting()) {
						rel.setVisibility(8);
						rel1.setVisibility(0);

						JSON_Data json_dates = new JSON_Data();
						Intent i = new Intent(getActivity()
								.getApplicationContext(), ResultsPage.class);
						List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
						params.add(new BasicNameValuePair("tag", "dates"));
						params.add(new BasicNameValuePair("branch", String
								.valueOf(branch)));
						params.add(new BasicNameValuePair("rollno", roll));
						JSONObject jobj = json_dates.getjson(params);
						String dates[] = new String[jobj.length()];
						try {
							for (int j = 0; j < jobj.length(); j++)
								dates[j] = jobj.getString("d" + j);
							
							 excep=dc.getDate(dates[0]);
							 
							 i.putExtra("dates", dates);
								i.putExtra("roll", roll);
								i.putExtra("branch", String.valueOf(branch));
								rel.setVisibility(0);
								rel1.setVisibility(8);
								startActivity(i);
							

						} catch (Exception e) {
							Toast.makeText(
									getActivity().getApplicationContext(),
									"No Details", Toast.LENGTH_LONG).show();
							rel.setVisibility(0);
							rel1.setVisibility(8);
							rollno.setText("");
						}
						
						
					} else {
						Toast.makeText(getActivity(), "No Internet Connection",
								Toast.LENGTH_LONG).show();
					}
				} else {
					Toast.makeText(getActivity(), "Roll No cannot be empty",
							Toast.LENGTH_LONG).show();
				}
			}
		});

		return rootView;
	}
}
