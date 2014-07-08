package com.osi.tools.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ToolsDataBase {



	public static final String DATABASE_NAME = "toolsdatabase.db";
	public static final int DATABASE_VERSION = 1;


	public static final String TABLE_Events = "_events";
	public static final String TABLE_RINGER = "_ringermode";
	public static final String TABLE_SMSDETAILS = "_smsdetailstable";
	public static final String TABLE_APP_INFO="appinfo";
	public static final String TABLE_BLACKLIST = "_blacklist";
	public static final String TABLE_TEMPLATES = "_templates";
	public static final String TABLE_SETTINGS = "_setting";
	public static final String TABLE_MANAGE = "_manage";
	public static final String TABLE_BACKUP = "_backup";
	public static final String TABLE_BUTTONMODE = "_buttonmode";

	public static final String ID = "id";
	public static final String EVENTNAME = "EventName";
	public static final String DESCRIPTION = "description";
	public static final String STARTDATE = "startdate";
	public static final String ENDDATE = "enddate";
	public static final String AUDIO = "audio";

	public static final String MODE = "prevmode";
	public static final String PHONE = "phonenumber";
	public static final String MESSAGE = "message";
	public static final String TIMESTAMP = "timestamp";
	public static final String PACKAGENAME="packagename";
	public static final String APPINFO="appinformation";
	public static final String DATE_TIME="dateandtime";
	public static final String APP_NAME="appname";
	public static final String NAME = "Name";
	public static final String Phone = "phonenumber";
	public static final String SMS = "smsval";
	public static final String CALL = "calval";
	public static final String FROM = "fromtime";
	public static final String TO = "totime";
	public static final String TYPE = "type";
	public static final String TEMPLATE = "template";
	public static final String BLOCK = "blocking";
	public static final String CALLOPTION = "callsoption";
	public static final String FILETYPE = "type";
	public static final String FILENAME = "name";
	public static final String FILEDATE = "date";
	public static final String PATH = "path";
	public static final String ICONTAG = "icontag";
	public static final String LASTUPDATE = "lastupdate";
	public static final String STORAGE = "intstorage";
	public static final String MODEBTN = "btnmode";
	public static final String USERMODE = "usermode";

	public static final String CREATE_TABLE = "create table ";
	public static final String TEXT_NOTNULL = " TEXT NOT NULL ";
	public static final String TEXT = " TEXT ";
	public static final String INTEGER = " INTEGER ";
	public static final String INTEGER_AUTOINCREMENT = " INTEGER  primary key autoincrement";


	private static final String eventQuery = CREATE_TABLE + TABLE_Events+"(" + 
			ID + INTEGER_AUTOINCREMENT + "," + 
			EVENTNAME + TEXT_NOTNULL + "," + 
			DESCRIPTION + TEXT +","+ STARTDATE + TEXT + ","+ENDDATE+TEXT+");";

	private static final String modeQuery = CREATE_TABLE + TABLE_RINGER+"(" + 
			ID + INTEGER_AUTOINCREMENT + ","+MODE+INTEGER+");";

	private static final String smsdetailsQuery = CREATE_TABLE + TABLE_SMSDETAILS+"(" + 
			ID + INTEGER_AUTOINCREMENT + "," + 
			PHONE + TEXT_NOTNULL +","+ MESSAGE + TEXT_NOTNULL +","+ TIMESTAMP +INTEGER+");";

	private static final String appinformation=CREATE_TABLE +TABLE_APP_INFO+"(" + 
			ID + INTEGER_AUTOINCREMENT + "," + 
			PACKAGENAME + TEXT_NOTNULL + "," + 
			APPINFO + TEXT +","+DATE_TIME+INTEGER+","+APP_NAME+TEXT+");";
	
	private static final String blacklistQuery = CREATE_TABLE + TABLE_BLACKLIST+"(" + 
			ID + INTEGER_AUTOINCREMENT + "," + 
			NAME + TEXT_NOTNULL + "," + 
			Phone + TEXT_NOTNULL +","+ SMS + TEXT + ","+ CALL +TEXT+","+FROM+TEXT+","+ TO +TEXT+
			","+ TYPE +INTEGER+","+ TEMPLATE +TEXT+");";

	private static final String templateQuery = CREATE_TABLE + TABLE_TEMPLATES+"(" + 
			ID + INTEGER_AUTOINCREMENT + "," + 
			TEMPLATE + TEXT_NOTNULL +");";
	
	private static final String buttonmodeQuery = CREATE_TABLE + TABLE_BUTTONMODE+"(" + 
			ID + INTEGER_AUTOINCREMENT + "," + 
			MODEBTN + TEXT + "," +USERMODE + INTEGER +"," +AUDIO + TEXT +");";

	private static final String settingQuery = CREATE_TABLE + TABLE_SETTINGS+"(" + 
			ID + INTEGER_AUTOINCREMENT + "," + 
			BLOCK + TEXT_NOTNULL +","+ CALLOPTION +TEXT+","+ STORAGE +TEXT+");";

	private static final String manageQuery = CREATE_TABLE + TABLE_MANAGE+"(" + 
			ID + INTEGER_AUTOINCREMENT + "," + 
			FILETYPE + TEXT +","+ FILENAME +TEXT+","+ FILEDATE +TEXT+","+ PATH +TEXT+","+ ICONTAG +INTEGER+");";


	private static final String backupQuery = CREATE_TABLE + TABLE_BACKUP+"(" + 
			ID + INTEGER_AUTOINCREMENT + "," + 
			LASTUPDATE + TEXT +");";


	private SQLiteDatabase db;

	public ToolsDataBase(Context con)
	{

		try {   
			db = con.openOrCreateDatabase(DATABASE_NAME, DATABASE_VERSION, null);
			db.execSQL(eventQuery);
			db.execSQL(modeQuery);
			db.execSQL(smsdetailsQuery);
			db.execSQL(appinformation);
			db.execSQL(blacklistQuery);
			db.execSQL(templateQuery);
			db.execSQL(settingQuery);
			db.execSQL(manageQuery);
			db.execSQL(backupQuery);
			db.execSQL(buttonmodeQuery);

		} catch (Exception e) {
			e.printStackTrace();				
		} finally{
			db.close();
		}

	}




}
