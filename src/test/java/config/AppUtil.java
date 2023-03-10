package config;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class AppUtil {
	
public static WebDriver driver;
public static Properties config;

@BeforeSuite

public static void setUp()throws Throwable
{
	config=new Properties();
	config.load(new FileInputStream("D:\\11oclockSelenium\\Hybrid_Maven\\PropertyFile\\Environment.properties"));
	
	if (config.getProperty("Browser").equalsIgnoreCase("chrome")) 
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(config.getProperty("Url"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
}

@AfterSuite

public static void teardown()
{
	driver.close();
}
}
