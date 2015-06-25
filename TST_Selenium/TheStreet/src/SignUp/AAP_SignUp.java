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


public class AAP_SignUp 
{
	@Test
	public void aapSignUp(ITestContext context) throws Exception 
	{
		WebDriver driver = (WebDriver)context.getAttribute("TSTDriver");
		WebElement element = null;
				  
	    //Get object of WebElementMapping class
	    WebElementMapping elementmap = new WebElementMapping(CommonConstant.Config_File_path);
	    
	    ReadExcel.setExcelFile(CommonConstant.Test_Data_Path, CommonConstant.sheet);
	    String email_id = ReadExcel.getCellData(2, 0);
		
	    element = driver.findElement(elementmap.getLocator("subscribe_link"));
		element.click();
		
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		
		element = driver.findElement(elementmap.getLocator("aap_link"));
		element.click();
		
		element = driver.findElement(elementmap.getLocator("portfolio_button"));
		element.click();
		
		element = driver.findElement(elementmap.getLocator("email_add"));
		element.click();
		element.sendKeys(email_id);
		
		element = driver.findElement(elementmap.getLocator("submit_button"));
		element.click();
		
		CreateAccount ca;
		ca= new CreateAccount();
		ca.newAccount(driver);
		
		element=driver.findElement(elementmap.getLocator("telephone_number"));
		element.sendKeys("1234567890");
		
		element=driver.findElement(elementmap.getLocator("submit_button")); 
		element.click();
		
		//Explicit wait for Thank you message
	    WebDriverWait wait = new WebDriverWait(driver, 10);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(elementmap.getLocator("aap_thkyou_text")));
		
		driver.navigate().to(CommonConstant.streeturl);
	}
	
}
