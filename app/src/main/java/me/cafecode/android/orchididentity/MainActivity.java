package me.cafecode.android.orchididentity;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import me.cafecode.android.orchididentity.camera.Camera2Fragment;
import me.cafecode.android.orchididentity.camera.CameraFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (savedInstanceState == null) {
            // Check min sdk < 21?
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                // Use Camera API
                getFragmentManager().beginTransaction()
                        .replace(R.id.main_content, CameraFragment.newInstance("", ""))
                        .commit();
            } else {
                // Min sdk 21
                // Use Camera2 API
                getFragmentManager().beginTransaction()
                        .replace(R.id.main_content, Camera2Fragment.newInstance())
                        .commit();
            }
        }
    }
}
