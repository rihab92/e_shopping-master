package tn.iit.e_shopping.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import tn.iit.e_shopping.R;
import tn.iit.e_shopping.database.DataBaseUserHelper;

public class Inscription extends AppCompatActivity  implements View.OnClickListener{
    EditText nom,prenom,adresse,email,login,pwd;
    Button inscription;
    DataBaseUserHelper duh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
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

        nom= (EditText) findViewById(R.id.txt_ins_nom);
        prenom=(EditText) findViewById(R.id.txt_ins_prenom);
        adresse=(EditText) findViewById(R.id.txt_ins_adresse);
        email= (EditText) findViewById(R.id.txt_ins_email);
        login= (EditText) findViewById(R.id.txt_ins_login);
        pwd= (EditText) findViewById(R.id.txt_ins_pwd);
        inscription= (Button) findViewById(R.id.bt_Register);
        inscription.setOnClickListener(this);
        duh=new DataBaseUserHelper(this);
    }

    @Override
    public void onClick(View v) {
        duh.inscription(nom.getText().toString(),prenom.getText().toString(),adresse.getText().toString(),email.getText().toString(),login.getText().toString(),pwd.getText().toString());
        Intent in=new Intent(getApplicationContext(),Acceuil.class);
        startActivity(in);
    }
}
