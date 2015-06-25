package com.selenium.common;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.selenium.test.BaseTest;

/**
 * Class which contains utility methods.
 * 
 * @author Sameer Patil
 */

public class SystemUtil extends BaseTest {

    public String OsName;
    public String BrowserVersion;

    private static enum Os {
        WINDOWS("Windows"), MAC("Mac"), LINUX("Linux");
        private String os;

        private String getOs() {
            return os;
        }

        Os(String os) {
            this.os = os;
        }
    }

    /**
     * Method to detect the OS and its version
     */
    public void detectOSVersion() {
        String os = null;
        if (System.getProperty("os.name").contains(Os.WINDOWS.getOs())) {
            os = "WINDOWS";
        } else if (System.getProperty("os.name").contains(Os.MAC.getOs())) {
            os = "MAC";
        } else if (System.getProperty("os.name").contains(Os.LINUX.getOs())) {
            os = "Linux";
        } else {
            Assert.fail("Could not find OS Version for your operating system !");
        }
        String OsVersion = System.getProperty("os.version");
        if (os.equals("WINDOWS")) {
            if (OsVersion.equals("3.10")) {
                OsVersion = "NT 3.1";
            } else if (OsVersion.equals("3.11")) {
                OsVersion = "for Workgroups 3.11";
            } else if (OsVersion.equals("3.5")) {
                OsVersion = "NT Workstation 3.5";
            } else if (OsVersion.equals("4.1")) {
                OsVersion = "98";
            } else if (OsVersion.equals("5.0")) {
                OsVersion = "2000 Professional";
            } else if (OsVersion.equals("5.1")) {
                OsVersion = "XP";
            } else if (OsVersion.equals("5.2")) {
                OsVersion = "XP Professional x64 Edition";
            } else if (OsVersion.equals("6.0")) {
                OsVersion = "Vista";
            } else if (OsVersion.equals("6.1")) {
                OsVersion = "7";
            }
        }
        OsName = os + " " + OsVersion;
        logger.info("Operating System is : " + OsName);
    }

    public void detectBrowserVersion() {
        Capabilities capabilities = ((RemoteWebDriver) driver)
                .getCapabilities();
        String browserName = capabilities.getBrowserName();
        String browserVersion = capabilities.getVersion().toString();
        BrowserVersion = browserName + " " + browserVersion;
        logger.info("Browser is : " + BrowserVersion);
    }
}
