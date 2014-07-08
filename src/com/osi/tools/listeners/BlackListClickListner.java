package com.osi.tools.listeners;




import com.osi.tools.R;
import com.osi.tools.fragments.BlackListFragment;

import android.content.Intent;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class BlackListClickListner implements OnClickListener{

	BlackListFragment context;

	public BlackListClickListner(BlackListFragment con)
	{

		this.context = con;

	}


	@Override
	public void onClick(View v) {


		/*if(v.getId() == R.id.blcontactlayout)
		{

			Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
			context.startActivityForResult(intent,2);

		}else if(v.getId() == R.id.blnumberlayout)
		{

			context.writenumberDialog();

		}
*/		
		if(v.getId() == R.id.contactaddd)
		{
			
			if(context.numberedt.getText().toString().trim().length()>0)
			{
				
				if(context.nameholder.equalsIgnoreCase(""))
				{
					context.insert("UNKNOWN",context.numberedt.getText().toString());
				}else
				{
					context.insert(context.nameholder,context.numberholder);
				}
				
				
				
			}else
			{
				Toast.makeText(context.getActivity(), "Number cannot be empty",Toast.LENGTH_SHORT).show();
			}
			
			
		}else if(v.getId() == R.id.imagecon)
		{
			
			Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
			context.startActivityForResult(intent,2);
			
		}



	}



}
