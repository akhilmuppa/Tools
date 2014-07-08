package com.osi.tools.utils;

import com.osi.tools.R;
import com.osi.tools.ToolsActivity;

public class FuelStrategy  implements Strategy{


	@Override
	public double Convert(String from, String to, double input) {
	
		
		
		if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.fuelmpgus)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.fuellitper100km)))){
			//if((from.equals("mpgus")) && (to.equals("liter/100km"))){
				double ret = (100 *3.785411784)/(input * 1.609344);

				return ret;
			}
		if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.fuelmpgus)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.fuelkmperliter)))){
			//if((from.equals("mpgus")) && (to.equals("km/liter"))){
				double ret = input * 0.4251437;

				return ret;
			}
		if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.fuelmpgus)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.fuelmpgimpl)))){
			//if((from.equals("mpgus")) && (to.equals("mpgimpl"))){
				double ret = input *  1.20095042;

				return ret;
			}
		if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.fuellitper100km)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.fuelmpgus)))){
			//if((from.equals("liter/100km"")) && (to.equals("mpgus"))){
				double ret = (100 *3.785411784)/(input * 1.609344);

				return ret;
			}
		
		if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.fuellitper100km)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.fuelmpgimpl)))){
			//if((from.equals("liter/100km"")) && (to.equals("mpgimpl"))){
			double ret = input*282.481; 
		
				return ret;
			}
		if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.fuellitper100km)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.fuelkmperliter)))){
			//if((from.equals("liter/100km"")) && (to.equals("km/liter"))){
			double ret = 100/input;

				return ret;
			}
		
		
		if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.fuelmpgimpl)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.fuelmpgus)))){
			//if((from.equals("mpgimpl")) && (to.equals("mpgus"))){
				
			double ret = input *   0.83267384;
				return ret;
			}
		if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.fuelmpgimpl)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.fuellitper100km)))){
			//if((from.equals("mpgimpl")) && (to.equals("liter/100km"))){
			double ret = input*282.481; 

				return ret;
			}
		if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.fuelmpgimpl)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.fuelkmperliter)))){
			//if((from.equals("mpgimpl")) && (to.equals("km/liter"))){
				
			double ret = input*0.354; 
				return ret;
				
			}
		if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.fuelkmperliter)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.fuellitper100km)))){
			//if((from.equals("km/liter")) && (to.equals("liter/100km"))){
			
			double ret = 100/input;
				return ret;
			}
		if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.fuelkmperliter)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.fuelmpgus)))){
			//if((from.equals("km/liter")) && (to.equals("impgus"))){
				
			double ret = input * 2.35215;
				return ret;
			}
		if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.fuelkmperliter)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.fuelmpgimpl)))){
			//if((from.equals("km/liter")) && (to.equals("mpgimpl"))){
				double ret = input * 2.824811;

				return ret;
			}
		
		if(from.equals(to)){
			return input;	
		}
		return 0.0;
	}
	

}
