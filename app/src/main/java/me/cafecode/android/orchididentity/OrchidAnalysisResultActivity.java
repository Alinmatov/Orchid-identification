package me.cafecode.android.orchididentity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;

import me.cafecode.android.orchididentity.api.ApiManager;
import me.cafecode.android.orchididentity.api.ApiResponse;
import me.cafecode.android.orchididentity.api.ResponseCallback;
import me.cafecode.android.orchididentity.photo.PhotoManager;
import me.cafecode.android.orchididentity.view.OrchidFragmentPagerAdapter;

public class OrchidAnalysisResultActivity extends AppCompatActivity
        implements ResponseCallback<ApiResponse> {

    @SuppressWarnings("unused")
    private static final String LOG_TAG = OrchidAnalysisResultActivity.class.getSimpleName();
    private ProgressBar mProgressBar;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orchid_analysis_result);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        File photoFile = PhotoManager.getFile(this);
        ApiManager.identifyOrchid(photoFile, this);

//        OrchidDetailFragment orchidDetailFragment = OrchidDetailFragment.newInstance("Test");
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.orchid_detail_fragment, new OrchidAnalysisResultFragment())
//                .commit();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public void startRequest() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void endRequest() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess(ApiResponse response) {
        mViewPager.setVisibility(View.VISIBLE);
        OrchidFragmentPagerAdapter adapter = new OrchidFragmentPagerAdapter(
                getSupportFragmentManager(),
                response.getOrchid());
        mViewPager.setAdapter(adapter);
    }

    @Override
    public void onFailure(Throwable throwable) {
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
        finish();
    }
}
