package core.AlertsandNotifications;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import com.BasePackage.Base_Class;
import com.BasePackage.Login_Class;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.listeners.TestListener;

public class AlertsMaskAcAndMaskingAutho_TestClass {
	
	Base_Class baseclass;
	com.Utility.ExcelReader ExcelReader;
	WebDriver driver;
	private List<WebDriver> drivers = new ArrayList<>();
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	ExtentTest extenttest;
	Login_Class corelogin;
	AlertsMaskAcAndMaskingAutho_MainClass alertsmaskacandmaskingautho_mainclass;
	
	@BeforeClass

	public void SetUp() throws Exception {
		
		baseclass = new Base_Class();
		TestListener = new TestListener();
	}
	
	@BeforeMethod
    public void setupTest(Method method) {
		driver = baseclass.getDriver(); 
		drivers.add(driver);
		alertsmaskacandmaskingautho_mainclass = new AlertsMaskAcAndMaskingAutho_MainClass(driver);
	    screenShot = new com.Utility.ScreenShot(driver);
	    //ExcelReader = new com.Utility.ExcelReader("AlertsNotificationManagement");
        // Start a new ExtentTest for the current test method
        extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("CoreAlertsMaskAcAndMaskingAutho");
    }
	
	 @Test(priority = 1)
	    public void testLoginAndNavigateToMaskAccountWindow() throws Exception { 
	        // Login into the core application
		 Login_Class.CoreLogin();
		 driver = baseclass.getDriver();
		 alertsmaskacandmaskingautho_mainclass = new AlertsMaskAcAndMaskingAutho_MainClass(driver);
		 screenShot = new com.Utility.ScreenShot(driver);
	        // Navigate to the alerts and notification menu
		 alertsmaskacandmaskingautho_mainclass.clickOnAlertsAndNotifications();

	        // Click on the mask account window link
		 alertsmaskacandmaskingautho_mainclass.clickOnMaskAccountWindow();

	        // Assert the mask account window is displayed
	        Assert.assertTrue(alertsmaskacandmaskingautho_mainclass.isMaskAccountWindowDisplayed(), "Mask account window is not displayed");
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
