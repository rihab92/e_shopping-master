package tn.iit.e_shopping.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import tn.iit.e_shopping.R;
import tn.iit.e_shopping.database.DataBaseHelper;

public class RechercheProduit extends AppCompatActivity {

    EditText input;
    Button recherche;
    SeekBar sb_min,sb_max;
    TextView txt_min,txt_max;
    int valeur_max,valeur_min;
    DataBaseHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_produit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        input= (EditText) findViewById(R.id.txt_recherche);
        sb_max= (SeekBar) findViewById(R.id.seek_max);
        sb_min=(SeekBar) findViewById(R.id.seek_min);
        recherche= (Button) findViewById(R.id.bt_rechercher);
        txt_min= (TextView) findViewById(R.id.txt_prix_minimale_value);
        txt_max= (TextView) findViewById(R.id.txt_prix_maxiamale_value);
        dbh=new DataBaseHelper(this);

        sb_max.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txt_max.setText(String.valueOf(progress));
                valeur_max=progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        sb_min.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txt_min.setText(String.valueOf(progress));
                valeur_min=progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        recherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Cursor cr=dbh.rechercher(String.valueOf(valeur_min), String.valueOf(valeur_max), input.getText().toString());


                Intent ii=new Intent(RechercheProduit.this, ResultatRecherche.class);



                StringBuffer sb=new StringBuffer();
                ii.putExtra("nbre_resultat", String.valueOf(cr.getCount()));
               int i=0;

                while (cr.moveToNext())
                {
                    sb.append("id "+cr.getString(0));
                    sb.append("marque "+cr.getString(1));
                    sb.append("crarestique "+cr.getString(2));
                    sb.append("prix "+cr.getString(3));

                    ii.putExtra("magasin"+i, cr.getString(4)+"/"+cr.getString(3));

                }
              //  Toast.makeText(getApplicationContext(),sb.toString(),Toast.LENGTH_LONG).show();
                startActivity(ii);
            }
        });

    }

}
