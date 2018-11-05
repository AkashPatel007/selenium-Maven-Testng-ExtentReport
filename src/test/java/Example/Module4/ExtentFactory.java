package Example.Module4;

import com.relevantcodes.extentreports.ExtentReports;
import config.Constants;
import utility.ExcelUtils;
import utility.OtherUtilities;

public class ExtentFactory
{
	public ExtentReports extent;
	Constants c= new Constants();
	OtherUtilities O = new OtherUtilities();
	ExcelUtils EU = new ExcelUtils();
	
	public ExtentReports getInstance()
	{
		//ExtentReports extent;
		String path = c.ExtentreportFolderPath+O.CurrentDate()+"/GroupedModule4.html";
		extent = new ExtentReports(path,false);
		return extent;
	}

	
/*import java.io.File;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.AbstractReporter;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
 
	public static ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	Constants c= new Constants();
	OtherUtilities O = new OtherUtilities();
	ExcelUtils EU = new ExcelUtils();
	
	@BeforeSuite
	public void ReportInitialise(){
		htmlReporter = new ExtentHtmlReporter(c.ExtentreportFolderPath+O.CurrentDate()+"/CombinedReport.html");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("AkashDocName");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName("AkashReportName");
		extent= new ExtentReports();
		extent.attachReporter(htmlReporter);
		// false -- because we want to append & not create new report each time
		//extent.loadConfig(new File(c.RootFolderPath+"extent-config.xml"));
	}
	
	@AfterMethod
	public void getResult(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			test.fail(MarkupHelper.createLabel(result.getName()+"Test case Failed",ExtentColor.RED));
			test.fail(result.getThrowable());
		}
		else if(result.getStatus()==ITestResult.SUCCESS)
		{
			test.pass(MarkupHelper.createLabel(result.getName()+"Test case Passed",ExtentColor.GREEN));
		}
		else
		{
			test.skip(MarkupHelper.createLabel(result.getName()+"Test case Skiped",ExtentColor.YELLOW));
			test.skip(result.getThrowable());
		}
		
	}
	@AfterSuite
	public void objectclosure()
	{
		extent.flush();
	}
*/
}
