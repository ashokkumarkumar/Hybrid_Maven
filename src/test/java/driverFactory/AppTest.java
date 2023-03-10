package driverFactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import commonFunctions.AdminLoginPage;
import commonFunctions.AdminLogoutPage;
import commonFunctions.BranchCreationPage;
import commonFunctions.BranchUpdationPage;
import commonFunctions.BranchesPage;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class AppTest extends AppUtil{
	
String inputpath ="D:\\11oclockSelenium\\Hybrid_Maven\\TestInput\\DAtaEngine.xlsx";
String outputpath="D:\\11oclockSelenium\\Hybrid_Maven\\TestOutPut\\HybridResults.xlsx";
String TCSheet ="TestCases";
String TSSheet ="TestSteps";

@Test

public void startTest()throws Throwable
 
{
	boolean res=false;
	String tcres="";
	
	//create object for excelfileuytil class
	
	ExcelFileUtil xl = new ExcelFileUtil(inputpath);
	
	//count no of rows in both sheet
	
	int TCCount =xl.rowCount(TCSheet);
	int TSCount =xl.rowCount(TSSheet);
	Reporter.log("No of rows in TCSheet::"+TCCount+"  "+"No of rows in TSSheet::"+TSCount,true);
	
	//iterate all rows in TCSheet
	
	for(int i=1;i<=TCCount;i++)
	{
		//read module status cell
		
		String ModuleStatus =xl.getCellData(TCSheet, i, 2);
		if(ModuleStatus.equalsIgnoreCase("Y"))
		{
			String tcid  =xl.getCellData(TCSheet, i, 0);
			
			//iterate all rows in TSSheet
			
			for(int j=1;j<=TSCount;j++)
			{
				
				//read tsid cell From TSSheet
				
				String tsid =xl.getCellData(TSSheet, j, 0);
				if(tcid.equalsIgnoreCase(tsid))
				{
					
					//read keyword cell from TSSheet
					
					String keyword =xl.getCellData(TSSheet, j, 3);
					if(keyword.equalsIgnoreCase("AdminLogin"))
					{
						
						//call admin login page
						
						AdminLoginPage login =PageFactory.initElements(driver, AdminLoginPage.class);
						String Para1 =xl.getCellData(TSSheet, j, 5);
						String para2 =xl.getCellData(TSSheet, j, 6);
						
						//call login method
						
						res =login.verifyLogin(Para1, para2);
					}
					else if(keyword.equalsIgnoreCase("BranchCreation"))
					{
						BranchesPage branches =PageFactory.initElements(driver, BranchesPage.class);
						BranchCreationPage newBranch =PageFactory.initElements(driver, BranchCreationPage.class);
						String para1 =xl.getCellData(TSSheet, j, 5);
						String para2 =xl.getCellData(TSSheet, j, 6);
						String para3 =xl.getCellData(TSSheet, j, 7);
						String para4 =xl.getCellData(TSSheet, j, 8);
						String para5 =xl.getCellData(TSSheet, j, 9);
						String para6 =xl.getCellData(TSSheet, j, 10);
						String para7 =xl.getCellData(TSSheet, j, 11);
						String para8 =xl.getCellData(TSSheet, j, 12);
						String para9 =xl.getCellData(TSSheet, j, 13);
						branches.verifyBranches();
						res =newBranch.verifyNewBranch(para1, para2, para3, para4, para5, para6, para7, para8, para9);
					}
					else if(keyword.equalsIgnoreCase("BranchUpdation"))
					{
						BranchesPage branches =PageFactory.initElements(driver, BranchesPage.class);
						BranchUpdationPage update =PageFactory.initElements(driver, BranchUpdationPage.class);
						String BranchName =xl.getCellData(TSSheet, j, 5);
						String Address1 =xl.getCellData(TSSheet, j, 6);
						String Area =xl.getCellData(TSSheet, j, 9);
						String Zipcode =xl.getCellData(TSSheet, j, 10);
						branches.verifyBranches();
						res = update.verifyBranchUpdate(BranchName, Address1, Area, Zipcode);
					}
					else if(keyword.equalsIgnoreCase("AdminLogout"))
					{
						AdminLogoutPage logout =PageFactory.initElements(driver, AdminLogoutPage.class);
						res =logout.verifyLogout();
					}
					
					String tsres="";
					if(res)
					{
						
						//if res is true write as pass into status cell in TSSheet
						
						tsres ="Pass";
						xl.setCellData(TSSheet, j, 4, tsres, outputpath);
								
					}
					else
					{
						
						//if res is false write as Fail into status cell in TSSheet
						
						tsres="Fail";
						xl.setCellData(TSSheet, j, 4, tsres, outputpath);
					}
					tcres = tsres;
					
				}
			}
			
			//writte as tcres into TCCheest
			
			xl.setCellData(TCSheet, i, 3, tcres, outputpath);
		}
		else
		{
			
			///write as Blocked which test case Flaged to N In TCSheet
			
			xl.setCellData(TCSheet, i, 3, "Blocked", outputpath);
		}
	}
}

}