package com.qa.democart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;	
	private ElementUtil elementUtil;

	//private By locators:
	
       private By emailId=By.id("input-email");
       private By password=By.id("input-password");
       private By loginBtn=By.xpath("//input[@value='Login']");
       private By forgotPwdLink=By.xpath("//div[@class='form-group']//a[text()='Forgotten Password']");
       private By header=By.xpath("//div[@id='logo']//a");
       
       private By registerLink=By.linkText("Register");
       
       //constructors
       public LoginPage(WebDriver driver) {
    	   this.driver=driver;
    	   elementUtil=new ElementUtil(driver); 
    	  
       }
       
      //page actions/page methods/functionality/behaviour of the page/no assertion
       
       @Step("getting login page title...")
       public String  getLoginPageTitle() {
    	  // return driver.getTitle();
    	 return elementUtil.waitFortitleIs(Constants.LOGIN_PAGE_TITLE, 5);
       }
       
       @Step("getting login page header text...")
       public String  getPageHeaderText() {
    	   //return driver.findElement(header).getText();
    	   return elementUtil.doGetText(header);
       }
       
       @Step("checking forgot pwd link exist or not...")
       public boolean  isForgotPwdLinkExist() {
    	  // return driver.findElement(forgotPwdLink).isDisplayed();
    	   return elementUtil.doIsDisplayed(forgotPwdLink);
       }
       
       @Step("login to application with username {0} and password {1}")
       public AccountsPage doLogin(String un,String pwd) {
    	   
//    	   driver.findElement(emailId).sendKeys(un);
//    	   driver.findElement(password).sendKeys(pwd);
//    	   driver.findElement(loginBtn).click();
    	   elementUtil.doActionSendKeys(emailId, un);
    	   elementUtil.doActionSendKeys(password, pwd);
    	   elementUtil.doClick(loginBtn);
    	   return new AccountsPage(driver);    	   
    	   
       }
       
       @Step("navigate to registration page...")
       public RegistrationsPage navigateToRegisterPage() {
    	   elementUtil.doClick(registerLink);
    	   return new RegistrationsPage(driver);
       }
}
