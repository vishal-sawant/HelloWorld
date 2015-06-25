package com.selenium.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.TestNG;

public class Launch {

    public static void main(String[] args) throws Exception {
        Logs logs = new Logs();
        Logger logger = logs.getLogger();
        try {
            logger.info("Starting Test Suite...");
            TestNG testng = new TestNG();
            List<String> suites = new ArrayList<String>();
            suites.add(Constants.TESTNG_FILE_PATH);
            testng.setTestSuites(suites);
            testng.run();
        } catch (Exception e) {
            logger.info("Error in main : " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Mailer.sendStatusEmail();
        }
    }
}
