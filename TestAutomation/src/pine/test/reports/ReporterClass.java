package pine.test.reports;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


import pine.test.utility.Constant;

public class ReporterClass  {
	
	private ExtentReports extent;
	private ExtentTest logger;
	private ExtentTest childLogger;
	
	public ReporterClass() {
		
		DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
		String todayDate = LocalDate.now().format(formatter);
		formatter = DateTimeFormatter.ofPattern("HHmmss");
		String currentTime = LocalTime.now().format(formatter);
		
		extent = new ExtentReports(Constant.Path_TestData + "/test-output/Reports/TestExtentReport"+todayDate+"_"+currentTime+".html", true);
		
		extent.loadConfig(new File(Constant.Reports_Config));
	}
	
	
	
	public void startTest(String testName) {
		logger = extent.startTest(testName);
	}
	
	public void endTest() {
		extent.endTest(logger);
	}
	
	public void writeToFile() {
		extent.flush();
		extent.close();
	}
	
	public void parentLog(LogStatus status, String message) {
		logger.log(status, message);
	}
	
	public void Log(LogStatus status, String message) {
		if(childLogger!=null) {
			childLogger.log(status, message);
		}
		else {
			logger.log(status, message);
		}
	}
	
	public void startChildTest(String description) {
		childLogger = extent.startTest(description);
		logger.appendChild(childLogger);
	}
	
	public void LogScreenshot(LogStatus ls, String imagePath) {
		if(childLogger!=null) {
			childLogger.log(ls, logger.addScreenCapture(imagePath));
		}
		else {
			logger.log(ls, logger.addScreenCapture(imagePath));
		}
		
	}

}
