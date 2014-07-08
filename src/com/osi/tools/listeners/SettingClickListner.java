package com.osi.tools.listeners;



import com.osi.tools.R;

import com.osi.tools.fragments.SettingsFragment;
import com.osi.tools.tables.SettingTable;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class SettingClickListner implements OnClickListener{

	SettingsFragment context;

	public SettingClickListner(SettingsFragment con)
	{

		this.context = con;

	}



	@Override
	public void onClick(View v) {


		if(v.getId() == R.id.savebtn)
		{

			context.stable = new SettingTable(context.getActivity());

			context.stable.updatetemplate(1,context.bloakval,String.valueOf(context.callssetting.isChecked()),context.storageval);

			context.stable.close();

			Toast.makeText(context.getActivity(),"Successfully Saved",Toast.LENGTH_SHORT).show();


		}
	}

}
