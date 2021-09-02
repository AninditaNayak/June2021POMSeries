package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest{
	
	
	@BeforeClass
	public void regSetUp() {
		regPage=loginPage.navigateToRegisterPage();
	}
	
	public String getRandomEmail() {
		Random random=new Random();
		String email = "testAutomation"+random.nextInt(5000)+"@gmail.com";
		System.out.println(email);
		return email;
	}
	
	/*@DataProvider
	public Object[][] getRegTestDta(){
		return new Object[][] {
			{"ani","nayak","8767356656","ani123","yes"},
			{"Tom","peter","8756356656","ani123","no"},
			{"haryhj","peterkn","8767356645","ani12","yes"}
			}; 
	}*/
	//Using Excel util
	
	@DataProvider
	public Object[][] getRegTestDta(){
		return ExcelUtil.getTestDat(Constants.REGISTER_SHEET_NAME);
	}
	@Test(dataProvider="getRegTestDta")
	public void registrationTest(String firstName,String lastName,String telephone,
			                     String password,String subscribe ) {
		Assert.assertTrue(regPage.accountRegistration(firstName,lastName,getRandomEmail(),telephone,password,subscribe));
	}
	

}
