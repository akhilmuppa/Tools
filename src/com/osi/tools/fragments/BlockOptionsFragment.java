package com.osi.tools.fragments;

import java.util.ArrayList;
import java.util.Calendar;




import com.osi.tools.R;
import com.osi.tools.adapters.TemplateAdapter;
import com.osi.tools.bean.BlackListBean;
import com.osi.tools.bean.TemplateBean;
import com.osi.tools.listeners.BlockOptionClickListner;
import com.osi.tools.listeners.BlockOptionItemClickListner;
import com.osi.tools.tables.BlackListTable;
import com.osi.tools.tables.TemplateTable;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class BlockOptionsFragment extends Fragment{

	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	public int bid;
	public CheckBox smsch,callch;
	public EditText fromedt,toedt;
	public Spinner typespinner;
	public ListView templatelistview;
	public Button setbt,cancelbt;
	public BlackListTable blt;
	public int typeval;
	public BlackListBean bean;
	public TemplateTable ttable;
	public TemplateAdapter ta;
	public String templatevalholder;
	public ImageView addtemp;
	public Dialog addtempdialog;
	public Calendar cal;
	public int hour,min;
	public static int selectedPosition = 0;
	public LinearLayout hidelinear;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.activity_blockoptions, container, false);
		
		initializeVariables(view);

		setbt.setOnClickListener(new BlockOptionClickListner(BlockOptionsFragment.this));
		cancelbt.setOnClickListener(new BlockOptionClickListner(BlockOptionsFragment.this));
		addtemp.setOnClickListener(new BlockOptionClickListner(BlockOptionsFragment.this));
		fromedt.setOnClickListener(new BlockOptionClickListner(BlockOptionsFragment.this));
		toedt.setOnClickListener(new BlockOptionClickListner(BlockOptionsFragment.this));

		addListnerfortypechange();
		settouchListner();

		cal = Calendar.getInstance();
		hour = cal.get(Calendar.HOUR_OF_DAY);
		min = cal.get(Calendar.MINUTE);

	
		
		Bundle bundle = this.getArguments();

		if(bundle!=null)
		{

				
           bid =   bundle.getInt("idval", 0);
         
           Log.d("val", ""+bid);
		}

		if(bid>0)
		{
			blt = new BlackListTable(getActivity());

			bean = blt.getdetailfromid(bid);

			blt.close();

			if(bean.getSms().equalsIgnoreCase("true"))
			{

				smsch.setChecked(true);
			}else{
				smsch.setChecked(false);
			}

			if(bean.getCall().equalsIgnoreCase("true"))
			{
				callch.setChecked(true);

			}else
			{
				callch.setChecked(false);	
			}

			fromedt.setText(bean.getFrom());
			toedt.setText(bean.getTo());
			typespinner.setSelection(bean.getType()-1);
			typeval = bean.getType();

		}

		initAdapter();
		templatelistview.setOnItemClickListener(new BlockOptionItemClickListner(BlockOptionsFragment.this));

		int currentapiVersion = android.os.Build.VERSION.SDK_INT;
		if (currentapiVersion >= android.os.Build.VERSION_CODES.KITKAT){

			smsch.setEnabled(false);


		} 

		
	return view;	
	}
	
	public void initializeVariables(View view)
	{

		smsch = (CheckBox)view.findViewById(R.id.chkboxsms);
		callch = (CheckBox)view.findViewById(R.id.chkboxcall);
		fromedt = (EditText)view.findViewById(R.id.fromtime);
		toedt = (EditText)view.findViewById(R.id.totime);
		typespinner = (Spinner)view.findViewById(R.id.typespinner);
		setbt = (Button)view.findViewById(R.id.setbtn);
		cancelbt = (Button)view.findViewById(R.id.cancelbtn);
		templatelistview = (ListView)view.findViewById(R.id.templatelistview);
		addtemp = (ImageView)view.findViewById(R.id.addtemp);
		hidelinear = (LinearLayout)view.findViewById(R.id.hidelinearlyt);


	}
	
	public TimePickerDialog.OnTimeSetListener timePickerListenerfrom = new 
			TimePickerDialog.OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {



			fromedt.setText(hourOfDay+":"+minute);

		}
	}; 

	public TimePickerDialog.OnTimeSetListener timePickerListenerto = new 
			TimePickerDialog.OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {




			toedt.setText(hourOfDay+":"+minute);
		}
	}; 	



	public void addListnerfortypechange()
	{

		typespinner.setOnItemSelectedListener(new OnItemSelectedListener() {


			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

				typeval = position+1;

				if(position==0)
				{
					hidelinear.setVisibility(View.GONE);
				}else
				{
					hidelinear.setVisibility(View.VISIBLE);
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {


			}
		});

	}

	public void initAdapter()
	{

		ttable = new TemplateTable(getActivity());

		ArrayList<TemplateBean> arraybean = ttable.getalldetails();

		ttable.close();

		ta = new TemplateAdapter(BlockOptionsFragment.this, arraybean);
		templatelistview.setAdapter(ta);


	}

	public void updatetemplate()
	{



	}

	public void settouchListner()
	{
		templatelistview.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				v.getParent().requestDisallowInterceptTouchEvent(true);
				return false;
			}


		});
	}

	public void addtempDialog()
	{

		addtempdialog = new Dialog(getActivity());
		addtempdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		addtempdialog.getWindow().getAttributes().windowAnimations=R.style.DialogAnimations;
		addtempdialog.setContentView(R.layout.addtemplate);

		Button ok = (Button)addtempdialog.findViewById(R.id.okt);
		Button cancel = (Button)addtempdialog.findViewById(R.id.cancelt);

		final EditText val = (EditText)addtempdialog.findViewById(R.id.addtemp_edt);

		addtempdialog.setTitle("SMS Template");

		ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if(val.getText().toString().trim().length()>0)
				{

					ttable = new TemplateTable(getActivity());
					ttable.inserttemplate(val.getText().toString());

					initAdapter();

					addtempdialog.cancel();
				}else
				{
					Toast.makeText(getActivity(), "All fields are Mandatory",Toast.LENGTH_SHORT).show();
				}

				
			}

		});

		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				addtempdialog.cancel();

			}

		});

		addtempdialog.show();

	}
	
}
