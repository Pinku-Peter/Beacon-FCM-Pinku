package com.test.Disposition_masterModule;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
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
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.BasePackage.Base_Class;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.testautomation.pages.DispositionMasterPage;
import com.listeners.TestListener;
//import com.testautomation.pages.AllScenarios_Disposition_masterModule;

public class AllScenarios_Disposition_masterModule {

	com.Utility.ExcelReader ExcelReader;
	Base_Class baseclass;
	WebDriver driver;
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	DispositionMasterPage dispositionMasterPage;
	ExtentTest extenttest;
	@BeforeSuite

	public void SetUp() throws IOException, InterruptedException {
		
		ExcelReader = new com.Utility.ExcelReader("Disposition_master");
		
		baseclass = new Base_Class();
		TestListener = new TestListener();
		baseclass.SetUp();
		driver = baseclass.getDriver(); // Retrieve the driver instance
		screenShot = new com.Utility.ScreenShot(driver);
		dispositionMasterPage = new DispositionMasterPage(driver);
		//PageFactory.initElements(driver, this);
		// Initialize the dispositionPage object
		//dispositionMasterPage = PageFactory.initElements(driver, DispositionMasterPage.class);
	}

	@Test(priority = 1)
	public void testDispositionMasterNavigation() throws InterruptedException {
		 extenttest = ExtentTestManager.startTest("Disposition Master Test-Cases");
		
		try {
			extenttest.log(Status.INFO, "Step 1: Hover over the 'Disposition' main menu");
		// Step 1: Hover over the "Disposition" main menu
		Actions actions = new Actions(driver);
		actions.moveToElement(dispositionMasterPage.getDispositionMainMenu()).perform();
		extenttest.log(Status.PASS, "Hovered over the 'Disposition' main menu successfully.");
		extenttest.log(Status.INFO, "Step 2: Waiting for the 'Disposition Master' sub-menu to be visible");
		// Step 2: Wait until "Disposition Master" sub-menu is visible
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title='Disposition Master']")));
		Assert.assertNotNull(element, "Disposition Master sub-menu not found in DOM");
		extenttest.log(Status.PASS, "'Disposition Master' sub-menu is visible.");
		// Step 3: Click the "Disposition Master" sub-menu
		// Scroll into view and ensure visibility
		extenttest.log(Status.INFO, "Step 3: Clicking the 'Disposition Master' sub-menu");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);

		// Click the sub-menu
		try {
			element.click();
			extenttest.log(Status.PASS, "Clicked 'Disposition Master' sub-menu using standard click.");
		} catch (ElementNotInteractableException e) {
			js.executeScript("arguments[0].click();", element);
			extenttest.log(Status.WARNING, "'Disposition Master' sub-menu was not interactable; used JavaScript click instead.");
		}


		// Step 4: Verify that the Disposition is displayed
		extenttest.log(Status.INFO, "Step 4: Verifying the Disposition window is displayed");
		 Assert.assertTrue(dispositionMasterPage.isDispositionDisplayed(),
		            "Disposition window is not displayed.");
		 extenttest.log(Status.PASS, "Disposition window is displayed successfully.");

		// Step 5: Verify that the Sub-Disposition is displayed
		 extenttest.log(Status.INFO, "Step 5: Verifying the Sub-Disposition tab is displayed");
		Assert.assertTrue(dispositionMasterPage.isSubDispositionDisplayed(),
				"Sub-Disposition window is not displayed.");
		extenttest.log(Status.PASS, "Sub-Disposition tab is displayed successfully.");
		// Step 6: Verify the last link address
		extenttest.log(Status.INFO, "Step 6: Verifying the URL contains 'Admin/DispositionMaster'");
		String currentUrl = driver.getCurrentUrl();
		extenttest.log(Status.INFO, "Current URL: " + currentUrl);
		System.out.println("Current URL: " + currentUrl); // Log the URL for debugging
		String expectedUrlPart = "Admin/DispositionMaster";
		Assert.assertTrue(currentUrl.contains(expectedUrlPart),
				"The last link address does not match. Expected: " + expectedUrlPart);
		
		
		
		ExtentTestManager.getTest().log(Status.PASS, "TestCase_01 : Verify Disposition Master Navigation - Disposition master window opens, displaying Disposition and Sub Disposition tabs. Should show last link address as Admin/DispositionMaster.");
		}
		
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "TestCase_01 : Verify Disposition Master Navigation - Test Failed: " + e.getMessage());
            throw e;
        }
		Thread.sleep(3000);
		//ExtentTestManager.getTest().log(Status.FAIL, "Expected Result not matching with Actual Result");
	}
	@Test(priority = 2)
	public void verifyInitialLoadOfActiveDispositions() throws InterruptedException {
		//ExtentTestManager.startTest("TestCase_02 : Verify Initial Load of Active Dispositions");
		try {
		// Precondition: User is on the Disposition Master window
		Assert.assertTrue(dispositionMasterPage.isDispositionMasterPageDisplayed(), "Disposition Master page not displayed");
		int activeDispositionsCount = dispositionMasterPage.getActiveDispositionsCount();
		Assert.assertEquals(activeDispositionsCount, 10, "Active dispositions count does not equal 10");
		
		
		ExtentTestManager.getTest().log(Status.PASS, "TestCase_02 : Verify Initial Load of Active Dispositions - 10 active dispositions are displayed.");
//		ExtentTestManager.getTest().log(Status.FAIL, "Expected Result not matching with Actual Result");
		}
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "TestCase_02 : Verify Initial Load of Active Dispositions - Test Failed: " + e.getMessage());
            throw e;
		}
		Thread.sleep(3000);
	}

	@Test(priority = 3)
	public void actionOwnerDropdownSelections() throws IOException, InterruptedException {
		//ExtentTestManager.startTest("TestCase_03 : Action Owner Dropdown Selections");
		try {
		dispositionMasterPage.selectActionOwnerOptions();
		
		
		
		ExtentTestManager.getTest().log(Status.PASS, "TestCase_03 : Action Owner Dropdown Selections - Selected options should be visible and selectable.");
//		ExtentTestManager.getTest().log(Status.FAIL, "Expected Result not matching with Actual Result");
		}
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "TestCase_03 : Action Owner Dropdown Selections - Test Failed: " + e.getMessage());
            throw e;
		}
		Thread.sleep(3000);
	}

	@Test(priority = 4)
	public void testVerifyIsActiveCheckboxDefaultState() throws InterruptedException {
		//ExtentTestManager.startTest("TestCase_04 : Verify Is Active Checkbox Default State");
		try {
		Assert.assertTrue(dispositionMasterPage.isIsActiveCheckboxChecked(), "Is Active checkbox is not checked by default.");
		
		
		ExtentTestManager.getTest().log(Status.PASS, "TestCase_04 : Verify Is Active Checkbox Default State - Is Active checkbox is checked (active) by default.");
//		ExtentTestManager.getTest().log(Status.FAIL, "Expected Result not matching with Actual Result");
		}
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "TestCase_04 : Verify Is Active Checkbox Default State - Test Failed: " + e.getMessage());
            throw e;
		}
		Thread.sleep(3000);
	}

	@Test(priority = 5)
	public void verifyActionColumnOptions() throws InterruptedException {
		//ExtentTestManager.startTest("TestCase_05 : Verify Action Column Options");
		try {
		//dispositionMasterPage.navigateToDispositionMaster();
		Assert.assertTrue(dispositionMasterPage.verifyActionOptions());
		
		
		ExtentTestManager.getTest().log(Status.PASS, "TestCase_05 : Verify Action Column Options - Edit and Activate/Deactivate options are displayed.");
//		ExtentTestManager.getTest().log(Status.FAIL, "Expected Result not matching with Actual Result");
		}
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "TestCase_05 : Verify Action Column Options - Test Failed: " + e.getMessage());
            throw e;
		}
		Thread.sleep(3000);
	}

	@Test(priority = 6)
	public void verifyStatusColumnAppearance() throws InterruptedException {
		//ExtentTestManager.startTest("TestCase_06 : Verify Status Column Appearance");
		try {
		Assert.assertTrue(dispositionMasterPage.areAllStatusIconsGreenTicks(), "Not all status icons are green ticks");
		
		ExtentTestManager.getTest().log(Status.PASS, "TestCase_06 : Verify Status Column Appearance - A green tick icon is displayed, indicating active dispositions.");
//		ExtentTestManager.getTest().log(Status.FAIL, "Expected Result not matching with Actual Result");
		}
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "TestCase_06 : Verify Status Column Appearance - Test Failed: " + e.getMessage());
            throw e;
		}
		Thread.sleep(3000);
	}

	@Test(priority = 7)
    public void validatePaginationInitialLoad() throws InterruptedException {
		//ExtentTestManager.startTest("TestCase_07 : Validate Pagination - Initial Load");
		try {
        Assert.assertTrue(dispositionMasterPage.isPaginationCorrect());
        
		
		ExtentTestManager.getTest().log(Status.PASS, "TestCase_07 : Validate Pagination - Initial Load - Previous button is disabled, Page 1 is selected Next and >> buttons are enabled, No << button appears");
//		ExtentTestManager.getTest().log(Status.FAIL, "Expected Result not matching with Actual Result");
		}
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "TestCase_07 : Validate Pagination - Initial Load - Test Failed: " + e.getMessage());
            throw e;
		}
		Thread.sleep(3000);
    }
	
	@Test(priority = 8)
    public void testValidateNextButton() throws InterruptedException {
		//ExtentTestManager.startTest("TestCase_08 : Validate Next Button");
		try {
		dispositionMasterPage.clickNextPagination();
		
		
		ExtentTestManager.getTest().log(Status.PASS, "TestCase_08 : Validate Next Button - Moves to Page 2, Previous button is enabled, Next and >> buttons remain enabled, << button appears");
//		ExtentTestManager.getTest().log(Status.FAIL, "Expected Result not matching with Actual Result");
		}
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "TestCase_08 : Validate Next Button - Test Failed: " + e.getMessage());
            throw e;
		}
		Thread.sleep(3000);
    }
	
	 @Test(priority = 9)
	    public void testValidatePreviousButton() throws InterruptedException {
		 //ExtentTestManager.startTest("TestCase_09 : Validate Previous Button");
		 //page2.click();
		 try {
		 dispositionMasterPage.clickPreviousPagination();
		 
			
			ExtentTestManager.getTest().log(Status.PASS, "TestCase_09 : Validate Previous Button - Moves to Page 1, Previous button is disabled, Next and >> buttons are enabled, No << button appears.");
		 }
		 catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "TestCase_09 : Validate Previous Button - Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000);
	    }
	 
	 @Test(priority = 10)
	    public void testJumpToLastPage() throws InterruptedException {
		 //ExtentTestManager.startTest("TestCase_10 : Validate >> Button - Jump to Last Page");
		 try {
			 Thread.sleep(10000);	        
			 // Step 1: Click the last page button
	        dispositionMasterPage.clickLastPageButton();
	        ExtentTestManager.getTest().log(Status.PASS, "TestCase_10 : Validate >> Button - Jump to Last Page - Moves to the last page, Previous and << buttons are enabled, Next and >> buttons are disabled");
		 }
		 catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "TestCase_10 : Validate >> Button - Jump to Last Page - Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000);
	        
	    }
	 @Test(priority = 11)
	    public void testJumpToFirstPage() throws InterruptedException {
		 //ExtentTestManager.startTest("TestCase_11 : Validate << Button - Jump to First Page");
		 try { 
			 Thread.sleep(10000);
		 // Click the >> page button
		 dispositionMasterPage.clickFirstPageButton();
		 ExtentTestManager.getTest().log(Status.PASS, "TestCase_11 : Validate << Button - Jump to First Page - Moves to the last page, Previous and << buttons are enabled, Next and >> buttons are disabled");
		 }
		 catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "TestCase_11 : Validate << Button - Jump to First Page - Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000); 
	    }
	 
	 @Test(priority = 12)
	    public void testOpenAddDispositionForm() throws InterruptedException {
		 //ExtentTestManager.startTest("TestCase_12 : Open Add Disposition Form");
		 try {
	        // Step 1: Click on "Add Disposition" button
		 dispositionMasterPage.clickAddDispositionButton();
	        // Expected Result: Add Disposition popup opens with required fields and buttons
	        Assert.assertTrue(dispositionMasterPage.isPopupDisplayed(), "Add Disposition popup did not open as expected.");
	        ExtentTestManager.getTest().log(Status.PASS, "TestCase_12 : Open Add Disposition Form - Add Disposition popup opens with Action Owner, Name, Asset Category fields, and Submit, Close buttons.");
		 }
		 catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "TestCase_12 : Open Add Disposition Form - Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000); 
	    }
	 
	 @Test(priority = 13)
	    public void testCloseAddDispositionPopup() throws InterruptedException {
		 //ExtentTestManager.startTest("TestCase_13 : Close Add Disposition Form");
		 try {
	        // Close the popup
		 dispositionMasterPage.closeAddDispositionPopup();

	        // Verify the popup is closed
	        Assert.assertTrue(dispositionMasterPage.isPopupClosed(), "Add Disposition popup should be closed.");
	        ExtentTestManager.getTest().log(Status.PASS, "TestCase_13 : Close Add Disposition Form - Add Disposition popup is closed.");
		 }
		 catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "TestCase_13 : Close Add Disposition Form - Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000); 
	    }
	    
	 @Test(priority = 14, dataProvider = "TestData")
	    public void testSubmitWithAllFieldsValid(Map<Object, Object> testdata) throws InterruptedException {
		 
		 try {
		 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

				if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
					
					String actionowner = testdata.get("ActionOwner").toString();
					String name = testdata.get("Name").toString();
					String assetcategory = testdata.get("AssetCategory").toString();
					
					dispositionMasterPage.selectActionOwner(actionowner);
			        
			        // Step 2: Enter a valid name within 1 to 100 characters
				 dispositionMasterPage.enterName(name);
			        
			        // Step 3: Select "NPA Category" in Asset Category
				 dispositionMasterPage.selectAssetCategory(assetcategory);
				}
	        // Step 1: Select "Call centre" in Action Owner
		 
	        
	        // Step 4: Click on Submit
		 dispositionMasterPage.clickSubmit();
		// Validate the popup message
	
	        By popuppath = By.xpath("//div[@class='rz-growl-item']//div//span[contains(text(),'Success ')]");
	        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(popuppath));
	        Assert.assertTrue(popup.isDisplayed(), "Success popup is not displayed.");
	      
	        ExtentTestManager.getTest().log(Status.PASS, "TestCase_14 : Add Disposition - Submit with All Fields Valid");
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "TestCase_14 : Add Disposition - Submit with All Fields Valid - Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000); 
	    }
	 @Test(priority = 15, dataProvider = "TestData")
	    public void testMultiSelectActionOwner(Map<Object, Object> testdata) throws InterruptedException {
		 
		 try {
	        // Step 1: Click on the Action Owner dropdown
		 dispositionMasterPage.clickOnActionOwnerDropdown();
	        
	        // Step 2: Select multiple values
		// Step 1: Check if the test needs to be executed
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
		    	// Step 2: Retrieve the "ActionOwner" data from the test data
		        String[] actionOwners = testdata.get("ActionOwners").toString().split("\\|");;
		        
		     // Split the action owner values using the delimiter '|'
		       // String[] actionOwnerArray = actionOwners.split("\\|");
		        
		     // Step 4: Iterate through the action owner values and select them
		        for (String actionOwner : actionOwners) {
		            actionOwner = actionOwner.trim(); // Remove any leading/trailing spaces
		            dispositionMasterPage.selectActionOwners(actionOwner); // Ensure this method selects an item
		        }
		    }
		    // Step 5: Click outside the dropdown to close it
		 
		 By actionownerpath =By.xpath("//label[contains(text(),'Action Owner')]//following::div[1]");
	    	WebElement dropdown =  driver.findElement(actionownerpath);
	        dropdown.click();
	        ExtentTestManager.getTest().log(Status.PASS, "TestCase_15 : Action Owner Multi-select Functionality");
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "TestCase_15 : Action Owner Multi-select Functionality - Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000); 
		   // dispositionMasterPage.clickOnActionOwnerDropdown();
	        
	 }
	 
	 
	 @AfterMethod
	    public void takeScreenshotOnFailure(ITestResult result) throws IOException {
		 String methodName = result.getMethod().getMethodName();
		    String status = result.isSuccess() ? "Success" : "Failure";
		    try {
		    	File image =screenShot.takeScreenShot(methodName, status);
		    
		        ExtentTestManager.getTest().log(
		            result.isSuccess() ? Status.PASS : Status.FAIL,
		            "Screenshot captured for " + status + " test: " + methodName,MediaEntityBuilder.createScreenCaptureFromPath(image.getAbsolutePath()).build());


		    } catch (IOException e) {
		        System.err.println("Failed to capture screenshot: " + e.getMessage());
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

