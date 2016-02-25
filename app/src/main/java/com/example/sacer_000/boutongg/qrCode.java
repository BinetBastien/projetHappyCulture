package com.example.sacer_000.boutongg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Activité permettant le scan
 */
public class qrCode extends Activity implements ZXingScannerView.ResultHandler {
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
        barcodeFormats.add(BarcodeFormat.QR_CODE);
        setContentView(R.layout.qrcode);
        mScannerView = (ZXingScannerView) findViewById(R.id.scan);
        mScannerView.setFormats(barcodeFormats);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onResume() {
        super.onResume();
        barcodeFormats.add(BarcodeFormat.QR_CODE);
        setContentView(R.layout.qrcode);
        mScannerView = (ZXingScannerView) findViewById(R.id.scan);
        mScannerView.setFormats(barcodeFormats);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    public void onPause() {
        mScannerView.stopCamera();
        super.onPause();
        finish();
    }

    @Override
    public void handleResult(Result rawResult) {
        // rawResult.getText() -> texte recupéré contenu dans le qr code

        String chaine = rawResult.getText();
        List<Integer> echange = new ArrayList<Integer>();
        chaine = verif(chaine);
        echange = traducteurQRCode(chaine);
        Log.e("Blabla", "" + echange.size());
        if (echange.size() == 2) {
            Intent intentFicheProduit = new Intent(qrCode.this, menuRuches.class);/*ouvre une activite avec le texte en parametre*/
            intentFicheProduit.putExtra("n_rucher", echange.get(1));
            startActivity(intentFicheProduit);
            this.finish();
        } else if (echange.size() == 4) {
            Intent intentFicheProduit = new Intent(qrCode.this, infoRuche.class);/*ouvre une activite avec le texte en parametre*/
            intentFicheProduit.putExtra("n_rucher", echange.get(1));
            intentFicheProduit.putExtra("n_ruche", echange.get(2));
            intentFicheProduit.putExtra("exemplaire", echange.get(3));
            startActivity(intentFicheProduit);
            this.finish();
        } else {
            Toast.makeText(this, "QRCode invalide pour ce lecteur", Toast.LENGTH_LONG).show();
            onResume();
        }
    }
}
