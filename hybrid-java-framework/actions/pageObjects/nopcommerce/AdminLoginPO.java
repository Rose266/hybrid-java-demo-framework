package pageObjects.nopcommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopcommerce.RVAdminLoginPageUI;

public class AdminLoginPO extends BasePage {
	WebDriver driver;
	public AdminLoginPO (WebDriver driver) {
		this.driver = driver;
	}
	
	public void inputToEmailTextbox( String adminAccount) {
		waitForElementVisible(driver, RVAdminLoginPageUI.ADMIN_EMAIL_TEXTBOX);
		sendKeysToElement(driver, RVAdminLoginPageUI.ADMIN_EMAIL_TEXTBOX, adminAccount);
	}
	
	public void inputToPasswordTextbox ( String pass) {
		waitForElementClickable(driver, RVAdminLoginPageUI.ADMIN_PASSWORD_TEXTBOX);
		sendKeysToElement(driver, RVAdminLoginPageUI.ADMIN_PASSWORD_TEXTBOX, pass);
	}
	
	public AdminDashBoardPO clickToLoginButton () {
		waitForElementClickable(driver, RVAdminLoginPageUI.ADMIN_LOGIN_BUTTON);
		clickToElement(driver, RVAdminLoginPageUI.ADMIN_LOGIN_BUTTON);
		return PageGeneratorPO.getAdminDashBoardPage(driver);
	}
	
	
}
