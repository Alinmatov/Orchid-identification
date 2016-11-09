package me.cafecode.android.orchididentity.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import me.cafecode.android.orchididentity.OrchidAnalysisResultFragment;
import me.cafecode.android.orchididentity.OrchidDetailFragment;
import me.cafecode.android.orchididentity.api.Orchid;

public class OrchidFragmentPagerAdapter extends FragmentPagerAdapter {

    private Orchid mOrchid;

    public OrchidFragmentPagerAdapter(FragmentManager fm, Orchid orchid) {
        super(fm);
        mOrchid = orchid;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return OrchidAnalysisResultFragment.newInstance(mOrchid);
            case 1:
                return OrchidDetailFragment.newInstance(mOrchid);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
