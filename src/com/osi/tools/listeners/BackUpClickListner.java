package com.osi.tools.listeners;




import com.osi.tools.R;
import com.osi.tools.fragments.BackupFragment;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class BackUpClickListner implements OnClickListener{


	BackupFragment context;

	public BackUpClickListner(BackupFragment con)
	{

		this.context = con;

	}


	@Override
	public void onClick(View v) {


		if(v.getId()==R.id.Backuplyt)
		{

			if(context.one==true || context.three==true ||context.two==true)
			{
				context.getSelecteditem();
			}else
			{
				Toast.makeText(context.getActivity(), "Select any one for backup",Toast.LENGTH_SHORT).show();
			}


		}


	}



}
