package com.example.sacer_000.boutongg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

/**
 * Created by Jeoffrey on 22/10/2015.
 */
public class newRecolte extends AppCompatActivity {
    Date dat = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newrecolte);
        Button bVal = (Button) findViewById(R.id.recolteValider);
        Button bAnn = (Button) findViewById(R.id.recolteAnnuler);
        Intent intent = getIntent();
        final int rucher = intent.getIntExtra("n_rucher", 40);

        DatePicker d = (DatePicker) findViewById(R.id.datepickerRecolte);
        CalendarView c = d.getCalendarView();
        GregorianCalendar g = new GregorianCalendar(d.getYear(), d.getMonth(), d.getDayOfMonth());
        dat = g.getTime();
        d.setSpinnersShown(false);
        c.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                GregorianCalendar g = new GregorianCalendar(year, month, dayOfMonth);
                dat = g.getTime();
            }
        });
        bVal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(newRecolte.this, TableauRucher.class);
                EditText et_quantite = (EditText) findViewById(R.id.quantite);
                EditText et_observations = (EditText) findViewById(R.id.observation);
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String date = formatter.format(dat);
                float quantite;
                if (et_quantite.getText().toString().isEmpty()) {
                    et_quantite.setError("Veuillez remplir le champ QUANTITE");
                } else if (menuPcpl.bdd.verifierAnnee(date)) {
                    Toast.makeText(newRecolte.this, "Cette date existe déjà", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("LOL", date);
                    quantite = Float.parseFloat(et_quantite.getText().toString());
                    String observation = et_observations.getText().toString();
                    menuPcpl.bdd.insert_recolte(rucher, date, quantite, observation);
                    intent.putExtra("n_rucher", rucher);
                    finish();
                    startActivity(intent);
                }
            }
        });
        bAnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(newRecolte.this, TableauRucher.class);
                intent.putExtra("n_rucher", rucher);
                finish();
                startActivity(intent);
            }
        });

    }

    ;

}
