package coffeespace.com.br.periciasis.Activitys;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import coffeespace.com.br.periciasis.R;

/**
 * Created by user on 23/10/2017.
 */

public class Settings extends PreferencesActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {


        super.onCreate(savedInstanceState, persistentState);
        getFragmentManager().beginTransaction().replace(android.R.id.content,new SettingsFrag()).commit();

    }
public static class SettingsFrag extends PreferenceFragment{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_screen);
    }
}


}
