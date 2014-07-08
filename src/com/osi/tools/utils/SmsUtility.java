package com.osi.tools.utils;

import android.telephony.SmsManager;

public class SmsUtility {


	
	public void sendSms(String message,String number)
	{
	
		 try {      
			 
	          SmsManager smsManager = SmsManager.getDefault();
	          smsManager.sendTextMessage(number, null, message, null, null);    
	         
	           } catch (Exception ex) {
	        
	          ex.printStackTrace();
	           } 
		
	}
	
}
