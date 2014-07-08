package com.osi.tools.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.json.JSONObject;

import android.util.Log;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.osi.tools.fragments.CurrencyConversionFragment;

public class asyc extends AsyncHttpResponseHandler{

	private static final String Mytag = "mytag";
	Iterator<String> keyarray;
	CurrencyConversionFragment context;
	public asyc(CurrencyConversionFragment con)
	{
		this.context = con;
	}


	@Override
	public void onFailure(Throwable error, String content) {
		super.onFailure(error, content);

		
		FileUtils futils = new FileUtils(context.getActivity());
		String reresponse = futils.ReadFile();
        
		update(reresponse);

	}

	@Override
	public void onFinish() {
		super.onFinish();
	}

	@Override
	public void onStart() {
		super.onStart();
		FileUtils futils = new FileUtils(context.getActivity());
		String reresponse = futils.ReadFile();
        
		update(reresponse);
	}

	@Override
	public void onSuccess(String reresponse) {
		try{
			
			FileUtils futils = new FileUtils(context.getActivity());
			futils.writeFile(reresponse);

			update(reresponse);


		}catch(Exception e){
			e.printStackTrace();
		}



	}

	public void update(String reresponse)
	{
		try
		{
			context.response = reresponse;
			context.jsonObject=new JSONObject(reresponse);
			context.ratesObject=context.jsonObject.getJSONObject("rates");
			keyarray = context.ratesObject.keys();
			ArrayList<String> listist = new ArrayList<String>();



			int i = 0;
			while(keyarray.hasNext()) {
				listist.add(i,  keyarray.next());
				Log.i(Mytag,"iterator"+listist);
				i++;
			}
            
			Collections.sort(listist);
			context.lista = listist;






		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}
}
