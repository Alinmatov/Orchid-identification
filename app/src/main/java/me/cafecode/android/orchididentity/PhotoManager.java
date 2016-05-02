package me.cafecode.android.orchididentity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;

/**
 * Created by Natthawut Hemathulin on 5/2/16 AD.
 * Email: natthawut1991@gmail.com
 */
public class PhotoManager {

    public PhotoManager() {
    }

    public Bitmap getBitmap(File file) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        return BitmapFactory.decodeFile(file.getPath(), options);
    }

}
