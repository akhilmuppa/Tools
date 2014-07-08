package com.osi.tools.receivers;

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
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

public class smsbackReceiver extends BroadcastReceiver{


	BlackListTable blt;
	SettingTable st;

	@Override
	public void onReceive(Context context, Intent intent)
	{


		try
		{

			blt = new BlackListTable(context);

			ArrayList<BlackListBean> beanarray = blt.getallDetails();

			blt.close();

			st = new SettingTable(context);

			SettingBean sbean = st.getalldetails();

			st.close();


			Bundle bundle=intent.getExtras();
			Object[] messages=(Object[])bundle.get("pdus");
			SmsMessage[] sms=new SmsMessage[messages.length];

			// Toast.makeText(context, "Hello", 1).show();

			for(int n=0;n<messages.length;n++){
				sms[n]=SmsMessage.createFromPdu((byte[]) messages[n]);
			}

			if(sbean.getBlockopt().equalsIgnoreCase("on"))
			{

				for(SmsMessage msg:sms){

					for(int i=0;i<beanarray.size();i++)
					{

						BlackListBean bean = beanarray.get(i);

						if(bean.getSms().equalsIgnoreCase("true"))
						{

							if(msg.getOriginatingAddress().endsWith(bean.getNumber()))
							{

								if(checkTime(bean.getFrom(), bean.getTo())==false)
								{

								}else
								{

									Log.d("block", "fdf");
									this.abortBroadcast();
									if(bean.getType()==2)
									{
										sendSMS(bean.getNumber(),bean.getTemplate());
									}

								}
							}
						}
					}
				}
			}

		}catch(Exception e)
		{
			e.printStackTrace();
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







}
