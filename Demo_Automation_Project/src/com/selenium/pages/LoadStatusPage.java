package com.selenium.pages;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.selenium.common.BasePage;

public class LoadStatusPage extends BasePage {

    public LoadStatusPage(WebDriver driver, Logger logger) {
        super(driver, logger);
    }

    public String loadPageStatus() {
        driver.get("http://www.google.com");
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        return executor.executeScript("return document.readyState").toString();
    }

}
