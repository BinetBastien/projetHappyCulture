package com.example.sacer_000.boutongg;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

public class traitementRuche extends AppCompatActivity {

    Date dat = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traitement_ruche);

        Intent intent = getIntent();
        final int n_rucher = intent.getIntExtra("n_rucher", 1);
        final int n_ruche = intent.getIntExtra("n_ruche", 1);
        final int exemplaire = intent.getIntExtra("exemplaire", 1);
        this.setTitle("Traitement, rucher n°" + n_rucher + ", ruche n°" + n_ruche);
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
        final Spinner spinnerTraitement = (Spinner) findViewById(R.id.spinnerTraitement);
        spinnerTraitement.setAdapter(adapter);

        DatePicker d = (DatePicker) findViewById(R.id.dateTraitement);
        CalendarView calendar = d.getCalendarView();
        d.setSpinnersShown(false);
        GregorianCalendar g = new GregorianCalendar(d.getYear(), d.getMonth(), d.getDayOfMonth());
        dat = g.getTime();

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                GregorianCalendar g = new GregorianCalendar(year, month, dayOfMonth);
                dat = g.getTime();
            }
        });
        Button valider = (Button) findViewById(R.id.traitementVal);
        Button retour = (Button) findViewById(R.id.traitementRet);

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(traitementRuche.this, tabbedInfoRuches.class);
                int position = spinnerTraitement.getSelectedItemPosition();
                int n_traitement = getNTraitement(position);
                DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                String str = format.format(dat);
                menuPcpl.bdd.insert_obtenir(n_rucher, n_ruche, n_traitement, str, 0, exemplaire);
                Toast.makeText(traitementRuche.this, "Traitement ajouté", Toast.LENGTH_SHORT).show();
                intent.putExtra("n_rucher", n_rucher);
                intent.putExtra("n_ruche", n_ruche);
                intent.putExtra("exemplaire", exemplaire);
                finish();
                startActivity(intent);
            }
        });

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(traitementRuche.this, tabbedInfoRuches.class);
                intent.putExtra("n_rucher", n_rucher);
                intent.putExtra("n_ruche", n_ruche);
                intent.putExtra("exemplaire", exemplaire);
                finish();
                startActivity(intent);
            }
        });
    }

    public int getNTraitement(int position) {
        Cursor c = menuPcpl.bdd.select_traitement();
        c.moveToPosition(position);
        return c.getInt(0);
    }

}
