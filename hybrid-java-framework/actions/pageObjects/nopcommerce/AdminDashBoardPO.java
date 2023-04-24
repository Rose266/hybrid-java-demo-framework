package pageObjects.nopcommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class AdminDashBoardPO extends BasePage {
	WebDriver driver;
	public AdminDashBoardPO(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isDashboarLinkDisplayed() {
		return isElementDisplayed(driver, pageUIs.nopcommerce.RVAdminDashboardPageUI.ADMIN_DASHBOARD_LINK_AT_SIDE_BAR);
	}
}
