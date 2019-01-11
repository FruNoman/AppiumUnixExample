package com.github.frunoman.tests;

import com.github.frunoman.pages.MainPage;

import io.appium.java_client.AppiumDriver;
import org.testng.annotations.Test;


public class BrowserTest extends BaseBrowserTest {

    @Test
    public void testSome(){
        MainPage mainPage = new MainPage((AppiumDriver) driver);
        mainPage.clickOnPersonalArea();
    }

}
