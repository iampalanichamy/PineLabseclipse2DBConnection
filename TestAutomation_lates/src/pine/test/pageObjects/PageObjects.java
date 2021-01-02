package pine.test.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class PageObjects {

	//private WebDriver driver;
	
	public PageObjects(WebDriver driver){
		
		PageFactory.initElements(driver, this);
		
	}
	
	//HomePage
	
	//MerchantManagement
	@FindBy(how = How.ID, using = "MerchantNodeLi")
	public WebElement MerchManagement;
	
	@FindBy(how = How.XPATH, using = "//li[@id='MerchantManagement']/a/span[text()='Merchant']")
	public WebElement MerchantTab;	
	
	
	@FindBy(how = How.XPATH, using = "//li[@id='MerchantManagement']/ul//li/a[text()='Create Merchant']")
	public WebElement CreateMerchant;	
	
	@FindBy(how = How.XPATH, using = "	//a[text()='Approve Merchant']")
	public WebElement ApproveMerchant;

	/***************************MerchantPage**************************/
	
	@FindBy(how = How.XPATH, using = "//input[@id='TextBoxDisplayNameIDMerchantName']")
	public WebElement MerchantDispName;	
	
	@FindBy(how = How.XPATH, using = "//input[@id='TextBoxaddress1IDMerchantPanel']")
	public WebElement AddressLine1;	
	
	@FindBy(how = How.XPATH, using = "//select/option[text()='INDIA']")
	public WebElement Country;
	
	@FindBy(how = How.XPATH, using = "//select[@id='ddlState']/option[text()='Uttar Pradesh']")
	public WebElement State;
	
	@FindBy(how = How.XPATH, using = "//select[@id='ddlCity']/option[contains(text(),'NOIDA')]")
	public WebElement City;
	
	@FindBy(how = How.XPATH, using = "//input[@id='TextBoxpinIDMerchantPanel']")
	public WebElement PostalCode;
	
	@FindBy(how = How.ID, using = "TB_PAN")
	public WebElement merchPAN;
	
	@FindBy(how = How.XPATH, using = "//div[@class='checkbox']/label[1]/span")
	public WebElement CloudValChckBox;
	
	@FindBy(how = How.ID, using = "MerchantPrimaryContactName")
	public WebElement MerchPrimaryContactName;
	
	@FindBy(how = How.ID, using = "TextBoxphone_numberIDMerchantPanel")
	public WebElement MerchPrimaryContactNum;
	
	@FindBy(how = How.ID, using = "TextBoxmobile_numberIDMerchantPanel")
	public WebElement MerchMobNum;
	
	@FindBy(how = How.ID, using = "TextBoxemailIDMerchantPanel")
	public WebElement MerchEmail;
	
	@FindBy(how = How.ID, using = "ButtonCreateMerchant")
	public WebElement CreateMerchantBtn;
	
	@FindBy(how = How.XPATH, using = "//div[@class='col-sm-12']/span[@id='ContentPlaceHolder1_HubMessageID']")
	public WebElement HubMessageMerchant;
	//Merchant Display Name Already Requested By Some Other Merchant.
	//Merchant Created Successfully and Request has been sent for Approval.Lat Long could not be updated.
	
	@FindBy(how = How.XPATH, using = "//span[@id='ContentPlaceHolder1_ExistingMerchantWithSamePANDetails']")
	public WebElement ExistingMerchantWithSamePAN;//Entered PAN or LMS ID value already exists for approval with following merchant details
	
	
	@FindBy(how = How.ID, using = "ContentPlaceHolder1_ClosePopup")
	public WebElement ClosePopup;
	
	@FindBy(how = How.XPATH, using = "//a/span[contains(text(),'Welcome')]")
	public WebElement UserDrpDwn;
	
	@FindBy(how = How.XPATH, using = "//a[@id='Logout']")
	public WebElement LogoutBtn;
	
	/*****************Approve Merchant Page**************************/
	
	@FindBy(how = How.ID, using = "MNameTB")
	public WebElement MerchantName;
	
	@FindBy(how = How.ID, using = "SaerchMerchantBtn")
	public WebElement SearchBtn;
	
	@FindBy(how = How.XPATH, using = "//a[text()='Approve/Reject']")
	public WebElement ApproveRejectBtn;
	
	@FindBy(how = How.ID, using = "help_helpLbl")
	public WebElement helpText;
	
	@FindBy(how = How.ID, using = "ApproveBtn")
	public WebElement ApproveBtn;
	
	@FindBy(how = How.ID, using = "ContentPlaceHolder1_ButtonOKApprove")
	public WebElement SaveBtn;
	
	
	@FindBy(how = How.ID, using = "ContentPlaceHolder1_HubMessageID")
	public WebElement MerchantId;
	
	//FileManagement
	@FindBy(how = How.ID, using = "FileManagement")
	public WebElement FileManagement;
	
	
		
}
