package com.example.dbex;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper
{

    public static final String DB_NAME="user.db";
    public static final String TABLE_NAME="info";
    public static final String ID="id";
    public static final String NAME="name";
    public static final String PHONE="phone";
    public static final int DB_VERSION=1;


    public DbHelper(@Nullable Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String query= "create table " + TABLE_NAME + " (" +ID +
                " integer primary key autoincrement, " +NAME+" text, "+PHONE+ " text )";
           sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        String upquery="drop table if exists "+TABLE_NAME;
        sqLiteDatabase.execSQL(upquery);
    }

    public long save(Model m)
    {

        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(NAME,m.getName());
        values.put(PHONE,m.getPhone());

        long id=db.insert(TABLE_NAME,ID,values);


        return  id;

    }
    public List<Model>viewdata()
    {
        List<Model>list=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        String col[]={ID,NAME,PHONE};
        Cursor cursor=db.query(TABLE_NAME,col,null,null,null,null,null);
        while(cursor.moveToNext())
        {
            int id=cursor.getInt(0);
            String name=cursor.getString(1);
            String phone=cursor.getString(2);

            Model  m= new Model();
            m.setId(id);
            m.setName(name);
            m.setPhone(phone);
            list.add(m);
        }

        return list;
    }

    public void delete(int id)
    {

        SQLiteDatabase db=getWritableDatabase();
        String deletequery=ID+"="+id;
        db.delete(TABLE_NAME,deletequery,null);


    }
    public void Update(Model m)
    {

        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(ID,m.getId());
        values.put(NAME,m.getName());
        values.put(PHONE,m.getPhone());
        String wherequery=ID+"="+m.getId();
        db.update(TABLE_NAME,values,wherequery,null);
    }

   /* public List<Model> viewdata()
    {
        List<Model>list=new ArrayList<>();

        SQLiteDatabase db=getReadableDatabase();
        String col[]={ID,NAME,PHONE};

        Cursor cursor=db.query(TABLE_NAME,col,null,null,null,null,null,null);
        while(cursor.moveToNext())
        {
            int id=cursor.getInt(0);
            String name=cursor.getString(1);
            String phone=cursor.getString(2);

            Model m =new Model();
            m.setId(id);
            m.setName(name);
            m.setPhone(phone);
            list.add(m);

        }



        return list;


    }


    public void update(Model m)
    {

        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(NAME,m.getName());
        values.put(PHONE,m.getPhone());
        String update1=ID+"="+m.getId();
       db.update(TABLE_NAME,values,update1,null);




    }

    public void delete(int id)
    {

        SQLiteDatabase db=getWritableDatabase();
        String deletequery=ID+"="+id;
        db.delete(TABLE_NAME,deletequery,null);


    }
*/

}
