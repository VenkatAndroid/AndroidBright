package com.mobi.android3.QuranInEnglishUrduArabic;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.mobifusion.minerva_lib.sql_lite.SqlLiteHelper;

public class PagerCard extends Activity {

	SqlLiteHelper sql;
	int pagesId=0;
	String data="";
	String caption="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.card);
		
		sql = new SqlLiteHelper(
				this,SqlLiteHelper.databaseName);
		 Cursor data1=sql.getCats("select Data,PageId  from flash_cards");
		
		 
		  if(data1 != null)
	       {
	    	   if(data1.moveToFirst())
	    	   {
	    		   do
	    		   {
	    			   data = data1.getString(data1.getColumnIndex("Data"));
	    			   pagesId = data1.getInt(data1.getColumnIndex("PageId"));
	    		  
	    	} while (data1.moveToNext());
			}
	       }
	    	   
		 
		 
		  Log.e("hell","aasdasd"+pagesId);
		
		 Cursor gettitles = sql
					.getCats("Select Title  from page_instance where PageInstanceId ="
											+ pagesId + "");
		 if(gettitles != null)
	       {
	    	   if(gettitles.moveToFirst())
	    	   {
	    		   do
	    		   {
	    			   caption=gettitles.getString(gettitles.getColumnIndex("Title"));
	    		  
	    	} while (gettitles.moveToNext());
			}
	       }
		 
		Log.e("hello capt","aasdasd"+caption);
		 
		// final int categoryid=categories.getInt(categories.getColumnIndex("CategoryId"));
		// final int categoryid=categories.getInt(categories.getColumnIndex("CategoryId"));
		
		 TextView tv1=(TextView)findViewById(R.id.title);
		TextView tv=(TextView)findViewById(R.id.selctext);
		//tv.setText(ContentPagerActivity.data);
		tv1.setText(caption);
		tv.setText(data);
		tv.setTextSize(20);


	/*	MyPagerAdapter pageAdapter = new MyPagerAdapter(getSupportFragmentManager());
		ViewPager pager = (ViewPager)findViewById(R.id.myViewPager);
		pager.setAdapter(pageAdapter);
		*/
		
	}

}