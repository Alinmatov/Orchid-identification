package me.cafecode.android.orchididentity;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static java.lang.Thread.sleep;

public class OrchidActivityTest {

    @Rule
    public ActivityTestRule<OrchidActivity> activityTestRule =
            new ActivityTestRule<>(OrchidActivity.class, false, false);

    @Test
    public void ui() throws Exception {

        activityTestRule.launchActivity(null);

        sleep(10000L);
    }

}
