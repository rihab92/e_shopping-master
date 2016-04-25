package tn.iit.e_shopping.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import tn.iit.e_shopping.R;
import tn.iit.e_shopping.database.DataBaseHelper;
import tn.iit.e_shopping.webservice.GetData;
import tn.iit.e_shopping.webservice.InsertData;

public class AjouterProduit extends AppCompatActivity {
    EditText marque,prix,categorie,carateristique;
    Spinner adresse;
    Button ajouter;
    DataBaseHelper dbh;
    Switch photo;;
    File imagef;
    ProgressBar bg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ajouter_produit);
        bg= (ProgressBar) findViewById(R.id.progress_ajouter);
        dbh=new DataBaseHelper(this);
        marque= (EditText) findViewById(R.id.et_nomproduit);
        prix=(EditText)findViewById(R.id.et_prix);
        adresse=(Spinner) findViewById(R.id.spinner);
        List<String> list = new ArrayList<String>();
        list.add("monoprix");
        list.add("carrefour market");
        list.add("les troix chemins");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adresse.setAdapter(dataAdapter);







        categorie=(EditText)findViewById(R.id.et_categorie);
        carateristique=(EditText)findViewById(R.id.et_caracte);
        ajouter =(Button)findViewById(R.id.bt_ajouter);
        photo= (Switch) findViewById(R.id.photo);
        photo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                Intent   it=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    imagef=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"test.jpg");
                    Uri tempuri= Uri.fromFile(imagef);
                    it.putExtra(MediaStore.EXTRA_OUTPUT,tempuri);
                    it.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,1);
                    startActivityForResult(it,0);
                } else {
                    // The toggle is disabled
                }
            }
        });


        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bg.setVisibility(View.VISIBLE);
               InsertData id=  new InsertData(getApplicationContext());
              // Toast.makeText(getApplicationContext(),marque.getText().toString()+""+carateristique.getText().toString()+""+prix.getText().toString()+""+adresse.getSelectedItem().toString()+""+categorie.getText().toString(),Toast.LENGTH_LONG).show();
             String texte=marque.getText().toString() + "/"+prix.getText().toString() + "/"+carateristique.getText().toString()+ "/"+adresse.getSelectedItem().toString() + "/"+categorie.getText().toString()+"/";






              id.execute(texte);

                bg.setVisibility(View.GONE);
             // Toast.makeText(getApplicationContext(), texte, Toast.LENGTH_SHORT).show();
                Intent in=new Intent(getApplicationContext(),Acceuil.class);
                startActivity(in);


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (resultCode == RESULT_OK) {
            Toast.makeText(this,imagef.getAbsolutePath(),Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.man_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.login:
                Intent  inte=new Intent(getApplicationContext(), Login.class);
                startActivity(inte);
                return true;
            case R.id.inscription:
                Intent  in=new Intent(getApplicationContext(), Inscription.class);
                startActivity(in);
                return true;

            case R.id.rechercher:
                Intent  ire=new Intent(getApplicationContext(), RechercheProduit.class);
                startActivity(ire);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
