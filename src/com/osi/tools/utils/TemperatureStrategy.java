/*
  	Copyright (c) 2012 Somenath Mukhopadhyay.
 	All rights reserved.
 */

package com.osi.tools.utils;

import com.osi.tools.R;
import com.osi.tools.ToolsActivity;

public class TemperatureStrategy implements Strategy {

	@Override
	public double Convert(String from, String to, double input) {
		// TODO Auto-generated method stub
		
		if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.temperatureunitc)) && to.equals((ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.temperatureunitf))))){
			double ret = (input*9/5)+32;
			return ret;
		}
	
		if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.temperatureunitf)) && to.equals((ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.temperatureunitc))))){
			double ret = (input-32)*5/9;
			return ret;
		}

	if(from.equals(to)){
		return input;	
	}
	return 0.0;
	}

}
