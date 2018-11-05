package Example.Module2;

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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

public class NewTestPrior1 {
	public WebDriver driver;	
	public static ExtentReports extent;
	public static ExtentTest test;
	Constants c= new Constants();
	OtherUtilities O = new OtherUtilities();
	ExcelUtils EU = new ExcelUtils();
	
	@BeforeSuite
	public void ReportInitialise(){
		extent= new ExtentReports(c.ExtentreportFolderPath+O.CurrentDate()+"/NewTest1.html",false);
		// false -- because we want to append & not create new report each time
		extent.loadConfig(new File(c.RootFolderPath+"extent-config.xml"));
	}
	
	@AfterSuite
	public void objectclosure(){
		extent.endTest(test);
		extent.flush();
		extent.close();
	}
		
	@Test				
	public void OpenApplication() throws Exception{
		try{
		driver.get(c.URL);
		//O.screenShot(driver);
		test.log(LogStatus.PASS, "Application opened " + test.addScreenCapture(O.screenShot(driver)));
		}catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Application failed to opened " + test.addScreenCapture(O.screenShot(driver)));
			e.printStackTrace();
		}
		
		String title = driver.getTitle();	
		//System.out.println(title);
		try{
		Assert.assertTrue(title.contains("Your Store"));
		test.log(LogStatus.PASS,"Page is : "+title);
		}catch(AssertionError e)
			{
				test.log(LogStatus.FAIL,"Page found is : "+title+"<br/>Page expected was : Your Store");
				e.printStackTrace();
			}
	}

	@Test(dependsOnMethods = "OpenApplication")				
	public void OpenLoginPage() throws Exception {
		
		O.waitForElementClickability(driver,test,"justanything",O.propertiesImport().getProperty("tab_MyAccount"));
		
		try{
		WebElement myElement=driver.findElement(By.xpath(O.propertiesImport().getProperty("tab_MyAccount")));
		myElement.findElement(By.xpath(O.propertiesImport().getProperty("tab_MyAccount"))).click();
		test.log(LogStatus.PASS,"my account tab click successful");
		}catch(Exception e)
		{
			test.log(LogStatus.FAIL,"my account tab click unsuccessful");
			e.printStackTrace();
		}
		O.waitForElementClickability(driver,test,"justanything",O.propertiesImport().getProperty("link_login"));
		//O.screenShot(driver);
		try{
		//myElement.findElement(By.xpath(O.propertiesImport().getProperty("link_login"))).click();
		test.log(LogStatus.PASS,"my account tab click successful");
		}catch(Exception e)
		{
			test.log(LogStatus.FAIL,"my account tab click unsuccessful");
			e.printStackTrace();
		}
		O.waitForElementClickability(driver,test,"justanything",O.propertiesImport().getProperty("link_BreadcrumbLogin"));
		//O.screenShot(driver);
		String title = driver.getTitle();
		//System.out.println(title);
		Assert.assertTrue(title.contains("Account Login")); 		
		test.log(LogStatus.PASS,"Page is : "+title);
		test.log(LogStatus.PASS, "Login Page opened " + test.addScreenCapture(O.screenShot(driver)));
	}
	@Test(dependsOnMethods = "OpenLoginPage")						
	public void LoginToApplication() throws Exception {
		
		EU.setExcelFile(c.ExcelInputFilePath_NewTest1,"Sheet1");
		
		O.waitForElementVisibility(driver,test,"justanything",O.propertiesImport().getProperty("txtbox_Email"));
		WebElement myElement=driver.findElement(By.xpath(O.propertiesImport().getProperty("txtbox_Email")));
		myElement.sendKeys(EU.getCellData(1,0));
		myElement.findElement(By.xpath(O.propertiesImport().getProperty("txtbox_Password"))).sendKeys(EU.getCellData(1,1));
		//O.screenShot(driver);
		test.log(LogStatus.PASS, "Values input : " + test.addScreenCapture(O.screenShot(driver)));
		myElement.findElement(By.xpath(O.propertiesImport().getProperty("btn_Login"))).click();
		O.waitForElementClickability(driver,test,"justanything",O.propertiesImport().getProperty("link_Logout_GroupItem"));
		//O.screenShot(driver);
		String title = driver.getTitle();
		//System.out.println(title);
		Assert.assertTrue(title.contains("My Account"));
		test.log(LogStatus.PASS,"Page is : "+title);
		test.log(LogStatus.PASS, "My Account page opened " + test.addScreenCapture(O.screenShot(driver)));
	}
	@Test(dependsOnMethods = "LoginToApplication")						
	public void NavigateToHomePage() throws Exception {
		
		O.waitForElementClickability(driver,test,"justanything",O.propertiesImport().getProperty("link_YourStore"));
		WebElement myElement=driver.findElement(By.xpath(O.propertiesImport().getProperty("link_YourStore")));
		myElement.click();
		O.waitForElementClickability(driver,test,"justanything",O.propertiesImport().getProperty("btn_AddToCart"));
		//O.screenShot(driver);
		String title = driver.getTitle();
		//System.out.println(title);
		Assert.assertTrue(title.contains("Your Store"));
		test.log(LogStatus.PASS,"Page is : "+title);
		test.log(LogStatus.PASS, "Home Page opened " + test.addScreenCapture(O.screenShot(driver)));
	}
	@Test(dependsOnMethods = "NavigateToHomePage")						
	public void LogoutApplication() throws Exception {
		
		O.waitForElementClickability(driver,test,"justanything",O.propertiesImport().getProperty("tab_MyAccount"));
		WebElement myElement=driver.findElement(By.xpath(O.propertiesImport().getProperty("tab_MyAccount")));
		myElement.click();
		O.waitForElementClickability(driver,test,"justanything",O.propertiesImport().getProperty("link_Logout"));
		//O.screenShot(driver);
		myElement.findElement(By.xpath(O.propertiesImport().getProperty("link_Logout"))).click();
		O.waitForElementVisibility(driver,test,"justanything","//a[@class='list-group-item'][text()='Login']");
		//O.screenShot(driver);
		String title = driver.getTitle();
		//System.out.println(title);
		Assert.assertTrue(title.contains("Account Logout"));
		test.log(LogStatus.PASS,"Page is : "+title);
		test.log(LogStatus.PASS, "Application Logged Out " + test.addScreenCapture(O.screenShot(driver)));
	}
	@BeforeTest
	public void beforeTest() throws IOException {
		System.setProperty("webdriver.chrome.driver", c.RootFolderPath+"Drivers/chromedriver.exe");
		driver = new ChromeDriver();  
		driver.manage().window().maximize();
		test=extent.startTest("NewTest1-"+O.CurrentTime(),"Example.Module1");
		test.log(LogStatus.PASS,"Browser launched successfully");
		//FileUtils.deleteDirectory(new File("D:/ashu/Automation/mavenartid/Output/test/"));
	}		
	@AfterTest
	public void afterTest() {
		driver.close();
		driver.quit();
		test.log(LogStatus.PASS,"Browser closed successfully");
	}		

}
