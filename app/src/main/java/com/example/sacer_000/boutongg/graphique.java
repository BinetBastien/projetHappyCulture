package com.example.sacer_000.boutongg;

import android.database.Cursor;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jeoffrey on 17/09/2015.
 */
public class graphique {

    private List<Float> tab_qte = new LinkedList<Float>();
    private List<String> tab_date = new LinkedList<String>();
    private int first_year;
    private int nb_annee;

    public graphique() {
        first_year = 0;
        nb_annee = 0;
    }

    public List<Float> getTab_qte() {
        return tab_qte;
    }

    public List<String> getTab_date() {
        return tab_date;
    }

    public int getFirst_year() {
        return first_year;
    }

    public void setFirst_year(int first_year) {
        this.first_year = first_year;
    }

    public int getNb_annee() {
        return nb_annee;
    }

    public void setNb_annee() {
        nb_annee = tab_qte.size();
    }

    public void inc_Nb_annee() {
        this.nb_annee++;
    }

    public void set_Tab_qte(List<Float> list) {
        this.tab_qte = list;
    }

    public void inc_Tab_qte(Float value) {
        this.tab_qte.add(value);
    }

    public void donnee_graph_recolte(DAOBase bdd, int n_rucher) {
        String req;

        setFirst_year(bdd.select_firstyear_recolte(n_rucher));
        Cursor cur = bdd.select_max_recolte(n_rucher);
        cur.moveToFirst();
        for (int i = 0; i < cur.getCount(); i++) {
            this.tab_qte.add(cur.getFloat(0));
            this.tab_date.add(cur.getString(1));
            cur.moveToNext();
        }
    }


    public void donnee_graph_poids(DAOBase bdd, int n_rucher, int n_ruche) {
        List<Float> result;

        setFirst_year(bdd.select_firstyear_poids(n_rucher, n_ruche));

        result = bdd.select_poids(n_rucher, n_ruche);

        this.set_Tab_qte(result);
        this.setNb_annee();
    }

}
