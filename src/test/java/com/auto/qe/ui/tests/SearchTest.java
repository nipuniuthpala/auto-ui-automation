package com.auto.qe.ui.tests;


import com.auto.qe.ui.common.Constants;
import com.auto.qe.ui.pages.SearchPage;
import com.auto.qe.ui.utils.DriverSetUpUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


public class SearchTest {

    @Test
    public void testSearchFunction() throws InterruptedException {

        //===================================
        // Initialize Web Driver.
        if (Constants.RUN_LOCALLY) {
            DriverSetUpUtil.setToRunLocally();
        }

        WebDriver driver = new ChromeDriver();
        // Load Page and Maximize.
        driver.get(Constants.APP_URL);
        driver.manage().window().maximize();
        //===================================

        SearchPage searchPage = new SearchPage(driver);
        searchPage.setRegistrationYearTo2015();
        searchPage.orderListByHighestPricingDesc();

        Thread.sleep(3000);
        searchPage.calculateTotalHits();
        searchPage.validateYearAndPrice();

        System.out.println("COMPLETED SUCCESSFULLY");
        // For Testing Purposes Only.
        Thread.sleep(5000);
        driver.close();
    }
}