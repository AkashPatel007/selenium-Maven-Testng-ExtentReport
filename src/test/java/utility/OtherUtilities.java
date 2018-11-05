package utility;

import config.Constants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class OtherUtilities {
	public WebDriver driver;	
	public ExtentReports extent;
	public ExtentTest test;
	Constants c= new Constants();
	ExcelUtils EU = new ExcelUtils();
	
	
	public void implicitwait(){
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void waitForElementVisibility(WebDriver driver,ExtentTest test,String ElementName,String xpath){
		try{
		    WebDriverWait wait = new WebDriverWait(driver, 5);
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		    test.log(LogStatus.PASS,"Wait for "+ElementName+" visibility Successfully");
			}catch(Exception e)
				{
					test.log(LogStatus.FAIL,"Wait for "+ElementName+" visibility Unsuccessfully");
					test.log(LogStatus.ERROR, ExceptionUtils.getStackTrace(e));
					e.printStackTrace();
				}
		
	}
	public void waitForElementNonVisibility(WebDriver driver,ExtentTest test,String ElementName,String xpath){
		try{
		    WebDriverWait wait = new WebDriverWait(driver, 5);
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
		    test.log(LogStatus.PASS,"Wait for "+ElementName+" Non-visibility Successfully");
			}catch(Exception e)
				{
					test.log(LogStatus.FAIL,"Wait for "+ElementName+" Non-visibility Unsuccessfully");
					test.log(LogStatus.ERROR, ExceptionUtils.getStackTrace(e));
					e.printStackTrace();
				}
	}
	
	public void waitForElementClickability(WebDriver driver,ExtentTest test,String ElementName,String xpath){
		try{
		    WebDriverWait wait = new WebDriverWait(driver, 5);
		    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		    test.log(LogStatus.PASS,"Wait for "+ElementName+" Clickability Successfully");
			}catch(Exception e)
				{
					test.log(LogStatus.FAIL,"Wait for "+ElementName+" Clickability Unsuccessfully");
					test.log(LogStatus.ERROR, ExceptionUtils.getStackTrace(e));
					e.printStackTrace();
				}
	}
	public void waitForElementNonClickability(WebDriver driver,ExtentTest test,String ElementName,String xpath){
		try{
		    WebDriverWait wait = new WebDriverWait(driver, 5);
		    wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(By.xpath(xpath))));
		    test.log(LogStatus.PASS,"Wait for "+ElementName+" visibility Successfully");
			}catch(Exception e)
				{
					test.log(LogStatus.FAIL,"Wait for "+ElementName+" visibility Unsuccessfully");
					test.log(LogStatus.ERROR, ExceptionUtils.getStackTrace(e));
					e.printStackTrace();
				}
	}
	public String screenShot(WebDriver driver) throws IOException{
	    File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    String filename =  new SimpleDateFormat("yyyyMMddhhmmss'.png'").format(new Date());
	    String dest1 = c.ScreenshotFolderPath+filename;
	    File dest = new File(dest1);
	    FileUtils.copyFile(scr, dest);
	    return dest1;
	}
	public Properties propertiesImport()
	{
		File file = new File(c.ORFolderPath+"OR.properties");
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		//load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	public String CurrentDate()
	{
		Date date = new Date();
	    SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy");
	    String TodaysDate = ft.format(date);
	    return TodaysDate;
	}
	public String CurrentTime()
	{
		Date date = new Date();
	    SimpleDateFormat ft = new SimpleDateFormat ("hh:mm:ss");
	    String TodaysTime = ft.format(date);
	    return TodaysTime;
	}
	
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	public void OpenApplication(WebDriver driver,ExtentTest test) throws Exception
	{
		try{
			System.out.println("before url");
			driver.get(c.URL);
			System.out.println("After url");
			test.log(LogStatus.PASS, "Application opened " + test.addScreenCapture(screenShot(driver)));
			}catch(Exception e)
			{
				test.log(LogStatus.FAIL, "Application failed to opened " + test.addScreenCapture(screenShot(driver)));
				test.log(LogStatus.ERROR, ExceptionUtils.getStackTrace(e));
				e.printStackTrace();
			}
	}	
	
	public void VerifyPageTitle(WebDriver driver,ExtentTest test,String pageTitle) throws IOException
	{
		String title = driver.getTitle();	
		try{
		Assert.assertTrue(title.contains(pageTitle));
		test.log(LogStatus.PASS,"Page is : "+title+test.addScreenCapture(screenShot(driver)));
		}catch(AssertionError e)
			{
				test.log(LogStatus.FAIL,"Page found is : "+title+"<br/>Page expected was : "+pageTitle+test.addScreenCapture(screenShot(driver)));
				test.log(LogStatus.ERROR, ExceptionUtils.getStackTrace(e));
				e.printStackTrace();
			}
	}
	public void FindElement(WebDriver driver,ExtentTest test,WebElement myElement,String ElementName,String xpath) throws IOException
	{
		try{
			myElement=driver.findElement(By.xpath(xpath));
			test.log(LogStatus.PASS,ElementName+" found successfully"+test.addScreenCapture(screenShot(driver)));
			}catch(Exception e)
			{
				test.log(LogStatus.FAIL,ElementName+" found unsuccessfully"+test.addScreenCapture(screenShot(driver)));
				e.printStackTrace();
			}
	}
	public void ClickElement(WebDriver driver,ExtentTest test,WebElement myElement,String ElementName,String xpath) throws IOException
	{
		try{
			myElement=driver.findElement(By.xpath(xpath));
			myElement.click();
			test.log(LogStatus.PASS,ElementName+" clicked successfully"+test.addScreenCapture(screenShot(driver)));
			}catch(Exception e)
			{
				test.log(LogStatus.FAIL,ElementName+" clicked unsuccessfully"+test.addScreenCapture(screenShot(driver)));
				e.printStackTrace();
			}
	}
	public void InputAtTextbox(WebDriver driver,ExtentTest test,WebElement myElement,String ElementName,String xpath,String InputValue) throws IOException
	{
		try{
			myElement=driver.findElement(By.xpath(xpath));
			myElement.sendKeys(InputValue);
			test.log(LogStatus.PASS," Input Successful at "+ElementName+test.addScreenCapture(screenShot(driver)));
			}catch(Exception e)
			{
				test.log(LogStatus.FAIL," Input Unsuccessful at "+ElementName+test.addScreenCapture(screenShot(driver)));
				e.printStackTrace();
			}
	}
}
	
/*	public void OpenBrowser(WebDriver driver,ExtentTest test,String Browser)
	{
		if (Browser.equalsIgnoreCase("chrome"))
		{	
			try{
			System.setProperty("webdriver.chrome.driver", c.RootFolderPath+"Drivers/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			test.log(LogStatus.PASS, "Chrome Browser opened & maximized Successfully" + test.addScreenCapture(screenShot(driver)));
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
//			DesiredCapabilities capabilities=DesiredCapabilities.firefox();
//			capabilities.setCapability("marionette", true);
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			test.log(LogStatus.PASS, "Firefox Browser opened & maximized Successfully" + test.addScreenCapture(screenShot(driver)));
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
			test.log(LogStatus.PASS, "IE Browser opened & maximized Successfully" + test.addScreenCapture(screenShot(driver)));
			}catch(Exception e)
			{
				test.log(LogStatus.ERROR, ExceptionUtils.getStackTrace(e));
				test.log(LogStatus.FAIL,"Browser failed to open");
				e.printStackTrace();
			}
		}
		else{
			System.out.println("Browser doesnot exist");
		}
		
	}*/
