package core.SummaryReports;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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
import java.time.Duration;
import com.BasePackage.Base_Class;
import com.BasePackage.Login_Class;
import com.BasePackage.PropertiesFileUtil;
import com.Page_Repository.CoreCollectionOfficerConfigRepo;
import com.Page_Repository.UpdationofDispositionRepo;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.listeners.TestListener;

public class BankRegularizationSummary_TestClass {
	
	Base_Class baseclass;
	static com.Utility.ExcelReader ExcelReader;
	WebDriver driver;
	private List<WebDriver> drivers = new ArrayList<>();
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	ExtentTest extenttest;
	Login_Class corelogin;
	BankRegularizationSummary_MainClass BankRegularizationSummary;
	String loginUserId,Fromday,Today; 
	
	@BeforeClass

	public void SetUp() throws Exception {
		
		baseclass = new Base_Class();
		TestListener = new TestListener();
	}
	
	@BeforeMethod
    public void setupTest(Method method) {
		driver = baseclass.getDriver(); 
		drivers.add(driver);
		BankRegularizationSummary = new BankRegularizationSummary_MainClass(driver); 
	    screenShot = new com.Utility.ScreenShot(driver);
        // Start a new ExtentTest for the current test method
        extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("CoreBankRegularizationSummary");
    }
	
	 @Test(priority = 1) 
	    public void testNavigateToBankRegularizationSummary() throws Exception { 
		 
			// Login into the core application  
		 Login_Class.CoreLogin(); 
		 driver = baseclass.getDriver();
		 BankRegularizationSummary = new BankRegularizationSummary_MainClass(driver);
		 screenShot = new com.Utility.ScreenShot(driver);

		 BankRegularizationSummary.navigateToBankRegularizationSummary(); 
	        
	        // Assertion to check navigation success (can include URL verification or element visibility checks)
	        Assert.assertTrue(driver.getCurrentUrl().contains("BankRegularizationSummary"), "Navigation to Bank Regularization Summary page failed");
	    }
	 
	 @Test(priority = 2)
	    public void testInsertTrnAcMovement() throws IOException { 
		 
		 String fileName = "CoreBranchUserCredentials_CoreUserManagement_Branch_User_Creation.properties";
		 Properties properties = PropertiesFileUtil.ReadFromPropertiesFile(fileName);
		 loginUserId = properties.getProperty("Branch_User_ID");
		 
	     List<Object> result = BankRegularizationSummary.removeACMovementForMyDeskDashboard(loginUserId); 
	     
	     String FromDate = (String)result.get(1);
	     Fromday = FromDate.split("-")[0];
	     String Todate = (String)result.get(0);	
	     Today = Todate.split("-")[0];
	     String StatusMessage = (String)result.get(2);
	     
	     Assert.assertTrue(
	    		    result.contains("Accounts inserted into TRN_AC_MOVEMENT with  MOVEMENT TYPE=4 and MOVEMENT_TYPE_CATEGORY=REMOVED") ||
	    		    result.contains("Data already exist in TRN_AC_MOVEMENT."),
	    		    "Expected message is not present in the result: either 'Accounts inserted into TRN_AC_MOVEMENT with  MOVEMENT TYPE=4 and MOVEMENT_TYPE_CATEGORY=REMOVED' or 'Data already exist in TRN_AC_MOVEMENT'"
	    		);
	    }
	 
	 @Test(priority = 3)
	    public void validateDataExecutionForBankRegularizationSummary() throws IOException {
 
		 List<Object> result = BankRegularizationSummary.runBranchRegularizationDashboardLoad();

	        // Validate the expected results
		 List<Object> result2 = BankRegularizationSummary.checkBranchRegularizationDashboardData(loginUserId);
		 
		 String summaryStatus  = (String) result2.get(0);
		 String detailsStatus  = (String) result2.get(1); 
		 
		 System.out.println("DW_BRANCH_REGULARIZATION_SUMMARY table entry: " + summaryStatus);
		 System.out.println("DW_BRANCH_REGULARIZATION_DETAILS table entry: " + detailsStatus);
		 
		 Assert.assertTrue(
				    summaryStatus.equalsIgnoreCase("Yes") && detailsStatus.equalsIgnoreCase("Yes"),
				    "Branch regularization dashboard data check failed. Expected both to be 'Yes' but got: " +
				    "Summary = '" + summaryStatus + "', Details = '" + detailsStatus + "'"
				);
		 
	    }
	 
	 @Test(priority = 4, dataProvider = "TestData")
	    public void testValidateBankSummaryData(Map<Object, Object> testdata) {
		 
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
		        String value1 = testdata.get("Region").toString();
		        String value2 = testdata.get("Branch").toString();
	        // Step 1: Select 'Region' value
		        BankRegularizationSummary.selectRegion(value1);

	        // Step 2: Select 'Branch' value
		        BankRegularizationSummary.selectBranch(value2);

	        // Step 3: Set 'From Date'
		        BankRegularizationSummary.setFromDate(Fromday);

	        // Step 4: Set 'To Date'
		        BankRegularizationSummary.setToDate(Today);
	        
		 }

	        // Step 5: Click 'Search' button
		 BankRegularizationSummary.clickSearch();

	        // Validate expected results
		 	String gridDetails = BankRegularizationSummary.getdetailsfromgrid();
		    System.out.println("Grid Details (first 6): " + gridDetails);
		    Assert.assertFalse(gridDetails.isEmpty(), "Grid details should not be empty");
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
		 ExcelReader = new com.Utility.ExcelReader("CoreBankRegularizationSummary");
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
