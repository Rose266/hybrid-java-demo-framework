package pageObjects.nopcommerce;

import org.openqa.selenium.WebDriver;

public class UserOrderPO extends UserSideBarPO {
	WebDriver driver;
	public UserOrderPO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	} 
	
}
