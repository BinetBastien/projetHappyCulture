package com.example.sacer_000.boutongg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by sacer_000 on 08/10/2015.
 */
public class ajoutTraitement extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajouttraitement);
        menuPcpl.bdd.open();

        Button valider = (Button) findViewById(R.id.btnAjout);
        Button annuler = (Button) findViewById(R.id.btnAnnuler);
        Button voir = (Button) findViewById(R.id.btnVoir);

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText libelle = (EditText) findViewById(R.id.libTraitement);

                if (libelle.getText().toString().isEmpty()) {
                    Toast.makeText(ajoutTraitement.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                } else {
                    String libTrait = libelle.getText().toString();
                    int numTrait = menuPcpl.bdd.select_max_traitement();

                    menuPcpl.bdd.insert_traitement(numTrait, libTrait, " ", " ");
                    Toast.makeText(ajoutTraitement.this, "Traitement ajouté", Toast.LENGTH_LONG).show();
                    libelle.setText("", null);
                }
            }
        });

        voir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ajoutTraitement.this, listeTraitements.class);
                onPause();
                startActivity(intent);
            }
        });

        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ajoutTraitement.this, option.class);
                finish();
                startActivity(intent);
            }
        });
    }
}
