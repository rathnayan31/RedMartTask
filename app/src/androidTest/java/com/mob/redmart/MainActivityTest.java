package com.mob.redmart;


import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;

import com.mob.redmart.view.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {


    @Rule
    public ActivityTestRule<MainActivity> mActivityRule
            = new ActivityTestRule<MainActivity>(MainActivity.class);

    private UiDevice mDevice;


    @Before
    public void setUp() throws Exception {
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
//        assertThat(mDevice, notNullValue());
        // Start from the home screen
        mDevice.pressHome();

    }


    @After
    public void tearDown() throws Exception {

    }

    private class ActivityTestRule<T> {
    }
}
