package com.osi.tools.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;




import com.osi.tools.fragments.BackupFragment;
import com.osi.tools.fragments.ManageFragment;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;


public class ContactsBackup {


	BackupFragment context;
	ManageFragment mcontext;
	public ContactsBackup(BackupFragment con)
	{
		this.context = con;
	}

	public ContactsBackup(ManageFragment mcon)
	{
		this.mcontext = mcon;
	}


	public void writecontactstoFile(File file,String fname)
	{

		ArrayList<String[]> arry = backupContacts();

		try
		{
			CSVWriter cw = new CSVWriter(new FileWriter(file));

			cw.writeAll(arry);
			cw.close();

			context.updatedate(file,fname,"3",2);


		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	public ArrayList<String[]> backupContacts()
	{

		ArrayList<String[]> arryholder= new ArrayList<String[]>();


		String[] h = initializeheaders();

		arryholder.add(h);

		ContentResolver cr = context.getActivity().getContentResolver();
		Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
				null, null, null, null);

		if(cur.getCount()>0)
		{
			while (cur.moveToNext()) {

				String id = cur.getString(cur.getColumnIndex(BaseColumns._ID));
				String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));	 


				String[] arry = new String[4];
				arry[0] = id;
				arry[1] = name;

				/*   String whereName = ContactsContract.Data.CONTACT_ID + " = ?";
             	    String[] whereNameParams = new String[] { ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE };
             	    Cursor nameCur = cr.query(ContactsContract.Data.CONTENT_URI, null, whereName, new String[]{id},null);
             	    if(nameCur.moveToFirst()) {
             	        String given = nameCur.getString(nameCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME));
             	        String family = nameCur.getString(nameCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME));
             	        String display = nameCur.getString(nameCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME));
             	   arry[1] = given;
             	   arry[2] = family;
             	//   arry[13] = display;

             	    }
             	    nameCur.close();*/


				if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {


					Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
							ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",
									new String[]{id}, null);
					if(pCur.moveToFirst()) {
						String phone = pCur.getString(
								pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
						arry[2] = phone;

					}
					pCur.close();





					Cursor emailCur = cr.query(
							ContactsContract.CommonDataKinds.Email.CONTENT_URI,
							null,
							ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
									new String[]{id}, null);
					if (emailCur.moveToFirst()) {
						// This would allow you get several email addresses
						// if the email addresses were stored in an array
						String email = emailCur.getString(
								emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
						String emailType = emailCur.getString(
								emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.TYPE));

						arry[3] = email;
					}
					emailCur.close();




					/*   String noteWhere = ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?";
	                      String[] noteWhereParams = new String[]{id,
	                      ContactsContract.CommonDataKinds.Note.CONTENT_ITEM_TYPE};
	                              Cursor noteCur = cr.query(ContactsContract.Data.CONTENT_URI, null, noteWhere, noteWhereParams, null);
	                      if (noteCur.moveToFirst()) {
	                          String note = noteCur.getString(noteCur.getColumnIndex(ContactsContract.CommonDataKinds.Note.NOTE));
	                       arry[5] = note;
	                      }
	                      noteCur.close();

	                      String addrWhere = ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?";
	                      String[] addrWhereParams = new String[]{id,
	                          ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE};
	                      Cursor addrCur = cr.query(ContactsContract.Data.CONTENT_URI,
	                                  null, addrWhere,addrWhereParams, null);
	                      if(addrCur.moveToFirst()) {
	                          String poBox = addrCur.getString(
	                                       addrCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POBOX));
	                          String street = addrCur.getString(
	                                       addrCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.STREET));
	                          String city = addrCur.getString(
	                                       addrCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CITY));
	                          String state = addrCur.getString(
	                                       addrCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.REGION));
	                          String postalCode = addrCur.getString(
	                                       addrCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE));
	                          String country = addrCur.getString(
	                                       addrCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY));
	                          String type = addrCur.getString(
	                                       addrCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.TYPE));

	                         arry[5] = poBox;
	                         arry[6] = street;
	                         arry[7] = city;
	                         arry[8] = state;
	                         arry[9] = postalCode;
	                         arry[10] = country;


	                      }
	                      addrCur.close();*/




				}
				arryholder.add(arry);
			}

		}

		return arryholder;
	}




	public void readcontactfrmfile(File file)
	{

		try {
			CSVReader cr = new CSVReader(new FileReader(file));
			String[] row = null;

			int count=0;
			while((row=cr.readNext())!= null)
			{
				if(count>0)
				{
					restoreContact(row);
				}
				count++;
			}

			cr.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}catch (Exception e) {

			e.printStackTrace();
		}


	}

	public void restoreContact(String[] row)
	{

		try{

			insertContact(row[0],row[1],row[2],row[3]);

		}catch(Exception e)
		{
			e.printStackTrace();
		}


	}

	public void insertContact(String id,String name,String phone,String email)
	{

		ArrayList<ContentProviderOperation> op_list = new ArrayList<ContentProviderOperation>(); 
		op_list.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI) 
				.withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null) 
				.withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null) 
				//.withValue(RawContacts.AGGREGATION_MODE, RawContacts.AGGREGATION_MODE_DEFAULT) 
				.build()); 




		// first and last names 
		op_list.add(ContentProviderOperation.newInsert(Data.CONTENT_URI) 
				.withValueBackReference(Data.RAW_CONTACT_ID, 0) 
				.withValue(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE) 
				.withValue(StructuredName.GIVEN_NAME,name) 
				.withValue(StructuredName.FAMILY_NAME, "") 
				.build()); 

		op_list.add(ContentProviderOperation.newInsert(Data.CONTENT_URI) 
				.withValueBackReference(Data.RAW_CONTACT_ID, 0) 
				.withValue(ContactsContract.Data.MIMETYPE,ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
				.withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, phone)
				.withValue(ContactsContract.CommonDataKinds.Phone.TYPE, Phone.TYPE_WORK)
				.build());
		op_list.add(ContentProviderOperation.newInsert(Data.CONTENT_URI) 
				.withValueBackReference(Data.RAW_CONTACT_ID, 0)

				.withValue(ContactsContract.Data.MIMETYPE,ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
				.withValue(ContactsContract.CommonDataKinds.Email.DATA, email)
				.withValue(ContactsContract.CommonDataKinds.Email.TYPE, Email.TYPE_WORK)
				.build());

		try{ 
			ContentProviderResult[] results = mcontext.getActivity().getContentResolver().applyBatch(ContactsContract.AUTHORITY, op_list); 
		}catch(Exception e){ 
			e.printStackTrace(); 
		} 



	}

	public String[] initializeheaders()
	{

		String [] h = new String[4];

		h[0] = "ContactId";
		h[1] = "Name";
		h[2] = "Phone Number";
		h[3] = "Email";


		return h;
	}


}
