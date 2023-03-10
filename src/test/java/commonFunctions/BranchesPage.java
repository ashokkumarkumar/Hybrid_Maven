package commonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BranchesPage {
	
@FindBy(xpath="(//img)[5]")
WebElement clickBranches;

public void verifyBranches()
{
	clickBranches.click();
}
}
