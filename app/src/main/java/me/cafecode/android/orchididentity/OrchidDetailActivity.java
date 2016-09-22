package me.cafecode.android.orchididentity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class OrchidDetailActivity extends AppCompatActivity {

    @SuppressWarnings("unused")
    private static final String LOG_TAG = OrchidDetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orchid_detail);

        OrchidDetailFragment orchidDetailFragment = OrchidDetailFragment.newInstance("Test");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.orchid_detail_fragment, orchidDetailFragment)
                .commit();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

}
