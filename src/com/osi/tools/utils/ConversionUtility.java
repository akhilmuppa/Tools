package com.osi.tools.utils;

import com.osi.tools.fragments.CurrencyConversionFragment;

public class ConversionUtility{
private static final String Mytag = "UtilityMytag";
CurrencyConversionFragment context;



public ConversionUtility(CurrencyConversionFragment con) {
	this.context=con;
}

public double getConvertedvalue(double givenvlue)
{
	double convertedvalue;
	
	if(context.from=="USD"){
		convertedvalue=givenvlue*context.togetdouble;
		
	}else if(context.to=="USD"){
		convertedvalue=givenvlue/ context.togetdouble;
	}else {
		double value=RoundbyMath(context.fromgetdouble);							//Log.i(Mytag,"from2decimal"+context.fromgetdouble);
		double usdollervalue=givenvlue/value;         								//Log.i(Mytag,"usdollervalue"+usdollervalue);
		 usdollervalue= RoundbyMath(usdollervalue);	                               //  Log.i(Mytag,"usdollervalue5decimal"+usdollervalue);
		convertedvalue=usdollervalue* context.togetdouble;
		convertedvalue=RoundbyMath(convertedvalue);		                             //   Log.i(Mytag,"convertedvalue"+convertedvalue);
	}
	
	return convertedvalue;
}


double RoundbyMath(double val){
	double newKB = Math.round(val*100000.0)/100000.0;
	return newKB;
 
}




}
