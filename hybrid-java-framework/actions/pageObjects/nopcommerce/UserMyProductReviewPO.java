package pageObjects.nopcommerce;

import org.openqa.selenium.WebDriver;

public class UserMyProductReviewPO extends UserSideBarPO {
	WebDriver driver;
	public UserMyProductReviewPO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
}
