package com.classes.altitude;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ResultsAdapter extends ArrayAdapter<String> {

	String[] values;
	private final Context context;
	Typeface tr;

	public ResultsAdapter(Context context, String[] values) {
		super(context, R.layout.dates_list_item, values);
		this.context = context;
		this.values = values;
		tr = Typeface.createFromAsset(context.getAssets(),
				"TitilliumWebRegular.ttf");
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View resultView = convertView;
		TextView t = null;

		if (resultView == null) {
			LayoutInflater inflater = LayoutInflater.from(context);
			resultView = inflater.inflate(R.layout.dates_list_item, null);
			t = (TextView) resultView.findViewById(R.id.tv1);
			t.setTypeface(tr);

		} else {
			t = (TextView) resultView.findViewById(R.id.tv1);
		}
		t.setText(values[position]);
		return resultView;
	}
}
