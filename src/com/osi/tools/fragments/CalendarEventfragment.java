package com.osi.tools.fragments;

import java.util.ArrayList;
import java.util.Calendar;

import com.osi.tools.R;
import com.osi.tools.bean.EventBean;
import com.osi.tools.receivers.EndReceiver;
import com.osi.tools.receivers.StartReceiver;
import com.osi.tools.services.EventListnerService;
import com.osi.tools.tables.EventTable;
import com.osi.tools.tables.ModeTable;
import com.osi.tools.utils.CalendarUtility;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class CalendarEventfragment extends Fragment{
	
	

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}
	
	
	public Button silentputbtn;
	public EventTable etable;
	public ModeTable mtable;
	public Spinner usermodespinner;
	public CheckBox audiochk;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_calendarevents, container, false);
		
		initialzeVariables(view);
		
		mtable = new ModeTable(getActivity());
		String btnmode  = mtable.getbtnmode();
		usermodespinner.setSelection(mtable.getusermode());
		
		String audiom = mtable.getaudiomode();
		mtable.close();
		if(btnmode.equalsIgnoreCase("true"))
		{
		
			silentputbtn.setBackgroundResource(R.drawable.roundbutton);
			
			
			
		}else
		{
			silentputbtn.setBackgroundResource(R.drawable.roundbuttonoff);
			
		}
		
		audiochk.setChecked(Boolean.parseBoolean(audiom));
		
		audiochk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				mtable = new ModeTable(getActivity());
				mtable.updateaudiomode(1,String.valueOf(audiochk.isChecked()));
				mtable.close();
				
			}
		});
		
		usermodespinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				
				mtable = new ModeTable(getActivity());
				  mtable.updateusermode(1,usermodespinner.getSelectedItemPosition());
					mtable.close();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		silentputbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				
				mtable = new ModeTable(getActivity());
				String btnmode  = mtable.getbtnmode();
		     // mtable.updateusermode(1,usermodespinner.getSelectedItemPosition());
				mtable.close();
				if(btnmode.equalsIgnoreCase("true"))
				{
					silentputbtn.setBackgroundResource(R.drawable.roundbuttonoff);
					CalendarEventfragment.canAlarms(getActivity());

					CalendarUtility.deletEvents(getActivity());
					mtable = new ModeTable(getActivity());
					mtable.updatebtnmode(1,"false");
					mtable.close();
				}else
				{
					mtable = new ModeTable(getActivity());
					mtable.updatebtnmode(1,"true");
					mtable.close();
					
					
					silentputbtn.setBackgroundResource(R.drawable.roundbutton);
					seAlarms(getActivity());
					
					 Intent service1 = new Intent(getActivity(),EventListnerService.class);
				 	   
				 	   getActivity().startService(service1);
				}
				
			
			}
		});
		
		
		return view;
	}
	
	public void initialzeVariables(View view)
	{
		silentputbtn = (Button)view.findViewById(R.id.btnsilent);
		usermodespinner = (Spinner)view.findViewById(R.id.usermodespinner);
		audiochk = (CheckBox)view.findViewById(R.id.audiochk);
		
	}
	
	
		
		 public static void seStartalaram(Context con,int requestcode,long ttime,long ntime,String eventnam)
			{
				
		  	    
		  	    Intent myIntent = new Intent(con,StartReceiver.class);
		  	    myIntent.putExtra("id", requestcode);
		  	    myIntent.putExtra("endtime",ntime);
		  	    myIntent.putExtra("eventname",eventnam);
		  	    PendingIntent pendingIntent = PendingIntent.getBroadcast(con, requestcode, myIntent,0);
		  	    
		  	    
		  	    
		  	    android.app.AlarmManager alarmManager = (android.app.AlarmManager)con.getSystemService("alarm");
		  	 
		  		alarmManager.set(android.app.AlarmManager.RTC_WAKEUP,ttime,pendingIntent);
		     	
			}
			
			public static void seEndalaram(Context con,int requestcode,long ttime)
			{
				
		  	    
		  	    Intent myIntent = new Intent(con,EndReceiver.class);
		  	    PendingIntent pendingIntent = PendingIntent.getBroadcast(con, requestcode, myIntent,0);
		  	    
		  	    android.app.AlarmManager alarmManager = (android.app.AlarmManager)con.getSystemService("alarm");
		  	 
		  		alarmManager.set(android.app.AlarmManager.RTC_WAKEUP,ttime,pendingIntent);
		  		
		     	
			}
		
			
			public static void canAlarms(Context cot)
			{
				Calendar c = Calendar.getInstance();
				long l = c.getTimeInMillis();
				
				CalendarUtility.readEvents(cot,l);
				
				EventTable etable = new EventTable(cot);
				
				ArrayList<EventBean> arraybean = etable.getallDetails();
				
			
				
				etable.close();
				
				for(int i=0;i<arraybean.size();i++)
				{
					
					
					EventBean bb = arraybean.get(i);
					
					CancelStartAlarm(cot,bb.getId(), Long.valueOf(bb.getStartdate()));
					
					CancelEndAlarm(cot,bb.getId(), Long.valueOf(bb.getEnddate()));
					
					
				}
				

			}

			public static void CancelStartAlarm(Context con,int requestcode,long ttime)
			{
				
				 Intent myIntent = new Intent(con,StartReceiver.class);
			  	    PendingIntent pendingIntent = PendingIntent.getBroadcast(con, requestcode, myIntent,0);
			  	    
			  	    AlarmManager alarmManager = (AlarmManager)con.getSystemService("alarm");
			  	 
			  		alarmManager.cancel(pendingIntent);
				
			}
			
			public static void CancelEndAlarm(Context con,int requestcode,long ttime)
			{
				
				 Intent myIntent = new Intent(con,EndReceiver.class);
			  	    PendingIntent pendingIntent = PendingIntent.getBroadcast(con, requestcode, myIntent,0);
			  	    
			  	    AlarmManager alarmManager = (AlarmManager)con.getSystemService("alarm");
			  	 
			  		alarmManager.cancel(pendingIntent);
				
			}
			
			
		
		public static void seAlarms(Context cot)
		{
			Calendar c = Calendar.getInstance();
			long l = c.getTimeInMillis();
			
			CalendarUtility.readEvents(cot,l);
			
			EventTable etable = new EventTable(cot);
			
			ArrayList<EventBean> arraybean = etable.getallDetails();
			
			
			
			etable.close();
			
			CalendarUtility.getringermode(cot);
			
			for(int i=0;i<arraybean.size();i++)
			{
				
				EventBean bb = arraybean.get(i);
				
				seStartalaram(cot,bb.getId(), Long.valueOf(bb.getStartdate()),Long.valueOf(bb.getEnddate()),bb.getNameofEvent());
				
				//seEndalaram(cot,bb.getId(), Long.valueOf(bb.getEnddate()));
				
				
				
			}
			

		}

		
	
		public  long getcurrenttime()
		{
			
			Calendar c = Calendar.getInstance();
			long l = c.getTimeInMillis();
			
			return l;
		}

	

}
