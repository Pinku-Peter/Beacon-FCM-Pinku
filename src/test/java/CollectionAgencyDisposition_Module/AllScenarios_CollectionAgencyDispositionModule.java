package CollectionAgencyDisposition_Module;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import java.sql.SQLException;
import java.time.Duration;
import com.BasePackage.Base_Class;
import com.BasePackage.DBUtils;
import com.BasePackage.DownloadedExcelReader;
import com.BasePackage.DownloadedExcelReader.DataSummary;
import com.BasePackage.ExecuteStoredProcedure;
import com.BasePackage.Login_Class;
import com.Page_Repository.CoreAutoAllocationRepo;
import com.Page_Repository.CoreManualAllocationRepo;
import com.Page_Repository.CoreRegularizationSummaryRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.testautomation.pages.CollectionAgencyDispositionPage;
import com.testautomation.pages.CoreAutoAllocationPage;
import com.testautomation.pages.CoreAutoAllocationPage.ProcedureResult;
import com.testautomation.pages.CoreManualAllocationPage;
import com.testautomation.pages.CoreRegularizationSummaryPage;
import com.testautomation.pages.UpdationofDispositionPage;
import bsh.ParseException;
import com.listeners.TestListener;



public class AllScenarios_CollectionAgencyDispositionModule {
	
	Base_Class baseclass;
	com.Utility.ExcelReader ExcelReader;
	WebDriver driver;
	private List<WebDriver> drivers = new ArrayList<>();
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	ExtentTest extenttest;
	Login_Class corelogin;
	Login_Class callcenterlogin;
	CollectionAgencyDispositionPage collectionagencydispositionPage;
	
	@BeforeSuite

	public void SetUp() throws Exception {
		
		baseclass = new Base_Class();
		corelogin = new Login_Class();
		corelogin.CoreLogin();
		driver = baseclass.getDriver(); // Retrieve the driver instance
		collectionagencydispositionPage = new CollectionAgencyDispositionPage(driver);
		ExcelReader = new com.Utility.ExcelReader("CollectionAgencyDispositionPage");
		TestListener = new TestListener();
		screenShot = new com.Utility.ScreenShot(driver);
	}
	
	@BeforeMethod
    public void setupTest(Method method) {
		//baseclass = new Base_Class();
	    //driver = baseclass.getDriver();
	    drivers.add(driver);
	    
	    //callcenterlogin = new Login_Class();
	    // Update the ScreenShot object with the new driver
	    //screenShot = new com.Utility.ScreenShot(driver);
    // Start a new ExtentTest for the current test method
        // Start a new ExtentTest for the current test method
        extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("Collection Agency Disposition");
    }
	
	@Test(priority = 1)
    public void testLoginAndNavigateToAgencyAccountAllocation() throws InterruptedException {

        // Navigate to Agency Account Allocation
		collectionagencydispositionPage.navigateToAgencyAccountAllocation();

        // Verify the page header
        String expectedHeader = "Agency Account Allocation";
        String actualHeader = collectionagencydispositionPage.getPageHeaderText();
        Assert.assertEquals(actualHeader, expectedHeader, "Page header did not match!");
    }
	
	 @Test(priority = 2)
	    public void testTruncateAndDeleteOperations() throws ClassNotFoundException, IOException {
	        try {
	        	String TruncateResult = collectionagencydispositionPage.truncateTable();
	        	String DeleteResult = collectionagencydispositionPage.deleteRecordsWhereDispositionDateIsToday();
	        	System.out.println(TruncateResult+"\n"+DeleteResult);
	            Assert.assertTrue(true, "Queries executed successfully without errors.");
	        } catch (SQLException e) {
	            Assert.fail("Database operation failed: " + e.getMessage());
	        }
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
	  // Close all tracked browser instances
	        for (WebDriver driverInstance : drivers) {
	            if (driverInstance != null) {
	                driverInstance.quit();
	            }
	        }

	        // Clear the list of drivers
	        drivers.clear();

	        System.out.println("All browser instances have been closed.");
	    }


}
