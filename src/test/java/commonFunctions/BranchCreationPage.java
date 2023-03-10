package commonFunctions;

import org.apache.regexp.recompile;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.Zip;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class BranchCreationPage {

WebDriver driver;

public BranchCreationPage(WebDriver driver)
{
	this.driver=driver;
}

@FindBy(xpath = "//input[@id='BtnNewBR']")
WebElement clickNewBranch;

@FindBy(name = "txtbName")
WebElement EnterBranch;

@FindBy(name = "txtAdd1")
WebElement EnterAddress1;

@FindBy(name = "Txtadd2")
WebElement EnterAddress2;

@FindBy(name = "txtadd3")
WebElement EnterAddress3;

@FindBy(name = "txtArea")
WebElement EnterArea;

@FindBy(name = "txtZip")
WebElement Enterzipcode;

@FindBy(name = "lst_counrtyU")
WebElement SelectCountry;

@FindBy(name = "lst_stateI")
WebElement SelectState;

@FindBy(name = "lst_cityI")
WebElement SelectCity;

@FindBy(name = "btn_insert")
WebElement ClickSubmit;

public boolean verifyNewBranch(String branchname,String Address1,String Address2,String Address3,String
		Area,String Zipcode,String Country,String State,String City)throws Throwable
{
	this.clickNewBranch.click();
	this.EnterBranch.sendKeys(branchname);
	this.EnterAddress1.sendKeys(Address1);
	this.EnterAddress2.sendKeys(Address2);
	this.EnterAddress3.sendKeys(Address3);
	this.EnterArea.sendKeys(Area);
	this.Enterzipcode.sendKeys(Zipcode);
	new Select(this.SelectCountry).selectByVisibleText(Country);
	new Select(this.SelectState).selectByVisibleText(State);
	new Select(this.SelectCity).selectByVisibleText(City);
	this.ClickSubmit.click();
	
	//capture alert message
	
	String ExpectedAlert =driver.switchTo().alert().getText();
	Thread.sleep(5000);
	driver.switchTo().alert().accept();
	String ActualAlert ="New Branch with";
	
	if(ExpectedAlert.toLowerCase().contains(ActualAlert.toLowerCase()))
	{
		Reporter.log(ExpectedAlert,true);
		return true;
	}
	else
	{
		Reporter.log("New Branch Creation Fail",true);
		return false;
	}
}
}