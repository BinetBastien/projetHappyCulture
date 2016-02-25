package com.example.sacer_000.boutongg;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;


public class DAOBase {

    protected static final String NOM = "database.db";
    protected static final int VERSION = 10;
    protected SQLiteDatabase BDD;
    protected BDD handler;

    public DAOBase(Context context) {
        BDD = null;
        handler = null;
        handler = new BDD(context, "database.db", null, 1);
    }

    public void close() {
        BDD.close();
    }

    public SQLiteDatabase getDb() {
        return BDD;
    }

    public SQLiteDatabase open() {
        BDD = handler.getWritableDatabase();
        return BDD;
    }


    // ***************************************************************************************** INSERT **********************************************************************************************

    /*------------------------------------------------------------------------CALENDRIER------------------------------------------------------------------------*/
    public void insert_calendrier(String date) {
        BDD.beginTransactionNonExclusive();
        try {
            String query = "INSERT INTO CALENDRIER VALUES('" + date + "');";
            BDD.execSQL(query);
            BDD.setTransactionSuccessful();
        } finally {
            BDD.endTransaction();
        }
    }

    public Cursor select_calendrier() {
        String query = "SELECT DATE FROM CALENDRIER";
        return BDD.rawQuery(query, null);
    }

    public Cursor select_calendrier(int jour) {
        String query = "SELECT DATE FROM CALENDRIER WHERE DATE like '" + jour + "/%'";
        return BDD.rawQuery(query, null);
    }

    public Cursor select_calendrier(int mois, int annee) {
        String query = "SELECT DATE FROM CALENDRIER WHERE DATE like '%/" + mois + "/" + annee + "'";
        return BDD.rawQuery(query, null);
    }

    public Cursor select_calendrier(int jour, int mois, int annee) {
        String query = "SELECT DATE FROM CALENDRIER WHERE DATE = '" + jour + "/" + mois + "/" + annee + "'";
        return BDD.rawQuery(query, null);
    }

    public Cursor select_calendrier_annee(int annee) {
        String query = "SELECT DATE FROM CALENDRIER WHERE DATE = '%/" + annee + "'";
        return BDD.rawQuery(query, null);
    }

    /*------------------------------------------------------------------------APICULTEUR------------------------------------------------------------------------*/
    public void insert_apiculteur(int num_agrit, String nom, String prenom, String date) {
        BDD.beginTransactionNonExclusive();
        try {
            String query = "INSERT INTO APICULTEUR VALUES(" + num_agrit + ",'" + nom + "','" + prenom + "','" + date + "');";
            BDD.execSQL(query);
            BDD.setTransactionSuccessful();
        } finally {
            BDD.endTransaction();
        }
    }

    public Cursor select_apiculteur() {
        String query = "SELECT NUM_AGRIT as _ID, NOM FROM APICULTEUR";
        return BDD.rawQuery(query, null);
    }

    public Cursor select_apiculteur(int num_agrit) {
        String query = "SELECT NUM_AGRIT as _ID, NOM FROM APICULTEUR WHERE NUM_AGRIT = " + num_agrit;
        return BDD.rawQuery(query, null);
    }

    public Cursor select_apiculteur(String nom) {
        String query = "SELECT NUM_AGRIT as _ID, NOM FROM APICULTEUR WHERE NOM = '" + nom + "'";
        return BDD.rawQuery(query, null);
    }

    public Cursor select_apiculteur(String nom, String prenom) {
        String query = "SELECT NUM_AGRIT as _ID, NOM FROM APICULTEUR WHERE NOM = '" + nom + "' AND PRENOM = '" + prenom + "'";
        return BDD.rawQuery(query, null);
    }


    /*------------------------------------------------------------------------TRAITEMENT------------------------------------------------------------------------*/
    public void insert_traitement(int num, String libelle, String modalite, String symptome) {
        BDD.beginTransactionNonExclusive();
        try {
            String query = "INSERT INTO TRAITEMENT  VALUES(" + num + ",'" + libelle + "', '" + modalite + "', '" + symptome + "');commit;";
            BDD.execSQL(query);
            BDD.setTransactionSuccessful();
        } finally {
            BDD.endTransaction();
        }
    }

    public Cursor select_traitement() {
        String query = "SELECT N_TRAITEMENT AS _ID, LIBELLE, MODALITE, SYMPTOME FROM TRAITEMENT";
        return BDD.rawQuery(query, null);
    }

    public Cursor select_traitement(int num) {
        String query = "SELECT N_TRAITEMENT AS _ID, LIBELLE, MODALITE, SYMPTOME FROM TRAITEMENT WHERE N_TRAITEMENT = " + num;
        return BDD.rawQuery(query, null);
    }

    public int select_max_traitement() {
        int valeur = 0;
        String query = "SELECT MAX(N_TRAITEMENT) FROM TRAITEMENT";
        Cursor c = BDD.rawQuery(query, null);
        if (c.moveToFirst()) {
            valeur = c.getInt(0) + 1;
        } else
            valeur = 1;
        return valeur;
    }


        /*------------------------------------------------------------------------RUCHER------------------------------------------------------------------------*/

    //NUM_AGRIT, N_RUCHER,VERSION, NUMERO, RUE, VILLE, CODE_POSTAL

    public void insert_rucher(int n_rucher, int version, String num_rue, String rue, String ville, int code_postal) {
        BDD.beginTransactionNonExclusive();
        try {
            String query = "INSERT INTO RUCHER VALUES (1, " + n_rucher + "," + version + ",'" + num_rue + "','" + rue + "','" + ville + "'," + code_postal + ")";
            BDD.execSQL(query);
            BDD.setTransactionSuccessful();
        } finally {
            BDD.endTransaction();
        }
    }

    public void update_rucher(int n_rucher, int version, String num_rue, String rue, String ville, int code_postal) {
        String query = "UPDATE RUCHER SET N_RUCHER =" + n_rucher + ", NUMERO=" + num_rue + ", RUE='" + rue + "', VILLE='" + ville + "', CODE_POSTAL=" + code_postal;
        BDD.execSQL(query);
    }

    public boolean rucher_exist(int n_rucher) {
        Cursor c;
        String query = "SELECT count(*) FROM RUCHER WHERE N_RUCHER = " + n_rucher;
        c = BDD.rawQuery(query, null);
        c.moveToNext();
        if (c.getInt(0) > 0) {
            c.close();
            return true;
        } else {
            c.close();
            return false;
        }
    }

    public Cursor select_all_rucher() {
        String query = "SELECT N_RUCHER AS _ID FROM RUCHER";
        return BDD.rawQuery(query, null);
    }

    public Cursor select_rucher() {
        String query = "SELECT NUM_AGRIT, N_RUCHER AS _ID, NUMERO, RUE, VILLE, CODE_POSTAL, VERSION FROM RUCHER RU WHERE VERSION NOT IN (SELECT VERSION FROM SUPP_RUCHER SR WHERE SR.N_RUCHER=RU.N_RUCHER)";
        return BDD.rawQuery(query, null);
    }

    public Cursor select_rucher(int n_rucher) {
        String query = "SELECT NUM_AGRIT, N_RUCHER AS _ID, NUMERO, RUE, VILLE, CODE_POSTAL, VERSION FROM RUCHER WHERE N_RUCHER = " + n_rucher + " AND VERSION NOT IN ( SELECT VERSION FROM SUPP_RUCHER WHERE N_RUCHER =" + n_rucher + ")";
        return BDD.rawQuery(query, null);
    }

    public Cursor select_rucher_sup(){
        String query = "select n_rucher, commentaire, RUCHER_SUPP_DATE from SUPP_RUCHER order by RUCHER_SUPP_DATE";
        return BDD.rawQuery(query,null);
    }

    public void delete_rucher(int n_rucher, String date){
        Cursor c = select_ruche(n_rucher);
        if(c!=null){
            c.moveToFirst();
            for(int i=0;i<c.getCount();i++){
                int n_ruche = c.getInt(2);
                int version = c.getInt(4);
                insert_supprimer_ruche(n_rucher, n_ruche, date, "",version);
                c.moveToNext();
            }
        }
        c.close();
        //NUM_AGRIT, N_RUCHER, RUCHER_SUPP_DATE, COMMENTAIRE, VERSION
        int ver = 1;
        c = select_rucher(n_rucher);
        if (c != null) {
            c.moveToFirst();
            ver = c.getInt(6);
        }
        String query = "insert into supp_rucher values (1," + n_rucher + ",'01/01/2000',''," + ver + ")";
        BDD.execSQL(query);
    }


    /*------------------------------------------------------------------------RUCHE------------------------------------------------------------------------*/
    public void insert_ruche(int n_rucher, int n_ruche, int nbr_hausse, int version) {
        BDD.beginTransactionNonExclusive();
        try {
            String query = "INSERT INTO RUCHE VALUES (1," + n_rucher + "," + n_ruche + "," + nbr_hausse + "," + version + ")";
            BDD.execSQL(query);
            BDD.setTransactionSuccessful();
        } finally {
            BDD.endTransaction();
        }
    }

    public Cursor select_ruche() {
        String query = "SELECT NUM_AGRIT, N_RUCHER, N_RUCHE AS _ID, NBR_HAUSSE, VERSION FROM RUCHE WHERE N_RUCHE IN ( SELECT N_RUCHE FROM RUCHE WHERE ";
        return BDD.rawQuery(query, null);
    }

    public Cursor select_ruche(int n_rucher) {
        String query = "SELECT NUM_AGRIT, N_RUCHER, N_RUCHE AS _ID, NBR_HAUSSE, VERSION FROM RUCHE RU WHERE N_RUCHER = " + n_rucher + " AND VERSION NOT IN " +
                "(SELECT VERSION FROM SUPPRIMER_RUCHE SR WHERE N_RUCHER=" + n_rucher + " AND SR.N_RUCHE=RU.N_RUCHE)";
        return BDD.rawQuery(query, null);
    }


    public Cursor select_ruche(int n_rucher, int n_ruche) {
        String query = "SELECT NUM_AGRIT, N_RUCHER, N_RUCHE AS _ID, NBR_HAUSSE, VERSION FROM RUCHE WHERE N_RUCHER = " + n_rucher + " AND VERSION IN ( SELECT VERSION FROM RUCHE WHERE N_RUCHER =" + n_rucher + " AND N_RUCHE = " + n_ruche + " EXCEPT SELECT VERSION FROM SUPPRIMER_RUCHE WHERE N_RUCHER = " + n_rucher + " AND N_RUCHE = " + n_ruche + ")";
        return BDD.rawQuery(query, null);
    }

    public boolean ruche_exist(int api, int n_rucher, int n_ruche, int exemp) {
        Cursor c;
        String query = "SELECT count(*) FROM RUCHE WHERE N_RUCHER = " + n_rucher + " AND N_RUCHE IN ( SELECT N_RUCHE FROM RUCHE WHERE N_RUCHER = " + n_rucher + " AND VERSION = " + exemp + " AND N_RUCHE = " + n_ruche + " EXCEPT SELECT N_RUCHE FROM SUPPRIMER_RUCHE WHERE N_RUCHER = " + n_rucher + " AND N_RUCHE = " + n_ruche + ")";
        c = BDD.rawQuery(query, null);
        c.moveToNext();
        if (c.getInt(0) > 0) {
            return true;
        } else {
            return false;
        }
    }


    /*------------------------------------------------------------------------SUPP_RUCHE------------------------------------------------------------------------*/
    public void insert_supprimer_ruche(int n_rucher, int n_ruche, String date, String commentaire, int version) {
        BDD.beginTransactionNonExclusive();
        try {
            String query = "INSERT INTO SUPPRIMER_RUCHE VALUES (1," + n_rucher + "," + n_ruche + ",'" + date + "','" + commentaire + "'," + version + ")";
            BDD.execSQL(query);
            BDD.setTransactionSuccessful();
        } finally {
            BDD.endTransaction();
        }
    }

    public Cursor select_supprimer_ruche() {
        String query = " SELECT NUM_AGRIT, N_RUCHER, N_RUCHE AS _ID, RUCHE_SUPP_DATE, RUCHE_SUPP_COMMENTAIRE, VERSION FROM SUPPRIMER_RUCHE";
        return BDD.rawQuery(query, null);
    }

    public Cursor select_ruche_sup(){
        return BDD.rawQuery("select n_rucher,n_ruche,VERSION,RUCHE_SUPP_DATE, RUCHE_SUPP_COMMENTAIRE from SUPPRIMER_RuCHE order by RUCHE_SUPP_DATE",null);
    }


    /*------------------------------------------------------------------------RECOLTER------------------------------------------------------------------------*/
    public void insert_recolte(int n_rucher, String date, float quantite, String observation) {
        String query = "SELECT MAX(N_RECOLTE) FROM RECOLTER WHERE N_RUCHER = " + n_rucher + " AND RECOLTER_DATE=" + date + ";";
        Cursor cursor = BDD.rawQuery(query, null);
        if (cursor == null) {
            query = "INSERT INTO RECOLTER VALUES (1, " + n_rucher + ", 1, '" + date + "'," + quantite + ", '" + observation + "');";
        } else {
            cursor.moveToFirst();
            int i = cursor.getInt(0);
            i++;
            query = "INSERT INTO RECOLTER VALUES (1, " + n_rucher + "," + i + ", '" + date + "'," + quantite + ", '" + observation + "');";
        }
        BDD.execSQL(query);
    }

    public Cursor select_recolter() {
        String query = " SELECT NUM_AGRIT, N_RUCHER AS _ID, N_RECOLTE, RECOLTER_JOUR, RECOLTER_MOIS, RECOLTER_ANNEE, RECOLTER_QUANTITE, OBSERVATION FROM RECOLTER";
        return BDD.rawQuery(query, null);
    }

    public Cursor select_recolter(int mois, int annee) {
        String query = " SELECT NUM_AGRIT, N_RUCHER AS _ID, N_RECOLTE, RECOLTER_DATE, RECOLTER_QUANTITE, OBSERVATION FROM RECOLTER WHERE RECOLTER_DATE like '%/" + mois + "/" + annee + "'";
        return BDD.rawQuery(query, null);
    }

    public Cursor select_recolter(int mois, int annee, int n_rucher) {
        String query = " SELECT NUM_AGRIT, N_RUCHER AS _ID, N_RECOLTE, RECOLTER_DATE, RECOLTER_QUANTITE, OBSERVATION FROM RECOLTER WHERE RECOLTER_DATE like '%/" + mois + "/" + annee + "' AND N_RUCHER = " + n_rucher;
        return BDD.rawQuery(query, null);
    }

    public Cursor select_recolter_annee(int n_rucher, int annee) {
        String query = "SELECT SUM(RECOLTER_QUANTITE), RECOLTER_DATE FROM RECOLTER WHERE N_RUCHER = " + n_rucher + " AND RECOLTER_DATE like'%/%/" + annee + "' GROUP BY RECOLTER_ANNEE";
        return BDD.rawQuery(query, null);
    }

    public Cursor select_recolter(int n_rucher) {
        String query = "SELECT SUM(RECOLTER_QUANTITE) FROM RECOLTER WHERE N_RUCHER = " + n_rucher + "";
        return BDD.rawQuery(query, null);
    }


    public Integer select_firstyear_recolte(int n_rucher) {
        String first, req;
        first = "select RECOLTER_DATE from RECOLTER where n_rucher = " + n_rucher + " order by RECOLTER_DATE desc";
        Cursor c = BDD.rawQuery(first, null);
        if (c.getCount() != 0) {
            c.moveToNext();
            String str = c.getString(0);
            String chaine = str.substring(6);
            int i = Integer.parseInt(chaine);
            return i;
        } else {
            return -1;
        }
    }

    public Cursor select_tab_year_recolte(int nb_annee, int n_rucher) {
        String req;
        req = "select RECOLTER_QUANTITE from RECOLTER where RECOLTER_DATE like '%/%/" + nb_annee + "' and n_rucher= " + n_rucher + " order by RECOLTER_DATE";
        Cursor c = BDD.rawQuery(req, null);
        return c;
    }


    public Cursor select_max_recolte(int n_rucher) {
        Cursor cur = BDD.rawQuery("select sum(RECOLTER_QUANTITE) as tot,RECOLTER_DATE from Recolter where n_rucher = " + n_rucher + " group by Recolter_date order by substr(Recolter_date,6),substr(Recolter_date,3,2),substr(Recolter_date,0,2)", null);

        return cur;
    }


    /*------------------------------------------------------------------------PERIODE------------------------------------------------------------------------*/
    public void insert_periode(String date_debut, String date_fin) {
        BDD.beginTransactionNonExclusive();
        try {
            String query = "INSERT INTO PERIODE VALUES ('" + date_debut + "','" + date_fin + "')";
            BDD.execSQL(query);
            BDD.setTransactionSuccessful();
        } finally {
            BDD.endTransaction();
        }
    }

    public Cursor select_periode(String date) {
        String query = "SELECT DATE_DEBUT, DATE_FIN WHERE DATE_DEBUT like'" + date + "'";
        return BDD.rawQuery(query, null);
    }

    /*------------------------------------------------------------------------NOURRISSEMENT------------------------------------------------------------------------*/
    public void insert_nourrissement(int num, String libelle) {
        BDD.beginTransactionNonExclusive();
        try {
            String query = "INSERT INTO NOURRISSEMENT  VALUES(" + num + ",'" + libelle + "');commit;";
            BDD.execSQL(query);
            BDD.setTransactionSuccessful();
        } finally {
            BDD.endTransaction();
        }
    }

    public Cursor select_nourrissement() {
        String query = "SELECT NUM_NOURRISSEMENT, TYPE_NOURRISSEMENT FROM NOURRISSEMENT";
        return BDD.rawQuery(query, null);
    }

    public int select_max_nourrissement() {
        int num;
        String query = "SELECT MAX(NUM_NOURRISSEMENT) FROM NOURRISSEMENT";
        Cursor c = BDD.rawQuery(query, null);
        if (c.moveToFirst()) {
            c.moveToFirst();
            num = c.getInt(0) + 1;
        } else {
            num = 1;
        }
        return num;
    }


    /*------------------------------------------------------------------------OBTENIR------------------------------------------------------------------------*/
    public void insert_obtenir(int n_rucher, int n_ruche, int n_traitement, String date, int dosage, int version) {
        BDD.beginTransactionNonExclusive();
        try {
            String query = "INSERT INTO OBTENIR  VALUES(1," + n_rucher + "," + n_ruche + "," + n_traitement + ",'" + date + "'," + dosage + "," + version + ");commit;";
            BDD.execSQL(query);
            BDD.setTransactionSuccessful();
        } finally {
            BDD.endTransaction();
        }
    }

    public Cursor select_obtenir(int n_rucher, int n_ruche) {
        String query = "SELECT NUM_AGRIT, N_RUCHER, N_RUCHE, DATE_DEBUT, DOSAGE, VERSION FROM OBTENIR WHERE N_RUCHE = " + n_rucher + " AND N_RUCHE = " + n_ruche;
        return BDD.rawQuery(query, null);
    }
    // Penser à ne prendre que les traitements des ruches actives (reprendre minus des ruches ou select des bonnes ruches au préalable)


    public Cursor select_obtenir_ruche(int n_rucher, int n_ruche, int version) {
        String query = "SELECT DATE_DEBUT as _id, LIBELLE FROM OBTENIR JOIN TRAITEMENT USING(N_TRAITEMENT) WHERE N_RUCHER = " + n_rucher + " AND N_RUCHE =" + n_ruche + " AND VERSION = " + version;
        return BDD.rawQuery(query, null);
    }

    /*------------------------------------------------------------------------PESER------------------------------------------------------------------------*/
    public void insert_peser(int n_rucher, int n_ruche, int version, String date, float poids) {
        BDD.beginTransactionNonExclusive();
        try {
            String query = "INSERT INTO PESER  VALUES(1," + n_rucher + "," + n_ruche + "," + version + ",'" + date + "'," + poids + ")";
            BDD.execSQL(query);
            BDD.setTransactionSuccessful();
        } finally {
            BDD.endTransaction();
        }
    }

    public Cursor select_peser(int n_rucher, int n_ruche) {
        String query = "SELECT NUM_AGRIT, N_RUCHER, N_RUCHE, VERSION, PESER_DATE, POIDS FROM PESER WHERE N_RUCHER = " + n_rucher + " AND N_RUCHE =" + n_ruche;
        return BDD.rawQuery(query, null);
    }

    public Cursor select_peser_annee(int n_rucher, int n_ruche) {
        String query = "SELECT SUM(POIDS) as total, PESER_DATE FROM PESER WHERE N_RUCHER = " + n_rucher + " AND N_RUCHE = " + n_ruche + " GROUP BY PESER_DATE order by Peser_DATE";
        return BDD.rawQuery(query, null);
    }

    public Integer select_firstyear_poids(int n_rucher, int n_ruche) {
        String first, req;
        first = "SELECT PESER_DATE FROM PESER WHERE N_RUCHER = " + n_rucher + " AND N_RUCHE = " + n_ruche + " ORDER BY PESER_DATE";
        Cursor c = BDD.rawQuery(first, null);
        if (c.getCount() != 0) {
            c.moveToFirst();
            int i = c.getInt(0);
            return i;
        } else {
            return -1;
        }
    }

    public Cursor select_tab_year_poids(int nb_annee, int n_rucher, int n_ruche) {
        String req;
        req = "select PESER_ANNEE from PESER where PESER_DATE = " + nb_annee + " and n_rucher= " + n_rucher + " and n_ruche = " + n_ruche + " order by PESER_DATE";
        Cursor c = BDD.rawQuery(req, null);
        return c;
    }

    public int select_max_poids(int n_rucher, int n_ruche) {
        Cursor cur = BDD.rawQuery("select max(tot) from (select sum(poids) as tot from peser where n_rucher = " + n_rucher + " and n_ruche = " + n_ruche + " group by peser_date)", null);
        cur.moveToFirst();
        return cur.getInt(0);
    }

    public List<Float> select_poids(int n_rucher, int n_ruche) {
        List<Float> poids = new LinkedList<Float>();
        Cursor cur = BDD.rawQuery("select poids from peser where n_rucher = " + n_rucher + " and n_ruche = " + n_ruche + "order by date", null);
        for (int i = 0; i < cur.getCount(); i++) {
            cur.moveToNext();
            poids.add(cur.getFloat(0));
        }
        return poids;
    }


    public Cursor select_peser_ruche(int n_rucher, int n_ruche, int version) {
        String query = "SELECT PESER_DATE as _ID , POIDS FROM PESER WHERE N_RUCHER=" + n_rucher + " AND N_RUCHE=" + n_ruche + " AND VERSION=" + version;
        return BDD.rawQuery(query, null);
    }


    /*------------------------------------------------------------------------RECEVOIR------------------------------------------------------------------------*/
    public void insert_recevoir(int n_rucher, int n_ruche, int num_nourrissement, String date, int quantite, String remarque, int version) {
        BDD.beginTransactionNonExclusive();
        try {
            String query = "INSERT INTO RECEVOIR  VALUES(1," + n_rucher + "," + n_ruche + "," + num_nourrissement + ",'" + date + "'," + quantite + ",'" + remarque + "'," + version + ")";
            BDD.execSQL(query);
            BDD.setTransactionSuccessful();
        } finally {
            BDD.endTransaction();
        }
    }

    public Cursor select_recevoir(int n_rucher, int n_ruche) {
        String query = "SELECT NUM_AGRIT, N_RUCHER, N_RUCHE, NUM_NOURRISSEMENT, JOUR, MOIS, ANNEE, QUANTITE, REMARQUE, VERSION FROM RECEVOIR WHERE N_RUCHER = " + n_rucher + " AND N_RUCHE = " + n_ruche;
        return BDD.rawQuery(query, null);
    }


    // Penser à ne prendre que les nourrissements des ruches actives (reprendre minus des ruches ou select des bonnes ruches au préalable)


    public Cursor select_recevoir_ruche(int n_rucher, int n_ruche, int version) {
        String query = "SELECT DATE_RECEVOIR as _id, TYPE_NOURRISSEMENT FROM RECEVOIR JOIN NOURRISSEMENT USING(NUM_NOURRISSEMENT) WHERE N_RUCHER = " + n_rucher + " AND N_RUCHE =" + n_ruche + " AND VERSION = " + version;
        return BDD.rawQuery(query, null);
    }


                               /* public void insert_poids(int n_rucher,int n_ruche, int poids, int date) {
                                    String query = "select max(poids) from Peser where N_Rucher =" + n_rucher + " and N_Ruche ="+n_ruche+" AND Date_Calendrier = " + date;
                                    Cursor cursor = BDD.rawQuery(query, null);
                                    cursor.moveToFirst();
                                    query = "insert into PESER values (1, " + n_rucher + ", " + n_ruche + ", " + (cursor.getInt(0)+1) + " ," + date + ", " + poids + ");commit;";

                                    BDD.execSQL(query);
                                }

                                public void insert_supp(){
                                    String query ="insert into supprimer_ruche values (1,1,2,0, 10, '')";
                                    BDD.execSQL(query);
                                }*/
    // ************************************************************** SELECT *******************************************************************

    public void insertDate(String d) {
        String query = "insert into date_test values ('" + d + "')";
        BDD.execSQL(query);
    }

    public String selectDate() {
        String query = "select dat as _id from date_test";
        Cursor c = BDD.rawQuery(query, null);
        c.moveToFirst();
        String s = "" + c.getString(0);
        return s;
    }


    public Cursor select_traitement(int n_rucher, int n_ruche) {
        return BDD.rawQuery("select n_traitement, date_debut, dosage from obtenir where n_rucher=" + n_rucher + " and n_ruche=" + n_ruche, null);
    }


    public Cursor selectTrait() {
        return BDD.rawQuery("SELECT N_Traitement AS _ID, libelle FROM TRAITEMENT ;", null);
    }

    public Cursor selectTrait(int num_traitement) {
        return BDD.rawQuery("SELECT N_Traitement AS _ID FROM TRAITEMENT where n_traitement = " + num_traitement + ";", null);
    }

    public Cursor selectNour() {
        return BDD.rawQuery("SELECT Num_Nourrissement AS _ID, Type_Nourrissement FROM NOURRISSEMENT ;", null);
    }

    public Cursor selectNour(int num_nourrissement) {
        return BDD.rawQuery("SELECT Num_Nourrissement AS _ID FROM NOURRISSEMENT where Num_Nourrissement = " + num_nourrissement + ";", null);
    }

    public int selectMaxExempRuche(int n_rucher, int n_ruche) {
        Cursor c = BDD.rawQuery("SELECT MAX(VERSION) from  ruche where n_rucher=" + n_rucher + " and n_ruche=" + n_ruche, null);
        if (c.getCount() == 0)
            return -1;
        else {
            c.moveToFirst();
            int i = c.getInt(0);
            return i;
        }
    }

    public int selectMaxExempSupp(int n_rucher, int n_ruche) {
        Cursor c = BDD.rawQuery("SELECT MAX(VERSION) from supprimer_ruche where n_rucher=" + n_rucher + " and n_ruche=" + n_ruche, null);
        if (c.getCount() == 0)
            return 0;
        else {
            c.moveToFirst();
            int i = c.getInt(0);
            return i;
        }
    }

    // ***************************************************************************************** VERIFICATIONS **********************************************************************************************


    public int verifierDonneesRuche(int n_rucher, int n_ruche) {
        int r = selectMaxExempRuche(n_rucher, n_ruche);
        int s = selectMaxExempSupp(n_rucher, n_ruche);
        if (r == -1) {
            return 1;
        } else if (r == s) {
            return s + 1;
        } else {
            return -1;
        }
    }

    public int verifierDonneesRucher(int n_rucher) {
        int r = selectMaxVerRucher(n_rucher);
        int s = selectMaxVerSuppRucher(n_rucher);
        if (r == -1) {
            return 1;
        } else if (r == s) {
            return ++r;
        } else {
            return -1;
        }
    }

    private int selectMaxVerSuppRucher(int n_rucher) {
        Cursor c = BDD.rawQuery("SELECT MAX(VERSION) FROM  RUCHER where n_rucher=" + n_rucher, null);
        if (c.getCount() == 0)
            return -1;
        else {
            c.moveToFirst();
            int i = c.getInt(0);
            return i;
        }
    }

    private int selectMaxVerRucher(int n_rucher) {
        Cursor c = BDD.rawQuery("SELECT MAX(VERSION) from  SUPP_RUCHER where n_rucher=" + n_rucher, null);
        if (c.getCount() == 0)
            return -1;
        else {
            c.moveToFirst();
            int i = c.getInt(0);
            return i;
        }
    }

    public boolean verifierAnnee(String an) {
        String query = "SELECT COUNT(*) FROM RECOLTER WHERE RECOLTER_DATE = " + an;
        Cursor curs = BDD.rawQuery(query, null);
        curs.moveToFirst();
        int nbLignes = curs.getInt(0);
        if (nbLignes == 0)
            return true;
        return false;
    }


    // ***************************************************************************************** BDD **********************************************************************************************

    public void creerTables() {
        handler.onCreate(BDD);
    }

    public void dropTable() {
        handler.delete(BDD);
    }

    public void upDate() {
        handler.onUpgrade(BDD, VERSION, VERSION + 1);
    }

    public void create() {
        handler.onCreate(BDD);
    }

    public void onUpgrade() {
        handler.onUpgrade(BDD, VERSION, VERSION + 1);
    }

    public void commit() {
        BDD.beginTransactionNonExclusive();
        BDD.setTransactionSuccessful();
        BDD.endTransaction();
    }


}
