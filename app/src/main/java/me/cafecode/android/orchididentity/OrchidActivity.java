package me.cafecode.android.orchididentity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.io.File;

public class OrchidActivity extends AppCompatActivity {

    private File mFile;
    ImageView mPhotoImage;
    ImageView mGrayImage;
    ImageView mSegmentationImage;

    private BaseLoaderCallback mOpenCVCallBack = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS: {
                    //your code
                    Bitmap bitmap = new PhotoManager().getBitmap(mFile);

                    Mat sourceMat = new Mat(bitmap.getWidth(), bitmap.getHeight(), CvType.CV_8UC1);
                    Utils.bitmapToMat(bitmap, sourceMat);

                    Mat grayMat = new Mat(bitmap.getWidth(), bitmap.getHeight(), CvType.CV_8UC1);
                    Imgproc.cvtColor(sourceMat, grayMat, Imgproc.COLOR_RGB2GRAY);

                    Bitmap grayBitmap = new PhotoManager().getBitmap(mFile);
                    Utils.matToBitmap(grayMat, grayBitmap);
                    mGrayImage.setImageBitmap(grayBitmap);

                    // TODO: Segmentation
                    Mat cannyMat = new Mat(bitmap.getWidth(), bitmap.getHeight(), CvType.CV_8UC1);
                    Imgproc.Canny(grayMat, cannyMat, 10, 180, 3, true);
                    Bitmap cannyBitmap = Bitmap.createBitmap(bitmap);
                    Utils.matToBitmap(cannyMat, cannyBitmap);
                    mSegmentationImage.setImageBitmap(cannyBitmap);
                }
                break;
                default: {
                    super.onManagerConnected(status);
                }
                break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orchid);

        mPhotoImage = (ImageView) findViewById(R.id.image);
        mGrayImage = (ImageView) findViewById(R.id.gray_image);
        mSegmentationImage = (ImageView) findViewById(R.id.segmentation_image);

        mFile = new File(getExternalFilesDir(null), Constants.PHOTO_NAME);

        Bitmap bitmap = new PhotoManager().getBitmap(mFile);
        if (mPhotoImage != null) {
            mPhotoImage.setImageBitmap(bitmap);
        }

        if (!OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_2, this, mOpenCVCallBack)) {
            Log.e("TEST", "Cannot connect to OpenCV Manager");
        }

    }

    private Bitmap processGrayScaleBitmap(Bitmap bitmap) {
        Mat sourceMat = new Mat(bitmap.getWidth(), bitmap.getHeight(), CvType.CV_8UC1);
        Utils.bitmapToMat(bitmap, sourceMat);
        Mat destinationMat = new Mat(bitmap.getWidth(), bitmap.getHeight(), CvType.CV_8UC1);
        Imgproc.cvtColor(sourceMat, destinationMat, Imgproc.COLOR_RGB2GRAY);

        Bitmap grayBitmap = new PhotoManager().getBitmap(mFile);

        // TODO: Segmentation
        Mat cannyMat = new Mat(grayBitmap.getWidth(), grayBitmap.getHeight(), CvType.CV_8UC1);
        Imgproc.Canny(destinationMat, cannyMat, 10, 100, 3, true);
        Bitmap cannyBitmap = Bitmap.createBitmap(grayBitmap);

        Utils.matToBitmap(cannyMat, cannyBitmap);

        return cannyBitmap;
    }

    private Bitmap processSegmentationBitmap(Bitmap grayBitmap) {

        Mat grayMat = new Mat(grayBitmap.getWidth(), grayBitmap.getHeight(), CvType.CV_8UC1);
        Mat cannyMat = new Mat(grayBitmap.getWidth(), grayBitmap.getHeight(), CvType.CV_8UC1);
        Imgproc.Canny(grayMat, cannyMat, 10, 100, 3, true);

        Bitmap cannyBitmap = Bitmap.createBitmap(grayBitmap);
        Utils.matToBitmap(cannyMat, cannyBitmap);
        return cannyBitmap;
    }

}
