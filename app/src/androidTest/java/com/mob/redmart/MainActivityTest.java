package com.mob.redmart;


import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private UiDevice mDevice;

    @Before
    public void setUp() {
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        assertThat(mDevice, notNullValue());

        // Start from the home screen
        mDevice.pressHome();

    }


    @Test
    public void launchApp() throws UiObjectNotFoundException, InterruptedException {

        UiObject mainWindow = mDevice.findObject(new UiSelector().packageName("com.mob.redmart").description("RedMartTask"));

        mainWindow.waitForExists(2000);

        mainWindow.clickAndWaitForNewWindow();
        assertThat(mainWindow, notNullValue());

        UiScrollable mRecyclerView = new UiScrollable(new UiSelector().className("android.support.v7.widget.RecyclerView"));

        for (int i = 0; i < mRecyclerView.getChildCount(); i++) {
            UiObject child = mRecyclerView.getChild(new UiSelector().className("android.widget.LinearLayout").index(i));

            child.waitForExists(1000);
            Thread.sleep(5000);
            mDevice.pressBack();
        }


        mRecyclerView.scrollToEnd(50);
        Thread.sleep(200);
    }

    @After
    public void tearDown() {
        mDevice = null;
    }
}
