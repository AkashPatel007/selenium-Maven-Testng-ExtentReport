package Example.Module1;

import utility.ExcelUtils;
import utility.OtherUtilities;
import config.Constants;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import java.io.File;
import java.io.IOException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
/*import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;*/
import org.testng.annotations.Parameters;


public class NewTest4 extends ExtentFactory{
	public WebDriver driver;	
	public WebElement myElement;
	public ExtentReports extent;
	public ExtentTest test;
	Constants c= new Constants();
	OtherUtilities O = new OtherUtilities();
	ExcelUtils EU = new ExcelUtils();
	ExtentFactory ef= new ExtentFactory();
	
/*	@BeforeTest
	public void ReportInitialise(){
		//extent= new ExtentReports(c.ExtentreportFolderPath+O.CurrentDate()+"/NewTest1.html",false);
        extent= getInstance();
		extent.loadConfig(new File(c.RootFolderPath+"extent-config.xml"));
		test=extent.startTest("NewTest1-"+O.CurrentTime(),"Example.Module1");
		//System.out.println("testing bc");
	}
*/	
	/*@AfterTest
	public void objectclosure(){
		extent.endTest(test);
		extent.flush();
		//extent.close();
	}
*/
	@BeforeClass
	@Parameters("Browser")
	public void beforeTest(String Browser) throws IOException {
		extent= ef.getInstance();
		extent.loadConfig(new File(c.RootFolderPath+"extent-config.xml"));
		test=extent.startTest("NewTest4-"+O.CurrentTime(),"Example.Module1");
		
		if (Browser.equalsIgnoreCase("chrome"))
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
		else if(Browser.equalsIgnoreCase("firefox"))
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
		else if(Browser.equalsIgnoreCase("IE"))
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

	@AfterClass
	public void afterTest() {
		driver.close();
		driver.quit();
		test.log(LogStatus.PASS,"Browser closed successfully");
		
		extent.endTest(test);
		extent.flush();
	}		

	@Test				
	public void Module1NewTest4() throws Exception{
		
		//Open Application & verify page title
		O.OpenApplication(driver,test);
		O.VerifyPageTitle(driver,test,"Your Store");
		
		//Open Login Page
		O.waitForElementClickability(driver,test,"My Account Tab",O.propertiesImport().getProperty("tab_MyAccount"));
		O.FindElement(driver,test,myElement,"My Account Tab",O.propertiesImport().getProperty("tab_MyAccount"));
		O.ClickElement(driver,test,myElement,"My Account Tab",O.propertiesImport().getProperty("tab_MyAccount"));
		O.waitForElementClickability(driver,test,"Login Link",O.propertiesImport().getProperty("link_login"));
		O.FindElement(driver,test,myElement,"Login Link",O.propertiesImport().getProperty("link_login"));
		O.ClickElement(driver,test,myElement,"Login Link",O.propertiesImport().getProperty("link_login"));
		O.waitForElementClickability(driver,test,"Login Link",O.propertiesImport().getProperty("link_BreadcrumbLogin"));
		O.VerifyPageTitle(driver, test, "Account Login");
		
		//Login to application
		EU.setExcelFile(c.ExcelInputFilePath_NewTest1,"Sheet1");
		
		O.waitForElementVisibility(driver,test,"Email Textbox",O.propertiesImport().getProperty("txtbox_Email"));
		O.FindElement(driver,test,myElement,"Email Textbox",O.propertiesImport().getProperty("txtbox_Email"));
		O.InputAtTextbox(driver,test,myElement,"Textbox Email",O.propertiesImport().getProperty("txtbox_Email"),EU.getCellData(1,0));
		O.InputAtTextbox(driver,test,myElement,"Textbox Passowrd",O.propertiesImport().getProperty("txtbox_Password"),EU.getCellData(1,1));
		O.FindElement(driver,test,myElement,"Login Button",O.propertiesImport().getProperty("btn_Login"));
		O.ClickElement(driver,test,myElement,"Login Button",O.propertiesImport().getProperty("btn_Login"));
		O.waitForElementClickability(driver,test,"Logout Link from GroupItem",O.propertiesImport().getProperty("link_Logout_GroupItem"));
		O.VerifyPageTitle(driver, test, "My Account");
	
		//Navigate to Home Page
		O.waitForElementClickability(driver,test,"YourStore Link",O.propertiesImport().getProperty("link_YourStore"));
		O.FindElement(driver,test,myElement,"Link YourStore",O.propertiesImport().getProperty("link_YourStore"));
		O.ClickElement(driver,test,myElement,"Link YourStore",O.propertiesImport().getProperty("link_YourStore"));
		O.waitForElementClickability(driver,test,"AddToCart Button",O.propertiesImport().getProperty("btn_AddToCart"));
		O.VerifyPageTitle(driver, test, "Your Store");
		
		// Logout from Application
		O.waitForElementClickability(driver,test,"MyAccount Tab",O.propertiesImport().getProperty("tab_MyAccount"));
		O.FindElement(driver,test,myElement,"MyAccount Tab",O.propertiesImport().getProperty("tab_MyAccount"));
		O.ClickElement(driver,test,myElement,"MyAccount Tab",O.propertiesImport().getProperty("tab_MyAccount"));
		O.waitForElementClickability(driver,test,"Link Logout",O.propertiesImport().getProperty("link_Logout"));
		O.FindElement(driver,test,myElement,"Link Logout",O.propertiesImport().getProperty("link_Logout"));
		O.ClickElement(driver,test,myElement,"Link Logout",O.propertiesImport().getProperty("link_Logout"));
		O.VerifyPageTitle(driver, test,"Account Logout");

	}
}
