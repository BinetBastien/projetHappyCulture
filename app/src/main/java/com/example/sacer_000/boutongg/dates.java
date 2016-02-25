package com.example.sacer_000.boutongg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;


public class dates extends AppCompatActivity {

    private Date d = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dates);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final DatePicker date = (DatePicker) findViewById(R.id.date);
        CalendarView c = date.getCalendarView();
        date.setSpinnersShown(false);
        c.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                int mois = month + 1;
                Toast.makeText(dates.this, dayOfMonth + "/" + mois + "/" + year, Toast.LENGTH_SHORT).show();
                GregorianCalendar g = new GregorianCalendar(year, month, dayOfMonth);
                d = g.getTime();

            }
        });

        Button add = (Button) findViewById(R.id.dateAdd);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                String str = format.format(d);
                menuPcpl.bdd.insertDate(str);
            }
        });

        Button show = (Button) findViewById(R.id.dateShow);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = menuPcpl.bdd.selectDate();
                Toast.makeText(dates.this, str, Toast.LENGTH_SHORT).show();
            }
        });

    }


}
