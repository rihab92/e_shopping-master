package tn.iit.e_shopping.Activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import tn.iit.e_shopping.R;
import tn.iit.e_shopping.database.DataBaseHelper;

public class InformationProduit extends AppCompatActivity {
   TextView   marque,prix,adresse,categorie,carateristique;
    DataBaseHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_produit);

     marque= (TextView) findViewById(R.id.texte_marque);
        carateristique=(TextView)findViewById(R.id.texte_caracteristique);
        prix=(TextView)findViewById(R.id.texte_prix);
        adresse=(TextView)findViewById(R.id.texte_adresse);
        categorie=(TextView)findViewById(R.id.texte_categorie);



      String  data = getIntent().getExtras().getString("marque");
        //Toast.makeText(this, data, Toast.LENGTH_LONG).show();
        dbh=new DataBaseHelper(this);
        dbh.getByName(data);
        StringBuffer sb=new StringBuffer();
        Cursor cr=dbh.getByName(data);
        cr.moveToFirst();

            marque.setText("marque : " + cr.getString(1));
            carateristique.setText("caracteristique : " + cr.getString(2));
            prix.setText("prix : " + cr.getString(3));
            adresse.setText("adresse : " +cr.getString(4));
            categorie.setText("categorie : " +cr.getString(5));


    }

}
