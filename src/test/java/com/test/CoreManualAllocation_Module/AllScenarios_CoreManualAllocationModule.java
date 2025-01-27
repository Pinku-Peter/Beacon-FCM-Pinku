package com.test.CoreManualAllocation_Module;

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
import java.time.Duration;
import com.BasePackage.Base_Class;
import com.BasePackage.DownloadedExcelReader;
import com.BasePackage.DownloadedExcelReader.DataSummary;
import com.BasePackage.ExecuteStoredProcedure;
import com.BasePackage.Login_Class;
import com.Page_Repository.CoreAutoAllocationRepo;
import com.Page_Repository.CoreManualAllocationRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.testautomation.pages.CoreAutoAllocationPage;
import com.testautomation.pages.CoreAutoAllocationPage.ProcedureResult;
import com.testautomation.pages.CoreManualAllocationPage;
import bsh.ParseException;
import com.listeners.TestListener;


public class AllScenarios_CoreManualAllocationModule {
	private List<WebDriver> drivers = new ArrayList<>();
	private static String accountNumberFromExcel;
	Base_Class baseclass;
	com.Utility.ExcelReader ExcelReader;
	WebDriver driver;
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	ExtentTest extenttest;
	Login_Class callcenterlogin;
	CoreManualAllocationPage coremanualallocationpage;
	
	@BeforeSuite

	public void SetUp() throws Exception {
		
		baseclass = new Base_Class();
		callcenterlogin = new Login_Class();
		callcenterlogin.CallCenterLogin();
		driver = baseclass.getDriver(); // Retrieve the driver instance
		coremanualallocationpage = new CoreManualAllocationPage(driver);
		ExcelReader = new com.Utility.ExcelReader("Core_Manual_Allocation");
		TestListener = new TestListener();
		screenShot = new com.Utility.ScreenShot(driver);
	}
	
	@BeforeMethod
    public void setupTest(Method method) {
		 baseclass = new Base_Class();
		    driver = baseclass.getDriver();
		    drivers.add(driver);
		    coremanualallocationpage = new CoreManualAllocationPage(driver);
		    callcenterlogin = new Login_Class();
		    // Update the ScreenShot object with the new driver
		    screenShot = new com.Utility.ScreenShot(driver);
        // Start a new ExtentTest for the current test method
        extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("Call Centre ManualAllocation");
    }
	
	@Test(priority = 1)
    public void Login_to_call_centre_application_and_take_account_filtration_page() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		try {
			ExtentTestManager.getTest().log(Status.PASS, "Opened the FCM Call Centre application.");
			ExtentTestManager.getTest().log(Status.PASS, "Entered valid credentials and logged in.");
        //Navigate to Call Centre Main Menu
			coremanualallocationpage.navigateToMainMenu();
		ExtentTestManager.getTest().log(Status.PASS, "Navigated to the Call Centre Main Menu.");
        //Click on Account Filtration sub-menu
		coremanualallocationpage.navigateToAccountFiltration();
		ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Account Filtration sub-menu.");
        // Expected Result: User is navigated to Account Filtration page
        // URL shows CallCentre/CallCentreLeadFiltration
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("CallCentre/CallCentreLeadFiltration"), "Not navigated to Account Filtration page.");
        ExtentTestManager.getTest().log(Status.PASS, "User is navigated to Account Filtration page, URL shows CallCentre/CallCentreLeadFiltration.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		}
		
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
        }
		Thread.sleep(3000);
    }
	
	@Test(priority = 2)
    public void Allocated_To_dropdown_mandatory_checking() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		try {
			ExtentTestManager.getTest().log(Status.PASS, "Left the Asset Category and Allocated To fields empty.");
       //Click on the Search button
			coremanualallocationpage.clickSearchButton();
		ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Search button.");
        // Verify the warning message
        String expectedMessage = "Allocated To is Required";
        Assert.assertEquals(coremanualallocationpage.getWarningMessage(), expectedMessage, "Warning message not displayed as expected");
        ExtentTestManager.getTest().log(Status.PASS, "Displays warning message \"Allocated to is required\".");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreAutoAllocationRepo.warningmsg));
}
		
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
        }
		Thread.sleep(3000);
    }
	
	@Test(priority = 3, dataProvider = "TestData")
    public void Asset_category_dropdown_mandatory_checking(Map<Object, Object> testdata) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
	 try {
        
	 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
	    	
	        String value = testdata.get("AllocatedTo").toString();
	     // Select any value in Allocated To field
	        coremanualallocationpage.selectAllocatedTo(value);
	        ExtentTestManager.getTest().log(Status.PASS, "Left the Asset Category field empty and selected a value in the Allocated To field.");
	    }
	// Click Search button
	 coremanualallocationpage.clickSearchButtonaftergivingvalueforallocatedto();
	 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Search button.");
	// Verify the warning message
     Assert.assertEquals(coremanualallocationpage.getWarningMessageaftergivingvalueforallocatedto(), "Asset Category is Required");
        ExtentTestManager.getTest().log(Status.PASS, "Displays warning message  \"asset category is required\".");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreAutoAllocationRepo.warningmsg2));
	 }
        catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
	 }
	 Thread.sleep(3000); 
        
 }
	
	@Test(priority = 4)
    public void Select_SMA_and_NPA_Categories() throws InterruptedException {
		 try {
        // Open Asset Category dropdown and selecting all values
			 coremanualallocationpage.openAssetCategoryDropdown();
		ExtentTestManager.getTest().log(Status.PASS, "Opened the Asset Category dropdown.");
		ExtentTestManager.getTest().log(Status.PASS, "Selected the SMA category and NPA category.");
		ExtentTestManager.getTest().log(Status.PASS, "SMA and NPA categories are selected.");
	 }
        catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
	 }
	 Thread.sleep(3000);
    }
	
	@Test(priority = 5)
    public void SMA_Category_Selection() throws InterruptedException {
		 try {
        // Open SMA Category dropdown and selecting all values
			 coremanualallocationpage.openSMACategoryDropdown(); 
		ExtentTestManager.getTest().log(Status.PASS, "Opened the SMA Category dropdown.");
		ExtentTestManager.getTest().log(Status.PASS, "Selected SMA 0, SMA 1, and SMA 2 from the dropdown.");
		ExtentTestManager.getTest().log(Status.PASS, "SMA 0, SMA 1, SMA 2 should be selected.");
	 }
        catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
	 }
	 Thread.sleep(3000);
    }
	
	@Test(priority = 6)
    public void NPA_Category_Selection() throws InterruptedException {
		 try {
        // Open SMA Category dropdown and selecting all values
			 coremanualallocationpage.openNPACategoryDropdown(); 
		ExtentTestManager.getTest().log(Status.PASS, "Opened the NPA Category dropdown.");
		ExtentTestManager.getTest().log(Status.PASS, "Selected sub-standard, doubtful-1, doubtful-2, doubtful-3, and loss asset.");
		ExtentTestManager.getTest().log(Status.PASS, "Selected NPA categories sub-standard , doubtful-1 , doubtful-2,doubtful-3,loss asset should be selected from the dropdown");
	 }
        catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
	 }
	 Thread.sleep(3000);
    }
	
	@Test(priority = 7, dataProvider = "TestData")
  public void Allocation_Type_Selection_Manual_allocation(Map<Object, Object> testdata) throws InterruptedException {
	 try {
      
	 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
	    	
	        String value = testdata.get("AllocationType").toString();
	        coremanualallocationpage.selectAllocationType(value); 
	        ExtentTestManager.getTest().log(Status.PASS, "Opened the Allocation Type dropdown.");
	        ExtentTestManager.getTest().log(Status.PASS, "Selected Auto Allocation.");
	    }
      ExtentTestManager.getTest().log(Status.PASS, "Manual Allocation is selected.");
	 }
      catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
          throw e;
	 }
	 Thread.sleep(3000); 
      
}
	
	@Test(priority = 8)
  public void Perform_Search_with_Valid_Data() throws InterruptedException {
	try {
	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		ExtentTestManager.getTest().log(Status.PASS, "Ensured that the Asset Category, SMA Category, NPA Category, and Allocated To mandatory fields are filled.");
	 WebElement downloadbutton = driver.findElement(CoreAutoAllocationRepo.downloadbutton);
  	JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("arguments[0].scrollIntoView(true);", downloadbutton);
      Thread.sleep(500);
      // Perform search
      coremanualallocationpage.clickSearchButton(); 
		ExtentTestManager.getTest().log(Status.PASS, "Clicked the Search button successfully.");
	 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	 String gridText = coremanualallocationpage.getResultGridText();
   if (gridText.contains("No records to display.")) {
  	 Assert.assertEquals(gridText, "No records to display.", "Expected no records to display");
		ExtentTestManager.getTest().log(Status.PASS, "Verified that the grid displays the message \"No records to display\" when no data is available.");
   } else {
  	 System.out.println("Total accounts allocated");
  	 Assert.fail("Test failed because grid text did not contain 'No records to display'. Instead, it contains: records");
   }
	}
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
          throw e;
	 }
	Thread.sleep(3000);  
  }
	
	@Test(priority = 9)
  public void  Login_and_Navigation_to_Manual_Allocation() throws Exception {
		try {
		callcenterlogin.CoreLogin();
		driver = baseclass.getDriver(); // Update the driver after CoreLogin
		drivers.add(driver); // Add the new driver to the list
		coremanualallocationpage = new CoreManualAllocationPage(driver);
		
		
      Assert.assertTrue(coremanualallocationpage.isManualAllocationPageLoaded(), "Manual Allocation page not loaded correctly.");
      ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Call Centre menu.");
      ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Manual Allocation submenu successfully.");
      ExtentTestManager.getTest().log(Status.PASS, "Auto Allocation page is displayed with URL ending in `CallCentre/ManualAllocationConfiguration`");
		}
		catch (AssertionError | Exception e) { 
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
          throw e;
	 }
	Thread.sleep(3000); 
  }
	
	 @Test(priority = 10) 
   public void Verify_Fields_and_Buttons_on_Manual_Allocation_Page() throws InterruptedException {
   	try {
       
       Assert.assertTrue(coremanualallocationpage.areFieldsAndButtonsPresent(), "Fields or Add button not present on Manual Allocation page.");
       ExtentTestManager.getTest().log(Status.PASS, "Verified the presence of the following fields: Allocation Name\",\"Zone/CO\",\"Region\",\"Branch\",\"Branch ID\",\"Vertical\",\"Scheme Type\",\"Product Type\",\"Scheme Code\",\"Asset Tagging Type\",\"Asset Category\",\"SMA Category\",\"NPA Category\",\"DPD\",\"O/S Balance\",\"%Overdue to EMI\",\"Action Owner\",\"Action Date From\" ,\"Action Date to \",\"Types of Account\",\"Not Allocated\",\"To\",\"Is PFTNPA\",\"Is FTNPA\",\"Save This Allocation Criteria\" , Search button and reset button and edit allocation criteria button, assign button, download in excel button , assigned list button.");
       ExtentTestManager.getTest().log(Status.PASS, "All fields and Add button are present on the page");
   	}
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
           throw e;
	 }
	Thread.sleep(3000);
   }
	 
	 @Test(priority = 11)
	    public void Mandatory_Field_Validation_for_Asset_Category() throws InterruptedException {
		 try {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
	        // Test case for mandatory field validation
		 ExtentTestManager.getTest().log(Status.PASS, "Asset Category and To fields were left blank");
		 coremanualallocationpage.clickSearchbutton(); 
		 ExtentTestManager.getTest().log(Status.PASS, "Search button was clicked");
	        String warning = coremanualallocationpage.getWarningmessage();
	        Assert.assertEquals(warning, "Asset Category Required");
	        ExtentTestManager.getTest().log(Status.PASS, "After leaving the Asset Category and To fields blank and clicking the search button, a warning message 'Asset category is Required' appeared.");
	    	wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreManualAllocationRepo.warningMessage));
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }

	    @Test(priority = 12)
	    public void Asset_Category_SMA_and_NPA_Selection() throws InterruptedException {
	    	try {
	        // Test case for asset category selection
	    	coremanualallocationpage.selectAssetCategory(); 
	    	ExtentTestManager.getTest().log(Status.PASS, "Selected 'NPA' and 'SMA' category from the Asset Category dropdown.");
	    	 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }

	    @Test(priority = 13)
	    public void SMA_Category_Dropdown_Selection() throws InterruptedException {
	    	try {
	        // Test case for SMA category dropdown selection
	    		ExtentTestManager.getTest().log(Status.PASS, "Clicked on the SMA category dropdown.");
	    	coremanualallocationpage.selectSmaCategories();
	    	ExtentTestManager.getTest().log(Status.PASS, "Selected 'SMA 0', 'SMA 1', and 'SMA 2'.");
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }

	    @Test(priority = 14)
	    public void NPA_Category_Dropdown_Selection() throws InterruptedException {
	    	try {
	    		ExtentTestManager.getTest().log(Status.PASS, "Clicked on the NPA category dropdown.");
	        // Test case for NPA category dropdown selection
	    	coremanualallocationpage.selectNpaCategories();
	    	ExtentTestManager.getTest().log(Status.PASS, "Selected 'Sub-standard', 'Doubtful-1', 'Doubtful-2', 'Doubtful-3', and 'Loss asset'.");
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	    
	    @Test(priority = 15, dataProvider = "TestData")
	    public void OS_Balance_Field_Validation(Map<Object, Object> testdata) throws InterruptedException {
	    	try {
	    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
		        String value1 = testdata.get("OutstandingBalanceOperator").toString();
		        String value2 = testdata.get("OutstandingBalance").toString();
	    	coremanualallocationpage.clickOsBalanceField();
	    	ExtentTestManager.getTest().log(Status.PASS, "Clicked on the O/S Balance field.");
	    	coremanualallocationpage.selectEqualFinancialOperator(value1);
	    	coremanualallocationpage.enterOsBalance(value2);
	    	ExtentTestManager.getTest().log(Status.PASS, "Selected '=' from the 'Select Financial Operator' dropdown, and entered '1234' in the O/S Balance field.");
	    	}
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }

	    @Test(priority = 16, dataProvider = "TestData")
	    public void To_Field_Dropdown_Selection(Map<Object, Object> testdata) throws InterruptedException {
	    	try {
	    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
		        String value1 = testdata.get("To").toString();
		        coremanualallocationpage.selectCallCentreFromToDropdown(value1);
		        ExtentTestManager.getTest().log(Status.PASS, "Selected 'Call Centre' from the 'To' dropdown.");
	    	}
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }

	    @Test(priority = 17)
	    public void Search_with_Mandatory_Fields() throws InterruptedException {
	    	try {
	    	coremanualallocationpage.clickSearchBtn();
	    	ExtentTestManager.getTest().log(Status.PASS, "Clicked the search button.");
	    	WebElement totalAccountSelected = driver.findElement(CoreManualAllocationRepo.TotalAccountSelected); // Update with the actual locator
            WebElement totalOutstandingAmount = driver.findElement(CoreManualAllocationRepo.TotalOutStandingAmount); // Update with the actual locator
	    	 // Verify if the text for "Total account selected" is displayed and is not empty
	    	Assert.assertTrue(totalAccountSelected.isDisplayed(),"Total account selected is not displayed" );
	    	Assert.assertTrue(totalOutstandingAmount.isDisplayed(),"Total outstanding amount is not displayed");
	    	ExtentTestManager.getTest().log(Status.PASS, "The accounts count is displayed with the columns 'Total Account Selected' and 'Total Outstanding Amount'.");
	    	Assert.assertFalse(totalAccountSelected.getText().isEmpty(),"Total account selected text is empty");
            Assert.assertFalse(totalOutstandingAmount.getText().isEmpty(),"Total outstanding amount text is empty");
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
            
	    }

	    @Test(priority = 18)
	    public void Download_Excel_Functionality() throws InterruptedException {
	    	try {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
	    	coremanualallocationpage.clickDownloadExcelButton();
	    	ExtentTestManager.getTest().log(Status.PASS, "Clicked the 'Download in Excel' button.");
	    	ExtentTestManager.getTest().log(Status.PASS, "Excel file is downloaded");
	    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    	wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreManualAllocationRepo.downloadedmsg));
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	    
	    @Test(priority = 19)
	    public void Excel_Account_Count_Verification() throws IOException, InterruptedException {
	    	try {
	        // Step 1: Verify the account count in Excel matches the grid account count
	        String gridAccountCount = coremanualallocationpage.getAccountGridCount();
	        DownloadedExcelReader.DataSummary dataSummary = DownloadedExcelReader.getAccountNumberSummary();
	        int excelAccountCount = dataSummary.getRowCount();

	        // Assertion to verify that the account count matches
	        Assert.assertEquals(Integer.parseInt(gridAccountCount), excelAccountCount,
	                "Account count in grid and Excel should match.");
	        ExtentTestManager.getTest().log(Status.PASS, "Verified that the account count in the Excel file matches the account count displayed in the grid.");
	        ExtentTestManager.getTest().log(Status.PASS, "The account count matches in both the grid and the downloaded Excel file.");
	     // Step 2: Get all account numbers from the Excel file
	        List<String> excelAccountNumbers = dataSummary.getAccountNumbers();

	        // Optional: Log the account numbers or perform additional validations
	        System.out.println("Account numbers from Excel: " + excelAccountNumbers);
	        
	        // Example: Assert that the list is not empty
	        Assert.assertFalse(excelAccountNumbers.isEmpty(), "Excel account numbers list should not be empty.");
	        
	     // Save one account number to the shared variable
	        accountNumberFromExcel = excelAccountNumbers.get(0); // Get the first account number
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	        
	    }
	    
	    @Test(priority = 20)
	    public void Login_to_call_centre_application_and_take_updation_of_disposition_page() throws Exception {
	  		callcenterlogin.CallCenterLogin();
	  		driver = baseclass.getDriver(); // Update the driver after CoreLogin
	  		coremanualallocationpage = new CoreManualAllocationPage(driver);
	  		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
	  		try {
	  			
	  			
	        //Navigate to Call Centre Main Menu
	  			coremanualallocationpage.navigateTodispostionMainMenu();
	  			coremanualallocationpage.enterAccountNumber(accountNumberFromExcel);
	  			ExtentTestManager.getTest().log(Status.PASS, "Entered the account number in the search tab.");
	  			coremanualallocationpage.clickSearchButon(); 
	  			ExtentTestManager.getTest().log(Status.PASS, "Clicked the search button.");
	  	        String warningMessage = coremanualallocationpage.getWarnMessage(); 
	  	        Assert.assertEquals("You are not authorized to do the disposition of this account number", warningMessage);
	  	      ExtentTestManager.getTest().log(Status.PASS, "After entering the account number in the search tab and clicking the search button, the warning message 'You are not authorized to do the disposition of this account number' appeared.");
	  }
	  		
	  		catch (AssertionError | Exception e) {
	  			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
	        }
	  		Thread.sleep(3000);
	    }
	    
		@Test(priority = 21)
		  public void  Login_and_Navigation_to_Manual_Allocation_Core_Application() throws Exception { 
				try {
				callcenterlogin.CoreLogin();
				driver = baseclass.getDriver(); // Update the driver after CoreLogin
				drivers.add(driver); // Add the new driver to the list
				coremanualallocationpage = new CoreManualAllocationPage(driver);
				
				
		      Assert.assertTrue(coremanualallocationpage.isManualAllocationPageLoaded(), "Manual Allocation page not loaded correctly.");
		      ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Call Centre menu.");
		      ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Manual Allocation submenu successfully.");
		      ExtentTestManager.getTest().log(Status.PASS, "The Manual Allocation page is displayed with the URL 'CallCentre/ManualAllocationConfiguration'.");
				}
				catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		          throw e;
			 }
			Thread.sleep(3000); 
		  }
		
		 @Test(priority = 22)
		    public void Mandatory_Field_Validation_for_Asset_Category_() throws InterruptedException {
			 try {
			 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
			 WebElement Searchbtn = driver.findElement(CoreManualAllocationRepo.Searchbtn);
			 JavascriptExecutor js = (JavascriptExecutor) driver;
		    	js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'start'});", Searchbtn);
		    	Thread.sleep(1000);		  
		    	ExtentTestManager.getTest().log(Status.PASS, "Did not enter values for the Asset Category and To fields.");
		    	// Test case for mandatory field validation
			 coremanualallocationpage.clickSearchbutton(); 
			 ExtentTestManager.getTest().log(Status.PASS, "Clicked the search button");
		        String warning = coremanualallocationpage.getWarningmessage();
		        Assert.assertEquals(warning, "Asset Category Required");
		        ExtentTestManager.getTest().log(Status.PASS, "After not entering values for the Asset Category and To fields and clicking the search button, the warning message 'Asset category is Required' appeared.");
		    	wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreManualAllocationRepo.warningMessage));
			 }
				catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		          throw e;
			 }
			Thread.sleep(3000);
		    }

		    @Test(priority = 23)
		    public void Asset_Category_SMA_and_NPA_Selection_() throws InterruptedException {
		    	try {
		        // Test case for asset category selection
		    	coremanualallocationpage.selectAssetCategory(); 
		    	ExtentTestManager.getTest().log(Status.PASS, "Selected 'NPA' category from the Asset Category dropdown and 'SMA' category from the dropdown.");
		    	}
				catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		          throw e;
			 }
			Thread.sleep(3000);
		    }

		    @Test(priority = 24)
		    public void SMA_Category_Dropdown_Selection_() throws InterruptedException {
		    	try {
		        // Test case for SMA category dropdown selection
		    	coremanualallocationpage.selectSmaCategories();
		    	ExtentTestManager.getTest().log(Status.PASS, "Clicked the SMA category dropdown");
		    	ExtentTestManager.getTest().log(Status.PASS, "selected 'SMA 0', 'SMA 1', and 'SMA 2'.");
		    	}
				catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		          throw e;
			 }
			Thread.sleep(3000);
		    }

		    @Test(priority = 25)
		    public void NPA_Category_Dropdown_Selection_() throws InterruptedException {
		    	try {
		    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		        // Test case for NPA category dropdown selection
		    	coremanualallocationpage.selectNpaCategories();
		    	ExtentTestManager.getTest().log(Status.PASS, "Clicked the NPA category dropdown");
		    	ExtentTestManager.getTest().log(Status.PASS, "Selected 'Sub-standard', 'Doubtful-1', 'Doubtful-2', 'Doubtful-3', and 'Loss asset'.");
		    	coremanualallocationpage.clickSearchbutton(); 
		    	String warning = coremanualallocationpage.getWarningmessageforTofieldmandatorychecking(); 
		        Assert.assertEquals(warning, "To is Required");
		        ExtentTestManager.getTest().log(Status.PASS, "After selecting values from the NPA category dropdown and clicking the search button, the warning message 'To is Required' appeared.");
		    	wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreManualAllocationRepo.Tofieldmandatorywarningmsg));
		    	}
				catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		          throw e;
			 }
			Thread.sleep(3000);
		    }
		    
//		    @Test(priority = 26, dataProvider = "TestData")
//		    public void OS_Balance_Field_Validation_(Map<Object, Object> testdata) throws InterruptedException {
//		    	try {
//		    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
//			    	
//			        String value1 = testdata.get("OutstandingBalanceOperator").toString();
//			        String value2 = testdata.get("OutstandingBalance").toString();
//		    	coremanualallocationpage.clickOsBalanceField();
//		    	ExtentTestManager.getTest().log(Status.PASS, "Clicked on the O/S Balance field.");
//		    	coremanualallocationpage.selectEqualFinancialOperator(value1);
//		    	coremanualallocationpage.enterOsBalance(value2);
//		    	ExtentTestManager.getTest().log(Status.PASS, "Selected '=' from the 'Select Financial Operator' dropdown, and entered '1234' in the O/S Balance field.");
//		    	}
//		    	}
//				catch (AssertionError | Exception e) {
//					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
//		           throw e;
//			 }
//			Thread.sleep(3000);
//		    }
//
//		    @Test(priority = 27, dataProvider = "TestData")
//		    public void To_Field_Dropdown_Selection_(Map<Object, Object> testdata) throws InterruptedException {
//		    	try {
//		    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
//			    	
//			        String value1 = testdata.get("To").toString();
//			        coremanualallocationpage.selectCallCentreFromToDropdown(value1);
//			        ExtentTestManager.getTest().log(Status.PASS, "Selected 'Call Centre' from the 'To' dropdown.");
//		    	}
//		    	}
//				catch (AssertionError | Exception e) {
//					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
//		           throw e;
//			 }
//			Thread.sleep(3000);
//		    }   
//		    
//		    @Test(priority = 28)
//		    public void Search_with_Mandatory_Fields_() throws InterruptedException {
//		    	try {
//		    	coremanualallocationpage.clickSearchBtn();
//		    	ExtentTestManager.getTest().log(Status.PASS, "Clicked the search button.");
//		    	WebElement totalAccountSelected = driver.findElement(CoreManualAllocationRepo.TotalAccountSelected); // Update with the actual locator
//	            WebElement totalOutstandingAmount = driver.findElement(CoreManualAllocationRepo.TotalOutStandingAmount); // Update with the actual locator
//		    	 // Verify if the text for "Total account selected" is displayed and is not empty
//		    	Assert.assertTrue(totalAccountSelected.isDisplayed(),"Total account selected is not displayed" );
//		    	Assert.assertTrue(totalOutstandingAmount.isDisplayed(),"Total outstanding amount is not displayed");
//		    	ExtentTestManager.getTest().log(Status.PASS, "The accounts count is displayed with the columns 'Total Account Selected' and 'Total Outstanding Amount'.");
//		    	Assert.assertFalse(totalAccountSelected.getText().isEmpty(),"Total account selected text is empty");
//	            Assert.assertFalse(totalOutstandingAmount.getText().isEmpty(),"Total outstanding amount text is empty");
//		    	}
//				catch (AssertionError | Exception e) {
//					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
//		           throw e;
//			 }
//			Thread.sleep(3000);
//	            
//		    }
//		    
//		 // Test case for 'Allocate to Field Dropdown Selection'
//		    @Test(priority = 29, dataProvider = "TestData")
//		    public void testAllocateToDropdownSelection(Map<Object, Object> testdata) {
//		    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
//			    	
//			        String value = testdata.get("AllocateTo").toString();
//		    	coremanualallocationpage.selectCallCentreFromAllocateToDropdown(value);
//		    	}
//		    }
//		    
//		    @Test(priority = 30, dataProvider = "TestData")
//		    public void testSelectCallCentreDropdown(Map<Object, Object> testdata) {
//		    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
//		    		String value = testdata.get("SelectCallCentre").toString();
//		        // Select "CallCentre 1" from dropdown
//		    		coremanualallocationpage.selectCallCentre(value);
//		    	}
//		       
//		    }
//		    
//		    @Test(priority = 31)
//		    public void testAssignButtonValidationMessage() {
//		        // Click the assign button
//		    	coremanualallocationpage.clickAssignButton();
//
//		        // Assert the validation message is "Assigned successfully"
//		        Assert.assertEquals(coremanualallocationpage.getValidationMessage(), "Assigned Successfully",
//		                "Validation message should be 'Assigned Successfully' after allocation.");
//		    }
		    
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
