package commonFunctions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class AdminLogoutPage {
WebDriver driver;

public AdminLogoutPage(WebDriver driver)
{
	this.driver=driver;
}

@FindBy(xpath = "(//img)[4]")
WebElement ClickLogout;

@FindBy(name = "login")
WebElement LoginBtn;

public boolean verifyLogout()
{
	this.ClickLogout.click();
	
	if(this.LoginBtn.isDisplayed())
	{
		Reporter.log("AdminLogout Success::",true);
		return true;
	}
	else
	{
		Reporter.log("AdminLogout Fail::",true);
		return false;
	}
}
}