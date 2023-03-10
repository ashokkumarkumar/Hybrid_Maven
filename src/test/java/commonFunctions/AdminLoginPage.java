package commonFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class AdminLoginPage {
	
WebDriver driver;

public AdminLoginPage(WebDriver driver)
{
	this.driver=driver;
	
}

@FindBy(name="txtuId")
WebElement EnterUser;

@FindBy(name="txtPword")
WebElement Enterpass;

@FindBy (name="login")
WebElement ClickLogin;

public boolean verifyLogin(String username,String password)
{
	this.EnterUser.sendKeys(username);
	this.Enterpass.sendKeys(password);
	this.ClickLogin.click();
	String Expected="adminflow";
	String Actual=driver.getCurrentUrl();
	
	if (Actual.toLowerCase().contains(Expected.toLowerCase())) 
	{
		Reporter.log("Login Success::"+Expected+"        "+Actual,true);
		return true;
	}
	else
	{
		Reporter.log("Login Fail::"+Expected+"        "+Actual,true);
		return true;
	}
}
}
