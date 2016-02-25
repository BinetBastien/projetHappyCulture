package com.example.sacer_000.boutongg;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class tabbedInfoRuches extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private int n_rucher;
    private int n_ruche;
    private int exemplaire;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_info_ruches);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        Intent intentRecep = getIntent();
        n_rucher = intentRecep.getIntExtra("n_rucher", 1);
        n_ruche = intentRecep.getIntExtra("n_ruche", 1);
        exemplaire = intentRecep.getIntExtra("exemplaire", 1);


        this.setTitle("Rucher n°" + n_rucher + ", ruche n°" + n_ruche);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        Intent intentRecep = getIntent();
        n_rucher = intentRecep.getIntExtra("n_rucher", 1);
        n_ruche = intentRecep.getIntExtra("n_ruche", 1);
        exemplaire = intentRecep.getIntExtra("exemplaire", 1);


        this.setTitle("Rucher n°" + n_rucher + ", ruche n°" + n_ruche);
    }

    public int getN_rucher() {
        return n_rucher;
    }

    public int getN_ruche() {
        return n_ruche;
    }



    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber, int n_rucher, int n_ruche, int exemplaire) {

            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putInt("n_rucher", n_rucher);
            args.putInt("n_ruche", n_ruche);
            args.putInt("exemplaire", exemplaire);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final Context contexte = this.getContext();

            final View rootView = inflater.inflate(R.layout.fragment_tabbed_info_ruches, container, false);
            final int section, n_rucher, n_ruche, exemplaire;
            Cursor infos;
            section = getArguments().getInt(ARG_SECTION_NUMBER);
            n_rucher = getArguments().getInt("n_rucher");
            n_ruche = getArguments().getInt("n_ruche");
            exemplaire = getArguments().getInt("exemplaire");

            Button nouveau = (Button) rootView.findViewById(R.id.nouveauTab);
            Button retour = (Button) rootView.findViewById(R.id.retourTab);
            retour.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(contexte, menuRuches.class);
                    intent.putExtra("n_rucher", n_rucher);
                    getActivity().finish();
                    startActivity(intent);
                }
            });

            switch (section) {
                case 1:
                    infos = menuPcpl.bdd.select_obtenir_ruche(n_rucher, n_ruche, exemplaire);
                    nouveau.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(contexte, traitementRuche.class);
                            intent.putExtra("n_rucher", n_rucher);
                            intent.putExtra("n_ruche", n_ruche);
                            intent.putExtra("exemplaire", exemplaire);
                            onPause();
                            startActivity(intent);
                        }
                    });
                    break;
                case 2:
                    infos = menuPcpl.bdd.select_recevoir_ruche(n_rucher, n_ruche, exemplaire);
                    nouveau.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(contexte, nourrissementRuche.class);
                            intent.putExtra("n_rucher", n_rucher);
                            intent.putExtra("n_ruche", n_ruche);
                            intent.putExtra("exemplaire", exemplaire);
                            onPause();
                            startActivity(intent);
                        }
                    });
                    break;
                case 3:
                    infos = menuPcpl.bdd.select_peser_ruche(n_rucher, n_ruche, exemplaire);
                    nouveau.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(contexte, poidsRuche.class);
                            intent.putExtra("n_rucher", n_rucher);
                            intent.putExtra("n_ruche", n_ruche);
                            intent.putExtra("exemplaire", exemplaire);
                            onPause();
                            startActivity(intent);
                        }
                    });
                    break;
                default:
                    infos = null;
            }
            List<HashMap<String, String>> liste = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> element;
            if (infos.getCount() > 0) {
                int i = 0;
                infos.moveToFirst();
                for (i = 0; i < infos.getCount(); i++) {
                    element = new HashMap<String, String>();
                    element.put("ID", "Date : " + infos.getString(0));
                    if (section == 3)
                        element.put("libelle", "" + infos.getString(1) + "Kg");
                    else
                        element.put("libelle", "" + infos.getString(1));
                    liste.add(element);
                    infos.moveToNext();
                }
            } else {
                element = new HashMap<String, String>();
                element.put("ID", "  ");
                element.put("libelle", "  ");
                liste.add(element);
            }

            ListAdapter adapter = new SimpleAdapter(contexte, liste, android.R.layout.simple_list_item_2, new String[]{"ID", "libelle"}, new int[]{android.R.id.text1, android.R.id.text2});
            ListView view = (ListView) rootView.findViewById(R.id.listeInfo);
            view.setAdapter(adapter);
            return rootView;

            // restent les problèmes avec les statiques et les problèmes de paramètres pour avoir les infos des bonnes ruches
        }

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            ListView view = (ListView) findViewById(R.id.listeInfo);
            return PlaceholderFragment.newInstance(position + 1, n_rucher, n_ruche, exemplaire);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Traitements";
                case 1:
                    return "Nourrissements";
                case 2:
                    return "Poids";
            }
            return null;
        }
    }
}
