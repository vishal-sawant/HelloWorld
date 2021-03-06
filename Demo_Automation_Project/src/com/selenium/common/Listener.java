package com.selenium.common;

import java.io.File;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IExecutionListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

/**
 * This is an implementation of TestNG listener and will be called on every test
 * case failure in order to take a screen shot.
 * 
 * @author Sameer Patil
 * 
 */

public class Listener implements ITestListener, IExecutionListener {

    public static WebDriver driver;
    private Logger logger;
    private long startTime;

    public Listener() {
        Logs logs = new Logs();
        logger = logs.getLogger();
    }

    public void onStart(ITestContext arg0) {
        Reporter.log("About to begin executing Test " + arg0.getName(), true);
    }

    public void onFinish(ITestContext arg0) {
        Reporter.log("Completed executing test " + arg0.getName(), true);
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
    }

    public void onTestFailure(ITestResult arg0) {
        String seperator = System.getProperty("file.separator");
        try {
            String fileName = String.format("image-%s.jpg", Calendar
                    .getInstance().getTimeInMillis());

            StringBuilder filePath = new StringBuilder();
            filePath.append(System.getProperty("user.dir"));
            filePath.append(seperator);
            filePath.append("test-output");
            filePath.append(seperator);
            filePath.append("ScreenShots");
            filePath.append(seperator);
            filePath.append(fileName);
            File screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(filePath.toString()));
            logger.info("Screen Shots file :  " + filePath);
            Reporter.log("Screen Shots file :  " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onTestSkipped(ITestResult arg0) {
        // TODO Auto-generated method stub

    }

    public void onTestStart(ITestResult arg0) {
        logger.info("Executing Test Case :- "
                + arg0.getMethod().getMethodName());
        Reporter.log("Executing Test Case :- "
                + arg0.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult arg0) {
        // TODO Auto-generated method stub

    }

    public void onExecutionStart() {
        startTime = System.currentTimeMillis();
        Reporter.log("TestNG is going to start the execution", true);
    }

    public void onExecutionFinish() {
        Reporter.log(
                "TestNG has finished execution. It took around "
                        + String.format(
                                "%.3f",
                                ((System.currentTimeMillis() - startTime) * 0.001))
                        + " seconds.", true);
    }

}
