package commonClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CreateAccount 
{
	public void newAccount(WebDriver accountdriver) throws Exception 
	{
		WebElement element=null;
		WebElementMapping elementmap = new WebElementMapping(CommonConstant.Config_File_path);
		
		element=accountdriver.findElement(elementmap.getLocator("pass"));
		element.sendKeys("thestreet");
		 
		element=accountdriver.findElement(elementmap.getLocator("confirmpass"));
		element.sendKeys("thestreet");
		
		element=accountdriver.findElement(elementmap.getLocator("ccreditcardnumber"));
		element.sendKeys("4111111111111111");
		
		element=accountdriver.findElement(elementmap.getLocator("ccreditcardcvv"));
		element.sendKeys("123");
		  
		element=accountdriver.findElement(elementmap.getLocator("nameoncreditcard"));
		element.sendKeys("testfn testln");
		  
		element=accountdriver.findElement(elementmap.getLocator("address1"));
		element.sendKeys("123 123");
		  
		element=accountdriver.findElement(elementmap.getLocator("address2"));
		element.sendKeys("123 123");
			  
		element=accountdriver.findElement(elementmap.getLocator("city"));
		element.sendKeys("New York");
		  
		Select dropdown=new Select(accountdriver.findElement(elementmap.getLocator("state")));
		dropdown.selectByValue("NY");
		  
		element=accountdriver.findElement(elementmap.getLocator("postalCode"));
		element.sendKeys("55555");
		  
	}

}
