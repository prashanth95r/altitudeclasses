package com.classes.altitude;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

public class JSON_Data {
	
	
	
	
	InputStream is=null;
	String json=null;
	JSONObject jobj=null;
	String URL="http://altitudeclasses.org/altitudeapp/getdata.php";
	
	 
	 
	
	 
	 protected JSONObject getjson(List<BasicNameValuePair> params) {
	

    try{
   	 DefaultHttpClient httpclient=new DefaultHttpClient();
   	 HttpPost httppost = new HttpPost(URL);
   	 httppost.setEntity(new UrlEncodedFormEntity(params));
   
   	 HttpResponse httpresponse= httpclient.execute(httppost);
   
   	 HttpEntity httpentity=httpresponse.getEntity();
   	 is=httpentity.getContent();
   	 
    }
    catch (UnsupportedEncodingException e) {
           e.printStackTrace();
       } catch (ClientProtocolException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }
    
try{
	
	BufferedReader br=new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);

   StringBuilder sb= new StringBuilder();
	String line="";
	while((line=br.readLine())!=null){
		
		sb.append(line + "\n");
		
	}
	is.close();
	json=sb.toString();
} catch(Exception e){
	 
e.printStackTrace();
	 
}
	try {
		
		jobj=new JSONObject(json);
		
	
	} catch (JSONException e) {
		
		e.printStackTrace();
		return null;

	}
	
	
	return jobj;
}

	 
	 
	 
	 
	 protected String getjsonarray(List<BasicNameValuePair> params) {
			

		    try{
		   	 DefaultHttpClient httpclient=new DefaultHttpClient();
		   	 HttpPost httppost = new HttpPost(URL);
		   	 httppost.setEntity(new UrlEncodedFormEntity(params));
		   
		   	 HttpResponse httpresponse= httpclient.execute(httppost);
		   
		   	 HttpEntity httpentity=httpresponse.getEntity();
		   	 is=httpentity.getContent();
		   	 
		    }
		    catch (UnsupportedEncodingException e) {
		           e.printStackTrace();
		       } catch (ClientProtocolException e) {
		           e.printStackTrace();
		       } catch (IOException e) {
		           e.printStackTrace();
		       }
		    
		try{
			
			BufferedReader br=new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);

		   StringBuilder sb= new StringBuilder();
			String line="";
			while((line=br.readLine())!=null){
				
				sb.append(line + "\n");
				
			}
			is.close();
			json=sb.toString();
			
		} catch(Exception e){
			 
		e.printStackTrace();
			 
		}
			
			
			
			return json;
		}




	

	
		
	}
	

