package pine.test.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import pine.test.pageObjects.Miscellaneous_Object;


public class FileUpload {
	
	 
	
	public void fileupload(WebDriver driver) throws InterruptedException {
		
		Miscellaneous_Object misobj = new Miscellaneous_Object(driver);
		misobj.Mis_Tab.click();
		misobj.uploaddownload.click();
		misobj.FileUpload.click();		
		//misobj.File_Type.sendKeys("Rule").click();
		}
	public void selectfileRule(WebDriver driver)
	{
	Miscellaneous_Object misobj = new Miscellaneous_Object(driver);	
	Select sel=new Select(misobj.File_Type);	
	sel.selectByIndex(3);
	}
	
	public void UploadFile(WebDriver driver) throws InterruptedException
	{
	Miscellaneous_Object misobj = new Miscellaneous_Object(driver);
	misobj.File_Upload.sendKeys("C:\\Users\\gunjan.varshney\\Documents\\ICSuwu.xml");
	Thread.sleep(10000);
	misobj.Submit.click();
	Thread.sleep(10000);
	misobj.okbtn.click();
	Thread.sleep(10000);
	}
	public void selectfilePVM(WebDriver driver) throws InterruptedException
	{
	Miscellaneous_Object misobj = new Miscellaneous_Object(driver);
	Select sel=new Select(misobj.File_Type);	
	sel.selectByIndex(1);
	
	}
	public void selectHardwareID(WebDriver driver)
	{
	Miscellaneous_Object misobj = new Miscellaneous_Object(driver);
	Select sel=new Select(misobj.Hardware_Family);	
	sel.selectByIndex(1);
	misobj.File_ID.sendKeys("12344");

	}
	public void UploadFilePVM(WebDriver driver) throws InterruptedException
	{
	Miscellaneous_Object misobj = new Miscellaneous_Object(driver);
	misobj.File_Upload.sendKeys("C:\\Users\\gunjan.varshney\\Downloads\\PVMePOS1.xml");
	Thread.sleep(10000);
	misobj.Submit.click();
	Thread.sleep(10000);
	misobj.okbtn.click();
	
	}

}