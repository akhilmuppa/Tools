package com.osi.tools.listeners;




import com.google.android.gms.internal.be;
import com.google.android.gms.internal.co;

import com.osi.tools.R;
import com.osi.tools.ToolsActivity;
import com.osi.tools.bean.BlackListBean;
import com.osi.tools.fragments.BlackListFragment;
import com.osi.tools.fragments.BlockOptionsFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class BlackListItemClickListner implements OnItemClickListener{


	BlackListFragment context;

	public BlackListItemClickListner(BlackListFragment con)
	{

		this.context = con;

	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		BlackListBean bean = (BlackListBean)context.bladapter.getItem(position);

		Fragment frag = new BlockOptionsFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("idval",bean.getId());
		frag.setArguments(bundle);
		((ToolsActivity)context.getActivity()).pushFragment(frag);
		context.getActivity().overridePendingTransition(R.animator.left_to_right,R.animator.right_to_left);
		
		/*Intent i = new Intent(context.getActivity(),BlockOptionActivity.class);

		i.putExtra("idval", bean.getId());

		context.startActivity(i);
*/		



	}

}
