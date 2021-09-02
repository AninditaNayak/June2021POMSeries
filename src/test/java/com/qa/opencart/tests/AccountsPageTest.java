package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.listenrs.TestAllureListener;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Errors;

@Listeners(TestAllureListener.class)
public class AccountsPageTest extends BaseTest{
	
	@BeforeClass
	public void accPageSetup() {
		accPage=loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		
	}

	@Test(priority=1)
	
	public void accPageTitleTest() {
		String title=accPage.getAccPageTitle();
		System.out.println("account page title is:"+title);
		Assert.assertEquals(title, Constants.ACCOUNT_PAGE_TITLE,Errors.TITLE_ERROR_MESSG);
	}
	
@Test(priority=2)
	
	public void accPageheaderTest() {
		String header=accPage.getAccPageHeader();
		System.out.println("account page header is:"+header);
		Assert.assertEquals(header,Constants.PAGE_HEADER,Errors.TITLE_ERROR_MESSG);
	}
	
@Test(priority=3)
	
	public void accPageUrlTest() {
		String url=accPage.getAccPageUrl();
		System.out.println("account page url is:"+url);
		Assert.assertTrue(url.contains(Constants.ACCOUNT_PAGE_URL_FRACTION),Errors.URL_ERROR_MESSG);
	}

@Test(priority=4)

public void accSectionsListTest() {
	List<String> actAccSecList = accPage.getAccSectionsList();
	System.out.println("actual sections:"+actAccSecList);
	Assert.assertEquals(actAccSecList, Constants.EXPECTED_ACC_LIST);
	
}

@Test(priority=5)
public void logoutLinkExistTest() {
	Assert.assertTrue(accPage.isLogoutLinkExist());
}

@DataProvider
public Object[][] getSearchData() {
	return new Object[][] {{"Macbook Pro"},{"Macbook Air"},{"Apple"}};
}
	

@Test(priority=6,dataProvider="getSearchData")
public void searchTest(String productName) {
	resultsPage=accPage.doSearch(productName);
	String resultHeader=resultsPage.getSearchPageHeader();
	
	System.out.println("result header is:"+resultHeader);	
	Assert.assertTrue(resultHeader.contains(productName));
		
}

@DataProvider
public Object[][] getProductSelectData() {
	
	return new Object[][] {{"Macbook","MacBook Air"},
		                   {"Macbook","Macbook Pro"},
		                   {"Apple","Apple Cinema 30\""}
		                   };
		}
	


@Test(priority=7,dataProvider="getProductSelectData")
public void selectProductTest(String productName,String mainProductName) 
{
	resultsPage=accPage.doSearch(productName);
	productInfoPage=resultsPage.selectProduct(productName);
	String header=productInfoPage.getProductHeaderText();
	System.out.println("product header is:"+header);
	//Assert.assertEquals(header,productName);
	Assert.assertTrue(header.contains(productName));
}
	
}
