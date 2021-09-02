package com.qa.democart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationsPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	//locators
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By pwd = By.id("input-password");
	private By confirmPwd = By.id("input-confirm");
	
	private By suscribeYes =By.xpath("(//label[@class='radio-inline'])[position()=1]/input");
	private By suscribeNo =By.xpath("(//label[@class='radio-inline'])[position()=2]/input");
	private By agreeCheckBox = By.name("agree");
	private By continuebutton=By.xpath("//input[@type='submit' and @value='Continue']");
	private By successMsg=By.xpath("//div[@id='content']/h1");
	private By logoutLink=By.linkText("Logout");
	private By registerLink=By.linkText("Register");
	
	public RegistrationsPage(WebDriver driver) {
		this.driver=driver;
		elementUtil=new ElementUtil(driver);
	}
	
	public boolean accountRegistration(String firstName,String lastName,String email,String telephone,
			                        String password,String subscribe){
		
		elementUtil.doSendkeys(this.firstName, firstName);
		elementUtil.doSendkeys(this.lastName, lastName);
		elementUtil.doSendkeys(this.email, email);
		elementUtil.doSendkeys(this.telephone, telephone);
		elementUtil.doSendkeys(pwd, password);
		elementUtil.doSendkeys(confirmPwd, password);
		
		if(subscribe.equalsIgnoreCase("yes")) {
			elementUtil.doClick(suscribeYes);
		}else {
			elementUtil.doClick(suscribeNo);
		}
		elementUtil.doClick(agreeCheckBox);
		elementUtil.doClick(continuebutton);
		
		String mesg=elementUtil.waitForElementPresence(successMsg,Constants.DEFAULT_TIME_OUT).getText();
		System.out.println(mesg);
		
		if(mesg.contains(Constants.REGISTER_SUCCESS_MSG)) {
			elementUtil.doClick(logoutLink);
			elementUtil.doClick(registerLink);			
			return true;
		}
			return false;
		
	}
	
	
 
}
