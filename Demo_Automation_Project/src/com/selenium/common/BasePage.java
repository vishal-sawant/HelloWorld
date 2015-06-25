package com.selenium.common;

import static org.testng.Assert.fail;

import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.server.browserlaunchers.Sleeper;

public class BasePage {
    protected WebDriver driver;
    protected Logger logger;

    public BasePage(WebDriver driver, Logger logger) {
        this.driver = driver;
        this.logger = logger;
    }

    /**
     * Clicks element found using By object.
     * 
     * @param by
     */
    public void click(By by) {
        driver.findElement(by).click();
    }

    /**
     * Clicks element found using By object.
     * 
     * @param by
     */
    public void findElement(By by) {
        driver.findElement(by);
    }

    /**
     * Clears the text for provided element
     * 
     * @param by
     */
    public void clear(By by) {
        driver.findElement(by).clear();
    }

    /**
     * Send text keys to the element found using By object.
     * 
     * @param by
     * @param inputText
     */
    public void type(By by, String inputText) {
        driver.findElement(by).sendKeys(inputText);
    }

    /**
     * Gets text from the element found using By object.
     * 
     * @param by
     */
    public String getText(By by) {
        return driver.findElement(by).getText();
    }

    /**
     * Gets count of element in the page using By object.
     * 
     * @param by
     */
    public long getcount(By by) {
        return driver.findElements(by).size();
    }

    /**
     * Checks if the elment is in the DOM and displayed.
     * 
     * @param by
     *            - selector to find the element
     * @return true or false
     */
    public boolean isElementPresentAndDisplayed(By by) {
        try {
            return driver.findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Returns the first WebElement using the given method. It shortens
     * "driver.findElement(By)".
     * 
     * @param by
     *            element locater.
     * @return the first WebElement
     */
    public WebElement getWebElement(By by) {
        return driver.findElement(by);
    }

    /**
     * Checks if check-box on the page is selected or not
     * 
     * @return true or false
     */
    public boolean isSelected(By by) {
        return (driver.findElement(by).isSelected());
    }

    /**
     * Check if page is loaded by comparing the expected page-title with an
     * actual page-title.
     * 
     * @return
     */
    public boolean isPageLoaded(String pageTitle) {
        return (driver.getTitle().contains(pageTitle));
    }

    /**
     * Method checks the presence of text on the page.
     * 
     * @param text
     * @return
     */
    public boolean isTextPresent(String text) {
        return driver.getPageSource().contains(text);
    }

    /**
     * Method checks the presence of element in the page.
     * 
     * @param by
     * @return
     */
    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Method checks the presence of element in the DOM.
     * 
     * @param cssSelector
     *            element locater
     * @return WebElement
     */
    public boolean isElementPresent(String cssSelector) {
        try {
            driver.findElement(By.cssSelector(cssSelector));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Method to check if required Text is present on the page.
     * 
     * @param text
     *            : Text to be searched on the page
     * @throws InterruptedException
     */
    public void checkForTextPresent(String text) throws InterruptedException {
        int time = 60;
        for (int second = 0;; second++) {
            if (second >= time) {
                fail("Timeout, Not able to find text: " + text + " on page : "
                        + driver.getTitle());
            }
            try {
                if (isTextPresent(text)) {
                    break;
                }
            } catch (Exception e) {
                logger.info("Required text was not found due to error: "
                        + e.getMessage());
            }
            Sleeper.sleepTight(500);
        }
    }

    /**
     * Method waits for page to get loaded.
     * 
     * @param timeOutInSeconds
     */
    public void waitForPageToLoad(String timeOutInSeconds) {
        String windowTitle = driver.getTitle();
        int time = Integer.parseInt(timeOutInSeconds);
        int pageLength = 0;
        for (int second = 0;; second++) {
            if (second >= time) {
                fail("Timeout... Page load could not complete in "
                        + timeOutInSeconds + " seconds");
            }
            if (pageLength == driver.getPageSource().length()
                    && windowTitle != driver.getTitle()) {
                break;
            }
            Sleeper.sleepTight(500);
            pageLength = driver.getPageSource().length();
        }
    }

    public void waitForElementPresent(By element) {
        int time = 60;
        for (int second = 0;; second++) {
            if (second >= time) {
                fail("Timeout, Not able to find element: " + element
                        + " on page : " + driver.getTitle());
            }
            try {
                if (isElementPresent(element)) {
                    break;
                }
            } catch (Exception e) {
                logger.info("Required element was not found due to error: "
                        + e.getMessage());
            }
            Sleeper.sleepTight(500);
        }
    }

    public void waitForTextPresent(String text) {
        int time = 60;
        for (int second = 0;; second++) {
            if (second >= time) {
                fail("Timeout, Not able to find text: " + text + " on page : "
                        + driver.getTitle());
            }
            try {
                if (isTextPresent(text)) {
                    break;
                }
            } catch (Exception e) {
                logger.info("Required text was not found due to error: "
                        + e.getMessage());
            }
            Sleeper.sleepTight(500);
        }
    }

}
