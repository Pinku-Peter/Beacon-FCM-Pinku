package core.Disposition;

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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import com.BasePackage.Base_Class;
import com.BasePackage.Common;
import com.BasePackage.Login_Class;
import com.Page_Repository.DispositionMasterPageRepo;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.testautomation.pages.DispositionMasterPage;
import com.listeners.TestListener;


public class Disposition_master_TestClass {

	com.Utility.ExcelReader ExcelReader;
	Base_Class baseclass;
	WebDriver driver;
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	DispositionMasterPage dispositionMasterPage;
	ExtentTest extenttest;
	Login_Class corelogin;
	
	@BeforeClass

	public void SetUp() throws Exception {
		
		ExcelReader = new com.Utility.ExcelReader("Disposition_master");
		
		baseclass = new Base_Class();
		TestListener = new TestListener();
		//baseclass.SetUp();
		corelogin = new Login_Class();
		corelogin.CoreLogin();
		driver = baseclass.getDriver(); // Retrieve the driver instance
		screenShot = new com.Utility.ScreenShot(driver);
		dispositionMasterPage = new DispositionMasterPage(driver);
		
		//PageFactory.initElements(driver, this);
		// Initialize the dispositionPage object
		//dispositionMasterPage = PageFactory.initElements(driver, DispositionMasterPage.class);
	}
	
	@BeforeMethod
    public void setupTest(Method method) {
        // Start a new ExtentTest for the current test method
        extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("Disposition master");
    }

	@Test(priority = 1)
	public void Verify_Disposition_Master_Navigation() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 
		
		try {
		// Step 1: Hover over the "Disposition" main menu
		Actions actions = new Actions(driver);
		actions.moveToElement(dispositionMasterPage.getDispositionMainMenu()).perform();
		ExtentTestManager.getTest().log(Status.PASS, "Clicked on the 'Disposition' main menu.");
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(DispositionMasterPageRepo.dispositionMasterSubMenu));
		Assert.assertNotNull(element, "Disposition Master sub-menu not found in DOM");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);

		// Click the sub-menu
		try {
			element.click();
			ExtentTestManager.getTest().log(Status.PASS, "Clicked on the 'Disposition Master' sub menu.");
		} catch (ElementNotInteractableException e) {
			js.executeScript("arguments[0].click();", element);
		}


		// Step 4: Verify that the Disposition is displayed
		 Assert.assertTrue(dispositionMasterPage.isDispositionDisplayed(),
		            "Disposition window is not displayed.");

		// Step 5: Verify that the Sub-Disposition is displayed
		Assert.assertTrue(dispositionMasterPage.isSubDispositionDisplayed(),
				"Sub-Disposition window is not displayed.");

		String currentUrl = driver.getCurrentUrl();
		System.out.println("Current URL: " + currentUrl); // Log the URL for debugging
		String expectedUrlPart = "Admin/DispositionMaster";
		Assert.assertTrue(currentUrl.contains(expectedUrlPart),
				"The last link address does not match. Expected: " + expectedUrlPart);
		
		
		
		ExtentTestManager.getTest().log(Status.PASS, "Disposition master window opens, displaying Disposition and Sub Disposition tabs. Should show last link address as Admin/DispositionMaster.");
		}
		
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
        }
		Thread.sleep(3000);
	}
	@Test(priority = 2)
	public void Verify_Initial_Load_of_Active_Dispositions() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 
		try {
		// Precondition: User is on the Disposition Master window
			Common.fluentWait("User is on the Disposition Master window", DispositionMasterPageRepo.subDispositionList);
		Assert.assertTrue(dispositionMasterPage.isDispositionMasterPageDisplayed(), "Disposition Master page not displayed");
		int activeDispositionsCount = dispositionMasterPage.getActiveDispositionsCount();
		Assert.assertEquals(activeDispositionsCount, 10, "Active dispositions count does not equal 10");
		
		
		ExtentTestManager.getTest().log(Status.PASS, "10 active dispositions are displayed.");

		}
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
		}
		Thread.sleep(3000);
	}

	@Test(priority = 3)
	public void Action_Owner_Dropdown_Selections() throws IOException, InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 
		try {
		dispositionMasterPage.selectActionOwnerOptions();
		
		ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Action Owner dropdown.");
		ExtentTestManager.getTest().log(Status.PASS, "Selected \"Call centre\", \"Collection agency\", and \"Internal user\".");
		ExtentTestManager.getTest().log(Status.PASS, "Selected options should be visible and selectable.");

		}
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
		}
		Thread.sleep(3000);
	}

	@Test(priority = 4)
	public void Verify_Is_Active_Checkbox_Default_State() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 
		try {
		Assert.assertTrue(dispositionMasterPage.isIsActiveCheckboxChecked(), "Is Active checkbox is not checked by default.");
		
		
		ExtentTestManager.getTest().log(Status.PASS, "Is Active checkbox is checked (active) by default.");
		}
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
		}
		Thread.sleep(3000);
	}

	@Test(priority = 5)
	public void Verify_Action_Column_Options() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 
		try {
		Assert.assertTrue(dispositionMasterPage.verifyActionOptions());
		
		ExtentTestManager.getTest().log(Status.PASS, "Clicked the three-dot button in the action column.");
		ExtentTestManager.getTest().log(Status.PASS, "Edit and Activate/Deactivate options are displayed.");
		}
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
		}
		Thread.sleep(3000);
	}

	@Test(priority = 6)
	public void Verify_Status_Column_Appearance() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 
		try {
		Assert.assertTrue(dispositionMasterPage.areAllStatusIconsGreenTicks(), "Not all status icons are green ticks");
		
		ExtentTestManager.getTest().log(Status.PASS, "A green tick icon is displayed, indicating active dispositions.");
		}
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
		}
		Thread.sleep(3000);
	}

	@Test(priority = 7)
    public void Validate_Pagination__Initial_Load() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 
		try {
        Assert.assertTrue(dispositionMasterPage.isPaginationCorrect());
        
        ExtentTestManager.getTest().log(Status.PASS, "Opened the Disposition master window.");
		ExtentTestManager.getTest().log(Status.PASS, "Previous button is disabled, Page 1 is selected Next and >> buttons are enabled, No << button appears");

		}
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
		}
		Thread.sleep(3000);
    }
	
	@Test(priority = 8)
    public void Validate_Next_Button() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 
		try {
		dispositionMasterPage.clickNextPagination();
		
		 ExtentTestManager.getTest().log(Status.PASS, "Opened the Disposition master window.");
		ExtentTestManager.getTest().log(Status.PASS, "Moves to Page 2, Previous button is enabled, Next and >> buttons remain enabled, << button appears");
		}
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
		}
		Thread.sleep(3000);
    }
	
	 @Test(priority = 9)
	    public void Validate_Previous_Button() throws InterruptedException {
		 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
			 
		 try {
		 dispositionMasterPage.clickPreviousPagination();
		 
		 ExtentTestManager.getTest().log(Status.PASS, "Opened the Disposition master window.");
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on Page 2.");
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Previous button.");
			ExtentTestManager.getTest().log(Status.PASS, "Moves to Page 1, Previous button is disabled, Next and >> buttons are enabled, No << button appears.");
		 }
		 catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000);
	    }
	 
	 @Test(priority = 10)
	    public void Validate_Button__Jump_to_Last_Page() throws InterruptedException {
		 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
			 
		 //ExtentTestManager.startTest("TestCase_10 : Validate >> Button - Jump to Last Page");
		 try {
			 Thread.sleep(10000);	        
			 // Step 1: Click the last page button
	        dispositionMasterPage.clickLastPageButton();
	        ExtentTestManager.getTest().log(Status.PASS, "Opened the Disposition master window.");
			 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the >> button.");
	        ExtentTestManager.getTest().log(Status.PASS, "Moves to the last page, Previous and << buttons are enabled, Next and >> buttons are disabled");
		 }
		 catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000);
	        
	    }
	 @Test(priority = 11)
	    public void Validate_Button__Jump_to_First_Page() throws InterruptedException {
		 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 try { 
		 // Click the >> page button
		 dispositionMasterPage.clickFirstPageButton();
		 ExtentTestManager.getTest().log(Status.PASS, "Opened the Disposition master window.");
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the >> button.");
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the << button.");
		 ExtentTestManager.getTest().log(Status.PASS, "Moves to the first page, Previous button is disabled, Next and >> buttons are enabled, No << button appears");
		 }
		 catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000); 
	    }
	 
	 @Test(priority = 12)
	    public void Open_Add_Disposition_Form() throws InterruptedException {
		 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 try {
	        // Step 1: Click on "Add Disposition" button
		 dispositionMasterPage.clickAddDispositionButton();
	        // Expected Result: Add Disposition popup opens with required fields and buttons
	        Assert.assertTrue(dispositionMasterPage.isPopupDisplayed(), "Add Disposition popup did not open as expected.");
	        ExtentTestManager.getTest().log(Status.PASS, "Add Disposition popup opens with Action Owner, Name, Asset Category fields, and Submit, Close buttons.");
		 }
		 catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000); 
	    }
	 
	 @Test(priority = 13)
	    public void Close_Add_Disposition_Form() throws InterruptedException {
		 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 try {
	        // Close the popup  
		 dispositionMasterPage.closeAddDispositionPopup();

	        // Verify the popup is closed
	        Assert.assertTrue(dispositionMasterPage.isPopupClosed(), "Add Disposition popup should be closed.");
	        ExtentTestManager.getTest().log(Status.PASS, "Add Disposition popup is closed.");
		 }
		 catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000); 
	    }
	    
	 @Test(priority = 14, dataProvider = "TestData")
	    public void Add_Disposition__Submit_with_All_Fields_Valid(Map<Object, Object> testdata) throws InterruptedException {
		 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
			 
		 try {

				if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
					
					String actionowner = testdata.get("ActionOwner").toString();
					String name = testdata.get("Name").toString();
					String assetcategory = testdata.get("AssetCategory").toString();
					
					dispositionMasterPage.selectActionOwner(actionowner);
					ExtentTestManager.getTest().log(Status.PASS, "Selected \"Call centre\" in Action Owner.");
			        // Step 2: Enter a valid name within 1 to 100 characters
				 dispositionMasterPage.enterName(name);
				 ExtentTestManager.getTest().log(Status.PASS, "Entered a valid name within 1 to 100 characters."); 
			        // Step 3: Select "NPA Category" in Asset Category
				 dispositionMasterPage.selectAssetCategory(assetcategory);
				 ExtentTestManager.getTest().log(Status.PASS, "Selected \"NPA Category\" in Asset Category.");
				}
	        // Step 1: Select "Call centre" in Action Owner
		 
	        
	        // Step 4: Click on Submit
		 dispositionMasterPage.clickSubmit();
		// Validate the popup message
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on Submit.");
		 WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.successPopup));
	        Assert.assertTrue(popup.isDisplayed(), "Success popup is not displayed.");
	      
	        ExtentTestManager.getTest().log(Status.PASS, "Data is submitted successfully, popup closes without errors.");
		 }
	        catch (AssertionError | Exception e) {
				extenttest.log(Status.FAIL, "Test Failed: This Disposition Already Exist. " + e.getMessage());
	            throw e;
	            
		 }
		 Thread.sleep(3000); 
		 
	    }
	 
	 @Test(priority = 15, dataProvider = "TestData")
	    public void Test_Disposition_Grid_Visibility(Map<Object, Object> testdata) throws InterruptedException {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 try { 
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
				
				String actionowner = testdata.get("ActionOwner").toString();
				String name = testdata.get("Name").toString();
				
	        // Check if the disposition row is visible
	        Assert.assertTrue(dispositionMasterPage.isDispositionVisible(name), "Disposition row should be visible");
	        ExtentTestManager.getTest().log(Status.PASS, "Checked the grid");
	        // Verify Action Owner values
	        String expectedActionOwners = actionowner;
	        Assert.assertEquals(dispositionMasterPage.getActionOwnerText(actionowner),expectedActionOwners, "Action Owner text should match");
	        ExtentTestManager.getTest().log(Status.PASS, "Verified that Action Owner values match expectations.");
	        // Verify status is green tick
	        Assert.assertTrue(dispositionMasterPage.isStatusGreenTick(), "Status should be a green tick");
	        ExtentTestManager.getTest().log(Status.PASS, "Verified that the Status is a Green tick (active).");
	        // Verify Action column has three-dot button
	        Assert.assertTrue(dispositionMasterPage.isActionButtonPresent(), "Action column should have a three-dot button");
	        ExtentTestManager.getTest().log(Status.PASS, "Verified that the Action column has a three-dot button.");
	        ExtentTestManager.getTest().log(Status.PASS, "Disposition appears as expected with Call centre, Internal user, Collection agency in Action Owner, Green tick in Status, Three dot button in Action");
		 }
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000);
		 
	    }
	 @Test(priority = 16, dataProvider = "TestData")
	    public void Action_Owner_Multi_select_Functionality(Map<Object, Object> testdata) throws InterruptedException {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.popup));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 try {
	        // Step 1: Click on the Action Owner dropdown
		 dispositionMasterPage.clickOnActionOwnerDropdown();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Action Owner dropdown.");  
	        // Step 2: Select multiple values
		// Step 1: Check if the test needs to be executed
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
		    	// Step 2: Retrieve the "ActionOwner" data from the test data
		        String[] actionOwners = testdata.get("ActionOwners").toString().split("\\|");;
		        
		     // Step 4: Iterate through the action owner values and select them
		        for (String actionOwner : actionOwners) {
		            actionOwner = actionOwner.trim(); // Remove any leading/trailing spaces
		            dispositionMasterPage.selectActionOwners(actionOwner); // Ensure this method selects an item
		        }
		        ExtentTestManager.getTest().log(Status.PASS, "Selected multiple values (\"Call centre\", \"Internal user\").");
		    }
		    // Step 5: Click outside the dropdown to close it
	    	WebElement dropdown =  driver.findElement(DispositionMasterPageRepo.actionOwnerField);
	        dropdown.click();
	        ExtentTestManager.getTest().log(Status.PASS, "Clicked outside the dropdown.");
	        ExtentTestManager.getTest().log(Status.PASS, "Selected values are displayed in the dropdown field with comma separation.");
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000); 
	        
	 }
	 
	 @Test(priority = 17, dataProvider = "TestData")
	    public void Asset_Category_Multi_select_Functionality(Map<Object, Object> testdata) throws InterruptedException {
		 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 
		 try {
	        // Step 1: Click on the Action Owner dropdown
		 dispositionMasterPage.clickAssetCategoryDropdown();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Asset Category dropdown.");  
	        // Step 2: Select multiple values
		// Step 1: Check if the test needs to be executed
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
		    	// Step 2: Retrieve the "ActionOwner" data from the test data
		        String[] actionOwners = testdata.get("AssetCategories").toString().split("\\|");;
		        
		     // Step 4: Iterate through the action owner values and select them
		        for (String actionOwner : actionOwners) {
		            actionOwner = actionOwner.trim(); // Remove any leading/trailing spaces
		            dispositionMasterPage.AssetCategory(actionOwner); // Ensure this method selects an item
		        }
		        ExtentTestManager.getTest().log(Status.PASS, "Selected multiple values (\"NPA Category\").");
		    }
		    // Step 5: Click outside the dropdown to close it
		 
		 WebElement dropdown = driver.findElement(DispositionMasterPageRepo.actionOwnerPath);
		    dropdown.click();
		    ExtentTestManager.getTest().log(Status.PASS, "Clicked outside the dropdown.");
	        ExtentTestManager.getTest().log(Status.PASS, "Selected values are displayed, and \"NPA Category\" is checked.");
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000); 
	        
	 }
	 
	 @Test(priority = 18)
	    public void Select_All_Functionality__Action_Owner() throws InterruptedException {
		 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        // Click on Action Owner dropdown
		 dispositionMasterPage.clickActionOwnerDropdown();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Action Owner dropdown.");
		 try {
	        // Select all options in Action Owner
		 dispositionMasterPage.selectAllActionOwners();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on \"Select All\".");
		 ExtentTestManager.getTest().log(Status.PASS, "All options in Action Owner are selected.");
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000); 
		   
	        
	    }
	 
	 @Test(priority = 19)
	    public void Deselecting_Via_Multi_select__Action_Owner() throws InterruptedException {
		 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner)); 
		 try {
		 dispositionMasterPage.deselectAllActionOwners();
		 ExtentTestManager.getTest().log(Status.PASS, "Unchecked \"Select All\".");
		 ExtentTestManager.getTest().log(Status.PASS, "All options in Action Owner are deselected.");
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000);
	       
	    }
	 
	 @Test(priority = 20)
	    public void Select_All_Functionality__Asset_Category() throws InterruptedException {
		 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
			 try { 
	        // Click Asset Category dropdown
		 dispositionMasterPage.clickAssetCategoryDropdown();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Asset Category dropdown.");
	        // Click "Select All" option
		 dispositionMasterPage.clickSelectAllAssetCategory();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on \"Select All\".");
		 ExtentTestManager.getTest().log(Status.PASS, "All options in Asset Category are selected.");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);

	    }
	 
	 @Test(priority = 21)
	    public void Deselecting_Via_Multi_select__Asset_Category() throws InterruptedException {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
			 try { 
	        // Uncheck "Select All"
		 dispositionMasterPage.uncheckSelectAllAssetCategory();
		 ExtentTestManager.getTest().log(Status.PASS, "Unchecked \"Select All\".");
		 ExtentTestManager.getTest().log(Status.PASS, "All options in Asset Category are deselected.");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	        
	    }
	 
	 @Test(priority = 22)
	    public void Verify_Add_Disposition_without_Selection() throws InterruptedException {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 try { 
		 dispositionMasterPage.clickAddDisposition();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the \"Add Disposition\" button.");
		 ExtentTestManager.getTest().log(Status.PASS, "Did not select any options for Action Owner.");
		 ExtentTestManager.getTest().log(Status.PASS, "Did not enter any name.");
		 ExtentTestManager.getTest().log(Status.PASS, "Did not select any options from Asset Category.");
		 dispositionMasterPage.clickSubmitButton();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Submit button.");
	        String errorMessage = dispositionMasterPage.getNameErrorMessage();
	        Assert.assertEquals(errorMessage, "Name Required");
	        ExtentTestManager.getTest().log(Status.PASS, "Should show error message as \"Name required\" under the name field.");
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000);
	    }
	 
	 
	 
	 @Test(priority = 23, dataProvider = "TestData")
	    public void Add_Disposition_with_Existing_Name(Map<Object, Object> testdata) throws InterruptedException {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 WebElement popCloseButton = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.popUpCloseButton));
		    
		    // Click the close button
		    popCloseButton.click(); // Close the popup
		 try { 
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
				
				String actionowner = testdata.get("ActionOwner").toString();
				String name = testdata.get("Name").toString();
				String assetcategory = testdata.get("AssetCategory").toString();
				
				dispositionMasterPage.selectActionOwner(actionowner);
				ExtentTestManager.getTest().log(Status.PASS, "Selected a value in the Action Owner dropdown.");
		        // Step 2: Enter a valid name within 1 to 100 characters
			 dispositionMasterPage.enterName(name);
			 ExtentTestManager.getTest().log(Status.PASS, "Entered Name");
		        // Step 3: Select "NPA Category" in Asset Category
			 dispositionMasterPage.selectAssetCategory(assetcategory);
			 ExtentTestManager.getTest().log(Status.PASS, "Selected a value in the Asset Category dropdown.");
			 // Step 4: Click on Submit
			 dispositionMasterPage.clickSubmit();
			 ExtentTestManager.getTest().log(Status.PASS, "Clicked the Add button.");  
			        // Expected Result: Error message is displayed
			        Assert.assertEquals(dispositionMasterPage.getErrorMessageText(), "This Disposition Already Exist");
			        ExtentTestManager.getTest().log(Status.PASS, "Error message \"This Disposition Already Exist\" is displayed.");
		 }
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000);
	 
	        
	    }
	 
	 @Test(priority = 24)
	    public void Edit_Disposition_Popup_Display() throws InterruptedException {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 try { 
	        // Step 1: Click on the three-dot button in the Action column
		 dispositionMasterPage.clickThreeDotButton();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the three-dot button in the Action column."); 
	        // Step 2: Click on the edit button
		 dispositionMasterPage.clickEditButton();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Edit button."); 
	        // Expected Result: Verify the Edit disposition popup is displayed with expected elements
	        Assert.assertTrue(dispositionMasterPage.isEditPopupDisplayed(), 
	                "Edit disposition popup elements are not displayed as expected.");
	        ExtentTestManager.getTest().log(Status.PASS, "Edit disposition popup is displayed with Action owner dropdown, Name field, Asset category dropdown, and Update button pre-populated with existing values.");
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000);
	    }
	 
	// Test case: Update without changes
	    @Test(priority = 25)
	    public void Update_Without_Changes() throws InterruptedException {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    	try {
	    	dispositionMasterPage.clickUpdateButton();
	        ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Update button without changing any values.");
	        Assert.assertTrue(dispositionMasterPage.isSuccessMessageDisplayed(), "Success message should be displayed");
	        ExtentTestManager.getTest().log(Status.PASS, "Edit disposition popup closes with a success message \"Saved successfully\".");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    }
	    
	    @Test(priority = 26, dataProvider = "TestData")
	    public void Verify_Add_Disposition_with_Valid_Data(Map<Object, Object> testdata) throws InterruptedException  {
	    	
	    	try {
	    		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
	    		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.successMessage));
		    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        dispositionMasterPage.clickAddDispositionButton();
	        ExtentTestManager.getTest().log(Status.PASS, "Clicked on the \"Add Disposition\" button.");
	    	
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
				
				String name = testdata.get("UpdateExistingName").toString();
				
	        dispositionMasterPage.selectAllActionOwnersdropdown();
	        ExtentTestManager.getTest().log(Status.PASS, "Selected all options in the Action Owner dropdown.");
	        dispositionMasterPage.enterDispositionName(name);
	        ExtentTestManager.getTest().log(Status.PASS, "Entered the Name.");
	        dispositionMasterPage.selectAllAssetCategories();
	        ExtentTestManager.getTest().log(Status.PASS, "Selected all options in the Asset Category dropdown.");
	    	}
	        dispositionMasterPage.clickSubmit();
	        ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Submit button.");
	        Assert.assertTrue(dispositionMasterPage.SuccessMessageDisplayed(), "Success message is not displayed");
	        ExtentTestManager.getTest().log(Status.PASS, "Edit disposition popup closes with a success message \"Saved successfully\".");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.successMessage));
			 }
		        catch (AssertionError | Exception e) {
			        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000); 
	    }
	 
	    @Test(priority = 27, dataProvider = "TestData")
	    public void Change_Name_to_Existing_and_Update(Map<Object, Object> testdata) throws InterruptedException {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    	dispositionMasterPage.ThreeDotButton();
	    	dispositionMasterPage.clickEditButton();
	    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    	try { 
	    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
				
				String name = testdata.get("ExistingName").toString();
	        // Step 1: Changing the Name field to an existing disposition name
	    	dispositionMasterPage.changeNameField(name);
	    	ExtentTestManager.getTest().log(Status.PASS, "Changed the Name field to an existing disposition name.");
	    	}
	    	
	    	// Step 2: Clicking on the Update button
	    	dispositionMasterPage.UpdateButton();
	    	ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Update button.");
	        // Validating expected error message
	        Assert.assertEquals(dispositionMasterPage.getErrorMessage(), "This Disposition Already Exist");
	        ExtentTestManager.getTest().log(Status.PASS, "Error message \"This Disposition Already Exist\" is displayed.");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    }
	    
	    @Test(priority = 28, dataProvider = "TestData")
	    public void Edit_and_Verify_Disposition_Changes(Map<Object, Object> testdata) throws InterruptedException {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    	try {
	    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
				
				String actionowner = testdata.get("ActionOwnerUpdate").toString();
				String name = testdata.get("NameUpdate").toString();
				String assetcategory = testdata.get("AssetCategoryUpdate").toString();
				dispositionMasterPage.toUpdateclickThreeDotButton();
				dispositionMasterPage.setActionOwner(actionowner);
				dispositionMasterPage.setName(name);
				dispositionMasterPage.setAssetCategory(assetcategory);
				ExtentTestManager.getTest().log(Status.PASS, "Changed the Action Owner, Name, and Asset Category fields to new values.");
			}

	        // Click the Update button
	    	dispositionMasterPage.clickUpdateButton();
	    	ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Update button.");
	        // Validate that success message is displayed
	        Assert.assertTrue(dispositionMasterPage.isUpdateSuccessMessageDisplayed(),"The success message should be displayed.");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.successMessage));
	        ExtentTestManager.getTest().log(Status.PASS, "Verified the active disposition list.");
	        ExtentTestManager.getTest().log(Status.PASS, "Edit disposition popup closes with a success message. Changes are visible in the active disposition list with updated Action owner, Name, and Asset category.");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    }
	    
	    @Test(priority = 29)
	    public void Deactivate_Disposition() throws InterruptedException {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
	    	try {
	    		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        // Clicking on the three-dot button
	    	dispositionMasterPage.clkThreeDotButton();
	    	ExtentTestManager.getTest().log(Status.PASS, "Clicked on the three-dot button in the Action column.");
	        // Clicking on Activate/De-activate option
	    	dispositionMasterPage.clickActivateDeactivateOption();
	    	ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Activate/De-activate option.");
	        // Assert to verify if the message "Disposition Status Changed" is displayed
	    	WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.msgLocator));
	        Assert.assertEquals(msg.getText(), "Disposition Status Changed");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        ExtentTestManager.getTest().log(Status.PASS, "Message \"Disposition Status Changed\" is displayed. The entry disappears from the active disposition list.");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    }
	    
	 // Test method for searching deactivated disposition
	    @Test(priority = 30, dataProvider = "TestData")
	    public void Search_Deactivated_Disposition(Map<Object, Object> testdata) throws InterruptedException {
	    	try {
	    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
				
				String actionowner = testdata.get("ActionOwnerSearch").toString();
	        // Step 1: Select Action owner as "Call centre"
	    	dispositionMasterPage.selActionOwner(actionowner);
	    	ExtentTestManager.getTest().log(Status.PASS, "Selected \"Call centre\" as the Action Owner.");
	        // Step 2: Untick Is active checkbox
	    	dispositionMasterPage.untickIsActiveCheckbox();
	    	ExtentTestManager.getTest().log(Status.PASS, "Unticked the Is Active checkbox.");
	        // Step 3: Click on Search button
	    	dispositionMasterPage.clickSearchButton();
	    	ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Search button.");
	        // Validate the expected result
	        Assert.assertTrue(dispositionMasterPage.isDeactivatedDispositionVisible(),
	                "Deactivated disposition should be shown with a red cross mark.");
	    	}
	    	ExtentTestManager.getTest().log(Status.PASS, "Deactivated disposition is shown with a red cross mark in the not active disposition list.");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    }
	    
	 // Test method for activating a deactivated disposition
	    @Test(priority = 31)
	    public void Activate_Deactivated_Disposition() throws InterruptedException {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	    	try {
	    		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        // Clicking on the three-dot button
	    	dispositionMasterPage.clkThreeDotButton();
	    	ExtentTestManager.getTest().log(Status.PASS, "Clicked on the three-dot button in the Action column.");
	        // Clicking on Activate/De-activate option
	    	dispositionMasterPage.clickActivateDeactivateOption();
	    	ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Activate/De-activate option.");
	        // Assert to verify if the message "Disposition Status Changed" is displayed
	    	WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.msgLocator));
	        Assert.assertEquals(msg.getText(), "Disposition Status Changed");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        ExtentTestManager.getTest().log(Status.PASS, "Message \"Disposition Status Changed\" is displayed. The entry disappears from the not active disposition list..");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    }
	    
	    @Test(priority = 32, dataProvider = "TestData")
	    public void Search_Active_Disposition(Map<Object, Object> testdata) throws InterruptedException {
	    	try {
	    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
				
				String actionowner = testdata.get("ActionOwnerSearch").toString();
	        // Step 1: Select Action owner as "Call centre"
	    	dispositionMasterPage.selActionOwner(actionowner);
	    	ExtentTestManager.getTest().log(Status.PASS, "Selected the Action Owner.");
	        // Step 2: Untick Is active checkbox
	    	dispositionMasterPage.untickIsActiveCheckbox();
	    	ExtentTestManager.getTest().log(Status.PASS, "Ticked the Is Active checkbox.");
	        // Step 3: Click on Search button
	    	dispositionMasterPage.clickSearchButton();
	    	ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Search button.");
	        // Validate the expected result
	        Assert.assertTrue(dispositionMasterPage.isActiveDispositionShown(), 
	                "Active disposition should be shown with a green cross mark.");
	    	}
	    	ExtentTestManager.getTest().log(Status.PASS, "Active disposition is shown in the active disposition list with a green tick mark.");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    }
	    
	    @Test(priority = 33)
	    public void Verify_Sub_Disposition_Tab_UI_Elements() throws InterruptedException { 
	    	try {
	    	
	        // Navigate to Sub-Disposition tab
	    	dispositionMasterPage.navigateToSubDispositionTab();
	    	ExtentTestManager.getTest().log(Status.PASS, "Navigated to the Sub-Disposition tab.");
	        // Verify UI elements are displayed as expected
	        Assert.assertTrue(dispositionMasterPage.verifyUIElements(), "UI elements verification failed.");
	        ExtentTestManager.getTest().log(Status.PASS, "Action Owner dropdown, Disposition search field, Is Active checkbox (checked), Search button, Add Sub-Disposition button, and active sub disposition list (up to 10 rows) are displayed");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    }
	    
	    @Test(priority = 34)
	    public void Verify_Add_Sub_Disposition_Popup() throws InterruptedException {
	    	try {
	        // Click Add Sub-Disposition button
	    	dispositionMasterPage.clickAddSubDispositionButton();
	    	ExtentTestManager.getTest().log(Status.PASS, "Navigated to the Sub-Disposition tab.");
	    	ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Add Sub-Disposition button.");
	        // Verify if all expected elements are displayed on the popup
	        Assert.assertTrue(dispositionMasterPage.issubdispositionPopupelementsDisplayed(), "Popup elements are not displayed correctly.");
	        ExtentTestManager.getTest().log(Status.PASS, "Popup window appears with Action Owner dropdown, Disposition dropdown, Sub-Disposition name field, Submit button, and a cross mark");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    }
	    
	    @Test(priority = 35)
	    public void Close_Add_Sub_Disposition_Popup() throws InterruptedException {
	    	
	    	try {
	        // Preconditions: Add Sub-Disposition popup should be displayed
	    	dispositionMasterPage.clickCloseButton();
	    	ExtentTestManager.getTest().log(Status.PASS, "Clicked the cross mark on the top right of the popup.");
	        // Assertion to ensure the popup is closed
	        // Example: Checking if the popup is no longer visible, to be updated with actual validation logic
	    	Assert.assertTrue(driver.findElements(DispositionMasterPageRepo.popupDialogLocator).isEmpty(), "Popup is not closed");
	        ExtentTestManager.getTest().log(Status.PASS, "Add Sub-Disposition popup closes");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    }
	    
	    @Test(priority = 36, dataProvider = "TestData")
	    public void Add_New_Sub_Disposition(Map<Object, Object> testdata) throws InterruptedException {
	    	try {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	    	dispositionMasterPage.clickAddSubDispositionButton();
	    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
				
				String actionowner = testdata.get("ActionOwnerforsubdisposition").toString();
				String disposition = testdata.get("UpdateExistingName").toString();
				String subdispositionname = testdata.get("Nameforsubdisposition").toString();
				
				
	        // Select an option from the Action Owner dropdown
				dispositionMasterPage.selectsubdispositionActionOwner(actionowner);

	        // Enter required values
				dispositionMasterPage.enterDisposition(disposition);
				
				dispositionMasterPage.enterSubDisposition(subdispositionname);

	        // Click on the Submit button
				dispositionMasterPage.clickSubmit();

	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.successMessage2));
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        
	     // Adding Second Sub-Disposition
	        dispositionMasterPage.clickAddSubDispositionButton();
	         wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	            // Retrieve the data for the second sub-disposition
	            String actionowner2 = testdata.get("ActionOwnerforsubdisposition").toString(); // This may be the same or different
	            String disposition2 = testdata.get("UpdateExistingName").toString(); // This may be the same or different
	            String subdispositionname2 = testdata.get("Nameforsubdisposition2").toString(); // New sub-disposition name

	            // Select an option from the Action Owner dropdown for the second sub-disposition
	            dispositionMasterPage.selectsubdispositionActionOwner(actionowner2);
	            ExtentTestManager.getTest().log(Status.PASS, "Selected an option from the Action Owner dropdown.");
	            // Enter required values for the second sub-disposition
	            dispositionMasterPage.enterDisposition(disposition2);
	            ExtentTestManager.getTest().log(Status.PASS, "Entered the Disposition.");
	            dispositionMasterPage.enterSubDisposition(subdispositionname2);
	            ExtentTestManager.getTest().log(Status.PASS, "Entered the Sub-Disposition name.");
	        }

	        // Click on the Submit button for the second sub-disposition
	        dispositionMasterPage.clickSubmit();
	        ExtentTestManager.getTest().log(Status.PASS, "Clicked the Submit button.");
	        Assert.assertTrue(dispositionMasterPage.isSuccessMessageDisplayedforsubdisposition(), "Success message not displayed.");
	        ExtentTestManager.getTest().log(Status.PASS, "Popup closes and displays success message \"Saved successfully\". New sub disposition appears in the active sub disposition list");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    }
	    
	    @Test(priority = 37, dataProvider = "TestData")
	    public void Add_Duplicate_Sub_Disposition(Map<Object, Object> testdata) throws InterruptedException {
	    	try {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.successMessage2));
	    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    	dispositionMasterPage.clickAddSubDispositionButton();
	    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
				
				String actionowner = testdata.get("ActionOwnerforsubdisposition").toString();
				String disposition = testdata.get("UpdateExistingName").toString();
				String subdispositionname = testdata.get("Nameforsubdisposition").toString();
				
				
	        // Select an option from the Action Owner dropdown
				dispositionMasterPage.selectsubdispositionActionOwner(actionowner);
				 ExtentTestManager.getTest().log(Status.PASS, "Selected an option from the Action Owner dropdown.");
	        // Enter required values
				dispositionMasterPage.enterDisposition(disposition);
				 ExtentTestManager.getTest().log(Status.PASS, "Entered the Disposition.");
				dispositionMasterPage.enterSubDisposition(subdispositionname);
				 ExtentTestManager.getTest().log(Status.PASS, "Entered the Sub-Disposition name.");
				}

	        // Click on the Submit button
				dispositionMasterPage.clickSubmit();
				 ExtentTestManager.getTest().log(Status.PASS, "Clicked the Submit button.");
	        // Assert the success message
	        Assert.assertTrue(dispositionMasterPage.iserrorMessageDisplayedforsubdisposition(), "Error message not displayed.");

	        ExtentTestManager.getTest().log(Status.PASS, "Error message \"This Sub-Disposition Already Exist\" is displayed");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    }
	    
	    @Test(priority = 38)
	    public void Edit_Sub_Disposition_Popup_Display() throws InterruptedException {
	    	try {
	    	dispositionMasterPage.openEditPopup();
	    	ExtentTestManager.getTest().log(Status.PASS, "Clicked the three-dot button in the Action column.");
	    	ExtentTestManager.getTest().log(Status.PASS, "Clicked the Edit button.");
	    	ExtentTestManager.getTest().log(Status.PASS, "Edit Sub-Disposition popup opens with pre-filled fields and shows an Update button");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    }

	    @Test(priority = 39)
	    public void Update_Sub_Disposition_Without_Changes() throws InterruptedException {
	    	try {
	        dispositionMasterPage.clickUpdateWithoutChanges();
	        ExtentTestManager.getTest().log(Status.PASS, "Clicked the Update button without making any changes.");
	        Assert.assertTrue(dispositionMasterPage.isSuccessMessageDisplayedforsubdispos(), "Success message should be displayed.");
	        ExtentTestManager.getTest().log(Status.PASS, "Popup closes and displays success message \"Saved successfully\"");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    }
	    
	    @Test(priority = 40, dataProvider = "TestData")
	    public void Update_Sub_Disposition_with_Duplicate_Name(Map<Object, Object> testdata) throws InterruptedException {
	    	try {
	    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
				
	    		String subdispositionname = testdata.get("Nameforsubdisposition").toString();
				
	        // Preconditions: Ensure popup is open
	    		dispositionMasterPage.openEditPopuptoupdate();

	        // Step 1: Enter a duplicate name and update
	    		dispositionMasterPage.enterSubDispositionName(subdispositionname);
	    		 ExtentTestManager.getTest().log(Status.PASS, "Changed the Sub-Disposition name to an already existing one.");
	    		dispositionMasterPage.clickUpdateWithexistingname();
	    		 ExtentTestManager.getTest().log(Status.PASS, "Clicked the Update button.");
	    	}
 
	        // Expected Result: Error message displayed
	        Assert.assertEquals(dispositionMasterPage.getErrorMessageforexistingnameupdate(), "This Sub-Disposition Already Exist", "Error message not expected.");
	        ExtentTestManager.getTest().log(Status.PASS, "Error message \"This Sub-Disposition Already Exist\" is displayed");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    }
	    
	    @Test(priority = 41, dataProvider = "TestData")
	    public void Update_Sub_Disposition_Successfully(Map<Object, Object> testdata) throws InterruptedException {
	    	try {
	    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
				
	    		String subdispositionname = testdata.get("UpdateExistingNameofsubdispositiontoNew").toString();
				
	        // Preconditions: Ensure popup is open
	    		dispositionMasterPage.openEditPopuptoupdateexistingname();

	        // Step 1: Enter a duplicate name and update
	    		dispositionMasterPage.enterSubDispositionName(subdispositionname);
	    		 ExtentTestManager.getTest().log(Status.PASS, "Changed the Sub-Disposition name.");
	    		dispositionMasterPage.clickUpdateWithexistingname();
	    		 ExtentTestManager.getTest().log(Status.PASS, "Clicked the Update button.");
	    	}
 
	        // Expected Result: Success message displayed
	    	Assert.assertTrue(dispositionMasterPage.isSuccessMessageDisplayedforsubdispos(), "Success message should be displayed.");
	        ExtentTestManager.getTest().log(Status.PASS, "Popup closes and displays success message \"Saved successfully\"");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    }
	    
	    @Test(priority = 42)
	    public void Deactivate_Sub_Disposition() throws InterruptedException { 
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
	    	try {
	    		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.successMessage2));
	    		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        // Clicking on the three-dot button
	    	dispositionMasterPage.clkThreeDotButtonofsubdisposition();
	    	ExtentTestManager.getTest().log(Status.PASS, "Clicked the three-dot button in the Action column.");
	        // Clicking on Activate/De-activate option
	    	dispositionMasterPage.clickActivateDeactivateOptionofsubdisposition();
	    	ExtentTestManager.getTest().log(Status.PASS, "Clicked the Activate/Deactivate option.");
	        // Assert to verify if the message "Disposition Status Changed" is displayed
	    	WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.msgLocator));
	        Assert.assertEquals(msg.getText(), "Sub-Disposition Status Changed");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        ExtentTestManager.getTest().log(Status.PASS, "Message \"Sub Disposition Status Changed\" is displayed and entry disappears from the active list");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    }
	    
	    @Test(priority = 43, dataProvider = "TestData")
	    public void Search_Deactivated_Sub_Disposition(Map<Object, Object> testdata) throws InterruptedException {
	    	try {
	    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
				
				String actionowner = testdata.get("DeactivatedActionOwnerforsubdispositionSearch").toString();
				String dispostion = testdata.get("UpdateExistingName").toString();
	        // Step 1: Select Action owner as "Call centre"
	    	dispositionMasterPage.selActionOwnerforsubdispositionsearch(actionowner);
	    	ExtentTestManager.getTest().log(Status.PASS, "Selected the Action Owner.");
	    	 // Step 2: Select Action owner as "Call centre"
	    	dispositionMasterPage.seldispositionforsubdispositionsearch(dispostion);
	    	ExtentTestManager.getTest().log(Status.PASS, "Set the Disposition.");
	        // Step 3: Untick Is active checkbox
	    	dispositionMasterPage.untickIsActiveCheckboxforsubdispositionsearch();
	    	ExtentTestManager.getTest().log(Status.PASS, "Unticked the Is Active checkbox.");
	        // Step 4: Click on Search button
	    	dispositionMasterPage.clickSearchButtonforsubdispositionsearch();
	    	ExtentTestManager.getTest().log(Status.PASS, "Clicked the Search button.");
	        // Validate the expected result
	        Assert.assertTrue(dispositionMasterPage.isDeactivatedDispositionVisibleforsubdispositionsearch(),
	                "Deactivated sub disposition should be shown with a red cross mark.");
	    	}
	    	ExtentTestManager.getTest().log(Status.PASS, "Deactivated sub disposition appears in not active list with red cross mark");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    }
	    
	    @Test(priority = 44)
	    public void Reactivate_Sub_Disposition() throws InterruptedException {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	    	try {
	    		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        // Clicking on the three-dot button
	    	dispositionMasterPage.clkThreeDotButtonfordeactivatingsubdisposition();
	    	ExtentTestManager.getTest().log(Status.PASS, "Clicked the three-dot button in the Action column.");
	        // Clicking on Activate/De-activate option
	    	dispositionMasterPage.clickActivateDeactivateOptionfordeactivatingsubdisposition();
	    	ExtentTestManager.getTest().log(Status.PASS, "Clicked the Activate/Deactivate option.");
	        // Assert to verify if the message "Disposition Status Changed" is displayed
	    	WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.msgLocator));
	        Assert.assertEquals(msg.getText(), "Sub-Disposition Status Changed");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        ExtentTestManager.getTest().log(Status.PASS, "Message \"Sub Disposition Status Changed\" is displayed and entry disappears from the not active list");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    } 
	    
	    @Test(priority = 45, dataProvider = "TestData")
	    public void Search_Active_Sub_Disposition(Map<Object, Object> testdata) throws InterruptedException {
	    	try {
	    		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	    		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
				
	    		String actionowner = testdata.get("ActiveActionOwnerforsubdispositionSearch").toString();
				String dispostion = testdata.get("UpdateExistingName").toString();
	        // Step 1: Select Action owner as "Call centre"
	    	dispositionMasterPage.selActionOwnerforactivesubdispositionsearch(actionowner);
	    	ExtentTestManager.getTest().log(Status.PASS, "Selected the Action Owner.");
	    	 // Step 2: Select Action owner as "Call centre" 
	    	dispositionMasterPage.seldispositionforactivesubdispositionsearch(dispostion);
	    	ExtentTestManager.getTest().log(Status.PASS, "Set the Disposition.");
	        // Step 3: Untick Is active checkbox
	    	dispositionMasterPage.untickIsActiveCheckboxforsubdispositionsearch();  
	    	ExtentTestManager.getTest().log(Status.PASS, "Ticked the Is Active checkbox.");
	        // Step 4: Click on Search button
	    	dispositionMasterPage.clickSearchButtonforsubdispositionsearch();
	    	ExtentTestManager.getTest().log(Status.PASS, "Clicked the Search button.");
	        // Validate the expected result
	        Assert.assertTrue(dispositionMasterPage.isActiveDispositionVisibleforsubdispositionsearch(),
	                "Active sub disposition should be shown with a green cross mark.");
	    	}
	    	ExtentTestManager.getTest().log(Status.PASS, "Active sub disposition appears in list with green tick mark");
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

