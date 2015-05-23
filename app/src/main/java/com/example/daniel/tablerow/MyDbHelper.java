package com.example.daniel.tablerow;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MyDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "food1";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "foodTable";
    public static final String CREATE_TABLE= "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            " fecha TEXT, tipo TEXT, desayuno INTEGER NULL, almuerzo INTEGER NULL, comida INTEGER NULL, merienda INTEGER NULL, cena INTEGER NULL" +
            " , objetivo NULL)";
    public static final String DELETE_TABLE="DROP TABLE IF EXISTS " + TABLE_NAME;

    public MyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);

    }
    //Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DELETE_TABLE);
        //Create tables again
        onCreate(db);
    }

    public void insertData(String fecha,String tipo,int desayuno,int almuerzo,int comida
            ,int merienda ,int cena,int objetivo){

        // Open the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // Start the transaction.
        db.beginTransaction();
        ContentValues values;

        try
        {
            values = new ContentValues();
            values.put("fecha", fecha);
            values.put("tipo", tipo);
            values.put("desayuno", desayuno);
            values.put("almuerzo", almuerzo);
            values.put("comida", comida);
            values.put("merienda", merienda);
            values.put("cena", cena);

            values.put("objetivo", objetivo);
            // Insert Row
            long i = db.insert(TABLE_NAME, null, values);
            Log.i("Insert", i + "");
            // Insert into database successfully.
            db.setTransactionSuccessful();

        }
        catch (SQLiteException e)
        {
            e.printStackTrace();

        }
        finally
        {
            db.endTransaction();
            // End the transaction.
            db.close();
            // Close database
        }

    }




}

    /*public static final String DATABASE_NAME = "foodDB";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "foodTable";
    public static final String CREATE_TABLE_OUTLET= "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            " tipo TEXT, desayuno TEXT NULL, almuerzo TEXT NULL, comida TEXT NULL, merienda TEXT NULL, cena TEXT NULL)";
    public static final String DELETE_TABLE_OUTLET="DROP TABLE IF EXISTS " + TABLE_NAME;


    public MyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(CREATE_TABLE_OUTLET);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + DELETE_TABLE_OUTLET);
            onCreate(db);

    }

    public void insertData(String tipo,String desayuno,String almuerzo,String comida
            ,String merienda ,String cena) {

        // Open the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // Start the transaction.
        db.beginTransaction();
        ContentValues values;

        try {
            values = new ContentValues();
            values.put("tipo", tipo);
            values.put("desayuno", desayuno);
            values.put("almuerzo", almuerzo);
            values.put("comida", comida);
            values.put("merienda", merienda);
            values.put("cena", cena);
            // Insert Row
            long i = db.insert(TABLE_NAME, null, values);
            Log.i("Insert", i + "");
            // Insert into database successfully.
            db.setTransactionSuccessful();

        } catch (SQLiteException e) {
            e.printStackTrace();

        } finally {
            db.endTransaction();
            // End the transaction.
            db.close();
            // Close database
        }
    }

    }*/
