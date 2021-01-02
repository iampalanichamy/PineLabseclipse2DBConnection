package pine.test.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class File_ManagementObject {
	
	public File_ManagementObject(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	//Miscellaneous Tab
	
	@FindBy(how = How.XPATH, using = "//span[text()='File Management']")
	public WebElement FileMgmt; 
	//File Upload
	
	//@FindBy(how = How.ID, using = "FileSearchLI")
	//public WebElement File_Search;
	@FindBy(how = How.LINK_TEXT, using = "Search")
	public WebElement File_Search;
	
	@FindBy(how = How.XPATH, using = "//div//select[@id='FileTypeRadioButton']")
	public WebElement FileType; 
	
	@FindBy(how = How.ID, using = "SearchCategory")
	public WebElement Search_Category; 
	
	@FindBy(how = How.ID, using = "ContentPlaceHolder1_TextFileName")
	public WebElement File_name;
	
	@FindBy(how = How.ID, using = "Button1")
	public WebElement Search;
	
	@FindBy(how = How.ID, using = "ContentPlaceHolder1_TextTerminalId")
    public  WebElement FileSearch_POSId;
    
    @FindBy(how = How.ID, using = "ContentPlaceHolder1_Gridview1_HyperLink99_0")
    public  WebElement Association;

    @FindBy(id = "SearchCategory")
    public  WebElement Association_SearchType;

    @FindBy(id = "TextTerminalId")
    public  WebElement Association_POSId;
    
    @FindBy(id = "ContentPlaceHolder1_TextFileName")
    public WebElement Association_FileName;

    @FindBy(id = "ContentPlaceHolder1_Button3")
    public  WebElement Association_Search;

    @FindBy(id = "TerminalListBox1")
    public  WebElement POSSelection;

    @FindBy(id = "ContentPlaceHolder1_AssociationTerminal")
    public  WebElement ApplyAssociation;
        
    @FindBy(how = How.XPATH, using = "//*[@id=\"alertify\"]/div/article/p")
    public WebElement associationPopUpMessage;
    
    @FindBy(how = How.ID, using = "alertify-ok")
    public  WebElement okButton;

}
