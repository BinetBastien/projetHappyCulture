package com.example.sacer_000.boutongg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class poidsRuche extends AppCompatActivity {
    Date date = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poids_ruche);
        final EditText etPoids = (EditText) findViewById(R.id.etPoids);
        final DatePicker d = (DatePicker) findViewById(R.id.poidsDate);

        Intent intent = getIntent();
        final int n_rucher = intent.getIntExtra("n_rucher", 1);
        final int n_ruche = intent.getIntExtra("n_ruche", 1);
        final int exemplaire = intent.getIntExtra("exemplaire", 1);
        this.setTitle("Poids, rucher n°" + n_rucher + ", ruche n°" + n_ruche);
        d.setSpinnersShown(false);
        CalendarView c = d.getCalendarView();
        GregorianCalendar g = new GregorianCalendar(d.getYear(), d.getMonth(), d.getDayOfMonth());
        date = g.getTime();
        c.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                GregorianCalendar g = new GregorianCalendar(d.getYear(), d.getMonth(), d.getDayOfMonth());
                date = g.getTime();
            }
        });

        Button valider = (Button) findViewById(R.id.poidsVal);
        Button annuler = (Button) findViewById(R.id.poidsAnn);

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etPoids.getText().toString().isEmpty()) {
                    Toast.makeText(poidsRuche.this, "Veuillez saisir un poids", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(poidsRuche.this, tabbedInfoRuches.class);
                    String str = etPoids.getText().toString();
                    float poids = Float.parseFloat(str);
                    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    String dat = format.format(date);
                    menuPcpl.bdd.insert_peser(n_rucher, n_ruche, exemplaire, dat, poids);
                    Toast.makeText(poidsRuche.this, "Poids ajouté", Toast.LENGTH_SHORT).show();
                    intent.putExtra("n_rucher", n_rucher);
                    intent.putExtra("n_ruche", n_ruche);
                    intent.putExtra("exemplaire", exemplaire);
                    startActivity(intent);
                    finish();
                }
            }
        });

        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(poidsRuche.this, tabbedInfoRuches.class);
                intent.putExtra("n_rucher", n_rucher);
                intent.putExtra("n_ruche", n_ruche);
                intent.putExtra("exemplaire", exemplaire);
                startActivity(intent);
                finish();
            }
        });

    }
}
