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

			@FindBy(how = How.XPATH, using = "//*[@id=\"MiscellaneousUploadLI\"]/a")
			public WebElement miscFile;

			//*[@id="MiscellaneousUploadLI"]/a

			@FindBy(how = How.ID, using = "OperationType")
			public WebElement Misc_FileType; 

			@FindBy(how = How.ID, using = "ExcelID")
			public WebElement selectExcelFile;

			@FindBy(how = How.ID, using = "SubmitBtn")
			public WebElement submitBtn;

			@FindBy(how = How.XPATH, using = "//*[@id=\"alertify\"]/div/article/p")
			public WebElement popUpMessageBankTID;

			@FindBy(how = How.XPATH, using = "//*[@id=\"alertify-ok\"]")
			public WebElement okBtnTIDRegis;

			@FindBy(how = How.ID, using = "CB_FileName")
			public WebElement selectByFileName;

			@FindBy(how = How.ID, using = "TB_Dnld_FileName")
			public WebElement enterFileName;

			@FindBy(how = How.ID, using = "ContentPlaceHolder1_Button2")
			public WebElement submitButton;

			@FindBy(how = How.XPATH, using = "//*[@id=\"alertify\"]/div/article/p")
			public WebElement popUpDownload;


			@FindBy(how = How.XPATH, using = "//*[@id=\"alertify-ok\"]")
			public WebElement okbtnDownld;

			@FindBy(how = How.XPATH, using = "//*[@id=\"A21\"]")
			public WebElement bulkUpload;

			@FindBy(how = How.ID, using = "DDL_RequestType")
			public WebElement actionType;

			@FindBy(how = How.XPATH, using = "//*[@id=\"ExcelID\"]")
			public WebElement selectBulkFile;

			@FindBy(how = How.ID, using = "SubmitBtn")
			public WebElement bulkSubmitBtn;

			@FindBy(how = How.XPATH, using = "//*[@id=\"alertify\"]/div/article/p")
			public WebElement popUpMessageBulk;


			@FindBy(how = How.XPATH, using = "//*[@id=\"alertify-ok\"]")
			public WebElement okBtnBulk;

}
