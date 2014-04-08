package com.mobi.android3.QuranInEnglishUrduArabic;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.mobifusion.minerva_lib.R;
import com.mobifusion.minerva_lib.activities.ContentPagerMobi;
import com.mobifusion.minerva_lib.activities.MainMenuMobi;
import com.mobifusion.minerva_lib.dao.PageInstance;
import com.mobifusion.minerva_lib.data.DataStorage;
import com.mobifusion.minerva_lib.sql_lite.SqlLiteHelper;
import com.mobifusion.minerva_lib.util.ActivityUtil;





@SuppressWarnings("unused")
public  class Performance extends Activity implements OnClickListener  {
	

	public static List<PageInstance> pageInstances;
	 static PageInstance pInstance;
	 TableLayout scrtable,scrtable2,scrtable3= null;
	 TableRow scorerow,scorerowimg;

	 
	 int pageId;
	
		 int ordno;
		
		 String title,ptitle,titlename;
		 int ptagid;
		 int pginsid;
	 TextView even,start,evenimg;
	 ImageView img,userimg,img2;
	 ArrayList<Integer> pginsary=new ArrayList<Integer>();
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.performain);
		
		//RelativeLayout r1=(RelativeLayout)findViewById(R.id.main);

		
		final SqlLiteHelper triviadb = new SqlLiteHelper(
				this,SqlLiteHelper.databaseName);
		///TableRow scorerow = new TableRow(this);
		pInstance = new PageInstance();

		Cursor levelscores = triviadb
				.getCats("select Title,OrderNum,PageInstanceId from page_instance p");
		
		 titlename = levelscores.getString(levelscores
					.getColumnIndex("Title"));
		 ordno= levelscores.getInt(levelscores
					.getColumnIndex("OrderNum"));
		 
		scrtable = (TableLayout) findViewById(R.id.cattable3);
		scrtable2 = new TableLayout(this);
		scrtable3 = new TableLayout(this);
		evenimg = new TextView(this);
		 
		scorerowimg = new TableRow(this);
		 
		TableRow continuerow=new TableRow(this);
		TableRow titlerow=new TableRow(this);
		
		TableRow continuerow2=new TableRow(this);
		TableRow titlerow2=new TableRow(this);
		
		
		img= new ImageView(this);
		img2= new ImageView(this);
		userimg= new ImageView(this);
		FrameLayout fl=new FrameLayout(this);
	FrameLayout f2=new FrameLayout(this);
		TextView continueview=new TextView(this);
		TextView continueview2=new TextView(this);
		
		continueview.setTextSize(20);
		continueview.setText("Continue");
		continueview.setGravity(Gravity.CENTER);
		
		continueview2.setTextSize(20);
		continueview2.setText("Continue");
		continueview2.setGravity(Gravity.CENTER);
		
		TextView titleview=new TextView(this);
		titleview.setTextSize(20);
		titleview.setText("Title");
		titleview.setGravity(Gravity.CENTER);
		
		
		TextView titleview2=new TextView(this);
		titleview2.setTextSize(20);
		titleview2.setText("Title");
		titleview2.setGravity(Gravity.CENTER);
		
		
		Bitmap bmp=BitmapFactory.decodeResource(getResources(), R.drawable.list_bg);
		Bitmap bmp2=BitmapFactory.decodeResource(getResources(), R.drawable.img_btn_forward);
		int width=300;
		int height=420;
		Bitmap resizedbitmap=Bitmap.createScaledBitmap(bmp, width, height, true);
		int userimgwidth=100;
		int uesrimgheight=100;
		Bitmap resizedbitmap2=Bitmap.createScaledBitmap(bmp2, userimgwidth, uesrimgheight, true);
		img.setImageBitmap(resizedbitmap);
		
		int pwidth=300;
		int pheight=420;
		Bitmap resizedbitmap3=Bitmap.createScaledBitmap(bmp, pwidth, pheight, true);
	
	
		userimg.setImageBitmap(resizedbitmap2);
		userimg.setPadding(0, 0, 0, 20);
		fl.addView(img);
		f2.addView(img2);
		
		if(ordno % 2 ==1)
		{
			
			Log.e("ordno%2 if in not do loo","ordno%2 if in not do loo");
			evenimg.setPadding(350,50, 0,0);
			//evenimg.setText(titlename);
			img2.setPadding(350,50, 0,0);
			img2.setImageBitmap(resizedbitmap3);
		}
		else
		{
			evenimg.setPadding(80,70, 0, 0);
			img2.setPadding(80,70, 0, 0);
			//evenimg.setText(titlename);
			Log.e("ordno%2 else in not do loop","ordno%2 else in not do loop");
		}
		
		//scorerowimg.addView(evenimg);
		
	
		
		
		continuerow.addView(continueview);
		titlerow.addView(titleview);
		scrtable2.addView(continuerow);
		scrtable2.addView(titlerow);
		scrtable2.setGravity(Gravity.BOTTOM);
		continuerow.setGravity(Gravity.CENTER);
		titlerow.setPadding(0, 50, 0, 0);
		titlerow.setGravity(Gravity.CENTER);
		fl.addView(scrtable2);
		
		continuerow2.addView(continueview2);
		titlerow2.addView(titleview2);
		scrtable3.addView(continuerow2);
		scrtable3.addView(titlerow2);
		scrtable3.setGravity(Gravity.BOTTOM);
		continuerow2.setGravity(Gravity.RIGHT);
		continuerow2.setPadding(0,250, 100, 0);
		titlerow2.setPadding(0,100, 100, 0);
		titlerow2.setGravity(Gravity.RIGHT);
		f2.addView(scrtable3);
		
	scrtable.addView(f2);
	//scrtable.addView(titlerow2);
		//scrtable.addView(img2);
		//scrtable.addView(evenimg);
		
		
		scrtable.addView(userimg);
		
		scrtable.addView(fl);		
		
		
		if (levelscores != null) {
			if (levelscores.moveToFirst()) {
				do {
					
				 titlename = levelscores.getString(levelscores
							.getColumnIndex("Title"));
					Log.e("titlename",titlename);
				 ordno= levelscores.getInt(levelscores
							.getColumnIndex("OrderNum"));
				 pginsid=levelscores.getInt(levelscores
							.getColumnIndex("PageInstanceId"));
					
					pginsary.add(pginsid);
					
				 even = new TextView(this);
				 start = new TextView(this);
				 
				 scorerow = new TableRow(this);
			

				//img. setImageDrawable(getResources().getDrawable(R.drawable.list_bg));



					if(ordno % 2 ==1)
					{
						
						//Log.e("odrdno%2","odrdno%2");
						even.setPadding(500,100, 0,0);
						start.setPadding(420,40, 0,0);
						start.setText("Start");
						
						
					}
					else
					{
						even.setPadding(80,120, 0, 0);
						start.setPadding(80,60, 0,0);
						start.setText("Start");
						//Log.e("odrdno%2 else ","odrdno%2 else");
					}
					even.setText(titlename);
					scorerow.addView(even);
				
					even.setId(ordno);
					even.setOnClickListener(this);
					
					
					/*if(ordno%2==0)
					{
						
					
						even.setText(MainMenuMobi.pageInstances.get(i).getTitle());
						even.setTextColor(Color.WHITE);
						even.setPadding(315, 105, 15, 15);
				     
				        //scorerow.addView(even);
						
						even.setTextColor(Color.BLACK);
						even.setPadding(10,60, 0, 0);
						even.setText(titlename);
						even.setId(ordno);
						Log.e("ordno",even.getId()+"");
						even.setOnClickListener(this);
					even.setOnClickListener(new View.OnClickListener() {

				             @Override
				             public void onClick(View v) {
				                 Toast.makeText(getApplicationContext(), "CLICKED even"+v.getId(),
				                         Toast.LENGTH_LONG).show();
				                 
				                 int odda=v.getId();
				                 
				             	if (MainMenuMobi.freeToSendToContentPageFlag) {
				             		

				        			ActivityUtil.onTileClick(Performance.this, ContentPagerActivity.class,
				        					PageInstance.getPintanseId(ptitle,odda), ptagid, android.R.anim.fade_in,
				        					R.anim.right_slide_in);

				        			MainMenuMobi.freeToSendToContentPageFlag = false;
				         
				             	}
				             	}
				         });
						
					
						 
				
						scorerow.addView(even);
					
					
					}
		
				
					else
					{
						odd = new TextView(this);
						Log.e("ordno else",ordno+"");
					//odd.setText(MainMenuMobi.pageInstances.get(i).getTitle());
					odd.setTextColor(Color.BLACK);
					odd.setPadding(300,40, 0,0);
					odd.setText(titlename);
					odd.setId(ordno);
					odd.setOnClickListener(this);
					Log.e("ordno",odd.getId()+"");
					odd.setOnClickListener(new View.OnClickListener() {

			             @Override
			             public void onClick(View v) {
			                 Toast.makeText(getApplicationContext(), "CLICKED odd"+v.getId(),
			                         Toast.LENGTH_LONG).show();
			                 int odda=v.getId();
			                 
			             	if (MainMenuMobi.freeToSendToContentPageFlag) {
			             		

			        			ActivityUtil.onTileClick(Performance.this, ContentPagerActivity.class,
			        					PageInstance.getPintanseId(ptitle,odda), ptagid, android.R.anim.fade_in,
			        					R.anim.right_slide_in);

			        			MainMenuMobi.freeToSendToContentPageFlag = false;
			         
			             	}
			             	}
			         });
					
				
					scorerow1.addView(odd);
			
					}*/
					
				
					//r1.addView(even);
					/*if(ordno %2 ==0)
					{*/
					scrtable.addView(scorerow);
					
					//}
					
					
					
				
			
				
				} while (levelscores.moveToNext());
				
				
			
		}
	}
	}			
	public void onClick(View v) 
	{
		int odda=v.getId();
		int pgninsid=pginsary.get(odda-1);
		Toast.makeText(getApplicationContext(), "CLICKED odd"+v.getId(),
               Toast.LENGTH_LONG).show();
		int i=0;
		if(odda-1==i)
		{
			int j=odda;
			
			Toast.makeText(getApplicationContext(), j+"",
		               Toast.LENGTH_LONG).show();
		}
		else
		{
			Toast.makeText(getApplicationContext(), "-1 is not equl",
		               Toast.LENGTH_LONG).show();
		}
     	if (MainMenuMobi.freeToSendToContentPageFlag) 
     	{
     		
			ActivityUtil.onTileClick(Performance.this, ContentPagerActivity.class,
					pgninsid, ptagid, android.R.anim.fade_in,
					R.anim.right_slide_in);

			//MainMenuMobi.freeToSendToContentPageFlag = false;
     	}
	}
	
			
		

		
}
	
	
