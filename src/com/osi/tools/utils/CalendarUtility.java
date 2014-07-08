package com.osi.tools.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import com.osi.tools.ToolsActivity;
import com.osi.tools.bean.EventBean;
import com.osi.tools.tables.EventTable;
import com.osi.tools.tables.ModeTable;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.media.AudioManager;
import android.net.Uri;
import android.util.Log;

public class CalendarUtility {

	ToolsActivity cactvty;
	
	public CalendarUtility(ToolsActivity actvty)
	{
		
		this.cactvty = actvty;
	}
	
	
	
	 public static ArrayList<EventBean> readCalendarEvents(Context context,long t) {
	    	
		    
	    	ArrayList<EventBean> arraybean = new ArrayList<EventBean>();
	    	
	        Cursor cursor = context.getContentResolver()
	                .query(
	                        Uri.parse("content://com.android.calendar/events"),
	                        new String[] { "calendar_id", "title", "description",
	                                "dtstart", "dtend", "eventLocation" },null,
	                        null, null);
	        
	        
	        Date pd = new Date(t);
	        Log.d("dds",pd.toString());
	        
	        if(cursor.moveToFirst())
	        {
	        	
	        	do
	        	{
	        		Date gd = new Date(Long.parseLong(cursor.getString(3)));
	        		Date nd = new Date(Long.parseLong(cursor.getString(4)));
	        		
	        		
	        		if(gd.after(pd))
	        		{
	        			Log.d("dds",gd.toString());	
	        			Log.d("ndds",nd.toString());	
	        			
	        		EventBean b = new EventBean();
	        		
	        		b.setNameofEvent(cursor.getString(1));
	        		b.setStartdate(cursor.getString(3));
	        		b.setEnddate(cursor.getString(4));
	        		b.setDescription(cursor.getString(2));
	        		
	        		arraybean.add(b);
	        		}
	        		
	        	}while(cursor.moveToNext());
	        	
	        	
	        	
	        }
	        
	        
	      
	        return arraybean;
	    }
	 
	 public static void readEvents(Context context,long t) {
	    	
		    
	    	EventTable etable = new EventTable(context);
	    	
	    	int count = etable.getcount();
	    	
	        Cursor cursor = context.getContentResolver()
	                .query(
	                        Uri.parse("content://com.android.calendar/events"),
	                        new String[] { "calendar_id", "title", "description",
	                                "dtstart", "dtend", "eventLocation" },null,
	                        null, null);
	        
	        
	        Date pd = new Date(t);
	       
	        
	        if(cursor.moveToFirst())
	        {
	        	
	        	do
	        	{
	        		
	        		if(cursor.getString(3) ==null|| cursor.getString(4) ==null || cursor.getString(1) ==null)
	        		{
	        			continue;
	        		}
	        		
	        		Date gd = new Date(Long.parseLong(cursor.getString(3)));
	        		Date nd = new Date(Long.parseLong(cursor.getString(4)));
	        		
	        		
	        		if(gd.after(pd))
	        		{
	        			
	        				
	        		
	        			int hours = getElapsedTimeInHours(nd,gd);
	        			if(hours<20)
	        			{
	        				Log.d("ndds","h "+hours);
	        			if(count<=0)
	        			{
	        				
	        		etable.insertlastupdate(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));	
	        			
	        			}
	        		
	        			}
	        		
	        		}
	        		
	        	}while(cursor.moveToNext());
	        	
	        	
	        	
	        }
	        
	        etable.close();
	      
	       
	    }
	 
	 
	 public static int getElapsedTimeInHours(Date start,Date end){
		    int hours=(int)(start.getTime()-end.getTime())/(1000*60*60);
		    return hours;
		}

	 
	    
	 public static void deletEvents(Context context)
	 {
		 
		 EventTable etable = new EventTable(context);
		 
		 ArrayList<EventBean> arraybean = etable.getallDetails();
		 
		 for(int i=0;i<arraybean.size();i++)
		 {
			 
			 EventBean bean = arraybean.get(i);
			 
			 etable.deleteEventrow(bean.getId());
			 
		 }
		 
		 etable.close();
		 
	 }
	 
	 

	    public static String getDate(long milliSeconds) {
	        SimpleDateFormat formatter = new SimpleDateFormat(
	                "dd/MM/yyyy hh:mm:ss a");
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTimeInMillis(milliSeconds);
	        return formatter.format(calendar.getTime());
	    }
	    
	    public static void setringermode(Context context,int mode)
	    {
	    	
	    	AudioManager am;
	    	am= (AudioManager) ((ContextWrapper) context).getBaseContext().getSystemService(Context.AUDIO_SERVICE);

	    	    	am.setRingerMode(mode);
	    	
	    /*	//For Normal mode
	    	am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);

	    	//For Silent mode
	    	am.setRingerMode(AudioManager.RINGER_MODE_SILENT);

	    	//For Vibrate mode
	    	am.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);*/
	    	
	    }
	    
	    public static void silentmode(Context con)
	    {
	    	AudioManager am;
	    	am= (AudioManager) ((ContextWrapper) con).getBaseContext().getSystemService(Context.AUDIO_SERVICE);

	    	
	    	am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
	    	
	    	
	    }
	    
	    public static void getringermode(Context con)
	    {
	    	
	    	AudioManager am;
	    	am= (AudioManager) ((ContextWrapper) con).getBaseContext().getSystemService(Context.AUDIO_SERVICE);

	    	
	    	int mode = am.getRingerMode();
	    	
	    	ModeTable mtable = new ModeTable(con);
	    	
	    	int c = mtable.getmodecount();
	    	
	    	if(c==0)
	    	{
	    		mtable.insertmode(mode);
	    	}else if(c>0)
	    	{
	    		mtable.updatemode(1, mode);
	    	}
	    	
	    	mtable.close();
	    	
	    	
	    }
		
}
