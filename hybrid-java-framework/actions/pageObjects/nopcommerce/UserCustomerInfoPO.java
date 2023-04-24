
package pageObjects.nopcommerce;

import org.openqa.selenium.WebDriver;

import pageUIs.nopcommerce.RVUserCustomerInfoPageUI;

public class UserCustomerInfoPO extends UserSideBarPO {
	WebDriver driver;
	
	
	public UserCustomerInfoPO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}


	public boolean isGenderFemaleRadioSelected() {
		waitForElementSelected(driver, RVUserCustomerInfoPageUI.FEMALE_GENDER_RADIO_BUTTON);
		return isElementSelected(driver, RVUserCustomerInfoPageUI.FEMALE_GENDER_RADIO_BUTTON);
	}


	public String getFirstNameTextboxAttribute() {
		waitForElementVisible(driver, RVUserCustomerInfoPageUI.FIRSTNAME_TEXTBOX);
		return getElementAttribute(driver, RVUserCustomerInfoPageUI.FIRSTNAME_TEXTBOX	, "value");
	}


	public String getLastNameTextboxAttribute() {
		waitForElementVisible(driver, RVUserCustomerInfoPageUI.LASTNAME_TEXTBOX);
		return getElementAttribute(driver, RVUserCustomerInfoPageUI.LASTNAME_TEXTBOX, "value");
	}


	public String getDayDropdownSelectedItem() {
		waitForElementVisible(driver, RVUserCustomerInfoPageUI.DAY_DROPDOWN);
		return getSlectedOptionInDropdown(driver, RVUserCustomerInfoPageUI.DAY_DROPDOWN);
	}


	public String getMonthDropdownSelectedItem() {
		waitForElementVisible(driver, RVUserCustomerInfoPageUI.MONTH_DROPDOWN);
		return getSlectedOptionInDropdown(driver, RVUserCustomerInfoPageUI.MONTH_DROPDOWN);
	}


	public String getYearDropdownSelectedItem() {
		waitForElementVisible(driver, RVUserCustomerInfoPageUI.YEAR_DROPDOWN);
		return getSlectedOptionInDropdown(driver, RVUserCustomerInfoPageUI.YEAR_DROPDOWN);
	}


	public String getEmailAddressTextboxAttribute() {
		waitForElementVisible(driver, RVUserCustomerInfoPageUI.EMAIL_ADDRESS_TEXTBOX);
		return getElementAttribute(driver, RVUserCustomerInfoPageUI.EMAIL_ADDRESS_TEXTBOX, "value");
	}


	public String getCompanyTextboxAttribute() {
		waitForElementVisible(driver, RVUserCustomerInfoPageUI.COMPANY_TEXTBOX);
		return getElementAttribute(driver, RVUserCustomerInfoPageUI.COMPANY_TEXTBOX, "value");
	}
	
		
}
