package com.osi.tools.receivers;


import com.osi.tools.fragments.CalendarEventfragment;
import com.osi.tools.tables.ModeTable;
import com.osi.tools.utils.AudioRecorder;
import com.osi.tools.utils.CalendarUtility;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StartReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {


		int rcode = intent.getIntExtra("id",0);
		long endtime = intent.getLongExtra("endtime",0);
		String eventname = intent.getStringExtra("eventname");
		
		ModeTable mtaTable = new ModeTable(context);
		
		int mode = mtaTable.getusermode();
	    String audio =  mtaTable.getaudiomode();
		
		mtaTable.close();
		CalendarUtility.setringermode(context, mode);
		CalendarEventfragment.seEndalaram(context, rcode, endtime);
		if(audio.equalsIgnoreCase("true"))
		{
		AudioRecorder.StartRecording(eventname+".3gpp",context);
		}
		//CalendarUtility.silentmode(context);
		

	}

}
