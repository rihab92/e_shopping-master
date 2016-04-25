package tn.iit.e_shopping.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by user on 17/04/2016.
 */
public class DataBaseUserHelper extends SQLiteOpenHelper {
    public static final String database="e_shopping2";
    public static final String table="user";
    public static final String nom="NOM";
    public static final String prenom="PRENOM";
    public static final String adresse="ADR";
    public static final String email="EMAIL";
    public static final String login="LOGIN";
    public static final String pwd="PWD";
    public DataBaseUserHelper(Context context) {
        super(context, database, null, 1);
        SQLiteDatabase   db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + table + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + this.nom + " TEXT," + this.prenom + " TEXT," + this.adresse + " TEXT," + this.email + " TEXT," + this.login + " TEXT," + this.pwd + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+table);
        onCreate(db);
    }

    public void inscription(String nom,String prenom,String adresse,String email,String login,String pwd)
    {
        SQLiteDatabase   db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(this.nom,nom);
        cv.put(this.prenom,prenom);
        cv.put(this.adresse,adresse);
        cv.put(this.email,email);
        cv.put(this.login,login);
        cv.put(this.pwd, pwd);
        db.insert(this.table, null, cv);
    }

    public boolean  login(String login,String pwd)
    {
        SQLiteDatabase   db=this.getWritableDatabase();
       String req= "select * from "+this.table+" where "+this.login+"= '"+login+"' and "+this.pwd+"= '"+pwd+"' ";
        Cursor res=db.rawQuery(req,null);
        res.moveToFirst();
       if(res.getCount()>0)
       {
           return true;
       }else
       {
           return false;
       }

    }
}
