package com.selenium.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.selenium.common.BasePage;

public class SearchOnGooglePage extends BasePage {

    public SearchOnGooglePage(WebDriver driver, Logger logger) {
        super(driver, logger);
    }

    public void search() {
        driver.get("http://www.google.com/");
        waitForPageToLoad("60");
        type(By.name("q"), "Cheese!");
        click(By.name("btnG"));
        Reporter.log("Searching for text 'Cheese!'");
        waitForPageToLoad("60");
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
