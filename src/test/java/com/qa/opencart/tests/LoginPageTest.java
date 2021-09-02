package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.democart.pages.AccountsPage;
import com.qa.opencart.listenrs.TestAllureListener;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Errors;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("EPIC 100:Design Login Page for open cart application")
@Story("US 101:Login Page feature with different features...")
@Listeners(TestAllureListener.class)
public class LoginPageTest extends BaseTest {
	
	@Description("Login Page title test...")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=1)
	public void loginPageTitleTest() {
		String title=loginPage.getLoginPageTitle();
		System.out.println("LoginPage title is:"+title);
		Assert.assertEquals(title,Constants.LOGIN_PAGE_TITLE,Errors.TITLE_ERROR_MESSG);
	}
	
	@Description("Login Page header test...")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=2)
	public void loginPageHeaderTest() {
		String header=loginPage.getPageHeaderText();
		System.out.println("LoginPage title is:"+header);
		Assert.assertEquals(header,Constants.PAGE_HEADER,Errors.HEADER_ERROR_MESSG);
		
	}
	
	@Description("forgot pwd link test...")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=3)
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist(),Errors.LINK_ERROR_MESSG);
		
	}

	@Description("Login page test...")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=4)
	public void loginTest() {
		AccountsPage accPage=loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
}
