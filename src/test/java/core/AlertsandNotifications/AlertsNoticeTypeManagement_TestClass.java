package core.AlertsandNotifications;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.time.Duration;
import com.BasePackage.Base_Class;
import com.BasePackage.Login_Class;
import com.Page_Repository.CoreAlertsMaskAcAndMaskingAuthoRepo;
import com.Page_Repository.CoreAlertsNoticeTypeMa_NoticeAcRepo;
import com.Page_Repository.CoreAlertsTemplateManagementRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Page_Repository.UpdationofDispositionRepo;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.listeners.TestListener;

public class AlertsNoticeTypeManagement_TestClass {
	
	Base_Class baseclass;
	com.Utility.ExcelReader ExcelReader;
	WebDriver driver;
	private List<WebDriver> drivers = new ArrayList<>();
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	ExtentTest extenttest;
	Login_Class corelogin;
	AlertsNoticeTypeManagement_MainClass alertsnoticetypemanagement_mainclass;
	String searchValue,templatename,Noticecategory,Papertype,ProductCategory,account_number,User_ID;
	List<WebElement>  elements;
	
	@BeforeClass

	public void SetUp() throws Exception {
		
		baseclass = new Base_Class();
		TestListener = new TestListener();
	}
	
	@BeforeMethod
    public void setupTest(Method method) {
		driver = baseclass.getDriver(); 
		drivers.add(driver);
		alertsnoticetypemanagement_mainclass = new AlertsNoticeTypeManagement_MainClass(driver); 
	    screenShot = new com.Utility.ScreenShot(driver);
	    ExcelReader = new com.Utility.ExcelReader("CoreAlertsNoticeTypeMa.NoticeAc");
        // Start a new ExtentTest for the current test method
        extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("CoreAlertsNoticeTypeMaNoticeAc");
    }
	
	 @Test(priority = 1)
	    public void testSuccessfulLogin() throws Exception {
		   // Login into the core application
				 Login_Class.CoreLogin();
				 driver = baseclass.getDriver();
				 alertsnoticetypemanagement_mainclass = new AlertsNoticeTypeManagement_MainClass(driver);
				 screenShot = new com.Utility.ScreenShot(driver);
				 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
	        String expectedUrl = "http://192.168.32.33:8599/Home";
	        String actualUrl = driver.getCurrentUrl();
	        Assert.assertEquals(actualUrl, expectedUrl, "User is not redirected to the Home"); // Verify redirection to Home
	        WebElement userid = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.loginuserid));
	        User_ID = userid.getText();
	    }

	 @Test(priority = 2)
	    public void testAccessAlertsAndNotifications() throws InterruptedException {
	        // Step 1: Verify the "Alerts and Notifications" is visible in the menu
	        Assert.assertTrue(alertsnoticetypemanagement_mainclass.isAlertsAndNotificationsVisible(), 
	                "'Alerts and Notifications' menu item is not available");
	        
	        elements = driver.findElements(CoreAlertsMaskAcAndMaskingAuthoRepo.accountnumberfromdashboard);
			 account_number = elements.get(1).getText(); 
	        
	        // Click the "Alerts and Notifications" menu
	        alertsnoticetypemanagement_mainclass.clickOnAlertsAndNotifications(); 

	    }
	 
	 @Test(priority = 3)
	    public void testAddNewTemplate() {
	        // Step 1: Click on "Template Management" sub menu
		 alertsnoticetypemanagement_mainclass.clickTemplateManagement(); 

	        // Step 2: Click "Add Template" button
		 alertsnoticetypemanagement_mainclass.clickAddTemplate();

	        // Expected Result: New Template form is displayed
		 Assert.assertTrue(alertsnoticetypemanagement_mainclass.isAddTemplateFormDisplayed(), 
	                "'Add Template' form is not available");
	    }
	 
	 @Test(priority = 4, dataProvider = "TestData")
	    public void testCreateTemplateWithValidDetails(Map<Object, Object> testdata) throws IOException, ClassNotFoundException, SQLException, InterruptedException {
		 try {
			 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
		        String value1 = testdata.get("NotificationTypefortemplatecreation").toString();
		        String value2 = testdata.get("InitialStatusfortemplatecreation").toString();
		        String value3 = testdata.get("Categoryfortemplatecreation").toString();
		        templatename = testdata.get("TemplateNamefortemplatecreation").toString();
		        String insertQuery = "Check_Insert_ALT_Template_Placeholder";

	        // Step 2: Enter mandatory fields
		        alertsnoticetypemanagement_mainclass.templateselectNotificationType(value1);
		        alertsnoticetypemanagement_mainclass.templateenterInitialStatus(value2);
		        alertsnoticetypemanagement_mainclass.templateenterCategory(value3);
		        
		        alertsnoticetypemanagement_mainclass.enterTemplateName(templatename);
		        List<String> result = alertsnoticetypemanagement_mainclass.prepareAndExecuteInsertQuery(insertQuery);
		        searchValue = result.get(1);
		        alertsnoticetypemanagement_mainclass.enterTemplatebody(searchValue);
		        
		        alertsnoticetypemanagement_mainclass.deleteAlertTemplate(templatename);
		        
		        alertsnoticetypemanagement_mainclass.deleteNoticeType(templatename);  
		        
		        alertsnoticetypemanagement_mainclass.deleteAlertScheduleRecords(User_ID);
		        
		     // Step 3: Click Save
		        alertsnoticetypemanagement_mainclass.clickSave();
		        ExtentTestManager.getTest().log(Status.PASS, "\"Clicked 'Save'.\"");
	 }
	        // Validating the expected result
	        Assert.assertTrue(alertsnoticetypemanagement_mainclass.isSuccessMessageDisplayed(), "Success message not displayed");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreAlertsTemplateManagementRepo.templatecreationsuccessmsg));
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        ExtentTestManager.getTest().log(Status.PASS, "Pop-up closed, success message displayed, and template listed.\"");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage()); 
	           throw e;
		 }
			Thread.sleep(3000); 
	    }
	 
	 @Test(priority = 5, dataProvider = "TestData")
	    public void testCreateTemplateWithMissingDetails(Map<Object, Object> testdata) throws IOException, ClassNotFoundException, SQLException, InterruptedException {
		 try {
			 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
		        String value1 = testdata.get("NotificationTypefortemplatecreation").toString();
		        String value2 = testdata.get("InitialStatusfortemplatecreation").toString();
		        String value3 = testdata.get("Categoryfortemplatecreation").toString();
		        templatename = testdata.get("TemplateNamefortemplatecreation").toString();
		        String insertQuery = "Check_Insert_ALT_Template_Placeholder";
		        
		        // Step 1: Click "Add Template" button
				 alertsnoticetypemanagement_mainclass.clickAddTemplate();

	        // Step 2: Enter mandatory fields
		        alertsnoticetypemanagement_mainclass.templateselectNotificationType(value1);
		        alertsnoticetypemanagement_mainclass.templateenterInitialStatus(value2);
		        alertsnoticetypemanagement_mainclass.templateenterCategory(value3);
		        
		        //alertsnoticetypemanagement_mainclass.enterTemplateName(templatename);
		        List<String> result = alertsnoticetypemanagement_mainclass.prepareAndExecuteInsertQuery(insertQuery);
		        searchValue = result.get(1);
		        alertsnoticetypemanagement_mainclass.enterTemplatebody(searchValue);
	        
		       // alertsnoticetypemanagement_mainclass.deleteAlertTemplate(templatename); 
		        ExtentTestManager.getTest().log(Status.PASS, "\"Entered mandatory fields.\"");
		     // Step 3: Click Save
		        alertsnoticetypemanagement_mainclass.clickSave();
		        ExtentTestManager.getTest().log(Status.PASS, "\"Clicked 'Save'.\"");
	 }
	        // Validating the expected result
		 String actualErrorMessage = alertsnoticetypemanagement_mainclass.getTemplateNameErrorMessage();
	        Assert.assertEquals(actualErrorMessage, "Please enter template name.", "Error message is not as expected.");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.templateNameError)); 
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage()); 
	           throw e;
		 }
			Thread.sleep(3000);  
	    }
	 
	 @Test(priority = 6)
	    public void testDisplayAddNoticeTypePopUp() throws InterruptedException {
		 
		 	alertsnoticetypemanagement_mainclass.clickCancelbutton(); 
		 // Click the "Alerts and Notifications" menu
	        alertsnoticetypemanagement_mainclass.clickOnAlertsAndNotifications(); 
	        // Click on 'Notice Type Management' submenu
	        alertsnoticetypemanagement_mainclass.clickNoticeTypeManagementSubMenu();
	        // Click on 'Add Notice Type'
	        alertsnoticetypemanagement_mainclass.clickAddNoticeType();
	        // Assert the pop-up is displayed
	        Assert.assertTrue(alertsnoticetypemanagement_mainclass.isAddNoticeTypePopUpDisplayed(), "Add Notice Type pop-up is not displayed.");

	    }
	 
	 @Test(priority = 7, dataProvider = "TestData")
	    public void testSaveValidNoticeType(Map<Object, Object> testdata) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
			 	Noticecategory = testdata.get("Noticecategory").toString();
			 	String value2 = testdata.get("NoticeTypeName").toString();
			 	Papertype = testdata.get("Papertype").toString();
		        String value4 = testdata.get("CostPerNotice").toString();
		        ProductCategory = testdata.get("ProductCategory").toString();
		        
	        // Step 1: Fill in required fields
		        alertsnoticetypemanagement_mainclass.enterNoticedetails(Noticecategory,value2,Papertype,value4,ProductCategory,templatename); 
		 }
	        // Step 2: Select acknowledgement checkbox
		 alertsnoticetypemanagement_mainclass.selectAcknowledgementRequired();

	        // Step 3: Click save button
		 alertsnoticetypemanagement_mainclass.clickSaveforaddNoticeType();  

	        // Certifying that the 'Notice type is saved successfully' message appears
	        Assert.assertTrue(alertsnoticetypemanagement_mainclass.issuccessmessageDisplayed(), "Success Message is not displayed.");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.Noticecreationsuccessmessage));
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner)); 
	    }
	 
	 @Test(priority = 8, dataProvider = "TestData")
	    public void testAttemptSaveWithMissingFields(Map<Object, Object> testdata) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
		        String value1 = testdata.get("Noticecategory").toString();
		        String value2 = "";
		        String value3 = testdata.get("Papertype").toString();
		        String value4 = testdata.get("CostPerNotice").toString();
		        String value5 = testdata.get("ProductCategory").toString();
		        
		     // Click on 'Add Notice Type'
		        alertsnoticetypemanagement_mainclass.clickAddNoticeType();
		        
	        // Step 1: Fill in required fields
		        alertsnoticetypemanagement_mainclass.enterNoticedetails(value1,value2,value3,value4,value5,templatename); 
		 }
	        // Step 2: Select acknowledgement checkbox
		 alertsnoticetypemanagement_mainclass.selectAcknowledgementRequired();

	        // Step 3: Click save button
		 alertsnoticetypemanagement_mainclass.clickSaveforaddNoticeType();  

		 // Verify error message is displayed
	        String expectedError = "Please enter a notice type name.";
	        String actualError = alertsnoticetypemanagement_mainclass.getErrorMessage();
	        Assert.assertEquals(actualError, expectedError, "Expected error message for missing notice type.");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.errorMessage));
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    }
	 
	 @Test(priority = 9)
	    public void testCancelNoticeTypeCreation() {
	        // Execute the test: Click 'Cancel' button
		 alertsnoticetypemanagement_mainclass.clickCancel();

	        // Verify the expected outcome: Pop-up should be closed
	        Assert.assertTrue(alertsnoticetypemanagement_mainclass.isPopupClosed(), "The pop-up should be closed and no changes should be saved.");
	    }
	 
	 @Test(priority = 10)
	    public void testSearchFunctionality() {
	        // Verify search criteria and results
		 alertsnoticetypemanagement_mainclass.enterSearchCriteria(Noticecategory,Papertype,ProductCategory);
		 
		 alertsnoticetypemanagement_mainclass.clickSearchButton(); 
		 
	     Assert.assertTrue(alertsnoticetypemanagement_mainclass.isResultListFiltered(), "Results list was not filtered properly");
	    }
	 
	  @Test(priority = 11)
	    public void testViewPostedNotice() throws InterruptedException {
	        // Step 1: Click on Alerts and Notifications menu
	        alertsnoticetypemanagement_mainclass.clickOnAlertsAndNotifications(); 
	        
	        // Step 2: Click on View Posted Notice sub menu
	        alertsnoticetypemanagement_mainclass.clickViewPostedNoticeSubMenu();
	        
	        // Expected Result: Page with posted notices is displayed 
	        Assert.assertTrue(alertsnoticetypemanagement_mainclass.isPostedNoticePageDisplayed(), "Failed to display the posted notices page.");
	    }
	  
//	  @Test(priority = 12)
//	    public void testVerifyStatusChange() {
//		  
//		  String value = "C:\\Selenium WebDriver\\ExeLauncher\\ExeRunner.exe";
//	        // Verify initial status is "Pending"
//	        Assert.assertTrue(alertsnoticetypemanagement_mainclass.isPendingDisplayed(), "Expected 'Pending' status not displayed");
//	        
//	        alertsnoticetypemanagement_mainclass.executeExe(value);
//	        
//	        Assert.assertTrue(alertsnoticetypemanagement_mainclass.isProcessingDisplayed(), "Expected 'Processing' status not displayed");
//	        
//	        // Verify final status change to "Sent"
//	        Assert.assertTrue(alertsnoticetypemanagement_mainclass.isSentDisplayed(), "Expected 'Sent' status not displayed");
//	    }
	  
	  @Test(priority = 13)
	    // Test case for adding a new notice
	    public void testAddNewNotice() {
	        // Click on Add Notice button
		  alertsnoticetypemanagement_mainclass.clickAddNotice(); 

	        // Assert if the Add Notice form is displayed
	        Assert.assertTrue(alertsnoticetypemanagement_mainclass.isAddNoticeFormDisplayed(), "Add Notice form is not displayed");
	    }
	  
	 
	  
	  @Test(priority = 14)
	    public void testAccountNumberFieldBVAValidations() throws InterruptedException {
		  
		  boolean isBVAValid = alertsnoticetypemanagement_mainclass.validateAccountNumberBVA(); 
		  
		  Assert.assertTrue(isBVAValid, "Boundary Value Analysis (BVA) for Account Number failed.");
	    }
	  
	  @Test(priority = 15)
	    public void testAccountNumberFieldECPValidations() throws InterruptedException {
		  
		  boolean isECPValid = alertsnoticetypemanagement_mainclass.validateAccountNumberECP(); 
		  
		  Assert.assertTrue(isECPValid, "Equivalence Class Partitioning (ECP) for Account Number failed.");
	    }
	  
	  @Test(priority = 16)
	    public void testSubmitNoticeWithoutSelectingCategory() {
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	        // Entering valid data in Notice Category field
	        
		  alertsnoticetypemanagement_mainclass.enterNoticeCategory(Noticecategory);
	        // Clicking submit without selecting the category
		  alertsnoticetypemanagement_mainclass.clickSubmit();

	        // Asserting that the error message is displayed
	        Assert.assertEquals(alertsnoticetypemanagement_mainclass.getErrorMessageAddNotice(), "Please select a notice type name.", "Error message does not match expected output.");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.errorMessageAddNotice));
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    }
	  
	  @Test(priority = 17, dataProvider = "TestData")
	    public void testPostNewNotice(Map<Object, Object> testdata) {
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		  if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
		        String value1 = testdata.get("Noticecategory").toString();
		        String value2 = testdata.get("NoticeTypeName").toString();;

		        alertsnoticetypemanagement_mainclass.selectNoticeCategory(value1);
		        
		        alertsnoticetypemanagement_mainclass.selectNoticeTypeName(value2); 
		        
		        alertsnoticetypemanagement_mainclass.enterAccountNumberNoticePosting(account_number);
		  }
		  alertsnoticetypemanagement_mainclass.selectIncludeCoBorrower();
		  
		  alertsnoticetypemanagement_mainclass.clickPostButton();
	        
	        Assert.assertTrue(alertsnoticetypemanagement_mainclass.isSuccessMessageDisplayedNoticePosting(), "Success message is not displayed");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.successMessage));
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    }
	  
	  @Test(priority = 18)
	    public void testOpenNoticeAcknowledgementSection() throws InterruptedException { 
	        // Navigate to Alerts and Notifications
		  alertsnoticetypemanagement_mainclass.clickOnAlertsAndNotifications(); 
	        
	        // Click Notice Acknowledgement 
		  alertsnoticetypemanagement_mainclass.clickNoticeAcknowledgement();
	        
	        // Assert that Notice Acknowledgement section is open
	        Assert.assertTrue(alertsnoticetypemanagement_mainclass.isNoticeAcknowledgementSectionOpen(), "Notice Acknowledgement section did not open.");
	    }
	  
	  @Test(priority = 19, dataProvider = "TestData")
	    public void testEnterInvalidAccountNumber(Map<Object, Object> testdata) {
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		  if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
		        String value1 = testdata.get("InvalidAccountNumber").toString();
		  
	        // Enter invalid account number
		        alertsnoticetypemanagement_mainclass.enterInvalidAccountNumber(value1);
		  }
	        // Attempt to search
		  alertsnoticetypemanagement_mainclass.clicksearchButon();

	        // Verify error message is displayed
	        Assert.assertEquals(alertsnoticetypemanagement_mainclass.geterrorMsg(), "Invalid account number.");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.errorMsg));
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
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
	 
	 @AfterClass
	 public void afterEachTest() {
	     ExtentManager.getInstance().flush();
	  // Close all tracked browser instances
//	        for (WebDriver driverInstance : drivers) {
//	            if (driverInstance != null) {
//	                driverInstance.quit();
//	            }
//	        }
//
//	        // Clear the list of drivers
//	        drivers.clear();
//
//	        System.out.println("All browser instances have been closed.");
	    }


}
