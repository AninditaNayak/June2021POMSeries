package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	public WebDriver driver;
	public static String highlight;
	private OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();
	
	
	/**
	 * This method is used to initialize the driver
	 * @param browserName
	 * @return
	 */
	
	//public WebDriver initDriver(String browserName) {
	public WebDriver initDriver(Properties prop) {
		String browserName=prop.getProperty("browser");
		highlight=prop.getProperty("highlight");
		System.out.println("browser name is :"+ browserName);
		
		optionsManager=new OptionsManager(prop);
		
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver=new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		
		else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver=new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
						
		}
		
		else {
			System.out.println("Please pass the right browser name:"+browserName);
		}
		
//		driver.manage().deleteAllCookies();
//		driver.manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		//driver.get("https://demo.opencart.com/index.php?route=account/login");
		//driver.get(prop.getProperty("url"));
		getDriver().get(prop.getProperty("url"));
		
		//return driver;
		return getDriver();
	}
	
	public WebDriver getDriver() {
		return tlDriver.get();
	}
	
	/**
	 * This method is used to initialize the properties on the basis of given env
	 * @return
	 */
	
	public Properties initProperties() {
		Properties prop=null;
		FileInputStream ip=null;
		String env=System.getProperty("env");//mvn clean install -Denv="qa"
		
		try {
		if(env==null) {
			System.out.println("Running on Environment: PROD env...");
			
				ip=new FileInputStream("./src/test/resources/config/config.properties");			
		}
		
		else {
			System.out.println("Running on Environment:"+env);
			
			switch (env) {
			case "qa":
				ip=new FileInputStream("./src/test/resources/config/qa.config.properties");
				break;
				
			case "dev":
				ip=new FileInputStream("./src/test/resources/config/dev.config.properties");
				break;
				
			case "stage":
				ip=new FileInputStream("./src/test/resources/config/stage.config.properties");
				break;

			default:
				System.out.println("No Env Found...");
				throw new Exception("NOENVFOUNDEXCEPTION");
				//break;
			}
		}
		} catch (FileNotFoundException e) {				
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			
			prop=new Properties();
			prop.load(ip);
			
		}		
		catch (IOException e) {			
			e.printStackTrace();
		}
		
		return prop;
	}
	
	/**
	 *take screenshot
	 */

	public  String getScreenshot() {
		File srcFile=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
		File destination=new File(path);
		
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		return path;
	}
}
