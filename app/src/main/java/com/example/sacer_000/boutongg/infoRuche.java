package com.example.sacer_000.boutongg;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class infoRuche extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inforuche);
        Intent recepInt = getIntent();
        menuPcpl.bdd.open();
        final int n_rucher = recepInt.getIntExtra("n_rucher", 1);
        final int n_ruche = recepInt.getIntExtra("n_ruche", 1);
        final int exemp = recepInt.getIntExtra("exemplaire", 1);
        Cursor c = menuPcpl.bdd.selectTrait();
        c.moveToFirst();
        final List<HashMap<String, String>> liste = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> element;
        while (!c.isAfterLast()) {
            element = new HashMap<String, String>();
            element.put("ID", "Traitement n°" + c.getInt(0));
            element.put("Libelle", "" + c.getString(1));
            liste.add(element);
            c.moveToNext();
        }
        SpinnerAdapter adapter = new SimpleAdapter(this, liste, android.R.layout.simple_list_item_2, new String[]{"ID", "Libelle"}, new int[]{android.R.id.text1, android.R.id.text2});
        final Spinner spinnerTraitement = (Spinner) findViewById(R.id.listInfoRuche);
        spinnerTraitement.setAdapter(adapter);

        Cursor c1 = menuPcpl.bdd.selectNour();
        c1.moveToFirst();
        final List<HashMap<String, String>> liste1 = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> element1;
        while (!c1.isAfterLast()) {
            element1 = new HashMap<String, String>();
            element1.put("ID", "Nourrissement n°" + c1.getInt(0));
            element1.put("Libelle", "" + c1.getString(1));
            liste1.add(element1);
            c1.moveToNext();
        }
        SpinnerAdapter adapter1 = new SimpleAdapter(this, liste1, android.R.layout.simple_list_item_2, new String[]{"ID", "Libelle"}, new int[]{android.R.id.text1, android.R.id.text2});
        final Spinner spinnerNourrissement = (Spinner) findViewById(R.id.listInfoRuche2);
        spinnerNourrissement.setAdapter(adapter1);


        Button bRet = (Button) findViewById(R.id.infoRucheRet);
        bRet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(infoRuche.this, menuRuches.class);
                finish();
                startActivity(intent);
            }
        });

        Button poids = (Button) findViewById(R.id.infoPoids);
        poids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(infoRuche.this, graphPoids.class);
                intent.putExtra("n_rucher", n_rucher);
                intent.putExtra("n_ruche", n_ruche);
                onPause();
                startActivity(intent);
            }
        });

        final NumberPicker NPjour = (NumberPicker) findViewById(R.id.ruche_jour);
        final NumberPicker NPmois = (NumberPicker) findViewById(R.id.ruche_mois);
        final NumberPicker NPannee = (NumberPicker) findViewById(R.id.ruche_annee);
        NPjour.setMaxValue(31);
        NPjour.setMinValue(1);
        NPjour.setWrapSelectorWheel(true);
        NPmois.setMinValue(1);
        NPmois.setMaxValue(12);
        NPmois.setWrapSelectorWheel(true);
        NPannee.setMinValue(2000);
        NPannee.setMaxValue(2100);
        NPannee.setWrapSelectorWheel(true);

        Button ajoutTraitement = (Button) findViewById(R.id.rucheAjoutTraitement);
        ajoutTraitement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = spinnerTraitement.getSelectedItemPosition();
                int jour = NPjour.getValue();
                int mois = NPmois.getValue();
                int annee = NPannee.getValue();
                int n_traitement = getNTraitement(position);
                //menuPcpl.bdd.insert_obtenir(n_rucher, n_ruche, n_traitement, jour, mois, annee, 0, exemp);
                Toast.makeText(infoRuche.this, "Traitement ajouté", Toast.LENGTH_SHORT).show();
            }
        });

        Button ajoutNourrissement = (Button) findViewById(R.id.rucheAjoutNourrissement);
        ajoutNourrissement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = spinnerNourrissement.getSelectedItemPosition();
                int jour = NPjour.getValue();
                int mois = NPmois.getValue();
                int annee = NPannee.getValue();
                int n_nourrissement = getNNourrissement(position);
                // menuPcpl.bdd.insert_recevoir(n_rucher, n_ruche, n_nourrissement, jour, mois, annee, 0, "", exemp);
                Toast.makeText(infoRuche.this, "Nourrissement ajouté", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public int getNTraitement(int position) {
        Cursor c = menuPcpl.bdd.select_traitement();
        c.moveToPosition(position);
        return c.getInt(0);
    }

    public int getNNourrissement(int position) {
        Cursor c = menuPcpl.bdd.select_nourrissement();
        c.moveToPosition(position);
        return c.getInt(0);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }
}
