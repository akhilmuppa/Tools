package com.osi.tools.fragments;

import java.util.ArrayList;

import org.json.JSONObject;





























import com.loopj.android.http.AsyncHttpClient;
import com.osi.tools.R;
import com.osi.tools.androidwheel.ArrayWheelAdapter;
import com.osi.tools.androidwheel.OnWheelChangedListener;
import com.osi.tools.androidwheel.OnWheelScrollListener;
import com.osi.tools.androidwheel.WheelView;
import com.osi.tools.utils.AreaStrategy;
import com.osi.tools.utils.ConversionUtility;
import com.osi.tools.utils.DigitalStoragesSrategy;
import com.osi.tools.utils.EnergyStrategy;
import com.osi.tools.utils.FuelStrategy;
import com.osi.tools.utils.LengthStrategy;
import com.osi.tools.utils.PowerStrategy;
import com.osi.tools.utils.Strategy;
import com.osi.tools.utils.TemperatureStrategy;
import com.osi.tools.utils.VelocityStrategy;
import com.osi.tools.utils.VolumeStrategy;
import com.osi.tools.utils.WeightStrategy;
import com.osi.tools.utils.asyc;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class CurrencyConversionFragment extends Fragment{


	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	protected static final String Mytag = "Maintag";


	private EditText givenvalue;
	private TextView convertedvalue;
	public String CHANGING="mytag",from,to;
	public static Double fromcur=null,tocur=null;
	public ArrayList<String> keysarray;
	public ArrayList<String> lista = new ArrayList<String>();
	public String response;
	public JSONObject  jsonObject,ratesObject;
	public ConversionUtility utilobject;
	public String url="http://openexchangerates.org/api/latest.json?app_id=49ea5bd652064ae885bf7cc29bd6b5fa";
	public Double fromgetdouble,togetdouble;
	public WheelView frmwheel,numwheel,towheel;
	public int unitval;


	private Strategy currentStrategy;
	private Strategy lastStrategy;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_currencyconvert, container, false);

		initializeValues(view);
		establishConnection();
		givenvalue.clearFocus();
		initWheel11(getActivity().getResources().getStringArray(R.array.unitarray));


		currentStrategy = new TemperatureStrategy();

		lastStrategy = currentStrategy;
		fillFromToSpinner(0);
		setFromtoval(0);

		givenvalue.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

				if(s.length()!=0)
				{
					updateStatus();
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {


			}

			@Override
			public void afterTextChanged(Editable s) {


			}
		});





		return view;	
	}

	/*private void updateStatus()
	{
		String fromw = lista.get(frmwheel.getCurrentItem()).toString();



		String tonw = lista.get(towheel.getCurrentItem()).toString();
		String numw = numva[numwheel.getCurrentItem()];

		try {
			fromgetdouble=ratesObject.getDouble(fromw);
			togetdouble=ratesObject.getDouble(tonw);
			 utilobject=new ConversionUtility(CurrencyConversionFragment.this);
				double convertcur=utilobject.getConvertedvalue(Double.parseDouble(numw));
				convertedvalue.setText(String.valueOf(convertcur));
		} catch (JSONException e) {
			
			e.printStackTrace();
		}



		//text.setText(wheelMenu1[getWheel(R.id.p1).getCurrentItem()] + " - " + wheelMenu2[getWheel(R.id.p2).getCurrentItem()] + " - " + wheelMenu3[getWheel(R.id.p3).getCurrentItem()]);
	}*/

	public void updateStatus()
	{

		try{
			if(givenvalue.getText().toString().length()>0)
			{
				double ival = Double.parseDouble(givenvalue.getText().toString());
				setFromtoval(unitval);
				if(unitval==8)
				{
					fromgetdouble=ratesObject.getDouble(from);
					togetdouble=ratesObject.getDouble(to);
					utilobject=new ConversionUtility(CurrencyConversionFragment.this);
					double convertcur=utilobject.getConvertedvalue(Double.parseDouble(givenvalue.getText().toString()));
					String mres = givenvalue.getText().toString()+" "+from+" = "+convertcur+" "+to;
					convertedvalue.setText(String.valueOf(convertcur));
					convertedvalue.setText(mres);
				}else if(unitval==9)
				{
					double res = currentStrategy.Convert(from, to, ival);
				
					String mres = givenvalue.getText().toString()+" "+from+" = "+res+" "+to;
					//convertedvalue.setText(String.valueOf(fval));
					convertedvalue.setText(mres);	
				}
				else
				{
					double res = currentStrategy.Convert(from, to, ival);
					double fval = RoundbyMath(res);
					String mres = givenvalue.getText().toString()+" "+from+" = "+fval+" "+to;
					//convertedvalue.setText(String.valueOf(fval));
					convertedvalue.setText(mres);
				}
				
				
				
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}


	}

	public void  initializeValues(View view){

		givenvalue=(EditText)view.findViewById(R.id.fromcur_editxtnew);
		givenvalue.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "digital_7.ttf"));
		convertedvalue=(TextView)view.findViewById(R.id.tocon_txtview);
		frmwheel = (WheelView)view.findViewById(R.id.p1);
		towheel= (WheelView)view.findViewById(R.id.p3);
		numwheel = (WheelView)view.findViewById(R.id.p2);

	}

	public void establishConnection()
	{

		AsyncHttpClient client = new AsyncHttpClient();


		client.get(url, new asyc(CurrencyConversionFragment.this));



	}

	private final OnWheelChangedListener changedListener = new OnWheelChangedListener()
	{
		@Override
		public void onChanged(WheelView wheel, int oldValue, int newValue)
		{

			if(wheel.getId() == R.id.p1)
			{
				unitval = newValue;
				switch(newValue){
				case 0:
					setStrategy(new TemperatureStrategy());

					break;

				case 1:
					setStrategy( new WeightStrategy());
					break;

				case 2:
					setStrategy(new LengthStrategy());
					break;

				case 3:
					setStrategy(new PowerStrategy());
					break;

				case 4:
					setStrategy(new EnergyStrategy());
					break;

				case 5:
					setStrategy(new VelocityStrategy());
					break;

				case 6:
					setStrategy(new AreaStrategy());
					break;

				case 7:
					setStrategy(new VolumeStrategy());
					break;

				case 9:
					setStrategy(new DigitalStoragesSrategy());
				break;

				case 10:
					setStrategy(new FuelStrategy());
				break;


					
				}

				fillFromToSpinner(newValue);
				setFromtoval(unitval);

				convertedvalue.setText("");

			}else if(wheel.getId() == R.id.p2)
			{

				//	setFromval(newValue);


			}else if(wheel.getId() == R.id.p3)
			{
				//	settoVal(newValue);

			}
			updateStatus();

		}
	};




	private void fillFromToSpinner(int position){

		switch(position)
		{
		case 0:

			initWheel22(getActivity().getResources().getStringArray(R.array.temparray));
			initWheel33(getActivity().getResources().getStringArray(R.array.temparray));

			break;

		case 1:

			initWheel22(getActivity().getResources().getStringArray(R.array.weightarray));
			initWheel33(getActivity().getResources().getStringArray(R.array.weightarray));

			break;

		case 2:

			initWheel22(getActivity().getResources().getStringArray(R.array.lengtharray));
			initWheel33(getActivity().getResources().getStringArray(R.array.lengtharray));

			break;

		case 3:

			initWheel22(getActivity().getResources().getStringArray(R.array.powerarray));
			initWheel33(getActivity().getResources().getStringArray(R.array.powerarray));

			break;

		case 4:

			initWheel22(getActivity().getResources().getStringArray(R.array.energyarray));
			initWheel33(getActivity().getResources().getStringArray(R.array.energyarray));

			break;

		case 5:

			initWheel22(getActivity().getResources().getStringArray(R.array.velocityarray));
			initWheel33(getActivity().getResources().getStringArray(R.array.velocityarray));

			break;

		case 6:

			initWheel22(getActivity().getResources().getStringArray(R.array.arrayarray));
			initWheel33(getActivity().getResources().getStringArray(R.array.arrayarray));

			break;

		case 7:

			initWheel22(getActivity().getResources().getStringArray(R.array.volumeyarray));
			initWheel33(getActivity().getResources().getStringArray(R.array.volumeyarray));

			break;

		case 8:
			initWheel22(converttostringarry(lista));
			initWheel33(converttostringarry(lista));

			break;

		case 9: 
			initWheel22(getActivity().getResources().getStringArray(R.array.bytearray));
			initWheel33(getActivity().getResources().getStringArray(R.array.bytearray));
			break;

		case 10:
			initWheel22(getActivity().getResources().getStringArray(R.array.fuelarray));
			initWheel33(getActivity().getResources().getStringArray(R.array.fuelarray));
			break;


		}

	}


	private void setFromtoval(int pos)
	{
		switch(pos)
		{
		case 0:
			String[] val = getActivity().getResources().getStringArray(R.array.temparray);

			from = val[numwheel.getCurrentItem()];
			to = val[towheel.getCurrentItem()];


			break;

		case 1:
			String[] val1 = getActivity().getResources().getStringArray(R.array.weightarray);

			from = val1[numwheel.getCurrentItem()];
			to = val1[towheel.getCurrentItem()];
			break;

		case 2:
			String[] val2 = getActivity().getResources().getStringArray(R.array.lengtharray);

			from = val2[numwheel.getCurrentItem()];
			to = val2[towheel.getCurrentItem()];
			break;

		case 3:
			String[] val3 = getActivity().getResources().getStringArray(R.array.powerarray);

			from = val3[numwheel.getCurrentItem()];
			to = val3[towheel.getCurrentItem()];
			break;

		case 4:
			String[] val4 = getActivity().getResources().getStringArray(R.array.energyarray);

			from = val4[numwheel.getCurrentItem()];
			to = val4[towheel.getCurrentItem()];
			break;

		case 5:
			String[] val5 = getActivity().getResources().getStringArray(R.array.velocityarray);

			from = val5[numwheel.getCurrentItem()];
			to = val5[towheel.getCurrentItem()];
			break;

		case 6:
			String[] val6 = getActivity().getResources().getStringArray(R.array.arrayarray);

			from = val6[numwheel.getCurrentItem()];
			to = val6[towheel.getCurrentItem()];
			break;

		case 7:
			String[] val7 = getActivity().getResources().getStringArray(R.array.volumeyarray);

			from = val7[numwheel.getCurrentItem()];
			to = val7[towheel.getCurrentItem()];
			break;

		case 8:
			if(lista.size()>0)
			{
				from = lista.get(numwheel.getCurrentItem()).toString();
				to = lista.get(towheel.getCurrentItem()).toString();
			}
			break;

		case 9:
			String[] val9=getActivity().getResources().getStringArray(R.array.bytearray);
		from=val9[numwheel.getCurrentItem()];
		to=val9[towheel.getCurrentItem()];
			break;

		case 10: 
			 String[] val10=getActivity().getResources().getStringArray(R.array.fuelarray);
				from=val10[numwheel.getCurrentItem()];
				to=val10[towheel.getCurrentItem()];
					break;

			
			
		}

	}



	OnWheelScrollListener scrolledListener = new OnWheelScrollListener()
	{
		public void onScrollStarts(WheelView wheel)
		{

		}

		public void onScrollEnds(WheelView wheel)
		{


		}

		@Override
		public void onScrollingStarted(WheelView wheel) {


		}

		@Override
		public void onScrollingFinished(WheelView wheel) {


		}
	};






	public void initWheel11(String[] val)
	{

		frmwheel.setViewAdapter(new ArrayWheelAdapter<Object>(getActivity(),val));

		frmwheel.setVisibleItems(2);
		frmwheel.setCurrentItem(0);
		frmwheel.addChangingListener(changedListener);
		frmwheel.addScrollingListener(scrolledListener);
	}

	public void initWheel22(String[] val)
	{

		numwheel.setViewAdapter(new ArrayWheelAdapter<Object>(getActivity(),val));
		numwheel.setVisibleItems(2);
		numwheel.setCurrentItem(0);
		numwheel.addChangingListener(changedListener);
		numwheel.addScrollingListener(scrolledListener);
	}




	public void initWheel33(String[] val)
	{

		towheel.setViewAdapter(new ArrayWheelAdapter<Object>(getActivity(),val));
		towheel.setVisibleItems(2);
		towheel.setCurrentItem(0);
		towheel.addChangingListener(changedListener);
		towheel.addScrollingListener(scrolledListener);

	}

	public String[] converttostringarry(ArrayList<String> val)
	{

		String[] va = new String[val.size()];
		Log.e("sat",""+val.size());
		for(int i=0;i<val.size();i++)
		{

			va[i] = val.get(i).toString();

		}


		return va;
	}

	private void setStrategy(Strategy s){

		lastStrategy = currentStrategy;
		currentStrategy = s;
		//make the last strategy eligible for garbage collection
		lastStrategy = null;
	} 

	double RoundbyMath(double val){
		double newKB = Math.round(val*100000.0)/100000.0;
		return newKB;

	}


}
