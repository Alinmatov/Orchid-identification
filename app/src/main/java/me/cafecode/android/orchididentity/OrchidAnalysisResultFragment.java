package me.cafecode.android.orchididentity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;

import me.cafecode.android.orchididentity.api.Orchid;
import me.cafecode.android.orchididentity.photo.PhotoManager;

public class OrchidAnalysisResultFragment extends Fragment {

    private ImageView mOrchidImageView;
    private ImageView mPhotoImageView;

    private TextView mScienceNameText;
    private TextView mOtherNameText;
    private Orchid mOrchid;

    public static OrchidAnalysisResultFragment newInstance(Orchid orchid) {
        OrchidAnalysisResultFragment fragment = new OrchidAnalysisResultFragment();
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
        View view = inflater.inflate(R.layout.fragment_orchid_analysis_result, container, false);

        mOrchidImageView = (ImageView) view.findViewById(R.id.orchid_image);
        mPhotoImageView = (ImageView) view.findViewById(R.id.photo_image);

        mScienceNameText = (TextView) view.findViewById(R.id.orchid_science_name_text);
        mOtherNameText = (TextView) view.findViewById(R.id.orchid_other_name_text);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bindView();
    }

    private void bindView() {
        File photoFile = PhotoManager.getFile(getActivity());
        final Bitmap photoBitmap = PhotoManager.getBitmap(photoFile);
        mPhotoImageView.setImageBitmap(photoBitmap);

        Glide.with(getActivity())
                .load(mOrchid.getOrchidImage())
                .crossFade()
                .centerCrop()
                .into(mOrchidImageView);

        mScienceNameText.setText(mOrchid.getScienceName());
        mOtherNameText.setText(mOrchid.getOtherName());
    }
}
