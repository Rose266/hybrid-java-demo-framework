package pageObjects.nopcommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopcommerce.RVUserLoginPageUI;

public class UserLoginPO extends BasePage {
	WebDriver driver;

	public UserLoginPO(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToEmailAddressTextbox(String email) {
		waitForElementVisible(driver, RVUserLoginPageUI.USER_EMAIL_ADDRESS_TEXTBOX);
		sendKeysToElement(driver, RVUserLoginPageUI.USER_EMAIL_ADDRESS_TEXTBOX, email);
	}

	public void inputToPasswordTextbox(String pass) {
		waitForElementVisible(driver, RVUserLoginPageUI.USER_PASSWORD_TEXTBOX);
		sendKeysToElement(driver, RVUserLoginPageUI.USER_PASSWORD_TEXTBOX, pass);
	}

	public UserHomePO clickToLoginButton() {
		waitForElementClickable(driver, RVUserLoginPageUI.USER_LOGIN_BUTTON);
		clickToElement(driver, RVUserLoginPageUI.USER_LOGIN_BUTTON);
		return PageGeneratorPO.getHomePage(driver);
	}
}
