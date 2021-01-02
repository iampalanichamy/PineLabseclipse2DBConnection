package pine.test.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class UserManagementObjects {
public UserManagementObjects(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}

@FindBy(how = How.XPATH, using = "//span[text()='User Management']")
public WebElement Users_Mangmt; 	

@FindBy(how = How.XPATH, using = "//span[text()='Users']")
public WebElement Users; 

@FindBy(how = How.XPATH, using = "//a[text()='Search User']")
public WebElement Search_User;

@FindBy(how = How.ID, using = "ContentPlaceHolder1_USerSearchDDL")
public WebElement Search_type;

@FindBy(how = How.ID, using = "UserSearchTB")
public WebElement Search_key;

@FindBy(how = How.ID, using = "ContentPlaceHolder1_SearchUserBtn")
public WebElement Search_Btn;

@FindBy(how = How.ID, using = "MasterPageContentBody")
public WebElement PopUpMessage;

@FindBy(how = How.ID, using = "alertify-ok")
public  WebElement okButton;

}
