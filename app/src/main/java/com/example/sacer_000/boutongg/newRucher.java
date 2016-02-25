package com.example.sacer_000.boutongg;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by sacer_000 on 16/09/2015.
 */
public class newRucher extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newrucher);
        Intent intent = getIntent();
        int mode = intent.getIntExtra("mode", 0);
        int n_rucher = intent.getIntExtra("n_rucher", 1);
        Button bVal = (Button) findViewById(R.id.graphValider);
        Button bAnn = (Button) findViewById(R.id.graphAnnuler);

        final EditText et_n_rucher = (EditText) findViewById(R.id.n_rucher);
        final EditText et_num_adresse = (EditText) findViewById(R.id.num_adresse);
        final EditText et_nom_adresse = (EditText) findViewById(R.id.nom_adresse);
        final EditText et_ville_adresse = (EditText) findViewById(R.id.ville_adresse);
        final EditText et_cp_adresse = (EditText) findViewById(R.id.cp_adresse);
        if (mode == 1) {
            Cursor c = menuPcpl.bdd.select_rucher(n_rucher);
            c.moveToFirst();
            int num = c.getInt(3);
            String nom = c.getString(4);
            String ville = c.getString(5);
            int cp = c.getInt(6);
            String numero = "" + n_rucher;
            et_n_rucher.setText(numero);
            et_nom_adresse.setText(nom);
            et_num_adresse.setText("" + num);
            et_cp_adresse.setText("" + cp);
            et_ville_adresse.setText(ville);
        }

        bVal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(newRucher.this, menuRuchers.class);

                int n_rucher, cp;
                String num = "";
                String nom, ville;

                Context c = newRucher.this;
                if (et_n_rucher.getText().toString().isEmpty() || et_nom_adresse.getText().toString().isEmpty() || et_ville_adresse.getText().toString().isEmpty() || et_cp_adresse.getText().toString().isEmpty()) {
                    Toast.makeText(newRucher.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                } else {
                    n_rucher = Integer.parseInt(et_n_rucher.getText().toString());
                    if (!et_num_adresse.getText().toString().isEmpty())
                        num = "" + Integer.parseInt(et_num_adresse.getText().toString());
                    else
                        num = "";
                    nom = et_nom_adresse.getText().toString();
                    ville = et_ville_adresse.getText().toString();
                    cp = Integer.parseInt(et_cp_adresse.getText().toString());
                    int version = menuPcpl.bdd.verifierDonneesRucher(n_rucher);
                    if (version != -1) {
                        menuPcpl.bdd.insert_rucher(n_rucher, version, num, nom, ville, cp);
                        finish();
                        startActivity(intent);
                    } else
                        Toast.makeText(c, "Le rucher existe déjà", Toast.LENGTH_SHORT).show();
                }
            }
        });
        bAnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(newRucher.this, menuRuchers.class);
                finish();
                startActivity(intent);
            }
        });
    }
}
