package com.osi.tools.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.osi.tools.R;
import com.osi.tools.ToolsActivity;

public class DigitalStoragesSrategy implements Strategy { NumberFormat formatter = new DecimalFormat("0.####E0");

@Override
public double Convert(String from, String to, double input) {
	if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitbyte)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitkilobyte)))){
		//if((from.equals("Byte")) && (to.equals("Kilobyte"))){
			double ret = input/1024;
			ret= Double.valueOf(formatter.format(ret)).doubleValue();
			return ret;
		}

if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitbyte)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitmegabyte)))){
	//if((from.equals("Byte")) && (to.equals("megabyte"))){
		double ret = input/(1024*1024);
		ret= Double.valueOf(formatter.format(ret)).doubleValue();

		return ret;
	}
if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitbyte)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitgigabyte)))){
	//if((from.equals("Byte")) && (to.equals("gigabyte"))){
		double ret = input/(1024*1024*1024);
		ret= Double.valueOf(formatter.format(ret)).doubleValue();

		return ret;
	}
if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitbyte)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitterabyte)))){
	//if((from.equals("Byte")) && (to.equals("terabyte"))){
	 

		double ret = ((((input) / 1024) /1024 / 1024) /1024);
		ret= Double.valueOf(formatter.format(ret)).doubleValue();
		return ret;
	}
if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitbyte)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitpetabyte)))){
	//if((from.equals("Byte")) && (to.equals("petabyte"))){
   

		double ret = ( ((((input) / 1024) /1024 / 1024) /1024)/1024);
	   
		ret= Double.valueOf(formatter.format(ret)).doubleValue();
		return  ret;
	}

if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitkilobyte)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitbyte)))){
	//if((from.equals("kiloByte")) && (to.equals("byte"))){
		double ret = input*1024;
		return ret;
	}

if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitkilobyte)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitmegabyte)))){
	//if((from.equals("kiloByte")) && (to.equals("megabyte"))){
		double ret = input/1024;
		ret= Double.valueOf(formatter.format(ret)).doubleValue();
		return ret;
	}

if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitkilobyte)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitgigabyte)))){
	//if((from.equals("kiloByte")) && (to.equals("gigabyte"))){
	double ret = input/(1024*1024);
	ret= Double.valueOf(formatter.format(ret)).doubleValue();

	return ret;
	}
if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitkilobyte)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitterabyte)))){
	//if((from.equals("kiloByte")) && (to.equals("terabyte"))){
		double ret = input/(1024*1024*1024);
		ret= Double.valueOf(formatter.format(ret)).doubleValue();

		return ret;
	}
if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitkilobyte)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitpetabyte)))){
	//if((from.equals("kiloByte")) && (to.equals("petabyte"))){
	double ret = ((((input) / 1024) /1024 / 1024) /1024);
	ret= Double.valueOf(formatter.format(ret)).doubleValue();
		return ret;
	}


if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitmegabyte)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitbyte)))){
	//if((from.equals("megaByte")) && (to.equals("byte"))){
		double ret = input*1024*1024;
		return ret;
	}

if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitmegabyte)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitkilobyte)))){
	//if((from.equals("megaByte")) && (to.equals("kilobyte"))){
		double ret = input*1024;
		return ret;
	}

if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitmegabyte)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitgigabyte)))){
	//if((from.equals("MegaByte")) && (to.equals("gigabyte"))){
	double ret = input/(1024);
	ret= Double.valueOf(formatter.format(ret)).doubleValue();
	return ret;
	}
if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitmegabyte)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitterabyte)))){
	//if((from.equals("MegaByte")) && (to.equals("terabyte"))){
		double ret = input/(1024*1024);
		ret= Double.valueOf(formatter.format(ret)).doubleValue();

		return ret;
	}
if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitmegabyte)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitpetabyte)))){
	//if((from.equals("MegaByte")) && (to.equals("petabyte"))){
		double ret = input/(1024*1024*1024);
		ret= Double.valueOf(formatter.format(ret)).doubleValue();

		return ret;
	}
if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitgigabyte)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitbyte)))){
	//if((from.equals("gigaByte")) && (to.equals("byte"))){
		double ret = input*1024*1024*1024;
		ret= Double.valueOf(formatter.format(ret)).doubleValue();
		return ret;
	}

if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitgigabyte)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitkilobyte)))){
	//if((from.equals("gigaByte")) && (to.equals("kilobyte"))){
		double ret = input*1024*1024;

		return ret;
	}

if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitgigabyte)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitmegabyte)))){
	//if((from.equals("GigaByte")) && (to.equals("Megabyte"))){
	double ret = input*1024;
	return ret;
	}
if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitgigabyte)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitterabyte)))){
	//if((from.equals("GigaByte")) && (to.equals("terabyte"))){
		double ret = input/(1024);
		ret= Double.valueOf(formatter.format(ret)).doubleValue();
		return ret;
	}
if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitgigabyte)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitpetabyte)))){
	//if((from.equals("GigaByte")) && (to.equals("petabyte"))){
		double ret = input/(1024*1024);
		ret= Double.valueOf(formatter.format(ret)).doubleValue();
		return ret;
	}

if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitterabyte)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitbyte)))){
	//if((from.equals("teraByte")) && (to.equals("byte"))){
		double ret = input*1024*1024*1024*1024;
		ret= Double.valueOf(formatter.format(ret)).doubleValue();

		return ret;
	}

if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitterabyte)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitkilobyte)))){
	//if((from.equals("teraByte")) && (to.equals("kilobyte"))){
		double ret = input*1024*1024*1024;
		ret= Double.valueOf(formatter.format(ret)).doubleValue();
		return ret;
	}

if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitterabyte)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitmegabyte)))){
	//if((from.equals("teraByte")) && (to.equals("Megabyte"))){
	double ret = input*1024*1024;

	return ret;
	}
if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitterabyte)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitgigabyte)))){
	//if((from.equals("teraByte")) && (to.equals("gigabyte"))){
		double ret = input*1024;
		return ret;
	}
if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitterabyte)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitpetabyte)))){
	//if((from.equals("teraByte")) && (to.equals("petabyte"))){
		double ret = input/1024;
		ret= Double.valueOf(formatter.format(ret)).doubleValue();
		return ret;
	}

if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitpetabyte)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitbyte)))){
	//if((from.equals("petabyte")) && (to.equals("byte"))){
		double ret = input*1024*1024*1024*1024*1024;
		ret= Double.valueOf(formatter.format(ret)).doubleValue();
		return ret;
	}

if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitpetabyte)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitkilobyte)))){
	//if((from.equals("petabyte")) && (to.equals("kilobyte"))){
		double ret = input*1024*1024*1024*1024;
		ret= Double.valueOf(formatter.format(ret)).doubleValue();
		return ret;
	}

if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitpetabyte)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitmegabyte)))){
	//if((from.equals("petabyte")) && (to.equals("Megabyte"))){
	double ret = input*1024*1024*1024;
	ret= Double.valueOf(formatter.format(ret)).doubleValue();

	return ret;
	}
if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitpetabyte)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitgigabyte)))){
	//if((from.equals("petabyte")) && (to.equals("gigabyte"))){
		double ret = input*1024*1024;
		return ret;
	}
if((from.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitpetabyte)) && to.equals(ToolsActivity.getInstance().getApplicationContext().getResources().getString(R.string.unitterabyte)))){
	//if((from.equals("petabyte")) && (to.equals("petabyte"))){
		double ret = input*1024;
		
		return ret;
	}



if(from.equals(to)){
	return input;	
}
return 0.0;

}
}
