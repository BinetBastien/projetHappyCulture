package com.example.sacer_000.boutongg;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Bastien on 18/11/2015.
 */
public class listeTraitements extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listetraitements);


        menuPcpl.bdd.open();
        menuPcpl.bdd.commit();
        final Cursor c = menuPcpl.bdd.select_traitement();
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


        ListAdapter adapter = new SimpleAdapter(this, liste, android.R.layout.simple_list_item_2, new String[]{"ID", "Libelle"}, new int[]{android.R.id.text1, android.R.id.text2});
        final ListView view = (ListView) findViewById(R.id.listTrait);
        view.setAdapter(adapter);

        Button retour = (Button) findViewById(R.id.retourListeTraitements);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(listeTraitements.this, ajoutTraitement.class);
                finish();
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.listetraitements);


        menuPcpl.bdd.open();
        menuPcpl.bdd.commit();
        final Cursor c = menuPcpl.bdd.selectTrait();
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


        ListAdapter adapter = new SimpleAdapter(this, liste, android.R.layout.simple_list_item_2, new String[]{"ID", "Libelle"}, new int[]{android.R.id.text1, android.R.id.text2});
        final ListView view = (ListView) findViewById(R.id.listTrait);
        view.setAdapter(adapter);

        Button retour = (Button) findViewById(R.id.retourListeTraitements);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(listeTraitements.this, ajoutTraitement.class);
                finish();
                startActivity(intent);
            }
        });
    }
}
