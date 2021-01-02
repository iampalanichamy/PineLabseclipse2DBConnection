package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObjects {
	
	public LoginPageObjects(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how=How.XPATH, using ="//a[text()='Login as PineLabs Administrator']")
	public WebElement adminLoginBtn;

	
	@FindBy(how=How.ID, using ="UserNameTB")
	public WebElement userBox;

	@FindBy(how=How.ID, using ="PwdTB")
	public WebElement pwdBox;
	
	@FindBy(how=How.XPATH, using ="//img[contains(@src,'Captcha.aspx')]")
	public WebElement captchaImg;
	
	@FindBy(how=How.ID, using ="CaptchaText")
	public WebElement captchaTxt;
	
	@FindBy(how=How.ID, using ="btnSubmit")
	public WebElement lgnBtn;
	
	@FindBy(how=How.XPATH, using ="//a[@href='/Home/Logout']")
	public WebElement logoutBtn;
	
}
