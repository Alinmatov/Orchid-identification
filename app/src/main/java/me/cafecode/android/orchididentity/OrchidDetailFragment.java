package me.cafecode.android.orchididentity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;

import me.cafecode.android.orchididentity.api.ApiManager;
import me.cafecode.android.orchididentity.api.ApiResponse;
import me.cafecode.android.orchididentity.api.Orchid;
import me.cafecode.android.orchididentity.api.ResponseCallback;
import me.cafecode.android.orchididentity.photo.PhotoManager;

public class OrchidDetailFragment extends Fragment implements ResponseCallback<ApiResponse> {

    private static final String ARG_IMAGE_ENERGY = "image_energy";

    @SuppressWarnings("unused")
    private static final String LOG_TAG = OrchidDetailFragment.class.getSimpleName();

    private OnFragmentInteractionListener mListener;
    private File mFile;
    private ProgressDialog mLoadingDialog;
    private TextView mScienceNameText;
    private Toolbar mToolBar;
    private TextView mNatureText;
    private ImageView mOrchidImage;
    private TextView mOtherNameText;

    private Orchid mOrchid;
    private TextView mNativePlaceText;
    private TextView mGeneralText;
    private TextView mShootText;
    private TextView mLeafText;
    private TextView mFlowerText;
    private TextView mBloomText;

    public OrchidDetailFragment() {
        // Required empty public constructor
    }

    public static OrchidDetailFragment newInstance(String imageEnergy) {
        OrchidDetailFragment fragment = new OrchidDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_IMAGE_ENERGY, imageEnergy);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFile = new File(getActivity().getExternalFilesDir(null), Constants.PHOTO_NAME);
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

        ImageView photoImage = (ImageView) view.findViewById(R.id.orchid_image);

        final Bitmap photoBitmap = PhotoManager.getBitmap(mFile);
        photoImage.setImageBitmap(photoBitmap);

        mOrchidImage = (ImageView) view.findViewById(R.id.orchid_header_image);
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

        mToolBar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolBar);

        // Get orchid endpoint
        callIdentifyOrchidEndpoint(mFile);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    //region Identify orchid endpoint
    private void callIdentifyOrchidEndpoint(File photoFile) {
        ApiManager.identifyOrchid(photoFile, this);
    }

    @Override
    public void startRequest() {

        mLoadingDialog = ProgressDialog.show(getActivity(),
                null,
                getString(R.string.process_photo),
                false,
                false);
    }

    @Override
    public void endRequest() {
        mLoadingDialog.dismiss();
    }

    @Override
    public void onSuccess(ApiResponse response) {
        mOrchid = response.getOrchid();
        bindView();
    }

    @Override
    public void onFailure() {
        Toast.makeText(getActivity(), "Call api failed.", Toast.LENGTH_SHORT).show();
    }

    //endregion

    private void bindView() {
        mToolBar.setTitle("");
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
                .into(mOrchidImage);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
