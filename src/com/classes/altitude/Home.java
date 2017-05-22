package com.classes.altitude;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Home extends Fragment {
	TextView tv,tv_service1,tv_ser2;
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	 
	        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
	        
	        tv=(TextView)rootView.findViewById(R.id.home1);
	        tv_service1=(TextView)rootView.findViewById(R.id.services);
	        tv_ser2=(TextView)rootView.findViewById(R.id.services_matter);
	        
	        
	        Typeface tp=Typeface.createFromAsset(getActivity().getAssets(),"TitilliumWebRegular.ttf");
	        Typeface tb=Typeface.createFromAsset(getActivity().getAssets(),"TitilliumWebSemiBold.ttf");
	        tv.setTypeface(tp);
	        tv_service1.setTypeface(tb);
	        tv_ser2.setTypeface(tp);
	         
	        return rootView;
	    }

}

