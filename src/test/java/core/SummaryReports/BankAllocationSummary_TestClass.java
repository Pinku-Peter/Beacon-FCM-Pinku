package core.SummaryReports;

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
import com.Page_Repository.CoreCollectionOfficerConfigRepo;
import com.Page_Repository.UpdationofDispositionRepo;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.listeners.TestListener;

import core.AlertsandNotifications.AlertsNoticeTypeManagement_MainClass;

public class BankAllocationSummary_TestClass {
	
	Base_Class baseclass;
	com.Utility.ExcelReader ExcelReader;
	WebDriver driver;
	private List<WebDriver> drivers = new ArrayList<>();
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	ExtentTest extenttest;
	Login_Class corelogin;
	BankAllocationSummary_MainClass bankallocationsummary;
	String userid,password,userName,userId;
	List<String> account_numbers_count;
	
	@BeforeClass

	public void SetUp() throws Exception {
		
		baseclass = new Base_Class();
		TestListener = new TestListener();
	}
	
	@BeforeMethod
    public void setupTest(Method method) {
		driver = baseclass.getDriver(); 
		drivers.add(driver);
		bankallocationsummary = new BankAllocationSummary_MainClass(driver); 
	    screenShot = new com.Utility.ScreenShot(driver);
        // Start a new ExtentTest for the current test method
        extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("CoreBankAllocationSummary");
    }
	
	@Test(priority = 1, dataProvider = "TestData")
    public void testSuccessfulAccountAllocation(Map<Object, Object> testdata) throws Exception {
	 
	// Login into the core application
	 Login_Class.CoreLogin(); 
	 driver = baseclass.getDriver();
	 bankallocationsummary = new BankAllocationSummary_MainClass(driver);
	 screenShot = new com.Utility.ScreenShot(driver);
	 
	 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
	    	
	        String value1 = testdata.get("AssetCategory").toString();
	        String value2 = testdata.get("SMACategory").toString();
	        String value3 = testdata.get("Region").toString();
	        String value4 = testdata.get("Branch").toString();
	        String value5 = testdata.get("OsBalanceOperator").toString(); 
	        String value6 = testdata.get("OsBalanceAmount").toString();
	        String value7 = testdata.get("AllocateTo").toString();
	 
	 List<Object> results1 = bankallocationsummary.deleteZoneUserToBranchUserAccountAllocation(value4,value7,"IBU0000240");
	 System.out.println("Status message: " + results1.get(0));
	 
	 List<Object> results2 = bankallocationsummary.insertBCOUser("IBU0000240");
	 System.out.println("Status message: " + results2.get(0));
	        
	        bankallocationsummary.selectSmaTile();
	        bankallocationsummary.selectAssetCategory(value1);
	        bankallocationsummary.selectSmaCategory(value2);
	        bankallocationsummary.selectRegion(value3);
	        bankallocationsummary.selectBranch(value4);
	        bankallocationsummary.selectDpdFinancialOperation(value5,value6);
	        bankallocationsummary.clickSearch();
	        bankallocationsummary.selectAllocateToBranch(value7);
	 }
	 bankallocationsummary.clickAllocate();
        
        // Assert the confirmation message is displayed
        Assert.assertTrue(bankallocationsummary.isConfirmationMessageDisplayed());
    }
	
	 @Test(priority = 2) 
	    public void testValidUserLogin() throws Exception{
		 
//		 List<Object> results = bankallocationsummary.createBranchUser();  
//
//	        // You can use the results if needed
//		 userid = (String) results.get(0);
//	     password = (String) results.get(1);
//	     String statusMessage = (String) results.get(2);
//	     System.out.println(statusMessage);  
		 Login_Class.CoreLoginWithInputs("IBU0000240","ses@987"); 
		 driver = baseclass.getDriver();
		 drivers.add(driver);
		 bankallocationsummary = new BankAllocationSummary_MainClass(driver);
		 screenShot = new com.Utility.ScreenShot(driver);
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		// Assertion for successful login
	        String expectedURL = "http://192.168.32.33:8599/Home";
	        Assert.assertEquals( driver.getCurrentUrl(),expectedURL);
	        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfigRepo.username));
			 userName = username.getText(); 
			 WebElement userid = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.loginuserid));
			 userId = userid.getText();
	    }
	 
	 @Test(priority = 3)
	    public void testAccountAllocation() throws InterruptedException {

		 bankallocationsummary.navigateToMyDesk();
		 bankallocationsummary.selectSmaFromBranchAttention();
		 bankallocationsummary.selectAllAccountsFromFirstPage();
		 account_numbers_count=bankallocationsummary.getAccountNumbersFromGrid();
		 bankallocationsummary.assignToBranch(userName);
		 bankallocationsummary.clickAssign();
	        Assert.assertTrue(bankallocationsummary.isConfirmationDisplayed(), "Accounts not Assigned successfully.");
	        
	    }
	 
	 @Test(priority = 4) 
	    public void validateDataExecutionForBankAllocationSummary() throws IOException {
	       
		 String statusMessage = bankallocationsummary.insertAccountsForUser(userId); 

		    System.out.println("Status message: " + statusMessage);

		    // You can verify the message contains expected text
		    Assert.assertTrue(statusMessage.equals("Accounts inserted into TRN_AC_MOVEMENT with MOVEMENT TYPE=1 and MOVEMENT_TYPE_CATEGORY=NEW"),"Expected success message not found!"); 
	    }
	 
	 @Test(priority = 5, dataProvider = "TestData")
	    public void testVerifyTodaysAccountsInBranchAllocation(Map<Object, Object> testdata) throws Exception { 
		 
		 Login_Class.CoreLogin(); 
		 driver = baseclass.getDriver();
		 bankallocationsummary = new BankAllocationSummary_MainClass(driver);
		 screenShot = new com.Utility.ScreenShot(driver);
		 
		 bankallocationsummary.openAllocationSummaryWindow(); 
		 
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
		        String value1 = testdata.get("Region").toString();
		        String value2 = testdata.get("Branch").toString();
		        String value3 = testdata.get("FromandToDates").toString(); 
		        String value4 = testdata.get("FromandToDates").toString();
		        
		        bankallocationsummary.fillMandatoryFields(value1, value2, value3, value4);  
	        
		 }
		 bankallocationsummary.clickSearchButton();
	        
	        // Verifying that today's allocations are displayed correctly
	        Assert.assertTrue(bankallocationsummary.areTodaysAllocationsDisplayed(account_numbers_count), "Today's allocated accounts are not displayed correctly.");
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
		public Object[][] gettestdate() throws IOException {
		 ExcelReader = new com.Utility.ExcelReader("CoreBankAllocationSummary");
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
