package com.osi.tools.receivers;

import com.osi.tools.tables.ModeTable;
import com.osi.tools.utils.AudioRecorder;
import com.osi.tools.utils.CalendarUtility;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class EndReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {


		ModeTable mtaTable = new ModeTable(context);

		int mode = mtaTable.getdetails();
 
		mtaTable.close();
		
		CalendarUtility.setringermode(context,mode);
		
           AudioRecorder.StopRecording();

	}

}
