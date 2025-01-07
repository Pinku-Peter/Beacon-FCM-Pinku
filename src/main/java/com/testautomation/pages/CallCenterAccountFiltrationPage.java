package com.testautomation.pages;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.BasePackage.DownloadedExcelReader;
import com.Page_Repository.CallCenterAccountFiltrationRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Utility.Log;

import io.netty.handler.timeout.TimeoutException;

public class CallCenterAccountFiltrationPage {
	
	private WebDriver driver;
	public CallCenterAccountFiltrationPage(WebDriver driver) {
		Log.info("Initializing CallCenterAccountFiltrationPage...");
        this.driver = driver;
        Log.info("WebDriver instance assigned.");
        Log.info("Initializing Web elements using PageFactory...");
        PageFactory.initElements(driver, this);
        Log.info("Web elements initialized using PageFactory.");
        Log.info("CallCenterAccountFiltrationPage initialization completed.");
    }
	
	 // Method to navigate to Call Centre Main Menu
    public void navigateToMainMenu() {
    	Log.info("Starting navigation to the Call Centre Main Menu...");
    	Log.info("Locating the Call Centre Main Menu element...");
    	WebElement callcenter = driver.findElement(CallCenterAccountFiltrationRepo.callcentermainmenu);
    	Log.info("Call Centre Main Menu element located successfully.");
    	Log.info("Clicking on the Call Centre Main Menu...");
    	callcenter.click();
    	Log.info("Clicked on the Call Centre Main Menu. Navigation complete.");
    }

    // Method to navigate to Account Filtration page
    public void navigateToAccountFiltration() {
    	Log.info("Starting navigation to the Account Filtration submenu...");
    	Log.info("Locating the Account Filtration submenu element...");
    	WebElement accountfiltration = driver.findElement(CallCenterAccountFiltrationRepo.accountfiltrationsubmenu);
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
    	WebElement searchbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(CallCenterAccountFiltrationRepo.searchbutton));
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
    
    public String getResultGridText() {
    	 try {
    	        Log.info("Starting the process to locate the 'No records' message element...");
    	        
    	        // Try to locate the element
    	        List<WebElement> norecordsmsg = driver.findElements(CallCenterAccountFiltrationRepo.norecordsmsg);
    	        
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

    // Method to get the text of the warning message
    public String getWarningMessage() {
    	 Log.info("Starting the process to retrieve the warning message...");
    	 Log.info("Waiting for the warning message to become visible...");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    	WebElement warningMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(CallCenterAccountFiltrationRepo.warningmsg));
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
    	WebElement allocatedtodropdown = driver.findElement(CallCenterAccountFiltrationRepo.allocatedtodropdown);
    	Log.info("Clicking on the Allocated To dropdown...");
    	allocatedtodropdown.click();
    	Log.info("Allocated To dropdown clicked.");
    	Log.info("Waiting for the spinner to disappear...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner is no longer visible.");
    	Log.info("Waiting for the value '" + value + "' to be visible...");
    	WebElement allocatedtovalue = wait.until(ExpectedConditions.visibilityOfElementLocated(CallCenterAccountFiltrationRepo.allocatedtovalue(value)));
    	Log.info("Clicking on the value '" + value + "'...");
    	allocatedtovalue.click();
    	Log.info("Value '" + value + "' selected from the dropdown.");
    	Log.info("Waiting for the spinner to disappear after selecting the value...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner is no longer visible after selecting the value.");
    	Log.info("Clicking outside the dropdown to close it...");
    	WebElement outside = driver.findElement(CallCenterAccountFiltrationRepo.outarea);
    	outside.click();
    	Log.info("Clicked outside the dropdown to close it.");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }
 // Method to click the Search button
    public void clickSearchButtonaftergivingvalueforallocatedto() {
    	Log.info("Starting the process to click the Search button after assigning a value to 'Allocated To'...");
    	Log.info("Waiting for the Search button to become visible...");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    	WebElement searchbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(CallCenterAccountFiltrationRepo.searchbutton));
    	Log.info("Clicking the Search button...");
    	searchbutton.click();
    	Log.info("Search button clicked successfully.");
    }

    // Method to retrieve the warning message text
    public String getWarningMessageaftergivingvalueforallocatedto() {
    	Log.info("Starting the process to retrieve the warning message after assigning a value to 'Allocated To'...");
    	Log.info("Waiting for the warning message to become visible...");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    	WebElement warningMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(CallCenterAccountFiltrationRepo.warningmsg2));
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
    	WebElement assetCategoryDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(CallCenterAccountFiltrationRepo.assetCategoryDropdown));
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
    	WebElement assetCategoryvalueall = wait.until(ExpectedConditions.visibilityOfElementLocated(CallCenterAccountFiltrationRepo.assetCategoryvalueall));
    	Log.info("Clicking the 'All' value in the Asset Category dropdown...");
    	assetCategoryvalueall.click();
    	Log.info("'All' value selected from the dropdown.");
    	Log.info("Waiting for the spinner to disappear after selecting the 'All' value...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner is no longer visible after selection.");
    	Log.info("Clicking outside the dropdown to close it...");
    	WebElement outside = driver.findElement(CallCenterAccountFiltrationRepo.outarea);
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
    	WebElement SMACategoryDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(CallCenterAccountFiltrationRepo.SMAcategoryDropdown));
    	Log.info("Clicking the SMA Category dropdown...");
    	SMACategoryDropdown.click();
    	Log.info("SMA Category dropdown clicked.");
    	Log.info("Waiting for the spinner to disappear...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner is no longer visible.");
    	Log.info("Waiting for the 'All' value in the SMA Category dropdown to become visible...");
    	WebElement SMACategoryvalueall = wait.until(ExpectedConditions.visibilityOfElementLocated(CallCenterAccountFiltrationRepo.SMAcategoryvalueall));
    	Log.info("Clicking the 'All' value in the SMA Category dropdown...");
    	SMACategoryvalueall.click();
    	Log.info("'All' value selected from the dropdown.");
    	Log.info("Waiting for the spinner to disappear after selecting the 'All' value...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner is no longer visible after selection.");
    	Log.info("Clicking outside the dropdown to close it...");
    	WebElement outside = driver.findElement(CallCenterAccountFiltrationRepo.outarea);
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
    	WebElement SMACategoryDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(CallCenterAccountFiltrationRepo.NPAcategoryDropdown));
    	Log.info("NPA Category dropdown is now visible.");
    	Log.info("Clicking the NPA Category dropdown...");
    	SMACategoryDropdown.click();
    	Log.info("NPA Category dropdown clicked.");
    	 Log.info("Waiting for the spinner to disappear...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner is no longer visible.");
    	Log.info("Waiting for the 'All' value in the NPA Category dropdown to become visible...");
    	WebElement SMACategoryvalueall = wait.until(ExpectedConditions.visibilityOfElementLocated(CallCenterAccountFiltrationRepo.NPAcategoryvalueall));
    	Log.info("'All' value is now visible in the dropdown.");
    	Log.info("Clicking the 'All' value in the NPA Category dropdown...");
    	SMACategoryvalueall.click();
    	Log.info("'All' value selected from the dropdown.");
    	Log.info("Waiting for the spinner to disappear after selecting the 'All' value...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner is no longer visible after selection.");
    	Log.info("Clicking outside the dropdown to close it...");
    	WebElement outside = driver.findElement(CallCenterAccountFiltrationRepo.outarea);
    	outside.click();
    	Log.info("Clicked outside the dropdown to close it.");
    	Log.info("Waiting for the spinner to disappear after clicking outside...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner is no longer visible after closing the dropdown.");
    }
    
 // Method to select operator from the dropdown
    public void selectOutstandingBalanceOperator(String value) {
    	// Log the start of the process
        Log.info("Starting the process to select the Outstanding Balance Operator...");

        // Log before locating and clicking the dropdown for Outstanding Balance Operator
        Log.info("Locating the Outstanding Balance Operator dropdown...");
        WebElement OutstandingBalLimitOperatordropdown = driver.findElement(CallCenterAccountFiltrationRepo.OutstandingBalLimitOperatordropdown);
        Log.info("Clicking the Outstanding Balance Operator dropdown...");
        OutstandingBalLimitOperatordropdown.click();

        // Log before locating and clicking the dropdown value for the selected operator
        Log.info("Locating the dropdown value for operator: " + value);
        WebElement OutstandingBalLimitOperatordropdownvalue = driver.findElement(CallCenterAccountFiltrationRepo.OutstandingBalLimitOperatordropdownvalue(value));
        Log.info("Clicking the dropdown value for the selected operator: " + value);
        OutstandingBalLimitOperatordropdownvalue.click();

        // Log after completing the process
        Log.info("Successfully selected the Outstanding Balance Operator: " + value);
    }
    
    // Method to enter value in the outstanding balance input field
    public void enterOutstandingBalance(String balance) {
    	// Log the start of the process
        Log.info("Starting the process to enter the outstanding balance...");

        // Log before locating the input field for outstanding balance
        Log.info("Locating the input field for Outstanding Balance...");
        WebElement inputField = driver.findElement(CallCenterAccountFiltrationRepo.OutstandingBalLimit);
        
        // Log before clearing the input field
        Log.info("Clearing the existing value in the Outstanding Balance input field...");
        inputField.clear();
        
        // Log before entering the balance value
        Log.info("Entering the balance value: " + balance + " into the Outstanding Balance input field...");
        inputField.sendKeys(balance);

        // Log after completing the process
        Log.info("Successfully entered the outstanding balance.");
    }
    
    
 // Method to select any value in Allocated To field
    public void selectAllocationType(String value) throws InterruptedException {
    	Log.info("Starting the process to select an allocation type...");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    	Log.info("Locating the Allocation Type dropdown...");
    	WebElement AllocationTypedropdown = driver.findElement(CallCenterAccountFiltrationRepo.allocationtypedropdown);
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
    	WebElement AllocationTypevalue = wait.until(ExpectedConditions.visibilityOfElementLocated(CallCenterAccountFiltrationRepo.allocationtypevalue(value)));
    	Log.info("Clicking on the value in the Allocation Type dropdown...");
    	AllocationTypevalue.click();
    	Log.info("Waiting for the spinner to disappear after selecting the value...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Locating the area outside the dropdown...");
    	WebElement outside = driver.findElement(CallCenterAccountFiltrationRepo.outarea);
    	Log.info("Clicking outside to close the dropdown...");
    	outside.click();
    	Log.info("Waiting for the spinner to disappear after clicking outside...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Completed the process to select an allocation type.");
    }
    
    public boolean isAutoAllocationPageLoaded() throws InterruptedException {
    	Log.info("Starting the process to verify if the Auto Allocation page is loaded...");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	Log.info("Locating the Call Center menu...");
    	WebElement callcentermenu = driver.findElement(CallCenterAccountFiltrationRepo.callcentermenu);
    	Log.info("Call Center menu located successfully.");
    	Log.info("Clicking on the Call Center menu...");
    	callcentermenu.click();
    	Log.info("Call Center menu clicked successfully.");
    	Log.info("Locating the Auto Allocation submenu...");
    	WebElement autoallocationsubmenu = driver.findElement(CallCenterAccountFiltrationRepo.autoallocationsubmenu);
    	Log.info("Auto Allocation submenu located successfully.");
    	Log.info("Clicking on the Auto Allocation submenu...");
    	autoallocationsubmenu.click();
    	Log.info("Auto Allocation submenu clicked successfully.");
    	Log.info("Pausing for a brief moment...");
    	Thread.sleep(1000);
    	Log.info("Waiting for the spinner to disappear...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner disappeared successfully.");
    	Log.info("Checking if the current URL ends with 'CallCentre/AutoAllocationConfiguration'...");
        boolean isPageLoaded = driver.getCurrentUrl().endsWith("CallCentre/AutoAllocationConfiguration");

        if (isPageLoaded) {
            Log.info("Auto Allocation page loaded successfully.");
        } else {
            Log.warn("Failed to load the Auto Allocation page.");
        }

        Log.info("Process to verify Auto Allocation page load completed.");
        return isPageLoaded;
    }
    public boolean areFieldsAndButtonsPresent() {
    	 Log.info("Starting the process to verify the presence of all required fields and buttons on the page...");

    	    try {
    	        Log.info("Locating the Allocation Name field...");
    	        WebElement allocationName = driver.findElement(CallCenterAccountFiltrationRepo.allocationName);
    	        Log.info("Allocation Name field located successfully.");

    	        Log.info("Locating the Effective Date field...");
    	        WebElement effectDate = driver.findElement(CallCenterAccountFiltrationRepo.effectDate);
    	        Log.info("Effective Date field located successfully.");

    	        Log.info("Locating the Asset Category field...");
    	        WebElement assetCategory = driver.findElement(CallCenterAccountFiltrationRepo.assetCategory);
    	        Log.info("Asset Category field located successfully.");

    	        Log.info("Locating the SMA Category field...");
    	        WebElement smaCategory = driver.findElement(CallCenterAccountFiltrationRepo.smaCategory);
    	        Log.info("SMA Category field located successfully.");

    	        Log.info("Locating the NPA Category field...");
    	        WebElement npaCategory = driver.findElement(CallCenterAccountFiltrationRepo.npaCategory);
    	        Log.info("NPA Category field located successfully.");

    	        Log.info("Locating the Zone field...");
    	        WebElement zone = driver.findElement(CallCenterAccountFiltrationRepo.zone);
    	        Log.info("Zone field located successfully.");

    	        Log.info("Locating the Vertical field...");
    	        WebElement vertical = driver.findElement(CallCenterAccountFiltrationRepo.vertical);
    	        Log.info("Vertical field located successfully.");

    	        Log.info("Locating the Scheme Type field...");
    	        WebElement schemeType = driver.findElement(CallCenterAccountFiltrationRepo.schemeType);
    	        Log.info("Scheme Type field located successfully.");

    	        Log.info("Locating the Product Type field...");
    	        WebElement productType = driver.findElement(CallCenterAccountFiltrationRepo.productType);
    	        Log.info("Product Type field located successfully.");

    	        Log.info("Locating the Scheme Code field...");
    	        WebElement schemeCode = driver.findElement(CallCenterAccountFiltrationRepo.schemeCode);
    	        Log.info("Scheme Code field located successfully.");

    	        Log.info("Locating the Asset Tagging Type field...");
    	        WebElement assetTaggingType = driver.findElement(CallCenterAccountFiltrationRepo.assetTaggingType);
    	        Log.info("Asset Tagging Type field located successfully.");

    	        Log.info("Locating the Outstanding Balance field...");
    	        WebElement outstandingBalance = driver.findElement(CallCenterAccountFiltrationRepo.outstandingBalance);
    	        Log.info("Outstanding Balance field located successfully.");

    	        Log.info("Locating the Outstanding Balance value field...");
    	        WebElement outstandingBalancevalue = driver.findElement(CallCenterAccountFiltrationRepo.outstandingBalancevalue);
    	        Log.info("Outstanding Balance value field located successfully.");

    	        Log.info("Locating the Total Overdue field...");
    	        WebElement totalOverdue = driver.findElement(CallCenterAccountFiltrationRepo.totalOverdue);
    	        Log.info("Total Overdue field located successfully.");

    	        Log.info("Locating the Total Overdue value field...");
    	        WebElement totalOverduevalue = driver.findElement(CallCenterAccountFiltrationRepo.totalOverduevalue);
    	        Log.info("Total Overdue value field located successfully.");

    	        Log.info("Locating the DPD field...");
    	        WebElement dpd = driver.findElement(CallCenterAccountFiltrationRepo.dpd);
    	        Log.info("DPD field located successfully.");

    	        Log.info("Locating the DPD value field...");
    	        WebElement dpdvalue = driver.findElement(CallCenterAccountFiltrationRepo.dpdvalue);
    	        Log.info("DPD value field located successfully.");

    	        Log.info("Locating the Percent Overdue To EMI field...");
    	        WebElement percentOverdueToEMI = driver.findElement(CallCenterAccountFiltrationRepo.percentOverdueToEMI);
    	        Log.info("Percent Overdue To EMI field located successfully.");

    	        Log.info("Locating the Percent Overdue To EMI value field...");
    	        WebElement percentOverdueToEMIvalue = driver.findElement(CallCenterAccountFiltrationRepo.percentOverdueToEMIvalue);
    	        Log.info("Percent Overdue To EMI value field located successfully.");

    	        Log.info("Locating the Corporate BCBF field...");
    	        WebElement corporateBCBF = driver.findElement(CallCenterAccountFiltrationRepo.corporateBCBF);
    	        Log.info("Corporate BCBF field located successfully.");

    	        Log.info("Locating the Processing Interval field...");
    	        WebElement processingInterval = driver.findElement(CallCenterAccountFiltrationRepo.processingInterval);
    	        Log.info("Processing Interval field located successfully.");

    	        Log.info("Locating the Expiry Date field...");
    	        WebElement expiryDate = driver.findElement(CallCenterAccountFiltrationRepo.expiryDate);
    	        Log.info("Expiry Date field located successfully.");

    	        Log.info("Locating the To field...");
    	        WebElement to = driver.findElement(CallCenterAccountFiltrationRepo.to);
    	        Log.info("To field located successfully.");

    	        Log.info("Locating the Add button...");
    	        WebElement addbutton = driver.findElement(CallCenterAccountFiltrationRepo.addbutton);
    	        Log.info("Add button located successfully.");

    	        Log.info("Locating the Reset button...");
    	        WebElement resetbutton = driver.findElement(CallCenterAccountFiltrationRepo.resetbutton);
    	        Log.info("Reset button located successfully.");

    	        Log.info("Locating the Edit button...");
    	        WebElement editbutton = driver.findElement(CallCenterAccountFiltrationRepo.editbutton);
    	        Log.info("Edit button located successfully.");

    	        Log.info("Locating the Activate/Deactivate button...");
    	        WebElement activate_deactivatebutton = driver.findElement(CallCenterAccountFiltrationRepo.activate_deactivatebutton);
    	        Log.info("Activate/Deactivate button located successfully.");

    	        Log.info("Verifying if all fields and buttons are displayed...");
    	        boolean areAllElementsDisplayed = 
    	                allocationName.isDisplayed() &&
    	                effectDate.isDisplayed() &&
    	                assetCategory.isDisplayed() &&
    	                smaCategory.isDisplayed() &&
    	                npaCategory.isDisplayed() &&
    	                zone.isDisplayed() &&
    	                vertical.isDisplayed() &&
    	                schemeType.isDisplayed() &&
    	                productType.isDisplayed() &&
    	                schemeCode.isDisplayed() &&
    	                assetTaggingType.isDisplayed() &&
    	                outstandingBalance.isDisplayed() &&
    	                outstandingBalancevalue.isDisplayed() &&
    	                totalOverdue.isDisplayed() &&
    	                totalOverduevalue.isDisplayed() &&
    	                dpd.isDisplayed() &&
    	                dpdvalue.isDisplayed() &&
    	                percentOverdueToEMI.isDisplayed() &&
    	                percentOverdueToEMIvalue.isDisplayed() &&
    	                corporateBCBF.isDisplayed() &&
    	                processingInterval.isDisplayed() &&
    	                expiryDate.isDisplayed() &&
    	                to.isDisplayed() &&
    	                addbutton.isDisplayed() &&
    	                resetbutton.isDisplayed() &&
    	                editbutton.isDisplayed() &&
    	                activate_deactivatebutton.isDisplayed();

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
 // Method to enter Allocation Name
    public void enterAllocationName(String name) {
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	 wait.until(ExpectedConditions.visibilityOfElementLocated(CallCenterAccountFiltrationRepo.zoneall));
    	Log.info("Starting the process to enter the Allocation Name...");

        try {
            Log.info("Locating the Allocation Name field...");
            WebElement allocationName = driver.findElement(CallCenterAccountFiltrationRepo.allocationName);
            Log.info("Allocation Name field located successfully.");

            Log.info("Entering the value '" + name + "' into the Allocation Name field...");
            allocationName.sendKeys(name);
            Log.info("Value '" + name + "' entered into the Allocation Name field successfully.");

        } catch (Exception e) {
            Log.error("An error occurred while entering the Allocation Name: " + e.getMessage(), e);
            throw e; // Re-throwing the exception for higher-level handling, if needed.
        }

        Log.info("Process to enter Allocation Name completed successfully.");
    }

    // Method to click Add button
    public void clickAddButton() {
    	 Log.info("Starting the process to click the Add button...");

    	    try {
    	        Log.info("Locating the Add button...");
    	        WebElement addbutton = driver.findElement(CallCenterAccountFiltrationRepo.addbutton);
    	        Log.info("Add button located successfully.");

    	        Log.info("Clicking the Add button...");
    	        addbutton.click();
    	        Log.info("Add button clicked successfully.");

    	    } catch (Exception e) {
    	        Log.error("An error occurred while clicking the Add button: " + e.getMessage(), e);
    	        throw e; 
    	    }

    	    Log.info("Process to click the Add button completed successfully.");
    }
    
 // Method to click on Effect Date field
    public void clickEffectDateField() {
    	Log.info("Starting the process to click the Effect Date field...");

        try {
            Log.info("Locating the Effect Date field...");
            WebElement effectDate = driver.findElement(CallCenterAccountFiltrationRepo.effectDate);
            Log.info("Effect Date field located successfully.");

            Log.info("Clicking the Effect Date field...");
            effectDate.click();
            Log.info("Effect Date field clicked successfully.");

        } catch (Exception e) {
            Log.error("An error occurred while clicking the Effect Date field: " + e.getMessage(), e);
            throw e; 
        }

        Log.info("Process to click the Effect Date field completed successfully.");
    }
    
    public void selectDateFromDatePicker(String date) {
    	Log.info("Starting the process to select the date from the date picker...");

        try {
            Log.info("Locating the date value '" + date + "' in the date picker...");
            WebElement effectDatevalue = driver.findElement(CallCenterAccountFiltrationRepo.effectDatevalue(date));
            Log.info("Date value '" + date + "' located successfully in the date picker.");

            Log.info("Selecting the date '" + date + "' from the date picker...");
            effectDatevalue.click();
            Log.info("Date '" + date + "' selected successfully from the date picker.");

        } catch (Exception e) {
            Log.error("An error occurred while selecting the date '" + date + "' from the date picker: " + e.getMessage(), e);
            throw e;
        }

        Log.info("Process to select the date from the date picker completed successfully.");
    }
    
 // Method to get the value from Effect Date field
    public String getEffectDateFieldValue() {
    	Log.info("Starting the process to retrieve the value from the Effect Date field...");

        try {
            Log.info("Locating the Effect Date field...");
            WebElement effectDate = driver.findElement(CallCenterAccountFiltrationRepo.effectDate);
            Log.info("Effect Date field located successfully.");

            Log.info("Retrieving the value from the Effect Date field...");
            String value = effectDate.getAttribute("value");
            Log.info("Retrieved value from the Effect Date field: " + value);

            return value;

        } catch (Exception e) {
            Log.error("An error occurred while retrieving the value from the Effect Date field: " + e.getMessage(), e);
            throw e; // Re-throwing the exception for higher-level handling, if needed.
        }
    }
    
    // Method to verify date format
    public boolean isDateFormatDDMMYYY(String date) {
    	Log.info("Starting the process to verify if the date format is 'dd-MM-yyyy' for the date: " + date);

        try {
            Log.info("Attempting to parse the date '" + date + "' using the 'dd-MM-yyyy' format...");
            new SimpleDateFormat("dd-MM-yyyy").parse(date);
            Log.info("Date '" + date + "' matches the 'dd-MM-yyyy' format.");
            return true;
        } catch (Exception e) {
            Log.error("An error occurred while verifying the date format for '" + date + "': " + e.getMessage(), e);
            return false;
        }
    }
    
    // Method to select 'All' in Asset Category Dropdown
    public void selectAllInAssetCategory() throws InterruptedException {
    	Log.info("Starting the process to select all in Asset Category...");

        try {
        	Thread.sleep(10000);
            Log.info("Waiting for spinner to be invisible...");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
            Log.info("Locating the Asset Category field...");
            WebElement assetCategory = driver.findElement(CallCenterAccountFiltrationRepo.assetCategory); 
            Log.info("Asset Category field located successfully.");

            Log.info("Clicking on the Asset Category field...");
            assetCategory.click();
            Log.info("Asset Category clicked successfully.");

            Log.info("Waiting for spinner to be invisible again...");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
            Thread.sleep(10000);

            Log.info("Locating the 'Select All' option in Asset Category...");
            WebElement assetCategoryselectall = driver.findElement(CallCenterAccountFiltrationRepo.assetCategoryselectall);
            Log.info("'Select All' option in Asset Category located successfully.");

            Log.info("Clicking the 'Select All' option...");
            assetCategoryselectall.click();
            Log.info("'Select All' option clicked successfully.");

            Log.info("Waiting for spinner to be invisible once more...");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
            Thread.sleep(10000);

            Log.info("Locating the outside area element...");
            WebElement outarea2 = driver.findElement(CallCenterAccountFiltrationRepo.outarea2);
            Log.info("Outside area element located successfully.");

            Log.info("Clicking the outside area element...");
            outarea2.click();
            Log.info("Outside area clicked successfully.");

        } catch (Exception e) {
            Log.error("An error occurred while selecting all in Asset Category: " + e.getMessage(), e);
            throw e; 
        }

        Log.info("Process to select all in Asset Category completed successfully.");
    }

    // Method to select 'All' in SMA Category Dropdown
    public void selectAllInSmaCategory() throws InterruptedException {
    	Log.info("Starting the process to select all in SMA Category...");

        try {
            Log.info("Waiting for spinner to be invisible...");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

            Log.info("Locating the SMA Category field...");
            WebElement smaCategory = driver.findElement(CallCenterAccountFiltrationRepo.smaCategory);
            Log.info("SMA Category field located successfully.");

            Log.info("Clicking on the SMA Category field...");
            smaCategory.click();
            Log.info("SMA Category clicked successfully.");

            Log.info("Waiting for spinner to be invisible again...");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
            Thread.sleep(5000);

            Log.info("Locating the 'Select All' option in SMA Category...");
            WebElement smaCategoryselectall = driver.findElement(CallCenterAccountFiltrationRepo.smaCategoryselectall);
            Log.info("'Select All' option in SMA Category located successfully.");

            Log.info("Clicking the 'Select All' option...");
            smaCategoryselectall.click();
            Log.info("'Select All' option clicked successfully.");

            Log.info("Waiting for spinner to be invisible once more...");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

            Log.info("Locating the outside area element...");
            WebElement outarea2 = driver.findElement(CallCenterAccountFiltrationRepo.outarea2);
            Log.info("Outside area element located successfully.");

            Log.info("Clicking the outside area element...");
            outarea2.click();
            Log.info("Outside area clicked successfully.");

        } catch (Exception e) {
            Log.error("An error occurred while selecting all in SMA Category: " + e.getMessage(), e);
            throw e; 
        }

        Log.info("Process to select all in SMA Category completed successfully.");
    }

    // Method to select 'All' in NPA Category Dropdown
    public void selectAllInNpaCategory() throws InterruptedException {
    	Log.info("Starting the process to select all in NPA Category...");

        try {
            Log.info("Waiting for spinner to be invisible...");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

            Log.info("Locating the NPA Category field...");
            WebElement npaCategory = driver.findElement(CallCenterAccountFiltrationRepo.npaCategory);
            Log.info("NPA Category field located successfully.");

            Log.info("Clicking on the NPA Category field...");
            npaCategory.click();
            Log.info("NPA Category clicked successfully.");

            Log.info("Waiting for spinner to be invisible again...");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
            Thread.sleep(5000);

            Log.info("Locating the 'Select All' option in NPA Category...");
            WebElement npaCategoryselectall = driver.findElement(CallCenterAccountFiltrationRepo.npaCategoryselectall);
            Log.info("'Select All' option in NPA Category located successfully.");

            Log.info("Clicking the 'Select All' option...");
            npaCategoryselectall.click();
            Log.info("'Select All' option clicked successfully.");

            Log.info("Waiting for spinner to be invisible once more...");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

            Log.info("Locating the outside area element...");
            WebElement outarea2 = driver.findElement(CallCenterAccountFiltrationRepo.outarea2);
            Log.info("Outside area element located successfully.");

            Log.info("Clicking the outside area element...");
            outarea2.click();
            Log.info("Outside area clicked successfully.");

        } catch (Exception e) {
            Log.error("An error occurred while selecting all in NPA Category: " + e.getMessage(), e);
            throw e; 
        }

        Log.info("Process to select all in NPA Category completed successfully.");
    }
    
 // Method to select Mumbai from Zone dropdown
    public void selectZone(String zoneName) throws InterruptedException {
    	Log.info("Starting the process to select zone: " + zoneName);

        try {
            Log.info("Waiting for spinner to be invisible...");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

            Log.info("Locating the Zone field...");
            WebElement zone = driver.findElement(CallCenterAccountFiltrationRepo.zone);
            Log.info("Zone field located successfully.");

            Log.info("Clicking on the Zone field...");
            zone.click();
            Log.info("Zone field clicked successfully.");

            Log.info("Waiting for spinner to be invisible again...");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
            Thread.sleep(5000);

            Log.info("Locating the Zone value field for zone name: " + zoneName);
            WebElement zonevalue = driver.findElement(CallCenterAccountFiltrationRepo.zonevalue(zoneName));
            Log.info("Zone value for '" + zoneName + "' located successfully.");

            Log.info("Clicking on the Zone value...");
            zonevalue.click();
            Log.info("Zone value clicked successfully.");

            Log.info("Waiting for spinner to be invisible once more...");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        } catch (Exception e) {
        	Log.error("An error occurred while selecting the zone: " + zoneName, e);
            throw e; // Re-throwing the exception for higher-level handling, if needed.
        }

        Log.info("Process to select zone '" + zoneName + "' completed successfully.");
    }
    
 // Method to select Daily from Processing Interval dropdown
    public void selectProcessingInterval(String interval) throws InterruptedException {
    	Log.info("Starting the process to select the processing interval...");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        
        // Locating the processing interval element
        Log.info("Locating the processing interval element...");
        WebElement processingInterval = driver.findElement(CallCenterAccountFiltrationRepo.processingInterval);
        Log.info("Processing interval element located successfully.");
        
        // Waiting for spinner to be invisible
        Log.info("Waiting for spinner to be invisible...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Spinner is invisible.");
        
        // Clicking the processing interval element
        Log.info("Clicking the processing interval element...");
        processingInterval.click();
        Log.info("Processing interval element clicked successfully.");
        
        // Waiting for spinner to be invisible again
        Log.info("Waiting for spinner to be invisible after clicking...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Spinner is invisible after click.");
        
        // Sleeping for a brief moment (this may not be necessary if the spinner visibility is sufficient)
        Thread.sleep(5000);
        
        // Locating the processing interval value based on the provided interval
        Log.info("Locating the processing interval value for: " + interval);
        WebElement processingIntervalvalue = driver.findElement(CallCenterAccountFiltrationRepo.processingIntervalvalue(interval));
        Log.info("Processing interval value for '" + interval + "' located successfully.");
        
        // Clicking the selected processing interval value
        Log.info("Clicking the selected processing interval value...");
        processingIntervalvalue.click();
        Log.info("Processing interval value clicked successfully.");
        
        // Waiting for spinner to be invisible after click
        Log.info("Waiting for spinner to be invisible after selecting the processing interval...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Spinner is invisible after selecting the processing interval.");
        
        Log.info("Processing interval selection process completed successfully.");
    }
    
 // Method to select Call Centre from To dropdown 
    public void selectTo(String toOption) throws InterruptedException {
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

    	    // Log before locating the 'To' element
    	    Log.info("Locating the 'To' field...");

    	    WebElement to = driver.findElement(CallCenterAccountFiltrationRepo.to);

    	    // Log after locating the 'To' element
    	    Log.info("'To' field located successfully.");

    	    wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

    	    // Log before clicking the 'To' field
    	    Log.info("Clicking on the 'To' field...");

    	    to.click();

    	    // Log after clicking the 'To' field
    	    Log.info("'To' field clicked successfully.");

    	    wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

    	    // Adding a delay
    	    Thread.sleep(5000);

    	    // Log before locating the 'To' value element
    	    Log.info("Locating the 'To' value field for option: " + toOption);

    	    WebElement tovalue = driver.findElement(CallCenterAccountFiltrationRepo.tovalue(toOption));

    	    // Log after locating the 'To' value element
    	    Log.info("'To' value for located successfully." + toOption);
    	    

    	    wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

    	    // Log before clicking the 'To' value field
    	    Log.info("Clicking on the 'To' value...");

    	    tovalue.click();

    	    // Log after clicking the 'To' value field
    	    Log.info("'To' value clicked successfully.");

    	    wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

    	    Log.info("Process to select 'To' option '{}' completed successfully." +toOption);
    }
    
 // Method to click the add button
    public void clickAddButtonaftergivingrequiredvalues() {
    	 Log.info("Starting the process to click the Add button after providing required values...");

    	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

    	    Log.info("Locating the Add button...");
    	    WebElement addbutton = driver.findElement(CallCenterAccountFiltrationRepo.addbutton);
    	    Log.info("Add button located successfully.");

    	    Log.info("Locating the first row in the grid...");
    	    WebElement firstrowingrid = driver.findElement(CallCenterAccountFiltrationRepo.firstrowingrid);
    	    Log.info("First row in the grid located successfully.");

    	    Log.info("Clicking on the Add button...");
    	    addbutton.click();
    	    Log.info("Add button clicked successfully.");

    	    Log.info("Waiting for spinner to become invisible...");
    	    wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	    Log.info("Spinner is now invisible.");

    	    Log.info("Scrolling to the first row in the grid...");
    	    JavascriptExecutor js = (JavascriptExecutor) driver;
    	    js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", firstrowingrid);
    	    Log.info("Scrolled to the first row in the grid.");

    	    Log.info("Process to click Add button and scroll completed successfully.");
    }
    
    public boolean allocationNameInGrid(String text) {
    	Log.info("Starting the process to check for the visibility of allocation name in the grid with text: {}" +text);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Waiting for the spinner to become invisible...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Spinner is now invisible.");

        try {
            Log.info("Waiting for the visibility of grid element with text: {}" +text);
            // Wait for the visibility of the grid element with the given text
            WebElement allocationName = wait.until(ExpectedConditions.visibilityOfElementLocated(CallCenterAccountFiltrationRepo.gridvalues(text)));
            Log.info("Element with text '{}' is visible." +text);

            // If the element is visible, return true
            return allocationName.isDisplayed();
        } catch (TimeoutException e) {
            // Log or handle the timeout scenario as needed
            Log.error("Element with the text '{}' is not visible within the timeout period." +text);
            return false;
        }
    }
    
    public boolean zoneNameInGrid(String text) {
    	 Log.info("Starting the process to check for the visibility of zone name in the grid with text: {}" +text);

    	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

    	    Log.info("Waiting for the spinner to become invisible...");
    	    wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	    Log.info("Spinner is now invisible.");

    	    try {
    	        Log.info("Waiting for the visibility of grid element with text: {}" +text);
    	        // Wait for the visibility of the grid element with the given text
    	        WebElement zoneName = wait.until(ExpectedConditions.visibilityOfElementLocated(CallCenterAccountFiltrationRepo.gridvalues(text)));
    	        Log.info("Element with text '{}' is visible." +text);

    	        // If the element is visible, return true
    	        return zoneName.isDisplayed();
    	    } catch (TimeoutException e) {
    	        // Log or handle the timeout scenario as needed
    	        Log.error("Element with the text '{}' is not visible within the timeout period." +text);
    	        return false;
    	    }
    }
    
    // Method to check if status is Active (green tick)
    public boolean isStatusActive() throws InterruptedException {
    	Log.info("Starting the process to check if the status is active.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        
        Log.info("Waiting for the spinner to become invisible...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Spinner is now invisible.");

        Log.info("Locating the status icon...");
        WebElement statusIcon = driver.findElement(CallCenterAccountFiltrationRepo.statusColumnactive);

        Log.info("Scrolling the status icon into view...");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", statusIcon);

        // Optional: You can log any additional information like style attribute
        // String style = statusIcon.getAttribute("style");
        // Log.info("Status icon style attribute: {}", style);
        
        Thread.sleep(1000);
        Log.info("Checking the status icon's style to determine if it's active...");
        
        boolean isActive = statusIcon.getAttribute("style").contains("color: green");
        
        if (isActive) {
            Log.info("Status is active (green color).");
        } else {
            Log.info("Status is not active.");
        }

        return isActive;
    }
    
 // Method to select an allocation name
    public void selectAllocationName() throws InterruptedException {
    	Log.info("Starting the process to select allocation name.");

        // Locate the allocation name checkbox
        Log.info("Locating the allocation name checkbox...");
        WebElement allocationNameCheckbox = driver.findElement(CallCenterAccountFiltrationRepo.allocationNameCheckbox);

        // Scroll the checkbox into view
        Log.info("Scrolling the allocation name checkbox into view...");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", allocationNameCheckbox);

        // Optional: You can add log to indicate the checkbox is in view now.
        Log.info("Allocation name checkbox is now in view.");

        // Sleep to allow UI adjustments
        Log.info("Waiting for 1 second before clicking...");
        Thread.sleep(1000);

        // Click the checkbox
        Log.info("Clicking the allocation name checkbox...");
        allocationNameCheckbox.click();

        Log.info("Allocation name checkbox clicked successfully.");
    }
    
    // Method to verify Deactivate button is enabled
    public boolean isDeactivateButtonEnabled() {
    	Log.info("Starting the process to check if the deactivate button is enabled.");

        // Locate the deactivate button
        Log.info("Locating the deactivate button...");
        WebElement deactivateButton = driver.findElement(CallCenterAccountFiltrationRepo.deactivateButton);

        // Check if the button is enabled
        Log.info("Checking if the deactivate button is enabled...");
        boolean isEnabled = deactivateButton.isEnabled();

        // Log the result
        if (isEnabled) {
            Log.info("Deactivate button is enabled.");
        } else {
            Log.info("Deactivate button is disabled.");
        }

        return isEnabled;
    }
    
    // Method to click Deactivate button
    public void clickDeactivateButton() {
    	Log.info("Starting the process to click the deactivate button.");

        try {
            // Locate the deactivate button
            Log.info("Locating the deactivate button...");
            WebElement deactivateButton = driver.findElement(CallCenterAccountFiltrationRepo.deactivateButton);
            
            // Click the deactivate button
            Log.info("Clicking the deactivate button...");
            deactivateButton.click();
            Log.info("Deactivate button clicked successfully.");

        } catch (Exception e) {
            Log.error("An error occurred while clicking the deactivate button.", e);
            throw e; // Rethrow the exception for further handling if needed
        }

        Log.info("Process to click the deactivate button completed.");
    }
 // Method to get validation message text
    public String getValidationMessage() {
    	Log.info("Starting the process to get the validation message.");

        try {
            // Wait for the validation message element to be visible
            Log.info("Waiting for the validation message to be visible...");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
            WebElement validationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(CallCenterAccountFiltrationRepo.validationMessage));
            Log.info("Validation message element is now visible.");

            // Get the text of the validation message
            Log.info("Retrieving the validation message text...");
            String message = validationMessage.getText();
            Log.info("Validation message retrieved: {}" +message);
            Log.info("Process to get validation message completed.");
            return message;

        } catch (Exception e) {
            Log.error("An error occurred while retrieving the validation message.", e);
            throw e; // Rethrow the exception for higher-level handling, if needed
        }
    }
    
    // Method to check if status is Active (green tick)
    public boolean isStatusInActive() throws InterruptedException {
    	Log.info("Starting the process to check if the status is inactive.");

        try {
            // Waiting for the validation message and spinner to be invisible
            Log.info("Waiting for the validation message and spinner to be invisible...");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(CallCenterAccountFiltrationRepo.validationMessage));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
            Log.info("Validation message and spinner are now invisible.");

            // Locate the status icon
            Log.info("Locating the status icon for inactive status...");
            WebElement statusIcon = driver.findElement(CallCenterAccountFiltrationRepo.statusColumninactive);
            Log.info("Status icon located successfully.");

            // Scroll into view
            Log.info("Scrolling the status icon into view...");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", statusIcon);
            Log.info("Status icon scrolled into view successfully.");

            // Wait for a moment for the UI to update
            Log.info("Waiting for 1 second to allow UI updates...");
            Thread.sleep(1000);

            // Check if the status is inactive (color: red)
            Log.info("Checking if the status is inactive (color: red)...");
            boolean isInactive = statusIcon.getAttribute("style").contains("color: red");
            Log.info("Status inactive check completed. Result: {}" +isInactive);

            return isInactive;
        } catch (Exception e) {
            Log.error("An error occurred while checking if the status is inactive.", e);
            throw e; // Rethrow the exception for higher-level handling, if needed
        }
    }
    
 // Method to verify Activate button is enabled
    public boolean isActivateButtonEnabled() {
    	Log.info("Starting the process to check if the 'Activate' button is enabled.");

        try {
            // Locate the activate button
            Log.info("Locating the 'Activate' button...");
            WebElement activateButton = driver.findElement(CallCenterAccountFiltrationRepo.activateButton);
            Log.info("'Activate' button located successfully.");

            // Check if the activate button is enabled
            Log.info("Checking if the 'Activate' button is enabled...");
            boolean isEnabled = activateButton.isEnabled();
            Log.info("'Activate' button enabled status: {}" +isEnabled);

            return isEnabled;
        } catch (Exception e) {
            Log.error("An error occurred while checking if the 'Activate' button is enabled.", e);
            throw e; // Rethrow the exception for higher-level handling, if needed
        }
    }

    // Method to click Activate button
    public void clickActivateButton() {
    	Log.info("Starting the process to click the 'Activate' button.");

        try {
            // Locate the 'Activate' button
            Log.info("Locating the 'Activate' button...");
            WebElement activateButton = driver.findElement(CallCenterAccountFiltrationRepo.activateButton);
            Log.info("'Activate' button located successfully.");

            // Click on the 'Activate' button
            Log.info("Clicking on the 'Activate' button...");
            activateButton.click();
            Log.info("'Activate' button clicked successfully.");
            
        } catch (Exception e) {
            Log.error("An error occurred while clicking the 'Activate' button.", e);
            throw e; // Rethrow the exception for higher-level handling, if needed
        }

        Log.info("Process to click the 'Activate' button completed successfully.");
    }
    
 // Method to get the total accounts count from the grid
    public String getTotalAccountsCount() {
    	Log.info("Starting the process to get the total accounts count...");
    	Log.info("Locating the element that contains the total accounts count...");
    	WebElement accontsselected = driver.findElement(CallCenterAccountFiltrationRepo.totalaccountselectedingrid);
    	String totalAccountsCount = accontsselected.getText();
        Log.info("Successfully retrieved the total accounts count: " + totalAccountsCount);

        return totalAccountsCount;
    }

    // Method to get the total outstanding amount from the grid
    public String getTotalOutstandingAmount() {
    	Log.info("Starting the process to get the total outstanding amount...");
    	Log.info("Locating the element that contains the total outstanding amount...");
    	WebElement outstandingamount = driver.findElement(CallCenterAccountFiltrationRepo.totaloutstandingamountingrid);
    	String totalOutstandingAmount = outstandingamount.getText();
        Log.info("Successfully retrieved the total outstanding amount: " + totalOutstandingAmount);

        return totalOutstandingAmount;
    }
    
 // Method to click on the Download File dropdown
    public void clickDownloadDropdown() {
    	Log.info("Starting the process to click the Download Dropdown...");
    	Log.info("Locating the Download Dropdown element...");
    	WebElement downloadfiledropdown = driver.findElement(CallCenterAccountFiltrationRepo.downloadfiledropdown);
    	 // Log before clicking the element
        Log.info("Clicking the Download Dropdown...");
        downloadfiledropdown.click();
        
        // Log after clicking the element
        Log.info("Successfully clicked the Download Dropdown.");
    }

    // Method to select the List of Accounts option
    public void selectListOfAccounts(String value) {
    	 // Log the start of the process
        Log.info("Starting the process to select a list of accounts with value: " + value);

        // Log before finding the dropdown value element
        Log.info("Locating the dropdown value element for: " + value);
        WebElement downloadfiledropdownvalue = driver.findElement(CallCenterAccountFiltrationRepo.downloadfiledropdownvalue(value));
        
        // Log before clicking the dropdown value
        Log.info("Clicking the dropdown value: " + value);
        downloadfiledropdownvalue.click();
        
        // Log before finding the outside area element
        Log.info("Locating the outside area element to click...");
        WebElement outside = driver.findElement(CallCenterAccountFiltrationRepo.outarea);
        
        // Log before clicking the outside area
        Log.info("Clicking the outside area to close the dropdown...");
        outside.click();

        // Log after completing the action
        Log.info("Successfully selected the list of accounts and closed the dropdown.");
    }

    // Method to click the Download button
    public void clickDownloadButton() {
    	   // Log the start of the process
        Log.info("Starting the process to click the Download Button...");

        // Log before locating the download button
        Log.info("Locating the Download Button...");
        WebElement downloadbutton = driver.findElement(CallCenterAccountFiltrationRepo.downloadbutton);
        
        // Log before clicking the download button
        Log.info("Clicking the Download Button...");
        downloadbutton.click();
        
        // Log before waiting for the spinner to disappear
        Log.info("Waiting for the spinner to disappear...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofDays(1)); // It's better to use a reasonable wait time like 30 seconds
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        
        // Log after the spinner disappears
        Log.info("Successfully waited for the spinner to disappear. Download Button click process completed.");
    }
    
 // Method to verify accounts allocation
    public boolean verifyAccountsAllocation(String expectedData) {
    	Log.info("Starting the process to verify account allocation...");

        try {
            // Log before reading the last Sr No. from the downloaded file
            Log.info("Getting the last Sr No. from the latest downloaded file...");
            int lastSrNo = DownloadedExcelReader.getLastSrNo();

            // Convert the integer to a String to match expectedData type
            String actualData = String.valueOf(lastSrNo);

            // Log before comparing the expectedData with actualData
            Log.info("Comparing expectedData: " + expectedData + " with actualData: " + actualData);
            
            // Compare expectedData with actualData and return the result
            boolean isDataMatching = expectedData.equals(actualData);
            
            // Log the result of the comparison
            if (isDataMatching) {
                Log.info("Data match successful: Expected data matches the actual data.");
            } else {
                Log.info("Data mismatch: Expected data does not match the actual data.");
            }
            
            return isDataMatching;

        } catch (IOException e) {
            // Log the error if an exception occurs
            Log.error("Error while reading the Excel file: " + e.getMessage(), e);
            return false; // Or handle error appropriately
        }
}
}
