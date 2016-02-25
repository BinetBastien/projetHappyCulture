package com.example.sacer_000.boutongg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class menuPcpl extends AppCompatActivity {
    protected static DAOBase bdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menupcpl);
        bdd = new DAOBase(this);
        bdd.open();
        bdd.dropTable();
        bdd.create();
        //bdd.onUpgrade();
        //bdd.insert_supp();
        final Button bRucher = (Button) findViewById(R.id.btnMPcplRucher);
        final Button bQRCode = (Button) findViewById(R.id.btnMPcplQRCode);
        final Button option = (Button) findViewById(R.id.optn);
        bRucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menuPcpl.this, menuRuchers.class);
                onPause();
                startActivity(intent);
            }
        });
        bQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menuPcpl.this, LecteurQRCode.class);
                onPause();
                startActivity(intent);
            }
        });
        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menuPcpl.this, option.class);
                onPause();
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
