package pine.test.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Manage_TerminalObjects {
public Manage_TerminalObjects(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
@FindBy(how = How.XPATH, using = "//span[text()='File Management']")
public WebElement FileMgmtd1; 	

@FindBy(how = How.XPATH, using = "//a[text()='Manage Terminal']")
    public WebElement Manage_Terminal;

   @FindBy(how = How.XPATH, using = "//div//select[@id='ContentPlaceHolder1_SearchTypeDDL']")
	public WebElement Search_Type;
	
	@FindBy(how = How.ID, using = "InputTB")
	public WebElement Search_key;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Search')]")
	public WebElement Search_Btn;

	@FindBy(how = How.ID, using = "ContentPlaceHolder1_TabContainer1_GridView_PosGV_ClintIdLB_0")
	public WebElement ClientID;
	
	@FindBy(how = How.XPATH, using = "//div//input[@id='UpdateBtn']")
	public WebElement Update_Btn;
	
	@FindBy(how = How.XPATH, using = "//div//input[@id='ContentPlaceHolder1_CloseBtn']")
	public WebElement close_Btn;
	
	@FindBy(how = How.ID, using = "ContentPlaceHolder1_HubMessageID")
    public WebElement PopUpMessage;
	
	 @FindBy(how = How.ID, using = "ContentPlaceHolder1_ClosePopup")
	  public  WebElement okButton;
	 
	 @FindBy(how = How.XPATH, using = "//a[text()='Block']")
	 public WebElement Block;
	 
	 @FindBy(how = How.XPATH, using = "//a[text()='Activate']")
	 public WebElement Activate;
	 
	 @FindBy(how = How.XPATH, using = "//a[text()='Delete']")
	 public WebElement Delete;
	 }
