package tn.iit.e_shopping.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 17/04/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

   public static final String database_name="e_shopping";
    public static final String tablename_name="produit";
    public static final String marque="MARQUE";
    public static final String caracteristique="CARACTERISTIQUE";
    public static final String prix="PRIX";
    public static final String adresse="ADRESSE";
    public static final String categorie="CATEGORIE";
    public static final String photo="PHOTO";
    public DataBaseHelper(Context context) {
        super(context, database_name, null, 1);
       // SQLiteDatabase   db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
   db.execSQL("create table " + tablename_name + "(id INTEGER PRIMARY KEY AUTOINCREMENT ," + marque + " TEXT," + caracteristique + " TEXT ," + prix + " INTEGER," + adresse + " TEXT," + categorie + " TEXT," + photo + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+tablename_name);
        onCreate(db);
    }


    public void insertDB(String marque,String caracteristique, String prix,String adresse,String categorie,String photo) {
        SQLiteDatabase   db=this.getWritableDatabase();
        ContentValues  cv=new ContentValues();
        cv.put(this.marque,marque);
        cv.put(this.caracteristique,caracteristique);
        cv.put(this.prix,prix);
        cv.put(this.adresse,adresse);
        cv.put(this.categorie,categorie);
        cv.put(this.photo, photo);
        db.insert(tablename_name, null, cv);

    }

    public Cursor getAllData()
    {
        SQLiteDatabase   db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+tablename_name,null);
        return res;
    }

    public Cursor getByName(String marque)
    {
        SQLiteDatabase   db=this.getWritableDatabase();
      //  Cursor res=db.rawQuery("select * from "+tablename_name+" where "+this.marque+"='"+marque+"'",null);
        Cursor res=db.rawQuery("select * from "+tablename_name+" where "+this.marque+"='"+marque+"'",null);
        return res;
    }

    public Cursor  rechercher(String prix_min,String prix_max,String nomproduit)
    {
        SQLiteDatabase   db=this.getWritableDatabase();
        String req= "select * from "+tablename_name+" where "+this.prix+">'"+prix_min+"' and "+this.prix+"< '"+prix_max+"' and "+this.marque+"='"+nomproduit+"'";
        Cursor res=db.rawQuery(req,null);
        return res;


    }


}
