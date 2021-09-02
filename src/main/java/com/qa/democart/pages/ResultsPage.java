package com.qa.democart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ResultsPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	//private By locators:
	
	private By searchHeader=By.xpath("//div[@id='content']/h1");
	private By productResults=By.xpath("//div[@class='caption']//a");
	
	//constructors
		public ResultsPage(WebDriver driver) {
			this.driver=driver;
			elementUtil=new ElementUtil(driver);
		}
	

		public String  getSearchPageHeader() {
			return elementUtil.doGetText(searchHeader);
		}

		public int getSearchProductListCount() {
			return elementUtil.getElements(productResults).size();
		}
		
		public ProductInfoPage selectProduct(String mainProductMain) {
			List<WebElement> searchList = elementUtil.getElements(productResults);
			for (WebElement e : searchList) {
				if(e.getText().trim().equals(mainProductMain)) {
					e.click();
					break;
				}
			}
			
			return new ProductInfoPage(driver);
			
		}
		
}
