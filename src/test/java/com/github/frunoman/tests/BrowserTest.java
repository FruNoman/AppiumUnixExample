package com.github.frunoman.tests;

import com.github.frunoman.pages.MainPage;

import com.github.frunoman.utils.Categories;
import org.testng.annotations.Test;


public class BrowserTest extends BaseBrowserTest {

    @Test
    public void testSome(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnCategory(Categories.getRandomCategory());
        System.out.println(driver.getTitle());
    }

}
