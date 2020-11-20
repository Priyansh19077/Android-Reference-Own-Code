package com.example.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CUSTOMER_AGE = "CUSTOMER_AGE";
    public static final String COLUMN_ACTIVE_CUSTOMER = "ACTIVE_CUSTOMER";
    public static final String COLUMN_ID = "ID";
    public DataBaseHelper(@Nullable Context context) {
        super(context, "customer.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement= "CREATE TABLE " + CUSTOMER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CUSTOMER_NAME + " TEXT, " + COLUMN_CUSTOMER_AGE + " INT, " + COLUMN_ACTIVE_CUSTOMER + " BOOL)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
    public boolean addOne(CustomerModel customerModel)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_CUSTOMER_NAME,customerModel.getName());
        cv.put(COLUMN_CUSTOMER_AGE,customerModel.getAge());
        cv.put(COLUMN_ACTIVE_CUSTOMER,customerModel.isActive());
        long insert=db.insert(CUSTOMER_TABLE,null,cv);
        if(insert>0)
            return true;
        return false;
    }
    public List<CustomerModel> getEveryone()
    {
        List<CustomerModel> list=new ArrayList<>();
        String s="SELECT * FROM "+ CUSTOMER_TABLE;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(s,null);
        if(cursor.moveToFirst()) {
            //loop through the results and create new customer object for each row
            do {
                int customerID = cursor.getInt(0);
                String customer_name = cursor.getString(1);
                int customer_age = cursor.getInt(2);
                boolean activity = cursor.getInt(3) == 0 ? false : true;
                CustomerModel cm = new CustomerModel(customerID, customer_name, customer_age, activity);
                list.add(cm);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }
    public boolean deleteOne(CustomerModel customerModel)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String query="DELETE FROM "+CUSTOMER_TABLE+" WHERE "+COLUMN_ID+" = "+customerModel.getId();
        final Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst())
            return true;
        return false;
    }
}
