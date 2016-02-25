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
 * Created by Jeoffrey on 28/01/2016.
 */
public class menuRucherSup extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuruchersup);

        final Cursor c = menuPcpl.bdd.select_rucher_sup();


        final List<HashMap<String,String>> liste = new ArrayList<HashMap<String,String>>();
        HashMap<String,String> element;

        int i=0;
        while(i < c.getCount()){
            c.moveToNext();
            element=new HashMap<String, String>();
            element.put("ID", "Ruche n°"+c.getInt(0)+"   ----------->   "+c.getString(2));
            element.put("Valeur", c.getString(1));
            liste.add(element);
            i++;
        }

        ListAdapter adapter = new SimpleAdapter(this,liste,android.R.layout.simple_list_item_2,new String[]{"ID","Valeur"}, new int[]{android.R.id.text1, android.R.id.text2});
        final ListView view = (ListView) findViewById(R.id.listeRucher);
        view.setAdapter(adapter);

        Button retour = (Button) findViewById(R.id.retourOption);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menuRucherSup.this, menuSuppression.class);
                onPause();
                startActivity(intent);
            }
        });

    }
}
