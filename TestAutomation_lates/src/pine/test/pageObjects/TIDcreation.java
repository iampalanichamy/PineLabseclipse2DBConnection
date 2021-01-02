package pine.test.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class TIDcreation {

	public TIDcreation(WebDriver driver) {
		PageFactory.initElements(driver, this);

	}

	@FindBy(how = How.LINK_TEXT, using = "Manage Terminal")
	public WebElement ManageTerminal;

	@FindBy(how = How.ID, using = "ContentPlaceHolder1_SearchTypeDDL")
	public WebElement Searchtype_M;

	@FindBy(how = How.ID, using = "InputTB")
	public WebElement EnterClientID;

	@FindBy(how = How.XPATH, using = "//*[@id=\'ContentPlaceHolder1_UpdatePanel1\']/div[1]/div[1]/div[3]/div")
	public WebElement ClickSearch;

	@FindBy(how = How.ID, using = "ContentPlaceHolder1_TabContainer1_GridView_PosGV_RegisterBankLB_0")
	public WebElement RegisterTid;
	
	
	@FindBy(how = How.ID, using = "ContentPlaceHolder1_TextBoxBankTIDBankPanel")
	public WebElement EnterBankTID;
	
	
	
	@FindBy(how = How.ID, using = "ContentPlaceHolder1_DropDownBankNameBankPanel")
	public WebElement SelectBank;
	
	
	
	@FindBy(how = How.NAME, using = "ctl00$ContentPlaceHolder1$DDLPurpose")
	public WebElement TIDPurpostype;
	
	
	@FindBy(how = How.ID, using = "ContentPlaceHolder1_DropDownListTerminalType")
	public WebElement TerminalType;
	
	
	
	@FindBy(how = How.ID, using = "ContentPlaceHolder1_ButtonCreateBank")
	public WebElement TIDSaveButton;
	
	
	
	@FindBy(how = How.XPATH, using = "/html/body/section[1]/div/article/p")
	public WebElement TIDPOPUP;
	
	@FindBy(how = How.ID, using = "alertify-ok")
	public WebElement clickOkonPopup;
	
	@FindBy(how = How.ID, using = "ContentPlaceHolder1_TabContainer1_GridView_PosGV_bankTIdCountLB_0")
	public WebElement TidNumbers;
	
	
	@FindBy(how = How.ID, using = "ContentPlaceHolder1_TabContainer1_GridView_TidGV_BlockTIDLB_0")
	public WebElement blocktid;
	
	
	
	@FindBy(how = How.ID, using = "ContentPlaceHolder1_SuccessID")
	public WebElement tidPop;
	
	@FindBy(how = How.ID, using = "ContentPlaceHolder1_ClosePopup")
	public WebElement blocktidSuccessfulPop;
	
	
	
}
