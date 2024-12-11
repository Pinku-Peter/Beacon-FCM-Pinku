package com.testautomation.pages;

import java.time.Duration;
import java.util.List;
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
	
	public UpdationofDispositionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        Log.info("UpdationofDispositionPage initialized.");
    }
	
	// Navigate to the Disposition Updation screen
    public void navigateToUpdationOfDisposition() {
		WebElement disposition = driver.findElement(UpdationofDispositionRepo.dispositionMenu);
		Log.info("Clicking on the Disposition.");
		disposition.click();
		Log.info("Successfully clicked on the Disposition.");
		WebElement UpdationofDisposition = driver.findElement(UpdationofDispositionRepo.updationOfDispositionMenu);
		Log.info("Clicking on the Updation of Disposition.");
		UpdationofDisposition.click();
		Log.info("Successfully clicked on the Updation of Disposition.");
    }
    
 // Interact with Account Number Field
    public void enterAccountNumber(String accountNumber) {
    	WebElement accountnumber = driver.findElement(UpdationofDispositionRepo.accountNumberField);
    	accountnumber.clear();
    	accountnumber.sendKeys(accountNumber);
    	// Verify if the value is actually entered (optional, for additional validation)
        if (accountnumber.getAttribute("value").equals(accountNumber)) {
            System.out.println("Account number entered successfully.");
            Log.info("Account number entered successfully.");
        } else {
            System.out.println("Not able to enter the account number.");
            Log.info("Should not possible to type Alphabets and special characters in account number field.");
    }
  }
    
 // Interact with Account Number Field
    public void enterAccountNumberwithmore25characters(String accountNumber) {
    	WebElement accountnumber = driver.findElement(UpdationofDispositionRepo.accountNumberField);
    	accountnumber.clear();
    	accountnumber.sendKeys(accountNumber);
    	// Verify if the value is actually entered (optional, for additional validation)
        if (accountnumber.getAttribute("value").equals(accountNumber)) {
            System.out.println("Account number entered successfully.");
            Log.info("Account number entered successfully.");
        } else {
            System.out.println("Not able to enter the account number.");
            Log.info("Entry is restricted to 25 characters.");
    }
  }
    
 // Interact with Account Number Field
    public void enterInvalidAccountNumber(String accountNumber) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement accountnumber = driver.findElement(UpdationofDispositionRepo.accountNumberField);
    	accountnumber.clear();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	accountnumber.sendKeys(accountNumber);
    	// Verify if the value is actually entered (optional, for additional validation)
        if (accountnumber.getAttribute("value").equals(accountNumber)) {
            System.out.println("Account number entered successfully.");
            Log.info("Account number entered successfully.");
        } else {
            System.out.println("Not able to enter the account number.");
    }
  }
    
    public void withoutAccountNumber() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement accountnumber = driver.findElement(UpdationofDispositionRepo.accountNumberField);
    	accountnumber.clear();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
  }
    
 // Click Search
    public void clickSearchButton() {
    	WebElement searchButton = driver.findElement(UpdationofDispositionRepo.searchButton);
        searchButton.click();
    }
    
    // Get Error Message Text
    public String getErrorMessage() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.errorMessage));
        return msg.getText();
    }

 // Interact with Account Number Field
    public void entervalidAccountNumber() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement userid = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.loginuserid));
    	String procedureCall = "{CALL SPGETBRANCHALLOCATEDACCOUNTS(?, ?)}";
        String userId = userid.getText(); // Input parameter
    	WebElement accountnumber = driver.findElement(UpdationofDispositionRepo.accountNumberField);
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        try {
            List<String> AcNo = DBUtils.callStoredProcedureWithRefCursor(procedureCall, userId);
            String AccoutNumber = String.join(", ", AcNo);
            System.out.println("User Branch Account Number: " + AccoutNumber);
            accountnumber.sendKeys(AccoutNumber);
        	// Verify if the value is actually entered (optional, for additional validation)
            if (accountnumber.getAttribute("value").equals(AccoutNumber)) {
                System.out.println("Account number entered successfully.");
                Log.info("Account number entered successfully.");
            } else {
                System.out.println("Not able to enter the account number.");
        }
            
        } catch (Exception e) {
            System.err.println("Failed to execute stored procedure: " + e.getMessage());
        }
  }
    
    public void clickSaveButton() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement savebutton = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.savebutton));
    	savebutton.click();
    }
    
    public String getErrorMessagewithoutvalue() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement errormessage = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.errorMessage2));
        return errormessage.getText();
    }
    
}
