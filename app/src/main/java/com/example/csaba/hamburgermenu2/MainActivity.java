package com.example.csaba.hamburgermenu2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**this sharedpreferences brings the value from the hamburger menu settings*/
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        // getString retrieves a String value from the preferences. The second parameter is the default value for this preference.
        String newNumber = sharedPrefs.getString(
                getString(R.string.settings_min_magnitude_key),
                getString(R.string.settings_min_magnitude_default));

        TextView text = (TextView) findViewById(R.id.hello);
        text.setText(newNumber);
    }


    /**The first method is onCreateOptionsMenu. Similar to how onCreate() helps us inflate our Activity,
     * onCreateOptionsMenu() inflates the Options Menu we specified in the XML when the EarthquakeActivity opens up.*/
    @Override
    // This method initialize the contents of the Activity's options menu.
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the Options Menu we specified in XML
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    // This method is called whenever an item in the options menu is selected.
    /**This method passes the MenuItem that is selected:*/
    public boolean onOptionsItemSelected(MenuItem item) {
        /**To determine which item was selected and what action to take, call getItemId,
         * which returns the unique ID for the menu item (defined by the android:id attribute in the menu resource).*/
        int id = item.getItemId();
        /**You then match the ID against known menu items to perform the appropriate action.
         * In our case, we open the SettingsActivity via an intent.*/
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
