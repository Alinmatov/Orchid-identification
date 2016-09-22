package me.cafecode.android.orchididentity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;

import me.cafecode.android.orchididentity.photo.PhotoManager;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static java.lang.Thread.sleep;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Natthawut Hemathulin on 9/11/2016 AD.
 * Email: natthawut1991@gmail.com
 */

public class Camera2ActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class, true, false);

    File photoFile;

    @Before
    public void setUp() {
        activityTestRule.launchActivity(new Intent());
    }

    @After
    public void tearDown() throws Exception {
//        boolean isDeleted = photoFile.delete();
//        assertTrue(isDeleted);
    }

    @Test
    public void canTakePhoto() throws Exception {

        // Act
        onView(withId(R.id.capture_button)).perform(click());

        photoFile = new File(activityTestRule.getActivity().getExternalFilesDir(null), Constants.PHOTO_NAME);
        final Bitmap photoBitmap = PhotoManager.getBitmap(photoFile);

        sleep(5000);

        // Assert

        // Is square photo
        assertNotNull(photoBitmap);
        assertEquals(photoBitmap.getWidth(), photoBitmap.getHeight());


        boolean isDeleted = photoFile.delete();
        assertTrue(isDeleted);
    }
}
