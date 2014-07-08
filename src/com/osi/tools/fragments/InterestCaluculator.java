package com.osi.tools.fragments;

import com.osi.tools.R;
import com.osi.tools.androidwheel.ArrayWheelAdapter;
import com.osi.tools.androidwheel.OnWheelChangedListener;
import com.osi.tools.androidwheel.WheelView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class InterestCaluculator extends Fragment{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	public WheelView wheel1,wheel2,wheel3,wheel4;
	public String[] interestarray,loanarray,yearsarray,selarray;
	public TextView monthtxt,tinttxt,totpaytxt;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_interestcal, container, false);
		initaizeVaribales(view);
		
		initialzeWheel();
		
		
		
	return view;	
	}
	
public void initaizeVaribales(View view)
{
	
	
	wheel1 = (WheelView)view.findViewById(R.id.i1);
	wheel2 =  (WheelView)view.findViewById(R.id.i2);
	wheel3 = (WheelView)view.findViewById(R.id.i3);
	wheel4 =  (WheelView)view.findViewById(R.id.i4);
	monthtxt = (TextView)view.findViewById(R.id.mpayment);
	tinttxt =  (TextView)view.findViewById(R.id.tintpay);
	totpaytxt =  (TextView)view.findViewById(R.id.totpay);
	
	
}

public void updatestatus()
{
	
	String one = loanarray[wheel2.getCurrentItem()];
	String two = interestarray[wheel3.getCurrentItem()];
	String three = yearsarray[wheel4.getCurrentItem()];
	
	double emi = calculateemi(Double.valueOf(one),Double.valueOf(two),Double.valueOf(three));
	
	double totpa = emi*Double.valueOf(three)*12;
	
	double toti = totpa-Double.valueOf(one);
	
	int o = (int)RoundbyMath(emi);
	int t = (int)RoundbyMath(toti);
	int th = (int)RoundbyMath(totpa);
	
	monthtxt.setText("Monthly Payment(EMI)="+o);
	 tinttxt.setText("Total Interest Payable="+t);
	 totpaytxt.setText("Total Payment="+th);
	
}

public void initialzeWheel()
{
	selarray = new String[]{"Home","Personal","Car"};
	wheel1.setViewAdapter(new ArrayWheelAdapter(getActivity(),selarray));
	wheel1.setCurrentItem(0);
	wheel1.setVisibleItems(2);
	wheel1.addChangingListener(changedListener);
	
	String[] arr = getloanarray();
	
	wheel2.setViewAdapter(new ArrayWheelAdapter(getActivity(),arr));
	wheel2.setCurrentItem(0);
	wheel2.setVisibleItems(2);
	wheel2.addChangingListener(changedListener);
	
	String[] arr1 = getintarray();
	
	wheel3.setViewAdapter(new ArrayWheelAdapter(getActivity(),arr1));
	wheel3.setCurrentItem(0);
	wheel3.setVisibleItems(2);
	wheel3.addChangingListener(changedListener);
	
	String[] arr2 = getmontharray();
	
	wheel4.setViewAdapter(new ArrayWheelAdapter(getActivity(),arr2));
	wheel4.setCurrentItem(0);
	wheel4.setVisibleItems(2);
	wheel4.addChangingListener(changedListener);
	
}
private final OnWheelChangedListener changedListener = new OnWheelChangedListener()
{
	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue)
	{

		updatestatus();



	}
};

public String[] getloanarray()
{
	loanarray = new String[50];
	int add = 25000;
	int tadd=0;
	
	for(int i=0;i<50;i++)
	{
		tadd = tadd+add;
		loanarray[i] =String.valueOf(tadd);
		
		
	}
	
	return loanarray;
}

public String[] getintarray()
{
	interestarray = new String[40];
	double add =  0.5;
	double tadd=0;
	
	for(int i=0;i<40;i++)
	{
		
		tadd = tadd+add;
		interestarray[i] = String.valueOf(tadd);
		
		
	}
	
	
	return interestarray;
}
	
public String[] getmontharray()
{
	yearsarray = new String[20];

	
	for(int i=0;i<20;i++)
	{
		
		
		yearsarray[i] = String.valueOf(i+1);
		
		
	}
	
	
	return yearsarray;
}

public double calculateemi(double p,double r,double n)
{
	double emi;

	r = r/(12*100);
	r= RoundbyMath(r);
	int nn  = (int) (n*12);
	
	double top = Math.pow(1+r, nn);
	
	
	
	
	 emi = (p*r*top)/(top-1);
	

	
	
	return emi;
}

double RoundbyMath(double val){
	double newKB = Math.round(val*100000.0)/100000.0;
	return newKB;

}

	
}
