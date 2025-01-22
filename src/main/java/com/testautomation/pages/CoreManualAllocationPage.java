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
    	Thread.sleep(1000);
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
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
    	 WebElement Searchbtn = driver.findElement(CoreManualAllocationRepo.Searchbtn);
    	 Searchbtn.click();
    }
    
 // Method to get the warning message text
    public String getWarningmessage() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    	WebElement warningMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.warningMessage));
        return warningMessage.getText();
    }
    
 // Method to select categories from asset category dropdown
    public void selectAssetCategory() throws InterruptedException {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    	WebElement allocationName = driver.findElement(CoreManualAllocationRepo.allocationName);
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'start'});", allocationName);
        Thread.sleep(5000);
        WebElement assetcategory = driver.findElement(CoreManualAllocationRepo.assetcategory);
    	assetcategory.click();
    	WebElement assetcategorysellectall = driver.findElement(CoreManualAllocationRepo.assetcategorysellectall);
    	assetcategorysellectall.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));;
    }
    
 // Method to select SMA categories
    public void selectSmaCategories() {
    	WebElement smacategory = driver.findElement(CoreManualAllocationRepo.smacategory);
    	smacategory.click();
    	WebElement smacategorysellectall = driver.findElement(CoreManualAllocationRepo.smacategorysellectall);
    	smacategorysellectall.click();
    }
    
 // Method to select NPA categories
    public void selectNpaCategories() {
    	WebElement npacategory = driver.findElement(CoreManualAllocationRepo.npacategory);
    	npacategory.click();
    	WebElement npacategorysellectall = driver.findElement(CoreManualAllocationRepo.npacategorysellectall);
    	npacategorysellectall.click();
    }
    
    public void clickOsBalanceField() {
    	WebElement osbalanceoperatorsdropdown = driver.findElement(CoreManualAllocationRepo.osbalanceoperatorsdropdown);
    	osbalanceoperatorsdropdown.click();
    }

    public void selectEqualFinancialOperator(String value) {
    	WebElement OutstandingBalLimitOperatordropdownvalue = driver.findElement(CoreManualAllocationRepo.OutstandingBalLimitOperatordropdownvalue(value));
    	OutstandingBalLimitOperatordropdownvalue.click();
    }

    public void enterOsBalance(String balance) {
    	WebElement osbalancetextfield = driver.findElement(CoreManualAllocationRepo.osbalancetextfield);
    	osbalancetextfield.clear();
    	osbalancetextfield.sendKeys(balance);
    }

    public void selectCallCentreFromToDropdown(String value) {
    	WebElement To = driver.findElement(CoreManualAllocationRepo.To);
    	WebElement tovalue = driver.findElement(CoreManualAllocationRepo.tovalue(value));
    	To.click();
    	tovalue.click();
    }

    public void clickSearchBtn() {
    	WebElement Searchbtn = driver.findElement(CoreManualAllocationRepo.Searchbtn);
    	Searchbtn.click();
    }

    public void clickDownloadExcelButton() {
    	WebElement DownloadinExcel = driver.findElement(CoreManualAllocationRepo.DownloadinExcel);
    	DownloadinExcel.click();
    }
}
