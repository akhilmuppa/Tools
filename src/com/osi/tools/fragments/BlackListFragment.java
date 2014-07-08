package com.osi.tools.fragments;

import java.util.ArrayList;

import com.osi.tools.R;
import com.osi.tools.adapters.BlackListAdapter;
import com.osi.tools.bean.BlackListBean;
import com.osi.tools.listeners.BlackListClickListner;
import com.osi.tools.listeners.BlackListItemClickListner;
import com.osi.tools.tables.BlackListTable;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class BlackListFragment extends Fragment{

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}
	
	public ListView blacklistview;
	public BlackListTable blt;
	public BlackListAdapter bladapter;
	public Dialog numberdialog;
    public EditText numberedt;
    public ImageView contactimg;
    public Button addbt;
    public String nameholder,numberholder;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.activity_blacklist, container, false);

		initializeVariables(view);

		nameholder = "";
		numberholder = "";

		//	contact.setOnClickListener(new BlackListClickListner(BlackListActivity.this));
		//	writenumber.setOnClickListener(new BlackListClickListner(BlackListActivity.this));
		blacklistview.setOnItemClickListener(new BlackListItemClickListner(BlackListFragment.this));
		contactimg.setOnClickListener(new BlackListClickListner(BlackListFragment.this));
		addbt.setOnClickListener(new BlackListClickListner(BlackListFragment.this));

		initAdapter();
		
		
		return view;
	}
	
	public void initializeVariables(View view)
	{

	//	contact = (RelativeLayout)findViewById(R.id.blcontactlayout);
		//writenumber = (RelativeLayout)findViewById(R.id.blnumberlayout);
		blacklistview = (ListView)view.findViewById(R.id.blacklistview);
        numberedt = (EditText)view.findViewById(R.id.numberen);
        contactimg = (ImageView)view.findViewById(R.id.imagecon);
        addbt = (Button)view.findViewById(R.id.contactaddd);


	}
	
	public void initAdapter()
	{

		blt = new BlackListTable(getActivity());

		ArrayList<BlackListBean> beanarray = blt.getallDetails();
		blt.close();

		Animation anim= AnimationUtils.loadAnimation(getActivity(),R.animator.scale);
		blacklistview.startAnimation(anim);

		bladapter = new BlackListAdapter(beanarray, BlackListFragment.this);
		blacklistview.setAdapter(bladapter);



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

						nameholder = name;
						numberholder = num;
						numberedt.setText(num);
						
					//	insert(name, num);

					}



				}
			}
		}
	}


	public void writenumberDialog()
	{

		numberdialog = new Dialog(getActivity());

		numberdialog.getWindow().getAttributes().windowAnimations=R.style.DialogAnimations;
		numberdialog.setContentView(R.layout.writenumber);

		Button ok = (Button)numberdialog.findViewById(R.id.ok);
		Button cancel = (Button)numberdialog.findViewById(R.id.cancel);

		final EditText val = (EditText)numberdialog.findViewById(R.id.writenum_edt);

		numberdialog.setTitle("UNKNOWN Contact");


		ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if(val.getText().toString().trim().length()>0)
				{

					
					
					//insert("UNKNOWN",val.getText().toString().trim());
				
					/* blt = new BlackListTable(BlackListActivity.this);
                	 blt.insertblacklist("UNKNOWN",val.getText().toString().trim(), "true","true","", "", 1,"");
                	 blt.close();
                	 initAdapter();*/
					numberdialog.cancel();
				}else
				{
					Toast.makeText(getActivity(), "All fields are Mandatory",Toast.LENGTH_SHORT).show();
				}


			}

		});

		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				numberdialog.cancel();

			}

		});

		numberdialog.show();

	}


	public void insert(String name,String num)
	{

		blt = new BlackListTable(getActivity());
		int currentapiVersion = android.os.Build.VERSION.SDK_INT;
		if (currentapiVersion >= android.os.Build.VERSION_CODES.KITKAT){

			blt.insertblacklist(name, num, "false","true","", "", 1,"");
		} else
		{
			blt.insertblacklist(name, num, "true","true","", "", 1,"");
		}
		blt.close();
		initAdapter();
         
		nameholder = "";
		numberholder = "";
		numberedt.setText("");
		
	}


	
	
}
