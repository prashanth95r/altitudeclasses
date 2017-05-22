package com.classes.altitude;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

public class Display extends Activity{
	TextView name,model,cond_date,subject1,subject2,subject3,total,rank1,rollno,rt;
	TextView nt,rnt,mot,cot,subj1,subj2,subj3,tot,rat;
	int sub1,sub2,sub3,rank,branch;
	String testcode,student_name,date,rollno1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.results_display2);
		ActionBar a=getActionBar();
        a.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#33b5e5")));
		name=(TextView)findViewById(R.id.name);
		Bundle b=getIntent().getExtras();
		student_name=b.getString("name");
		rollno1=b.getString("roll");
		testcode=b.getString("code");
		branch=b.getInt("branch");
		date=b.getString("date");
		sub1=Integer.parseInt(b.getString("sub1"));
		sub2=Integer.parseInt(b.getString("sub2"));
		sub3=Integer.parseInt(b.getString("sub3"));
		rank=Integer.parseInt(b.getString("rank"));
		
		
		rt=(TextView)findViewById(R.id.resultstitle);
		
		name=(TextView)findViewById(R.id.name);
		rollno=(TextView)findViewById(R.id.rollno);
		model=(TextView)findViewById(R.id.model);
		cond_date=(TextView)findViewById(R.id.cond_date);
		subject1=(TextView)findViewById(R.id.sub1_marks);
		subject2=(TextView)findViewById(R.id.sub2_marks);
		subject3=(TextView)findViewById(R.id.sub3_marks);
		total=(TextView)findViewById(R.id.total);
		rank1=(TextView)findViewById(R.id.rank);
		
		nt=(TextView)findViewById(R.id.nt);
		rnt=(TextView)findViewById(R.id.rnt);
		mot=(TextView)findViewById(R.id.mot);
		cot=(TextView)findViewById(R.id.cot);
		subj1=(TextView)findViewById(R.id.subject1);
		subj2=(TextView)findViewById(R.id.subject2);
		subj3=(TextView)findViewById(R.id.subject3);
		tot=(TextView)findViewById(R.id.tot);
		rat=(TextView)findViewById(R.id.rat);
		
		Typeface tp=Typeface.createFromAsset(this.getAssets(),"TitilliumWebSemiBoldItalic.ttf");
		Typeface tb=Typeface.createFromAsset(this.getAssets(),"TitilliumWebBold.ttf");
		Typeface tr=Typeface.createFromAsset(this.getAssets(),"TitilliumWebRegular.ttf");
		rt.setTypeface(tp);
		nt.setTypeface(tb);
		rnt.setTypeface(tb);
		mot.setTypeface(tb);
		cot.setTypeface(tb);
		subj1.setTypeface(tb);
		subj2.setTypeface(tb);
		subj3.setTypeface(tb);
		tot.setTypeface(tb);
		rat.setTypeface(tb);
		name.setTypeface(tr);
		model.setTypeface(tr);
		cond_date.setTypeface(tr);
		subject1.setTypeface(tr);
		subject2.setTypeface(tr);
		subject3.setTypeface(tr);
		total.setTypeface(tr);
		rank1.setTypeface(tr);
		rollno.setTypeface(tr);
		
		
		if(branch==1)
		{
		subj1.setText("Mathematics");
		subj2.setText("Physics");
		subj3.setText("Chemistry");
		}
		else if(branch==2)
		{
			subj1.setText("Physics");
			subj2.setText("Chemistry");
			subj3.setText("Biology");
			}
		
		
		name.setText(student_name);
		rollno.setText(rollno1);
		model.setText(testcode);
		cond_date.setText(date);
		subject1.setText(String.valueOf(sub1));
		subject2.setText(String.valueOf(sub2));
		subject3.setText(String.valueOf(sub3));
		total.setText(String.valueOf(sub1+sub2+sub3));
		rank1.setText(String.valueOf(rank));
	}

	
}
