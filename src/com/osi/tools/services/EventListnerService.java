package com.osi.tools.services;



import java.util.Calendar;

import com.osi.tools.fragments.CalendarEventfragment;
import com.osi.tools.utils.CalendarUtility;

import android.app.Service;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;


public class EventListnerService extends Service{

	@Override
	public IBinder onBind(Intent intent) {

		return null;
	}

	@Override
	public void onCreate() 
	{
		// TODO Auto-generated method stub  
		super.onCreate();
	}

	@SuppressWarnings("static-access")
	@Override
	public void onStart(Intent intent, int startId)
	{
		super.onStart(intent, startId);


		//    getContentResolver().notifyChange(Uri.parse("content://com.android.calendar/events"),cob);
		getContentResolver().registerContentObserver(Uri.parse("content://com.android.calendar/events"),true,cob);



	}
	
	ContentObserver cob = new ContentObserver(new Handler()) {

		@Override
		public boolean deliverSelfNotifications() {
			return true;
		}

		@Override
		public void onChange(boolean selfChange) {
			super.onChange(selfChange);





			Calendar c = Calendar.getInstance();
			long l = c.getTimeInMillis();

			CalendarEventfragment.canAlarms(getApplicationContext());

			CalendarUtility.deletEvents(getApplicationContext());

			CalendarUtility.readEvents(getApplicationContext(), l);


			CalendarEventfragment.seAlarms(getApplicationContext());

		}


	};


	@Override
	public void onDestroy() 
	{

		super.onDestroy();
	}

}
