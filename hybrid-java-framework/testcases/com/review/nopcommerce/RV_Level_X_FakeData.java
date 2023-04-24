package com.review.nopcommerce;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.nopcommerce.PageGeneratorPO;
import pageObjects.nopcommerce.UserCustomerInfoPO;
import pageObjects.nopcommerce.UserHomePO;
import pageObjects.nopcommerce.UserLoginPO;
import pageObjects.nopcommerce.UserMyProductReviewPO;
import pageObjects.nopcommerce.UserOrderPO;
import pageObjects.nopcommerce.UserRegisterPO;
import pageObjects.nopcommerce.UserRewardPointsPO;
import review.utilities.RVDataHelper;

public class RV_Level_X_FakeData extends BaseTest {
	@Parameters({"browser","userUrl"})
	@BeforeClass
	public void beforeClass(String browserName, String userUrl) {
		driver = getBrowserDriver(browserName,userUrl);
		datahelper = RVDataHelper.getRVDataHelper();
		firstName =  datahelper.getFirstName();
		lastName = datahelper.getLastName();
		dobDay = "4";
		dobMonth = "June";
		dobYear = "1996";
		pass = datahelper.getPassword();
		email = datahelper.getEmail();
		System.out.println(email);
		companyName = datahelper.getCompany();
		
	}

	@Test (invocationCount = 3)
	public void User_01_Register() {
		log.info("HomePage - Step 01: Initiate HomePage");
		homePage = PageGeneratorPO.getHomePage(driver);
		
		log.info("RegisterPage - Step 02: Navigate to Register Page");
		registerPage = homePage.openRegisterPage();
		
		log.info("RegisterPage - Step 03: Click to radio button [Gender] with value = 'female'");
		registerPage.clickToGenderFemaleRadio();
		
		log.info("RegisterPage - Step 04: Input into [First Name] textbox with value ='" + firstName + "'");
		registerPage.inputToFirstNameTextbox(firstName);
		
		log.info("RegisterPage - Step 05: Input into [Last Name]  textbox with value ='" + lastName + "'");
		registerPage.inputToLastNameTextbox(lastName);
		
		log.info("RegisterPage - Step 06: Select day of birthday in dropdown list");
		registerPage.selectDayDropdown(dobDay);
		
		log.info("RegisterPage - Step 07: Select month of birthday in dropdown list");
		registerPage.selectMonthDropdown(dobMonth);
		
		log.info("RegisterPage - Step 08: Select year of birthday in dropdown list");
		registerPage.selectYearDropdown(dobYear);
		
		log.info("RegisterPage - Step 09: Input into [Email] textbox with value = '" + email + "'");
		registerPage.inputToEmailAddressTextbox(email);
		
		log.info("RegisterPage - Step 10: Input into [Company Name] textbox with value = '" + companyName + "'");
		registerPage.inputToCompanyTextbox(companyName);
		
		log.info("RegisterPage - Step 11: Input into [Password] textbox with value = '" + pass + "'");
		registerPage.inputToPasswordTextbox(pass);

		log.info("RegisterPage - Step 12: Input into [Confirm Password] textbox with value = '" + pass + "'");
		registerPage.inputToConfirmPasswordTextbox(pass);
		
		log.info("RegisterPage - Step 13: Click to [Register] button");
		registerPage.clickToRegisterButton();
		
		log.info("RegisterPage - Step 14: Verify register success message");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		log.info("RegisterPage - Step 15: Navigate to Home Page");
		homePage = registerPage.clickToContinueButton();
	}

	@Test
	public void User_02_Login() {
		log.info("HomePage - Step 01: Click to Login link to navigate");
		loginPage = homePage.openLoginPage();
		
		log.info("LoginPage - Step 02: Input into [Email] textbox with value = '" + email + "'");
		loginPage.inputToEmailAddressTextbox(email);
		
		log.info("LoginPage - Step 03: Input into [Password] textbox with value = '" + pass +"'");
		loginPage.inputToPasswordTextbox(pass);
		
		log.info("LoginPage - Step 04: Click to [Login] button");
		homePage = loginPage.clickToLoginButton();
		
		log.info("HomePage - Step 05: Verify [My Account] display");
		verifyTrue(homePage.isMyAccountLinkDisplay());
		
		
	}
	
	

	@AfterClass
	public void afterClass() {

	}
	
	UserOrderPO orderPage;
	UserMyProductReviewPO myProductReviewPage;
	UserRewardPointsPO rewardPointsPage;
	RVDataHelper datahelper;
	WebDriver driver;
	UserHomePO homePage;
	UserLoginPO loginPage;
	UserRegisterPO registerPage;
	UserCustomerInfoPO customerInfoPage;
	String firstName, lastName, dobDay, dobMonth, dobYear, email, pass, companyName;
	

}

