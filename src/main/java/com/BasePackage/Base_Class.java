package com.BasePackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Utility.Log;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base_Class {

public static RemoteWebDriver driver = null;
WebDriverWait wait;
	
	public WebDriver getDriver() {
		return driver;
	}
	
	private static By L_username = By.xpath("//input[@placeholder='User Name']");
	private static By L_password = By.xpath("//input[@placeholder='Password']");
	private static  By L_Login = By.xpath("//button[contains(text(),'LOGIN')]");
	private static By Go_Collection = By.xpath("//button[contains(text(),'Go Collection')]");
	private static By spinner = By.xpath("//div[@class='spinner']");
	//private static By L_LogOut= By.xpath("//div[@class='user position ng-tns-c3-15 ng-star-inserted']");
	//private static By L_LogAlert= By.xpath("//button[text()='Sign out']");
	
	public static String Pagetitle;

	public static Properties configloader() throws IOException {
		FileInputStream File = new FileInputStream(".\\src\\test\\resources\\config.properties");
		Properties properties = new Properties();
		properties.load(File);
		return properties;
	}
//	public void clickingOnWebElement(WebElement element, long waitTimeInSeconds)
//	{
//	WebDriverWait webwait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeInSeconds));
//	WebElement elements = null;
//	elements = webwait.until(ExpectedConditions.elementToBeClickable(element));
//	elements.click();
//	}
	public  void SetUp() throws IOException, InterruptedException {
		
		String Browser = configloader().getProperty("Browser");
		String Url = configloader().getProperty("URL");
		String UserName = configloader().getProperty("UserName");
		String Password = configloader().getProperty("Password");
		switch (Browser.toUpperCase()) {

		case "CHROME":

			ChromeOptions options = new ChromeOptions();
			//options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
			options.addArguments("--disable-extensions");
			//System.setProperty("webdriver.chrome.driver", "C:\\Selenium WebDriver\\Chrome Driver\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);		
			break;

		case "FIREFOX":

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();			
			break;

		default:
			System.err.println("The Driver is not defined");
		}

		driver.manage().window().maximize();
		//driver.manage().deleteAllCookies();
		wait = new WebDriverWait(driver,Duration.ofDays(1));
		Log.info("Driver has initialized successfully for "+Browser+"browser");
		driver.get(Url);
		wait.until(ExpectedConditions.visibilityOfElementLocated(L_username));
		Pagetitle = driver.getTitle();
		Log.info("Title is displayed : "+Pagetitle);
		input(L_username, UserName);
		input(L_password, Password);
		click(L_Login);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Go Collection')]")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner));
		wait.until(ExpectedConditions.elementToBeClickable(Go_Collection));
		click(Go_Collection);
		Thread.sleep(30000);


	}


	public static  void input(By element, String Value) throws InterruptedException {


		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait2.until(ExpectedConditions.presenceOfElementLocated(element)).sendKeys(Value);


	}

	public static  void click(By element) throws InterruptedException {

		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		Thread.sleep(2000);

	}

	public static void select(String value,By element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		Select selWeekDayDropDown = new Select(driver.findElement(element));
		selWeekDayDropDown.selectByVisibleText(value);
		
	}

	public static void clear(By element)throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(element)).clear();
		Thread.sleep(2000);
	}


	public static void AcceptAlert()
	{
		driver.switchTo().alert().accept();
		//driver.switchTo().alert().dismiss();

		
	}

	public static  void INclick(By element) throws InterruptedException {

		//Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
		//Thread.sleep(2000);

	}
	
	public static void SwitchToFrame(By frameName)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
	}
	
	public static void ScrollUntilElementVisible(By locator)
	{ 
		WebElement element = driver.findElement(locator);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

	public static boolean ElementDisplayed(By locator)
	{
		WebElement element = driver.findElement(locator);
		Boolean flag = element.isDisplayed();
		return flag;
	}
	
	public static boolean ElementEnabled(By locator)
	{
		WebElement element = driver.findElement(locator);
		Boolean flag = element.isEnabled();
		return flag;
	}
	
	public static void UploadFile(By locator, String path)
	{
		WebElement uploadElement = driver.findElement(locator);
		String path1=System.getProperty("user.dir");
		Log.info("path is :" + path1);
		uploadElement.sendKeys(path1 + path);
		
	}
	
	
//	public static void UploadFile(By locator)
//	{
//		WebElement uploadElement = driver.findElement(locator);
//		 String path=System.getProperty("user.dir");
//		 Log.info("path is :" + path);
//		//System.out.Directory.GetParent(this.GetType().Assembly.Location).ToString();
//		uploadElement.sendKeys(path);
//		
//		
//	}
	public static boolean ElementEnableOrDisable(By locator)
	{
		WebElement element = driver.findElement(locator);
		Boolean flag = element.isEnabled();
		return flag;
	}
	
	public static boolean CheckElementDisable(By locator)
	{  
		//disable
		WebElement element = driver.findElement(locator);
		Boolean flag = element.isEnabled();	
		
		if (flag==false) {
			flag=true;
		}else if(flag==true)
			flag=false;
		
		return flag;
	}
	
	
	public static  void Hover(By element) throws InterruptedException {
		WebElement element1 = driver.findElement(element);
		Actions actions = new Actions(driver);
		actions.moveToElement(element1).perform();

		

	}

public static Connection OracleDBConnection() throws IOException {
		
		Connection connection = null;
        try {
        	String DB_URL = configloader().getProperty("DatabaseURL");
        	String DB_UserName = configloader().getProperty("DB_UserName");
        	String DB_Password = configloader().getProperty("DB_Password");
        	
            // JDBC URL for Oracle database
            String URL = "jdbc:oracle:thin:@"+ DB_URL.trim();
            String username = DB_UserName.trim();
            String password = DB_Password.trim();
            // Establish connection
            System.out.println("URL="+URL);
            System.out.println("username="+username);
            System.out.println("password="+password);
            connection = DriverManager.getConnection(URL, username, password);
            
            if (connection != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.println("Connection failed!");
            e.printStackTrace();
        } 
		return connection;
		
	}

}
