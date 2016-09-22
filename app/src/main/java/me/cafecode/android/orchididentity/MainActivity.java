package me.cafecode.android.orchididentity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

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
            getFragmentManager().beginTransaction()
                    .replace(R.id.main_content, CameraFragment.newInstance())
                    .commit();
        }
    }
}
