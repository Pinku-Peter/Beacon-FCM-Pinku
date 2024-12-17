package com.testautomation.pages;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.BasePackage.DBUtils;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Page_Repository.UpdationofDispositionRepo;
import com.Utility.Log;

public class UpdationofDispositionPage {
	
	private WebDriver driver;
	private String AccoutNumber;
	public UpdationofDispositionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        Log.info("UpdationofDispositionPage initialized.");
    }
	
	// Navigate to the Disposition Updation screen
    public void navigateToUpdationOfDisposition() {
    	Log.info("Method navigateToUpdationOfDisposition started.");
    	try {
    		Log.info("Locating Disposition menu.");
		WebElement disposition = driver.findElement(UpdationofDispositionRepo.dispositionMenu);
		Log.info("Clicking on Disposition menu.");
		disposition.click();
		Log.info("Locating Updation of Disposition menu.");
		WebElement UpdationofDisposition = driver.findElement(UpdationofDispositionRepo.updationOfDispositionMenu);
		Log.info("Clicking on the Updation of Disposition.");
		UpdationofDisposition.click();
		Log.info("Successfully navigated to the Updation of Disposition screen.");
    	  } catch (Exception e) {
    	        Log.error("Error occurred in navigateToUpdationOfDisposition: " + e.getMessage(), e);
    	        throw e;
    	    }
    	    Log.info("Method navigateToUpdationOfDisposition completed.");
    }
    
 // Interact with Account Number Field
    public void enterAccountNumber(String accountNumber) {
    	Log.info("Starting to enter account number.");
    	Log.info("Locating the account number field.");
    	WebElement accountnumber = driver.findElement(UpdationofDispositionRepo.accountNumberField);
    	Log.info("Clearing the account number field.");
    	accountnumber.clear();
    	Log.info("Entering the account number: " + accountNumber);
    	accountnumber.sendKeys(accountNumber);
    	Log.info("Verifying the entered account number.");
    	// Verify if the value is actually entered (optional, for additional validation)
        if (accountnumber.getAttribute("value").equals(accountNumber)) {
        	Log.info("Account number entered successfully.");
        } else {
        	Log.error("Failed to enter the account number. Either invalid input or a problem with the field.");
        	Log.error("Should not allow typing alphabets or special characters in the account number field.");
    }
        Log.info("Finished entering account number.");
  }
    
 // Interact with Account Number Field
    public void enterAccountNumberwithmore25characters(String accountNumber) {
    	Log.info("Starting to interact with the Account Number field.");
    	 try {
    		 Log.info("Attempting to locate the Account Number field.");
    	WebElement accountnumber = driver.findElement(UpdationofDispositionRepo.accountNumberField);
    	Log.info("Successfully located the Account Number field.");
    	Log.info("Clearing any existing value in the Account Number field.");
    	accountnumber.clear();
    	 Log.info("Successfully cleared the Account Number field.");
    	 Log.info("Entering the account number: " + accountNumber);
    	accountnumber.sendKeys(accountNumber);
    	 Log.info("Successfully entered the account number.");
    	 Log.info("Validating the entered account number.");
    	// Verify if the value is actually entered (optional, for additional validation)
        if (accountnumber.getAttribute("value").equals(accountNumber)) {
        	Log.info("Account number entered successfully.");
            System.out.println("Account number entered successfully.");
            Log.info("Account number entered successfully.");
        } else {
            Log.error("Failed to enter the account number. Entry might be restricted to 25 characters.");
            System.out.println("Not able to enter the account number.");
    }
    	 } catch (Exception e) {
    	        Log.error("An error occurred while interacting with the Account Number field: " + e.getMessage(), e);
    	        throw e; // Re-throw the exception for further handling
    	    } finally {
    	        Log.info("Completed interaction with the Account Number field.");
    	    }
  }
    
 // Interact with Account Number Field
    public void enterInvalidAccountNumber(String accountNumber) {
    	Log.info("Starting the process of entering an invalid account number.");
    	try {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	Log.info("Attempting to locate the Account Number field.");
    	WebElement accountnumber = driver.findElement(UpdationofDispositionRepo.accountNumberField);
    	Log.info("Successfully located the Account Number field.");
    	Log.info("Clearing the existing value in the Account Number field.");
    	accountnumber.clear();
    	Log.info("Successfully cleared the Account Number field.");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Entering the invalid account number: " + accountNumber);
    	accountnumber.sendKeys(accountNumber);
    	Log.info("Successfully entered the invalid account number.");
    	Log.info("Validating the entered account number.");
    	// Verify if the value is actually entered (optional, for additional validation)
        if (accountnumber.getAttribute("value").equals(accountNumber)) {
        	Log.info("Account number entered successfully.");
            System.out.println("Account number entered successfully.");
        } else {
        	Log.error("Failed to enter the account number. Validation failed.");
            System.out.println("Not able to enter the account number.");
    }
    	} catch (Exception e) {
            Log.error("An error occurred while entering the invalid account number: " + e.getMessage(), e);
            throw e; // Re-throw the exception for further handling
        } finally {
            Log.info("Completed the process of entering an invalid account number.");
        }

  }
    
    public void withoutAccountNumber() {
    	Log.info("Starting the process to clear the Account Number field without entering a value.");
    	 try {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	Log.info("Attempting to locate the Account Number field.");
    	WebElement accountnumber = driver.findElement(UpdationofDispositionRepo.accountNumberField);
    	Log.info("Successfully located the Account Number field.");
    	Log.info("Clearing any existing value in the Account Number field.");
    	accountnumber.clear();
    	Log.info("Successfully cleared the Account Number field.");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	 } catch (Exception e) {
    	        Log.error("An error occurred while clearing the Account Number field: " + e.getMessage(), e);
    	        throw e; // Re-throwing the exception for further handling
    	    } finally {
    	        Log.info("Completed the process of clearing the Account Number field without entering a value.");
    	    }
  }
    
 // Click Search
    public void clickSearchButton() {
    	Log.info("Starting the process to click the Search button.");
    	try {
    	Log.info("Attempting to locate the Search button.");
    	WebElement searchButton = driver.findElement(UpdationofDispositionRepo.searchButton);
    	Log.info("Successfully located the Search button.");
    	 Log.info("Attempting to click the Search button.");
        searchButton.click();
        Log.info("Successfully clicked the Search button.");
    	} catch (Exception e) {
            Log.error("An error occurred while attempting to click the Search button: " + e.getMessage(), e);
            throw e; // Re-throwing the exception for further handling
        } finally {
            Log.info("Completed the process of interacting with the Search button.");
        }
    }
    
    // Get Error Message Text
    public String getErrorMessage() {
    	Log.info("Starting the process to retrieve the error message.");
    	String errorMessage = null;
    	try {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    	WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.errorMessage8));
    	Log.info("Error message element is now visible.");
    	Log.info("Attempting to retrieve the error message text.");
    	errorMessage = msg.getText();
    	Log.info("Successfully retrieved the error message: " + errorMessage);
    	} catch (Exception e) {
            Log.error("An error occurred while retrieving the error message: " + e.getMessage(), e);
            throw e; // Re-throwing the exception for further handling

        } finally {
            Log.info("Completed the process to retrieve the error message.");
        }

        return errorMessage;
        
    }
    
    // Get Error Message Text
    public String getErrorMessageforemptysearch() {
    	Log.info("Starting the process to retrieve the error message for an empty search.");
    	String errorMessage = null;
    	try {
    		Log.info("Waiting for the error message element to become visible.");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    	WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.errorMessage));
    	Log.info("Error message element is now visible.");
    	Log.info("Attempting to retrieve the error message text.");
        errorMessage = msg.getText();
        Log.info("Successfully retrieved the error message: " + errorMessage);
    	} catch (Exception e) {
            Log.error("An error occurred while retrieving the error message: " + e.getMessage(), e);
            throw e; // Re-throwing the exception for further handling

        } finally {
            Log.info("Completed the process to retrieve the error message for an empty search.");
        }

        return errorMessage;
    }

 // Interact with Account Number Field
    public String entervalidAccountNumber() {
    	Log.info("Starting the process to retrieve and enter a valid account number.");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement userid = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.loginuserid));
    	String procedureCall = "{CALL SPGETBRANCHALLOCATEDACCOUNTS(?, ?)}";
        String userId = userid.getText(); // Input parameter
        Log.info("Retrieved user ID: " + userId);
    	WebElement accountnumber = driver.findElement(UpdationofDispositionRepo.accountNumberField);
    	 Log.info("Waiting for any spinner to disappear before proceeding with account number entry.");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        try {
        	Log.info("Calling stored procedure to fetch branch allocated accounts.");
            List<String> AcNo = DBUtils.callStoredProcedureWithRefCursor(procedureCall, userId);
            AccoutNumber = String.join(", ", AcNo);
            Log.info("Fetched user branch account numbers: " + AccoutNumber);
            Log.info("Clearing the account number field before entering the new value.");
            accountnumber.clear();
            Log.info("Waiting for any spinner to disappear before entering the account number.");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
            Log.info("Entering the account number into the field.");
            accountnumber.sendKeys(AccoutNumber);
        	// Verify if the value is actually entered (optional, for additional validation)
            if (accountnumber.getAttribute("value").equals(AccoutNumber)) {
            	 Log.info("Account number entered successfully: " + AccoutNumber);
                System.out.println("Account number entered successfully.");
            } else {
            	 Log.warn("Failed to enter the account number correctly.");
                System.out.println("Not able to enter the account number.");
        }
             
        } catch (Exception e) {
        	Log.error("Failed to execute stored procedure or interact with the account number field: " + e.getMessage(), e);
            System.err.println("Failed to execute stored procedure: " + e.getMessage());
        }
        Log.info("Completed the process of entering the account number.");
        return AccoutNumber;
  }
    
    public void clickSaveButton() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement savebutton = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.savebutton));
    	savebutton.click();
    }
    
    public String getErrorMessagewithoutvalue() {
    	Log.info("Starting to retrieve the error message without a value.");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	 Log.info("Waiting for the error message element to be visible.");
    	WebElement errormessage = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.errorMessage2));
    	Log.info("Error message element is now visible. Retrieving the error message text.");
    	String errorMessageText = errormessage.getText();
    	Log.info("Retrieved error message: " + errorMessageText);

        return errorMessageText;
    }
    
    public void selectNextActionOwner(String owner) {
    	Log.info("Starting the process to select the next action owner.");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	Log.info("Waiting for the spinner to disappear before interacting with the dropdown.");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner is invisible, proceeding to select the next action owner.");
    	WebElement actionOwnerdropdown = driver.findElement(UpdationofDispositionRepo.nextActionOwnerDropdown);
    	Log.info("Clicking on the next action owner dropdown.");
    	actionOwnerdropdown.click();
    	Log.info("Next action owner dropdown clicked.");
    	Log.info("Waiting for the option '" + owner + "' to be visible in the dropdown.");
    	WebElement actionowner = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.nextActionOwnerDropdownvalues(owner)));
    	Log.info("Option '" + owner + "' is visible, clicking on it.");
    	actionowner.click();
    	Log.info("Selected '" + owner + "' as the next action owner.");
    }
    public String getErrorMessageafterenteronlyactionowner() {
    	Log.info("Starting the process to retrieve the error message after entering only the action owner.");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	Log.info("Waiting for the error message element to be visible.");
    	WebElement errormessage = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.errorMessage3));
    	Log.info("Error message element is visible.");
    	String errorMsg = errormessage.getText();
        Log.info("Error message retrieved: " + errorMsg);

        return errorMsg;
    }
    
    public void selectDisposition(String disposition) {
    	Log.info("Starting the process to select disposition.");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	Log.info("Waiting for the spinner to disappear before accessing the disposition dropdown.");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner disappeared, now interacting with disposition dropdown.");
    	WebElement dispositiondropdown = driver.findElement(UpdationofDispositionRepo.dispositionDropdown);
    	Log.info("Clicking on the disposition dropdown.");
    	dispositiondropdown.click();
    	Log.info("Disposition dropdown clicked.");
    	Log.info("Waiting for the spinner to disappear after opening the dropdown.");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner disappeared, now selecting the disposition value.");
    	WebElement dispositionvalue = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.dispositionDropdownvalues(disposition)));
    	Log.info("Found the disposition value: " + disposition + ", now clicking on it.");
    	dispositionvalue.click();
    	Log.info("Disposition value selected.");
    	Log.info("Waiting for the spinner to disappear after selecting the disposition.");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner disappeared, disposition selection process completed.");
    }
    
    public String getErrorMessageafterenterdisposition() {
    	 Log.info("Starting to retrieve error message after entering disposition.");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	Log.info("Waiting for the error message element to become visible.");
    	WebElement errormessage = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.errorMessage4));
    	Log.info("Error message element is now visible.");
    	 // Get the text of the error message
        String errorMessageText = errormessage.getText();
        Log.info("Retrieved the error message text: " + errorMessageText);

        // Return the error message
        return errorMessageText;
    }
    
    public void selectsubDisposition(String subdisposition) {
    	 Log.info("Starting to select the sub-disposition: " + subdisposition);
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	Log.info("Waiting for the spinner to disappear.");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner is no longer visible.");
    	WebElement subdispositiondropdown = driver.findElement(UpdationofDispositionRepo.subdispositionDropdown);
    	Log.info("Clicking on the sub-disposition dropdown.");
    	subdispositiondropdown.click();
    	Log.info("Sub-disposition dropdown clicked.");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Waiting for the spinner to disappear after clicking the dropdown.");
    	WebElement dispositionvalue = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.dispositionDropdownvalues(subdisposition)));
    	 Log.info("Sub-disposition value '" + subdisposition + "' is now visible. Clicking the value.");
    	dispositionvalue.click();
    	Log.info("Sub-disposition value '" + subdisposition + "' selected.");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner is no longer visible after selecting the sub-disposition.");
    }
    
    public String getErrorMessageafterentersubdisposition() {
    	Log.info("Starting to get the error message after entering sub-disposition.");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	Log.info("Waiting for the error message element to be visible.");
    	WebElement errormessage = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.errorMessage5));
    	 Log.info("Error message element is visible.");
    	 String errorMessageText = errormessage.getText();
    	    Log.info("Error message retrieved: " + errorMessageText);

    	    // Return the error message text
    	    return errorMessageText;
    }
    
    public void enterNextActionDate(String date) {
    	Log.info("Starting to enter the next action date: " + date);
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	Log.info("Waiting for the spinner to disappear before interacting with the datepicker.");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	 Log.info("Spinner disappeared. Proceeding with datepicker interaction.");
    	WebElement datepicker = driver.findElement(UpdationofDispositionRepo.datepicker);
    	Log.info("Clicking on the datepicker.");
    	datepicker.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	 Log.info("Spinner disappeared. Proceeding with selecting the date.");
    	WebElement day = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.day(date)));
    	Log.info("Selecting the day: " + date);
    	day.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Date selected successfully: " + date);
    }
    public String getErrorMessageafterenterdate() {
    	Log.info("Starting to retrieve the error message after entering the date.");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	Log.info("Waiting for the spinner to disappear before closing the popup.");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner disappeared. Proceeding to close the popup.");
    	WebElement popupclose = wait.until(
                ExpectedConditions.elementToBeClickable(UpdationofDispositionRepo.deviationrequestpopupclose));
    	Log.info("Clicking the close button on the popup.");
    	popupclose.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	 Log.info("Popup closed successfully, waiting for the spinner to disappear again.");
    	WebElement errormessage = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.errorMessage6));
    	Log.info("Retrieving the error message after entering the date.");
    	  String errorMessage = errormessage.getText();
    	    Log.info("Error message retrieved: " + errorMessage);

    	    return errorMessage;
    }
    
    public void enterRemarks(String remarks) {
    	 Log.info("Starting to enter remarks.");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	Log.info("Waiting for the spinner to disappear before interacting with the remarks field.");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner disappeared. Proceeding to click on the remarks field.");
    	WebElement remark = driver.findElement(UpdationofDispositionRepo.remarks);
    	Log.info("Clicking on the remarks field.");
    	remark.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner disappeared after clicking remarks field. Proceeding to enter remarks.");
    	remark.sendKeys(remarks);
    	Log.info("Entered remarks: " + remarks);
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner disappeared after entering remarks.");
    }
    
    public boolean isSuccessMessageDisplayed() {
    	Log.info("Starting to check for success message display.");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	Log.info("Waiting for the spinner to disappear before proceeding.");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner disappeared. Proceeding to close the popup if present.");
    	WebElement popupclose = wait.until(
        ExpectedConditions.elementToBeClickable(UpdationofDispositionRepo.deviationrequestpopupclose));
    	Log.info("Clicking on the popup close button.");
    	popupclose.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner disappeared after closing the popup. Proceeding to check for success message.");
    	WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.successMessage));
    	 Log.info("Checking visibility of the success message.");
    	 boolean isDisplayed = successMessage.isDisplayed();
    	    if (isDisplayed) {
    	        Log.info("Success message is displayed.");
    	    } else {
    	        Log.info("Success message is not displayed.");
    	    }

    	    // Return the result
    	    return isDisplayed;
    }
    
    public void enterAccountNumber() {
    	Log.info("Starting to enter the account number.");
    	if (AccoutNumber == null) {
    		Log.error("Account number is not fetched yet. Ensure 'entervalidAccountNumber()' is executed first.");
            System.err.println("Account number is not fetched yet. Ensure 'entervalidAccountNumber()' is executed first.");
            return;
        }
    	 Log.info("Locating the account number field on the page.");
        WebElement accountnumberField = driver.findElement(UpdationofDispositionRepo.accountNumberField);
        Log.info("Account number field located.");
        Log.info("Clearing any existing value in the account number field.");
        accountnumberField.clear();
        Log.info("Existing value cleared.");
        Log.info("Entering the fetched account number.");
        accountnumberField.sendKeys(AccoutNumber); // Use the stored account number
        Log.info("Account number entered successfully using the previously fetched value.");
    }
    
    public boolean isTransactionDisplayedWithExpectedDetails(String date, String userType) {
    	Log.info("Starting to check if the transaction is displayed with the expected details.");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    	Log.info("Creating a dynamic locator for transaction heading with date: " + date + " and userType: " + userType);
    	
        // Create a dynamic By locator using the provided date and userType
        By transactionHeadingLocator = UpdationofDispositionRepo.transactionHeading(date, userType);
        Log.info("Waiting for the transaction heading to be visible.");
        wait.until(ExpectedConditions.visibilityOfElementLocated(transactionHeadingLocator));
        try {
        	wait.until(ExpectedConditions.visibilityOfElementLocated(transactionHeadingLocator));
        	Log.info("Transaction heading is visible.");
        	 Log.info("Checking if the transaction heading is displayed.");
            // Check if the element is displayed
        	 boolean isDisplayed = driver.findElement(transactionHeadingLocator).isDisplayed();
        	 if (isDisplayed) {
                 Log.info("Transaction heading is displayed with the expected details.");
             } else {
                 Log.info("Transaction heading is not displayed with the expected details.");
             }

             return isDisplayed;
        } catch (NoSuchElementException e) {
            // Handle the case where the element is not found
        	Log.error("Transaction heading not found for the given date: " + date + " and userType: " + userType, e);
            return false;
        }
    }
    
    public void verifyInteractionDetails(String disposition, String subDisposition, String remarks, 
            String actionDoneBy, String userEIN, 
            String nextActionOwner, String nextActionDate) {
    		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    		Log.info("Verifying Disposition: " + disposition);
    		 // Verify Disposition
    	    Assert.assertTrue(
    	        wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.InteractionDetailsDisposition(disposition))).isDisplayed(),
    	        "Disposition value is not displayed: " + disposition);
    	    Log.info("Disposition verified successfully: " + disposition);
    	    Log.info("Verifying Sub-Disposition: " + subDisposition);
    		// Verify Sub-Disposition
    		Assert.assertTrue(
    				wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.InteractionDetailsSubDisposition(subDisposition))).isDisplayed(),
    				"Sub-Disposition value is not displayed: " + subDisposition);
    		Log.info("Sub-Disposition verified successfully: " + subDisposition);
    		Log.info("Verifying Remarks: " + remarks);
    		// Verify Remarks
    		Assert.assertTrue(
    				wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.InteractionDetailsRemarks(remarks))).isDisplayed(),
    				"Remarks value is not displayed: " + remarks);
    		Log.info("Remarks verified successfully: " + remarks);
    		Log.info("Verifying Action Done By: " + actionDoneBy);
    		// Verify Action Done By
    		Assert.assertTrue(
    				wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.InteractionDetailsActionDoneBy(actionDoneBy))).isDisplayed(),
    				"Action Done By value is not displayed: " + actionDoneBy);
    		Log.info("Action Done By verified successfully: " + actionDoneBy);
    		Log.info("Verifying User EIN: " + userEIN);
    		// Verify User EIN
    		Assert.assertTrue(
    				wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.InteractionDetailsUserEIN(userEIN))).isDisplayed(),
    				"User EIN value is not displayed: " + userEIN);
    		Log.info("User EIN verified successfully: " + userEIN);
    		Log.info("Verifying Next Action Owner: " + nextActionOwner);
    		// Verify Next Action Owner
    		Assert.assertTrue(
    				wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.InteractionDetailsNextActionOwner(nextActionOwner))).isDisplayed(),
    				"Next Action Owner value is not displayed: " + nextActionOwner);
    		Log.info("Next Action Owner verified successfully: " + nextActionOwner);
    		Log.info("Verifying Next Action Date: " + nextActionDate);
    		// Verify Next Action Date
    		Assert.assertTrue(
    				wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.InteractionDetailsNextActionDate(nextActionDate))).isDisplayed(),
    				"Next Action Date value is not displayed: " + nextActionDate);

    		Log.info("Next Action Date verified successfully: " + nextActionDate);

    }
    
    public void enterinvalidAccountNumbernotassigned() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	Log.info("Fetching the user ID from the page...");
    	WebElement userid = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.loginuserid));
    	String procedureCall = "{CALL SP_GET_USER_OTHERBRANCH_ACCOUNTS(?, ?)}";
        String userId = userid.getText(); // Input parameter
        Log.info("User ID fetched: " + userId);
    	WebElement accountnumber = driver.findElement(UpdationofDispositionRepo.accountNumberField);
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	 Log.info("Calling stored procedure to fetch account numbers for user: " + userId);
        try {
            List<String> AcNo = DBUtils.callStoredProcedureWithRefCursor(procedureCall, userId);
            AccoutNumber = String.join(", ", AcNo);
            Log.info("Fetched Account Numbers: " + AccoutNumber);
            Log.info("Entering the account number into the field...");
            accountnumber.sendKeys(AccoutNumber);
            Log.info("Verifying if the account number is entered correctly...");
        	// Verify if the value is actually entered (optional, for additional validation)
            if (accountnumber.getAttribute("value").equals(AccoutNumber)) {
            	Log.info("Account number entered successfully: " + AccoutNumber);
                System.out.println("Account number entered successfully.");
            } else {
            	Log.warn("Failed to enter the account number correctly.");
                System.out.println("Not able to enter the account number.");
        }
             
        } catch (Exception e) {
        	Log.error("Failed to execute stored procedure: " + e.getMessage());
            System.err.println("Failed to execute stored procedure: " + e.getMessage());
        }
    }
    
    public String getErrorMessageforinvalidAccountNumbernotassigned() {
    	Log.info("Starting to retrieve the error message for invalid account number...");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    	Log.info("Waiting for the error message element to become visible...");
    	WebElement errormsg = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.errorMessage7));
    	Log.info("Error message element is visible. Retrieving the message...");
    	 String errorMessage = errormsg.getText();
    	    
    	    // Log the retrieved error message
    	    Log.info("Retrieved error message: " + errorMessage);
    	    
    	    // Return the error message
    	    return errorMessage;
    }
}
