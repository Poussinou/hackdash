package com.tasomaniac.dashclock.hackerspace.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.apps.dashclock.api.DashClockExtension;
import com.tasomaniac.dashclock.hackerspace.R;

public class SettingsActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        boolean fromDashClock = getIntent().getBooleanExtra(DashClockExtension.EXTRA_FROM_DASHCLOCK_SETTINGS, false);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_done);
        toolbar.setNavigationContentDescription(R.string.done);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(getString(R.string.settings_label));

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.fragment_container,
                            SettingsFragment.newInstance(fromDashClock))
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            if (prefs.getBoolean(getString(R.string.pref_key_dashclock_integration), false)) {
                Toast.makeText(this, R.string.error_install_dashclock, Toast.LENGTH_LONG).show();
            } else {
                finish();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
