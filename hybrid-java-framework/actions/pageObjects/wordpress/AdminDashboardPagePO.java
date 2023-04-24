package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

public class AdminDashboardPagePO extends AdminSideBarPO{
	WebDriver driver;
	public AdminDashboardPagePO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
}
