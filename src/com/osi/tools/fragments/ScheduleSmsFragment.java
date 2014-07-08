package com.osi.tools.fragments;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.osi.tools.R;
import com.osi.tools.ToolsActivity;
import com.osi.tools.db.ToolsDataBase;
import com.osi.tools.receivers.SmsReceiver;
import com.osi.tools.tables.SmsDetailsTable;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.net.ParseException;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.TimePicker.OnTimeChangedListener;

public class ScheduleSmsFragment extends Fragment{

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	protected static final String Mytag = null;
	protected Button btnSend;
	protected ImageView imagcontact;
	protected EditText etPhoneNo, etMsg;
	protected Button 	dateButton;
	protected SmsDetailsTable table;
	protected ToolsDataBase databse;

	protected SimpleDateFormat sdf = new SimpleDateFormat("EEE hh:mm aa, dd MMM yyyy");
	private Dialog dateSelectDialog;
	private Date refDate = new Date();
	private Calendar refCal = new GregorianCalendar();
	protected Date processDate = new Date();
	long milliseconds;
	private  DatePicker datePicker;
	private TimePicker timePicker ;
	private Button okDateButton,cancelDateButton,cancelbt;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_schedulesms, container, false);

		initialzeVaribles(view);



		btnSend.setOnClickListener(new OnClickListener() {    
			@Override
			public void onClick(View v) { 

				if(etMsg.getText().toString().trim().length()>0 && etPhoneNo.getText().toString().trim().length()>0)
				{
					if(dateButton.getText().toString().trim().equalsIgnoreCase("Set Time"))
					{
						Toast.makeText(getActivity(), "Please Set Time to Schedule",Toast.LENGTH_SHORT).show();
					}else
					{
						milliseconds=processDate.getTime();
						setScheduleSms(milliseconds,etPhoneNo.getText().toString(),etMsg.getText().toString());
						Toast.makeText(getActivity(),"Scheduled Successfully",Toast.LENGTH_SHORT).show();
					
						Fragment smsfrag = new ScheduleSmsMainFrag();


						((ToolsActivity)getActivity()).pushFragment(smsfrag);
						
					}
				}else{
					Toast.makeText(getActivity(), "Please enter all Fields",Toast.LENGTH_SHORT).show();
				}

			}
		});


		imagcontact.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
				startActivityForResult(intent,2);
			}
		});
		
		cancelbt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Fragment smsfrag = new ScheduleSmsMainFrag();


				((ToolsActivity)getActivity()).pushFragment(smsfrag);
				
			}
		});
		
		
		dateButton.setOnClickListener(new OnClickListener() {


			@Override
			public void onClick(View v) {

				dateSelectDialog = new Dialog(getActivity());
				dateSelectDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dateSelectDialog.setContentView(R.layout.date_time_picker);

				datePicker   	= (DatePicker)dateSelectDialog.findViewById(R.id.new_date_picker);
				timePicker   	= (TimePicker)  dateSelectDialog.findViewById(R.id.new_time_picker);
				okDateButton 			= (Button) 		dateSelectDialog.findViewById(R.id.new_date_dialog_ok_button);
				cancelDateButton 		= (Button) 		dateSelectDialog.findViewById(R.id.new_date_dialog_cancel_button);



				//---Setting DatePicker value change listener--------

				timePicker.setCurrentHour(processDate.getHours());
				timePicker.setCurrentMinute(processDate.getMinutes());
				final int mYear = processDate.getYear() + 1900;
				final int mMonth = processDate.getMonth();
				final int mDay = processDate.getDate();


				datePicker.init(mYear, mMonth, mDay, new OnDateChangedListener() {

					@Override
					public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {



					}
				});


				timePicker.setOnTimeChangedListener(new OnTimeChangedListener() {

					@Override
					public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {



					}
				});


				okDateButton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						refCal = new GregorianCalendar(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), timePicker.getCurrentHour(), timePicker.getCurrentMinute());
						refDate = refCal.getTime();

						if(checkDateValidity(refDate)){
							processDate = refDate;
							dateSelectDialog.cancel();
							String temp = sdf.format(new Date(processDate.getYear(), processDate.getMonth(), processDate.getDate(), processDate.getHours(), processDate.getMinutes()));
							dateButton.setText(temp);
						}else{
							processDate = refDate;
							dateSelectDialog.cancel();
							String temp = sdf.format(new Date(processDate.getYear(), processDate.getMonth(), processDate.getDate(), processDate.getHours(), processDate.getMinutes()));
							dateButton.setText(temp);
						}

					}
				});
				dateSelectDialog.show();
				cancelDateButton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						dateSelectDialog.cancel();
					}
				});


			}
		});


		return view;	
	}


	public void initialzeVaribles(View view)
	{

		btnSend = (Button)view.findViewById(R.id.send);
		etPhoneNo = (EditText)view.findViewById(R.id.numberen);
		etMsg = (EditText)view.findViewById(R.id.smstxt);
		imagcontact=(ImageView)view.findViewById(R.id.imagecon);
		dateButton 	= (Button)view.findViewById(R.id.new_date_button);
		dateButton.setText(sdf.format(processDate));
		cancelbt = (Button)view.findViewById(R.id.sendcancel);

	}

	public long getmillisfrmDate(String date) throws ParseException {
		Date currentpastDate = null;
		try {

			currentpastDate = sdf.parse(date);


		} catch (Exception E) {
			E.printStackTrace();
		}
		return currentpastDate.getTime();

	}






	public void setScheduleSms(long ttime,String number,String message)
	{

		table=new SmsDetailsTable(getActivity());
		long id=  table.insertsmsdetails(number, message, ttime);

		Intent myIntent = new Intent(getActivity(),SmsReceiver.class);
		myIntent.putExtra("id",id);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(),0, myIntent,0);  
		AlarmManager alarmManager = (AlarmManager)getActivity().getSystemService("alarm");

		alarmManager.set(AlarmManager.RTC_WAKEUP,ttime,pendingIntent);



	}


	@Override
	public void onDestroy() {
		super.onDestroy();

		((ToolsActivity)getActivity()).removeFragment(new ScheduleSmsMainFrag());
	}

	@Override
	public void onActivityResult(int reqCode, int resultCode, Intent data){
		super.onActivityResult(reqCode, resultCode, data);

		switch(reqCode){
		case (2):
			if (resultCode == Activity.RESULT_OK){
				Uri contactData = data.getData();
				Cursor c = getActivity().getContentResolver().query(contactData, null, null, null, null);

				if (c.moveToFirst()){

					String name = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
					String num = "";

					int columnIndex_ID = c.getColumnIndex(BaseColumns._ID);
					String contactID = c.getString(columnIndex_ID);

					int columnIndex_HASPHONENUMBER = c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
					String stringHasPhoneNumber = c.getString(columnIndex_HASPHONENUMBER);

					if(stringHasPhoneNumber.equalsIgnoreCase("1")){

						Cursor cursorNum = getActivity().getContentResolver().query(
								ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
								null, 
								ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactID, 
								null, 
								null);


						if(cursorNum.moveToFirst()){
							int columnIndex_number = cursorNum.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
							String stringNumber = cursorNum.getString(columnIndex_number);
							num = stringNumber;
						}


					}



					if(name.equalsIgnoreCase("") || num.equalsIgnoreCase(""))
					{

					}else{

						
						etPhoneNo.setText(num);
					//	insert(name, num);

					}



				}
			}
		}
	}




	protected boolean checkDateValidity(Date date){
		Calendar cal = new GregorianCalendar(date.getYear() + 1900, date.getMonth(), date.getDate(), date.getHours(), date.getMinutes());
		if((cal.getTimeInMillis()-System.currentTimeMillis()) <= 0){
			return false;
		}else{
			return true;
		}
	}



}
