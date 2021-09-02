package com.qa.democart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil elementUtil;
	
	//private By locators:
	
	private By accSections=By.xpath("//div[@id='content']/h2");
	private By header=By.xpath("//div[@id='logo']//a");
	private By logoutLink=By.xpath("//div[@class='list-group']/a[text()='Logout']");
	private By searchField=By.name("search");
	private By searchBtn=By.xpath("//div[@id='search']//button");
	
	//constructors
	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		elementUtil=new ElementUtil(driver);
	}
	
	public String  getAccPageTitle() {
		return elementUtil.waitFortitleIs(Constants.ACCOUNT_PAGE_TITLE, 5);
	}
	
	public String  getAccPageUrl() {
		return elementUtil.waitForUrlContains(Constants.ACCOUNT_PAGE_URL_FRACTION, 5);
	}
	
	public String getAccPageHeader() {
		return elementUtil.doGetText(header);
	}
	
	public List<String> getAccSectionsList() {
		 return elementUtil.getLinkTextList(accSections);
	}
	
	public boolean  isLogoutLinkExist() {
		return elementUtil.doIsDisplayed(logoutLink);
	}
	
	public ResultsPage doSearch(String productName) {
		System.out.println("searching the product:"+productName);
		elementUtil.doSendkeys(searchField, productName);
		elementUtil.doClick(searchBtn);
		return new ResultsPage(driver);//Here after clicking the search btn we are navigating next landing page that is 
		                              //Results page, but Results page currently not ready we dont have ,as soon as we see the 
		                              // requirement according to req, start creating the new page class , 
		                              //that is called Test Driven Development (TDD) approach
		
	}
}
