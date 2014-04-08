package com.mobi.android3.QuranInEnglishUrduArabic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.actionbarsherlock.app.SherlockActivity;
import com.mobi.bright.minerva.dao.quiz.Answers;
import com.mobi.bright.minerva.dao.quiz.Question;
import com.mobi.bright.minerva.dao.quiz.Quiz;
import com.mobifusion.minerva_lib.R;
import com.mobifusion.minerva_lib.activities.MainMenuMobi;
import com.mobifusion.minerva_lib.activities.SplashMobi;
import com.mobifusion.minerva_lib.dao.PageInstance;
import com.mobifusion.minerva_lib.dao.Tags;
import com.mobifusion.minerva_lib.data.DataHolder;
import com.mobifusion.minerva_lib.sectionlist.EntryAdapter;
import com.mobifusion.minerva_lib.sectionlist.EntryAdapterQuiz;
import com.mobifusion.minerva_lib.sectionlist.EntryItem;
import com.mobifusion.minerva_lib.sectionlist.Item;
import com.mobifusion.minerva_lib.sectionlist.SectionItem;
import com.mobifusion.minerva_lib.sql_lite.SqlLiteHelper;
import com.mobifusion.minerva_lib.util.ActivityUtil;
import com.mobifusion.minerva_lib.util.BundleTags;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.SyncStateContract.Helpers;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;



@SuppressWarnings("unused")
public class Progress extends SherlockActivity   {
	SqlLiteHelper sql;
	//int quizid;
/*	String questionId;
	String queText;
	String optionslist;*/
	ArrayList<String> checkarray=new ArrayList<String>();
	public static ListView progCustomList;
	public static ArrayList<Item> items;
	
	LinearLayout linearLayout;
	public static List<PageInstance> pageInstances;
	List<Quiz> Quizinstances;
	List<Question>Questioninstances;
	List<Answers>Answersinstances;
	Question question;
	Answers answer;
	PageInstance pInstance;
	boolean freeToSendToContentPageFlag = true;
	public static List<Tags> tagsList = null;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progmain);
	final ArrayList<Integer> qdlist;
	linearLayout = (LinearLayout) findViewById(R.id.main);
	int pageId=1207;
	tagsList = DataHolder.getTagsListMap().get(pageId);// 204
		
	progCustomList = (ListView) getLayoutInflater().inflate(R.layout.proglist_main, null);
		

	pInstance = new PageInstance();
	items = new ArrayList<Item>();
	
	
	if(SplashMobi.dbsize.equals("small"))
		
	{
		
for (Tags tag : tagsList) {
		pageInstances = tag.getPageInstances();
        	
		Quizinstances=Quiz.getQuizzes();
		Questioninstances=Question.getQuest();
		Answersinstances=Answers.getAnsw();
		
		//This is for showing tags in the listview
		//items.add(new SectionItem(tag.getTag()));
		
		//this is for showing quiz and listview.
		for (int i = 0; i < pageInstances.size(); i++) {
			items.add(new EntryItem(pageInstances.get(i).getTitle(),Quizinstances.get(i).getName(),
					 pageInstances.get(
							i).getId(),pageInstances.get(i).getImage()));
		}

	}
	}
	EntryAdapterQuiz adapter = new EntryAdapterQuiz(getAssets(),Progress.this, items);
	
	progCustomList.setAdapter(adapter);

	sql = new SqlLiteHelper(
			this,SqlLiteHelper.databaseName);
//question =new Question();
//answer=new Answers();
progCustomList.setOnItemClickListener(new OnItemClickListener() {

		public void onItemClick(AdapterView<?> av, View v,
				int position, long id) {
			
			/*int levelid = 0;
			int totalquestions = 0;
			String categoryname = "";*/
			EntryItem item = (EntryItem) items.get(position);
BundleTags.pos=Integer.toString(position);

Log.e("possssss",Integer.toString(position));

		
			 //questionId=Integer.toString(Questioninstances.get(position).getQuestionId());*/
			// Log.e("questionId", questionId+"");
			doListViewClick(pInstance.getTagId(item.pinstnaceId),
					item.pinstnaceId,position); // pageInstances.get(position)

			//Log.e("optionslist", optionslist+"");
			// .getId());
			
		}

	});
	linearLayout.addView(progCustomList);
	Runtime.getRuntime().gc();
	
}
	
	
	private void doListViewClick(int tagId, int pageInstaceId,int position) {
		synchronized (this) {
			//if (freeToSendToContentPageFlag) {
		
				//Log.e("quizid123", questionId+"");
				
				ActivityUtil.onTileClick2(this,CopyOfContentPagerActivity.class,
						pageInstaceId, tagId, android.R.anim.fade_in,
						R.anim.right_slide_in,position);

				
				
			
		}// synchronized
	}
	
	public void showToast(String customText) {
		Toast.makeText(Progress.this, customText.trim(), Toast.LENGTH_LONG)
				.show();
	}

/*public  void alertSingleChoiceItems()
{
	String[] array=null;
	List<String> list=null;
	
	array = getResources().getStringArray(R.array.choices);
	int a=getResources().getStringArray(R.array.choices).length;
	
	
	Log.e("length is",a+"");
	
	list = new ArrayList<String>();
    list = Arrays.asList(array);
    ArrayList<String> checkarray = new ArrayList<String>(list);
	 Log.e("Options list",optionslist+"");
	//String[] strArray = new String[] {optionslist};
	 ArrayList<String> answerlist = new ArrayList<String>();
	 Cursor optionlist = sql
				.getCats("Select answerText from Answer where questionId="+questionId);
		if (optionlist != null) {
			if (optionlist.moveToFirst()) {
				do {
					
					final String answername = optionlist
							.getString(optionlist
									.getColumnIndex("answerText"));
					
					Log.e("answer name",answername+"");
					final int answer = optionlist.getInt(optionlist
							.getColumnIndex("Answer"));
					final int half = optionlist.getInt(optionlist
							.getColumnIndex("Half"));
				
					//answerlist.add(answername);
					//halfanswerlist.add(half);
					getResources().getStringArray(R.array.choices);
					checkarray.add(answername);
				} while (optionlist.moveToNext());
			}
		}
		
		
		
		array = checkarray.toArray(new String[list.size()]);
		
	    AlertDialog.Builder builder = new AlertDialog.Builder(Progress.this);
	    //Log.e("answer list",Answersinstances.get(0).getAnswerText());
	    // Set the dialog title
	    builder.setTitle(queText)
	    
		// specify the list array, the items to be selected by default (null for none),
		// and the listener through which to receive call backs when items are selected
	    // again, R.array.choices were set in the resources res/values/strings.xml
	    
	   
	    .setSingleChoiceItems(array, 0, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				showToast("Some actions maybe? Selected index: " + arg1);
			}
	
	    })
	           
		 // Set the action buttons
		.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {				
				
				int selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
				showToast("selectedPosition: " + selectedPosition);
				
			}
		})
		
		.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				
				
			}
		})		
		.show();
	    }
*/
}