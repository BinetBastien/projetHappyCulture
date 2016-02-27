package com.example.sacer_000.boutongg;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class LecteurQRCode extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    List<BarcodeFormat> barcodeFormats = new ArrayList<>();
    private ZXingScannerView mScannerView;

    public static List<Integer> traducteurQRCode(String chaine) {
        List<Integer> echange = new ArrayList<Integer>();
        while (chaine.contains("-")) {
            echange.add(Integer.parseInt(chaine.substring(0, chaine.indexOf('-'))));
            chaine = chaine.substring(chaine.indexOf('-') + 1);
        }
        echange.add(Integer.parseInt(chaine));

        return echange;
    }

    public static String verif(String chaine) {
        char[] tCarac;
        tCarac = chaine.toCharArray();
        chaine = "";

        //Retrait de tous les caractere suceptible de planter l'application
        for (int i = 0; i < tCarac.length; i++) {
            if ((int) tCarac[i] < 48 || (int) tCarac[i] > 57) {
                chaine = chaine + '-';
            } else {
                chaine = chaine + tCarac[i];
            }
        }

        //Retrait du double tiret
        while (chaine.contains("--")) {
            chaine = chaine.replace("--", "-");
        }

        //Retrait du tiret simple au debut et a la fin du message
        while (chaine.endsWith("-")) {
            chaine = chaine.substring(0, chaine.length() - 1);
        }
        while (chaine.startsWith("-")) {
            chaine = chaine.substring(chaine.indexOf('-') + 1);
        }

        return chaine;
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
        barcodeFormats.add(BarcodeFormat.QR_CODE);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {


        new AsyncTask<Result, Void, List<Integer>>() {

            @Override
            protected List<Integer> doInBackground(String... nf) {
                String chaine = rawResult.getText();
                String old = "gnee";
                List<Integer> echange = new ArrayList<Integer>();
                chaine = verif(chaine);
                echange = traducteurQRCode(chaine);
                Log.e("Blabla", "" + echange.size());
                return echange;
            }

            @Override
            protected void onPostExecute(Integer[] args) {

            }
        }.execute(rawResult);


        // Do something with the result here

        if (echange.size() == 2) {
            Intent intentFicheProduit = new Intent(LecteurQRCode.this, menuRuches.class);/*ouvre une activite avec le texte en parametre*/
            intentFicheProduit.putExtra("n_rucher", echange.get(1));
            startActivity(intentFicheProduit);
            this.finish();
        } else if (echange.size() == 4) {
            Intent intentFicheProduit = new Intent(LecteurQRCode.this, tabbedInfoRuches.class);/*ouvre une activite avec le texte en parametre*/
            intentFicheProduit.putExtra("n_rucher", echange.get(1));
            intentFicheProduit.putExtra("n_ruche", echange.get(2));
            intentFicheProduit.putExtra("exemplaire", echange.get(3));
            startActivity(intentFicheProduit);
            this.finish();
        } else {
            if (old == chaine) {
                mScannerView.resumeCameraPreview(this);
            } else {
                Log.e("old", old);
                old = chaine;
                Toast.makeText(this, "QRCode invalide pour ce lecteur", Toast.LENGTH_LONG).show();
                mScannerView.resumeCameraPreview(this);
            }

        }
        // If you would like to resume scanning, call this method below:
        //mScannerView.resumeCameraPreview(this);
    }

}