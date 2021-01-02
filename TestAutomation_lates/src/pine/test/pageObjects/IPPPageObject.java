package pine.test.pageObjects;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.utils.DateTimeUtil;

public class IPPPageObject {
	private WebDriver driver;
	
	public IPPPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(how = How.ID, using="IPP")
	public WebElement IPPPage;
	
	@FindBy(how = How.XPATH, using="//li[@id='IPP']/ul/li/a/span[text()='Tools']")
	public WebElement IPPTools;
	
	@FindBy(how = How.XPATH, using="//a[@href='AddOnRulePage.aspx']")
	public WebElement RuleGenerator;
	
	@FindBy(how = How.XPATH, using="//label[@for='Chk_PaymentController']")
	public WebElement PaymentControllerChkBox;
	
	@FindBy(how = How.XPATH, using="//label[@for='RuleGeneratorRBL_Create']")
	public WebElement CreateChkBox;
	
	@FindBy(how = How.XPATH, using="//span[contains(text(),'Rule Approver Properties')]")
	public WebElement RuleApproverPropText;
	
	@FindBy(how = How.ID, using="ContentPlaceHolder1_ProgramTypeTV")
	public WebElement ProgramTypeDrpDwn;
	//--BRAND_OFFUS_IPP
	
	@FindBy(how = How.XPATH, using="//button[@class='btn btn-red1 pull-right']")
	public WebElement AddProgramBtn;
	
	@FindBy(how = How.ID, using="ContentPlaceHolder1_DepartmentDDL")
	public WebElement selectDepartment;
	
	@FindBy(how = How.ID, using="ContentPlaceHolder1_ApprovalUserByDepartmentLB")
	public WebElement selectApprover;
	
	@FindBy(how = How.XPATH, using="//input[@name='ctl00$ContentPlaceHolder1$RuleNameTB']")
	public WebElement ruleName;

	@FindBy(how = How.ID, using="RuleStartHrDTPickerTB")
	public WebElement ruleStartHr;
	
	@FindBy(how = How.ID, using="RuleEndHrDTPickerTB")
	public WebElement ruleEndHr;
	
	@FindBy(how = How.XPATH, using="//b[@id='BRAND_OFFUS_IPP_PT']")
	public WebElement BrandOffUsIPP;
	
	@FindBy(how = How.XPATH, using="//ol[@id='ContentPlaceHolder1_custom_menu_ol']/li[@id='AddSchema']")
	public WebElement AddSchema;
	//table[@class='ui-datepicker-calendar']/tbody/tr/td[@class=' ui-datepicker-days-cell-over  ui-datepicker-today']
	@FindBy(how = How.XPATH, using="//td[@class=' ui-datepicker-days-cell-over  ui-datepicker-today']")
	public WebElement datePickerToday;
	
	@FindBy(how = How.ID, using="ui-datepicker-year")
	public WebElement datePickerYear;
	
	@FindBy(how = How.ID, using="ui-datepicker-div")
	public WebElement datePicker;
	
	@FindBy(how = How.ID, using="(//input[@class='ui-timepicker-input ui-spinner-input'])[1]")
	public WebElement datePickerHour;
	
	@FindBy(how = How.ID, using="(//td[@data-year='2029'])[1]")
	public WebElement datePickerEndYear;
	

	public void CreateAddonRule() throws InterruptedException {
		
		IPPPage.click();
		Thread.sleep(1000);
		IPPTools.click();
		Thread.sleep(1000);
		RuleGenerator.click();
		
		PaymentControllerChkBox.click();
		CreateChkBox.click();
		
		Select selectProgramType = new Select(ProgramTypeDrpDwn);
		selectProgramType.selectByVisibleText("BRAND_OFFUS_IPP");
		
		AddProgramBtn.click();
		
		Select selectDept = new Select(selectDepartment);
		selectDept.selectByVisibleText("Finance");
		
		Select selectAppver = new Select(selectApprover);
		selectAppver.selectByIndex(0);
		Thread.sleep(5000);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		LocalDate todayDate = LocalDate.now();
		LocalDate endingDate = todayDate.plusYears(5);
		String startDate = todayDate.format(formatter);
		String endDate = endingDate.format(formatter);
	
		System.out.println(startDate+": "+endDate);
		formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		String currentTime = LocalTime.now().format(formatter);
		System.out.println("CurrentTime:" +currentTime);
		String ruleStartHour = startDate+" "+currentTime;
		String ruleEndHour = endDate+" "+currentTime;
		
		ruleName.sendKeys("BrandOffusIPP_"+startDate);
		Thread.sleep(1000);

		ruleStartHr.sendKeys(ruleStartHour);
		Thread.sleep(1000);
		//datePickerHour.clear();
		datePickerHour.sendKeys("23");
		datePickerToday.click();
		//ruleStartHr.click();
		
		ruleEndHr.sendKeys(ruleEndHour);
		Select selectEndYear = new Select(datePickerYear);
		
		selectEndYear.selectByValue("2029");
		datePickerEndYear.click();
		
		Actions action = new Actions(driver);
		
		action.contextClick(BrandOffUsIPP).perform();
		AddSchema.click();
		
		
		
	}
}
