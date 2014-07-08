package com.osi.tools.fragments;

import java.util.Calendar;

import com.osi.tools.R;
import com.osi.tools.androidwheel.ArrayWheelAdapter;
import com.osi.tools.androidwheel.NumericWheelAdapter;
import com.osi.tools.androidwheel.OnWheelChangedListener;
import com.osi.tools.androidwheel.WheelView;
import com.osi.tools.utils.AgeCalculation;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

public class AgeCalculatorfragment extends Fragment{




	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}


	private TextView result;

	private int startYear=1970;
	private int startMonth=6;
	private int startDay=15;
	private int year,month,day;
	private int noofyears = 50;
	public WheelView daywheel,mnthwheel,yearwheel;
	String months[] = new String[] { "January", "February", "March",
			"April", "May", "June", "July", "August", "September",
			"October", "November", "December" };
	public AgeCalculation age;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_agecalculator, container, false);
		initialzeVaribles(view);
		initialzeWheel();

		age = new AgeCalculation();

        age.getCurrentDate();


		return view;
	}

	public void initialzeVaribles(View view)
	{

		result = (TextView)view.findViewById(R.id.Result);

		daywheel= (WheelView)view.findViewById(R.id.a1);
		mnthwheel = (WheelView)view.findViewById(R.id.a2);
		yearwheel = (WheelView)view.findViewById(R.id.a3);

	}

	private DatePickerDialog.OnDateSetListener mDateSetListener 
	= new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			startYear=selectedYear;
			startMonth=selectedMonth;
			startDay=selectedDay;
			age.setDateOfBirth(startYear, startMonth, startDay);

			calculateAge();
		}
	};

	private void calculateAge()
	{       
		age.calcualteYear();
		age.calcualteMonth();
		age.calcualteDay();
		//result.setText("AGE (DD/MM/YY) :"+age.getResult());
		
		Log.d("sec",""+age.getSeconde());
		
		result.setText(age.getResult());
	}

	public void updatestatus()
	{
		Calendar calendar = Calendar.getInstance();
		month = mnthwheel.getCurrentItem();
		day =  Math.min(31, daywheel.getCurrentItem() + 1);
		year = calendar.get(Calendar.YEAR) + (yearwheel.getCurrentItem()-noofyears);

		age.setDateOfBirth(year,month,day);
		calculateAge();
	}

	public void initialzeWheel()
	{

		daywheel.setViewAdapter(new NumericWheelAdapter(getActivity(), 1, 31));
		daywheel.setVisibleItems(2);
		daywheel.setCurrentItem(0);
		daywheel.addChangingListener(changedListener);

		mnthwheel.setViewAdapter(new ArrayWheelAdapter(getActivity(),months));
		mnthwheel.setVisibleItems(2);
		mnthwheel.setCurrentItem(0);
		mnthwheel.addChangingListener(changedListener);

		Calendar c = Calendar.getInstance();
		int yer = c.get(Calendar.YEAR);

		yearwheel.setViewAdapter(new NumericWheelAdapter(getActivity(), yer-noofyears,yer+noofyears));
		yearwheel.setVisibleItems(2);
		yearwheel.setCurrentItem(0);
		yearwheel.addChangingListener(changedListener);
	}
	private final OnWheelChangedListener changedListener = new OnWheelChangedListener()
	{
		@Override
		public void onChanged(WheelView wheel, int oldValue, int newValue)
		{

			updatestatus();



		}
	};


}
