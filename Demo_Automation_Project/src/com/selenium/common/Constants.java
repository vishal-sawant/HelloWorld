package com.selenium.common;

/**
 * Class which contains global constants.
 * 
 * @author Sameer Patil
 */
public class Constants {
  public static final String INPUT_CONFIG_PATH = System.getProperty("user.dir")
      + System.getProperty("file.separator") + "config/input.properties";
  public static final String CSS_CONFIG_PATH = System.getProperty("user.dir")
      + System.getProperty("file.separator") + "config/css.properties";
  public static final String LOG_FILEPATH = System.getProperty("user.dir")
      + System.getProperty("file.separator") + "logs";
  public static final String LOG4J_FILEPATH = System.getProperty("user.dir")
      + System.getProperty("file.separator") + "config/log4j.properties";
  public static final String TESTNG_FILE_PATH = System.getProperty("user.dir")
  + System.getProperty("file.separator") + "testNG.xml";
}
