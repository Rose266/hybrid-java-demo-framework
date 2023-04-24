package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.RVAdminLoginPageUI;

public class AdminLoginPagePO extends BasePage {
	WebDriver driver;
	public AdminLoginPagePO (WebDriver driver) {
		this.driver = driver;
	}
	public void inputIntoUserNameTextbox(String adminUserName) {
		waitForElementVisible(driver, RVAdminLoginPageUI.USERNAME_TEXTBOX);
		sendKeysToElement(driver, RVAdminLoginPageUI.USERNAME_TEXTBOX, adminUserName);
	}
	public void inputIntoPasswordTextbox(String adminPass) {
		waitForElementVisible(driver, RVAdminLoginPageUI.PASSWORD_TEXTBOX);
		sendKeysToElement(driver, RVAdminLoginPageUI.PASSWORD_TEXTBOX, adminPass);
	}
	public AdminDashboardPagePO clickToLoginButton() {
		waitForElementClickable(driver, RVAdminLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, RVAdminLoginPageUI.LOGIN_BUTTON);
		return AdminPageGeneratorPO.getRVAdminDashboardPage(driver);
	}
	
	
}
