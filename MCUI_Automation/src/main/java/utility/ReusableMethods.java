package utility;

import java.io.File;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;


public class ReusableMethods {

	
	public String GetCaptchaText(WebElement element) {
		String captcha_val = null;
		try {
			File newFile = WebElementExtender.captureElementPicture(element);
			FileHandler.copy(newFile, new File(System.getProperty("user.dir")+ "/Captcha.png"));
			ITesseract tess = new Tesseract();
			tess.setDatapath(System.getProperty("user.dir"));
			//tess.setPageSegMode(7);
			tess.setTessVariable("tessedit_char_whitelist", "0123456789");
			tess.setTessVariable("user_defined_dpi", "300");
			
			captcha_val = tess.doOCR(newFile);
			Thread.sleep(3000);
			
			System.out.println("CAPTCHA: " + captcha_val);
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
			e.printStackTrace();
		}
		return captcha_val;

	}
}
