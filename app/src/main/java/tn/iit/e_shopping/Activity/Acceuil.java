package tn.iit.e_shopping.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import tn.iit.e_shopping.R;
import tn.iit.e_shopping.webservice.GetData;

public class Acceuil extends AppCompatActivity  {
    Button recherche,ajouter,quitter,miseajour,liste;
    ProgressBar bg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        recherche= (Button) findViewById(R.id.bt_recherche_produit);
        ajouter= (Button) findViewById(R.id.bt_ajouter_produit);
        quitter= (Button) findViewById(R.id.bt_quitter);
        miseajour= (Button) findViewById(R.id.bt_mise_ajour);
        liste= (Button) findViewById(R.id.bt_liste_de_produit);
        bg= (ProgressBar) findViewById(R.id.progress);


        liste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),ListesProduit.class);
                startActivity(i);
            }
        });

        recherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),RechercheProduit.class);
                startActivity(i);
            }
        });



        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), AjouterProduit.class);
                startActivity(i);
            }
        });



        quitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });



        miseajour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bg.setVisibility(View.VISIBLE);
                GetData gd=new GetData(getApplicationContext());
                gd.execute();
                bg.setVisibility(View.INVISIBLE);

            }
        });
    }


}
