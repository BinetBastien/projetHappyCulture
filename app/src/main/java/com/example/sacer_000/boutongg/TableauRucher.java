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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Utilisateur on 11/01/2016.
 */
public class TableauRucher extends AppCompatActivity {

    private graphique tab_graph = new graphique();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tableaurucher);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intentRecep = getIntent();
        final int n_rucher = intentRecep.getIntExtra("n_rucher", 1);
        tab_graph.donnee_graph_recolte(menuPcpl.bdd, n_rucher);

        this.setTitle("Rucher n°" + n_rucher);
        final List<HashMap<String, String>> liste = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> element;


        for (int i = 0; i < tab_graph.getTab_qte().size(); i++) {
            element = new HashMap<String, String>();
            element.put("ID", "" + (tab_graph.getTab_date().get(i)));
            element.put("Valeur", tab_graph.getTab_qte().get(i) + " Kgs");
            liste.add(element);
        }

        ListAdapter adapter = new SimpleAdapter(this, liste, android.R.layout.simple_list_item_2, new String[]{"ID", "Valeur"}, new int[]{android.R.id.text1, android.R.id.text2});
        final ListView view = (ListView) findViewById(R.id.listeRuche);
        view.setAdapter(adapter);

        final Button newrecolte = (Button) findViewById(R.id.boutonValider2);
        final Button retour = (Button) findViewById(R.id.boutonRetour2);

        newrecolte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TableauRucher.this, newRecolte.class);
                intent.putExtra("n_rucher", n_rucher);
                finish();
                startActivity(intent);
            }
        });

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TableauRucher.this, menuRuches.class);
                intent.putExtra("n_rucher", n_rucher);
                finish();
                startActivity(intent);
            }
        });

        TextView zonetext = (TextView) findViewById(R.id.quantite);
        final Cursor recolte = menuPcpl.bdd.select_recolter(n_rucher);
        final String text;
        if (recolte.getCount() <= 0)
            text = "" + 0;
        else {
            recolte.moveToFirst();
            text = "" + recolte.getFloat(0);
        }
        zonetext.setText(text);
        zonetext.setText("Quantité de Miel : " + text + " Kgs");
        this.setTitle("Rucher " + n_rucher);
    }
}
