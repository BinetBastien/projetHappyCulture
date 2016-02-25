package com.example.sacer_000.boutongg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Jeoffrey on 28/01/2016.
 */
public class menuSuppression extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menusuppression);

        Button ruche = (Button) findViewById(R.id.ruche);
        ruche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menuSuppression.this, menuRucheSup.class);
                onPause();
                startActivity(intent);
            }
        });

        Button rucher = (Button) findViewById(R.id.rucher);
        rucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menuSuppression.this, menuRucherSup.class);
                onPause();
                startActivity(intent);
            }
        });

        Button retour = (Button) findViewById(R.id.retour);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menuSuppression.this, option.class);
                onPause();
                startActivity(intent);
            }
        });
    }
}
