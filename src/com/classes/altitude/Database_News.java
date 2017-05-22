package com.classes.altitude;


import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class Database_News extends SQLiteOpenHelper {
	Context c;
   
	private static Database_News sinstance; {
		
	}
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "altitudedb";
	private static final String TABLE_LOGIN = "altitudenews";
	private static final String SERVER_KEY_ID = "id";
	private static final String KEY_HEADLINES = "headlines";
	private static final String KEY_DESCRIPTION = "description";
	private static final String KEY_BRANCH = "branch";
	private static final String KEY_DATE = "date";
	
	
	
	public static Database_News getInstance(Context context) {

	   
	    if (sinstance == null) {
	      sinstance = new Database_News(context.getApplicationContext());
	    }
	    return sinstance;
	  }
	
	public Database_News(Context con) {
		super(con ,DATABASE_NAME , null, DATABASE_VERSION);
		this.c=con;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_LOGIN + "( "
				+ SERVER_KEY_ID + " INTEGER  ,"
				+ KEY_HEADLINES + " TEXT," 
				+ KEY_DESCRIPTION + " TEXT,"
				+ KEY_BRANCH + " TEXT,"
				+ KEY_DATE + " TEXT )";
		
		db.execSQL(CREATE_LOGIN_TABLE);

	}

	@Override
	 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
              
		db.execSQL("DROP table if exists"+TABLE_LOGIN);
		onCreate(db);
		
	} 

	public void addNews(int id,String headlines, String description,String date,String branch) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(SERVER_KEY_ID,id);
		values.put(KEY_HEADLINES, headlines);
		values.put(KEY_DESCRIPTION, description);
		values.put(KEY_BRANCH, branch);
		values.put(KEY_DATE, date);
		
		db.insert(TABLE_LOGIN, null, values);

		db.close();
	}
	

	public HashMap<String, String> getNews(int server_id) {
		
	
		HashMap<String, String> user = new HashMap<String, String>();
		String selectQuery = "SELECT  * FROM " + TABLE_LOGIN + " WHERE id=" + server_id;

		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.rawQuery(selectQuery, null);
		
		
		cursor.moveToFirst();
		
		
		try{
	 
		user.put("headlines", cursor.getString(1));
		user.put("description", cursor.getString(2));
    	user.put("branch", cursor.getString(3));
    	user.put("date", cursor.getString(4));
    	
    
		cursor.close();
		db.close();
		}catch(CursorIndexOutOfBoundsException e){
		}
		
		
		
		return user;
		
		
	}

	public int getRowCount() {
		
		
		String countQuery = "SELECT  * FROM " + TABLE_LOGIN;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		
		int rowCount = cursor.getCount();
		
	
		cursor.close();
	
		db.close();

		return rowCount;
	}
	
	
	public void resetTables(){
        SQLiteDatabase db = this.getWritableDatabase();
 //     String query="DELETE FROM "+ TABLE_LOGIN;
        db.delete(TABLE_LOGIN,null, null);
    //    db.rawQuery(query, null);
        db.close();
        
	}

	public int toprow_id() {
		
		int highest_server_id=0;
		
		
		String selectQuery = "SELECT  * FROM " + TABLE_LOGIN +" ORDER BY id desc" ;

		SQLiteDatabase db = this.getReadableDatabase();
		

		Cursor cursor = db.rawQuery(selectQuery, null);
		
		cursor.moveToFirst();
		try
		{
		highest_server_id=cursor.getInt(0);
		}
		catch(Exception e)
		{
			Toast.makeText(c, "No News Updates", Toast.LENGTH_SHORT).show();
		}
		
		
		cursor.close();
		db.close();
		return highest_server_id;
		
	}
	
  public int Server_lastrow_id() {
		
		int top1=0;
		
		
		String selectQuery = "SELECT  * FROM " + TABLE_LOGIN ;

		SQLiteDatabase db = this.getReadableDatabase();
		

		Cursor cursor = db.rawQuery(selectQuery, null);
		
		cursor.moveToLast();
		
		top1=cursor.getInt(0);
		
		cursor.close();
		db.close();
		return top1;
		
	}
  public int lastrow_id() {
		
		int least_server_id=0;
		
		
		String selectQuery = "SELECT  * FROM " + TABLE_LOGIN +" ORDER BY id desc" ;

		SQLiteDatabase db = this.getReadableDatabase();
		

		Cursor cursor = db.rawQuery(selectQuery, null);
		
		cursor.moveToLast();
		
		least_server_id=cursor.getInt(0);
		
		cursor.close();
		db.close();
		return least_server_id;
		
		
	}
	
	
	public Cursor rowIds(String branch2)
	{
		String selectQuery = "SELECT id FROM " + TABLE_LOGIN +" WHERE branch="+branch2 ;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor=db.rawQuery(selectQuery, null);
		return cursor;
		
	}

}
