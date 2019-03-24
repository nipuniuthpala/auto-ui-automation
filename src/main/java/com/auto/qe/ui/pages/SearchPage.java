package com.auto.qe.ui.pages;

import com.auto.qe.ui.model.CarListing;
import com.auto.qe.ui.model.CarListingView;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Use of CSS Selectors with data-qa-selector
 * https://www.utest.com/articles/data-qa-attribute-a-better-way-to-select-elements-for-ui-test-automation
 */
public class SearchPage {

    private WebDriver driver;

    /**
     * Web Elements
     */
    private WebElement yearFilter;
    private WebElement registrationDate2015Option;
    private WebElement sortFilterHighestPricingOrder;
    private WebElement pageNext;

    private int totalHits;

    // We ensure that page size is 72 always
    private int paginatedSize = 72;

    public SearchPage(WebDriver webDriver) {
        driver = webDriver;
        yearFilter = driver.findElement(By.cssSelector("[data-qa-selector='filter-year']"));
        registrationDate2015Option = driver.findElement(By.cssSelector("[data-qa-selector='filter-year'] [data-qa-selector-value='2015']"));
        sortFilterHighestPricingOrder = driver.findElement(By.cssSelector(
                "[data-qa-selector='sort-select'] [data-qa-selector='select'] [data-qa-selector-value='offerPrice.amountMinorUnits.desc']"));

        Select page72Option = new Select(driver.findElement(By.cssSelector("[data-qa-selector='page-size'] [data-qa-selector='select']")));
        page72Option.selectByVisibleText("72");
        pageNext = driver.findElement(By.xpath("//*[@id=\"app\"]/div/main/div[4]/div/div[2]/div/div[2]/div/div[2]/div[2]/div/ul/li[10]/a"));
    }

    public void setRegistrationYearTo2015() {
        yearFilter.click();
        registrationDate2015Option.click();

    }

    public void orderListByHighestPricingDesc() {
        sortFilterHighestPricingOrder.click();

    }

    public void calculateTotalHits() {
        String totalHitsDisplay = driver.findElement(By.cssSelector("[data-qa-selector='results-amount']")).getText();
        System.out.println("==== Total Hits Display:" + totalHitsDisplay);
        totalHits = Integer.parseInt(totalHitsDisplay.split("\\s")[0]);
        System.out.println("==== Total Hits: " + totalHits);
    }

    public void clickNextPage() {

        pageNext.click();

        // Just a static sleep for now.
        // Need to use proper wait options from selenium in real cases.
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isNextPageEnabled() {
        return pageNext.isEnabled();
    }

    public void validateYearAndPrice() {

        boolean hasNextPage = true;
        CarListing lastOfPreviousPage = null;

        int totalLeft = totalHits;
        int pageCounter = 1;
        int pageSize = paginatedSize;

        while (hasNextPage) {
            if (paginatedSize > totalLeft) {
                pageSize = totalLeft;
                hasNextPage = false;
            } else {
                totalLeft = totalLeft - paginatedSize;
            }

            CarListingView carListingView = getCarListing(pageSize, pageCounter);
            carListingView.checkPriceSorting();
            if (lastOfPreviousPage != null) {
                carListingView.checkStartingPriceOrder(lastOfPreviousPage);
            }
            lastOfPreviousPage = carListingView.getLastListing();

            if (hasNextPage) {
                clickNextPage();
            }
            pageCounter++;
        }

        // Make sure that in last page, next page click is disabled.
        assert isNextPageEnabled() : "Next page has to be disabled in last page";
    }

    private CarListingView getCarListing(int pageSize, int pageCounter) {

        List<CarListing> carListings = new ArrayList<CarListing>();

        System.out.println("=============================");
        System.out.println("=== PAGE NUMBER:" + pageCounter);
        System.out.println("=== PAGE SIZE:" + pageSize);
        System.out.println("-----------------------------");

        for (int i = 1; i <= pageSize; i++) {

            String byYearCssSelector = String.format("[data-qa-selector='ad-items'] > div:nth-of-type(%s) [data-qa-selector='spec-list'] > li:nth-of-type(1)", i);
            By byYear = By.cssSelector(byYearCssSelector);

            String byPriceCssSelector = String.format("[data-qa-selector='ad-items']  > div:nth-of-type(%s) [data-qa-selector='price']", i);
            By byPrice = By.cssSelector(byPriceCssSelector);

            System.out.println("=== Element: " + i);

            String yearAndMonth = driver.findElement(byYear).getText();
            String year = yearAndMonth.split("/")[1];
            System.out.println("=== Year on Display:" + year);

            String price = driver.findElement(byPrice).getText();

            System.out.println("=== Price on Display:" + price);

            CarListing carListing = new CarListing(Integer.parseInt(year), Double.valueOf(price.split("\\s")[0]));
            carListing.validateYear2015();

            carListings.add(carListing);
        }

        System.out.println("=============================");
        return new CarListingView(carListings);
    }

}




