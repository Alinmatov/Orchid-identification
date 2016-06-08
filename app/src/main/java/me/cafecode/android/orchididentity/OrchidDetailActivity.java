package me.cafecode.android.orchididentity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class OrchidDetailActivity extends AppCompatActivity
        implements OrchidDetailFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orchid_detail);

        OrchidDetailFragment orchidDetailFragment = OrchidDetailFragment.newInstance("Test");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.orchid_detail_fragment, orchidDetailFragment)
                .commit();

        //TODO: Test
//        ImageEnergy imageEnergy = new ImageEnergy();
//        imageEnergy.CalEn();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
