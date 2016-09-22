package me.cafecode.android.orchididentity.camera;


import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.android.cameraview.CameraView;

import java.io.IOException;

import me.cafecode.android.orchididentity.OrchidDetailActivity;
import me.cafecode.android.orchididentity.R;
import me.cafecode.android.orchididentity.photo.PhotoManager;

public class CameraFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = CameraFragment.class.getSimpleName();
    private static final int REQUEST_CAMERA_PERMISSION = 1;
    private static final int REQUEST_SELECT_PICTURE = 12;

    private CameraView mCameraView;
    private Handler mBackgroundHandler;

    private static final int[] FLASH_OPTIONS = {
            CameraView.FLASH_AUTO,
            CameraView.FLASH_OFF,
            CameraView.FLASH_ON,
    };

    private static final int[] FLASH_ICONS = {
            R.drawable.ic_flash_auto,
            R.drawable.ic_flash_off,
            R.drawable.ic_flash_on,
    };

    private static final int[] FLASH_TITLES = {
            R.string.flash_auto,
            R.string.flash_off,
            R.string.flash_on,
    };

    public CameraFragment() {
        // Required empty public constructor
    }

    public static CameraFragment newInstance() {
        return new CameraFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_camera, container, false);
        mCameraView = (CameraView) view.findViewById(R.id.cameraview);
        if (mCameraView != null) {
            mCameraView.addCallback(mCallback);
        }

        ImageButton takePhotoButton = (ImageButton) view.findViewById(R.id.capture_button);
        takePhotoButton.setOnClickListener(this);

        ImageButton galleryButton = (ImageButton) view.findViewById(R.id.gallery_button);
        galleryButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            mCameraView.start();
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                Manifest.permission.CAMERA)) {
//            ConfirmationDialogFragment
//                    .newInstance(R.string.camera_permission_confirmation,
//                            new String[]{Manifest.permission.CAMERA},
//                            REQUEST_CAMERA_PERMISSION,
//                            R.string.camera_permission_not_granted)
//                    .show(getSupportFragmentManager(), FRAGMENT_DIALOG);
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA},
                    REQUEST_CAMERA_PERMISSION);
        }
    }

    @Override
    public void onPause() {
        mCameraView.stop();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBackgroundHandler != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                mBackgroundHandler.getLooper().quitSafely();
            } else {
                mBackgroundHandler.getLooper().quit();
            }
            mBackgroundHandler = null;
        }
    }

    private Handler getBackgroundHandler() {
        if (mBackgroundHandler == null) {
            HandlerThread thread = new HandlerThread("background");
            thread.start();
            mBackgroundHandler = new Handler(thread.getLooper());
        }
        return mBackgroundHandler;
    }

    private CameraView.Callback mCallback
            = new CameraView.Callback() {

        @Override
        public void onCameraOpened(CameraView cameraView) {
            Log.d(TAG, "onCameraOpened");
        }

        @Override
        public void onCameraClosed(CameraView cameraView) {
            Log.d(TAG, "onCameraClosed");
        }

        @Override
        public void onPictureTaken(CameraView cameraView, final byte[] data) {
            getBackgroundHandler().post(new Runnable() {
                @Override
                public void run() {
                    // Save bitmap to file
                    PhotoManager.saveDataToFile(getActivity(), data);

                    startOrchidActivity();
                }
            });
        }

    };

    //region OnClickListener
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.capture_button:
                if (mCameraView != null) {
                    mCameraView.takePicture();
                }
                break;
            case R.id.gallery_button:
                pickPhotoFromGallery();
                break;
        }
    }
    //endregion

    private void pickPhotoFromGallery() {
        // in onCreate or any event where your want the user to
        // select a file
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), REQUEST_SELECT_PICTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_SELECT_PICTURE) {
                final Uri imageUri = data.getData();

                getBackgroundHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // Save file from gallery
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
                            PhotoManager.saveBitmapToFile(getActivity(), bitmap);

                            startOrchidActivity();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        }
    }

    private void startOrchidActivity() {
        startActivity(new Intent(getActivity(), OrchidDetailActivity.class));
    }
}
