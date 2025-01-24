package com.testautomation.pages;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.BasePackage.Base_Class;
import com.BasePackage.DownloadedExcelReader;
import com.BasePackage.DownloadedExcelReader.DataSummary;
import com.Page_Repository.CoreAutoAllocationRepo;
import com.Page_Repository.CoreManualAllocationRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Utility.Log;
import java.sql.Connection;

import io.netty.handler.timeout.TimeoutException;

public class CoreManualAllocationPage {
	
	private WebDriver driver;
	
	public CoreManualAllocationPage(WebDriver driver) {
		Log.info("Initializing CallCenterAccountFiltrationPage...");
        this.driver = driver;
        Log.info("WebDriver instance assigned.");
        Log.info("Initializing Web elements using PageFactory...");
        PageFactory.initElements(driver, this);
        Log.info("Web elements initialized using PageFactory.");
        Log.info("CallCenterAccountFiltrationPage initialization completed.");
    }
	
	public void navigateToMainMenu() {
    	Log.info("Starting navigation to the Call Centre Main Menu...");
    	Log.info("Locating the Call Centre Main Menu element...");
    	WebElement callcenter = driver.findElement(CoreAutoAllocationRepo.callcentermainmenu);
    	Log.info("Call Centre Main Menu element located successfully.");
    	Log.info("Clicking on the Call Centre Main Menu...");
    	callcenter.click();
    	Log.info("Clicked on the Call Centre Main Menu. Navigation complete.");
    }
	
	// Method to navigate to Account Filtration page
    public void navigateToAccountFiltration() {
    	Log.info("Starting navigation to the Account Filtration submenu...");
    	Log.info("Locating the Account Filtration submenu element...");
    	WebElement accountfiltration = driver.findElement(CoreAutoAllocationRepo.accountfiltrationsubmenu);
    	Log.info("Account Filtration submenu element located successfully.");
    	Log.info("Clicking on the Account Filtration submenu...");
    	accountfiltration.click();
    	 Log.info("Clicked on the Account Filtration submenu. Navigation complete.");
    }
    
 // Method to click the Search button
    public void clickSearchButton() throws InterruptedException {
    	Log.info("Starting the process to click the Search button...");
    	Log.info("Waiting for the Search button to become visible...");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    	WebElement searchbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.searchbutton));
    	Log.info("Scrolling the Search button into view...");
    	JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", searchbutton); 
        Log.info("Search button scrolled into view."); 
        Log.info("Waiting for 500ms to ensure smooth scrolling...");
        Thread.sleep(500);  // Add a short wait for smooth scrolling
        Log.info("Wait completed.");
        Log.info("Clicking the Search button...");
    	searchbutton.click();
    	Log.info("Search button clicked successfully."); 
    }
    
    // Method to get the text of the warning message
    public String getWarningMessage() {
    	 Log.info("Starting the process to retrieve the warning message...");
    	 Log.info("Waiting for the warning message to become visible...");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    	WebElement warningMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.warningmsg));
    	Log.info("Retrieving the text from the warning message...");
        String message = warningMessage.getText();
        Log.info("Warning message retrieved successfully: " + message); // Log after retrieving the message

        return message;
    }
    
 // Method to select any value in Allocated To field
    public void selectAllocatedTo(String value) {
    	Log.info("Starting the process to select a value from the 'Allocated To' dropdown...");
    	Log.info("Waiting for the Allocated To dropdown to be visible...");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    	WebElement allocatedtodropdown = driver.findElement(CoreAutoAllocationRepo.allocatedtodropdown);
    	Log.info("Clicking on the Allocated To dropdown...");
    	allocatedtodropdown.click();
    	Log.info("Allocated To dropdown clicked.");
    	Log.info("Waiting for the spinner to disappear...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner is no longer visible.");
    	Log.info("Waiting for the value '" + value + "' to be visible...");
    	WebElement allocatedtovalue = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.allocatedtovalue(value)));
    	Log.info("Clicking on the value '" + value + "'...");
    	allocatedtovalue.click();
    	Log.info("Value '" + value + "' selected from the dropdown.");
    	Log.info("Waiting for the spinner to disappear after selecting the value...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner is no longer visible after selecting the value.");
    	Log.info("Clicking outside the dropdown to close it...");
    	WebElement outside = driver.findElement(CoreAutoAllocationRepo.outarea);
    	outside.click();
    	Log.info("Clicked outside the dropdown to close it.");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }
 // Method to click the Search button
    public void clickSearchButtonaftergivingvalueforallocatedto() {
    	Log.info("Starting the process to click the Search button after assigning a value to 'Allocated To'...");
    	Log.info("Waiting for the Search button to become visible...");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    	WebElement searchbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.searchbutton));
    	Log.info("Clicking the Search button...");
    	searchbutton.click();
    	Log.info("Search button clicked successfully.");
    }

    // Method to retrieve the warning message text
    public String getWarningMessageaftergivingvalueforallocatedto() {
    	Log.info("Starting the process to retrieve the warning message after assigning a value to 'Allocated To'...");
    	Log.info("Waiting for the warning message to become visible...");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    	WebElement warningMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.warningmsg2));
    	Log.info("Retrieving the text from the warning message...");
        String message = warningMessage.getText();
        Log.info("Warning message retrieved successfully: " + message); // Log after retrieving the message

        return message;
    }
    
 // Method to open the asset category dropdown
    public void openAssetCategoryDropdown() throws InterruptedException {
    	Log.info("Starting the process to open the Asset Category dropdown...");
    	 Log.info("Waiting for the Asset Category dropdown to become visible...");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    	WebElement assetCategoryDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.assetCategoryDropdown));
    	Log.info("Scrolling the Asset Category dropdown into view...");
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", assetCategoryDropdown); 
    	Log.info("Asset Category dropdown scrolled into view.");
    	Log.info("Waiting for 1 second for smooth scrolling...");
        Thread.sleep(500);
        Log.info("Wait completed.");
        Log.info("Clicking the Asset Category dropdown...");
        assetCategoryDropdown.click();
        Log.info("Asset Category dropdown clicked.");
        Log.info("Waiting for the spinner to disappear...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner is no longer visible.");
    	 Log.info("Waiting for the 'All' value in the Asset Category dropdown to become visible...");
    	WebElement assetCategoryvalueall = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.assetCategoryvalueall));
    	Log.info("Clicking the 'All' value in the Asset Category dropdown...");
    	assetCategoryvalueall.click();
    	Log.info("'All' value selected from the dropdown.");
    	Log.info("Waiting for the spinner to disappear after selecting the 'All' value...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner is no longer visible after selection.");
    	Log.info("Clicking outside the dropdown to close it...");
    	WebElement outside = driver.findElement(CoreAutoAllocationRepo.outarea);
    	outside.click();
    	Log.info("Clicked outside the dropdown to close it.");
    	Log.info("Waiting for the spinner to disappear after clicking outside...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner is no longer visible after closing the dropdown.");
    }
    
    // Method to open the SMA category dropdown
    public void openSMACategoryDropdown() throws InterruptedException {
    	Log.info("Starting the process to open the SMA Category dropdown...");
    	Log.info("Waiting for the SMA Category dropdown to become visible...");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    	WebElement SMACategoryDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.SMAcategoryDropdown));
    	Log.info("Clicking the SMA Category dropdown...");
    	SMACategoryDropdown.click();
    	Log.info("SMA Category dropdown clicked.");
    	Log.info("Waiting for the spinner to disappear...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner is no longer visible.");
    	Log.info("Waiting for the 'All' value in the SMA Category dropdown to become visible...");
    	WebElement SMACategoryvalueall = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.SMAcategoryvalueall));
    	Log.info("Clicking the 'All' value in the SMA Category dropdown...");
    	SMACategoryvalueall.click();
    	Log.info("'All' value selected from the dropdown.");
    	Log.info("Waiting for the spinner to disappear after selecting the 'All' value...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner is no longer visible after selection.");
    	Log.info("Clicking outside the dropdown to close it...");
    	WebElement outside = driver.findElement(CoreAutoAllocationRepo.outarea);
    	outside.click();
    	Log.info("Clicked outside the dropdown to close it.");
    	Log.info("Waiting for the spinner to disappear after clicking outside...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner is no longer visible after closing the dropdown.");
    }
    // Method to open the SMA category dropdown
    public void openNPACategoryDropdown() throws InterruptedException {
    	Log.info("Starting the process to open the NPA Category dropdown...");
    	Log.info("Waiting for the NPA Category dropdown to become visible...");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    	WebElement SMACategoryDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.NPAcategoryDropdown));
    	Log.info("NPA Category dropdown is now visible.");
    	Log.info("Clicking the NPA Category dropdown...");
    	SMACategoryDropdown.click();
    	Log.info("NPA Category dropdown clicked.");
    	 Log.info("Waiting for the spinner to disappear...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner is no longer visible.");
    	Log.info("Waiting for the 'All' value in the NPA Category dropdown to become visible...");
    	WebElement SMACategoryvalueall = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.NPAcategoryvalueall));
    	Log.info("'All' value is now visible in the dropdown.");
    	Log.info("Clicking the 'All' value in the NPA Category dropdown...");
    	SMACategoryvalueall.click();
    	Log.info("'All' value selected from the dropdown.");
    	Log.info("Waiting for the spinner to disappear after selecting the 'All' value...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner is no longer visible after selection.");
    	Log.info("Clicking outside the dropdown to close it...");
    	WebElement outside = driver.findElement(CoreAutoAllocationRepo.outarea);
    	outside.click();
    	Log.info("Clicked outside the dropdown to close it.");
    	Log.info("Waiting for the spinner to disappear after clicking outside...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner is no longer visible after closing the dropdown.");
    }
    
 // Method to select any value in Allocated To field
    public void selectAllocationType(String value) throws InterruptedException {
    	Log.info("Starting the process to select an allocation type...");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    	Log.info("Locating the Allocation Type dropdown...");
    	WebElement AllocationTypedropdown = driver.findElement(CoreAutoAllocationRepo.allocationtypedropdown);
    	Log.info("Scrolling to the Allocation Type dropdown...");
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", AllocationTypedropdown); 
    	Log.info("Waiting for a brief moment before interacting with the dropdown...");
        Thread.sleep(500);
        Log.info("Clicking on the Allocation Type dropdown...");
    	AllocationTypedropdown.click();
    	Log.info("Waiting for the spinner to disappear...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Locating the value in the Allocation Type dropdown: " + value);
    	WebElement AllocationTypevalue = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.allocationtypevalue(value)));
    	Log.info("Clicking on the value in the Allocation Type dropdown...");
    	AllocationTypevalue.click();
    	Log.info("Waiting for the spinner to disappear after selecting the value...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Locating the area outside the dropdown...");
    	WebElement outside = driver.findElement(CoreAutoAllocationRepo.outarea);
    	Log.info("Clicking outside to close the dropdown...");
    	outside.click();
    	Log.info("Waiting for the spinner to disappear after clicking outside...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Completed the process to select an allocation type.");
    }
    
    public String getResultGridText() {
   	 try {
   	        Log.info("Starting the process to locate the 'No records' message element...");
   	        
   	        // Try to locate the element
   	        List<WebElement> norecordsmsg = driver.findElements(CoreAutoAllocationRepo.norecordsmsg);
   	        
   	        // Log if element is found or not
   	        if (!norecordsmsg.isEmpty()) {
   	            Log.info("'No records' message element found.");
   	            String resultText = norecordsmsg.get(0).getText();
   	            Log.info("Returning the text of the 'No records' message: " + resultText);
   	            return resultText;
   	        } else {
   	            Log.info("'No records' message element not found.");
   	            return "No records message element not found.";
   	        }
   	        
   	    } catch (Exception e) {
   	        // Handle any unexpected errors
   	        Log.error("An unexpected error occurred: " + e.getMessage(), e);
   	        return "An unexpected error occurred: " + e.getMessage();
   	    }
   }
    
    public boolean isManualAllocationPageLoaded() throws InterruptedException {
    	Log.info("Starting the process to verify if the Auto Allocation page is loaded...");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	Log.info("Locating the Call Center menu...");
//    	WebElement UserType = driver.findElement(CoreManualAllocationRepo.UserType);
//    	UserType.getText();
    	WebElement callcentermenu = driver.findElement(CoreAutoAllocationRepo.callcentermenu);
    	Log.info("Call Center menu located successfully.");
    	Log.info("Clicking on the Call Center menu...");
    	callcentermenu.click();
    	Log.info("Call Center menu clicked successfully.");
    	Log.info("Locating the Auto Allocation submenu...");
    	WebElement manaualallocationsubmenu = driver.findElement(CoreManualAllocationRepo.manualallocationsubmenu);
    	Log.info("Manual Allocation submenu located successfully.");
    	Log.info("Clicking on the Manual Allocation submenu...");
    	manaualallocationsubmenu.click();
    	Log.info("Manual Allocation submenu clicked successfully.");
    	Log.info("Waiting for the spinner to disappear...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner disappeared successfully.");
    	Log.info("Pausing for a brief moment...");
    	WebElement button = wait.until(ExpectedConditions.elementToBeClickable(CoreManualAllocationRepo.Searchbtn));
    	Log.info("Checking if the current URL ends with 'CallCentre/ManualAllocationConfiguration'...");
        boolean isPageLoaded = driver.getCurrentUrl().endsWith("CallCentre/ManualAllocationConfiguration");

        if (isPageLoaded) {
            Log.info("Manual Allocation page loaded successfully.");
        } else {
            Log.warn("Failed to load the Manual Allocation page.");
        }

        Log.info("Process to verify Manual Allocation page load completed.");
        return isPageLoaded;
    }
    
    public boolean areFieldsAndButtonsPresent() {
   	 Log.info("Starting the process to verify the presence of all required fields and buttons on the page...");

   	    try {
   	        
   	        WebElement allocationName = driver.findElement(CoreManualAllocationRepo.allocationName);
   	        

   	        
   	        WebElement zone_co = driver.findElement(CoreManualAllocationRepo.zone_co);
   	        

   	        
   	        WebElement branch = driver.findElement(CoreManualAllocationRepo.branch);
   	        

   	        
   	        WebElement branch_id = driver.findElement(CoreManualAllocationRepo.branch_id);
   	        

   	        
   	        WebElement vertical = driver.findElement(CoreManualAllocationRepo.vertical);
   	        

   	        
   	        WebElement schemetype = driver.findElement(CoreManualAllocationRepo.schemetype);
   	        

   	        
   	        WebElement producttype = driver.findElement(CoreManualAllocationRepo.producttype);
   	        

   	        
   	        WebElement schemecode = driver.findElement(CoreManualAllocationRepo.schemecode);
   	        

   	        
   	        WebElement assettaggingtype = driver.findElement(CoreManualAllocationRepo.assettaggingtype);
   	        

   	        
   	        WebElement assetcategory = driver.findElement(CoreManualAllocationRepo.assetcategory);
   	        

   	        
   	        WebElement smacategory = driver.findElement(CoreManualAllocationRepo.smacategory);
   	        

   	        
   	        WebElement npacategory = driver.findElement(CoreManualAllocationRepo.npacategory);
   	        

   	        
   	        WebElement dpdoperatorsdropdown = driver.findElement(CoreManualAllocationRepo.dpdoperatorsdropdown);
   	        

   	        
   	        WebElement dpdtextfield = driver.findElement(CoreManualAllocationRepo.dpdtextfield);
   	        

   	       
   	        WebElement osbalanceoperatorsdropdown = driver.findElement(CoreManualAllocationRepo.osbalanceoperatorsdropdown);
   	        

   	        
   	        WebElement osbalancetextfield = driver.findElement(CoreManualAllocationRepo.osbalancetextfield);
   	        

   	        
   	        WebElement overdueemioperatorsdropdown = driver.findElement(CoreManualAllocationRepo.overdueemioperatorsdropdown);
   	        

   	        
   	        WebElement overdueemiamounttextfield = driver.findElement(CoreManualAllocationRepo.overdueemiamounttextfield);
   	        

   	        
   	        WebElement actionowner = driver.findElement(CoreManualAllocationRepo.actionowner);
   	        

   	        
   	        WebElement ActionDateFrom = driver.findElement(CoreManualAllocationRepo.ActionDateFrom);
   	        

   	        
   	        WebElement ActionDateto = driver.findElement(CoreManualAllocationRepo.ActionDateto);
   	        

   	        
   	        WebElement TypesofAccount = driver.findElement(CoreManualAllocationRepo.TypesofAccount);
   	        

   	        
   	        WebElement To = driver.findElement(CoreManualAllocationRepo.To);
   	        

   	        
   	        WebElement IsPFTNPA = driver.findElement(CoreManualAllocationRepo.IsPFTNPA);
   	        

   	        
   	        WebElement IsFTNPA = driver.findElement(CoreManualAllocationRepo.IsFTNPA);
   	        

   	        
   	        WebElement SaveThisAllocationCriteria = driver.findElement(CoreManualAllocationRepo.SaveThisAllocationCriteria);
   	        

   	        
   	        WebElement Searchbtn = driver.findElement(CoreManualAllocationRepo.Searchbtn);
   	        
   	        
   	     WebElement Resetbtn = driver.findElement(CoreManualAllocationRepo.Resetbtn);
   	     WebElement EditAllocationCriteriabtn = driver.findElement(CoreManualAllocationRepo.EditAllocationCriteriabtn);
   	     WebElement AllocateTo = driver.findElement(CoreManualAllocationRepo.AllocateTo);
   	     WebElement ExpiryDate = driver.findElement(CoreManualAllocationRepo.ExpiryDate);
   	     WebElement Assignbtn = driver.findElement(CoreManualAllocationRepo.Assignbtn);
   	     WebElement DownloadinExcel = driver.findElement(CoreManualAllocationRepo.DownloadinExcel);
   	     WebElement AssignedList = driver.findElement(CoreManualAllocationRepo.AssignedList); 

   	        Log.info("Verifying if all fields and buttons are displayed...");
   	        boolean areAllElementsDisplayed = 
   	                allocationName.isDisplayed() &&
   	                zone_co.isDisplayed() &&
   	                branch.isDisplayed() &&
   	                branch_id.isDisplayed() &&
   	                vertical.isDisplayed() &&
   	                schemetype.isDisplayed() &&
   	                producttype.isDisplayed() &&
   	                schemecode.isDisplayed() &&
   	                assettaggingtype.isDisplayed() &&
   	                assetcategory.isDisplayed() &&
   	                smacategory.isDisplayed() &&
   	                npacategory.isDisplayed() &&
   	                dpdoperatorsdropdown.isDisplayed() &&
   	                dpdtextfield.isDisplayed() &&
   	                osbalanceoperatorsdropdown.isDisplayed() &&
   	                osbalancetextfield.isDisplayed() &&
   	                overdueemioperatorsdropdown.isDisplayed() &&
   	                overdueemiamounttextfield.isDisplayed() &&
   	                actionowner.isDisplayed() &&
   	                ActionDateFrom.isDisplayed() &&
   	                ActionDateto.isDisplayed() &&
   	                TypesofAccount.isDisplayed() &&
   	                To.isDisplayed() &&
   	                IsPFTNPA.isDisplayed() &&
   	                IsFTNPA.isDisplayed() &&
   	                SaveThisAllocationCriteria.isDisplayed() &&
   	                Searchbtn.isDisplayed() &&
   	                Resetbtn.isDisplayed() &&
   	                EditAllocationCriteriabtn.isDisplayed();
   	        
   	     JavascriptExecutor js = (JavascriptExecutor) driver;
         js.executeScript("arguments[0].scrollIntoView(true);", AssignedList);
         Thread.sleep(1000);
         areAllElementsDisplayed &=
   	                AllocateTo.isDisplayed() &&
   	                ExpiryDate.isDisplayed() &&
   	                Assignbtn.isDisplayed() &&
   	                DownloadinExcel.isDisplayed() &&
   	                AssignedList.isDisplayed();

   	        if (areAllElementsDisplayed) {
   	            Log.info("All required fields and buttons are present.");
   	        } else {
   	            Log.warn("One or more required fields or buttons are missing.");
   	        }

   	        Log.info("Completed the verification process for fields and buttons.");
   	        return areAllElementsDisplayed;

   	    } catch (Exception e) {
   	    	Log.error("An error occurred while verifying fields and buttons: " + e.getMessage(), e);
   	        return false;
   	    }
   }
    
 // Method to click the search button
    public void clickSearchbutton() {
    	 // Log before finding the element
    	Log.info("Attempting to locate the search button element.");

        // Find the search button
        WebElement Searchbtn = driver.findElement(CoreManualAllocationRepo.Searchbtn);

        // Log after locating the element
        Log.info("Search button element located successfully.");

        // Log before clicking the button
        Log.info("Attempting to click the search button.");

        // Perform the click action
        Searchbtn.click();

        // Log after clicking the button
        Log.info("Search button clicked successfully.");
    }
    
 // Method to get the warning message text
    public String getWarningmessage() {
    	 // Log before waiting for the warning message to appear
        Log.info("Waiting for the warning message to become visible on the page.");

        // Initialize WebDriverWait and wait for the element to become visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

        // Log after initializing the wait
        Log.info("WebDriverWait initialized with a timeout of 120 seconds.");

        // Wait for the warning message element to be located and visible
        WebElement warningMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.warningMessage));

        // Log after the element is located
        Log.info("Warning message element is now visible.");

        // Retrieve the text of the warning message
        String messageText = warningMessage.getText();

        // Log after retrieving the warning message text
        Log.info("Retrieved warning message text: " + messageText);

        // Return the warning message text
        return messageText;
    }
    
 // Method to select categories from asset category dropdown
    public void selectAssetCategory() throws InterruptedException {
    	// Log before initializing WebDriverWait
        Log.info("Initializing WebDriverWait with a timeout of 120 seconds.");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

        // Log before locating the allocation name element
        Log.info("Attempting to locate the allocation name element.");
        WebElement allocationName = driver.findElement(CoreManualAllocationRepo.allocationName);
        Log.info("Allocation name element located successfully.");

        // Log before scrolling to the allocation name element
        Log.info("Scrolling to the allocation name element using JavaScript.");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'start'});", allocationName);
        Log.info("Scroll action completed.");

        // Log before adding a delay
        Log.info("Waiting for 5 seconds to ensure proper loading.");
        Thread.sleep(5000);
        Log.info("Wait of 5 seconds completed.");

        // Log before waiting for spinner to disappear
        Log.info("Waiting for the spinner to become invisible.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Spinner is now invisible.");

        // Log before locating the asset category element
        Log.info("Attempting to locate the asset category element.");
        WebElement assetcategory = driver.findElement(CoreManualAllocationRepo.assetcategory);
        Log.info("Asset category element located successfully.");

        // Log before clicking on the asset category element
        Log.info("Clicking on the asset category element.");
        assetcategory.click();
        Log.info("Asset category element clicked successfully.");

        // Log before locating the select all element in the asset category
        Log.info("Attempting to locate the 'Select All' option in the asset category.");
        WebElement assetcategorysellectall = driver.findElement(CoreManualAllocationRepo.assetcategorysellectall);
        Log.info("'Select All' option located successfully.");

        // Log before clicking on the 'Select All' option
        Log.info("Clicking on the 'Select All' option in the asset category.");
        assetcategorysellectall.click();
        Log.info("'Select All' option clicked successfully.");

        // Log before waiting for spinner to disappear again
        Log.info("Waiting for the spinner to become invisible after selecting 'Select All'.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Spinner is now invisible after selecting 'Select All'.");
    }
    
 // Method to select SMA categories
    public void selectSmaCategories() throws InterruptedException {
    	// Log before locating the SMA category element
        Log.info("Attempting to locate the SMA category element.");
        WebElement smacategory = driver.findElement(CoreManualAllocationRepo.smacategory);
        Log.info("SMA category element located successfully.");

        // Log before clicking the SMA category element
        Log.info("Clicking on the SMA category element.");
        smacategory.click();
        Log.info("SMA category element clicked successfully.");

        // Log before adding a delay
        Log.info("Waiting for 1 second to ensure proper loading.");
        Thread.sleep(1000);
        Log.info("Wait of 1 second completed.");

        // Log before locating the 'Select All' option in SMA categories
        Log.info("Attempting to locate the 'Select All' option in SMA categories.");
        WebElement smacategorysellectall = driver.findElement(CoreManualAllocationRepo.smacategorysellectall);
        Log.info("'Select All' option located successfully.");

        // Log before clicking the 'Select All' option
        Log.info("Clicking on the 'Select All' option in SMA categories.");
        smacategorysellectall.click();
        Log.info("'Select All' option in SMA categories clicked successfully.");
    }
    
 // Method to select NPA categories
    public void selectNpaCategories() {
    	// Log before locating the NPA category element
        Log.info("Attempting to locate the NPA category element.");
        WebElement npacategory = driver.findElement(CoreManualAllocationRepo.npacategory);
        Log.info("NPA category element located successfully.");

        // Log before clicking the NPA category element
        Log.info("Clicking on the NPA category element.");
        npacategory.click();
        Log.info("NPA category element clicked successfully.");

        // Log before locating the 'Select All' option in NPA categories
        Log.info("Attempting to locate the 'Select All' option in NPA categories.");
        WebElement npacategorysellectall = driver.findElement(CoreManualAllocationRepo.npacategorysellectall);
        Log.info("'Select All' option in NPA categories located successfully.");

        // Log before clicking the 'Select All' option
        Log.info("Clicking on the 'Select All' option in NPA categories.");
        npacategorysellectall.click();
        Log.info("'Select All' option in NPA categories clicked successfully.");
    }
    
    public void clickOsBalanceField() {
    	// Log before locating the OS balance operators dropdown element
        Log.info("Attempting to locate the OS balance operators dropdown element.");
        WebElement osbalanceoperatorsdropdown = driver.findElement(CoreManualAllocationRepo.osbalanceoperatorsdropdown);
        Log.info("OS balance operators dropdown element located successfully.");

        // Log before clicking the OS balance operators dropdown element
        Log.info("Clicking on the OS balance operators dropdown element.");
        osbalanceoperatorsdropdown.click();
        Log.info("OS balance operators dropdown element clicked successfully.");
    }

    public void selectEqualFinancialOperator(String value) {
    	// Log before locating the dropdown value element
        Log.info("Attempting to locate the dropdown value element for the financial operator with value: " + value);
        WebElement OutstandingBalLimitOperatordropdownvalue = driver.findElement(CoreManualAllocationRepo.OutstandingBalLimitOperatordropdownvalue(value));
        Log.info("Dropdown value element for the financial operator with value: " + value + " located successfully.");

        // Log before clicking the dropdown value element
        Log.info("Clicking on the dropdown value element for the financial operator with value: " + value);
        OutstandingBalLimitOperatordropdownvalue.click();
        Log.info("Dropdown value element for the financial operator with value: " + value + " clicked successfully.");
    }

    public void enterOsBalance(String balance) {
    	// Log before locating the OS balance text field element
        Log.info("Attempting to locate the OS balance text field element.");
        WebElement osbalancetextfield = driver.findElement(CoreManualAllocationRepo.osbalancetextfield);
        Log.info("OS balance text field element located successfully.");

        // Log before clearing the text field
        Log.info("Clearing any existing value in the OS balance text field.");
        osbalancetextfield.clear();
        Log.info("Existing value in the OS balance text field cleared successfully.");

        // Log before entering the balance value
        Log.info("Entering the balance value: " + balance + " into the OS balance text field.");
        osbalancetextfield.sendKeys(balance);
        Log.info("Balance value: " + balance + " entered successfully into the OS balance text field.");
    }

    public void selectCallCentreFromToDropdown(String value) {
    	// Log before locating and clicking the 'To' dropdown element
        Log.info("Attempting to locate the 'To' dropdown element.");
        WebElement To = driver.findElement(CoreManualAllocationRepo.To);
        Log.info("'To' dropdown element located successfully.");

        Log.info("Clicking on the 'To' dropdown element.");
        To.click();
        Log.info("'To' dropdown element clicked successfully.");

        // Log before locating and clicking the specific value in the 'To' dropdown
        Log.info("Attempting to locate the value: " + value + " in the 'To' dropdown.");
        WebElement tovalue = driver.findElement(CoreManualAllocationRepo.tovalue(value));
        Log.info("Value: " + value + " located successfully in the 'To' dropdown.");

        Log.info("Clicking on the value: " + value + " in the 'To' dropdown.");
        tovalue.click();
        Log.info("Value: " + value + " clicked successfully in the 'To' dropdown.");
    }

    public void clickSearchBtn() throws InterruptedException {
    	 // Log before creating WebDriverWait instance
        Log.info("Creating WebDriverWait instance with a timeout of 120 seconds.");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        Log.info("WebDriverWait instance created successfully.");

        // Log before locating and clicking the Search button
        Log.info("Attempting to locate the Search button.");
        WebElement Searchbtn = driver.findElement(CoreManualAllocationRepo.Searchbtn);
        Log.info("Search button located successfully.");
        
        Log.info("Clicking on the Search button.");
        Searchbtn.click();
        Log.info("Search button clicked successfully.");

        // Log before waiting for the spinner to disappear
        Log.info("Waiting for the spinner to become invisible.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Spinner is now invisible.");

        // Log before locating the Assigned List element
        Log.info("Attempting to locate the Assigned List element.");
        WebElement AssignedList = driver.findElement(CoreManualAllocationRepo.AssignedList);
        Log.info("Assigned List element located successfully.");

        // Log before scrolling to the Assigned List element
        Log.info("Scrolling to the Assigned List element.");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'start'});", AssignedList);
        Log.info("Successfully scrolled to the Assigned List element.");

        // Log before adding a delay
        Log.info("Waiting for 5 seconds to ensure proper loading.");
        Thread.sleep(5000);
        Log.info("Wait of 5 seconds completed.");
    }

    public void clickDownloadExcelButton() {
    	// Log before locating the Download in Excel button
        Log.info("Attempting to locate the 'Download in Excel' button.");
        WebElement DownloadinExcel = driver.findElement(CoreManualAllocationRepo.DownloadinExcel);
        Log.info("'Download in Excel' button located successfully.");

        // Log before clicking the Download in Excel button
        Log.info("Clicking on the 'Download in Excel' button.");
        DownloadinExcel.click();
        Log.info("'Download in Excel' button clicked successfully.");
    }
    
 // Method to get the account count from the grid
    public String getAccountGridCount() {
    	// Log before locating the Total Account Selected element
        Log.info("Attempting to locate the 'Total Account Selected' element.");
        WebElement totalAccountSelected = driver.findElement(CoreManualAllocationRepo.TotalAccountSelected);
        Log.info("'Total Account Selected' element located successfully.");

        // Log before retrieving the text from the element
        Log.info("Retrieving the text from the 'Total Account Selected' element.");
        String accountCount = totalAccountSelected.getText();
        Log.info("Retrieved text successfully: " + accountCount);

        return accountCount;
    }
    
    public void navigateTodispostionMainMenu() {
    	// Log before creating WebDriverWait instance
        Log.info("Creating WebDriverWait instance with a timeout of 120 seconds.");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        Log.info("WebDriverWait instance created successfully.");

        // Log before locating and clicking the 'Disposition' menu option
        Log.info("Attempting to locate the 'Disposition' menu option.");
        WebElement Disposition = driver.findElement(CoreManualAllocationRepo.Disposition);
        Log.info("'Disposition' menu option located successfully.");
        
        Log.info("Clicking on the 'Disposition' menu option.");
        Disposition.click();
        Log.info("'Disposition' menu option clicked successfully.");

        // Log before locating and clicking the 'Updation of Disposition' option
        Log.info("Attempting to locate the 'Updation of Disposition' option.");
        WebElement UpdationofDisposition = driver.findElement(CoreManualAllocationRepo.UpdationofDisposition);
        Log.info("'Updation of Disposition' option located successfully.");

        Log.info("Clicking on the 'Updation of Disposition' option.");
        UpdationofDisposition.click();
        Log.info("'Updation of Disposition' option clicked successfully.");

        // Log before waiting for the spinner to disappear
        Log.info("Waiting for the spinner to become invisible.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Spinner is now invisible.");
    }
    
  //Enter account number in the search tab
    public void enterAccountNumber(String accountNumber) {
    	// Log before locating the Account Number text field
        Log.info("Attempting to locate the Account Number text field.");
        WebElement AccountNumbertextfield = driver.findElement(CoreManualAllocationRepo.AccountNumbertextfield);
        Log.info("Account Number text field located successfully.");

        // Log before entering the account number
        Log.info("Entering the account number: " + accountNumber + " into the Account Number text field.");
        AccountNumbertextfield.sendKeys(accountNumber);
        Log.info("Account number: " + accountNumber + " entered successfully into the Account Number text field.");
    }

    // Click search button
    public void clickSearchButon() {
    	// Log before locating the Search button
        Log.info("Attempting to locate the Search button.");
        WebElement Searchbut = driver.findElement(CoreManualAllocationRepo.Searchbut);
        Log.info("Search button located successfully.");

        // Log before clicking the Search button
        Log.info("Clicking on the Search button.");
        Searchbut.click();
        Log.info("Search button clicked successfully.");
    }

    // Get warning message text
    public String getWarnMessage() {
    	// Log before creating WebDriverWait instance
        Log.info("Creating WebDriverWait instance with a timeout of 120 seconds.");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        Log.info("WebDriverWait instance created successfully.");

        // Log before waiting for the visibility of the warning message element
        Log.info("Waiting for the warning message to become visible.");
        WebElement warningmsg = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.warningmsg));
        Log.info("Warning message is now visible.");

        // Log before retrieving the text of the warning message
        Log.info("Retrieving the text of the warning message.");
        String warningText = warningmsg.getText();
        Log.info("Warning message text retrieved successfully: " + warningText);

        return warningText;
    }
    
 // Method to get the warning message text
    public String getWarningmessageforTofieldmandatorychecking() {
    	// Log before creating WebDriverWait instance
        Log.info("Creating WebDriverWait instance with a timeout of 120 seconds.");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        Log.info("WebDriverWait instance created successfully.");

        // Log before waiting for the visibility of the warning message for the 'To' field
        Log.info("Waiting for the 'To' field mandatory warning message to become visible.");
        WebElement warningMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.Tofieldmandatorywarningmsg));
        Log.info("'To' field mandatory warning message is now visible.");

        // Log before retrieving the warning message text
        Log.info("Retrieving the text of the 'To' field mandatory warning message.");
        String warningText = warningMessage.getText();
        Log.info("'To' field warning message text retrieved successfully: " + warningText);

        return warningText;
    }
}
