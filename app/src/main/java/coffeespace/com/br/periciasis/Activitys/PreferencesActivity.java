package coffeespace.com.br.periciasis.Activitys;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import coffeespace.com.br.periciasis.R;

public class PreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        Fragment fragment = new SettingsScreen();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        if (savedInstanceState == null){
            //Created for the first time
            fragmentTransaction.add(R.id.preferencesactivity,fragment,"settings_fragment");
            fragmentTransaction.commit();
        }else{
            fragment = getFragmentManager().findFragmentByTag("settings_fragment");

        }
    }

    public static class SettingsScreen extends PreferenceFragment {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings_screen);
        }
    }

}
