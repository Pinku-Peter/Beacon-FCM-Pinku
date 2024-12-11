package com.test.Updation_of_DispositionModule;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.lang.reflect.Method;

import com.BasePackage.Base_Class;
import com.BasePackage.Login_Class;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Page_Repository.UpdationofDispositionRepo;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.testautomation.pages.UpdationofDispositionPage;
import com.listeners.TestListener;

public class AllScenarios_Updation_of_DispositionModule {
	
	UpdationofDispositionPage updationofdispositionMasterPage;
	Base_Class baseclass;
	com.Utility.ExcelReader ExcelReader;
	WebDriver driver;
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	ExtentTest extenttest;
	Login_Class corelogin;
	
	@BeforeSuite

	public void SetUp() throws Exception {
		
		baseclass = new Base_Class();
		corelogin = new Login_Class();
		corelogin.CoreLogin();
		driver = baseclass.getDriver(); // Retrieve the driver instance
		updationofdispositionMasterPage = new UpdationofDispositionPage(driver);
		ExcelReader = new com.Utility.ExcelReader("Updation_of_Disposition");
		TestListener = new TestListener();
		screenShot = new com.Utility.ScreenShot(driver);
	}
	
	@BeforeMethod
    public void setupTest(Method method) {
        // Start a new ExtentTest for the current test method
        extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("Updation of Disposition");
    }
	
	@Test(priority = 1)
    public void Open_Updation_of_Disposition_Screen() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		try {
		updationofdispositionMasterPage.navigateToUpdationOfDisposition();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		// Find the account number field element
	    WebElement accountNumberField = driver.findElement(UpdationofDispositionRepo.accountNumberField);
        Assert.assertTrue(accountNumberField.isDisplayed());
		// Find the search button element
	    WebElement SearchbuttonField = driver.findElement(UpdationofDispositionRepo.searchButton);
        Assert.assertTrue(SearchbuttonField.isDisplayed());
        ExtentTestManager.getTest().log(Status.PASS, "Disposition update screen with Account number text field and Search button is opened.");
		}
		
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
        }
		Thread.sleep(3000);
    }
	
	@Test(priority = 2, dataProvider = "TestData")
    public void Account_Number_Field__ECP_Validations(Map<Object, Object> testdata) throws InterruptedException {
	 try {
        
        // Step 1: Select multiple values
	// Step 1: Check if the test needs to be executed
	 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
	    	
	    	// Step 2: Retrieve the "Account Numbers" data from the test data
	        String[] InvalidAccountNumbers = testdata.get("InvalidAccountNumbers").toString().split("\\|");;
	        
	        for (String InvalidAccountNumber : InvalidAccountNumbers) {
	            InvalidAccountNumber = InvalidAccountNumber.trim(); // Remove any leading/trailing spaces
	            updationofdispositionMasterPage.enterAccountNumber(InvalidAccountNumber);
	        }
	    }
        ExtentTestManager.getTest().log(Status.PASS, "Should not possible to type Alphabets and special characters in account number field.");
	 }
        catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
	 }
	 Thread.sleep(3000); 
        
 }
	
	@Test(priority = 3, dataProvider = "TestData")
    public void Attempt_to_Enter_More_than_25_Characters(Map<Object, Object> testdata) throws InterruptedException {
	 try {
        
        // Step 1: Select multiple values
	// Step 1: Check if the test needs to be executed
	 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
	    	
	    	// Step 2: Retrieve the "Account Numbers" data from the test data
	        String InvalidAccountNumber = testdata.get("AccountNumberwithmorethan25characters").toString();
	        
	        updationofdispositionMasterPage.enterAccountNumberwithmore25characters(InvalidAccountNumber);
	    
	    }
        ExtentTestManager.getTest().log(Status.PASS, "Entry is restricted to 25 characters.");
	 }
        catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
	 }
	 Thread.sleep(3000); 
        
 }	
	
	@Test(priority = 4, dataProvider = "TestData")
    public void Enter_Invalid_Account_Number_and_Search(Map<Object, Object> testdata) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
	 try {
        
        // Step 1: Select multiple values
	// Step 1: Check if the test needs to be executed
	 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
	    	
	    	// Step 2: Retrieve the "Account Numbers" data from the test data
	        String InvalidAccountNumber = testdata.get("InvalidAccountNumber").toString();
	        
	        updationofdispositionMasterPage.enterInvalidAccountNumber(InvalidAccountNumber); 
	        
	        updationofdispositionMasterPage.clickSearchButton();
	        Assert.assertEquals(updationofdispositionMasterPage.getErrorMessage(), "Invalid Account Number");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.errorMessage));
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    }
        ExtentTestManager.getTest().log(Status.PASS, "An error message is displayed \"Invalid account number\".");
	 }
        catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
	 }
	 Thread.sleep(3000); 
        
 }
	
	@Test(priority = 5)
    public void Search_with_Empty_Account_Number_Field() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
	 try {
		 	updationofdispositionMasterPage.withoutAccountNumber();
	        updationofdispositionMasterPage.clickSearchButton();
	        Assert.assertEquals(updationofdispositionMasterPage.getErrorMessage(), "Invalid Account Number");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.errorMessage));
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    
        ExtentTestManager.getTest().log(Status.PASS, "An error message is displayed \"Invalid account number\".");
	 }
        catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
	 }
	 Thread.sleep(3000); 
        
 }
	@Test(priority = 6)
    public void Enter_Valid_Account_Number_and_Search() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		 try {
		updationofdispositionMasterPage.entervalidAccountNumber();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		updationofdispositionMasterPage.clickSearchButton();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.customername));
        Assert.assertTrue(driver.getPageSource().contains("cardsheadings"));
        ExtentTestManager.getTest().log(Status.PASS, "Account details are displayed along with options for New Interaction, Interaction History, Other and Additional details.");
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000); 
    }
	
	@Test(priority = 7)
    public void testAllFieldsMissingValidation() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		 try {
		updationofdispositionMasterPage.clickSaveButton();
        Assert.assertEquals(updationofdispositionMasterPage.getErrorMessagewithoutvalue(), "Please Enter All Fields In The Interaction Details");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(UpdationofDispositionRepo.errorMessage2));
        ExtentTestManager.getTest().log(Status.PASS, "Error message \"Please Enter All Fields In The Interaction Details\" is displayed.");
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000); 
    }
	
	
	
	@AfterMethod
	 public void takeScreenshotOnFailure(ITestResult result) throws IOException {
		    // Check if the test case failed
		    if (result.getStatus() == ITestResult.FAILURE) {
		        String methodName = result.getMethod().getMethodName();
		        try {
		            // Take the screenshot for the failed test
		            File image = screenShot.takeScreenShot(methodName, "Failure");
		            
		            extenttest.log(Status.INFO, "Screenshot of failure: ",
		                    MediaEntityBuilder.createScreenCaptureFromPath(image.getAbsolutePath()).build());
		            
		        } catch (IOException e) {
		            System.err.println("Failed to capture screenshot: " + e.getMessage());
		        }
		    }
		}
	 
	 @DataProvider(name = "TestData")
		public static Object[][] gettestdate() throws IOException {

			Object[][] objectarry = null;
			java.util.List<Map<String, String>> completedata = com.Utility.ExcelReader.getdata();

			objectarry = new Object[completedata.size()][1];

			for (int i = 0; i < completedata.size(); i++) {
				objectarry[i][0] = completedata.get(i);
			}
			return objectarry;
		}
	 
	 @AfterSuite
	 public void afterEachTest() {
	     ExtentManager.getInstance().flush();
	 }


}
