package coffeespace.com.br.periciasis.Activitys;

import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Environment;
import android.preference.PreferenceFragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.Metadata;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.query.Filters;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.drive.query.SearchableField;
import com.google.api.services.drive.model.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


import coffeespace.com.br.periciasis.R;
import coffeespace.com.br.periciasis.Sistema.Ocorrencia;
import coffeespace.com.br.periciasis.Sistema.Pericia;
import coffeespace.com.br.periciasis.Sistema.Perito;

public class MainActivity extends AppCompatActivity  {


    /*** MORON
     */

    static Pericia pericia;
    static Ocorrencia ocorrencia;
    static Perito perito;

    public static Ocorrencia getOcorrencia() {
        return ocorrencia;
    }

    public static void setOcorrencia(Ocorrencia ocorrencia) {
        MainActivity.ocorrencia = ocorrencia;
    }

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ocorrenciaModelo();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }




    /**
     * A placeholder fragment containing a simple view.
     */


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
            //return PlaceholderFragment.newInstance(position + 1);
            switch (position) {
                case 0:
                    OcorrenciaActivity oc = new OcorrenciaActivity();
                    oc.setAct(MainActivity.this);
                    return oc;
                case 1:
                    ExamesActivity exames = new ExamesActivity();
                    exames.setAct(MainActivity.this);
                    return exames;
                case 2:
                    DadosActivity dados = new DadosActivity();
                    dados.setAct(MainActivity.this);
                    return dados;
                case 3:
                    LaudoActivity laudo = new LaudoActivity();
                    laudo.setAct(MainActivity.this);
                    return laudo;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }
    }

    public static class MainSettingsFragment extends PreferenceFragment{
        @Override
        public void onCreate(Bundle SavedInstanceState){
            super.onCreate(SavedInstanceState);
            addPreferencesFromResource(R.xml.settings_screen);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mMenuInflater = getMenuInflater();
        mMenuInflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.config) {
            startActivity(new Intent(MainActivity.this, PreferencesActivity.class));
            Toast.makeText(this, "Config", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId() == R.id.about_us) {
            Toast.makeText(this, "Falta do que fazer dá nisso", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
    public void ocorrenciaModelo(){

        ocorrencia = new Ocorrencia("1234","4321","16º Delegacia de Polícia");
        ocorrencia.setAno("2000");
        ocorrencia.setDia("1");
        ocorrencia.setMes("janeiro");
        ocorrencia.setLaudo("0000");
        ocorrencia.setHora("22");
        ocorrencia.setMinuto("30");
        ocorrencia.setEndereco("Rua das Marrecas, nº 1559, Centro do Rio de Janeiro");
       ocorrencia.setTipolocal(getString(R.string.tipo_local));

        perito = new Perito("Zé das Pulgas");
        perito.setId("5.000.000-1");
        perito.setLotacao("SPL-Complexo do Alemão");
        perito.setOrgao("ICCE-RJ");
        pericia = new Pericia(ocorrencia);
        pericia.setPeritodesignado(perito);


    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // do nothing, just override
    }


}
