package pageObjects.nopcommerce;

import org.openqa.selenium.WebDriver;

public class PageGeneratorPO {
	public static UserHomePO getHomePage(WebDriver dirver) {
		return new UserHomePO(dirver);
	}

	public static UserLoginPO getLoginPage(WebDriver driver) {
		return new UserLoginPO(driver);
	}

	public static UserCustomerInfoPO getCustomerInfoPage(WebDriver driver) {
		return new UserCustomerInfoPO(driver);
	}

	public static UserRegisterPO getRegisterPage(WebDriver driver) {
		return new UserRegisterPO(driver);
	}
	
	public static UserOrderPO getOrderPage(WebDriver driver) {
		return new UserOrderPO(driver);
	}
	
	public static UserMyProductReviewPO getMyProductReviewPage(WebDriver driver) {
		return new UserMyProductReviewPO(driver);
	}
	
	public static UserRewardPointsPO getRewardPointPage(WebDriver driver) {
		return new UserRewardPointsPO(driver);
	}
	
	public static AdminDashBoardPO getAdminDashBoardPage(WebDriver driver) {
		return new AdminDashBoardPO(driver);
	}
	
	public static AdminLoginPO getAdminLoginPage (WebDriver driver) {
		return new AdminLoginPO(driver);
	}
	
	public static UserAddressPO getUserAddressPage (WebDriver driver) {
		return new UserAddressPO(driver);
	}
}
