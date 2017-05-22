package com.classes.altitude;

public class ListModel {
    
    private  String headline="";
    private  String location="";
    private  String date="";
     
    /*********** Set Methods ******************/
     
    public void setHeadlines(String headline)
    {
        this.headline = headline;
    }
     
    public void setLocation(String location)
    {
        this.location = location;
    }
    
    public void setDate(String date)
    {
        this.date = date;
    }
     
     
    public String getHeadlines()
    {
        return this.headline;
    }
     
 
    public String getLocation()
    {
        return this.location;
    } 
    
    public String getDate()
    {
        return this.date;
    } 
}
