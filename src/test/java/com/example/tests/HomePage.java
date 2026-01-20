package com.example.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://www.jiotv.com/");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // Try to click an "Allow" button if a permission popup appears
        try {
           driver.switchTo().alert().accept();
        } catch (Exception ignored) {
            System.out.println("Popup Called");
        }
    }

    // Initiate a search after calling open()
    public void tabclick() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            By sportsSelector = By.cssSelector("a.header_item-links_link[aria-label='Sports'][href='/sports']");
            WebElement sports = wait.until(ExpectedConditions.elementToBeClickable(sportsSelector));
            sports.click();
            Thread.sleep(4000); // Wait for 4 seconds after clicking
        } catch (Exception e) {
            System.out.println("Failed to click Sports tab: " + e.getMessage());
        }
    }
}
