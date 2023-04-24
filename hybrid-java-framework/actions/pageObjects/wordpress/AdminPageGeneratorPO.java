package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

public class AdminPageGeneratorPO {
	public static AdminDashboardPagePO getRVAdminDashboardPage(WebDriver driver) {
		return new AdminDashboardPagePO(driver);
	}

	public static AdminLoginPagePO getRVAdminLoginPage(WebDriver driver) {
		return new AdminLoginPagePO(driver);
	}

	public static AdminSideBarPO getRVAdminSideBar(WebDriver driver) {
		return new AdminSideBarPO(driver);
	}

	public static AdminUsersPagePO getRVAdminUsersPage(WebDriver driver) {
		return new AdminUsersPagePO(driver);
	}

}
