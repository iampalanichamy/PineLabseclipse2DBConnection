package pine.test.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class TerminalCreationObjects {

	public TerminalCreationObjects(WebDriver driver) {
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(how = How.LINK_TEXT, using = "Home")
	public WebElement HomePage;

	@FindBy(how = How.LINK_TEXT, using = "Terminal")
	public WebElement ClcTerminalLink;

	@FindBy(how = How.LINK_TEXT, using = "Create")
	public WebElement ClcCreateLink;

	@FindBy(how = How.ID, using = "MNameTB")
	public WebElement EnterMerchantName;

	@FindBy(how = How.ID, using = "ContentPlaceHolder1_SearchStoreTB")
	public WebElement EnterStoreName;

	@FindBy(how = How.ID, using = "SaerchMerchantBtn")
	public WebElement clickSerachbttn;

	@FindBy(how = How.ID, using = "SaerchMerchantBtn")
	public WebElement ClickSearchButtonforTerminal;

	@FindBy(how = How.LINK_TEXT, using = "Create Terminal")
	public WebElement ClickTerminalCreate;

	@FindBy(how = How.ID, using = "hardwareTypeDDL")
	public WebElement HardwareType;
	// ICT220-INGENICO

	@FindBy(how = How.ID, using = "ContentPlaceHolder1_SubTypeVersionDDL")
	public WebElement HardwareSubVersionType;
	// ICT220-11T3621A

	@FindBy(how = How.ID, using = "ContentPlaceHolder1_TextBoxChunkSizePOS")
	public WebElement ChunkSize;

	@FindBy(how = How.XPATH, using = "//select[@id='ContentPlaceHolder1_PVMDDL']/option[9]")
	public WebElement PVM;
	
	// 01022015_1_BFL_New.xml

	@FindBy(how = How.ID, using = "ContentPlaceHolder1_PonEDCDDL")
	public WebElement Printer;
	// EDC Printer

	@FindBy(how = How.ID, using = "ContentPlaceHolder1_DPWidth")
	public WebElement PrinterWidth;
	// 48

	@FindBy(how = How.ID, using = "ContentPlaceHolder1_HIdTB")
	public WebElement HardwareSerialNumber;
	// 233331222

	@FindBy(how = How.ID, using = "TB_LMSId")
	public WebElement LMSID;

	@FindBy(how = How.ID, using = "Btn_ValidateLMSID")
	public WebElement ClickValidateLMSID;

	@FindBy(how = How.XPATH, using = "/html/body/section[1]/div/article/p")
	public WebElement PopUpXpath;

	@FindBy(how = How.ID, using = "alertify-ok")
	public WebElement clickOkonPopup;

	@FindBy(how = How.ID, using = "ContentPlaceHolder1_ButtonCreatePOS")
	public WebElement CLickCreateButton;

	@FindBy(how = How.XPATH, using = "/html/body/form/div[2]/div[1]/div[11]/div/div/div/div[1]/div/div[1]/span")
	public WebElement TerminalCreationSuccessPopuptext;

	@FindBy(how = How.ID, using = "ContentPlaceHolder1_ClosePopup")
	public WebElement CloseterminalSuccesspop;

	
	
	@FindBy(how = How.ID, using = "ContentPlaceHolder1_JioMoneyTidTextBox")
	public WebElement Jiomey;
	
	@FindBy(how = How.ID, using = "ContentPlaceHolder1_JioMoneyMidTextBox")
	public WebElement Jiomey2;
	
	
}
