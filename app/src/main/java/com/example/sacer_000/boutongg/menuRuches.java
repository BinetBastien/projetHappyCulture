package com.example.sacer_000.boutongg;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sacer_000 on 14/09/2015.
 */
public class menuRuches extends AppCompatActivity {

    private GestureDetector gestureDetector;
    private int n_rucher;
    private Cursor c;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuruches);
        Intent intentRecep = getIntent();
        n_rucher = intentRecep.getIntExtra("n_rucher", 1);

        this.setTitle("Rucher n°" + n_rucher);

        final Button nouveau = (Button) findViewById(R.id.boutonValider2);
        final Button retour = (Button) findViewById(R.id.boutonRetour2);

        gestureDetector = new GestureDetector(new SwipeGestureDetector());
    }


    @Override
    protected void onResume() {
        super.onResume();
        final Intent intentRecep = getIntent();
        n_rucher = intentRecep.getIntExtra("n_rucher", 1);
        c = menuPcpl.bdd.select_ruche(n_rucher);
        c.moveToFirst();
        final List<HashMap<String, String>> liste = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> element;
        while (!c.isAfterLast()) {
            element = new HashMap<String, String>();
            element.put("ID", "Ruche n°" + c.getInt(2));
            element.put("Valeur", c.getInt(3) + " hausse(s)");//3
            liste.add(element);
            c.moveToNext();
        }

        ListAdapter adapter = new SimpleAdapter(this, liste, android.R.layout.simple_list_item_2, new String[]{"ID", "Valeur"}, new int[]{android.R.id.text1, android.R.id.text2});
        final ListView view = (ListView) findViewById(R.id.listeRuche);
        view.setAdapter(adapter);

        final Button nouveau = (Button) findViewById(R.id.boutonValider2);
        final Button retour = (Button) findViewById(R.id.boutonRetour2);

        nouveau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menuRuches.this, newRuche.class);
                intent.putExtra("n_rucher", n_rucher);
                onPause();
                startActivity(intent);
            }
        });
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menuRuches.this, menuRuchers.class);
                finish();
                startActivity(intent);
            }
        });

        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(menuRuches.this, tabbedInfoRuches.class);
                int n = getNRuche(getCursor(), position);
                int exemp = getExempRuche(getCursor(), position);
                intent.putExtra("n_rucher", n_rucher);
                intent.putExtra("n_ruche", n);
                intent.putExtra("exemplaire", exemp);
                onPause();
                startActivity(intent);
            }
        });

        registerForContextMenu(view);
    }

    public Cursor getCursor() {
        return this.c;
    }

    public int getVersion(int n_rucher, int n_ruche){
        Cursor n = menuPcpl.bdd.select_ruche(n_rucher, n_ruche);
        n.moveToFirst();
        int ver = n.getInt(4);
        return ver;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.listeRuche) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            menu.setHeaderTitle("Options");
            menu.addSubMenu(Menu.NONE, 0, Menu.NONE, "Supprimer");
            ListView view = (ListView) v;
            view.getSelectedItem();
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int n = getNRuche(getCursor(), menuInfo.position);
        int ver = getVersion(n_rucher, n);
        menuPcpl.bdd.insert_supprimer_ruche(n_rucher, n, "", "", ver);
        onResume();
        return super.onContextItemSelected(item);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (gestureDetector.onTouchEvent(event)) {
            return true;
        }
        return super.onTouchEvent(event);
    }


    private void onLeftSwipe() {
        Intent intent = new Intent(menuRuches.this, TableauRucher.class);
        intent.putExtra("n_rucher", n_rucher);
        finish();
        startActivity(intent);
    }

    private void onRightSwipe() {
        Intent intent = new Intent(menuRuches.this, TableauRucher.class);
        intent.putExtra("n_rucher", n_rucher);
        finish();
        startActivity(intent);
    }

    public int getNRuche(Cursor c, int position) {
        c.moveToPosition(position);
        int i = c.getInt(2);
        return i;
    }

    public int getExempRuche(Cursor c, int position) {
        c.moveToPosition(position);
        int i = c.getInt(4);
        return i;
    }

    // Private class for gestures
    private class SwipeGestureDetector
            extends GestureDetector.SimpleOnGestureListener {
        // Swipe properties, you can change it to make the swipe
        // longer or shorter and speed
        private static final int SWIPE_MIN_DISTANCE = 120;
        private static final int SWIPE_MAX_OFF_PATH = 200;
        private static final int SWIPE_THRESHOLD_VELOCITY = 200;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2,
                               float velocityX, float velocityY) {
            try {
                float diffAbs = Math.abs(e1.getY() - e2.getY());
                float diff = e1.getX() - e2.getX();

                if (diffAbs > SWIPE_MAX_OFF_PATH)
                    return false;

                // Left swipe
                if (diff > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    menuRuches.this.onLeftSwipe();

                    // Right swipe
                } else if (-diff > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    menuRuches.this.onRightSwipe();
                }
            } catch (Exception e) {
                Log.e("YourActivity", "Error on gestures");
            }
            return false;
        }


    }

}
