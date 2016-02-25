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

public class newPoids extends AppCompatActivity {
    private Date dat = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newpoids);
        Button bVal = (Button) findViewById(R.id.poidsValider);
        Button bAnn = (Button) findViewById(R.id.poidsAnnuler);
        Intent intent = getIntent();
        final int rucher = intent.getIntExtra("n_rucher", 1);
        final int ruche = intent.getIntExtra("n_ruche", 1);

        final DatePicker d = (DatePicker) findViewById(R.id.datepickerPoids);
        final CalendarView c = d.getCalendarView();
        d.setSpinnersShown(false);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.newpoids);
        Button bVal = (Button) findViewById(R.id.poidsValider);
        Button bAnn = (Button) findViewById(R.id.poidsAnnuler);
        Intent intent = getIntent();
        final int rucher = intent.getIntExtra("n_rucher", 1);
        final int ruche = intent.getIntExtra("n_ruche", 1);

        final DatePicker d = (DatePicker) findViewById(R.id.datepickerPoids);
        final CalendarView c = d.getCalendarView();
        d.setSpinnersShown(false);
        GregorianCalendar g = new GregorianCalendar(d.getDayOfMonth(), d.getMonth() + 1, d.getDayOfMonth());
        dat = g.getTime();
        c.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                                      @Override
                                      public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                                          month++;
                                          GregorianCalendar g = new GregorianCalendar(year, month, dayOfMonth);
                                          dat = g.getTime();
                                      }
                                  }
        );


        bVal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(newPoids.this, graphPoids.class);
                EditText et_poids = (EditText) findViewById(R.id.poids);
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String date = formatter.format(dat);
                float poids;
                int nbr_exemplaire;
                nbr_exemplaire = menuPcpl.bdd.verifierDonneesRuche(rucher, ruche);
                if (et_poids.getText().toString().isEmpty()) {
                    Toast.makeText(newPoids.this, "Veuillez remplir le champ POIDS", Toast.LENGTH_SHORT).show();
                } else {
                    poids = Integer.parseInt(et_poids.getText().toString());
                    menuPcpl.bdd.insert_peser(rucher, ruche, nbr_exemplaire, date, poids);
                    intent.putExtra("n_rucher", rucher);
                    intent.putExtra("n_ruche", ruche);
                    finish();
                    startActivity(intent);
                }
            }
        });
        bAnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(newPoids.this, graphPoids.class);
                intent.putExtra("n_rucher", rucher);
                intent.putExtra("n_ruche", ruche);
                finish();
                startActivity(intent);
            }
        });
    }
}