package com.example.sacer_000.boutongg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Bastien on 19/11/2015.
 */
public class ajoutNourrissement extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajout_nourrissement);
        menuPcpl.bdd.open();

        Button valider = (Button) findViewById(R.id.btnAjout);
        Button annuler = (Button) findViewById(R.id.btnAnnuler);
        Button voir = (Button) findViewById(R.id.btnVoir);

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText libelle = (EditText) findViewById(R.id.libNourrissement);

                if (libelle.getText().toString().isEmpty()) {
                    Toast.makeText(ajoutNourrissement.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                } else {
                    int numNour = menuPcpl.bdd.select_max_nourrissement();
                    String libNour = libelle.getText().toString();
                    menuPcpl.bdd.insert_nourrissement(numNour, libNour);
                    Toast.makeText(ajoutNourrissement.this, "Nourrissement ajouté", Toast.LENGTH_LONG).show();
                    libelle.setText("", null);
                }
            }
        });

        voir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ajoutNourrissement.this, listeNourrissement.class);
                onPause();
                startActivity(intent);
            }
        });

        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ajoutNourrissement.this, option.class);
                finish();
                startActivity(intent);
            }
        });
    }
}
