package me.cafecode.android.orchididentity.photo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import me.cafecode.android.orchididentity.Constants;

public class PhotoManager {

    private static final String TAG = PhotoManager.class.getSimpleName();

    public PhotoManager() {
    }

    public static Bitmap getBitmap(File file) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        return BitmapFactory.decodeFile(file.getPath(), options);
    }

    public static File getFile(Context context) {
        return new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                Constants.PHOTO_NAME);
    }

    public static void saveDataToFile(Context context, byte[] data) {

        Bitmap bmp;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inMutable = true;
        bmp = BitmapFactory.decodeByteArray(data, 0, data.length, options);
        final Bitmap squareBitmap = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getWidth());

        File file = getFile(context);
        OutputStream outStream;
        if (file.exists()) {
            if (file.delete()) {
                // Delete file completed
                Log.i(TAG, "Deleted");
            }
        }
        try {
            // make a new bitmap from your file
            outStream = new FileOutputStream(file);
            squareBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveBitmapToFile(Context context, Bitmap bitmap) {
        try {
            File mFile = getFile(context);
            FileOutputStream fileOutput = new FileOutputStream(mFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutput);
            fileOutput.close();
            Log.d(TAG, mFile.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
