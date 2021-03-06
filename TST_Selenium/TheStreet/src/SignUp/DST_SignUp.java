package SignUp;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import commonClasses.CommonConstant;
import commonClasses.CreateAccount;
import commonClasses.ReadExcel;
import commonClasses.WebElementMapping;


public class DST_SignUp 
{
	@Test
	public void dstSignUp(ITestContext context) throws Exception 
	{
		WebDriver dstdrvr = (WebDriver)context.getAttribute("TSTDriver");
		WebElement element = null;
			    
		 //Get object of WebElementMapping class
	    WebElementMapping elementmap = new WebElementMapping(CommonConstant.Config_File_path);
	    
	    ReadExcel.setExcelFile(CommonConstant.Test_Data_Path, CommonConstant.sheet);
	    String email_id = ReadExcel.getCellData(4, 0);
		
	    element = dstdrvr.findElement(elementmap.getLocator("subscribe_link"));
		element.click();
				
		dstdrvr.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		
		element = dstdrvr.findElement(elementmap.getLocator("dst_link"));
		element.click();
		
		element = dstdrvr.findElement(elementmap.getLocator("free-trial_button"));
		element.click();
		
		element=dstdrvr.findElement(elementmap.getLocator("email_add")); 
		element.click(); 
		element.sendKeys(email_id);
		 
		element=dstdrvr.findElement(elementmap.getLocator("fname")); 
		element.click();
		element.sendKeys("testfn");
		 
		element=dstdrvr.findElement(elementmap.getLocator("lname")); 
		element.click();
		element.sendKeys("testln");
		
		element=dstdrvr.findElement(elementmap.getLocator("submit_button")); 
		element.click();
				
		CreateAccount ca;
		ca= new CreateAccount();
		ca.newAccount(dstdrvr);
		
		element=dstdrvr.findElement(elementmap.getLocator("submit_button")); 
		element.click();
		
		//Explicit wait for Thank you message
	    WebDriverWait wait = new WebDriverWait(dstdrvr, 10);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(elementmap.getLocator("dst_thkyou_text")));
		
		dstdrvr.navigate().to(CommonConstant.streeturl);
	}
}
