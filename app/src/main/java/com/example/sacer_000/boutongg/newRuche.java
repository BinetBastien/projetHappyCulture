package com.example.sacer_000.boutongg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by sacer_000 on 16/09/2015.
 */
public class newRuche extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newruche);
        Button bVal = (Button) findViewById(R.id.btnNewRucheVal);
        Button bAnn = (Button) findViewById(R.id.btnNewRucheAnn);
        Intent intent = getIntent();
        final int rucher = intent.getIntExtra("n_rucher", 40);

        bVal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(newRuche.this, menuRuches.class);
                EditText et_num = (EditText) findViewById(R.id.n_ruche);
                EditText et_hausses = (EditText) findViewById(R.id.nb_hausses);
                if ((et_num.getText().toString().isEmpty()) || (et_hausses.getText().toString().isEmpty())) {
                    Toast.makeText(newRuche.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();

                } else {

                    int n_ruche = Integer.parseInt(et_num.getText().toString());
                    int hausses = Integer.parseInt(et_hausses.getText().toString());
                    int exemp = menuPcpl.bdd.verifierDonneesRuche(rucher, n_ruche);

                    if (exemp > -1) {
                        menuPcpl.bdd.insert_ruche(rucher, n_ruche, hausses, exemp);
                        finish();
                        startActivity(intent);
                    } else
                        Toast.makeText(newRuche.this, "La ruche existe déjà", Toast.LENGTH_SHORT).show();
                }


            }
        });
        bAnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(newRuche.this, menuRuches.class);
                finish();
                startActivity(intent);
            }
        });

    }
}
