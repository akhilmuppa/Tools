package com.osi.tools.listeners;




import com.osi.tools.adapters.TemplateAdapter;
import com.osi.tools.bean.TemplateBean;
import com.osi.tools.fragments.BlockOptionsFragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class BlockOptionItemClickListner implements OnItemClickListener{

	BlockOptionsFragment context;

	public BlockOptionItemClickListner(BlockOptionsFragment con)
	{

		this.context = con;

	}


	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		TemplateBean tb =(TemplateBean)context.ta.getItem(arg2);

		context.templatevalholder = tb.getTemplatename();
		BlockOptionsFragment.selectedPosition = arg2;
		TemplateAdapter.setSelectedIndex(BlockOptionsFragment.selectedPosition);
		context.ta.notifyDataSetChanged();

	}

}
