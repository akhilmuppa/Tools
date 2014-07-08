package com.osi.tools.receivers;

import com.osi.tools.bean.SmsBean;
import com.osi.tools.tables.SmsDetailsTable;
import com.osi.tools.utils.SmsUtility;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class SmsReceiver extends BroadcastReceiver
{
	 Context context;
	SmsDetailsTable table;
	
	
	@Override
	 public void onReceive(Context context, Intent intent)
	{
	  table=new SmsDetailsTable(context);
		long id = intent.getLongExtra("id",0);
		
		SmsBean bean=table.getdetails(id);
		if(bean!=null)
		{
		SmsUtility smsUtility=new SmsUtility();
		smsUtility.sendSms(bean.getMessage(), bean.getPhonenumber());
		table.deleterecord(id);
		}
		
	   
	 }
	
}
