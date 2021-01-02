package pine.test.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import pine.test.pageObjects.File_ManagementObject;

public class FileManagement 
{
public void File_Management (WebDriver driver) throws InterruptedException
{
		
	File_ManagementObject fileobj = new File_ManagementObject(driver);
		fileobj.FileMgmt.click();
		fileobj.File_Search.click();
		Thread.sleep(10000);
		}
public void selectfileType_Rule(WebDriver driver)
{
File_ManagementObject fileobj = new File_ManagementObject(driver);
Select sel=new Select(fileobj.FileType);	
sel.selectByValue("Rule");
}
public void search_Category(WebDriver driver)
{
File_ManagementObject fileobj = new File_ManagementObject(driver);
Select sel=new Select(fileobj.FileType);	
sel.selectByValue("File");
fileobj.File_name.sendKeys("ics.xml");
fileobj.Search.click();

}

}
