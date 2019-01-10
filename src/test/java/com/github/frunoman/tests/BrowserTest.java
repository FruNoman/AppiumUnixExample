package com.github.frunoman.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class BrowserTest extends BaseBrowserTest {

    @Test
    public void testSome(){
        driver.findElement(By.name("q")).sendKeys("papa");
    }

}
