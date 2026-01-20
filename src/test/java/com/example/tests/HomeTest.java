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
}
