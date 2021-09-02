package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProductInfoTest extends BaseTest {
	
	@BeforeClass
	public void productInfoPageSetup() {
		accPage=loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		
	}
	
	@Test
	public void productImageTest() {
		resultsPage=accPage.doSearch("iMac");
		productInfoPage=resultsPage.selectProduct("iMac");
		Assert.assertEquals(productInfoPage.getProductImagesCount(), 3);
	}

	@Test
	public void productInfoTest() {
		resultsPage=accPage.doSearch("Macbook");
		productInfoPage=resultsPage.selectProduct("Macbook Pro");
		//productInfoPage.getProductInfoCount();
//		//Map<String,String> actProductInfoMap=productInfoPage.getProductInfo();
//		
//		Assert.assertEquals(actProductInfoMap.get("name"), "Macbook Pro");
//		Assert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
//		Assert.assertEquals(actProductInfoMap.get("Product Code"), "Product 18");
//		Assert.assertEquals(actProductInfoMap.get("Reward Points"), "800");
//		
//		Assert.assertEquals(actProductInfoMap.get("price"), "$2,000.00");
		
	    /*softAssert.assertEquals(actProductInfoMap.get("name"), "Macbook Pro");
		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("Product Code"), "Product 18");
		softAssert.assertAll();  */
	}
}
