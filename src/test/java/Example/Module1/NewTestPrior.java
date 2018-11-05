package Example.Module1;

import utility.ExcelUtils;
import utility.OtherUtilities;
import config.Constants;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.BeforeTest;
import java.io.File;
import java.io.IOException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

public class NewTestPrior {
	public WebDriver driver;	
	public WebElement myElement;
	public ExtentReports extent;
	public ExtentTest test;
	Constants c= new Constants();
	OtherUtilities O = new OtherUtilities();
	ExcelUtils EU = new ExcelUtils();
	
	@BeforeSuite
	public void ReportInitialise(){
		extent= new ExtentReports(c.ExtentreportFolderPath+O.CurrentDate()+"/NewTest2.html",false);
		// false -- because we want to append & not create new report each time
		extent.loadConfig(new File(c.RootFolderPath+"extent-config.xml"));
	}
	
	@AfterSuite
	public void objectclosure(){
		extent.endTest(test);
		extent.flush();
		extent.close();
	}

	@BeforeTest
	public void beforeTest() throws IOException {
		test=extent.startTest("NewTest2-"+O.CurrentTime(),"Example.Module1");
		
		if (c.Browser.equalsIgnoreCase("chrome"))
		{	
			try{
			System.setProperty("webdriver.chrome.driver", c.RootFolderPath+"Drivers/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			test.log(LogStatus.PASS, "Chrome Browser opened & maximized Successfully");
			}catch(Exception e)
			{
				test.log(LogStatus.ERROR, ExceptionUtils.getStackTrace(e));
				test.log(LogStatus.FAIL,"Browser failed to open");
				e.printStackTrace();
			}
		}
		else if(c.Browser.equalsIgnoreCase("firefox"))
		{
			try{
			System.setProperty("webdriver.gecko.driver", c.RootFolderPath+"Drivers/geckodriver.exe");
			//DesiredCapabilities capabilities=DesiredCapabilities.firefox();
			//capabilities.setCapability("marionette", true);
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			test.log(LogStatus.PASS, "Firefox Browser opened & maximized Successfully");
			}catch(Exception e)
			{
				test.log(LogStatus.ERROR, ExceptionUtils.getStackTrace(e));
				test.log(LogStatus.FAIL,"Browser failed to open");
				e.printStackTrace();
			}
		}
		else if(c.Browser.equalsIgnoreCase("IE"))
		{	
			try{
			System.setProperty("webdriver.ie.driver",c.RootFolderPath+"Drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			test.log(LogStatus.PASS, "IE Browser opened & maximized Successfully");// + test.addScreenCapture(O.screenShot(driver)));
			}catch(Exception e)
			{
				test.log(LogStatus.ERROR, ExceptionUtils.getStackTrace(e));
				test.log(LogStatus.FAIL,"Browser failed to open");
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Browser doesnot exist");
		}
	}		

	@AfterTest
	public void afterTest() {
		driver.close();
		driver.quit();
		test.log(LogStatus.PASS,"Browser closed successfully");
	}		

	@Test				
	public void OpenApplication1() throws Exception{
		O.OpenApplication(driver,test);
		O.VerifyPageTitle(driver,test,"Your Store");
	}

	@Test(dependsOnMethods = "OpenApplication1")				
	public void OpenLoginPage() throws Exception {
		
		O.waitForElementClickability(driver,test,"My Account Tab",O.propertiesImport().getProperty("tab_MyAccount"));
		O.FindElement(driver,test,myElement,"My Account Tab",O.propertiesImport().getProperty("tab_MyAccount"));
		O.ClickElement(driver,test,myElement,"My Account Tab",O.propertiesImport().getProperty("tab_MyAccount"));
		O.waitForElementClickability(driver,test,"Login Link",O.propertiesImport().getProperty("link_login"));
		O.FindElement(driver,test,myElement,"Login Link",O.propertiesImport().getProperty("link_login"));
		O.ClickElement(driver,test,myElement,"Login Link",O.propertiesImport().getProperty("link_login"));
		O.waitForElementClickability(driver,test,"Login Link",O.propertiesImport().getProperty("link_BreadcrumbLogin"));
		O.VerifyPageTitle(driver, test, "Account Login");
	}
	@Test(dependsOnMethods = "OpenLoginPage")						
	public void LoginToApplication() throws Exception {
		
		EU.setExcelFile(c.ExcelInputFilePath_NewTest1,"Sheet1");
		
		O.waitForElementVisibility(driver,test,"Email Textbox",O.propertiesImport().getProperty("txtbox_Email"));
		O.FindElement(driver,test,myElement,"Email Textbox",O.propertiesImport().getProperty("txtbox_Email"));
		O.InputAtTextbox(driver,test,myElement,"Textbox Email",O.propertiesImport().getProperty("txtbox_Email"),EU.getCellData(1,0));
		O.InputAtTextbox(driver,test,myElement,"Textbox Passowrd",O.propertiesImport().getProperty("txtbox_Password"),EU.getCellData(1,1));
		O.FindElement(driver,test,myElement,"Login Button",O.propertiesImport().getProperty("btn_Login"));
		O.ClickElement(driver,test,myElement,"Login Button",O.propertiesImport().getProperty("btn_Login"));
		O.waitForElementClickability(driver,test,"Logout Link from GroupItem",O.propertiesImport().getProperty("link_Logout_GroupItem"));
		O.VerifyPageTitle(driver, test, "My Account");
	}
	@Test(dependsOnMethods = "LoginToApplication")						
	public void NavigateToHomePage() throws Exception {
		
		O.waitForElementClickability(driver,test,"YourStore Link",O.propertiesImport().getProperty("link_YourStore"));
		O.FindElement(driver,test,myElement,"Link YourStore",O.propertiesImport().getProperty("link_YourStore"));
		O.ClickElement(driver,test,myElement,"Link YourStore",O.propertiesImport().getProperty("link_YourStore"));
		O.waitForElementClickability(driver,test,"AddToCart Button",O.propertiesImport().getProperty("btn_AddToCart"));
		O.VerifyPageTitle(driver, test, "Your Store");
	}
	@Test(dependsOnMethods = "NavigateToHomePage")						
	public void LogoutApplication() throws Exception {
		
		O.waitForElementClickability(driver,test,"MyAccount Tab",O.propertiesImport().getProperty("tab_MyAccount"));
		O.FindElement(driver,test,myElement,"MyAccount Tab",O.propertiesImport().getProperty("tab_MyAccount"));
		O.ClickElement(driver,test,myElement,"MyAccount Tab",O.propertiesImport().getProperty("tab_MyAccount"));
		O.waitForElementClickability(driver,test,"Link Logout",O.propertiesImport().getProperty("link_Logout"));
		O.FindElement(driver,test,myElement,"Link Logout",O.propertiesImport().getProperty("link_Logout"));
		O.ClickElement(driver,test,myElement,"Link Logout",O.propertiesImport().getProperty("link_Logout"));
		O.VerifyPageTitle(driver, test,"Account Logout");
	}
}
