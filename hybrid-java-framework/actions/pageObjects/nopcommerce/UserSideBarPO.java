package pageObjects.nopcommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopcommerce.RVUserSideBarPageUI;

public class UserSideBarPO extends BasePage {
	WebDriver driver;
	public UserSideBarPO(WebDriver driver){
		this.driver = driver;
	}

	public UserOrderPO openOrderPage() {
		waitForElementClickable(driver, RVUserSideBarPageUI.ORDERS_LINK_AT_A_NAVIGATION_SIDEBAR);
		clickToElement(driver, RVUserSideBarPageUI.ORDERS_LINK_AT_A_NAVIGATION_SIDEBAR);
		return PageGeneratorPO.getOrderPage(driver);
	}

	public UserRewardPointsPO openRewardPointsPage() {
		waitForElementClickable(driver, RVUserSideBarPageUI.REWARD_POINTS_LINK_AT_A_NAVIGATION_SIDEBAR);
		clickToElement(driver, RVUserSideBarPageUI.REWARD_POINTS_LINK_AT_A_NAVIGATION_SIDEBAR);
		return PageGeneratorPO.getRewardPointPage(driver);
	}

	public UserMyProductReviewPO openMyProductReviewPage() {
		waitForElementClickable(driver, RVUserSideBarPageUI.MY_PRODUCT_REVIEWS_AT_A_NAVIGATION_SIDEBAR);
		clickToElement(driver, RVUserSideBarPageUI.MY_PRODUCT_REVIEWS_AT_A_NAVIGATION_SIDEBAR);
		return PageGeneratorPO.getMyProductReviewPage(driver);
	}
	
	public void openSideBarPageByName(String pageName) {
		waitForElementVisible(driver,RVUserSideBarPageUI.DYNAMIC_PAGE_AT_A_NAVIGATION_SIDEBAR_BY_PAGE__NAME,pageName);
		clickToElement(driver, RVUserSideBarPageUI.DYNAMIC_PAGE_AT_A_NAVIGATION_SIDEBAR_BY_PAGE__NAME, pageName);
	}
}
