package pine.test.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class loginPageObjects {
	
	//private WebDriver driver;
	
	public loginPageObjects (WebDriver driver) {
		// TODO Auto-generated constructor stub
		//this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//LoginPage
		@FindBy(how = How.NAME, using = "UserNameTB")
		public WebElement lgnId; 
		
		@FindBy(how = How.NAME, using = "PwdTB")
		public WebElement password;
		
		@FindBy(how = How.XPATH, using = "//img[@src='Captcha.aspx']")
		public WebElement captchaImg;
		
		
		@FindBy(how = How.NAME, using = "CaptchaText")
		public WebElement captchaTxt;
		
		@FindBy(how = How.NAME, using = "logindiv")
		public WebElement loginBtn;


}
