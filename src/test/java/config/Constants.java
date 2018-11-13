package config;

public class Constants 
{
	
		String currentDir = System.getProperty("user.dir");
		
		// URL of the application
		public String URL= "https://demo.opencart.com/";
		//%system.teamcity.build.workingDir%
		//D:/ashu/Automation/mavenartid/ExcelInputFile/NewTest1.xls
		//currentDir = D:/ashu/Automation/mavenartid
		// ExcelInput file folder location
		public String ExcelInputFilePath_NewTest1 = currentDir+"/ExcelInputFile/NewTest1.xls";
		public String ORFolderPath = currentDir+"/src/test/java/config/";
		public String ExtentreportFolderPath = currentDir+"/Output/ExtentReportFolder/";
		public String ScreenshotFolderPath = currentDir+"/Output/test/";
		public String RootFolderPath = currentDir+"/";
		public String Browser = "chrome";
		
}