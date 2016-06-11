package me.cafecode.android.orchididentity;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.URL;

import CalEn.ImageEnergy;

import static android.support.test.espresso.core.deps.guava.io.Resources.getResource;
import static junit.framework.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class ImageEnergyTest {

    @Test
    public void calEn_CanCalculatePhoto() throws Exception {

        // Arrange
        URL photoUrl = getResource("test_orchid_photo.jpg");

        // Act
        ImageEnergy imageEnergy = new ImageEnergy();
        Object actualResult = imageEnergy.CalEn(1, photoUrl.getPath());

        // Assert
        assertNotNull(actualResult);
    }

}
