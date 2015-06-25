package com.selenium.common;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class GetStatus {
    public static String totalCasesCount;
    public static String passedCasesCount;
    public static String failedCasesCount;
    public static String skippedCasesCount;

     private static Document parseFromFile(String filename) {
         try {
             File file = new File(filename);
             DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
             DocumentBuilder db = dbf.newDocumentBuilder();
             Document doc = db.parse(file);
             return doc;
         } catch (Throwable t) {
             System.out.println("Error in parsing");
         }
        return null;
     }

     public static void getResultStatus() throws Exception {
         Document doc = parseFromFile(System.getProperty("user.dir")
                 + System.getProperty("file.separator") + "test-output"
                 + System.getProperty("file.separator")
                 + "testng-results.xml");
         NodeList nodes = doc.getElementsByTagName("testng-results");
         totalCasesCount = nodes.item(0).getAttributes().getNamedItem("total").getNodeValue();
         passedCasesCount = nodes.item(0).getAttributes().getNamedItem("passed").getNodeValue();
         failedCasesCount = nodes.item(0).getAttributes().getNamedItem("failed").getNodeValue();
         skippedCasesCount = nodes.item(0).getAttributes().getNamedItem("skipped").getNodeValue();
      }
}
