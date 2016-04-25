package tn.iit.e_shopping.Activity;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import tn.iit.e_shopping.R;
import tn.iit.e_shopping.database.DataBaseHelper;

public class ListesProduit extends ListActivity {
    DataBaseHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listes_produit);
        dbh=new DataBaseHelper(this);

        StringBuffer sb=new StringBuffer();
        Cursor cr=dbh.getAllData();
        String maliste[]=new String[cr.getCount()] ;
        int  i=0;
        while (cr.moveToNext())
        {
            maliste[i]=cr.getString(1);
            i++;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListesProduit.this, android.R.layout.simple_list_item_1, maliste);
        setListAdapter(adapter);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        String text = l.getItemAtPosition(position).toString();
        Toast.makeText(this,text,Toast.LENGTH_LONG).show();
        Intent  i=new Intent(this,InformationProduit.class);
        i.putExtra("marque",text);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.liste_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.login:
                Intent inte = new Intent(getApplicationContext(), Login.class);
                startActivity(inte);
                return true;
            case R.id.inscription:
                Intent in = new Intent(getApplicationContext(), Inscription.class);
                startActivity(in);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




}
