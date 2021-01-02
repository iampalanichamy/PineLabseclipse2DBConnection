package pine.test.pageObjects;

/* 
 * Author : Manmeet Singh
 * Creation Date : 05-09-2020
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class StorePageObjects {
	
	public StorePageObjects(WebDriver driver){
		
		PageFactory.initElements(driver, this);	
	}
	//HomePage
	//StoreManagement
	@FindBy(how = How.ID, using = "MerchantNodeLi")
	public WebElement MerchManagement;
	
	@FindBy(how = How.XPATH, using = "//li[@id='StoreManagement']/a")
	public WebElement StoreTab;	
	
	@FindBy(how = How.XPATH, using = "//li[@id='StoreManagement']//li/a[text()='Create']")
	public WebElement CreateStore;	
	
	@FindBy(how = How.XPATH, using = "//li[@id='StoreManagement']//li/a[text()='Approve']")
	public WebElement ApproveStore;

	/***************************Store_Selection_Page**************************/
	
	@FindBy(how = How.XPATH, using = "//input[@id='MNameTB']")
	public WebElement SeacrhMerchant;	

	@FindBy(how = How.XPATH, using = "//input[@type='submit'][@id='ContentPlaceHolder1_SaerchMerchantBtn']")
	public WebElement SeacrhMerchantBtn;

	@FindBy(how = How.XPATH, using = "//a[@id='ContentPlaceHolder1_HyperLinkCreateStore']")
	public WebElement CreateStoreBtn;
	
	/***************************Store_Creation_Page**************************/	
	
	@FindBy(how = How.XPATH, using = "//input[@id='ContentPlaceHolder1_TextBoxDisplayNameStorePanel']")
	public WebElement StoreDispName;	
	
	@FindBy(how = How.XPATH, using = "//input[@id='ContentPlaceHolder1_TextBoxStoreDescription']")
	public WebElement StoreDescription;	
	
	@FindBy(how = How.XPATH, using = "//input[@id='ContentPlaceHolder1_TextBoxAddress1StorePanel']")
	public WebElement StoreAddressLine1;	
	
	@FindBy(how = How.XPATH, using = "//select/option[text()='INDIA']")
	public WebElement Country;
	
	@FindBy(how = How.XPATH, using = "//select[@id='ContentPlaceHolder1_ddlState']/option[3]")
	public WebElement State;
	
	@FindBy(how = How.XPATH, using = "//select[@id='ContentPlaceHolder1_ddlCity']/option[9]")
	public WebElement City;
	
	@FindBy(how = How.XPATH, using = "//input[@id='ContentPlaceHolder1_TextBoxPinCodeStorePanel']")
	public WebElement PostalCode;
	
	@FindBy(how = How.XPATH, using = "//div[@class='checkbox']/label[1]/span")
	public WebElement CloudValChckBox;
	
	@FindBy(how = How.ID, using = "TextBoxContactPerson")
	public WebElement StoreFirstContactName;
	
	@FindBy(how = How.ID, using = "TextBoxPhoneNumberStorePanel")
	public WebElement StorePrimaryContactNum;
	
	@FindBy(how = How.ID, using = "MobileNoTB")
	public WebElement StoreMobNum;
	
	@FindBy(how = How.ID, using = "TextBoxEmailIDStorePanel")
	public WebElement StoreEmail;
	
	@FindBy(how = How.ID, using = "ContentPlaceHolder1_ButtonCreateStore")
	public WebElement SaveStoreBtn;
	
	@FindBy(how = How.ID, using = "ContentPlaceHolder1_ClosePopup")
	public WebElement ClosePopup;
	
	@FindBy(how = How.XPATH, using = "//a/span[contains(text(),'Welcome')]")
	public WebElement UserDrpDwn;
	
	@FindBy(how = How.XPATH, using = "//a[@id='Logout']")
	public WebElement LogoutBtn;
	
	/*****************Approve Store Page**************************/
	
	@FindBy(how = How.ID, using = "MNameTB")
	public WebElement MerchantName;
	
	@FindBy(how = How.ID, using = "SearchMerchantBtn")
	public WebElement SearchBtn;
	
	@FindBy(how = How.XPATH, using = "//a[text()='Approve/Reject']")
	public WebElement ApproveRejectBtn;
	
	@FindBy(how = How.ID, using = "ApproveBtn")
	public WebElement ApproveBtn;
	
	@FindBy(how = How.ID, using = "ContentPlaceHolder1_ButtonOKApprove")
	public WebElement SaveBtn;
		
	@FindBy(how = How.ID, using = "ContentPlaceHolder1_HubMessageID")
	public WebElement MerchantId;
	
	//FileManagement
	@FindBy(how = How.ID, using = "FileManagement")
	public WebElement FileManagement;
	
	@FindBy(how = How.XPATH, using = "//li[@id='FileSearchLI']/a[text()='Search']")
	public WebElement FileSearch;		
}
