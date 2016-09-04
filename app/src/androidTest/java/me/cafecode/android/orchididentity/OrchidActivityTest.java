package me.cafecode.android.orchididentity;

import android.content.Intent;
import android.support.test.espresso.core.deps.guava.base.Charsets;
import android.support.test.espresso.core.deps.guava.io.Resources;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Thread.sleep;

public class OrchidActivityTest {

    @Rule
    public ActivityTestRule<OrchidDetailActivity> activityTestRule =
            new ActivityTestRule<>(OrchidDetailActivity.class, true, false);

    private MockWebServer mockServer;

    @Before
    public void setUp() throws IOException {
        mockServer = new MockWebServer();
        mockServer.start();
        Constants.HOST_URL = mockServer.url("/").toString();
    }

    @After
    public void tearDown() throws IOException {
        mockServer.shutdown();
    }

    @Test
    public void ui() throws Exception {

        // Arrange
        String response = Resources.toString(
                Resources.getResource("api_response.json"), Charsets.UTF_8);
        mockServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(response));

        activityTestRule.launchActivity(new Intent());

        sleep(10000L);

        // Act
        onView(withId(R.id.orchid_science_name_text)).check(
                matches(withText("Paphiopedilum concolor")));
    }

}
