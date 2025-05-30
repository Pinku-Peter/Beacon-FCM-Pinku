package com.test.Updation_of_DispositionModule;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
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

public class Updation_of_Disposition_TestClass {
	
	UpdationofDispositionPage updationofdispositionMasterPage;
	Base_Class baseclass;
	com.Utility.ExcelReader ExcelReader;
	WebDriver driver;
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	ExtentTest extenttest;
	Login_Class corelogin;
	
	@BeforeClass

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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		try {
		updationofdispositionMasterPage.navigateToUpdationOfDisposition();
		ExtentTestManager.getTest().log(Status.PASS, "Clicked on the 'Disposition' main menu.");
		ExtentTestManager.getTest().log(Status.PASS, "Selected the 'Updation of Disposition' menu.");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		// Find the account number field element
	    WebElement accountNumberField = driver.findElement(UpdationofDispositionRepo.accountNumberField);
        Assert.assertTrue(accountNumberField.isDisplayed());
		// Find the search button element
	    WebElement SearchbuttonField = driver.findElement(UpdationofDispositionRepo.searchButton);
        Assert.assertTrue(SearchbuttonField.isDisplayed());
        ExtentTestManager.getTest().log(Status.PASS, "Disposition update screen with Account number text field and Search button is opened.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.transactiondetails));
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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
	 try {
        // Step 1: Select multiple values
	// Step 1: Check if the test needs to be executed
	 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
	    	
	    	// Step 2: Retrieve the "Account Numbers" data from the test data
	        String InvalidAccountNumber = testdata.get("AccountNumberwithmorethan25characters").toString();
	        
	        updationofdispositionMasterPage.enterAccountNumberwithmore25characters(InvalidAccountNumber);
	    
	    }
        ExtentTestManager.getTest().log(Status.PASS, "Entry is restricted to 25 characters.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
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
	        ExtentTestManager.getTest().log(Status.PASS, "Entered an invalid account number like 1231232123123213123123123.");
	        updationofdispositionMasterPage.clickSearchButton();
	        ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Search button.");
	        Assert.assertEquals(updationofdispositionMasterPage.getErrorMessage(), "Invalid Account Number");
	    }
        ExtentTestManager.getTest().log(Status.PASS, "An error message is displayed \"Invalid account number\".");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(UpdationofDispositionRepo.errorMessage8));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
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
		 	ExtentTestManager.getTest().log(Status.PASS, "Left the Account Number field empty.");
	        updationofdispositionMasterPage.clickSearchButton();
	        ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Search button.");
	        Assert.assertEquals(updationofdispositionMasterPage.getErrorMessageforemptysearch(), "Invalid account number.");
	    
        ExtentTestManager.getTest().log(Status.PASS, "An error message is displayed \"Invalid account number\".");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(UpdationofDispositionRepo.errorMessage));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	 }
        catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
	 }
	 Thread.sleep(3000); 
        
 }
	@Test(priority = 6)
    public void Enter_invalid_Account_Number_which_is_user_not_assigned_branches_and_Search() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		 try {
		updationofdispositionMasterPage.enterinvalidAccountNumbernotassigned(); 
		ExtentTestManager.getTest().log(Status.PASS, "Executed the Oracle SQL stored procedure to fetch another branch's valid account number.");
		ExtentTestManager.getTest().log(Status.PASS, "Entered the account number in the account number field.");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		updationofdispositionMasterPage.clickSearchButton();
		ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Search button.");
		Assert.assertEquals(updationofdispositionMasterPage.getErrorMessageforinvalidAccountNumbernotassigned(), "You are not authorized to do the disposition of this account number");
        ExtentTestManager.getTest().log(Status.PASS, "Should display error message \"You are not authorized to do the disposition of this account number.\"");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(UpdationofDispositionRepo.errorMessage7));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000); 
    }
	
	@Test(priority = 7)
    public void Enter_Valid_Account_Number_and_Search() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		 try {
		updationofdispositionMasterPage.entervalidAccountNumber();
		ExtentTestManager.getTest().log(Status.PASS, "Executed the Oracle SQL stored procedure to fetch a valid account number.");
		ExtentTestManager.getTest().log(Status.PASS, "Entered the account number in the account number field.");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		updationofdispositionMasterPage.clickSearchButton();
		ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Search button.");
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
	
	@Test(priority = 8)
    public void Add_New_Interaction_Details__All_Fields_Missing_Validation() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		 try {
		updationofdispositionMasterPage.clickSaveButton();
		ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Save button without selecting any values in Next Action Owner, Disposition, Sub Disposition, Next Action Date, and without entering Remarks.");
        Assert.assertEquals(updationofdispositionMasterPage.getErrorMessagewithoutvalue(), "Please Enter All Fields In The Interaction Details");
        ExtentTestManager.getTest().log(Status.PASS, "Error message \"Please Enter All Fields In The Interaction Details\" is displayed.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(UpdationofDispositionRepo.errorMessage2));
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000); 
    }
	
	 @Test(priority = 9, dataProvider = "TestData")
	    public void Add_New_Interaction_Details__Disposition_Missing_Validation(Map<Object, Object> testdata) throws InterruptedException 
	    {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		 try {
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	 
		    	// Step 2: Retrieve the "Action Onwer" data from the test data
		        String Actionowner = testdata.get("ActionOwner").toString();
		        updationofdispositionMasterPage.selectNextActionOwner(Actionowner);
		        ExtentTestManager.getTest().log(Status.PASS, "Selected \"Internal User\" as the Next Action Owner");
		        ExtentTestManager.getTest().log(Status.PASS, "left Disposition, Sub Disposition, Next Action Date, and Remarks empty");
		        updationofdispositionMasterPage.clickSaveButton();
		        ExtentTestManager.getTest().log(Status.PASS, "clicked on the Save button.");
		 }
	        Assert.assertEquals(updationofdispositionMasterPage.getErrorMessageafterenteronlyactionowner(), "Select Disposition");
	        ExtentTestManager.getTest().log(Status.PASS, "Error message \"Select Disposition\" is displayed");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(UpdationofDispositionRepo.errorMessage3));
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000);
	    }
	 
	 @Test(priority = 10, dataProvider = "TestData")
	    public void Add_New_Interaction_Details__Sub_Disposition_Missing_Validation(Map<Object, Object> testdata) throws InterruptedException {
		 try {
			 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
			 
			 String disposition = testdata.get("Disposition").toString();
			 
			 updationofdispositionMasterPage.selectDisposition(disposition);
			 ExtentTestManager.getTest().log(Status.PASS, "Selected \"Internal User\" as the Next Action Owner");
			 ExtentTestManager.getTest().log(Status.PASS, "selected \"Direct call\" as the Disposition");
			 ExtentTestManager.getTest().log(Status.PASS, "left Sub Disposition, Next Action Date, and Remarks empty");
		        updationofdispositionMasterPage.clickSaveButton();
		        ExtentTestManager.getTest().log(Status.PASS, "clicked on the Save button.");
		 }
		 Assert.assertEquals(updationofdispositionMasterPage.getErrorMessageafterenterdisposition(), "Select Sub Disposition");
		 ExtentTestManager.getTest().log(Status.PASS, "Error message \"Select Sub Disposition\" is displayed");
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(UpdationofDispositionRepo.errorMessage4));
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000);
	    }
	 
	 @Test(priority = 11, dataProvider = "TestData")
	    public void Add_New_Interaction_Details__NextAction_Date_Missing_Validation(Map<Object, Object> testdata) throws InterruptedException {
		 try {
			 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
			 
			 String subdisposition = testdata.get("Subdisposition").toString();
			 
			 updationofdispositionMasterPage.selectsubDisposition(subdisposition);
			 ExtentTestManager.getTest().log(Status.PASS, "Selected \"Internal User\" as the Next Action Owner");
			 ExtentTestManager.getTest().log(Status.PASS, "selected \"Direct call\" as the Disposition");
			 ExtentTestManager.getTest().log(Status.PASS, "selected \"Mobile\" as the Sub Disposition");
			 ExtentTestManager.getTest().log(Status.PASS, "left Next Action Date and Remarks empty");
		        updationofdispositionMasterPage.clickSaveButton();
		        ExtentTestManager.getTest().log(Status.PASS, "clicked on the Save button");
		 }
		 Assert.assertEquals(updationofdispositionMasterPage.getErrorMessageafterentersubdisposition(), "Select Next Action Date");
		 ExtentTestManager.getTest().log(Status.PASS, "Error message \"Select Next action date\" is displayed");
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(UpdationofDispositionRepo.errorMessage5));
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000);
	    }
	 
	 @Test(priority = 12, dataProvider = "TestData")
	    public void Add_New_Interaction_Details__Remark_Missing_Validation(Map<Object, Object> testdata) throws InterruptedException {
		 try {
			 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
			 
			 String date = testdata.get("Date").toString(); 
			 
			 updationofdispositionMasterPage.enterNextActionDate(date);
			 ExtentTestManager.getTest().log(Status.PASS, "Selected \"Internal User\" as the Next Action Owner");
			 ExtentTestManager.getTest().log(Status.PASS, "selected \"Direct call\" as the Disposition");
			 ExtentTestManager.getTest().log(Status.PASS, "selected \"Mobile\" as the Sub Disposition");
			 ExtentTestManager.getTest().log(Status.PASS, "selected a date for the Next Action Date");
			 ExtentTestManager.getTest().log(Status.PASS, "left Remarks empty");
		        updationofdispositionMasterPage.clickSaveButton();
		        ExtentTestManager.getTest().log(Status.PASS, "clicked on the Save button");
		 }
		 Assert.assertEquals(updationofdispositionMasterPage.getErrorMessageafterenterdate(), "Remarks is required");
		 ExtentTestManager.getTest().log(Status.PASS, "Error message \"Remark is required\" is displayed");
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(UpdationofDispositionRepo.errorMessage6));
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000);
	    }
	 
	 @Test(priority = 13, dataProvider = "TestData")
	    public void Add_New_Interaction_Details__Successful_Form_Submission(Map<Object, Object> testdata) throws InterruptedException {
		 try {
			 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
			 
			 String remarks = testdata.get("Remarks").toString(); 
			  
			 updationofdispositionMasterPage.enterRemarks(remarks);
			 ExtentTestManager.getTest().log(Status.PASS, "Selected \"Internal User\" as the Next Action Owner");
			 ExtentTestManager.getTest().log(Status.PASS, "selected \"Direct call\" as the Disposition");
			 ExtentTestManager.getTest().log(Status.PASS, "selected \"Mobile\" as the Sub Disposition");
			 ExtentTestManager.getTest().log(Status.PASS, "selected a date for the Next Action Date");
			 ExtentTestManager.getTest().log(Status.PASS, "entered \"test remarks\" in the Remarks field");
		        updationofdispositionMasterPage.clickSaveButton();
		        ExtentTestManager.getTest().log(Status.PASS, "clicked on Save");
		        ExtentTestManager.getTest().log(Status.PASS, "clicked on the close button on the Deviation Request pop-up.");
		 }
		 Assert.assertTrue(updationofdispositionMasterPage.isSuccessMessageDisplayed(), "Success message not displayed");
		 ExtentTestManager.getTest().log(Status.PASS, "Message \"Saved successfully\" is displayed");
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(UpdationofDispositionRepo.successMessage));
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000);
	    }
	 
	 @Test(priority = 14, dataProvider = "TestData")
	    public void Verify_successfully_saved_interaction_in_history_of_interaction_details_section(Map<Object, Object> testdata) throws InterruptedException {
		 try {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		 WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.loginusername));
		 WebElement userid = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.loginuserid));
		// Get the current date
	        LocalDate currentDate = LocalDate.now();
	     // Define the custom date format
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
	        // Format the current date
	        String formattedDate = currentDate.format(formatter);
	        if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	 
		        String Actionowner = testdata.get("ActionOwner").toString();
		        String Disposition = testdata.get("Disposition").toString();
		        String Subdisposition = testdata.get("Subdisposition").toString();
		        String Remarks = testdata.get("Remarks").toString();
		        String date = testdata.get("Interactiondetailsdate").toString();
		        String actualdateformat = date.replace("-", "/");
		        String userName = username.getText();
		        String userId = userid.getText();
		       
		        
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 updationofdispositionMasterPage.enterAccountNumber();
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 updationofdispositionMasterPage.clickSearchButton();
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 ExtentTestManager.getTest().log(Status.PASS, "Navigated to the \"History Of Interaction Details\" area.");
		 wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.transactiondetails));
		 Assert.assertTrue(updationofdispositionMasterPage.isTransactionDisplayedWithExpectedDetails(formattedDate,Actionowner), 
                 "The newly added interaction details are not displayed as expected.");
		 // Verify all interaction details
		 updationofdispositionMasterPage.verifyInteractionDetails(Disposition, Subdisposition, 
				 Remarks, userName, userId, Actionowner, actualdateformat);
	        }
			 ExtentTestManager.getTest().log(Status.PASS, "Displays newly added interaction details with transaction date plus next action owner as heading and shows Disposition, Sub disposition, Remarks, Action done by, User EIN, Next Action Owner, Next Action Date details");
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
	  // Close the browser
	        if (driver != null) {
	            driver.quit();
	        }
	 }


}
