package com.example.sacer_000.boutongg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;

/**
 * Created by sacer_000 on 16/09/2015.
 */
public class graphPoids extends AppCompatActivity {
    private static final Random RANDOM = new Random();
    private LineGraphSeries<DataPoint> series;
    private graphique tab_graph = new graphique();
    private int n_rucher, n_ruche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphpoids);
        Intent intent = getIntent();
        this.n_ruche = intent.getIntExtra("n_ruche", 1);
        this.n_rucher = intent.getIntExtra("n_rucher", 1);
        GraphView graph = (GraphView) findViewById(R.id.graphPoids);
        // data
        series = new LineGraphSeries<DataPoint>();
        graph.addSeries(series);
        Viewport viewport = graph.getViewport();
        viewport.setYAxisBoundsManual(true);
        viewport.setXAxisBoundsManual(true);
        tab_graph.donnee_graph_poids(menuPcpl.bdd, n_rucher, n_ruche);
        viewport.setMinY(0);
        viewport.setMaxY(menuPcpl.bdd.select_max_poids(n_rucher, n_ruche) + 5);
        viewport.setMinX(0);
        viewport.setMaxX(tab_graph.getNb_annee());
        viewport.setScrollable(true);

        final Button nouveau = (Button) findViewById(R.id.boutonNouveau);
        final Button retour = (Button) findViewById(R.id.boutonRetour);

        nouveau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(graphPoids.this, newPoids.class);
                intent.putExtra("n_rucher", n_rucher);
                intent.putExtra("n_ruche", n_ruche);
                finish();
                startActivity(intent);
            }
        });

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(graphPoids.this, infoRuche.class);
                finish();
                startActivity(intent);
            }
        });
    }

    protected void onResume() {
        super.onResume();
        new Thread(new Runnable() {

            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        addEntry(tab_graph);
                    }
                });


            }
        }).start();
    }

    /* private void addEntry(graphique tab_graph) {
         int i;
         for(i=0;i<tab_graph.getNb_annee();i++) {
             series.appendData(new DataPoint(tab_graph.getFirst_year() + i, tab_graph.getTab_qte().get(i)), false, tab_graph.getNb_annee());
         }
     }*/
    private void addEntry(graphique tab_graph) {
        for (int i = 0; i < tab_graph.getNb_annee(); i++) {
            series.appendData(new DataPoint(i, tab_graph.getTab_qte().get(i)), false, tab_graph.getNb_annee());
        }
    }

}