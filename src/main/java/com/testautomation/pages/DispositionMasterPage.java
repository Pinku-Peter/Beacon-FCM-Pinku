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

import com.BasePackage.Common;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Utility.Log;

public class DispositionMasterPage {
	
	private WebDriver driver;
	
	// Constructor
	public DispositionMasterPage(WebDriver driver) {
		Log.info("Starting DispositionMasterPage initialization.");
        this.driver = driver;
        Log.info("WebDriver assigned to DispositionMasterPage.");
        Log.info("Initializing WebElements using PageFactory.");
        PageFactory.initElements(driver, this); // Initialize WebElements
        Log.info("WebElements initialization completed successfully.");
        Log.info("DispositionMasterPage initialization completed.");
    }
	
	public WebElement getDispositionMainMenu() {
		Log.info("Attempting to locate the 'Disposition Main Menu' WebElement.");
		WebElement mainmenu = driver.findElement(DispositionMasterPageRepo.dispositionMainMenu);
		Log.info("'Disposition Main Menu' WebElement located successfully.");
	    return mainmenu;
	}

	public void clickOnDispositionMainMenu() throws InterruptedException {
		Log.info("Attempting to locate the 'Disposition Main Menu' WebElement.");
		WebElement mainmenu = driver.findElement(DispositionMasterPageRepo.dispositionMainMenu);
		Log.info("'Disposition Main Menu' WebElement located successfully.");
		Log.info("Attempting to click on the 'Disposition Main Menu'.");
		mainmenu.click();
		Log.info("Successfully clicked on the 'Disposition Main Menu'.");
		Log.info("Waiting for 10 seconds after clicking on the 'Disposition Main Menu'.");
		Thread.sleep(10000);
		Log.info("Wait of 10 seconds completed.");
	}

	public void clickOnDispositionMasterSubMenu() throws InterruptedException {
		Log.info("Attempting to locate the 'Disposition Master SubMenu' WebElement.");
		WebElement submenu = driver.findElement(DispositionMasterPageRepo.dispositionMasterSubMenu);
		Log.info("'Disposition Master SubMenu' WebElement located successfully.");
		Log.info("Attempting to click on the 'Disposition Master SubMenu'.");
		submenu.click();
		Log.info("Successfully clicked on the 'Disposition Master SubMenu'.");
		Log.info("Waiting for 30 seconds after clicking on the 'Disposition Master SubMenu'.");
		Thread.sleep(30000);
		Log.info("Wait of 30 seconds completed.");
	}

	//Method to verify if the Disposition Master page is loaded
	public boolean isDispositionMasterPageDisplayed() {
		Log.info("Attempting to locate the 'Disposition Master Page Header' WebElement.");
		 try {
		WebElement masterheader = driver.findElement(DispositionMasterPageRepo.dispositionMasterHeader);
		Log.info("'Disposition Master Page Header' WebElement located successfully.");
		Log.info("Checking if the 'Disposition Master Page Header' is displayed.");
        boolean isDisplayed = masterheader.isDisplayed();
        if (isDisplayed) {
            Log.info("'Disposition Master Page Header' is displayed on the page.");
        } else {
            Log.warn("'Disposition Master Page Header' is not displayed on the page.");
        }
        return isDisplayed; // Return the visibility status
        
    } catch (Exception e) {
        Log.error("Failed to locate or check the 'Disposition Master Page Header'. Exception: " + e.getMessage());
        return false; // Return false if an exception occurs
    }
	}

	public boolean isDispositionDisplayed() {
		Log.info("Attempting to locate the 'Disposition Main Menu' WebElement.");
		try {
		WebElement disposition = driver.findElement(DispositionMasterPageRepo.dispositionMainMenu);
		Log.info("'Disposition Main Menu' WebElement located successfully.");
		Log.info("Checking if the 'Disposition Main Menu' is displayed."); // Log before checking visibility
        boolean isDisplayed = disposition.isDisplayed();
        if (isDisplayed) {
            Log.info("'Disposition Main Menu' is displayed on the page.");
        } else {
            Log.warn("'Disposition Main Menu' exists but is not displayed on the page.");
        }
        return isDisplayed;
        
    } catch (Exception e) {
        Log.error("Failed to locate or check the 'Disposition Main Menu'. Exception: " + e.getMessage());
        return false; // Return false if any error occurs
    }
	}

	public boolean isSubDispositionDisplayed() {
		Log.info("Attempting to locate the 'Sub Disposition' WebElement.");
		try {
		WebElement subdisposition = driver.findElement(DispositionMasterPageRepo.Sub_Disposition);
		Log.info("'Sub Disposition' WebElement located successfully.");
		Log.info("Checking if the 'Sub Disposition' is displayed."); // Log before checking visibility
        boolean isDisplayed = subdisposition.isDisplayed();
        
        if (isDisplayed) {
            Log.info("'Sub Disposition' is displayed on the page."); // Log if element is displayed
        } else {
            Log.warn("'Sub Disposition' exists but is not displayed on the page."); // Log if not displayed
        }
        return isDisplayed; // Return the visibility status
        
    } catch (Exception e) {
        Log.error("Failed to locate or check the 'Sub Disposition'. Exception: " + e.getMessage());
        return false; // Return false if an exception occurs
    }
	}
	public int getActiveDispositionsCount() throws InterruptedException {
		Log.info("Initiating retrieval of active dispositions count.");
		 try {
		// Replace the locator below with the actual locator for active disposition rows
			 Log.info("Waiting for 30 seconds before locating active disposition rows."); // Log before wait
		        Thread.sleep(30000);
		        Log.info("Wait completed. Proceeding to locate active disposition rows.");

		// Find all active disposition rows
		        Log.info("Locating all active disposition rows using locator: 'subDispositionList'.");
		        int count = driver.findElements(DispositionMasterPageRepo.subDispositionList).size();
		        
		        Log.info("Successfully located active disposition rows. Count: " + count);
		        return count; // Return the count of elements found
		    } catch (Exception e) {
		        Log.error("An error occurred while retrieving the active dispositions count. Exception: " + e.getMessage());
		        return 0; // Return 0 if an exception occurs
		    }
	}

	//Method to select Action Owner from dropdown
	public void selectActionOwnerOptions() {
		Log.info("Starting the process to select Action Owner options.");
		try {
			Log.info("Attempting to locate the 'Action Owner' dropdown.");
		WebElement actionownerdropdown = driver.findElement(DispositionMasterPageRepo.actionOwnerDropdown);
		Log.info("Successfully located the 'Action Owner' dropdown. Clicking to open it.");
		actionownerdropdown.click();
		Log.info("'Action Owner' dropdown opened successfully.");
		 // Locate and select 'Call Centre' option
        Log.info("Attempting to locate the 'Call Centre' option.");
        WebElement callCentreOption = driver.findElement(DispositionMasterPageRepo.callCentreOption);
        Log.info("Successfully located the 'Call Centre' option. Clicking to select it.");
        callCentreOption.click();
        Log.info("'Call Centre' option selected successfully.");

        // Locate and select 'Collection Agency' option
        Log.info("Attempting to locate the 'Collection Agency' option.");
        WebElement collectionAgencyOption = driver.findElement(DispositionMasterPageRepo.collectionAgencyOption);
        Log.info("Successfully located the 'Collection Agency' option. Clicking to select it.");
        collectionAgencyOption.click();
        Log.info("'Collection Agency' option selected successfully.");

        // Locate and select 'Internal User' option
        Log.info("Attempting to locate the 'Internal User' option.");
        WebElement internalUserOption = driver.findElement(DispositionMasterPageRepo.internalUserOption);
        Log.info("Successfully located the 'Internal User' option. Clicking to select it.");
        internalUserOption.click();
        Log.info("'Internal User' option selected successfully.");

        Log.info("Action Owner options have been selected successfully.");
    } catch (Exception e) {
        Log.error("An error occurred while selecting Action Owner options. Exception: " + e.getMessage());
        throw e; // Re-throw the exception after logging it
    }
	}

	public boolean isIsActiveCheckboxChecked() {
		Log.info("Starting the process to check if the 'Is Active' checkbox is selected.");
	    
	    try {
	        // Locate the 'Is Active' checkbox
	        Log.info("Attempting to locate the 'Is Active' checkbox.");
	        WebElement activeCheckbox = driver.findElement(DispositionMasterPageRepo.isActiveCheckbox);
	        Log.info("Successfully located the 'Is Active' checkbox.");
	        
	        // Check if the checkbox is selected
	        Log.info("Checking if the 'Is Active' checkbox is selected.");
	        boolean isChecked = activeCheckbox.isSelected();
	        
	        if (isChecked) {
	            Log.info("The 'Is Active' checkbox is checked.");
	        } else {
	            Log.info("The 'Is Active' checkbox is NOT checked.");
	        }
	        
	        return isChecked; // Return the checkbox selection state
	    } catch (Exception e) {
	        Log.error("An error occurred while checking the 'Is Active' checkbox. Exception: " + e.getMessage());
	        throw e; // Re-throw exception after logging
	    }
	}

	// Method to verify action column options
	public boolean verifyActionOptions() {
		Log.info("Starting the verification of action options.");
	    
	    try {
	        // Locate and click the action column button
	        Log.info("Attempting to locate and click the 'Action Column' button.");
	        WebElement actionColumnButton = driver.findElement(DispositionMasterPageRepo.actionColumnButton);
	        actionColumnButton.click();
	        Log.info("Successfully clicked on the 'Action Column' button.");
	        
	        // Wait for the Edit and Activate/Deactivate options to appear
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        Log.info("Waiting for the 'Edit' and 'Activate/Deactivate' options to appear.");
	        
	        WebElement editElement = wait.until(ExpectedConditions.presenceOfElementLocated(DispositionMasterPageRepo.editOption));
	        Log.info("Successfully located the 'Edit' option.");
	        
	        WebElement activateDeactivateElement = wait.until(ExpectedConditions.presenceOfElementLocated(DispositionMasterPageRepo.activateDeactivateOption));
	        Log.info("Successfully located the 'Activate/Deactivate' option.");
	        
	        // Verify if both options are displayed
	        boolean result = editElement.isDisplayed() && activateDeactivateElement.isDisplayed();
	        
	        if (result) {
	            Log.info("Both 'Edit' and 'Activate/Deactivate' options are displayed.");
	        } else {
	            Log.info("Either 'Edit' or 'Activate/Deactivate' options are not displayed.");
	        }
	        
	        return result;
	    } catch (Exception e) {
	        Log.error("An error occurred while verifying the action options. Exception: " + e.getMessage());
	        return false;
	    }
	}

	public boolean areAllStatusIconsGreenTicks() {
		
		Log.info("Starting the verification of status icons to check if they are all green ticks.");
	    
	    // Locate the list of status icons
	    Log.info("Attempting to locate the status icons list.");
	    List<WebElement> statusIcons = driver.findElements(DispositionMasterPageRepo.statusIconslist);
	    Log.info("Successfully located the status icons list. Total icons found: " + statusIcons.size());
	    
	    for (WebElement icon : statusIcons) {
	        Log.info("Checking the status of the icon with style: " + icon.getAttribute("style"));
	        
	        // Check if the icon contains "color: green"
	        if (!icon.getAttribute("style").contains("color: green")) {
	            Log.error("Icon is not a green tick. Icon style: " + icon.getAttribute("style"));
	            return false; // Return false immediately if any icon is not a green tick
	        } else {
	            Log.info("Icon is a green tick.");
	        }
	    }
	    
	    Log.info("All icons are green ticks.");
	    return true;
	}
	
	public boolean isPaginationCorrect() {
		Log.info("Starting the verification of pagination buttons.");
	    
	    try {
	        // Locate the pagination container
	        Log.info("Attempting to locate the pagination element.");
	        WebElement pagination = driver.findElement(DispositionMasterPageRepo.pagination);
	        Log.info("Successfully located the pagination element.");
	        
	        // Locate the previous, page1, next, and double arrow buttons
	        Log.info("Attempting to locate the 'Previous' button.");
	        WebElement previousButton = pagination.findElement(DispositionMasterPageRepo.nextButton);
	        Log.info("Successfully located the 'Previous' button.");
	        
	        Log.info("Attempting to locate the 'Page 1' button.");
	        WebElement page1Button = pagination.findElement(DispositionMasterPageRepo.page1Button);
	        Log.info("Successfully located the 'Page 1' button.");
	        
	        Log.info("Attempting to locate the 'Next' button.");
	        WebElement nextButton = pagination.findElement(DispositionMasterPageRepo.paginationNextButton);
	        Log.info("Successfully located the 'Next' button.");
	        
	        Log.info("Attempting to locate the 'Double Arrow' (>>) button.");
	        WebElement doubleArrowButton = pagination.findElement(DispositionMasterPageRepo.lastPageButton);
	        Log.info("Successfully located the 'Double Arrow' (>>) button.");

	        // Check if the Previous button is disabled
	        Log.info("Checking if the 'Previous' button is disabled.");
	        String classAttribute = previousButton.getAttribute("class");
	        boolean containsClassName = classAttribute.contains("page-item disabled");
	        Assert.assertTrue(containsClassName, "Previous button is not disabled.");
	        Log.info("The 'Previous' button is disabled as expected.");

	        // Verify Page 1 is selected
	        Log.info("Verifying if 'Page 1' button is selected.");
	        Assert.assertTrue(page1Button.isDisplayed(), "Page 1 button is not selected.");
	        Log.info("The 'Page 1' button is displayed and selected.");
	        
	        // Verify Next button is enabled
	        Log.info("Verifying if 'Next' button is enabled.");
	        Assert.assertTrue(nextButton.isEnabled(), "Next button is not enabled.");
	        Log.info("The 'Next' button is enabled as expected.");
	        
	        // Verify Double Arrow (>>) button is enabled
	        Log.info("Verifying if 'Double Arrow' (>>) button is enabled.");
	        Assert.assertTrue(doubleArrowButton.isEnabled(), "Double Arrow button is not enabled.");
	        Log.info("The 'Double Arrow' (>>) button is enabled as expected.");
	        
	        // Final check on pagination text
	        Log.info("Checking if the pagination contains the expected text.");
	        boolean result = pagination.getText().contains("Previous") 
	                         && pagination.getText().contains("1") 
	                         && !pagination.getText().contains("<<");
	                         
	        if (result) {
	            Log.info("Pagination contains the expected text.");
	        } else {
	            Log.info("Pagination does not contain the expected text.");
	        }
	        
	        return result;
	    } catch (Exception e) {
	        Log.error("An error occurred during pagination verification. Exception: " + e.getMessage());
	        return false;
	    }
        
    }
	
	public void clickNextPagination() throws InterruptedException {
		Log.info("Starting the verification for 'Next' pagination.");
	    
	    // Create WebDriverWait instance
	    Log.info("Creating WebDriverWait instance to wait for elements.");
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	    
	    try {
	        // Locate the pagination next button
	        Log.info("Attempting to locate the 'Next' pagination button.");
	        WebElement paginationnextbutton = driver.findElement(DispositionMasterPageRepo.paginationNextButton);
	        Log.info("Successfully located the 'Next' pagination button.");
	        
	        // Locate page 2 button
	        Log.info("Attempting to locate the 'Page 2' button.");
	        WebElement page2Button = paginationnextbutton.findElement(DispositionMasterPageRepo.page2);
	        Log.info("Successfully located the 'Page 2' button.");
	        
	        // Locate the previous and next buttons
	        Log.info("Attempting to locate the 'Previous' button.");
	        WebElement previousButton = paginationnextbutton.findElement(DispositionMasterPageRepo.previousButton);
	        Log.info("Successfully located the 'Previous' button.");
	        
	        Log.info("Attempting to locate the 'Next' button.");
	        WebElement nextButton = paginationnextbutton.findElement(DispositionMasterPageRepo.paginationNextButton);
	        Log.info("Successfully located the 'Next' button.");
	        
	        // Locate the double arrow buttons
	        Log.info("Attempting to locate the 'Next Double Arrow' button.");
	        WebElement nextdoubleArrowButton = paginationnextbutton.findElement(DispositionMasterPageRepo.lastPageButton);
	        Log.info("Successfully located the 'Next Double Arrow' button.");
	        
	        Log.info("Attempting to locate the 'Previous Double Arrow' button.");
	        WebElement previousdoubleArrowButton = paginationnextbutton.findElement(DispositionMasterPageRepo.firstpage);
	        Log.info("Successfully located the 'Previous Double Arrow' button.");
	        
	        // Scroll the pagination element into view
	        Log.info("Scrolling the 'Next' pagination button into view.");
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView(true);", paginationnextbutton); 
	        Thread.sleep(500);  // Add a short wait for smooth scrolling
	        Log.info("Scrolled into view successfully.");

	        // Click the next pagination button
	        Log.info("Clicking the 'Next' pagination button.");
	        paginationnextbutton.click();
	        
	        // Wait for the spinner to disappear
	        Log.info("Waiting for the spinner to disappear.");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        Log.info("Spinner is no longer visible.");

	        // Verify Page 2 is selected
	        Log.info("Verifying if the 'Page 2' button is selected.");
	        Assert.assertTrue(page2Button.isDisplayed(), "Page 2 button is not selected.");
	        Log.info("Page 2 button is selected as expected.");
	        
	        // Verify Previous button is enabled
	        Log.info("Verifying if the 'Previous' button is enabled.");
	        Assert.assertTrue(previousButton.isEnabled(), "Previous button is not enabled.");
	        Log.info("The 'Previous' button is enabled as expected.");
	        
	        // Verify Next button is enabled
	        Log.info("Verifying if the 'Next' button is enabled.");
	        Assert.assertTrue(nextButton.isEnabled(), "Next button is not enabled.");
	        Log.info("The 'Next' button is enabled as expected.");
	        
	        // Verify Double Arrow (>>) button is enabled
	        Log.info("Verifying if the 'Next Double Arrow' (>>) button is enabled.");
	        Assert.assertTrue(nextdoubleArrowButton.isEnabled(), "Next Double Arrow button is not enabled.");
	        Log.info("The 'Next Double Arrow' (>>) button is enabled as expected.");
	        
	        // Verify Double Arrow (<<) button is enabled
	        Log.info("Verifying if the 'Previous Double Arrow' (<<) button is enabled.");
	        Assert.assertTrue(previousdoubleArrowButton.isEnabled(), "Previous Double Arrow button is not enabled.");
	        Log.info("The 'Previous Double Arrow' (<<) button is enabled as expected.");
	        
	    } catch (Exception e) {
	        Log.error("An error occurred during pagination verification. Exception: " + e.getMessage());
	    }
	    
	    Log.info("Completed the verification for 'Next' pagination.");
	}

	public void clickPreviousPagination() {
		Log.info("Starting the verification for 'Previous' pagination.");

	    // Create WebDriverWait instance
	    Log.info("Creating WebDriverWait instance to wait for elements.");
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	    
	    try {
	        // Locate the previous button
	        Log.info("Attempting to locate the 'Previous' pagination button.");
	        WebElement previousbutton = driver.findElement(DispositionMasterPageRepo.previousButton);
	        Log.info("Successfully located the 'Previous' pagination button.");
	        
	        // Locate the next pagination button
	        Log.info("Attempting to locate the 'Next' pagination button.");
	        WebElement paginationNextButton = driver.findElement(DispositionMasterPageRepo.paginationNextButton);
	        Log.info("Successfully located the 'Next' pagination button.");
	        
	        // Locate page 1 button
	        Log.info("Attempting to locate the 'Page 1' button.");
	        WebElement page1 = previousbutton.findElement(DispositionMasterPageRepo.page1Button);
	        Log.info("Successfully located the 'Page 1' button.");
	        
	        // Locate the next button and double arrow buttons
	        Log.info("Attempting to locate the 'Next' button.");
	        WebElement previousBut = previousbutton.findElement(DispositionMasterPageRepo.nextButton);
	        Log.info("Successfully located the 'Next' button.");
	        
	        Log.info("Attempting to locate the 'Next Double Arrow' button.");
	        WebElement nextButton = previousbutton.findElement(DispositionMasterPageRepo.paginationNextButton);
	        Log.info("Successfully located the 'Next Double Arrow' button.");
	        
	        Log.info("Attempting to locate the 'Previous Double Arrow' button.");
	        WebElement nextdoubleArrowButton = previousbutton.findElement(DispositionMasterPageRepo.lastPageButton);
	        Log.info("Successfully located the 'Previous Double Arrow' button.");
	        
	        // Scroll the pagination element into view
	        Log.info("Scrolling the 'Previous' pagination button into view.");
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView(true);", paginationNextButton);
	        Log.info("Scrolled into view successfully.");

	        // Click the previous pagination button
	        Log.info("Clicking the 'Previous' pagination button.");
	        previousbutton.click();
	        
	        // Wait for the spinner to disappear
	        Log.info("Waiting for the spinner to disappear.");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        Log.info("Spinner is no longer visible.");

	        // Verify Page 1 is selected
	        Log.info("Verifying if the 'Page 1' button is selected.");
	        Assert.assertTrue(page1.isDisplayed(), "Page 1 is not selected.");
	        Log.info("Page 1 button is selected as expected.");
	        
	        // Verify Previous button is disabled
	        Log.info("Verifying if the 'Previous' button is disabled.");
	        String classAttribute = previousBut.getAttribute("class");
	        boolean containsClassName = classAttribute.contains("page-item disabled ");
	        Assert.assertTrue(containsClassName, "Previous button should be disabled.");
	        Log.info("The 'Previous' button is disabled as expected.");
	        
	        // Verify Next button is enabled
	        Log.info("Verifying if the 'Next' button is enabled.");
	        Assert.assertTrue(nextButton.isEnabled(), "Next button is not enabled.");
	        Log.info("The 'Next' button is enabled as expected.");
	        
	        // Verify Double Arrow (>>) button is enabled
	        Log.info("Verifying if the 'Next Double Arrow' button is enabled.");
	        Assert.assertTrue(nextdoubleArrowButton.isEnabled(), "Next Double Arrow button is not enabled.");
	        Log.info("The 'Next Double Arrow' button is enabled as expected.");
	        
	        // Verify Double Arrow (<<) button is not visible
	        Log.info("Verifying if the 'Previous Double Arrow' button is not visible.");
	        List<WebElement> previousdoubleArrowButtons = driver.findElements(DispositionMasterPageRepo.previousDoubleArrowButtons);
	        if (previousdoubleArrowButtons.isEmpty()) {
	            Log.info("The '<<' button is not present in the DOM as expected.");
	        } else {
	            Assert.assertFalse(previousdoubleArrowButtons.get(0).isDisplayed(), "'<<' button is visible, but it should not be.");
	            Log.info("The '<<' button is not visible as expected.");
	        }
	        
	    } catch (Exception e) {
	        Log.error("An error occurred during pagination verification. Exception: " + e.getMessage());
	    }

	    Log.info("Completed the verification for 'Previous' pagination.");
	}
	
	public void clickLastPageButton() {
		Log.info("Starting the verification for 'Last Page' button.");

	    // Create WebDriverWait instance
	    Log.info("Creating WebDriverWait instance to wait for elements.");
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	    
	    try {
	        // Locate the last page button and click it
	        Log.info("Attempting to locate and click the 'Last Page' button.");
	        WebElement lastPageButton = driver.findElement(DispositionMasterPageRepo.lastPageButton);
	        lastPageButton.click();
	        Log.info("Successfully clicked the 'Last Page' button.");
	        
	        // Wait for the spinner to disappear
	        Log.info("Waiting for the spinner to disappear.");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        Log.info("Spinner is no longer visible.");

	        // Locate the next button
	        Log.info("Attempting to locate the 'Next' button.");
	        WebElement nextButton = driver.findElement(DispositionMasterPageRepo.nextButton);
	        Log.info("Successfully located the 'Next' button.");
	        
	        // Locate the previous page button
	        Log.info("Attempting to locate the 'Previous' page button.");
	        WebElement previouspage = driver.findElement(DispositionMasterPageRepo.previouspage);
	        Log.info("Successfully located the 'Previous' page button.");
	        
	        // Locate the first page button
	        Log.info("Attempting to locate the 'First' page button.");
	        WebElement firstpage = driver.findElement(DispositionMasterPageRepo.firstpage);
	        Log.info("Successfully located the 'First' page button.");

	        // Verify Next button is disabled (assertion)
	        Log.info("Verifying if the 'Next' button is disabled.");
	        String classAttribute = nextButton.getAttribute("class");
	        boolean containsClassName = classAttribute.contains("page-item disabled ");
	        Assert.assertTrue(containsClassName, "Next button should be disabled.");
	        Log.info("The 'Next' button is disabled as expected.");

	        // Verify if '>>' button (Last Page arrow) is not visible
	        Log.info("Verifying if the '>>' (Last Page arrow) button is not visible.");
	        List<WebElement> lastPageArrowButtons = driver.findElements(DispositionMasterPageRepo.lastPageArrowButton);
	        if (lastPageArrowButtons.isEmpty()) {
	            Log.info("The '>>' button is not present in the DOM as expected.");
	        } else {
	            Assert.assertFalse(lastPageArrowButtons.get(0).isDisplayed(), "'>>' button is visible, but it should not be.");
	            Log.info("The '>>' button is not visible as expected.");
	        }

	        // Verify Previous button is enabled
	        Log.info("Verifying if the 'Previous' button is enabled.");
	        Assert.assertTrue(previouspage.isEnabled(), "Previous button is not enabled.");
	        Log.info("The 'Previous' button is enabled as expected.");

	        // Verify << button (First page) is enabled
	        Log.info("Verifying if the '<<' (First page) button is enabled.");
	        Assert.assertTrue(firstpage.isEnabled(), "First page button is not enabled.");
	        Log.info("The 'First page' button is enabled as expected.");
	        
	    } catch (Exception e) {
	        Log.error("An error occurred during verification for the 'Last Page' button. Exception: " + e.getMessage());
	    }

	    Log.info("Completed the verification for 'Last Page' button.");
     
    }
	
	public void clickFirstPageButton() {
		Log.info("Starting the verification for 'First Page' button.");

	    // Create WebDriverWait instance
	    Log.info("Creating WebDriverWait instance to wait for elements.");
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

	    try {
	        // Locate the 'First Page' button and click it
	        Log.info("Attempting to locate and click the 'First Page' button.");
	        WebElement firstpage = driver.findElement(DispositionMasterPageRepo.firstpage);
	        firstpage.click();
	        Log.info("Successfully clicked the 'First Page' button.");

	        // Wait for the spinner to disappear
	        Log.info("Waiting for the spinner to disappear.");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        Log.info("Spinner is no longer visible.");

	        // Locate the Previous button and Next button
	        Log.info("Attempting to locate the 'Previous' button.");
	        WebElement previousButton = driver.findElement(DispositionMasterPageRepo.previousButton);
	        Log.info("Successfully located the 'Previous' button.");

	        Log.info("Attempting to locate the 'Next' button.");
	        WebElement nextButton = driver.findElement(DispositionMasterPageRepo.nextButton);
	        Log.info("Successfully located the 'Next' button.");

	        Log.info("Attempting to locate the 'Last Page' button.");
	        WebElement lastPageButton = driver.findElement(DispositionMasterPageRepo.lastPageButton);
	        Log.info("Successfully located the 'Last Page' button.");

	        // Verify Previous button is disabled
	        Log.info("Verifying if the 'Previous' button is disabled.");
	        WebElement previousBut = previousButton.findElement(DispositionMasterPageRepo.nextButton);
	        String classAttribute = previousBut.getAttribute("class");
	        boolean containsClassName = classAttribute.contains("page-item disabled ");
	        Assert.assertTrue(containsClassName, "Previous button should be disabled.");
	        Log.info("The 'Previous' button is disabled as expected.");

	        // Verify '<<' button (Previous Double Arrow) is not visible
	        Log.info("Verifying if the '<<' (Previous Double Arrow) button is not visible.");
	        List<WebElement> previousdoubleArrowButtons = driver.findElements(DispositionMasterPageRepo.previousdoubleArrowButtons);
	        if (previousdoubleArrowButtons.isEmpty()) {
	            Log.info("The '<<' button is not present in the DOM as expected.");
	        } else {
	            Assert.assertFalse(previousdoubleArrowButtons.get(0).isDisplayed(), "'<<' button is visible, but it should not be.");
	            Log.info("The '<<' button is not visible as expected.");
	        }

	        // Verify Next button is enabled
	        Log.info("Verifying if the 'Next' button is enabled.");
	        Assert.assertTrue(nextButton.isEnabled(), "Next button is not enabled.");
	        Log.info("The 'Next' button is enabled as expected.");

	        // Verify '>>' button (Last Page button) is enabled
	        Log.info("Verifying if the '>>' (Last Page) button is enabled.");
	        Assert.assertTrue(lastPageButton.isEnabled(), "'>>' button is not enabled.");
	        Log.info("The '>>' button is enabled as expected.");

	    } catch (Exception e) {
	        Log.error("An error occurred during verification for the 'First Page' button. Exception: " + e.getMessage());
	    }

	    Log.info("Completed the verification for 'First Page' button.");
    }
	
	public void clickAddDispositionButton() {
		Log.info("Starting the action to click the 'Add Disposition' button.");
	    
	    try {
	        // Locate the 'Add Disposition' button
	        Log.info("Locating the 'Add Disposition' button.");
	        WebElement addDispositionButton = driver.findElement(DispositionMasterPageRepo.addDispositionButton);
	        
	        // Scroll to the 'Add Disposition' button to bring it into view
	        Log.info("Scrolling to the 'Add Disposition' button to bring it into view.");
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", addDispositionButton);
	        Log.info("Successfully scrolled to the 'Add Disposition' button.");
	        Thread.sleep(500);
	        // Click the 'Add Disposition' button
	        Log.info("Attempting to click the 'Add Disposition' button.");
	        addDispositionButton.click();
	        Log.info("Successfully clicked the 'Add Disposition' button.");
	        
	    } catch (Exception e) {
	        Log.error("An error occurred while clicking the 'Add Disposition' button: " + e.getMessage());
	    }

	    Log.info("Completed the action to click the 'Add Disposition' button.");
    }
	
	public boolean isPopupDisplayed() {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		    Log.info("Starting check to verify if the popup is displayed.");
		    
		    try {
		        // Wait for each element to be visible and log each step
		        Log.info("Waiting for 'Action Owner' field to be visible.");
		        wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.actionOwnerField));
		        Log.info("'Action Owner' field is visible.");
		        
		        Log.info("Waiting for 'Name' field to be visible.");
		        wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.nameField));
		        Log.info("'Name' field is visible.");
		        
		        Log.info("Waiting for 'Asset Category' field to be visible.");
		        wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.assetCategoryField));
		        Log.info("'Asset Category' field is visible.");
		        
		        Log.info("Waiting for 'Submit' button to be visible.");
		        wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.submitButton));
		        Log.info("'Submit' button is visible.");
		        
		        Log.info("Waiting for 'Close' button to be visible.");
		        wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.closeButton));
		        Log.info("'Close' button is visible.");
		        
		        // Return true if all elements are visible
		        Log.info("All popup elements are displayed.");
		        return true;
		    } catch (Exception e) {
		        Log.error("Popup elements not displayed: " + e.getMessage());
		        return false;
		    } finally {
		        Log.info("Completed the check to verify if the popup is displayed.");
		    }
}
	
	// Method to close the Add Disposition popup
    public void closeAddDispositionPopup() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        Log.info("Starting the process to close the 'Add Disposition' popup.");
        
        try {
            Log.info("Waiting for 'Close' button to be visible.");
            WebElement close = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.closeButton));
            Log.info("'Close' button is visible.");
            
            Log.info("Clicking the 'Close' button.");
            close.click();
            Log.info("'Close' button clicked successfully.");
            
        } catch (Exception e) {
            Log.error("Error occurred while trying to close the popup: " + e.getMessage());
        } finally {
            Log.info("Completed the process to close the 'Add Disposition' popup.");
        }
    }

    // Method to verify that popup is closed
    public boolean isPopupClosed() {
    	Log.info("Starting the process to check if the popup is closed.");
        
        // Check if the close button is visible, or use other checks if necessary
        List<WebElement> closeButtonList = driver.findElements(DispositionMasterPageRepo.closeButton);
        
        // If the close button is not found in the DOM, it is assumed the popup is closed
        if (closeButtonList.isEmpty()) {
            Log.info("Popup is closed, 'Close' button not found in the DOM.");
            return true;
        }
        
        // If the close button is found, check if it's visible
        WebElement closeButton = closeButtonList.get(0);
        boolean isPopupClosed = !closeButton.isDisplayed();
        
        if (isPopupClosed) {
            Log.info("Popup is closed, 'Close' button is not displayed.");
        } else {
            Log.info("Popup is still open, 'Close' button is displayed.");
        }
        
        return isPopupClosed;
    }
    
 // Method to select action owner
    public void selectActionOwner(String actionOwner) {
    	Log.info("Starting the process to select an action owner.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Wait for the spinner to disappear
        Log.info("Waiting for the spinner to disappear.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Spinner disappeared.");

        // Click on the 'Add Disposition' button
        WebElement addDisposition = driver.findElement(DispositionMasterPageRepo.addDispositionButton);
        Log.info("Clicking on the 'Add Disposition' button.");
        addDisposition.click();
        Log.info("Clicked on the 'Add Disposition' button.");

        // Click on the Action Owner field
        WebElement actionOwnerElement = driver.findElement(DispositionMasterPageRepo.actionOwnerField);
        Log.info("Clicking on the Action Owner field.");
        actionOwnerElement.click();
        Log.info("Clicked on the Action Owner field.");

        // Wait for the specific Action Owner option to become visible and click it
        Log.info("Waiting for the Action Owner option: " + actionOwner);
        WebElement ccelement = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.actionOwnerOption(actionOwner)));
        Log.info("Action Owner option '" + actionOwner + "' is visible.");
        ccelement.click();
        Log.info("Selected Action Owner option: " + actionOwner);

    }

    // Method to enter disposition name
    public void enterName(String name) {
    	Log.info("Starting the process to enter a name.");

        // Find the name input field
        Log.info("Locating the name input field.");
        WebElement nameInputField = driver.findElement(DispositionMasterPageRepo.nameField);
        Log.info("Name input field located.");

        // Enter the name
        Log.info("Entering the name: " + name);
        nameInputField.sendKeys(name);
        Log.info("Entered the name: " + name);
    }

    // Method to select asset category
    public void selectAssetCategory(String category) {
    	Log.info("Starting the process to select an asset category.");

        // Locate and click the asset category field
        Log.info("Locating the asset category field.");
        WebElement assetCategoryElement = driver.findElement(DispositionMasterPageRepo.assetCategoryField);
        Log.info("Asset category field located.");
        assetCategoryElement.click();
        Log.info("Clicked on the asset category field.");

        // Wait for the category options to be visible
        Log.info("Waiting for the asset category options to be visible.");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement ccelement = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.assetCategoryOption(category)));
        Log.info("Asset category options are now visible.");

        // Select the specified asset category
        Log.info("Selecting the asset category: " + category);
        ccelement.click();
        Log.info("Successfully selected the asset category: " + category);
    }

    // Method to click the submit button
    public void clickSubmit() {
    	
    	Log.info("Attempting to click the submit button.");

        // Locate the submit button
        Log.info("Locating the submit button.");
        WebElement submitButton = driver.findElement(DispositionMasterPageRepo.submitButton);
        Log.info("Submit button located.");

        // Click the submit button
        Log.info("Clicking the submit button.");
        submitButton.click();

        // Log after the action is completed
        Log.info("Submit button clicked successfully.");
    	
    }
    
    public void clickOnActionOwnerDropdown() {
    	Log.info("Attempting to click on the 'Add Disposition' button.");

        // Wait and locate the 'Add Disposition' button
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement addDispositionButton = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.addDispositionButton));
        Log.info("'Add Disposition' button located successfully.");

        // Click the 'Add Disposition' button
        Log.info("Clicking on the 'Add Disposition' button.");
        addDispositionButton.click();

        // Wait until the spinner disappears
        Log.info("Waiting for the spinner to disappear.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Spinner disappeared.");

        // Locate the Action Owner dropdown
        Log.info("Locating the Action Owner field.");
        WebElement actionOwnerElement = driver.findElement(DispositionMasterPageRepo.actionOwnerField);
        Log.info("Action Owner field located.");

        // Click the Action Owner dropdown
        Log.info("Clicking on the Action Owner dropdown.");
        actionOwnerElement.click();

        // Log after the action is completed
        Log.info("Action Owner dropdown clicked successfully.");
    }
    
    public void selectActionOwners(String actionOwner) {
    	Log.info("Attempting to select Action Owner: " + actionOwner);

        // Initialize WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        Log.info("Initialized WebDriverWait for 60 seconds.");

        // Locate the Action Owner option by the provided actionOwner parameter
        Log.info("Locating the Action Owner option for: " + actionOwner);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.actionOwnerOption(actionOwner)));
        Log.info("Action Owner option for '" + actionOwner + "' located successfully.");

        // Click the Action Owner option
        Log.info("Clicking on the Action Owner option: " + actionOwner);
        option.click();
        Log.info("Action Owner option '" + actionOwner + "' selected successfully.");
    }
    
    public void clickAssetCategoryDropdown() {
    	
        Log.info("Attempting to click the Asset Category dropdown.");

        // Locate the Asset Category dropdown element
        Log.info("Locating the Asset Category field element.");
        WebElement assetCategoryElement = driver.findElement(DispositionMasterPageRepo.assetCategoryField);
        Log.info("Asset Category field element located successfully.");

        // Click on the Asset Category dropdown
        Log.info("Clicking the Asset Category dropdown.");
        assetCategoryElement.click();
        Log.info("Asset Category dropdown clicked successfully.");
        
    }
    
    public void AssetCategory(String category) {
    	Log.info("Attempting to select Asset Category: " + category);
        
        // Initialize WebDriverWait
        Log.info("Initializing WebDriverWait to wait for the visibility of the asset category option.");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        
        // Locate the asset category option element
        Log.info("Waiting for the visibility of the Asset Category option: " + category);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.assetCategoryOption(category)));
        Log.info("Asset Category option located successfully: " + category);
        
        // Click on the asset category option
        Log.info("Clicking the Asset Category option: " + category);
        option.click();
        Log.info("Asset Category option selected successfully: " + category);
        
        }
    
    public void clickActionOwnerDropdown() {
    	Log.info("Attempting to click the pop-up close button.");
        
        // Locate and click the close button
        WebElement popCloseButton = driver.findElement(DispositionMasterPageRepo.closeButton);
        Log.info("Clicking the pop-up close button.");
        popCloseButton.click();
        Log.info("Pop-up close button clicked successfully.");
        
        // Log before waiting for the 'Add Disposition' button
        Log.info("Waiting for the 'Add Disposition' button to be visible.");
        
        // Initialize WebDriverWait and wait for 'Add Disposition' button
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement addDispositionButton = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.addDispositionButton));
        Log.info("'Add Disposition' button is now visible.");
        
        // Click the 'Add Disposition' button
        Log.info("Clicking the 'Add Disposition' button.");
        addDispositionButton.click();
        Log.info("'Add Disposition' button clicked successfully.");
        
        // Log before waiting for the 'Action Owner' dropdown
        Log.info("Waiting for the 'Action Owner' dropdown to be visible.");
        
        // Wait for the 'Action Owner' dropdown to be visible and click it
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.actionOwnerField));
        Log.info("'Action Owner' dropdown is now visible.");
        
        // Click the 'Action Owner' dropdown
        Log.info("Clicking the 'Action Owner' dropdown.");
        dropdown.click();
        Log.info("'Action Owner' dropdown clicked successfully.");
    }
    
    public void selectAllActionOwners() {
    	Log.info("Waiting for the spinner to disappear before selecting all action owners.");
        
        // Initialize WebDriverWait and wait for the spinner to disappear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Spinner is no longer visible.");
        
        // Log before selecting the 'Select All' option
        Log.info("Attempting to click the 'Select All' action owners option.");
        
        // Find and click the 'Select All' option
        WebElement selectAllOption = driver.findElement(DispositionMasterPageRepo.selectAllOptionpath); 
        selectAllOption.click();
        
        // Log after the 'Select All' option is clicked
        Log.info("'Select All' action owners option clicked successfully.");
    	
    }
    
    public void deselectAllActionOwners() {
    	Log.info("Attempting to deselect all action owners.");

        // Find and click the 'Deselect All' option
        WebElement deselectAllOption = driver.findElement(DispositionMasterPageRepo.selectAllOptionpath);
        deselectAllOption.click();

        // Log after clicking the 'Deselect All' option
        Log.info("All action owners have been deselected.");
    }
 
 // Method to click on "Select All" option
    public void clickSelectAllAssetCategory() {
    	Log.info("Attempting to select all asset categories.");

        // Wait for spinner to disappear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement selectAllOption = driver.findElement(DispositionMasterPageRepo.selectAllOptionpath);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        
        // Log before clicking the 'Select All' option
        Log.info("Waiting for spinner to disappear and locating the 'Select All' option.");
        
        // Click the 'Select All' option
        selectAllOption.click();
        
        // Log after clicking the 'Select All' option
        Log.info("All asset categories have been selected.");
    	
    }
    
 // Method to uncheck the "Select All" checkbox
    public void uncheckSelectAllAssetCategory() {
    	Log.info("Attempting to uncheck/select all asset categories.");

        // Wait for spinner to disappear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement deselectAllOption = driver.findElement(DispositionMasterPageRepo.selectAllOptionpath);
        
        // Log before waiting for spinner to disappear
        Log.info("Waiting for spinner to disappear before locating the 'Deselect All' option.");
        
        // Wait until the spinner is gone
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        // Log before clicking the 'Deselect All' option
        Log.info("Deselecting the 'Select All' option.");
        
        // Click the 'Deselect All' option to uncheck
        deselectAllOption.click();
        
        // Log after clicking the 'Deselect All' option
        Log.info("Asset categories have been deselected.");
    }
    
 // Method to click on Add Disposition button
    public void clickAddDisposition() {
    	Log.info("Attempting to locate and click the 'Close' button.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        
        // Wait for the 'Close' button to be visible and log the event
        WebElement close = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.closeButton));
        
        // Log before clicking the 'Close' button
        Log.info("Clicking the 'Close' button.");
        close.click();
        
        // Log before locating the 'Add Disposition' button
        Log.info("Attempting to locate and click the 'Add Disposition' button.");
        
        // Locate the 'Add Disposition' button and log the event
        WebElement addDispositionButtonElement = driver.findElement(DispositionMasterPageRepo.addDispositionButton);
        
        // Log before clicking the 'Add Disposition' button
        Log.info("Clicking the 'Add Disposition' button.");
        addDispositionButtonElement.click();
        
        // Log after clicking the 'Add Disposition' button
        Log.info("'Add Disposition' button clicked successfully.");
    }
    
    public void clickSubmitButton() {
    	Log.info("Waiting for the spinner to disappear before proceeding.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        
        // Wait for the spinner to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        
        // Log after waiting for the spinner
        Log.info("Spinner is now invisible, proceeding to click the Submit button.");
        
        // Log before locating the 'Submit' button
        Log.info("Attempting to locate the 'Submit' button.");

        WebElement submitButtonElement = driver.findElement(DispositionMasterPageRepo.submitButton);

        // Log before clicking the 'Submit' button
        Log.info("Clicking the 'Submit' button.");
        submitButtonElement.click();
        
        // Log after clicking the 'Submit' button
        Log.info("'Submit' button clicked successfully.");
    }
    
 // Method to get error message for name field
    public String getNameErrorMessage() {
    	Log.info("Attempting to locate the 'Name' error message element.");
        
        WebElement nameErrorMessage = driver.findElement(DispositionMasterPageRepo.nameErrorMessage);
        
        // Log after the error message element is located
        Log.info("'Name' error message element located successfully.");
        
        // Log before retrieving the error message text
        Log.info("Retrieving the error message text.");
        
        String errorMessage = nameErrorMessage.getText();
        
        // Log after retrieving the error message text
        Log.info("Error message retrieved: " + errorMessage);
        
        return errorMessage;
        
    }
    
    // Method to verify if a disposition row is visible
    public boolean isDispositionVisible(String name) {
    	Log.info("Waiting for the spinner to become invisible.");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        
        // Log after spinner becomes invisible
        Log.info("Spinner is now invisible.");

        // Log before attempting to find the disposition by name
        Log.info("Attempting to locate the disposition with name: " + name);
        
        boolean isVisible = driver.findElement(DispositionMasterPageRepo.dispositionByName(name)).isDisplayed();
        
        // Log the result of visibility check
        if (isVisible) {
            Log.info("Disposition with name '" + name + "' is visible.");
        } else {
            Log.info("Disposition with name '" + name + "' is not visible.");
        }
        
        return isVisible;
    }

    // Method to get action owner text
    public String getActionOwnerText(String ActionOwner) {
    	
    	Log.info("Attempting to retrieve the action owner text for: " + ActionOwner);
        
        WebElement actionOwnerElement = driver.findElement(DispositionMasterPageRepo.actionOwnerText(ActionOwner));
        
        // Log after locating the element
        Log.info("Found the action owner element for: " + ActionOwner);
        
        String actionOwnerText = actionOwnerElement.getText();
        
        // Log after getting the text
        Log.info("Retrieved action owner text: " + actionOwnerText);
        
        return actionOwnerText;
    }

    // Method to verify if status is a green tick
    public boolean isStatusGreenTick() {
    	Log.info("Attempting to locate the status icon to check if it's displayed.");
        
        WebElement statusIcon = driver.findElement(DispositionMasterPageRepo.statusIcon);
        
        // Log after locating the status icon
        Log.info("Status icon found.");
        
        boolean isDisplayed = statusIcon.isDisplayed();
        
        // Log the result of the status icon visibility check
        Log.info("Status icon displayed: " + isDisplayed);
        
        return isDisplayed;
    }

    // Method to check the presence of the action button
    public boolean isActionButtonPresent() {
    	Log.info("Attempting to locate the action button in the action column.");
        
        WebElement actionButton = driver.findElement(DispositionMasterPageRepo.actionColumnButton);
        
        // Log after locating the action button
        Log.info("Action button located.");
        
        boolean isDisplayed = actionButton.isDisplayed();
        
        // Log the result of the action button visibility check
        Log.info("Action button displayed: " + isDisplayed);
        
        return isDisplayed;
    }
    
 // Method to get the error message text
    public String getErrorMessageText() {
    	Log.info("Waiting for the error message to be visible.");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        
        // Wait for the error message popup to be visible
        WebElement errorPopUp = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.errorMessage));
        
        // Log after the error message has been found
        Log.info("Error message popup is visible.");
        
        // Retrieve the text of the error message
        String errorMessage = errorPopUp.getText();
        
        // Log the retrieved error message
        Log.info("Error message retrieved: " + errorMessage);
        
        return errorMessage;
    }
    
 // Method to click the three-dot button
    public void clickThreeDotButton() {
    	Log.info("Waiting for the error message to disappear, if present.");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        
        // Log before waiting for the close button to be visible
        Log.info("Waiting for the close button to become visible.");
        WebElement actionColumnButton = driver.findElement(DispositionMasterPageRepo.actionColumnButton);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.errorMessage));
        
        // Log after close button becomes visible
        Log.info("Close button is now visible.");

        // Log before clicking the close button
        Log.info("Clicking the close button.");
        WebElement close = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.closeButton));
        close.click();
        
        // Log after clicking the close button
        Log.info("Close button clicked, waiting for the spinner to disappear.");
        
        // Wait for the spinner to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        
        // Log before clicking the action column button
        Log.info("Clicking the action column button.");
        actionColumnButton.click();
        
        // Log after clicking the action column button
        Log.info("Action column button clicked.");

    }

    // Method to click the edit button
    public void clickEditButton() {
    	Log.info("Waiting for the spinner to disappear before clicking the edit button.");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        
        // Log before locating the edit button
        Log.info("Locating the edit button.");
        WebElement editButton = driver.findElement(DispositionMasterPageRepo.editButton);
        
        // Log before clicking the edit button
        Log.info("Clicking the edit button.");
        editButton.click();
        
        // Log after clicking the edit button
        Log.info("Edit button clicked, now waiting for the spinner to disappear.");
        
        // Wait for the spinner to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        
        // Log after the spinner disappears
        Log.info("Spinner disappeared, edit button action completed.");

    }

    // Method to verify the popup elements are displayed
    public boolean isEditPopupDisplayed() {
    	Log.info("Checking if the Edit popup elements are displayed.");
        
        // Log before finding action owner dropdown
        Log.info("Locating Action Owner dropdown in the popup.");
        WebElement actionOwnerDropdowninpop = driver.findElement(DispositionMasterPageRepo.actionOwnerDropdowninpop);
        
        // Log after finding action owner dropdown
        Log.info("Action Owner dropdown located, checking if it's displayed.");
        boolean isActionOwnerDisplayed = actionOwnerDropdowninpop.isDisplayed();
        
        // Log before finding name field
        Log.info("Locating Name field in the popup.");
        WebElement nameFieldinpop = driver.findElement(DispositionMasterPageRepo.nameFieldinpop);
        
        // Log after finding name field
        Log.info("Name field located, checking if it's displayed.");
        boolean isNameFieldDisplayed = nameFieldinpop.isDisplayed();
        
        // Log before finding asset category dropdown
        Log.info("Locating Asset Category dropdown in the popup.");
        WebElement assetCategoryDropdowninpop = driver.findElement(DispositionMasterPageRepo.assetCategoryDropdowninpop);
        
        // Log after finding asset category dropdown
        Log.info("Asset Category dropdown located, checking if it's displayed.");
        boolean isAssetCategoryDisplayed = assetCategoryDropdowninpop.isDisplayed();
        
        // Log before finding update button
        Log.info("Locating Update button in the popup.");
        WebElement updateButton = driver.findElement(DispositionMasterPageRepo.updateButton);
        
        // Log after finding update button
        Log.info("Update button located, checking if it's displayed.");
        boolean isUpdateButtonDisplayed = updateButton.isDisplayed();
        
        // Log before returning result
        Log.info("Returning result for Edit popup visibility.");
        
        return isActionOwnerDisplayed && isNameFieldDisplayed && isAssetCategoryDisplayed && isUpdateButtonDisplayed;

    }
 
 // Method to click on the update button
    public void clickUpdateButton() {
    	Log.info("Locating the Update button.");
        
        // Locate the Update button
        WebElement updateButton = driver.findElement(DispositionMasterPageRepo.updateButton);
        
        // Log after locating the Update button
        Log.info("Update button located, performing click action.");
        
        // Perform click action on the Update button
        updateButton.click();
        
        // Log after clicking the Update button
        Log.info("Update button clicked successfully.");
    }
    
    // Method to verify success message
    public boolean isSuccessMessageDisplayed() {
    	Log.info("Waiting for the success message popup to be visible.");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        
        // Wait for the success message popup to be visible
        WebElement successPopupElement = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.successMessage));
        
        // Log after waiting for the success message popup to be visible
        Log.info("Success message popup is now visible.");
        
        // Log before checking if the success message is displayed
        Log.info("Checking if the success message is displayed and matches 'Saved Successfully'.");
        
        // Check if the success message is displayed and matches the expected text
        boolean isDisplayed = successPopupElement.isDisplayed() && successPopupElement.getText().equals("Saved Successfully");
        
        // Log the result of the check
        if (isDisplayed) {
            Log.info("Success message is displayed with the correct text.");
        } else {
            Log.info("Success message is either not displayed or does not have the correct text.");
        }
        
        // Return the result
        return isDisplayed;
    }
    
    public void selectAllActionOwnersdropdown() {
    	Log.info("Waiting for the spinner to disappear before interacting with the dropdown.");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        
        // Wait for the spinner to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Spinner is no longer visible.");
        
        // Log before interacting with the Action Owner dropdown
        Log.info("Waiting for Action Owner dropdown to be visible.");
        
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.actionOwnerDropdown));
        
        // Log after the Action Owner dropdown is visible
        Log.info("Action Owner dropdown is now visible, clicking on it.");
        
        dropdown.click();
        
        // Log before performing Fluent Wait for the Select All option
        Log.info("Performing Fluent Wait for the 'Select All' option.");
        
        Common.fluentWait("Action Owner Dropdown", DispositionMasterPageRepo.selectAllOptionpath);
        
        // Log before finding the 'Select All' option
        Log.info("Locating the 'Select All' option.");
        
        WebElement selectAllOption = driver.findElement(DispositionMasterPageRepo.selectAllOptionpath);
        
        // Log before waiting for the spinner to disappear again
        Log.info("Waiting for the spinner to disappear again before clicking the 'Select All' option.");
        
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        
        // Log before clicking the 'Select All' option
        Log.info("Clicking on the 'Select All' option.");
        
        selectAllOption.click();
        
        // Log after selecting all action owners
        Log.info("Successfully selected all Action Owners from the dropdown.");
        }
    
    public void enterDispositionName(String name) {
    	Log.info("Locating the 'Name' field element on the page.");
        
        // Find the name field element
        WebElement nameField = driver.findElement(DispositionMasterPageRepo.nameField);
        
        // Log after finding the name field
        Log.info("'Name' field element found, entering the name: " + name);
        
        // Enter the provided name into the 'Name' field
        nameField.sendKeys(name);
        
        // Log after entering the name
        Log.info("Name entered successfully: " + name);
    }
    
    public void selectAllAssetCategories() {
    	Log.info("Waiting for spinner to disappear before interacting with asset category field.");
        
        // Wait for the spinner to disappear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        // Log before finding and clicking the asset category field
        Log.info("Locating and clicking the 'Asset Category' field.");

        // Locate and click the asset category field
        WebElement assetField = driver.findElement(DispositionMasterPageRepo.assetCategoryField);
        assetField.click();

        // Log after clicking the asset category field
        Log.info("'Asset Category' field clicked, waiting for spinner to disappear.");

        // Wait for spinner to disappear after clicking the asset category field
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        // Log before locating the 'Select All' option
        Log.info("Locating 'Select All' option from asset category dropdown.");

        // Locate the 'Select All' option
        WebElement selectAllOption = driver.findElement(DispositionMasterPageRepo.selectAllOptionpath);

        // Log after finding the 'Select All' option
        Log.info("'Select All' option found, clicking to select all asset categories.");

        // Wait for spinner to disappear again before clicking
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        // Click on 'Select All' option
        selectAllOption.click();

        // Log after clicking the 'Select All' option
        Log.info("'Select All' option clicked. All asset categories selected.");
    	
        }
    public boolean SuccessMessageDisplayed() {
    	Log.info("Waiting for the success message popup to be visible.");

        // Wait for the success message popup to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement successpop = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.successMessage));
        
        // Log after the success message popup is visible
        Log.info("Success message popup is visible, checking if it contains the expected text.");

        // Return true if the success message is displayed and contains the correct text
        boolean isDisplayedAndCorrectText = successpop.isDisplayed() && successpop.getText().equals("Saved Successfully");

        // Log the result of the check
        if (isDisplayedAndCorrectText) {
            Log.info("Success message is displayed and contains the expected text: 'Saved Successfully'.");
        } else {
            Log.warn("Success message is either not displayed or does not contain the expected text.");
        }

        return isDisplayedAndCorrectText;
    }
    
    public void ThreeDotButton() {
    	Log.info("Attempting to locate the action column button.");

        // Locate the action column button
        WebElement actionColumnButton = driver.findElement(DispositionMasterPageRepo.actionColumnButton);

        // Log after the action column button is found
        Log.info("Action column button located successfully.");

        // Initialize WebDriverWait and log the action
        Log.info("Waiting for any spinner to disappear before clicking the button.");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Wait until the spinner is invisible
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        // Log before clicking the action column button
        Log.info("Clicking on the action column button.");

        // Click the action column button
        actionColumnButton.click();

        // Log after clicking the action column button
        Log.info("Action column button clicked successfully.");
    	
    }
    
 // Method to change the name field to a given value
    public void changeNameField(String name) {
    	Log.info("Attempting to clear the name field.");

        // Locate the name field element and clear it
        WebElement nameFieldElement = driver.findElement(DispositionMasterPageRepo.nameField);
        nameFieldElement.clear();

        // Log after clearing the name field
        Log.info("Name field cleared successfully.");

        // Log before sending keys (name)
        Log.info("Entering the new name: " + name);

        // Send new name to the name field
        nameFieldElement.sendKeys(name);

        // Log after entering the new name
        Log.info("New name entered successfully: " + name);

        // Log before clicking outside element
        Log.info("Attempting to click the outside element to move focus away from the name field.");

        // Locate and click the outside element (e.g., <body>)
        WebElement outsideElementWebElement = driver.findElement(DispositionMasterPageRepo.outsideElement);
        outsideElementWebElement.click();

        // Log after clicking the outside element
        Log.info("Focus moved away from the name field by clicking the outside element.");
    }
    
    public void UpdateButton() {
    	  Log.info("Attempting to click the update button.");

    	    // Locate the update button and click it
    	    WebElement updateButton = driver.findElement(DispositionMasterPageRepo.updateButton);
    	    updateButton.click();

    	    // Log after clicking the update button
    	    Log.info("Update button clicked successfully.");
    }
    
 // Method to get the error message text
    public String getErrorMessage() {
    	Log.info("Waiting for the error message pop-up to be visible.");

        // Wait for the error message pop-up to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement existingmsgpop = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.errorMessage));

        // Log after the error message is visible and retrieved
        Log.info("Error message pop-up is visible. Retrieving the text.");

        // Return the error message text
        return existingmsgpop.getText();
    }
    
    public void toUpdateclickThreeDotButton() {
    	 Log.info("Locating the action column button.");

    	    WebElement actionColumnButton = driver.findElement(DispositionMasterPageRepo.actionColumnButton);

    	    // Log after action column button is located
    	    Log.info("Action column button located successfully.");

    	    // Log before finding the edit button
    	    Log.info("Locating the edit button.");
    	    WebElement editButton = driver.findElement(DispositionMasterPageRepo.editButton);

    	    // Log after the edit button is located
    	    Log.info("Edit button located successfully.");

    	    // Log before starting to wait for the error message to disappear
    	    Log.info("Waiting for the error message to disappear.");

    	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	    wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.errorMessage));

    	    // Log after the error message is confirmed to be invisible
    	    Log.info("Error message is no longer visible.");

    	    // Log before locating and clicking the close button
    	    Log.info("Locating the close button.");
    	    WebElement close = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.closeButton));
    	    close.click();

    	    // Log after the close button is clicked
    	    Log.info("Close button clicked.");

    	    // Log before waiting for the spinner to disappear
    	    Log.info("Waiting for the spinner to disappear.");
    	    wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

    	    // Log before clicking the action column button
    	    Log.info("Clicking the action column button.");
    	    actionColumnButton.click();

    	    // Log after clicking the action column button
    	    Log.info("Action column button clicked.");

    	    // Log before clicking the edit button
    	    Log.info("Clicking the edit button.");
    	    editButton.click();

    	    // Log after clicking the edit button
    	    Log.info("Edit button clicked.");

    	    // Log before waiting for the spinner to disappear after clicking the edit button
    	    Log.info("Waiting for the spinner to disappear after clicking the edit button.");
    	    wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

    	    // Log after the spinner disappears
    	    Log.info("Spinner has disappeared after clicking the edit button.");
    }
    
 // Method to change Action Owner
    public void setActionOwner(String actionOwner) {
    	 Log.info("Initializing WebDriverWait for action owner field interaction.");
    	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	    
    	    // Log before clearing the value in the action owner field
    	    Log.info("Clicking the clear value icon to reset action owner.");
    	    WebElement clearValue = driver.findElement(DispositionMasterPageRepo.clearValueIcon);
    	    clearValue.click();
    	    Log.info("Clear value icon clicked.");

    	    // Log before clicking the action owner field
    	    Log.info("Clicking the action owner field.");
    	    WebElement actionOwnerElement = driver.findElement(DispositionMasterPageRepo.actionOwnerField);
    	    actionOwnerElement.click();
    	    Log.info("Action owner field clicked.");

    	    // Log before waiting for the action owner option to become visible
    	    Log.info("Waiting for the action owner option to become visible.");
    	    WebElement ccelement = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.actionOwnerOption4(actionOwner)));
    	    Log.info("Action owner option for '" + actionOwner + "' is now visible.");

    	    // Log after the action owner option is clicked
    	    Log.info("Clicking the action owner option.");
    	    ccelement.click();
    	    Log.info("Action owner option clicked.");

    	    // Log before clicking the outside element
    	    Log.info("Clicking outside element to close dropdown.");
    	    WebElement outsideElement = driver.findElement(DispositionMasterPageRepo.outsideElement); // Assuming the <body> tag is safe to click
    	    outsideElement.click();
    	    Log.info("Outside element clicked to close dropdown.");
    }

    // Method to change Name
    public void setName(String name) {
    	Log.info("Locating the name field to clear any existing value.");
        WebElement nameField = driver.findElement(DispositionMasterPageRepo.nameField);
        
        // Log before clearing the value in the name field
        Log.info("Clearing the existing value in the name field.");
        nameField.clear();
        Log.info("Name field cleared.");

        // Log before sending new keys (the name)
        Log.info("Entering the name: " + name + " into the name field.");
        nameField.sendKeys(name);
        Log.info("Name entered into the name field.");

        // Log before clicking outside element (to close or focus out of the field)
        Log.info("Clicking outside the name field to close or focus out.");
        WebElement outsideElement = driver.findElement(DispositionMasterPageRepo.outsideElement); // Assuming the <body> tag is safe to click
        outsideElement.click();
        Log.info("Outside element clicked to focus out of the name field.");
    }

    // Method to change Asset Category
    public void setAssetCategory(String assetCategory) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	 Log.info("Locating the clear value button to reset the field.");
    	 
    	    WebElement clearValueButton = driver.findElement(DispositionMasterPageRepo.clearvaluepath);
    	    
    	    // Log before clearing the value
    	    Log.info("Clearing the asset category field.");
    	    clearValueButton.click();
    	    Log.info("Asset category field cleared.");

    	    // Log before clicking the asset category field
    	    Log.info("Locating the asset category field to select a new category.");
    	    WebElement assetLabel = driver.findElement(DispositionMasterPageRepo.assetCategoryField);
    	    assetLabel.click();
    	    Log.info("Asset category field clicked.");

    	    // Log before selecting the specific asset category option
    	    Log.info("Locating and selecting the asset category option: " + assetCategory);
    	    WebElement ccelement = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.assetCategoryOption2(assetCategory)));
    	    ccelement.click();
    	    Log.info("Asset category option selected: " + assetCategory);

    	    // Log before clicking outside element to close or focus out of the field
    	    Log.info("Clicking outside the asset category field to focus out.");
    	    WebElement outsideElement = driver.findElement(DispositionMasterPageRepo.outsideElement); // Assuming the <body> tag is safe to click
    	    outsideElement.click();
    	    Log.info("Outside element clicked to focus out of the asset category field.");
    }

    // Method to verify success message
    public boolean isUpdateSuccessMessageDisplayed() {
    	Log.info("Waiting for the success message to be visible.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Log before fetching the success message element
        Log.info("Locating the success message element.");
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.successMessage));

        // Log after finding the success message
        Log.info("Success message element located.");

        // Log before returning the result
        Log.info("Checking if the success message is displayed.");
        boolean isDisplayed = successMessage.isDisplayed();
        
        // Log after returning the result
        Log.info("Success message is displayed: " + isDisplayed);

        return isDisplayed;
    }
    
 // Method to click on the three-dot action button
    public void clkThreeDotButton() {
    	Log.info("Locating the action column button.");

        WebElement actionColumnButton = driver.findElement(DispositionMasterPageRepo.actionColumnButton);

        // Log after finding the action column button
        Log.info("Action column button located.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Log before waiting for the spinner to disappear
        Log.info("Waiting for the spinner to disappear.");

        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        // Log after the spinner is gone
        Log.info("Spinner is now invisible.");

        // Log before clicking the action column button
        Log.info("Clicking the action column button.");

        actionColumnButton.click();

        // Log after clicking the button
        Log.info("Action column button clicked.");
    }

    // Method to click on the Activate/De-activate option
    public void clickActivateDeactivateOption() {
    	Log.info("Locating the activate/deactivate option.");

        WebElement activateDeactivateOption = driver.findElement(DispositionMasterPageRepo.activateDeactivateOption);

        // Log after locating the activate/deactivate option
        Log.info("Activate/deactivate option located.");

        // Log before clicking the activate/deactivate option
        Log.info("Clicking the activate/deactivate option.");

        activateDeactivateOption.click();

        // Log after clicking the activate/deactivate option
        Log.info("Activate/deactivate option clicked.");
    }
    
 // Method to select action owner as Call centre
    public void selActionOwner(String actionOwner) {
    	Log.info("Starting the action to select the action owner.");

        try {
            WebElement clearValueButton = driver.findElement(DispositionMasterPageRepo.clearvaluepath2);
            // Click the clear value button
            Log.info("Clicking the clear value button.");
            clearValueButton.click();
            Log.info("Clear value button clicked.");

            WebElement actionOwnerDropdown = driver.findElement(DispositionMasterPageRepo.actionownerdropdownpath);
            // Click the action owner dropdown
            Log.info("Clicking the action owner dropdown.");
            actionOwnerDropdown.click();
            Log.info("Action owner dropdown clicked.");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            WebElement ccelement = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.actionOwnerOption3(actionOwner)));
            // Click the action owner option
            Log.info("Selecting the action owner option: " + actionOwner);
            ccelement.click();
            Log.info("Action owner option selected.");

            WebElement outsideElement = driver.findElement(DispositionMasterPageRepo.outsideElementPath2);
            // Click outside to close the dropdown
            Log.info("Clicking outside the dropdown to close it.");
            outsideElement.click();
            Log.info("Dropdown closed.");

        } catch (Exception e) {
            Log.error("An error occurred while selecting the action owner: " + e.getMessage());
        }

        Log.info("Completed the action to select the action owner.");
    }
    
 // Method to untick Is active checkbox
    public void untickIsActiveCheckbox() {
    	Log.info("Locating the 'Is Active' checkbox to untick.");

        WebElement checkbox = driver.findElement(DispositionMasterPageRepo.isActiveCheckbox2);

        // Log after the checkbox is located
        Log.info("Located the 'Is Active' checkbox.");

        // Log before clicking the checkbox
        Log.info("Clicking the 'Is Active' checkbox to untick it.");

        checkbox.click();

        // Log after clicking the checkbox
        Log.info("The 'Is Active' checkbox has been unticked.");

        }
    
 // Method to click on the search button
    public void clickSearchButton() {
    	Log.info("Locating the 'Search' button.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement searchButton = driver.findElement(DispositionMasterPageRepo.searchButton);

        // Log after locating the search button
        Log.info("Located the 'Search' button.");

        // Log before clicking the search button
        Log.info("Clicking the 'Search' button.");

        searchButton.click();

        // Log after clicking the search button
        Log.info("Clicked the 'Search' button.");

        // Log before waiting for the spinner to disappear
        Log.info("Waiting for the spinner to disappear after clicking the 'Search' button.");

        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        // Log after the spinner has disappeared
        Log.info("Spinner has disappeared. The search process is complete.");
    }
    
 // Method to verify the deactivated disposition icon
    public boolean isDeactivatedDispositionVisible() {
    	
    	Log.info("Waiting for the spinner to disappear.");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        // Log after spinner has disappeared
        Log.info("Spinner is no longer visible, continuing with the process.");

        // Log before finding the status icons
        Log.info("Locating all status icons.");
        
        List<WebElement> statusIcons = driver.findElements(DispositionMasterPageRepo.statusIconslist);
        
        // Log after finding the status icons
        Log.info("Found " + statusIcons.size() + " status icons.");

        // Iterate through the list of status icons
        for (WebElement icon : statusIcons) {
            // Log for each icon processing
            Log.info("Processing icon with style: " + icon.getAttribute("style"));

            if (!icon.getAttribute("style").contains("color: red")) {
                // Log if the icon is not red
                Log.warn("Icon is not in a red tick: " + icon.getAttribute("style"));
                return false;
            } else {
                // Log if the icon is red
                Log.info("Icon is in a red tick.");
            }
        }

        // Log after successfully processing all icons
        Log.info("All items are in a red tick.");

        return true;
    }
    
    public boolean isActiveDispositionShown() {
    	Log.info("Waiting for status icons to load.");
        
        // Wait for the status icons list to be ready
        Common.fluentWait("Status icon list", DispositionMasterPageRepo.statusIconslist);
        
        // Log after status icons are loaded
        Log.info("Status icons have been loaded, proceeding to process each icon.");

        // Locate all status icons
        List<WebElement> statusIcons = driver.findElements(DispositionMasterPageRepo.statusIconslist);
        
        // Log the number of status icons found
        Log.info("Found " + statusIcons.size() + " status icons to check.");

        // Iterate through the list of status icons
        for (WebElement icon : statusIcons) {
            // Log before checking each icon's style
            Log.info("Checking icon with style: " + icon.getAttribute("style"));

            // Check if the icon has a green color
            if (!icon.getAttribute("style").contains("color: green")) {
                // Log if the icon is not green
                Log.warn("Icon is not in a green tick: " + icon.getAttribute("style"));
                return false;
            } else {
                // Log if the icon is green
                Log.info("Icon is in a green tick.");
            }
        }

        // Log if all icons are green
        Log.info("All items are in a green tick.");
        
        return true;
    }
    
 // Method to navigate to Sub-Disposition tab
    public void navigateToSubDispositionTab() {
    	Log.info("Starting the navigation to the Sub Disposition tab.");

        try {
            // Log before waiting for the spinner to disappear
            Log.info("Waiting for spinner to disappear.");
            
            // Wait until the spinner is no longer visible
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
            Log.info("Spinner is no longer visible.");

            // Locate the Sub Disposition tab
            Log.info("Locating the Sub Disposition tab element.");
            WebElement subDispositionElement = driver.findElement(DispositionMasterPageRepo.subDispositionOption);

            // Click the Sub Disposition tab
            Log.info("Clicking the Sub Disposition tab.");
            subDispositionElement.click();
            Log.info("Sub Disposition tab clicked successfully.");

        } catch (Exception e) {
            Log.error("An error occurred while navigating to the Sub Disposition tab: " + e.getMessage());
        }

        Log.info("Completed the navigation to the Sub Disposition tab.");
    }
    
    // Method to verify UI elements
    public boolean verifyUIElements() {
    	Log.info("Verifying presence and status of UI elements.");

        // Log before finding the subDispositionList
        Log.info("Finding subDisposition list.");
        List<WebElement> subDispositionList = driver.findElements(DispositionMasterPageRepo.subDispositionList);
        Log.info("Found subDisposition list with size: " + subDispositionList.size());

        // Log before finding the subActionOwnerDropdown
        Log.info("Finding subActionOwnerDropdown.");
        WebElement subActionOwnerDropdown = driver.findElement(DispositionMasterPageRepo.subactionOwnerDropdown);
        Log.info("subActionOwnerDropdown found, checking visibility.");

        // Log before checking if the subActionOwnerDropdown is displayed
        boolean isSubActionOwnerDropdownDisplayed = subActionOwnerDropdown.isDisplayed();
        Log.info("subActionOwnerDropdown displayed: " + isSubActionOwnerDropdownDisplayed);

        // Log before finding the dispositionSearchField
        Log.info("Finding dispositionSearchField.");
        WebElement dispositionSearchField = driver.findElement(DispositionMasterPageRepo.dispositionSearchField);
        Log.info("dispositionSearchField found, checking visibility.");

        // Log before checking if the dispositionSearchField is displayed
        boolean isDispositionSearchFieldDisplayed = dispositionSearchField.isDisplayed();
        Log.info("dispositionSearchField displayed: " + isDispositionSearchFieldDisplayed);

        // Log before finding the subIsActiveCheckbox
        Log.info("Finding subIsActiveCheckbox.");
        WebElement subIsActiveCheckbox = driver.findElement(DispositionMasterPageRepo.subisActiveCheckbox);
        Log.info("subIsActiveCheckbox found, checking if it is selected.");

        // Log before checking if the subIsActiveCheckbox is selected
        boolean isSubIsActiveCheckboxSelected = subIsActiveCheckbox.isSelected();
        Log.info("subIsActiveCheckbox selected: " + isSubIsActiveCheckboxSelected);

        // Log before finding the subSearchButton
        Log.info("Finding subSearchButton.");
        WebElement subSearchButton = driver.findElement(DispositionMasterPageRepo.subsearchButton);
        Log.info("subSearchButton found, checking visibility.");

        // Log before checking if the subSearchButton is displayed
        boolean isSubSearchButtonDisplayed = subSearchButton.isDisplayed();
        Log.info("subSearchButton displayed: " + isSubSearchButtonDisplayed);

        // Log before finding the addSubDispositionButton
        Log.info("Finding addSubDispositionButton.");
        WebElement addSubDispositionButton = driver.findElement(DispositionMasterPageRepo.addSubDispositionButton);
        Log.info("addSubDispositionButton found, checking visibility.");

        // Log before checking if the addSubDispositionButton is displayed
        boolean isAddSubDispositionButtonDisplayed = addSubDispositionButton.isDisplayed();
        Log.info("addSubDispositionButton displayed: " + isAddSubDispositionButtonDisplayed);

        // Log before checking the size of the subDispositionList
        Log.info("Checking size of the subDisposition list.");
        boolean isSubDispositionListValid = subDispositionList.size() <= 10;
        Log.info("subDisposition list size is valid: " + isSubDispositionListValid);

        // Return the final result based on all conditions
        return isSubActionOwnerDropdownDisplayed &&
               isDispositionSearchFieldDisplayed &&
               isSubIsActiveCheckboxSelected &&
               isSubSearchButtonDisplayed &&
               isAddSubDispositionButtonDisplayed &&
               isSubDispositionListValid;
    }
    
 // Method to click on Add Sub-Disposition button
    public void clickAddSubDispositionButton() {
    	Log.info("Starting the process of clicking the 'Add Sub Disposition' button.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Log before finding the 'Add Sub Disposition' button
        Log.info("Locating 'Add Sub Disposition' button.");
        WebElement addSubDispositionButton = driver.findElement(DispositionMasterPageRepo.addSubDispositionButton);

        // Log before waiting for the spinner to disappear
        Log.info("Waiting for the spinner to disappear.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        // Log before clicking the 'Add Sub Disposition' button
        Log.info("Clicking the 'Add Sub Disposition' button.");
        addSubDispositionButton.click();

        // Log after clicking the button
        Log.info("Successfully clicked the 'Add Sub Disposition' button.");
    }

    // Method to verify popup elements are present
    public boolean issubdispositionPopupelementsDisplayed() {
    	
    	Log.info("Starting the process of checking if sub disposition popup elements are displayed.");

        // Log before locating the 'Action Owner Dropdown' element
        Log.info("Locating 'Action Owner Dropdown' element.");
        WebElement subPopupActionOwnerDropdownElement = driver.findElement(DispositionMasterPageRepo.actionOwnerDropdown);

        // Log before locating the 'Disposition Dropdown' element
        Log.info("Locating 'Disposition Dropdown' element.");
        WebElement subPopupDispositionDropdownElement = driver.findElement(DispositionMasterPageRepo.subPopupDispositionDropdown);

        // Log before locating the 'Sub Disposition Name Field' element
        Log.info("Locating 'Sub Disposition Name Field' element.");
        WebElement subPopupSubDispositionNameFieldElement = driver.findElement(DispositionMasterPageRepo.subpopupsubDispositionNameField);

        // Log before locating the 'Submit Button' element
        Log.info("Locating 'Submit Button' element.");
        WebElement subPopupSubmitButtonElement = driver.findElement(DispositionMasterPageRepo.submitButton);

        // Log before locating the 'Close Popup Button' element
        Log.info("Locating 'Close Popup Button' element.");
        WebElement subPopupClosePopupButtonElement = driver.findElement(DispositionMasterPageRepo.subpopupclosePopupButton);

        // Log before returning the result
        Log.info("Checking if all popup elements are displayed.");

        // Return if all the elements are displayed
        boolean result = subPopupActionOwnerDropdownElement.isDisplayed()
                && subPopupDispositionDropdownElement.isDisplayed()
                && subPopupSubDispositionNameFieldElement.isDisplayed()
                && subPopupSubmitButtonElement.isDisplayed()
                && subPopupClosePopupButtonElement.isDisplayed();

        // Log after returning the result
        Log.info("Sub disposition popup elements visibility check completed with result: " + result);

        return result;
    }
    
 // Method to click the close button in the popup
    public void clickCloseButton() {
    	Log.info("Locating 'Close Popup Button' element.");
        
        // Locate the 'Close Popup Button' element
        WebElement subPopupClosePopupButtonElement = driver.findElement(DispositionMasterPageRepo.subpopupclosePopupButton);
        
        // Log before clicking the 'Close Popup Button' element
        Log.info("Clicking the 'Close Popup Button' element.");
        
        // Click on the 'Close Popup Button' element
        subPopupClosePopupButtonElement.click();
        
        // Log after clicking the button
        Log.info("Successfully clicked the 'Close Popup Button' element.");
    }
    
 // Method to select an action owner
    public void selectsubdispositionActionOwner(String owner) {
    	Log.info("Waiting for the spinner to disappear before selecting the Action Owner.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        
        // Log before locating the 'Action Owner Dropdown'
        Log.info("Locating the 'Action Owner Dropdown' element.");

        // Implement logic to select the required option
        WebElement subPopupClosePopupButtonElement = driver.findElement(DispositionMasterPageRepo.actionOwnerDropdown);
        
        // Log before clicking on 'Action Owner Dropdown'
        Log.info("Clicking on the 'Action Owner Dropdown' element.");
        subPopupClosePopupButtonElement.click();
        
        // Log after clicking on 'Action Owner Dropdown'
        Log.info("Successfully clicked on the 'Action Owner Dropdown' element.");
        
        // Log before waiting for spinner to disappear again
        Log.info("Waiting for the spinner to disappear after clicking on the dropdown.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        
        // Log before selecting the option in the dropdown
        Log.info("Locating and selecting the action owner: " + owner);

        By subpopupactionOwnerDropdownvaluepath = DispositionMasterPageRepo.subDispositionActionOwnerOption(owner);
        WebElement subpopupactionOwnervalue = driver.findElement(subpopupactionOwnerDropdownvaluepath);
        
        // Log before clicking the selected owner option
        Log.info("Clicking on the selected action owner option: " + owner);
        subpopupactionOwnervalue.click();
        
        // Log after selecting the option
        Log.info("Successfully selected the action owner: " + owner);
        
        // Log before waiting for spinner to disappear after selection
        Log.info("Waiting for the spinner to disappear after selecting the action owner.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        
        // Log that the action was completed successfully
        Log.info("Action Owner selection process completed successfully.");
    }

    // Method to enter disposition
    public void enterDisposition(String disposition) {
    	Log.info("Waiting for the spinner to disappear before interacting with the Disposition dropdown.");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        
        // Log before clicking the 'Disposition' dropdown
        Log.info("Locating the 'Disposition' dropdown.");
        
        WebElement subpopupdispositionDropdown = driver.findElement(DispositionMasterPageRepo.subPopupDispositionDropdown);
        
        // Log before clicking the dropdown
        Log.info("Clicking the 'Disposition' dropdown.");
        subpopupdispositionDropdown.click();
        
        // Log after clicking the dropdown
        Log.info("Successfully clicked the 'Disposition' dropdown.");
        
        // Log before waiting for the spinner to disappear after dropdown click
        Log.info("Waiting for the spinner to disappear after clicking the 'Disposition' dropdown.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        
        // Log before selecting the disposition option
        Log.info("Locating the disposition option: " + disposition);
        
        By subpopupDispositionOption = DispositionMasterPageRepo.subPopupDispositionOption(disposition);
        WebElement subpopupactionOwnervalue = wait.until(ExpectedConditions.visibilityOfElementLocated(subpopupDispositionOption));
        // Log before clicking the selected disposition option
        Log.info("Clicking the disposition option: " + disposition);
        subpopupactionOwnervalue.click();
        
        // Log after clicking the disposition option
        Log.info("Successfully selected the disposition: " + disposition);
        
        // Log before waiting for the spinner to disappear after selection
        Log.info("Waiting for the spinner to disappear after selecting the disposition.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        
        // Log that the action was completed
        Log.info("Disposition selection process completed successfully.");
    }

    // Method to enter sub-disposition
    public void enterSubDisposition(String subDisposition) {
    	Log.info("Waiting for the spinner to disappear before interacting with the Sub-Disposition Name field.");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        
        // Log before locating the Sub-Disposition Name field
        Log.info("Locating the Sub-Disposition Name field.");
        
        WebElement subpopupsubDispositionNameField = driver.findElement(DispositionMasterPageRepo.subpopupsubDispositionNameField);
        
        // Log before entering text into the Sub-Disposition Name field
        Log.info("Entering text into the Sub-Disposition Name field: " + subDisposition);
        subpopupsubDispositionNameField.sendKeys(subDisposition);
        
        // Log after entering text into the Sub-Disposition Name field
        Log.info("Successfully entered the Sub-Disposition Name: " + subDisposition);
    }

    // Method to click submit button
    public void addsubdisposistionSubmit() {
    	Log.info("Locating the Submit button.");
        
        WebElement submitButton = driver.findElement(DispositionMasterPageRepo.submitButton);
        
        // Log before waiting for the spinner to disappear
        Log.info("Waiting for the spinner to disappear before clicking the Submit button.");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        
        // Log before clicking the Submit button
        Log.info("Clicking the Submit button.");
        
        submitButton.click();
        
        // Log after clicking the Submit button
        Log.info("Successfully clicked the Submit button.");
    }
    
 // Method to verify if success message is displayed
    public boolean isSuccessMessageDisplayedforsubdisposition() {
    	Log.info("Waiting for the success message to be visible.");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        
        // Log before retrieving the success message element
        Log.info("Retrieving the success message element.");
        
        WebElement successmsg =  wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.successMessage2));
        
        // Log before checking the text of the success message
        Log.info("Checking if the success message text is 'Saved Successfully'.");
        
        boolean isMessageDisplayed = successmsg.getText().equals("Saved Successfully");
        
        // Log after checking the success message
        if (isMessageDisplayed) {
            Log.info("Success message is displayed with text: 'Saved Successfully'.");
        } else {
            Log.info("Success message is not displayed or text does not match.");
        }
        
        return isMessageDisplayed;
    }
    
 
    // Method to verify if success message is displayed
    public boolean iserrorMessageDisplayedforsubdisposition() {
    	Log.info("Waiting for the error message to be visible.");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        
        // Log before retrieving the error message element
        Log.info("Retrieving the error message element.");
        
        WebElement errormsg =  wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.errorMessageforsubdispostion));
        
        // Log before checking the text of the error message
        Log.info("Checking if the error message text is 'This Sub-Disposition Already Exist'.");
        
        boolean isErrorMessageDisplayed = errormsg.getText().equals("This Sub-Disposition Already Exist");
        
        // Log after checking the error message
        if (isErrorMessageDisplayed) {
            Log.info("Error message is displayed with text: 'This Sub-Disposition Already Exist'.");
        } else {
            Log.info("Error message is not displayed or text does not match.");
        }
        
        return isErrorMessageDisplayed;
    }
    
 // Method to open edit dialog
    public void openEditPopup() {
    	Log.info("Waiting for error message to disappear.");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.errorMessageforsubdispostion));
        
        // Log before waiting for the spinner to disappear
        Log.info("Waiting for spinner to disappear.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        
        // Log before clicking the close popup button
        Log.info("Clicking the close popup button.");
        WebElement subpopupclosePopupButton = driver.findElement(DispositionMasterPageRepo.subpopupclosePopupButton);
        subpopupclosePopupButton.click();
        
        // Log after clicking the close popup button
        Log.info("Close popup button clicked.");
        
        // Log before waiting for the spinner to disappear again
        Log.info("Waiting for spinner to disappear after closing the popup.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        
        // Log before clicking the action button
        Log.info("Clicking the action button.");
        WebElement actionButton = driver.findElement(DispositionMasterPageRepo.actionButton);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        actionButton.click();
        
        // Log after clicking the action button
        Log.info("Action button clicked.");
        
        // Log before clicking the sub-disposition edit button
        Log.info("Clicking the sub-disposition edit button.");
        WebElement subdispoeditButton = driver.findElement(DispositionMasterPageRepo.subdispoeditButton);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        subdispoeditButton.click();
        
        // Log after clicking the sub-disposition edit button
        Log.info("Sub-disposition edit button clicked.");
        
        // Log after waiting for the spinner to disappear
        Log.info("Waiting for spinner to disappear after clicking the edit button.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }

    // Method to click update without any changes
    public void clickUpdateWithoutChanges() {
    	Log.info("Attempting to locate the update button.");
        
        // Locate the update button
        WebElement subdispoupdateButton = driver.findElement(DispositionMasterPageRepo.subdispoupdateButton);
        
        // Log after the update button is located
        Log.info("Update button located.");
        
        // Log before clicking the update button
        Log.info("Clicking the update button.");
        
        // Click the update button
        subdispoupdateButton.click();
        
        // Log after clicking the update button
        Log.info("Update button clicked.");
    }
    
 // Method to verify success message
    public boolean isSuccessMessageDisplayedforsubdispos() {
    	Log.info("Waiting for the success message to be visible.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Log before retrieving the success message element
        Log.info("Retrieving the success message element.");
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.successMessage2));

        // Log after retrieving the success message element
        Log.info("Success message element retrieved.");

        // Log before checking if the success message is displayed
        Log.info("Checking if the success message is displayed.");

        boolean isDisplayed = successMessage.isDisplayed();

        // Log after checking if the success message is displayed
        Log.info("Success message displayed: " + isDisplayed);

        return isDisplayed;
    }
    
    public void openEditPopuptoupdate() {
    	Log.info("Initializing WebDriverWait with a timeout of 60 seconds.");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        
        // Log before locating the action button
        Log.info("Locating the action button.");
        WebElement actionButton = driver.findElement(DispositionMasterPageRepo.actionButton);

        // Log before waiting for spinner to disappear
        Log.info("Waiting for the spinner to disappear before clicking the action button.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        // Log before clicking the action button
        Log.info("Clicking the action button.");
        actionButton.click();

        // Log before locating the sub-disposition edit button
        Log.info("Locating the sub-disposition edit button.");
        WebElement subdispoeditButton = driver.findElement(DispositionMasterPageRepo.subdispoeditButton);

        // Log before waiting for spinner to disappear again
        Log.info("Waiting for the spinner to disappear before clicking the sub-disposition edit button.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        // Log before clicking the sub-disposition edit button
        Log.info("Clicking the sub-disposition edit button.");
        subdispoeditButton.click();

        // Log after clicking the sub-disposition edit button
        Log.info("Sub-disposition edit button clicked. Waiting for the spinner to disappear.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        // Log after completing the action
        Log.info("Edit popup opened successfully to update.");
    }
    
    public void enterSubDispositionName(String name) {
    	Log.info("Locating the sub-disposition name field.");

        WebElement nameFieldElement = driver.findElement(DispositionMasterPageRepo.subpopupsubDispositionNameField);

        // Log before clearing the field
        Log.info("Clearing the existing value in the sub-disposition name field.");
        nameFieldElement.clear();

        // Log before entering the new name
        Log.info("Entering the new name: " + name + " into the sub-disposition name field.");
        nameFieldElement.sendKeys(name);

        // Log before locating the outside element (body tag)
        Log.info("Locating the outside element to click (body tag).");

        WebElement outsideElementWebElement = driver.findElement(DispositionMasterPageRepo.outsideElementPath3); // Assuming the <body> tag is safe to click

        // Log before clicking the outside element
        Log.info("Clicking the outside element to save the input.");

        outsideElementWebElement.click();

        // Log after clicking the outside element
        Log.info("Sub-disposition name entered and outside element clicked.");
    }
    
    public void clickUpdateWithexistingname() {
    	Log.info("Locating the update button for the existing name.");

        WebElement subdispoupdateButton = driver.findElement(DispositionMasterPageRepo.subdispoupdateButton);

        // Log before clicking the update button
        Log.info("Clicking the update button for the existing name.");

        subdispoupdateButton.click();

        // Log after clicking the update button
        Log.info("Update button clicked for the existing name.");
    }
    
    public String getErrorMessageforexistingnameupdate() {
    	Log.info("Waiting for the error message to be visible.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement existingmsgpop = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.errorMessage2));

        // Log before retrieving the error message text
        Log.info("Error message is now visible. Retrieving the error message text.");

        String errorMessage = existingmsgpop.getText();

        // Log after retrieving the error message
        Log.info("Error message retrieved: " + errorMessage);

        return errorMessage;
    }
    
    public void openEditPopuptoupdateexistingname() {
    	Log.info("Waiting for the error message to disappear.");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.errorMessage2));

        // Log before waiting for the spinner to disappear
        Log.info("Waiting for the spinner to disappear.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        // Log before clicking the close popup button
        Log.info("Clicking the close popup button.");
        WebElement subPopupClosePopupButtonElement = driver.findElement(DispositionMasterPageRepo.subpopupclosePopupButton);
        subPopupClosePopupButtonElement.click();

        // Log after clicking close popup button
        Log.info("Close popup button clicked.");

        // Log before clicking the action button
        Log.info("Clicking the action button.");
        WebElement actionButton = driver.findElement(DispositionMasterPageRepo.actionButton);
        actionButton.click();

        // Log after clicking action button
        Log.info("Action button clicked.");

        // Log before clicking the edit button
        Log.info("Clicking the edit button.");
        WebElement subdispoeditButton = driver.findElement(DispositionMasterPageRepo.subdispoeditButton);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        subdispoeditButton.click();

        // Log after clicking the edit button
        Log.info("Edit button clicked.");

        // Log after waiting for spinner to disappear
        Log.info("Waiting for spinner to disappear after clicking edit button.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

    }
    
    public void clkThreeDotButtonofsubdisposition() {
    	Log.info("Waiting for the success message to disappear.");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.successMessage2));

        // Log before waiting for spinner to disappear
        Log.info("Waiting for the spinner to disappear.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        // Log before clicking the action column button
        Log.info("Clicking the three-dot button in the action column.");
        WebElement actionColumnButton = driver.findElement(DispositionMasterPageRepo.actionColumnButton2);
        actionColumnButton.click();

        // Log after clicking the action column button
        Log.info("Three-dot button clicked in the action column.");
    }
    
 // Method to click on the Activate/De-activate option
    public void clickActivateDeactivateOptionofsubdisposition() {
    	Log.info("Attempting to click the Activate/Deactivate option for Sub-Disposition.");
        
        // Find the activate/deactivate option element
        WebElement activateDeactivateOption = driver.findElement(DispositionMasterPageRepo.activateDeactivateOption2);
        
        // Log before performing the click action
        Log.info("Clicking the Activate/Deactivate option.");
        activateDeactivateOption.click();
        
        // Log after the click action
        Log.info("Activate/Deactivate option clicked successfully.");
    }
    
 // Method to select action owner as Call centre
    public void selActionOwnerforsubdispositionsearch(String actionOwner) {
    	Log.info("Waiting for spinner to disappear before selecting action owner.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        
        // Log before finding and clicking the action owner dropdown
        Log.info("Finding the action owner dropdown and clicking it.");
        WebElement actionOwnerDropdown = driver.findElement(DispositionMasterPageRepo.subPopupDispositionDropdown);
        actionOwnerDropdown.click();
        
        // Log after clicking the action owner dropdown and waiting for spinner to disappear
        Log.info("Waiting for spinner to disappear after clicking action owner dropdown.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        
        // Log before finding and selecting the action owner option
        Log.info("Selecting action owner option: " + actionOwner);
        By actionOwnerOption = DispositionMasterPageRepo.actionOwnerOption3(actionOwner);
        WebElement ccelement = wait.until(ExpectedConditions.visibilityOfElementLocated(actionOwnerOption));
        ccelement.click();
        
        // Log after selecting the action owner
        Log.info("Action owner selected successfully: " + actionOwner);
        
        // Log before clicking outside element to finalize selection
        Log.info("Clicking outside element to finalize selection.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        WebElement outsideElement = driver.findElement(DispositionMasterPageRepo.outsideElementPath2); // Assuming the <body> tag is safe to click
        outsideElement.click();
        
        // Log after clicking outside element
        Log.info("Clicked outside element to finalize action owner selection.");

        // Log before waiting for spinner to disappear after final click
        Log.info("Waiting for spinner to disappear after clicking outside element.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

    }
    
 // Method to select action owner as Call centre
    public void seldispositionforsubdispositionsearch(String dispostion) {
    	Log.info("Waiting for spinner to disappear before selecting disposition.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        
        // Log before finding and clicking the disposition search field
        Log.info("Finding and clicking the disposition search field.");
        WebElement dispositionDropdown = driver.findElement(DispositionMasterPageRepo.dispositionSearchField);
        dispositionDropdown.click();
        
        // Log after clicking the disposition search field and waiting for spinner to disappear
        Log.info("Waiting for spinner to disappear after clicking disposition search field.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        
        // Log before selecting the disposition option
        Log.info("Selecting disposition option: " + dispostion);
        By dispositionOption = DispositionMasterPageRepo.subDispositionOption(dispostion);
        WebElement subpopupDispositionValue = wait.until(ExpectedConditions.visibilityOfElementLocated(dispositionOption));
        Log.info("Clicking the disposition option: " + dispostion);
        subpopupDispositionValue.click();
        // Log after selecting the disposition option
        Log.info("Disposition option selected successfully: " + dispostion);
        
        // Log before clicking outside element to finalize selection
        Log.info("Clicking outside element to finalize disposition selection.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        WebElement outsideElement = driver.findElement(DispositionMasterPageRepo.outsideElementPath2); // Assuming the <body> tag is safe to click
        outsideElement.click();
        
        // Log after clicking outside element
        Log.info("Clicked outside element to finalize disposition selection.");

        // Log before waiting for spinner to disappear after final click
        Log.info("Waiting for spinner to disappear after clicking outside element.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

    }
    
 // Method to untick Is active checkbox
    public void untickIsActiveCheckboxforsubdispositionsearch() {
    	Log.info("Waiting for spinner to disappear before unticking the Is Active checkbox.");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        
        // Log before finding the checkbox
        Log.info("Finding the Is Active checkbox.");
        WebElement checkbox = driver.findElement(DispositionMasterPageRepo.isActiveCheckbox3);
        
        // Log before clicking the checkbox
        Log.info("Unticking the Is Active checkbox.");
        checkbox.click();
        
        // Log after clicking the checkbox and waiting for spinner to disappear
        Log.info("Waiting for spinner to disappear after unticking the Is Active checkbox.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        
        // Log after the spinner disappears
        Log.info("Spinner disappeared after unticking the Is Active checkbox.");

        } 
    
 // Method to click on the search button
    public void clickSearchButtonforsubdispositionsearch() {
    	Log.info("Waiting for spinner to disappear before clicking the search button.");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        
        // Log before finding the search button
        Log.info("Finding the search button.");
        WebElement searchButton = driver.findElement(DispositionMasterPageRepo.searchButton2);
        
        // Log before clicking the search button
        Log.info("Clicking the search button.");
        searchButton.click();
        
        // Log after clicking the search button and waiting for the spinner to disappear
        Log.info("Waiting for spinner to disappear after clicking the search button.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        
        // Log after the spinner disappears
        Log.info("Spinner disappeared after clicking the search button.");
    }
    
 // Method to verify the deactivated disposition icon
    public boolean isDeactivatedDispositionVisibleforsubdispositionsearch() {
    	
    	Log.info("Waiting for spinner to disappear before checking status icons.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        
        // Log after spinner disappears
        Log.info("Spinner disappeared, proceeding to check status icons.");
        
        // Log before finding the status icons
        Log.info("Finding status icons on the page.");
        List<WebElement> statusIcons = driver.findElements(DispositionMasterPageRepo.statusIconslist2);
        
        // Log before looping through each icon
        Log.info("Looping through the status icons to check their colors.");
        
        for (WebElement icon : statusIcons) {
            // Log before checking the style of the icon
            Log.info("Checking icon style: " + icon.getAttribute("style"));
            
            if (!icon.getAttribute("style").contains("color: red")) {
                // Log if the icon is not red
                Log.info("Icon is not in a red tick: " + icon.getAttribute("style"));
                return false;
            } else {
                // Log if the icon is red
                Log.info("All items are in a red tick.");
            }
        }
        
        // Log after looping through all icons
        Log.info("Finished checking all icons. All items are in a red tick.");
        
        return true;
    }
    
 // Method to click on the three-dot action button
    public void clkThreeDotButtonfordeactivatingsubdisposition() {
    	Log.info("Finding the action column button to click for deactivating sub disposition.");

        WebElement actionColumnButton = driver.findElement(DispositionMasterPageRepo.actionColumnButton2);
        
        // Log after finding the button
        Log.info("Action column button found.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        
        // Log before waiting for the spinner to disappear
        Log.info("Waiting for the spinner to disappear before clicking the action column button.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        
        // Log after waiting for the spinner to disappear
        Log.info("Spinner disappeared, ready to click the action column button.");

        // Log before clicking the action column button
        Log.info("Clicking the action column button to deactivate sub disposition.");
        actionColumnButton.click();

        // Log after clicking the button
        Log.info("Action column button clicked successfully.");
    }
    
 // Method to click on the Activate/De-activate option
    public void clickActivateDeactivateOptionfordeactivatingsubdisposition() {
    	Log.info("Finding the Activate/Deactivate option for deactivating sub disposition.");

        WebElement activateDeactivateOption = driver.findElement(DispositionMasterPageRepo.activateDeactivateOption2);
        
        // Log after finding the Activate/Deactivate option
        Log.info("Activate/Deactivate option found.");

        // Log before clicking the Activate/Deactivate option
        Log.info("Clicking the Activate/Deactivate option to deactivate the sub disposition.");
        activateDeactivateOption.click();

        // Log after clicking the Activate/Deactivate option
        Log.info("Activate/Deactivate option clicked successfully.");
    }
    
 // Method to select action owner as Call centre
    public void selActionOwnerforactivesubdispositionsearch(String actionOwner) {
    	Log.info("Clearing the existing value in the action owner field.");
        WebElement clearValueButton = driver.findElement(DispositionMasterPageRepo.clearvaluepath3);
        clearValueButton.click();
        Log.info("Existing value in the action owner field cleared.");

        // Wait until the spinner is invisible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        // Log before selecting the action owner dropdown
        Log.info("Opening the action owner dropdown.");
        WebElement actionOwnerDropdown = driver.findElement(DispositionMasterPageRepo.subPopupDispositionDropdown);
        actionOwnerDropdown.click();
        Log.info("Action owner dropdown opened.");

        // Log before selecting the action owner from the dropdown
        Log.info("Selecting the action owner: " + actionOwner);
        By actionOwnerLocator = DispositionMasterPageRepo.actionOwnerOption3(actionOwner);
        WebElement ccelement = wait.until(ExpectedConditions.visibilityOfElementLocated(actionOwnerLocator));
        ccelement.click();
        Log.info("Action owner selected: " + actionOwner);

        // Wait until the spinner is invisible
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        // Log before clicking outside element to close the dropdown
        Log.info("Clicking outside to close the action owner dropdown.");
        WebElement outsideElement = driver.findElement(DispositionMasterPageRepo.outsideElementPath2); // Assuming the <body> tag is safe to click
        outsideElement.click();
        Log.info("Clicked outside the dropdown to close it.");

        // Wait until the spinner is invisible
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

    }
    
 // Method to select action owner as Call centre
    public void seldispositionforactivesubdispositionsearch(String disposition) {
    	Log.info("Waiting for the spinner to disappear before selecting disposition.");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Spinner is no longer visible.");

        // Log before clicking on the disposition dropdown
        Log.info("Opening the disposition dropdown.");
        WebElement dispositionDropdown = driver.findElement(DispositionMasterPageRepo.dispositionSearchField);
        dispositionDropdown.click();
        Log.info("Disposition dropdown opened.");

        // Wait until the spinner is invisible
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        // Log before selecting a disposition option
        Log.info("Selecting the disposition: " + disposition);
        By dispositionLocator = DispositionMasterPageRepo.dispositionOption(disposition);
        WebElement subpopupactionOwnervalue = wait.until(ExpectedConditions.visibilityOfElementLocated(dispositionLocator));
        Log.info("Clicking the disposition option: " + disposition);
        subpopupactionOwnervalue.click();
        Log.info("Disposition selected: " + disposition);

        // Wait until the spinner is invisible
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        // Log before clicking outside the element to close the dropdown
        Log.info("Clicking outside to close the disposition dropdown.");
        WebElement outsideElement = driver.findElement(DispositionMasterPageRepo.outsideElementPath2); // Assuming the <body> tag is safe to click
        outsideElement.click();
        Log.info("Clicked outside to close the disposition dropdown.");

        // Wait until the spinner is invisible
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

    }
    
 // Method to verify the deactivated disposition icon
    public boolean isActiveDispositionVisibleforsubdispositionsearch() {
    	
    	 Log.info("Waiting for the spinner to disappear before checking active disposition.");

    	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	    wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	    Log.info("Spinner is no longer visible.");

    	    // Log before finding status icons
    	    Log.info("Finding status icons for active dispositions.");
    	    List<WebElement> statusIcons = driver.findElements(DispositionMasterPageRepo.statusIconslist2);

    	    // Log before iterating through the status icons
    	    Log.info("Checking each status icon to ensure it has a green tick.");

    	    for (WebElement icon : statusIcons) {
    	        // Log before checking the icon color
    	        Log.info("Checking the icon's style attribute: " + icon.getAttribute("style"));
    	        
    	        if (!icon.getAttribute("style").contains("color: green")) {
    	            // Log if the icon is not green
    	            Log.warn("Icon is not in a green tick: " + icon.getAttribute("style"));
    	            return false;
    	        } else {
    	            // Log if the icon is green
    	            Log.info("All items are in a green tick.");
    	        }
    	    }

    	    // Log after checking all icons
    	    Log.info("All icons are verified as having a green tick.");
    	    return true;
    }
}
    
 
    
    
