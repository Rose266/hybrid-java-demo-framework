package pageObjects.nopcommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopcommerce.RVUserHomePageUI;

public class UserHomePO extends BasePage {
	WebDriver driver;

	public UserHomePO(WebDriver driver) {
		this.driver = driver;
	}

	public UserRegisterPO openRegisterPage() {
		sleepInSeconds(10);
		waitForElementClickable(driver, RVUserHomePageUI.REGISTER_LINK);
		clickToElement(driver, RVUserHomePageUI.REGISTER_LINK);
		return PageGeneratorPO.getRegisterPage(driver);
	}

	public UserLoginPO openLoginPage() {
		waitForElementClickable(driver, RVUserHomePageUI.LOGIN_LINK);
		clickToElement(driver, RVUserHomePageUI.LOGIN_LINK);
		return PageGeneratorPO.getLoginPage(driver);
	}

	public UserCustomerInfoPO openCustomerInfoPage() {
		waitForElementClickable(driver, RVUserHomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, RVUserHomePageUI.MY_ACCOUNT_LINK);
		return PageGeneratorPO.getCustomerInfoPage(driver);
	}

	public boolean isMyAccountLinkDisplay() {
		return isElementDisplayed(driver, RVUserHomePageUI.MY_ACCOUNT_LINK);
	}
}
