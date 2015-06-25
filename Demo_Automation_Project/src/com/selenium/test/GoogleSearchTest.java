package com.selenium.test;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.selenium.pages.SearchOnGooglePage;

public class GoogleSearchTest extends BaseTest {

    @Test(groups = { "smokeTest" })
    public void searchOnGoogleTest() {
        SearchOnGooglePage googleSearch = new SearchOnGooglePage(driver, logger);
        Reporter.log("On Google Search Page.");
        googleSearch.search();
        String currentPageTitle = googleSearch.getPageTitle();
        logger.info("Page title is: " + currentPageTitle);
        Reporter.log("Page title is: " + currentPageTitle);
        Assert.assertTrue(currentPageTitle.equals("Cheese! - Google Search"));
    }

}
