package com.example.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class HomeTest extends BaseTest {

    @Test(priority = 1)
    public void openHomePage() {
        HomePage page = new HomePage(driver);
        page.open();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By sportsLink = By.xpath("//a[normalize-space() = 'Sports' or contains(@href,'/sports')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(sportsLink));
    }

    @Test(priority = 2, dependsOnMethods = {"openHomePage"})
    public void clickSportsTab() {
        HomePage page = new HomePage(driver);
        page.tabclick();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/sports"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/sports"), "Expected to be on sports page after clicking tab, but was: " + currentUrl);
    }
    @Test(priority = 3, dependsOnMethods = {"clickSportsTab"})
    public void searchShow() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By searchInput = By.id("searchInput");
        // Wait for search input to be clickable and click it
        wait.until(ExpectedConditions.elementToBeClickable(searchInput)).click();



        // Enter search text and press Enter using Actions class
        org.openqa.selenium.WebElement inputElem = driver.findElement(searchInput);
        inputElem.sendKeys("Taarak ka mehta Ooltah Chashmah");
        new org.openqa.selenium.interactions.Actions(driver)
            .moveToElement(inputElem)
            .sendKeys(org.openqa.selenium.Keys.ENTER)
            .perform();
        try {
            Thread.sleep(5000); // Wait for 5 seconds after search
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Validate search result is working (wait for some result element to appear)
        // This is a placeholder: update the selector as per actual result DOM
        By resultSelector = By.xpath("//*[contains(text(),'Taarak') or contains(text(),'Ooltah')]");
        boolean found = wait.until(ExpectedConditions.or(
            ExpectedConditions.visibilityOfElementLocated(resultSelector),
            ExpectedConditions.presenceOfElementLocated(resultSelector)
        )) != null;
        Assert.assertTrue(found, "Search result for 'Taarak ka mehta Ooltah Chashmah' should be visible");
    }
}
