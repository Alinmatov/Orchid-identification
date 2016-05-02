package me.cafecode.android.orchididentity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.File;

public class OrchidActivity extends AppCompatActivity {

    private File mFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orchid);

        ImageView photoImage = (ImageView) findViewById(R.id.image);

        mFile = new File(getExternalFilesDir(null), Constants.PHOTO_NAME);

        Bitmap bitmap = new PhotoManager().getBitmap(mFile);
        if (photoImage != null) {
            photoImage.setImageBitmap(bitmap);
        }

    }
}
