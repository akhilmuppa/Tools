package com.osi.tools.fragments;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;



import java.util.List;

import com.osi.tools.R;
import com.osi.tools.ToolsActivity;
import com.osi.tools.adapters.applistAdapter;
import com.osi.tools.receivers.AppReceiver;
import com.osi.tools.tables.AppTable;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TimePicker.OnTimeChangedListener;
import android.widget.Toast;

public class ScheduleAppFragment extends Fragment{


	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	public Button selectapp,seldatetme,scheduleapp;
	protected SimpleDateFormat sdf = new SimpleDateFormat(
			"EEE hh:mm aa, dd MMM yyyy");
	private Dialog dateSelectDialog;
	private Date refDate = new Date();
	private Calendar refCal = new GregorianCalendar();
	protected Date processDate = new Date();
	private Dialog appdialog;
	public int apppos;
	public AppTable atable;
	public Button cancelbt;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_scheduleapp, container, false);

		initialzeVaribles(view);



		selectapp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				applistDialog();
			}
		});

		scheduleapp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if(selectapp.getText().toString().equalsIgnoreCase("Select App") ||seldatetme.getText().toString().equalsIgnoreCase("Set Time"))
				{
					Toast.makeText(getActivity(), "Please enter all Fields",Toast.LENGTH_SHORT).show();
				}else{
					ScheduleApp(apppos,processDate.getTime());
					Toast.makeText(getActivity(),"Scheduled Successfully",Toast.LENGTH_SHORT).show();
					
					Fragment scheduleappFrag = new ScheduleAppMainFrag();
					((ToolsActivity)getActivity()).pushFragment(scheduleappFrag);

					
				}

			}
		});

		cancelbt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				Fragment scheduleappFrag = new ScheduleAppMainFrag();
				((ToolsActivity)getActivity()).pushFragment(scheduleappFrag);

				
			}
		});
		
		seldatetme.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {



				dateSelectDialog = new Dialog(getActivity());
				dateSelectDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dateSelectDialog.setContentView(R.layout.date_time_picker);

				final DatePicker datePicker = (DatePicker) dateSelectDialog
						.findViewById(R.id.new_date_picker);
				final TimePicker timePicker = (TimePicker) dateSelectDialog
						.findViewById(R.id.new_time_picker);
				Button okDateButton = (Button) dateSelectDialog
						.findViewById(R.id.new_date_dialog_ok_button);
				Button cancelDateButton = (Button) dateSelectDialog
						.findViewById(R.id.new_date_dialog_cancel_button);

				// ---Setting DatePicker value change listener--------

				timePicker.setCurrentHour(processDate.getHours());
				timePicker.setCurrentMinute(processDate.getMinutes());
				final int mYear = processDate.getYear() + 1900;
				final int mMonth = processDate.getMonth();
				final int mDay = processDate.getDate();

				datePicker.init(mYear, mMonth, mDay,
						new OnDateChangedListener() {

					@Override
					public void onDateChanged(DatePicker view,
							int year, int monthOfYear, int dayOfMonth) {

					}
				});

				// ---------------------------------------end of DatePicker
				// setup------

				// ---Setting TimePicker value change listener--------
				timePicker
				.setOnTimeChangedListener(new OnTimeChangedListener() {

					@Override
					public void onTimeChanged(TimePicker view,
							int hourOfDay, int minute) {

					}

				});
				okDateButton.setOnClickListener(new OnClickListener() {

					@Override
					@SuppressWarnings("deprecation")
					public void onClick(View v) {
						refCal = new GregorianCalendar(datePicker.getYear(),
								datePicker.getMonth(), datePicker
								.getDayOfMonth(), timePicker
								.getCurrentHour(), timePicker
								.getCurrentMinute());
						refDate = refCal.getTime();

						if (checkDateValidity(refDate)) {
							processDate = refDate;
							dateSelectDialog.cancel();
							@SuppressWarnings("deprecation")
							String temp = sdf.format(new Date(processDate
									.getYear(), processDate.getMonth(),
									processDate.getDate(), processDate
									.getHours(), processDate
									.getMinutes()));
							seldatetme.setText(temp);
						} else {
							processDate = refDate;
							dateSelectDialog.cancel();
							String temp = sdf.format(new Date(processDate
									.getYear(), processDate.getMonth(),
									processDate.getDate(), processDate
									.getHours(), processDate
									.getMinutes()));
							seldatetme.setText(temp);
						}

					}
				});

				cancelDateButton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						dateSelectDialog.cancel();
					}
				});
				dateSelectDialog.show();

			}
		});



		return view;	
	}

	public void initialzeVaribles(View view)
	{

		selectapp = (Button)view.findViewById(R.id.selectapp);
		seldatetme = (Button)view.findViewById(R.id.appdatetime);
		scheduleapp = (Button)view.findViewById(R.id.scheduleapp);
        cancelbt= (Button)view.findViewById(R.id.schedulecancel);
	}

	public void ScheduleApp(int pos,long ttime)
	{
		Log.d("dddate", ""+ttime);
		ResolveInfo info = adapter.getItem(pos);
		ActivityInfo activity = info.activityInfo;
		atable = new AppTable(getActivity());
		String appname = (String) adapter.getItem(pos).loadLabel(pm);
		long id =  atable.insertscheduleapp(activity.applicationInfo.packageName,activity.name, ttime,appname);
		int mid = (int) id;
		atable.close();
		Intent myIntent = new Intent(getActivity(),AppReceiver.class);
		myIntent.putExtra("appid",id);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(),0, myIntent,0);  
		AlarmManager alarmManager = (AlarmManager)getActivity().getSystemService("alarm");

		alarmManager.set(AlarmManager.RTC_WAKEUP,ttime,pendingIntent);

	}


	public applistAdapter adapter;
	public PackageManager pm;
	public void applistDialog() {

		appdialog = new Dialog(getActivity());
		appdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		appdialog.setContentView(R.layout.listviewdialog);

		ListView lview = (ListView) appdialog.findViewById(R.id.mainListView);

		pm = getActivity().getPackageManager();
		Intent main = new Intent(Intent.ACTION_MAIN, null);

		main.addCategory(Intent.CATEGORY_LAUNCHER);

		List<ResolveInfo> launchables = pm.queryIntentActivities(main, 0);

		Collections
		.sort(launchables, new ResolveInfo.DisplayNameComparator(pm));

		adapter = new applistAdapter(getActivity(), pm, launchables,ScheduleAppFragment.this);

		lview.setAdapter(adapter);

		lview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				apppos = arg2;
				/*
				ResolveInfo launchable = adapter.getItem(arg2);

				ActivityInfo activity = launchable.activityInfo;
				ComponentName name = new ComponentName(
						activity.applicationInfo.packageName, activity.name);
				Intent i = new Intent(Intent.ACTION_MAIN);

				i.addCategory(Intent.CATEGORY_LAUNCHER);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
				i.setComponent(name);

				appdialog.cancel();

				startActivity(i);*/
				String apname = (String) adapter.getItem(arg2).loadLabel(pm);
				selectapp.setText(apname);
				appdialog.cancel();

			}
		});
		appdialog.show();

	}

	protected boolean checkDateValidity(Date date) {
		Calendar cal = new GregorianCalendar(date.getYear() + 1900,
				date.getMonth(), date.getDate(), date.getHours(),
				date.getMinutes());
		if ((cal.getTimeInMillis() - System.currentTimeMillis()) <= 0) {
			return false;
		} else {
			return true;
		}
	}


}
