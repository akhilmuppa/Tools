package com.osi.tools.listeners;




import com.osi.tools.R;
import com.osi.tools.ToolsActivity;
import com.osi.tools.fragments.BlackListFragment;
import com.osi.tools.fragments.BlockOptionsFragment;
import com.osi.tools.tables.BlackListTable;

import android.app.TimePickerDialog;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class BlockOptionClickListner implements OnClickListener{

	BlockOptionsFragment context;

	public BlockOptionClickListner(BlockOptionsFragment con)
	{

		this.context = con;

	}

	@Override
	public void onClick(View v) {


		if(v.getId() == R.id.setbtn)
		{

			 Log.d("val1", "");
			
			if(context.bid>0)
			{
				context.blt = new BlackListTable(context.getActivity());

				 Log.d("val2", "");

				context.blt.updateblacklist(context.bid,context.bean.getName(),context.bean.getNumber(),
						String.valueOf(context.smsch.isChecked()),String.valueOf(context.callch.isChecked()),
						context.fromedt.getText().toString(),context.toedt.getText().toString(),context.typeval,context.templatevalholder);

				context.blt.close();

				Fragment frag = new BlackListFragment();
				
				((ToolsActivity)context.getActivity()).pushFragment(frag);
				context.getActivity().overridePendingTransition(R.animator.left_to_right,R.animator.right_to_left);



			}

		}else if(v.getId() == R.id.cancelbtn)
		{

			Fragment frag = new BlackListFragment();
			
			((ToolsActivity)context.getActivity()).pushFragment(frag);
			context.getActivity().overridePendingTransition(R.animator.left_to_right,R.animator.right_to_left);

		}else if(v.getId() ==R.id.addtemp)
		{

			context.addtempDialog();

		}else if(v.getId() == R.id.fromtime)
		{

			new TimePickerDialog(context.getActivity(),context.timePickerListenerfrom,context.hour,context.min,false).show();

		}else if(v.getId() == R.id.totime)
		{

			new TimePickerDialog(context.getActivity(),context.timePickerListenerto,context.hour,context.min,false).show();

		}


	}



}
