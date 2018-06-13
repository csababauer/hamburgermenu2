package com.example.csaba.hamburgermenu2;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
    }

    public static class EventPreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener{

        /**Finally we just have to override the onCreate() method of EventPreferenceFragment:*/
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings_main);


            /**update the preference summary (the UI) when the settings activity is launched in onCreate().
             * To do so, we’ll need to first find the preference we’re interested in
             * and then bind the current preference value to be displayed.*/
            Preference minMagnitude = findPreference(getString(R.string.settings_min_magnitude_key));
            bindPreferenceSummaryToValue(minMagnitude);
        }

        /**set up an empty onPreferenceChange method right under the onCreate() method that we’ll be filling in.
         * Remember that this method will be called when the user has changed a Preference,
         * so inside of it we should add whatever action we want to happen after this change.
         * In this case, we’ll want to update the displayed preference summary (the UI) after it’s been changed: */
        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();
            preference.setSummary(stringValue);
            return true;
        }

        /**helper method to overwrite the minMagnitude*/
        private void bindPreferenceSummaryToValue(Preference preference) {
            /**This method takes in a Preference as its parameter,
             * and we use setOnPreferenceChangeListener to set the current EventPreferenceFragment instance to listen for changes
             * to the preference we pass in using:*/
            preference.setOnPreferenceChangeListener(this);
            /**We also read the current value of the preference stored in the SharedPreferences on the device,
             * and display that in the preference summary
             * (so that the user can see the current value of the preference):*/
            SharedPreferences preferences =
                    PreferenceManager.getDefaultSharedPreferences(preference.getContext());
            String preferenceString = preferences.getString(preference.getKey(), "");
            onPreferenceChange(preference, preferenceString);
        }
    }
}