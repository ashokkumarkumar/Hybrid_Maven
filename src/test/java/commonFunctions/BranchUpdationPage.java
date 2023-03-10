package commonFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class BranchUpdationPage {
	
WebDriver driver;

public BranchUpdationPage(WebDriver driver)
{
	this.driver=driver;
}

@FindBy(xpath = "(//img)[10]")
WebElement ClickEdit;

@FindBy(name = "txtbnameU")
WebElement EnterBranch;

@FindBy(name = "txtadd1u")
WebElement EnterAddress1;

@FindBy(name = "txtareaU")
WebElement EnterArea;

@FindBy(name = "txtzipu")
WebElement EnterZip;

@FindBy(name = "btnupdate")
WebElement ClickUpdate;

public boolean verifyBranchUpdate(String BranchName,String Address1,String Area,String Zipcode)throws Throwable
{
	this.ClickEdit.click();
	this.EnterBranch.clear();
	this.EnterBranch.sendKeys(BranchName);
	this.EnterAddress1.clear();
	this.EnterAddress1.sendKeys(Address1);
	this.EnterArea.clear();
	this.EnterArea.sendKeys(Area);
	this.EnterZip.clear();
	this.EnterZip.sendKeys(Zipcode);
	this.ClickUpdate.click();
	
	String ExpectedAlert =driver.switchTo().alert().getText();
	Thread.sleep(5000);
	driver.switchTo().alert().accept();
	String ActualAlert ="Branch updated";
	
	if(ExpectedAlert.toLowerCase().contains(ActualAlert.toLowerCase()))
	{
		Reporter.log(ExpectedAlert,true);
		return true;
	}
	else
	{
		Reporter.log("Branch Updation  Fail",true);
		return false;
	}
	
}
}