package pageObjects.nopcommerce;

import org.openqa.selenium.WebDriver;

public class UserAddressPO extends UserSideBarPO {
	WebDriver driver;
	public UserAddressPO (WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
}
