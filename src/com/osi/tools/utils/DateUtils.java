package com.osi.tools.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateUtils {


	public DateUtils()
	{

	}


	public String getpresentDate()
	{

		SimpleDateFormat formatter1 = new SimpleDateFormat("MMM dd yyyy",Locale.getDefault());
		Calendar cal = Calendar.getInstance();
		String datest = formatter1.format(cal.getTime());

		return datest;
	}

	public String gettime()
	{

		Calendar  cal = Calendar.getInstance();
		int  hour = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE);

		String val = hour+""+min;

		return val;
	}

	public String gettimeval()
	{

		Calendar  cal = Calendar.getInstance();
		int  hour = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE);

		String val = hour+":"+min;

		return val;
	}

}
