package com.example.demo123;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "LoginForm.db";
    public static final String CONTACTS_TABLE_NAME = "form";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_EMAIL = "email";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table form " +
                        "(id integer primary key, name text,email text)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS form");
        onCreate(db);
    }
    public boolean insertContact (String name, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        db.insert("form", null, contentValues);
        return true;
    }
    public ArrayList<Form> getAllCotacts() {
        ArrayList<Form> array_list = new ArrayList<Form>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from form", null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            Form form=new Form(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME))
                    ,res.getString(res.getColumnIndex(CONTACTS_COLUMN_EMAIL)));
            array_list.add(form);
            res.moveToNext();
        }
        return array_list;
    }
    public class Form {
        public String name;
        public String email;

        public Form(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
