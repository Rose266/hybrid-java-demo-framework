package pageObjects.nopcommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopcommerce.RVUserRegisterPageUI;

public class UserRegisterPO extends BasePage {
	WebDriver driver;

	public UserRegisterPO(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToGenderFemaleRadio() {
		waitForElementClickable(driver, RVUserRegisterPageUI.GENDER_FEMALE_RADIOBUTTON);
		clickToElement(driver, RVUserRegisterPageUI.GENDER_FEMALE_RADIOBUTTON);
	}
	
	public void inputToFirstNameTextbox(String firstName) {
		waitForElementVisible(driver, RVUserRegisterPageUI.FIRSTNAME_TEXTBOX);
		sendKeysToElement(driver, RVUserRegisterPageUI.FIRSTNAME_TEXTBOX, firstName);
	}
	
	public void inputToLastNameTextbox(String lastName) {
		waitForElementVisible(driver, RVUserRegisterPageUI.LASTNAME_TEXTBOX);
		sendKeysToElement(driver, RVUserRegisterPageUI.LASTNAME_TEXTBOX, lastName);
	}
	
	public void selectDayDropdown(String day) {
		waitForElementClickable(driver, RVUserRegisterPageUI.DAY_DROPDOWN);
		selectItemInDropDown(driver, RVUserRegisterPageUI.DAY_DROPDOWN, day);
	}
	
	public void selectMonthDropdown(String month) {
		waitForElementClickable(driver, RVUserRegisterPageUI.MONTH_DROPDOWN);
		selectItemInDropDown(driver, RVUserRegisterPageUI.MONTH_DROPDOWN, month);
	}

	public void selectYearDropdown(String year) {
		waitForElementClickable(driver, RVUserRegisterPageUI.YEAR_DROPDOWN);
		selectItemInDropDown(driver, RVUserRegisterPageUI.YEAR_DROPDOWN,year );
	}
	
	public void inputToEmailAddressTextbox(String emailAddress) {
		waitForElementVisible(driver, RVUserRegisterPageUI.EMAIL_ADDRESS_TEXTBOX);
		sendKeysToElement(driver, RVUserRegisterPageUI.EMAIL_ADDRESS_TEXTBOX, emailAddress);
	}

	public void inputToCompanyTextbox(String companyName) {
		waitForElementVisible(driver, RVUserRegisterPageUI.COMPANY_TEXTBOX);
		sendKeysToElement(driver, RVUserRegisterPageUI.COMPANY_TEXTBOX, companyName);
	}
	public void inputToConfirmPasswordTextbox(String pass) {
		waitForElementVisible(driver, RVUserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendKeysToElement(driver, RVUserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, pass);
	}

	public void inputToPasswordTextbox(String pass) {
		waitForElementVisible(driver, RVUserRegisterPageUI.PASSWORD_TEXTBOX);
		sendKeysToElement(driver, RVUserRegisterPageUI.PASSWORD_TEXTBOX, pass);
	}

	public void clickToRegisterButton() {
		waitForElementClickable(driver, RVUserRegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RVUserRegisterPageUI.REGISTER_BUTTON);
	}

	public String getRegisterSuccessMessage() {
		waitForElementVisible(driver, RVUserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
		return getElementText(driver, RVUserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
	}

	public UserHomePO clickToContinueButton() {
		waitForElementClickable(driver, RVUserRegisterPageUI.CONTINUE_BUTTON);
		clickToElement(driver, RVUserRegisterPageUI.CONTINUE_BUTTON);
		return PageGeneratorPO.getHomePage(driver);
	}
}
