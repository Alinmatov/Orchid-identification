package me.cafecode.android.orchididentity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import me.cafecode.android.orchididentity.api.Orchid;

public class OrchidDetailFragment extends Fragment {

    private TextView mScienceNameText;
    private TextView mOtherNameText;

    private TextView mNatureText;
    private TextView mNativePlaceText;
    private TextView mGeneralText;
    private TextView mShootText;
    private TextView mLeafText;
    private TextView mFlowerText;
    private TextView mBloomText;
    private ImageView mPhotoImage;
    private Orchid mOrchid;

    public static OrchidDetailFragment newInstance(Orchid orchid) {
        OrchidDetailFragment fragment = new OrchidDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(Orchid.ARG_ORCHID, orchid);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mOrchid = bundle.getParcelable(Orchid.ARG_ORCHID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_orchid_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPhotoImage = (ImageView) view.findViewById(R.id.orchid_photo_image);
        mScienceNameText = (TextView) view.findViewById(R.id.orchid_science_name_text);
        mOtherNameText = (TextView) view.findViewById(R.id.orchid_other_name_text);
        mNativePlaceText = (TextView) view.findViewById(R.id.orchid_native_place_text);
        mGeneralText = (TextView) view.findViewById(R.id.orchid_general_text);
        mShootText = (TextView) view.findViewById(R.id.orchid_shoot_text);
        mLeafText = (TextView) view.findViewById(R.id.orchid_leaf_text);
        mFlowerText = (TextView) view.findViewById(R.id.orchid_flower_text);
        mBloomText = (TextView) view.findViewById(R.id.orchid_bloom_text);
        mNatureText = (TextView) view.findViewById(R.id.orchid_nature_text);

        setHasOptionsMenu(true);

        bindView();
    }
    //endregion

    private void bindView() {
        mScienceNameText.setText(mOrchid.getScienceName());
        mOtherNameText.setText(mOrchid.getOtherName());

        mNativePlaceText.setText(Html.fromHtml(String.format(
                getString(R.string.orchid_detail_native_place),
                mOrchid.getNativePlace())));

        mGeneralText.setText(Html.fromHtml(String.format(
                getString(R.string.orchid_detail_general),
                mOrchid.getGeneral())));


        mShootText.setText(Html.fromHtml(String.format(
                getString(R.string.orchid_detail_shoot),
                mOrchid.getShoot())));

        mLeafText.setText(Html.fromHtml(String.format(
                getString(R.string.orchid_detail_leaf),
                mOrchid.getLeaf())));

        mFlowerText.setText(Html.fromHtml(String.format(
                getString(R.string.orchid_detail_flower),
                mOrchid.getFlower())));

        mBloomText.setText(Html.fromHtml(String.format(
                getString(R.string.orchid_detail_bloom),
                mOrchid.getBloom())));

        mNatureText.setText(Html.fromHtml(String.format(
                getString(R.string.orchid_detail_nature),
                mOrchid.getNature())));

        Glide.with(getActivity())
                .load(mOrchid.getOrchidImage())
                .crossFade()
                .centerCrop()
                .into(mPhotoImage);
    }

}
