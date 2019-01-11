package com.github.frunoman.appium_tests;

import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import org.testng.annotations.Test;

public class SimpleAppiumTests extends BaseAppiumTest {

    @Test
    public void someTest(){
      clickOnElement();
    }

    @Step("Щелкнуть по кнопке 'Next'")
    public void clickOnElement(){
        driver.findElementById("com.whaleshark.retailmenot:id/next_button").click();
    }
}
