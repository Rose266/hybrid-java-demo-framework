package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.RVAdminSideBarUI;

public class AdminSideBarPO extends BasePage {
	WebDriver driver;
	public AdminSideBarPO(WebDriver driver)  {
		this.driver = driver;
	}
	
	public  void openSidebarMenuPageByName (String menuPageName) {
		waitForElementVisible(driver, RVAdminSideBarUI.MENU_PAGE_BY_NAME, menuPageName);
		clickToElement(driver, RVAdminSideBarUI.MENU_PAGE_BY_NAME, menuPageName);
	}
}
