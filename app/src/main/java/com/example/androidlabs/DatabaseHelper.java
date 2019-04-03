package com.example.androidlabs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.Arrays;




public class DatabaseHelper  extends SQLiteOpenHelper {

    public static final String DB_NAME = "MessagesDB";
    public static final String DB_TABLE = "Messages_Table";
    public static final String COL_MESSAGE = "Message";
    public static final String COL_ISSEND = "IsSend";
    public static final String COL_MESSAGEID = "MessageID";
     public static final String CREATE_TABLE = "CREATE TABLE "+DB_TABLE+" ("+COL_MESSAGEID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_MESSAGE+" TEXT, "+COL_ISSEND+" BIT);";
    public static final int  DATABASE_VERSION=1;

     DatabaseHelper(Context context) {

        super(context, DB_NAME, null, DATABASE_VERSION);

    }




    @Override

    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(CREATE_TABLE);
        }catch(SQLException e) {
            e.printStackTrace();
        }

    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);

        onCreate(db);

    }

    public long insertData(String message, boolean isSend) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_MESSAGE, message);

        if (isSend)

            contentValues.put(COL_ISSEND, 0);

        else

            contentValues.put(COL_ISSEND, 1);

        long result = db.insert(DB_TABLE, null, contentValues);


        return result;

    }




    //je viens de add ca
    public int deleteData(long id){
        int result;
        SQLiteDatabase database = this.getWritableDatabase();
         result=database.delete(DB_TABLE,COL_MESSAGEID+"=?",new String[]{Long.toString(id)});
        return result;
    }

    public Cursor viewData(){

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * from "+DB_TABLE;

        Cursor cursor = db.rawQuery(query, null);

        Log.e("Database Version Number", Integer.toString(db.getVersion()));

        Log.e("Column Count", Integer.toString(cursor.getColumnCount()));



        Log.e("Column Names", Arrays.toString(cursor.getColumnNames()));

        Log.e("Row Count", Integer.toString(cursor.getCount()));

        Log.e("Cursor Object", DatabaseUtils.dumpCursorToString(cursor));

        return cursor;

    }

    public Cursor viewData(String m_id){

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * from "+DB_TABLE +" where "+COL_MESSAGEID + "= \'" + m_id + "\'";;

        Cursor cursor = db.rawQuery(query, null);


        return cursor;

    }





}
