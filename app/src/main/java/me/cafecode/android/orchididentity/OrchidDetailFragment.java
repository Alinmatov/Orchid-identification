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
    private TextView mOrchidNameText;
    private Toolbar mToolBar;
    private TextView mOrchidDetailText;
    private ImageView mOrchidImage;

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
        mOrchidNameText = (TextView) view.findViewById(R.id.orchid_name_text);
        mOrchidDetailText = (TextView) view.findViewById(R.id.orchid_description_text);

        setHasOptionsMenu(true);

        mToolBar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolBar);
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
        Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_SHORT).show();

        updateView(response.getOrchid());
    }

    @Override
    public void onFailure() {
        Toast.makeText(getActivity(), "Call api failed.", Toast.LENGTH_SHORT).show();
    }

    //endregion

    private void updateView(Orchid orchid) {
        mToolBar.setTitle(orchid.getScienceName());
        mOrchidNameText.setText(orchid.getScienceName());
        mOrchidDetailText.setText(orchid.getNativePlace());

        Glide.with(getActivity())
                .load(orchid.getOrchidImage())
                .crossFade()
                .centerCrop()
                .into(mOrchidImage);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
