package me.cafecode.android.orchididentity;


import android.content.res.AssetManager;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import me.cafecode.android.orchididentity.api.ApiManager;
import me.cafecode.android.orchididentity.api.ApiResponse;
import me.cafecode.android.orchididentity.api.AppPreferences;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static junit.framework.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class ApiManagerTest {

    private File photoFile;

    @Before
    public void setUp() throws Exception {
        AssetManager assetManager = getInstrumentation().getTargetContext().getAssets();
        InputStream stream = assetManager.open("orchid_photo.jpg");
        photoFile = createFileFromInputStream(stream);

    }

    @After
    public void tearDown() {

    }

    private File createFileFromInputStream(InputStream inputStream) throws Exception {

        try{
            File f = new File("assets/photo.jpg");
            OutputStream outputStream = new FileOutputStream(f);
            byte buffer[] = new byte[1024];
            int length = 0;

            while((length=inputStream.read(buffer)) > 0) {
                outputStream.write(buffer,0,length);
            }

            outputStream.close();
            inputStream.close();

            return f;
        }catch (IOException e) {
            //Logging exception
        }

        return null;
    }

    @Test
    public void identifyOrchid_shouldSuccess() throws Exception {

        ApiManager.identifyOrchid(photoFile, AppPreferences.TYPE_EDGE).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                fail("Unexpected this error.");
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                t.printStackTrace();
                fail("Unexpected this error.");
            }
        });
    }

}
