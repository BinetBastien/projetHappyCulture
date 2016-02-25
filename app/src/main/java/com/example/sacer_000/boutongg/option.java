package com.example.sacer_000.boutongg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by sacer_000 on 01/10/2015.
 */
public class option extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);

        Button trait = (Button) findViewById(R.id.trait);
        trait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(option.this, ajoutTraitement.class);
                onPause();
                startActivity(intent);
            }
        });
        Button nour = (Button) findViewById(R.id.addNour);
        nour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(option.this, ajoutNourrissement.class);
                onPause();
                startActivity(intent);
            }
        });
        Button retour = (Button) findViewById(R.id.retourOption);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(option.this, menuPcpl.class);
                onPause();
                startActivity(intent);
            }
        });

        Button generer = (Button) findViewById(R.id.genererBase);
        generer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuPcpl.bdd.onUpgrade();
                Toast.makeText(option.this, "Base générée", Toast.LENGTH_SHORT).show();
            }
        });

        Button suppressed = (Button) findViewById(R.id.suppRuch);
        suppressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(option.this, menuSuppression.class);
                startActivity(intent);
            }
        });

    }

   /* @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.options);

        Button trait = (Button) findViewById(R.id.trait);
        trait.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View v) {
                Intent intent = new Intent(option.this, ajoutTraitement.class);
                onPause();
                startActivity(intent);
            }
        });
        Button date = (Button) findViewById(R.id.addDate);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(option.this, ajouterDate.class);
                onPause();
                startActivity(intent);
            }
        });
    }*/
}
