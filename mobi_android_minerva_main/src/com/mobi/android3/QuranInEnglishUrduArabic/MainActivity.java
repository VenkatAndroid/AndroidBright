package com.mobi.android3.QuranInEnglishUrduArabic;

import java.util.ArrayList;

import android.R.color;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.SherlockListFragment;
import com.mobifusion.minerva_lib.sql_lite.SqlLiteHelper;

public class MainActivity extends SherlockFragmentActivity   {
	 MyAdapter mAdapter;
	static SqlLiteHelper sql;
	static int pagesId=0;
	static String data="";
	static String caption="";
	ViewPager pager;
	static int NUM_ITEMS = 10;
	int flsscreencnt=0;
	 static ArrayList<String> arl=new ArrayList<String>();
	 static ArrayList<Integer> arl1=new ArrayList<Integer>();
	 public static  int width;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//setTheme(SampleList.THEME);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mAdapter = new MyAdapter(getSupportFragmentManager());
		pager=(ViewPager)findViewById(R.id.pager);
		 pager.setAdapter(mAdapter);
		
		 sql = new SqlLiteHelper(
					this,SqlLiteHelper.databaseName);
		 Display display = getWindowManager().getDefaultDisplay();
		 width = display.getWidth();
 		
		 Cursor data1=sql.getCats("select Data,PageId  from flash_cards");
		
		 if(data1 != null)
	       {
	    	   if(data1.moveToFirst())
	    	   {
	    		   do
	    		   {
	    			   data = data1.getString(data1.getColumnIndex("Data"));
	    			   pagesId = data1.getInt(data1.getColumnIndex("PageId"));
	    		  arl.add(data);
	    		  arl1.add(pagesId);
	    	} while (data1.moveToNext());
			}
	       }
		 
		 
		 Cursor flashcount=sql.getCats("select count(*) as flashcnt  from flash_cards");
			
		 if(flashcount != null)
	       {
	    	   if(flashcount.moveToFirst())
	    	   {
	    		   do
	    		   {
	    			  
	    			   flsscreencnt = flashcount.getInt(flashcount.getColumnIndex("flashcnt"));
	    		  
	    	} while (flashcount.moveToNext());
			}
	       }
	    	Log.e("flashscreencnt",flsscreencnt+"");   
		 NUM_ITEMS=flsscreencnt;
		 
		 
		 
		 
		
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
		 
		/* 
		Log.e("hello capt","aasdasd"+caption);
		 
		// final int categoryid=categories.getInt(categories.getColumnIndex("CategoryId"));
		// final int categoryid=categories.getInt(categories.getColumnIndex("CategoryId"));
		
		 //TextView tv1=(TextView)findViewById(R.id.title);
		//TextView tv=(TextView)findViewById(R.id.selctext);
		//tv.setText(ContentPagerActivity.data);
		
		TextView tv1=new TextView(this);
		TextView tv=new TextView(this);
		tv1.setText(caption);
		tv.setText(data);
		tv1.setTextSize(20);
		tv.setTextSize(20);
		pager.addView(tv1);
		pager.addView(tv);*/


	/*	MyPagerAdapter pageAdapter = new MyPagerAdapter(getSupportFragmentManager());
		ViewPager pager = (ViewPager)findViewById(R.id.myViewPager);
		pager.setAdapter(pageAdapter);
		*/
		
	}
	 public static class MyAdapter extends FragmentPagerAdapter {
	        public MyAdapter(FragmentManager fm) {
	            super(fm);
	        }

	        @Override
	        public int getCount() {
	            return NUM_ITEMS;
	        }

	        @Override
	        public Fragment getItem(int position) {
	            return ArrayListFragment.newInstance(position);
	        }
	    }

	    public static class ArrayListFragment extends SherlockListFragment {
	        int mNum;

	        /**
	         * Create a new instance of CountingFragment, providing "num"
	         * as an argument.
	         */
	        static ArrayListFragment newInstance(int num) {
	            ArrayListFragment f = new ArrayListFragment();

	            // Supply num input as an argument.
	            Bundle args = new Bundle();
	            args.putInt("num", num);
	            f.setArguments(args);

	            return f;
	        }

	        /**
	         * When creating, retrieve this instance's number from its arguments.
	         */
	        @Override
	        public void onCreate(Bundle savedInstanceState) {
	            super.onCreate(savedInstanceState);
	            mNum = getArguments() != null ? getArguments().getInt("num") : 1;
	        }

	        /**
	         * The Fragment's UI is just a simple text view showing its
	         * instance number.
	         */
	        @Override
	        public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                Bundle savedInstanceState) {
	            View v = inflater.inflate(R.layout.card, container, false);
	           
	            LinearLayout cardcolour=(LinearLayout)v.findViewById(R.id.forcolour);
	            if(mNum % 3 == 0)
	            {
	            cardcolour.setBackgroundColor(Color.parseColor("#FF6B6B"));
	            }
	            else if(mNum % 3 == 1)
	            {
	            	cardcolour.setBackgroundColor(Color.parseColor("#C7F464"));
	            }
	            else
	            {
	            	cardcolour.setBackgroundColor(Color.parseColor("#4ECDC4"));
	            }
	    		
	            Log.e("mnumber",NUM_ITEMS+"");
	            int pid=arl1.get(mNum);
	            String headdata=arl.get(mNum);
	            
	            Cursor gettitles = sql
						.getCats("Select Title  from page_instance where PageInstanceId ="
												+ pid + "");
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
	           
	            View tv1=(TextView)v.findViewById(R.id.cardtitle);
	            View tv=(TextView)v.findViewById(R.id.selctext);
	            View tv2=(TextView)v.findViewById(R.id.progress1);
	            Log.e("Width",width/2+"");
	            ((TextView)tv2).setMinimumHeight(15);
	            ((TextView)tv2).setMinimumWidth(width/2);
	            
	    		 ((TextView)tv1).setText(caption);
	    		 ((TextView)tv).setText(headdata);
	    		 ((TextView)tv1).setTextSize(40);
	    		 ((TextView)tv).setTextSize(30);
	            return v;
	        }
	        
	        @Override
	        public void onListItemClick(ListView l, View v, int position, long id) {
	            Log.i("FragmentList", "Item clicked: " + id);
	        }

	       
	    }

}
