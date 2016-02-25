package com.example.sacer_000.boutongg;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDD extends SQLiteOpenHelper {

    /*------------------------------------CALENDRIER------------------------------------*/
    public static final String CALENDRIER_TABLE_NAME = "CALENDRIER";

    //DATE

    public static final String DATE_CALENDRIER = "DATE";

    public static final String CALENDRIER_TABLE_CREATE =
            "CREATE TABLE " + CALENDRIER_TABLE_NAME + " (" +
                    DATE_CALENDRIER + " DATE NOT NULL," +
                    "PRIMARY KEY (" + DATE_CALENDRIER + "));";

    public static final String CALENDRIER_TABLE_DROP = "DROP TABLE IF EXISTS " + CALENDRIER_TABLE_NAME + ";";


    /*------------------------------------APICULTEUR------------------------------------*/
    public static final String APICULTEUR_TABLE_NAME = "APICULTEUR";

    // NUM_AGRIT, NOM, PRENOM, DATE_NAISSANCE

    public static final String APICULTEUR_NUMERO = "NUM_AGRIT";
    public static final String APICULTEUR_NOM = "NOM";
    public static final String APICULTEUR_PRENOM = "PRENOM";
    public static final String DATE_NAISSANCE = "DATE_NAISSANCE";

    public static final String APICULTEUR_TABLE_CREATE =
            "CREATE TABLE " + APICULTEUR_TABLE_NAME + " (" +
                    APICULTEUR_NUMERO + " INTEGER," +
                    APICULTEUR_NOM + " TEXT, " +
                    APICULTEUR_PRENOM + " TEXT, " +
                    DATE_NAISSANCE + " DATE," +
                    "PRIMARY KEY (" + APICULTEUR_NUMERO + "));";

    public static final String APICULTEUR_TABLE_DROP = "DROP TABLE IF EXISTS " + APICULTEUR_TABLE_NAME + ";";

    /*------------------------------------TRAITEMENT------------------------------------*/
    public static final String TRAITEMENT_TABLE_NAME = "TRAITEMENT";

    // N_TRAITEMENT, TRAITEMENT_CONS, MODALITE, SYMPTOME

    public static final String TRAITEMENT_NUMERO = "N_TRAITEMENT";
    public static final String TRAITEMENT_LIBELLE = "LIBELLE";
    public static final String TRAITEMENT_MODA = "MODALITE";
    public static final String TRAITEMENT_SYMP = "SYMPTOME";

    public static final String TRAITEMENT_TABLE_CREATE =
            "CREATE TABLE " + TRAITEMENT_TABLE_NAME + " (" +
                    TRAITEMENT_NUMERO + " INTEGER PRIMARY KEY, " +
                    TRAITEMENT_LIBELLE + " TEXT, " +
                    TRAITEMENT_MODA + " TEXT, " +
                    TRAITEMENT_SYMP + " TEXT);";

    public static final String TRAITEMENT_TABLE_DROP = "DROP TABLE IF EXISTS " + TRAITEMENT_TABLE_NAME + ";";

    /*------------------------------------RUCHER------------------------------------*/
    public static final String RUCHER_TABLE_NAME = "RUCHER";

    // NUM_AGRIT, N_RUCHER,VERSION, NUMERO, RUE, VILLE, CODE_POSTAL

    public static final String RUCHER_NUM_AGRIT = "NUM_AGRIT";
    public static final String RUCHER_N_RUCHER = "N_RUCHER";
    public static final String RUCHER_NUMERO_RUE = "NUMERO";
    public static final String RUCHER_RUE = "RUE";
    public static final String RUCHER_VILLE = "VILLE";
    public static final String RUCHER_CODE_POSTAL = "CODE_POSTAL";
    public static final String RUCHER_VERSION = "VERSION";


    public static final String RUCHER_TABLE_CREATE =
            "CREATE TABLE " + RUCHER_TABLE_NAME + " (" +
                    RUCHER_NUM_AGRIT + " INTEGER, " +
                    RUCHER_N_RUCHER + " INTEGER, " +
                    RUCHER_VERSION + " INTEGER, " +
                    RUCHER_NUMERO_RUE + " INTEGER, " +
                    RUCHER_RUE + " TEXT, " +
                    RUCHER_VILLE + " TEXT, " +
                    RUCHER_CODE_POSTAL + " INTEGER, " +
                    "PRIMARY KEY (" + RUCHER_NUM_AGRIT + "," + RUCHER_N_RUCHER + ")," +
                    "FOREIGN KEY (" + RUCHER_NUM_AGRIT + ") REFERENCES APICULTEUR (" + APICULTEUR_NUMERO + ") ) ";

    public static final String RUCHER_TABLE_DROP = "DROP TABLE IF EXISTS " + RUCHER_TABLE_NAME + ";";

        /*------------------------------------SUPP_RUCHER------------------------------------*/

    public static final String RUCHER_SUPP_TABLE_NAME = "SUPP_RUCHER";

    //NUM_AGRIT, N_RUCHER, RUCHER_SUPP_DATE, VERSION

    public static final String RUCHER_SUPP_NUM_AGRIT = "NUM_AGRIT";
    public static final String RUCHER_SUPP_NUMERO_RUCHER = "N_RUCHER";
    public static final String RUCHER_SUPP_DATE = "RUCHER_SUPP_DATE";
    public static final String RUCHER_SUPP_COMMENTAIRE = "COMMENTAIRE";
    public static final String RUCHER_SUPP_VERSION = "VERSION";

    public static final String RUCHER_SUPP_TABLE_CREATE =
            "CREATE TABLE " + RUCHER_SUPP_TABLE_NAME + " (" +
                    RUCHER_SUPP_NUM_AGRIT + " INTEGER, " +
                    RUCHER_SUPP_NUMERO_RUCHER + " INTEGER, " +
                    RUCHER_SUPP_DATE + " DATE," +
                    RUCHER_SUPP_VERSION + " INTEGER," +
                    RUCHER_SUPP_COMMENTAIRE + " TEXT ," +
                    "PRIMARY KEY (" + RUCHER_SUPP_NUM_AGRIT + "," + RUCHER_SUPP_NUMERO_RUCHER + "," + RUCHER_SUPP_VERSION + ")" +
                    "FOREIGN KEY (" + RUCHER_SUPP_NUM_AGRIT + "," + RUCHER_SUPP_NUMERO_RUCHER + "," + RUCHER_SUPP_VERSION + ") REFERENCES RUCHE(" + RUCHER_NUM_AGRIT + "," + RUCHER_N_RUCHER + "," + RUCHER_VERSION + ") ," +
                    "FOREIGN KEY (" + RUCHER_SUPP_DATE + ") REFERENCES CALENDRIER(" + DATE_CALENDRIER + ") );";

    public static final String RUCHER_SUPP_TABLE_DROP = "DROP TABLE IF EXISTS " + RUCHER_SUPP_TABLE_NAME + ";";

    /*------------------------------------RUCHE------------------------------------*/
    public static final String RUCHE_TABLE_NAME = "RUCHE";

    //NUM_AGRIT, N_RUCHER, N_RUCHE, NBR_HAUSSE, VERSION

    public static final String RUCHE_NUM_AGRIT = "NUM_AGRIT";
    public static final String RUCHE_NUMERO_RUCHER = "N_RUCHER";
    public static final String RUCHE_NUMERO_RUCHE = "N_RUCHE";
    public static final String RUCHE_NBR_HAUSSE = "NBR_HAUSSE";
    public static final String RUCHE_VERSION = "VERSION";

    public static final String RUCHE_TABLE_CREATE =
            "CREATE TABLE " + RUCHE_TABLE_NAME + " (" +
                    RUCHE_NUM_AGRIT + " INTEGER, " +
                    RUCHE_NUMERO_RUCHER + " INTEGER, " +
                    RUCHE_NUMERO_RUCHE + " INTEGER, " +
                    RUCHE_NBR_HAUSSE + " INTEGER ," +
                    RUCHE_VERSION + " INTEGER," +
                    "PRIMARY KEY (" + RUCHE_NUM_AGRIT + "," + RUCHE_NUMERO_RUCHER + "," + RUCHE_NUMERO_RUCHE + "," + RUCHE_VERSION + ")" +
                    "FOREIGN KEY (" + RUCHE_NUM_AGRIT + "," + RUCHE_NUMERO_RUCHER + ") REFERENCES RUCHER (" + RUCHER_NUM_AGRIT + "," + RUCHER_N_RUCHER + ") );";

    public static final String RUCHE_TABLE_DROP = "DROP TABLE IF EXISTS " + RUCHE_TABLE_NAME + ";";

    /*------------------------------------SUPP_RUCHE------------------------------------*/
    public static final String RUCHE_SUPP_NAME = "SUPPRIMER_RUCHE";

    //NUM_AGRIT, N_RUCHER, N_RUCHE, RUCHE_SUPP_DATE, RUCH_SUPP_COMMENTAIRE, VERSION

    public static final String RUCHE_SUPP_NUM_AGRIT = "NUM_AGRIT";
    public static final String RUCHE_SUPP_NUMERO_RUCHER = "N_RUCHER";
    public static final String RUCHE_SUPP_NUMERO_RUCHE = "N_RUCHE";
    public static final String RUCHE_SUPP_DATE = "RUCHE_SUPP_DATE";
    public static final String RUCHE_SUPP_COMMENTAIRE = "RUCHE_SUPP_COMMENTAIRE";
    public static final String RUCHE_SUPP_VERSION = "VERSION";

    public static final String RUCHE_SUPP_TABLE_CREATE =
            "CREATE TABLE " + RUCHE_SUPP_NAME + " (" +
                    RUCHE_SUPP_NUM_AGRIT + " INTEGER, " +
                    RUCHE_SUPP_NUMERO_RUCHER + " INTEGER, " +
                    RUCHE_SUPP_NUMERO_RUCHE + " INTEGER, " +
                    RUCHE_SUPP_DATE + " DATE," +
                    RUCHE_SUPP_COMMENTAIRE + " TEXT," +
                    RUCHE_SUPP_VERSION + " INTEGER," +
                    "PRIMARY KEY (" + RUCHE_SUPP_NUM_AGRIT + "," + RUCHE_SUPP_NUMERO_RUCHER + "," + RUCHE_SUPP_NUMERO_RUCHE + "," + RUCHE_SUPP_VERSION + ")" +
                    "FOREIGN KEY (" + RUCHE_SUPP_NUM_AGRIT + "," + RUCHE_SUPP_NUMERO_RUCHER + "," + RUCHE_SUPP_NUMERO_RUCHE + "," + RUCHE_SUPP_VERSION + ") REFERENCES RUCHE(" + RUCHE_NUM_AGRIT + "," + RUCHE_NUMERO_RUCHER + "," + RUCHE_NUMERO_RUCHE + "," + RUCHE_VERSION + ") ," +
                    "FOREIGN KEY (" + RUCHE_SUPP_DATE + ") REFERENCES CALENDRIER(" + DATE_CALENDRIER + ") );";

    public static final String RUCHE_SUPP_TABLE_DROP = "DROP TABLE IF EXISTS " + RUCHE_SUPP_NAME + ";";

    /*------------------------------------RECOLTER------------------------------------*/
    public static final String RECOLTER_TABLE_NAME = "RECOLTER";

    //NUM_AGRIT, N_RUCHER, N_RECOLTE, RECOLTER_JOUR, RECOLTER_MOIS, RECOLTER_ANNEE, RECOLTER_QUANTITE, OBSERVATION

    public static final String RECOLTER_NUM_AGRIT = "NUM_AGRIT";
    public static final String RECOLTER_NUMERO_RUCHER = "N_RUCHER";
    public static final String RECOLTER_NUMERO_RECOLTE = "N_RECOLTE";
    public static final String RECOLTER_DATE = "RECOLTER_DATE";
    public static final String RECOLTER_QUANTITE = "RECOLTER_QUANTITE";
    public static final String RECOLTER_OBSERVATION = "OBSERVATION";

    public static final String RECOLTER_TABLE_CREATE =
            "CREATE TABLE " + RECOLTER_TABLE_NAME + " (" +
                    RECOLTER_NUM_AGRIT + " INTEGER, " +
                    RECOLTER_NUMERO_RUCHER + " INTEGER, " +
                    RECOLTER_NUMERO_RECOLTE + " INTEGER," +
                    RECOLTER_DATE + " DATE, " +
                    RECOLTER_QUANTITE + " REAL, " +
                    RECOLTER_OBSERVATION + " TEXT, " +
                    "PRIMARY KEY (" + RECOLTER_NUM_AGRIT + "," + RECOLTER_NUMERO_RUCHER + "," + RECOLTER_DATE + "," + RECOLTER_NUMERO_RECOLTE + ")," +
                    "FOREIGN KEY (" + RECOLTER_NUM_AGRIT + "," + RECOLTER_NUMERO_RUCHER + ")REFERENCES RUCHE (" + RUCHER_NUM_AGRIT + "," + RUCHER_N_RUCHER + ") ," +
                    "FOREIGN KEY (" + RECOLTER_DATE + ")REFERENCES CALENDRIER (" + DATE_CALENDRIER + ") );";

    public static final String RECOLTER_TABLE_DROP = "DROP TABLE IF EXISTS " + RECOLTER_TABLE_NAME + ";";


    /*------------------------------------PERIODE------------------------------------*/
    public static final String PERIODE_TABLE_NAME = "PERIODE";

    //JOUR_DEBUT, MOIS_DEBUT, ANNEE_DEBUT, JOUR_FIN, MOIS_FIN, ANNEE_FIN

    public static final String DATE_DEBUT = "DATE_DEBUT";
    public static final String DATE_FIN = "JOUR_FIN";

    public static final String PERIODE_TABLE_CREATE =
            "CREATE TABLE " + PERIODE_TABLE_NAME + " (" +
                    DATE_DEBUT + " DATE, " +
                    DATE_FIN + " DATE, " +
                    " PRIMARY KEY (" + DATE_DEBUT + "));";

    public static final String PERIODE_TABLE_DROP = "DROP TABLE IF EXISTS " + PERIODE_TABLE_NAME + ";";

    /*------------------------------------NOURRISSEMENT------------------------------------*/
    public static final String NOURRISSEMENT_TABLE_NAME = "NOURRISSEMENT";

    //NUM_NOURRISSEMENT, TYPE_NOURRISSEMENT

    public static final String NUM_NOURRISSEMENT = "NUM_NOURRISSEMENT";
    public static final String TYPE_NOURRISSEMENT = "TYPE_NOURRISSEMENT";

    public static final String NOURRISSEMENT_TABLE_CREATE =
            "CREATE TABLE " + NOURRISSEMENT_TABLE_NAME + " (" +
                    NUM_NOURRISSEMENT + " INTEGER, " +
                    TYPE_NOURRISSEMENT + " TEXT, PRIMARY KEY (" + NUM_NOURRISSEMENT + "));";

    public static final String NOURRISSEMENT_TABLE_DROP = "DROP TABLE IF EXISTS " + NOURRISSEMENT_TABLE_NAME + ";";

    /*------------------------------------OBTENIR------------------------------------*/
    public static final String OBTENIR_TABLE_NAME = "OBTENIR";

    //NUM_AGRIT, N_RUCHER, N_RUCHE, N_TRAITEMENT, JOUR_DEBUT, MOIS_DEBUT, ANNEE_DEBUT, DOSAGE, VERSION

    public static final String OBTENIR_NUM_AGRIT = "NUM_AGRIT";
    public static final String OBTENIR_NUMERO_RUCHER = "N_RUCHER";
    public static final String OBTENIR_NUMERO_RUCHE = "N_RUCHE";
    public static final String OBTENIR_RUCHE_VERSION = "VERSION";
    public static final String OBTENIR_TRAITEMENT_NUMERO = "N_TRAITEMENT";
    public static final String OBTENIR_DATE_DEBUT = "DATE_DEBUT";
    public static final String OBTENIR_DOSAGE = "DOSAGE";


    public static final String OBTENIR_TABLE_CREATE =
            "CREATE TABLE " + OBTENIR_TABLE_NAME + " (" +
                    OBTENIR_NUM_AGRIT + " INTEGER, " +
                    OBTENIR_NUMERO_RUCHER + "  INTEGER, " +
                    OBTENIR_NUMERO_RUCHE + "  INTEGER, " +
                    OBTENIR_TRAITEMENT_NUMERO + " INTEGER, " +
                    OBTENIR_DATE_DEBUT + " DATE," +
                    OBTENIR_DOSAGE + " INTEGER," +
                    OBTENIR_RUCHE_VERSION + " INTEGER," +
                    "PRIMARY KEY (" + OBTENIR_NUM_AGRIT + "," + OBTENIR_NUMERO_RUCHER + "," + OBTENIR_NUMERO_RUCHE + "," + OBTENIR_TRAITEMENT_NUMERO + "," + OBTENIR_RUCHE_VERSION + "," + OBTENIR_DATE_DEBUT + ")," +
                    "FOREIGN KEY (" + OBTENIR_NUM_AGRIT + "," + OBTENIR_NUMERO_RUCHER + "," + OBTENIR_NUMERO_RUCHE + "," + OBTENIR_RUCHE_VERSION + ")REFERENCES RUCHE (" + RUCHE_NUM_AGRIT + "," + RUCHE_NUMERO_RUCHER + "," + RUCHE_NUMERO_RUCHE + "," + RUCHE_VERSION + ") ," +
                    "FOREIGN KEY (" + OBTENIR_TRAITEMENT_NUMERO + ")REFERENCES TRAITEMENT (" + TRAITEMENT_NUMERO + ") ," +
                    "FOREIGN KEY (" + OBTENIR_DATE_DEBUT + ")REFERENCES PERIODE (" + DATE_DEBUT + ") );";

    public static final String OBTENIR_TABLE_DROP = "DROP TABLE IF EXISTS " + OBTENIR_TABLE_NAME + ";";


    /*------------------------------------PESER------------------------------------*/
    public static final String PESER_TABLE_NAME = "PESER";

    //NUM_AGRIT, N_RUCHER, N_RUCHE, VERSION, PESER_JOUR, PESER_MOIS, PESER_ANNEE, POIDS

    public static final String PESER_NUM_AGRIT = "NUM_AGRIT";
    public static final String PESER_NUMERO_RUCHER = "N_RUCHER";
    public static final String PESER_NUMERO_RUCHE = "N_RUCHE";
    public static final String PESER_RUCHE_VERSION = "VERSION";
    public static final String PESER_DATE = "PESER_DATE";
    public static final String PESER_POIDS = "POIDS";

    public static final String PESER_TABLE_CREATE =
            "CREATE TABLE " + PESER_TABLE_NAME + " (" +
                    PESER_NUM_AGRIT + " INTEGER, " +
                    PESER_NUMERO_RUCHER + "  INTEGER, " +
                    PESER_NUMERO_RUCHE + "  INTEGER, " +
                    PESER_RUCHE_VERSION + " INTEGER, " +
                    PESER_DATE + " DATE, " +
                    PESER_POIDS + " REAL," +
                    "PRIMARY KEY (" + PESER_NUM_AGRIT + "," + PESER_NUMERO_RUCHER + "," + PESER_NUMERO_RUCHE + "," + PESER_DATE + "," + PESER_RUCHE_VERSION + ")," +
                    "FOREIGN KEY (" + PESER_NUM_AGRIT + "," + PESER_NUMERO_RUCHER + "," + PESER_NUMERO_RUCHE + "," + PESER_RUCHE_VERSION + ")REFERENCES RUCHE (" + RUCHE_NUM_AGRIT + "," + RUCHE_NUMERO_RUCHER + "," + RUCHE_NUMERO_RUCHE + "," + RUCHE_VERSION + ") ," +
                    "FOREIGN KEY (" + PESER_DATE + ")REFERENCES PERIODE (" + DATE_DEBUT + ") );";

    public static final String PESER_TABLE_DROP = "DROP TABLE IF EXISTS " + PESER_TABLE_NAME + ";";

    /*------------------------------------RECEVOIR------------------------------------*/
    public static final String RECEVOIR_TABLE_NAME = "RECEVOIR";

    //NUM_AGRIT, N_RUCHER, N_RUCHE, NUM_NOURRISSEMENT, JOUR, MOIS, ANNEE, QUANTITE, REMARQUE, VESION

    public static final String RECEVOIR_NUM_AGRIT = "NUM_AGRIT";
    public static final String RECEVOIR_NUMERO_RUCHER = "N_RUCHER";
    public static final String RECEVOIR_NUMERO_RUCHE = "N_RUCHE";
    public static final String RECEVOIR_NUMERO_NOURRISSEMENT = "NUM_NOURRISSEMENT";
    public static final String RECEVOIR_RUCHE_VERSION = "VERSION";
    public static final String RECEVOIR_DATE = "DATE_RECEVOIR";
    public static final String RECEVOIR_QUANTITE = "QUANTITE";
    public static final String RECEVOIR_REMARQ = "REMARQUE";

    public static final String RECEVOIR_TABLE_CREATE =
            "CREATE TABLE " + RECEVOIR_TABLE_NAME + " (" +
                    RECEVOIR_NUM_AGRIT + " INTEGER, " +
                    RECEVOIR_NUMERO_RUCHER + " INTEGER, " +
                    RECEVOIR_NUMERO_RUCHE + " INTEGER, " +
                    RECEVOIR_NUMERO_NOURRISSEMENT + " INTEGER, " +
                    RECEVOIR_DATE + " DATE, " +
                    RECEVOIR_QUANTITE + " REAL, " +
                    RECEVOIR_REMARQ + " TEXT," +
                    RECEVOIR_RUCHE_VERSION + " INTEGER," +
                    "PRIMARY KEY (" + RECEVOIR_NUM_AGRIT + "," + RECEVOIR_NUMERO_RUCHER + "," + RECEVOIR_NUMERO_RUCHE + "," + RECEVOIR_RUCHE_VERSION + "," + RECEVOIR_NUMERO_NOURRISSEMENT + "," + RECEVOIR_DATE + ")," +
                    "FOREIGN KEY (" + RECEVOIR_NUM_AGRIT + "," + RECEVOIR_NUMERO_RUCHER + "," + RECEVOIR_NUMERO_RUCHE + "," + RECEVOIR_RUCHE_VERSION + ")REFERENCES RUCHE (" + RUCHE_NUM_AGRIT + "," + RUCHE_NUMERO_RUCHER + "," + RUCHE_NUMERO_RUCHE + "," + RUCHE_VERSION + ") ," +
                    "FOREIGN KEY (" + RECEVOIR_DATE + ")REFERENCES CALENDRIER (" + DATE_CALENDRIER + ") ," +
                    "FOREIGN KEY (" + RECEVOIR_NUMERO_NOURRISSEMENT + ")REFERENCES NOURRISSEMENT (" + NUM_NOURRISSEMENT + ") );";

    public static final String RECEVOIR_TABLE_DROP = "DROP TABLE IF EXISTS " + RECEVOIR_TABLE_NAME + ";";


    /*-------------------------------------------TRIGGERS-----------------------------------*/
            /*-----------------------------------APICULTEUR / NUM_AGRIT---------------------*/
    public static final String TRIGGER_UPDATE_APICULTEUR =
            "CREATE TRIGGER IF NOT EXISTS UPDATE_APICULTEUR BEFORE UPDATE OF NUM_AGRIT ON APICULTEUR " +
                "BEGIN " +
                    "UPDATE RECEVOIR SET NUM_AGRIT = NEW.NUM_AGRIT WHERE NUM_AGRIT=OLD.NUM_AGRIT;" +
                    "UPDATE PESER SET NUM_AGRIT = NEW.NUM_AGRIT WHERE NUM_AGRIT=OLD.NUM_AGRIT;" +
                    "UPDATE OBTENIR SET NUM_AGRIT = NEW.NUM_AGRIT WHERE NUM_AGRIT=OLD.NUM_AGRIT;" +
                    "UPDATE SUPPRIMER_RUCHE SET NUM_AGRIT = NEW.NUM_AGRIT WHERE NUM_AGRIT=OLD.NUM_AGRIT;" +
                    "UPDATE RUCHE SET NUM_AGRIT = NEW.NUM_AGRIT WHERE NUM_AGRIT=OLD.NUM_AGRIT;" +
                    "UPDATE RUCHER SET NUM_AGRIT = NEW.NUM_AGRIT WHERE NUM_AGRIT=OLD.NUM_AGRIT;" +
                "END;";
            /*-----------------------------------RUCHER / N_RUCHER---------------------*/
    public static final String TRIGGER_UPDATE_RUCHER =
            "CREATE TRIGGER IF NOT EXISTS UPDATE_RUCHER BEFORE UPDATE OF N_RUCHER ON RUCHER " +
                "BEGIN " +
                    "UPDATE RECEVOIR SET N_RUCHER = NEW.N_RUCHER WHERE N_RUCHER=OLD.N_RUCHER;" +
                    "UPDATE PESER SET N_RUCHER = NEW.N_RUCHER WHERE N_RUCHER=OLD.N_RUCHER;" +
                    "UPDATE OBTENIR SET N_RUCHER = NEW.N_RUCHER WHERE N_RUCHER=OLD.N_RUCHER;" +
                    "UPDATE SUPPRIMER_RUCHE SET N_RUCHER = NEW.N_RUCHER WHERE N_RUCHER=OLD.N_RUCHER;" +
                    "UPDATE RUCHE SET N_RUCHER = NEW.N_RUCHER WHERE N_RUCHER=OLD.N_RUCHER;" +
                    "UPDATE RUCHER SET N_RUCHER = NEW.N_RUCHER WHERE N_RUCHER=OLD.N_RUCHER;" +
                "END;";

    /*-----------------------------------RUCHE / N_RUCHE---------------------*/
    public static final String TRIGGER_UPDATE_RUCHE =
            "CREATE TRIGGER IF NOT EXISTS UPDATE_RUCHE BEFORE UPDATE OF N_RUCHE ON RUCHE " +
                "BEGIN " +
                    "UPDATE RECEVOIR SET N_RUCHE = NEW.N_RUCHE WHERE N_RUCHE=OLD.N_RUCHE AND N_RUCHER=OLD.N_RUCHER;" +
                    "UPDATE PESER SET N_RUCHE = NEW.N_RUCHE WHERE N_RUCHE=OLD.N_RUCHE AND N_RUCHER=OLD.N_RUCHER;" +
                    "UPDATE OBTENIR SET N_RUCHE = NEW.N_RUCHE WHERE N_RUCHE=OLD.N_RUCHE AND N_RUCHER=OLD.N_RUCHER;" +
                    "UPDATE SUPPRIMER_RUCHE SET N_RUCHE = NEW.N_RUCHE WHERE N_RUCHE=OLD.N_RUCHE AND N_RUCHER=OLD.N_RUCHER;" +
                    "UPDATE RUCHE SET N_RUCHE = NEW.N_RUCHE WHERE N_RUCHE=OLD.N_RUCHE AND N_RUCHER=OLD.N_RUCHER;" +
                    "UPDATE RUCHER SET N_RUCHE = NEW.N_RUCHE WHERE N_RUCHE=OLD.N_RUCHE AND N_RUCHER=OLD.N_RUCHER;" +
                "END;";

    public BDD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void inserer_Base(SQLiteDatabase db) {
        db.execSQL("INSERT INTO APICULTEUR VALUES (1,'Guimbretiere','Herve',1968)");

        db.execSQL("INSERT INTO RUCHER VALUES (1, 1, 1, 12, 'Rue Minants', 'Chezelles', 37220)");
        db.execSQL("INSERT INTO RUCHER VALUES (1, 2, 1, 42, 'Rue de l Abbaye', 'Conques', 11600)");
        db.execSQL("INSERT INTO RUCHER VALUES (1, 3, 1,  6, 'Chemin des paquerettes', 'Beneauville', 14600)");
        db.execSQL("INSERT INTO RUCHER VALUES (1, 4, 1,  5, 'Rue du bourdon', 'Frenouville', 14300)");
        db.execSQL("INSERT INTO RUCHER VALUES (1, 5, 1,  7, 'Rue du frelon', 'Cuverville', 14400)");
        db.execSQL("INSERT INTO RUCHER VALUES (1, 6, 1, 16, 'Impasse de la chouette', 'Cagny', 14300)");

        db.execSQL("INSERT INTO RUCHE VALUES (1,1,1,2,1)");
        db.execSQL("INSERT INTO RUCHE VALUES (1,1,2,1,1)");
        db.execSQL("INSERT INTO RUCHE VALUES (1,1,3,3,1)");
        db.execSQL("INSERT INTO RUCHE VALUES (1,1,4,2,1)");
        db.execSQL("INSERT INTO RUCHE VALUES (1,1,5,1,1)");
        db.execSQL("INSERT INTO RUCHE VALUES (1,2,1,1,1)");
        db.execSQL("INSERT INTO RUCHE VALUES (1,2,2,3,1)");
        db.execSQL("INSERT INTO RUCHE VALUES (1,2,3,2,1)");
        db.execSQL("INSERT INTO RUCHE VALUES (1,2,4,1,1)");
        db.execSQL("INSERT INTO RUCHE VALUES (1,2,5,3,1)");
        db.execSQL("INSERT INTO RUCHE VALUES (1,3,1,1,1)");
        db.execSQL("INSERT INTO RUCHE VALUES (1,3,2,2,1)");
        db.execSQL("INSERT INTO RUCHE VALUES (1,3,3,1,1)");
        db.execSQL("INSERT INTO RUCHE VALUES (1,3,4,2,1)");
        db.execSQL("INSERT INTO RUCHE VALUES (1,3,5,3,1)");
        db.execSQL("INSERT INTO RUCHE VALUES (1,4,1,2,1)");
        db.execSQL("INSERT INTO RUCHE VALUES (1,4,2,1,1)");
        db.execSQL("INSERT INTO RUCHE VALUES (1,4,3,1,1)");
        db.execSQL("INSERT INTO RUCHE VALUES (1,4,4,2,1)");
        db.execSQL("INSERT INTO RUCHE VALUES (1,4,5,3,1)");

        db.execSQL("INSERT INTO NOURRISSEMENT VALUES (1,'Sucre Candy 200g')");
        db.execSQL("INSERT INTO NOURRISSEMENT VALUES (2,'Sucre Candy 250g')");
        db.execSQL("INSERT INTO NOURRISSEMENT VALUES (3,'Sucre Candy 300g')");
        db.execSQL("INSERT INTO NOURRISSEMENT VALUES (4,'Sucre Candy 350g')");
        db.execSQL("INSERT INTO NOURRISSEMENT VALUES (5,'Sucre Candy 400g')");
        db.execSQL("INSERT INTO NOURRISSEMENT VALUES (6,'Sucre Liquide 200g')");
        db.execSQL("INSERT INTO NOURRISSEMENT VALUES (7,'Sucre Liquide 250g')");
        db.execSQL("INSERT INTO NOURRISSEMENT VALUES (8,'Sucre Liquide 300g')");
        db.execSQL("INSERT INTO NOURRISSEMENT VALUES (9,'Sucre Liquide 350g')");
        db.execSQL("INSERT INTO NOURRISSEMENT VALUES (10,'Sucre Liquide 400g')");

        db.execSQL("INSERT INTO TRAITEMENT VALUES (1,'Bandes anti-varroa : 2 bandes','Bandes','Moins d abeilles')");
        db.execSQL("INSERT INTO TRAITEMENT VALUES (2,'Bandes anti-varroa : 3 bandes','Bandes','Moins d abeilles')");
        db.execSQL("INSERT INTO TRAITEMENT VALUES (3,'Traitement frelon asiatique','Spray','Moins d abeilles')");

        db.execSQL("INSERT INTO RECOLTER VALUES (1, 1, 1, '24/08/2014',5,'Beaucoup de Miel')");
        db.execSQL("INSERT INTO RECOLTER VALUES (1, 1, 1, '31/08/2015',2.5,'Peu de Miel')");
        db.execSQL("INSERT INTO RECOLTER VALUES (1, 1, 1, '25/08/2016',2,'Peu de Miel')");
        db.execSQL("INSERT INTO RECOLTER VALUES (1, 1, 1, '15/08/2017',2.75,'Peu de Miel')");
        db.execSQL("INSERT INTO RECOLTER VALUES (1, 1, 1, '20/08/2018',6.00,'Beaucoup de Miel')");
        db.execSQL("INSERT INTO RECOLTER VALUES (1, 1, 1, '01/09/2019',5.50,'Beaucoup de Miel')");

        db.execSQL("INSERT INTO RECOLTER VALUES (1, 2, 1, '24/08/2014',4.75,'Beaucoup de Miel')");
        db.execSQL("INSERT INTO RECOLTER VALUES (1, 2, 1, '31/08/2015',2.75,'Peu de Miel')");
        db.execSQL("INSERT INTO RECOLTER VALUES (1, 2, 1, '25/08/2016',2.00,'Peu de Miel')");
        db.execSQL("INSERT INTO RECOLTER VALUES (1, 2, 1, '15/08/2017',2.75,'Peu de Miel')");
        db.execSQL("INSERT INTO RECOLTER VALUES (1, 2, 1, '20/08/2018',6.00,'Beaucoup de Miel')");
        db.execSQL("INSERT INTO RECOLTER VALUES (1, 2, 1, '01/09/2019',5.50,'Beaucoup de Miel')");

        db.execSQL("INSERT INTO RECOLTER VALUES (1, 3, 1, '25/08/2014',5.00,'Beaucoup de Miel')");
        db.execSQL("INSERT INTO RECOLTER VALUES (1, 3, 1, '31/08/2015',2.50,'Peu de Miel')");
        db.execSQL("INSERT INTO RECOLTER VALUES (1, 3, 1, '25/08/2016',2.00,'Peu de Miel')");
        db.execSQL("INSERT INTO RECOLTER VALUES (1, 3, 1, '15/08/2017',2.75,'Peu de Miel')");
        db.execSQL("INSERT INTO RECOLTER VALUES (1, 3, 1, '20/08/2018',6.00,'Beaucoup de Miel')");
        db.execSQL("INSERT INTO RECOLTER VALUES (1, 3, 1, '01/09/2019',5.50,'Beaucoup de Miel')");

        db.execSQL("INSERT INTO RECOLTER VALUES (1, 4, 1, '25/08/2014',5.00,'Beaucoup de Miel')");
        db.execSQL("INSERT INTO RECOLTER VALUES (1, 4, 1, '31/08/2015',2.50,'Peu de Miel')");
        db.execSQL("INSERT INTO RECOLTER VALUES (1, 4, 1, '25/08/2016',2.00,'Peu de Miel')");
        db.execSQL("INSERT INTO RECOLTER VALUES (1, 4, 1, '15/08/2017',2.75,'Peu de Miel')");
        db.execSQL("INSERT INTO RECOLTER VALUES (1, 4, 1, '20/08/2018',6.00,'Beaucoup de Miel')");
        db.execSQL("INSERT INTO RECOLTER VALUES (1, 4, 1, '01/09/2019',5.50,'Beaucoup de Miel')");


        db.execSQL("INSERT INTO PESER VALUES (1,1,1,1,'08/04/2014',34)");
        db.execSQL("INSERT INTO PESER VALUES (1,1,1,1,'30/08/2015',35)");
        db.execSQL("INSERT INTO PESER VALUES (1,1,1,1,'08/04/2015',36)");
        db.execSQL("INSERT INTO PESER VALUES (1,1,2,1,'30/08/2015',37)");
        db.execSQL("INSERT INTO PESER VALUES (1,1,3,1,'08/04/2015',35)");
        db.execSQL("INSERT INTO PESER VALUES (1,1,3,1,'30/08/2015',38)");
        db.execSQL("INSERT INTO PESER VALUES (1,1,4,1,'08/04/2015',35)");
        db.execSQL("INSERT INTO PESER VALUES (1,1,4,1,'30/08/2015',37)");
        db.execSQL("INSERT INTO PESER VALUES (1,1,5,1,'08/04/2015',36)");
        db.execSQL("INSERT INTO PESER VALUES (1,1,5,1,'30/08/2015',35)");

        db.execSQL("INSERT INTO PESER VALUES (1,2,1,1,'08/04/2014',34)");
        db.execSQL("INSERT INTO PESER VALUES (1,2,1,1,'30/08/2015',35)");
        db.execSQL("INSERT INTO PESER VALUES (1,2,1,1,'08/04/2015',36)");
        db.execSQL("INSERT INTO PESER VALUES (1,2,2,1,'30/08/2015',37)");
        db.execSQL("INSERT INTO PESER VALUES (1,2,3,1,'08/04/2015',35)");
        db.execSQL("INSERT INTO PESER VALUES (1,2,3,1,'30/08/2015',36)");
        db.execSQL("INSERT INTO PESER VALUES (1,2,4,1,'08/04/2015',34)");
        db.execSQL("INSERT INTO PESER VALUES (1,2,4,1,'30/08/2015',37)");
        db.execSQL("INSERT INTO PESER VALUES (1,2,5,1,'08/04/2015',36)");
        db.execSQL("INSERT INTO PESER VALUES (1,2,5,1,'30/08/2015',35)");

        db.execSQL("INSERT INTO PESER VALUES (1,3,1,1,'08/04/2014',34)");
        db.execSQL("INSERT INTO PESER VALUES (1,3,1,1,'30/08/2015',35)");
        db.execSQL("INSERT INTO PESER VALUES (1,3,1,1,'08/04/2015',36)");
        db.execSQL("INSERT INTO PESER VALUES (1,3,2,1,'30/08/2015',37)");
        db.execSQL("INSERT INTO PESER VALUES (1,3,3,1,'08/04/2015',35)");
        db.execSQL("INSERT INTO PESER VALUES (1,3,3,1,'30/08/2015',36)");
        db.execSQL("INSERT INTO PESER VALUES (1,3,4,1,'08/04/2015',34)");
        db.execSQL("INSERT INTO PESER VALUES (1,3,4,1,'30/08/2015',37)");
        db.execSQL("INSERT INTO PESER VALUES (1,3,5,1,'08/04/2015',35)");
        db.execSQL("INSERT INTO PESER VALUES (1,3,5,1,'30/08/2015',38)");

        db.execSQL("INSERT INTO PESER VALUES (1,4,1,1,'08/04/2014',34)");
        db.execSQL("INSERT INTO PESER VALUES (1,4,1,1,'30/08/2015',35)");
        db.execSQL("INSERT INTO PESER VALUES (1,4,1,1,'08/04/2015',36)");
        db.execSQL("INSERT INTO PESER VALUES (1,4,2,1,'30/08/2015',37)");
        db.execSQL("INSERT INTO PESER VALUES (1,4,3,1,'08/04/2015',37)");
        db.execSQL("INSERT INTO PESER VALUES (1,4,3,1,'30/08/2015',36)");
        db.execSQL("INSERT INTO PESER VALUES (1,4,4,1,'08/04/2015',35)");
        db.execSQL("INSERT INTO PESER VALUES (1,4,4,1,'30/08/2015',38)");
        db.execSQL("INSERT INTO PESER VALUES (1,4,5,1,'08/04/2015',34)");
        db.execSQL("INSERT INTO PESER VALUES (1,4,5,1,'30/08/2015',32)");



    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(APICULTEUR_TABLE_CREATE);
        db.execSQL(RUCHER_TABLE_CREATE);
        db.execSQL(RUCHE_SUPP_TABLE_CREATE);
        db.execSQL(RUCHE_TABLE_CREATE);
        db.execSQL(TRAITEMENT_TABLE_CREATE);
        db.execSQL(RECOLTER_TABLE_CREATE);
        db.execSQL(PERIODE_TABLE_CREATE);
        db.execSQL(NOURRISSEMENT_TABLE_CREATE);
        db.execSQL(OBTENIR_TABLE_CREATE);
        db.execSQL(CALENDRIER_TABLE_CREATE);
        db.execSQL(PESER_TABLE_CREATE);
        db.execSQL(RECEVOIR_TABLE_CREATE);
        db.execSQL(RUCHER_SUPP_TABLE_CREATE);
        db.execSQL(TRIGGER_UPDATE_APICULTEUR);
        db.execSQL(TRIGGER_UPDATE_RUCHER);
        db.execSQL(TRIGGER_UPDATE_RUCHE);
        inserer_Base(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        delete(db);
        onCreate(db);
    }

    public void delete(SQLiteDatabase db) {
        db.execSQL(RECOLTER_TABLE_DROP);
        db.execSQL(OBTENIR_TABLE_DROP);
        db.execSQL(PESER_TABLE_DROP);
        db.execSQL(RECEVOIR_TABLE_DROP);
        db.execSQL(PERIODE_TABLE_DROP);
        db.execSQL(CALENDRIER_TABLE_DROP);
        db.execSQL(NOURRISSEMENT_TABLE_DROP);
        db.execSQL(TRAITEMENT_TABLE_DROP);
        db.execSQL(RUCHE_SUPP_TABLE_DROP);
        db.execSQL(RUCHE_TABLE_DROP);
        db.execSQL(RUCHER_SUPP_TABLE_DROP);
        db.execSQL(RUCHER_TABLE_DROP);
        db.execSQL(APICULTEUR_TABLE_DROP);
    }
}
