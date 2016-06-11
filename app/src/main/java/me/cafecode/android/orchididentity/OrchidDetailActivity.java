package me.cafecode.android.orchididentity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.mathworks.toolbox.javabuilder.MWException;

import CalEn.ImageEnergy;

public class OrchidDetailActivity extends AppCompatActivity
        implements OrchidDetailFragment.OnFragmentInteractionListener {

    private static final String LOG_TAG = OrchidDetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orchid_detail);

        OrchidDetailFragment orchidDetailFragment = OrchidDetailFragment.newInstance("Test");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.orchid_detail_fragment, orchidDetailFragment)
                .commit();

        //TODO: Test
        try {
            ImageEnergy imageEnergy = new ImageEnergy();
            Object[] result = imageEnergy.CalEn(1, "hello", "Hi");
            Log.d(LOG_TAG, result.toString());
        } catch (MWException e) {
            Toast.makeText(OrchidDetailActivity.this, "ImageEnergy failed.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
