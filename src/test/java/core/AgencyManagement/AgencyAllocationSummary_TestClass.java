package core.AgencyManagement;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.WebDriver;
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
import com.Page_Repository.AgencyAllocationSummaryRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.listeners.TestListener;

public class AgencyAllocationSummary_TestClass {
	
	Base_Class baseclass;
	static com.Utility.ExcelReader ExcelReader;
	WebDriver driver;
	private List<WebDriver> drivers = new ArrayList<>();
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	ExtentTest extenttest;
	AgencyAllocationSummary_MainClass AgencyAllocationSummary;
	String UserID,Password,CoreUserName;
	
	@BeforeClass

	public void SetUp() throws Exception {
		
		baseclass = new Base_Class();
		TestListener = new TestListener();
	}
	
	@BeforeMethod
    public void setupTest(Method method) {
		driver = baseclass.getDriver(); 
		drivers.add(driver);
		AgencyAllocationSummary = new AgencyAllocationSummary_MainClass(driver); 
	    screenShot = new com.Utility.ScreenShot(driver);
        // Start a new ExtentTest for the current test method
        extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("Core-AgencyAllocationSummary");
    }
	
	 @Test(priority = 1) 
	    public void Truncate_mst_col_agency_acc_allocated() throws ClassNotFoundException, SQLException, IOException, InterruptedException {
		 try { 
	        // Execute truncate operation and assert it was successful 
	        boolean isSuccessful = AgencyAllocationSummary.truncateTable(); 
	        ExtentTestManager.getTest().log(Status.PASS, "Execution of the query to truncate the table mst_col_agency_acc_allocated has been successfully completed.");
	        Assert.assertTrue(isSuccessful, "Table truncation failed");
	        ExtentTestManager.getTest().log(Status.PASS, "The query has been executed successfully without any errors.");
		 }
			catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
     throw e;
	 }
		Thread.sleep(3000); 
	    }
	 
	 @Test(priority = 2)
	    public void Login_to_core_application_and_navigate_to_agency_account_agency_menu() throws Exception {  
		 try { 
		 CoreUserName = Base_Class.configloader().getProperty("CoreUserName");
		 List<Object> userDetails = AgencyAllocationSummary.generateZoneUserDetails(CoreUserName); 
		 UserID = (String) userDetails.get(1);
		 Password = (String) userDetails.get(2);
		 String Result = (String) userDetails.get(3);
		 ExtentTestManager.getTest().log(Status.PASS, Result); 
	        // Step 1: Open the Core application and login
		 Login_Class.CoreLoginWithInputs(UserID,Password);  
		 driver = baseclass.getDriver();
		 AgencyAllocationSummary = new AgencyAllocationSummary_MainClass(driver);
		 screenShot = new com.Utility.ScreenShot(driver);
		 ExtentTestManager.getTest().log(Status.PASS, "Opened the Core application and Logged in with valid credentials.");
	        // Step 2: Navigate to Collection agency menu -> agency account allocation
		 AgencyAllocationSummary.navigateToAgencyAccountAllocation();
		 ExtentTestManager.getTest().log(Status.PASS, "Navigated to the Collection Agency menu → Agency Account Allocation.");
	        // Step 3: Verify that the Agency Account Allocation page is displayed
	        String Url = driver.getCurrentUrl();
	        Assert.assertTrue(Url.contains("AgencyAccountAllocation"), "URL does not contain the expected text - 'AgencyAccountAllocation'");
	        ExtentTestManager.getTest().log(Status.PASS, "The Agency Account Allocation page was displayed for the logged-in Core user. The page label 'Agency Account Allocation' was displayed.");
		 }
			catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
     throw e;
	 }
		Thread.sleep(3000);
	    }
	 
	 @Test(priority = 3, dataProvider = "TestData")
	    public void Allocating_accounts_to_collection_agency(Map<Object, Object> testdata) throws InterruptedException, ClassNotFoundException, SQLException, IOException {
		 try { 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180)); 
		 String TruncateResult = AgencyAllocationSummary.truncate_Table();
		 ExtentTestManager.getTest().log(Status.PASS, TruncateResult);
     		String Agency_Name = AgencyAllocationSummary.getAgencyName(CoreUserName); 
     		ExtentTestManager.getTest().log(Status.PASS, "Agency Name : "+Agency_Name); 
     		
     		if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
    	    	
    	        String value1 = testdata.get("AccountType").toString();
    	        String value2 = testdata.get("SMACategory").toString();
    	        String value3 = testdata.get("NPACategory").toString();
    	        String value4 = testdata.get("Region").toString();
    	        String value5 = testdata.get("DPDDaysOperator").toString();
    	        String value6 = testdata.get("DPDDays").toString();
     		
     		AgencyAllocationSummary.performSearch(value1,value2,value3,value4,value5,value6);
     		ExtentTestManager.getTest().log(Status.PASS, "Selected the following criteria: "+value1+","+value2+","+value3+","+value4+","+value5+","+value6);
     		}
     		ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Search button and waited until the total accounts selected and total outstanding amount column values were visible");
     		AgencyAllocationSummary.selectCollectionAgency(Agency_Name);
     		ExtentTestManager.getTest().log(Status.PASS, "Selected a collection agency from the \"Select Collection Agency\" dropdown : "+Agency_Name);
     		Map<String, Object> results = AgencyAllocationSummary.downloadResults();
     		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Download button to view the accounts with the search criteria. Results : "+results); 
     		AgencyAllocationSummary.allocateAccounts();
     		ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Allocate button.");
     		ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Ok button in the alert box.");
	        String successMessage = AgencyAllocationSummary.getSuccessMessage();
	        Assert.assertEquals("Allocated Successfully.", successMessage);
	        ExtentTestManager.getTest().log(Status.PASS, "A success message was displayed, and the accounts were allocated to the selected agency.");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(AgencyAllocationSummaryRepo.successAlert));
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 }
			catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
     throw e;
	 }
		Thread.sleep(3000);
	    }
	 
	 @Test(priority = 4)
	    public void Validate_Data_process_for_agency_allocation_Summary() throws InterruptedException { 
		 try { 
	        // Validate that new entries for allocated accounts are present
	        boolean areNewEntriesPresent = AgencyAllocationSummary.validateNewEntriesPresent(UserID);
	        Assert.assertTrue(areNewEntriesPresent, "New entries of allocated accounts should be present");
	        ExtentTestManager.getTest().log(Status.PASS, "Entries were added to the trn_ac_movement table with movement_type = '1' for the allocated accounts.");
		 }
			catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
     throw e;
	 }
		Thread.sleep(3000); 
	    }
	 
	 @Test(priority = 5)
	    public void Navigate_to_agency_allocation_summary_page() throws InterruptedException { 
		 try { 
		 AgencyAllocationSummary.clickAgencyManagement();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on Agency Management.");
		 AgencyAllocationSummary.clickAllocationSummary();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on Allocation Summary.");
	        // Assert that the correct page is displayed
	        String actualURL = driver.getCurrentUrl();
	        Assert.assertTrue(actualURL.contains("Agency/Allocationsummary"), "URL does not contain the expected text - 'Agency/Allocationsummary'");
	        ExtentTestManager.getTest().log(Status.PASS, "The Agency Allocation Summary page was displayed with the page name shown as 'Agency Account Allocation'.");
		 }
			catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
     throw e;
	 }
		Thread.sleep(3000); 
	    }
	 
	 @Test(priority = 6)
	    public void Search_Without_Selecting_Select_collection_agency_dropdown() throws InterruptedException { 
		 try { 
	        // Click on the Search button
		 AgencyAllocationSummary.clickSearchButton();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Search button without selecting value from Agency dropdown.");
	        // Assert the validation message is displayed as expected
	        Assert.assertEquals(AgencyAllocationSummary.getValidationMessage(), "Please Select Agency");
	        ExtentTestManager.getTest().log(Status.PASS, "A validation message 'Please Select Agency' was displayed.");
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
		 ExcelReader = new com.Utility.ExcelReader("Core-AgencyAllocationSummary");
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
