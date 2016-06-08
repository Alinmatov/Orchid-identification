package me.cafecode.android.orchididentity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;

public class PhotoManager {

    public PhotoManager() {
    }

    public Bitmap getBitmap(File file) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        return BitmapFactory.decodeFile(file.getPath(), options);
    }

}
