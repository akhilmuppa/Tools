package com.osi.tools.receivers;

import java.lang.reflect.Method;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;








import com.osi.tools.bean.BlackListBean;
import com.osi.tools.bean.SettingBean;
import com.osi.tools.tables.BlackListTable;
import com.osi.tools.tables.SettingTable;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.CallLog;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class CallReceiver extends BroadcastReceiver{


	BlackListTable blt;
	SettingTable st;
	private String incomingnumber;

	@Override
	public void onReceive(Context context, Intent intent) {

		blt = new BlackListTable(context);

		ArrayList<BlackListBean> beanarray = blt.getallDetails();

		blt.close();

		st = new SettingTable(context);

		SettingBean sbean = st.getalldetails();

		st.close();

		Bundle myBundle = intent.getExtras();
		if (myBundle != null)
		{
			System.out.println("--------Not null-----");
			try 
			{
				if (intent.getAction().equals("android.intent.action.PHONE_STATE")) 
				{ 
					String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
					System.out.println("--------in state-----");
					if (state.equals(TelephonyManager.EXTRA_STATE_RINGING))
					{ 
						// Incoming call
						String incomingNumber =intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
						System.out.println("--------------my number---------"+incomingNumber);


						// this is main section of the code,. could also be use for particular number.
						// Get the boring old TelephonyManager.
						TelephonyManager telephonyManager =(TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

						// Get the getITelephony() method                        
						Class<?> classTelephony = Class.forName(telephonyManager.getClass().getName());
						Method methodGetITelephony = classTelephony.getDeclaredMethod("getITelephony");

						// Ignore that the method is supposed to be private                       
						methodGetITelephony.setAccessible(true);

						// Invoke getITelephony() to get the ITelephony interface
						Object telephonyInterface = methodGetITelephony.invoke(telephonyManager);

						// Get the endCall method from ITelephony
						Class<?> telephonyInterfaceClass = Class.forName(telephonyInterface.getClass().getName());
						Method methodEndCall = telephonyInterfaceClass.getDeclaredMethod("endCall");


						if(sbean.getBlockopt().equalsIgnoreCase("on"))
						{

							if(sbean.getCallopt().equalsIgnoreCase("true"))
							{
								methodEndCall.invoke(telephonyInterface);
							}else
							{

								for (int i = 0; i < beanarray.size(); i++) {

									BlackListBean bean = beanarray.get(i);

									if(bean.getCall().equalsIgnoreCase("true"))
									{

										if(incomingNumber.endsWith(bean.getNumber())){


											if(checkTime(bean.getFrom(), bean.getTo())==false)
											{

											}else
											{


												methodEndCall.invoke(telephonyInterface);


												if(bean.getType()==2)
												{
													sendSMS(bean.getNumber(),bean.getTemplate());
												}
												deletecallLogs(context, incomingNumber);

											}

										}


									}

								}
							}

						}


					}

				}
			}
			catch (Exception ex) 
			{ // Many things can go wrong with reflection calls
				ex.printStackTrace();
			}
		}

	}


	public boolean checkTime(String from,String to)
	{

		boolean check = false;

		final Calendar c = Calendar.getInstance();


		Time ct = new Time(c.getTime().getTime());

		if(from.trim().length()==0 && to.trim().length()==0)
		{
			return true;
		}


		try
		{
			SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd",Locale.getDefault());
			Calendar cal = Calendar.getInstance();
			String datest = formatter1.format(cal.getTime());

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm",Locale.getDefault());	
			java.util.Date givendate =  formatter.parse(datest+" "+from.trim());
			java.util.Date givendate1 =  formatter.parse(datest+" "+to.trim());

			Time gt1 = new Time(givendate.getTime());
			Time gt2 = new Time(givendate1.getTime());

			if(ct.after(gt1) && ct.before(gt2))
			{

				check = true;

			}else{
				check = false;
			}


		}catch(Exception e)
		{
			e.printStackTrace();
		}

		return check;
	}

	public void sendSMS(String number,String message) {

		try{

			if(number.trim().length()>0 && message.trim().length()>0)
			{


				SmsManager smsManager = SmsManager.getDefault();
				smsManager.sendTextMessage(number, null, message, null, null);

			}

		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}


	public void deletecallLogs(Context ct,String number)
	{
		Uri   delUri = Uri.withAppendedPath(CallLog.Calls.CONTENT_URI,""); 
		Cursor cursor = ct.getContentResolver().query(delUri,null,CallLog.Calls.NUMBER+"=?",new String[]{number},null);

		int id = 0;
		if(cursor.moveToLast())
		{

			id =cursor.getInt(cursor.getColumnIndex(BaseColumns._ID));
			String name = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));

			//id =cursor.getString(cursor.getColumnIndex(CallLog.Calls._ID));

			Toast.makeText(ct,id+"",Toast.LENGTH_SHORT).show();
			Toast.makeText(ct,name,Toast.LENGTH_SHORT).show();

		}

		String iid = String.valueOf(id);

		ct.getContentResolver().delete(delUri, BaseColumns._ID+"="+iid, null);

	}

}
