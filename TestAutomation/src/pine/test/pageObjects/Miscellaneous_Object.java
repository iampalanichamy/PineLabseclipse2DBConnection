package pine.test.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Miscellaneous_Object {
	//private WebDriver driver;
	
		public Miscellaneous_Object (WebDriver driver) {
			
			PageFactory.initElements(driver, this);
		}
		//Miscellaneous Tab
		
		@FindBy(how = How.ID, using = "MiscellaneousAndUpload")
		public WebElement Mis_Tab; 
		//File Upload
		@FindBy(how = How.XPATH, using = "//li[@id='Uploads']")
		public WebElement uploaddownload; 
		
		@FindBy(how = How.XPATH, using = "//a[text()='File Upload']")
		public WebElement FileUpload; 
		
		//File_Upload Page
			@FindBy(how = How.ID, using = "ContentPlaceHolder1_FileTypeRadioButton")
			public WebElement File_Type; 
			
			@FindBy(how = How.ID, using = "ContentPlaceHolder1_FileId")
			public WebElement File_ID;
			
			@FindBy(how = How.CLASS_NAME, using = "col-sm-8")
			public WebElement File_Name;
			
			@FindBy(how = How.XPATH, using = "//a[text()='File Upload']")
			public WebElement File_Upload;
			
			@FindBy(how = How.ID, using = "ContentPlaceHolder1_FileUpload")
			public WebElement Choose_File;
			
			@FindBy(how = How.ID, using = "alertify-ok")
			public WebElement okbtn; 
			
			@FindBy(how = How.ID, using = "DropDownListSelectHardwareFamily")
			public WebElement Hardware_Family;

			@FindBy(how = How.ID, using = "SubmitFileUpload")
			public WebElement Submit;
			
			@FindBy(how = How.XPATH, using = "//*[@id=\"alertify\"]/div/article/p")
			public WebElement popUpMessage;

}
