package com.selenium.test;

import org.testng.annotations.Test;

import com.selenium.pages.LoadStatusPage;

public class PageLoadTest extends BaseTest {

    @Test(groups = { "smokeTest" })
    public void pageLoadStatusTest() {
        LoadStatusPage pageLoad = new LoadStatusPage(driver, logger);
        String status = pageLoad.loadPageStatus();
        logger.info("Page Load Status: " + status);
    }

}
