package com.example.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        // By default block notifications and geolocation prompts so the browser
        // won't show the native "Allow / Block" permission dialog during tests.
        ChromeOptions options = createChromeOptions(false, false);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Create ChromeOptions with preferences to auto-allow or auto-block common
     * permission prompts (notifications, geolocation). Pass true to allow, false
     * to block. This removes the native Allow/Block prompt.
     */
    protected ChromeOptions createChromeOptions(boolean allowNotifications, boolean allowGeolocation) {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();

        // 1 = allow, 2 = block, 0 = default
        prefs.put("profile.default_content_setting_values.notifications", allowNotifications ? 1 : 2);
        prefs.put("profile.default_content_setting_values.geolocation", allowGeolocation ? 1 : 2);

        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--remote-allow-origins=*");
        return options;
    }
}
