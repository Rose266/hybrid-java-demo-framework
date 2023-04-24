package pageObjects.wordpress;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import commons.GlobalConstants;
import pageUIs.wordpress.RVAdminUsersPageUI;
import review.utilities.RV_MySQLConnectUtilities;



public class AdminUsersPagePO extends AdminSideBarPO {
	WebDriver driver;
	public AdminUsersPagePO (WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	public int getTotalUsersFromUI() {
		waitForElementVisible(driver, RVAdminUsersPageUI.TOTAL_NUMBER_TEXT);
		int total = Integer.parseInt(getElementText(driver, RVAdminUsersPageUI.TOTAL_NUMBER_TEXT).split(" ")[0]);
		return total;
	}
	public int getTotalUserFromDB() {
		Connection conn = RV_MySQLConnectUtilities.getMySQLConnection();
		List<Integer> totalUser = new ArrayList<Integer>();
		try {
			Statement stm = conn.createStatement();
			String sql = "SELECT * FROM wp_users;";
			ResultSet rst = stm.executeQuery(sql);
			while(rst.next()) {
				totalUser.add(rst.getInt(1));
				System.out.println(rst.getInt(1));;
				System.out.println(rst.getString(2));
				System.out.println(rst.getString(4));
				System.out.println(rst.getString(5));
				System.out.println(rst.getString(6));
				System.out.println("----------");
			} }catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(conn!= null) {
						conn.close();
					} } catch (SQLException e) {
						e.printStackTrace();
					}
				}
		return totalUser.size();
		
	} 
			
		
		
	
	public void inputToSearchTextbox(String valueToSendkey) {
		waitForElementVisible(driver, RVAdminUsersPageUI.SEARCH_TEXTBOX);
		sendKeysToElement(driver, RVAdminUsersPageUI.SEARCH_TEXTBOX, valueToSendkey);
	}
	public void clickToSearchButton() {
		waitForElementClickable(driver, RVAdminUsersPageUI.SEARCH_BUTTON);
		clickToElement(driver, RVAdminUsersPageUI.SEARCH_BUTTON);
		sleepInSeconds(GlobalConstants.SHORT_TIMEOUT);
	}

	public int getUserNameNumberFromDB(String userName) {
		Connection conn = RV_MySQLConnectUtilities.getMySQLConnection();
		List<Integer> userNumber = new ArrayList<Integer>();
		try {
			String sql = "SELECT * FROM wp_users WHERE user_login = ?;";
			PreparedStatement preStm = conn.prepareStatement(sql);
			preStm.setString(1, userName);
			ResultSet rst = preStm.executeQuery();
			while(rst.next()) {
				userNumber.add(rst.getInt(1));
				System.out.println(rst.getInt(1));;
				System.out.println(rst.getString(2));
				System.out.println(rst.getString(4));
				System.out.println(rst.getString(5));
				System.out.println(rst.getString(6));
				System.out.println("----------");
			} 
		}catch(SQLException e) {
				e.printStackTrace();
			}
		finally {
			try {
				if(conn!=null) {
					conn.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return userNumber.size();
		} 
		
	
	
	
}
