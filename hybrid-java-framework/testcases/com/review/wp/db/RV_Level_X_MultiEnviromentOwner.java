package com.review.wp.db;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.wordpress.AdminDashboardPagePO;
import pageObjects.wordpress.AdminLoginPagePO;
import pageObjects.wordpress.AdminPageGeneratorPO;
import pageObjects.wordpress.AdminSideBarPO;
import pageObjects.wordpress.AdminUsersPagePO;
import review.utilities.RVEnviroment;

public class RV_Level_X_MultiEnviromentOwner extends BaseTest{
	
		@Parameters ({"browser","enviroment"})
		@BeforeClass 
		public void beforeClass(String browserName, String enviromentName){
			ConfigFactory.setProperty("env",enviromentName);
			enviroment = ConfigFactory.create(RVEnviroment.class);
			System.out.println(enviroment.dbHost());
			System.out.println(enviroment.dbPass());
			System.out.println(enviroment.dbUserName());
			System.out.println(enviroment.userName());
			System.out.println(enviroment.url());
			
			driver = getBrowserDriver(browserName, enviroment.url());
			adminLoginPage = AdminPageGeneratorPO.getRVAdminLoginPage(driver);
			
			log.info("Pre-condition - Step 01: Input into UserName textbox");
			adminLoginPage.inputIntoUserNameTextbox(adminUserName);
			
			log.info("Pre-condition - Step 02: Input into Password textbox");
			adminLoginPage.inputIntoPasswordTextbox(adminPass);
			
			log.info("Pre-condition - Step 03: Click to button [Log In]");
			adminDardboardPage = adminLoginPage.clickToLoginButton();
		}
		
		@Test
		public void TC_01_View_Users() {
			log.info("View_Users - Step 01: Click to Users menu and go to Users page");
			adminDardboardPage.openSidebarMenuPageByName(userMenu);
			adminUserPage = AdminPageGeneratorPO.getRVAdminUsersPage(driver);
			
			log.info("View_Users - Step 02: Get all users from adminUI");
			int totalUsersUI = adminUserPage.getTotalUsersFromUI();
			
			log.info("View_Users - Step 03: Get all users from database");
			int totalUserDB = adminUserPage.getTotalUserFromDB();
			
			log.info("View_Users - Step 04: Verify the user's number matching");
			Assert.assertEquals(totalUserDB, totalUsersUI);
			
			log.info("View_Users - Step 05: Input to search textbox");
			adminUserPage.inputToSearchTextbox(userName);
			
			log.info("View Users - Step 06: Click to [Search Users] button");
			adminUserPage.clickToSearchButton();
			
			log.info("View_Users - Step 07: Get the number of users from adminUI  with userName = ''");
			int UserNumberUI = adminUserPage.getTotalUsersFromUI();
			
			log.info("View_Users - Step 08: Get the number of users from database userName = ''");
			int UserNumberDB = adminUserPage.getUserNameNumberFromDB(userName);
			
			log.info("View_Users - Step 09: Verify the user's number matching");
			Assert.assertEquals(UserNumberDB, UserNumberUI);
		}
		
		@AfterClass(alwaysRun = true)
		public void afterClass() {
			closeBrowserDriver();
		}
		
		WebDriver driver;
		RVEnviroment enviroment;
		AdminLoginPagePO adminLoginPage;
		AdminSideBarPO adminSidebar;
		AdminDashboardPagePO adminDardboardPage;
		AdminUsersPagePO adminUserPage;
		private String userMenu = "Users";
		private String userName = "nhung";
		private String adminUserName= "nhung";
		private String adminPass ="Nhung@123.com";
}
