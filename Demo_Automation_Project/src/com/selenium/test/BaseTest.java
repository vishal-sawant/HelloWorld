package com.selenium.test;

import static org.testng.Assert.fail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.selenium.common.Constants;
import com.selenium.common.Logs;
import com.selenium.common.Listener;

public class BaseTest {

    protected WebDriver driver;
    protected String baseUrl;
    protected String nodeUrl;
    protected StringBuffer verificationErrors = new StringBuffer();
    protected Logger logger;
    protected Properties cssProperties;
    protected Properties appProperties;
    private InputStream inputStream;

    public BaseTest() {

    }

    @BeforeClass(alwaysRun = true)
    @Parameters({ "browser" })
    public void setUp(@Optional("FF") String browser) {
        // Loading property files.
        try {
            LoadProperties();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Creating logger instance.
        logger = getLogger();
        logger.info("Loaded property files and created logger instance.");
        // Set Base URL
        baseUrl = getInputValue("url");
        // Creating driver instance.
        setupSeleniumWebDriver(browser);
        // Assigning Driver to required Utility classes
        Listener.driver = driver;
        logger.info("Created driver instance :- "
                + driver.getClass().getSimpleName());
    }

    /**
     * Sets up Web Driver and url to start with.
     * 
     * @param browser
     * @return
     */
    public WebDriver setupSeleniumWebDriver(String browser) {
        try {
            if (browser.equals("FF")) {
                logger.info("Setting up FireFox Driver.");
                driver = new FirefoxDriver();
            } else if (browser.equals("IE")) {
                logger.info("Setting up Internet Explorer Driver.");
                System.setProperty("webdriver.ie.driver",
                        "./resources/IEDriverServer.exe");
                driver = new InternetExplorerDriver();
            } else if (browser.equals("CH")) {
                logger.info("Setting up Chrome Driver.");
                System.setProperty("webdriver.chrome.driver",
                        "./resources/chromedriver.exe");
                driver = new ChromeDriver();
            } else if (browser.contains("REMOTE")) {
                nodeUrl = "http://172.30.141.23:5555/wd/hub";
                logger.info("Setting up REMOTE Driver.");
                DesiredCapabilities capabilities = DesiredCapabilities
                        .firefox();
                capabilities.setBrowserName("firefox");
                driver = new RemoteWebDriver(new URL(nodeUrl), capabilities);
            }
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        } catch (WebDriverException e) {
            logger.error("Oops, could not setup " + browser + " driver : "
                    + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            logger.error("Exception occured during driver setup : "
                    + e.getMessage());
            e.printStackTrace();
        }
        return driver;
    }

    /**
     * Stops the selenium server.
     */
    public void stopSeleniumWebDriver() {
        logger.info("Closing Driver instance.");
        try {
            driver.quit();
            logger.info("Test Suite Completed.");
            String verificationErrorString = verificationErrors.toString();
            if (!"".equals(verificationErrorString)) {
                fail(verificationErrorString);
            }
        } catch (Exception e) {
            logger.error("Oops, not able to close the driver : "
                    + e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Method reads and loads CSS properties
     * 
     * @throws IOException
     */
    private void LoadProperties() throws IOException {
        inputStream = new FileInputStream(Constants.INPUT_CONFIG_PATH);
        appProperties = new Properties();
        appProperties.load(inputStream);
        inputStream = new FileInputStream(Constants.CSS_CONFIG_PATH);
        cssProperties = new Properties();
        cssProperties.load(inputStream);

    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Logger getLogger() {
        Logs logs = new Logs();
        return logs.getLogger();
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    /**
     * Gets value of given key from CSS config file
     * 
     * @param cssKey
     * @return
     */
    public String getCssValue(String cssKey) {
        return cssProperties.getProperty(cssKey);
    }

    /**
     * Sets value of given key from CSS config file
     * 
     * @param cssConfig
     */
    public void setCssValue(String inputKey, String inputValue) {
        cssProperties.put(inputKey, inputValue);
    }

    /**
     * Gets value of given key from input config file
     * 
     * @param inputKey
     * @return
     */
    public String getInputValue(String inputKey) {
        return appProperties.getProperty(inputKey);
    }

    /**
     * Inserts key-value pair in input config file
     * 
     * @param inputKey
     * @param inputValue
     */
    public void setInputValue(String inputKey, String inputValue) {
        appProperties.put(inputKey, inputValue);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        stopSeleniumWebDriver();
    }

}
