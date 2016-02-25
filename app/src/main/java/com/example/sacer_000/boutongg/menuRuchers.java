package com.example.sacer_000.boutongg;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class menuRuchers extends AppCompatActivity {

    private GestureDetector gestureDetector;
    private Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuruchers);
        Button nouveau = (Button) findViewById(R.id.boutonValider);
        Button retour = (Button) findViewById(R.id.boutonRetour);
    }

    private Cursor getCursor() {
        return this.c;
    }

    @Override
    protected void onResume() {
        super.onResume();
        c = menuPcpl.bdd.select_rucher();
        c.moveToFirst();
        final List<HashMap<String, String>> liste = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> element;
        while (!c.isAfterLast()) {
            element = new HashMap<String, String>();
            element.put("ID", "Rucher n°" + c.getInt(1));
            element.put("Adresse", "" + c.getString(2) + " " + c.getString(3) + " " + c.getInt(5) + " " + c.getString(4));
            liste.add(element);
            c.moveToNext();
        }
        ListAdapter adapter = new SimpleAdapter(this, liste, android.R.layout.simple_list_item_2, new String[]{"ID", "Adresse"}, new int[]{android.R.id.text1, android.R.id.text2});
        final ListView view = (ListView) findViewById(R.id.listeRucher);
        view.setAdapter(adapter);

        Button retour = (Button) findViewById(R.id.boutonRetour);
        Button nouveau = (Button) findViewById(R.id.boutonValider);
        nouveau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menuRuchers.this, newRucher.class);
                onPause();
                startActivity(intent);
            }
        });
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menuRuchers.this, menuPcpl.class);
                finish();
                startActivity(intent);
            }
        });

        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(menuRuchers.this, menuRuches.class);
                int n = getRucher(c, position);
                intent.putExtra("n_rucher", n);
                onPause();
                startActivity(intent);
            }
        });

        registerForContextMenu(view);
    }

    public int getRucher(Cursor c, int pos) {
        c.moveToPosition(pos);
        int n = c.getInt(1);
        return n;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.listeRucher) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            menu.setHeaderTitle("Options");
            menu.addSubMenu(Menu.NONE, 0, Menu.NONE, "Modifier");
            menu.addSubMenu(Menu.NONE, 1, Menu.NONE, "Supprimer");
            ListView view = (ListView) v;
            view.getSelectedItem();
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();


        int n = getRucher(getCursor(), menuInfo.position);
        int i = item.getItemId();
        switch (i) {
            case 0:
                Intent intent = new Intent(this.getBaseContext(), newRucher.class);
                intent.putExtra("mode", 1);
                intent.putExtra("n_rucher", n);
                this.onPause();
                startActivity(intent);
                break;
            case 1:
                menuPcpl.bdd.delete_rucher(n,"");
                break;

        }
        return super.onContextItemSelected(item);
    }
}




