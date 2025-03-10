package core.Configurations;


import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.BasePackage.DBUtils;
import com.BasePackage.ExecuteStoredProcedure;
import com.Page_Repository.CollectionAgencyDispositionRepo;
import com.Page_Repository.CoreCollectionOfficerConfigRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Utility.Log;
import io.netty.handler.timeout.TimeoutException;

public class CollectionOfficerConfig_MainClass {
	
	private WebDriver driver;
	private String dbValue;
	String Username;
	
	public CollectionOfficerConfig_MainClass(WebDriver driver) {
		Log.info("Initializing CollectionOfficerConfig_MainClass...");
	    
	    this.driver = driver;
	    
	    Log.info("Initializing WebElements using PageFactory...");
	    PageFactory.initElements(driver, this);
	    
	    Log.info("CollectionOfficerConfig_MainClass initialized successfully.");
    }
	
	 public String executeSPInsertEmployee(String userid) {
		 Log.info("Starting execution of stored procedure: executeSPInsertEmployee");
		    
		    try {
		        Log.info("Received User ID: " + userid);
		        
		        String result = ExecuteStoredProcedure.executeInsertEmployeeSP(userid);
		        
		        Log.info("Stored Procedure executed successfully. Output: " + result);
		        
		        return result; // Returning the result
		    } catch (IOException e) {
		        Log.error("Error occurred while executing stored procedure: " + e.getMessage(), e);
		        return null; // Or return an appropriate error message
		    }
	 }
	 
	 public void clickSecurityManagement() {
		 Log.info("Starting the process to click on 'Security Management'...");

		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

		    Log.info("Waiting for spinner to disappear...");
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

		    Log.info("Waiting for the 'Security Management' main menu to be visible...");
		    WebElement SecurityManagementmainmenu = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfigRepo.SecurityManagementmainmenu));

		    Log.info("Clicking on 'Security Management' main menu...");
		    SecurityManagementmainmenu.click();

		    Log.info("'Security Management' clicked successfully.");
	    }

	    public void clickRoleManagement() {
	    	Log.info("Starting the process to click on 'Role Management' submenu...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for 'Role Management' submenu to be visible...");
	        WebElement RoleManagementsubmenu = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfigRepo.RoleManagementsubmenu));

	        Log.info("Clicking on 'Role Management' submenu...");
	        RoleManagementsubmenu.click();

	        Log.info("Waiting for spinner to disappear after clicking 'Role Management'...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	        Log.info("'Role Management' submenu clicked successfully.");
	    }

	    public void clickAddNewRole() {
	    	Log.info("Starting the process to click on 'Add New Role' button...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for 'Add New Role' button to be visible...");
	        WebElement AddNewRolebutton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfigRepo.AddNewRolebutton));

	        Log.info("Clicking on 'Add New Role' button...");
	        AddNewRolebutton.click();

	        Log.info("Waiting for spinner to disappear after clicking 'Add New Role'...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	        Log.info("'Add New Role' button clicked successfully.");
	    }

	    public boolean isCollectionOfficerConfigurationsAvailable() {
	    	Log.info("Checking if 'Collection Officer Configurations' checkbox is available...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for 'Collection Officer Configurations' checkbox to be visible...");
	        WebElement CollectionOfficerConfigcheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfigRepo.CollectionOfficerConfigcheckbox));

	        boolean isDisplayed = CollectionOfficerConfigcheckbox.isDisplayed();
	        
	        Log.info("'Collection Officer Configurations' checkbox is " + (isDisplayed ? "visible." : "not visible."));
	        
	        return isDisplayed;
	    }
	    
	    // Method to click on Configurations main menu
	    public void clickConfigurationsMenu() {
	    	Log.info("Starting the process to click on 'Configurations' menu...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Clicking on the close button if present...");
	        WebElement closebutton = driver.findElement(CoreCollectionOfficerConfigRepo.closebutton);
	        closebutton.click();
	        
	        Log.info("Waiting for spinner to disappear after clicking close button...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	        Log.info("Waiting for 'Configurations' main menu to be visible...");
	        WebElement Configurationsmainmenu = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfigRepo.Configurationsmainmenu));

	        Log.info("Clicking on 'Configurations' main menu...");
	        Configurationsmainmenu.click();

	        Log.info("'Configurations' menu clicked successfully.");
	    }

	    // Method to click on Collection Officer Config submenu
	    public void clickCollectionOfficerConfigSubmenu() {
	    	Log.info("Starting the process to click on 'Collection Officer Config' submenu...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for 'Collection Officer Config' submenu to be visible...");
	        WebElement CollectionOfficerConfigsubmenu = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfigRepo.CollectionOfficerConfigsubmenu));

	        Log.info("Clicking on 'Collection Officer Config' submenu...");
	        CollectionOfficerConfigsubmenu.click();

	        Log.info("Waiting for spinner to disappear after clicking 'Collection Officer Config'...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	        Log.info("'Collection Officer Config' submenu clicked successfully.");
	    }
	    
	    // Method to check if CO field is mandatory
	    public boolean isCOFieldMandatory() {
	    	Log.info("Checking if 'CO Field' is mandatory...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for 'CO Field' to be visible...");
	        wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfigRepo.ZoneCOfield));

	        Log.info("Retrieving border color of 'CO Field'...");
	        String borderColor = driver.findElement(CoreCollectionOfficerConfigRepo.ZoneCOfield)
	                                   .getCssValue("border-color");

	        boolean isMandatory = borderColor.equals("rgb(139, 0, 0)");
	        Log.info("'CO Field' mandatory status: " + (isMandatory ? "Yes" : "No"));

	        return isMandatory;
	    }
	    
	    // Method to verify presence of buttons
	    public boolean areButtonsPresent() {
	    	Log.info("Checking if all required buttons and fields are present...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for 'Region' field to be visible...");
	        WebElement Regionfield = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfigRepo.Regionfield));

	        Log.info("Waiting for 'Branch' field to be visible...");
	        WebElement Branchfield = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfigRepo.Branchfield));

	        Log.info("Waiting for 'Search' button to be visible...");
	        WebElement Searchbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfigRepo.Searchbutton));

	        Log.info("Waiting for 'Reset' button to be visible...");
	        WebElement Resetbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfigRepo.Resetbutton));

	        Log.info("Waiting for 'Save Configuration' button to be visible...");
	        WebElement SaveConfigurationbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfigRepo.SaveConfigurationbutton));

	        Log.info("Waiting for 'Download in Excel' button to be visible...");
	        WebElement DownloadinExcelbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfigRepo.DownloadinExcelbutton));

	        boolean allPresent = Regionfield.isDisplayed() &&
	                             Branchfield.isDisplayed() &&
	                             Searchbutton.isDisplayed() &&
	                             Resetbutton.isDisplayed() &&
	                             SaveConfigurationbutton.isDisplayed() &&
	                             DownloadinExcelbutton.isDisplayed();

	        Log.info("All required buttons and fields are " + (allPresent ? "present." : "not present."));
	        
	        return allPresent;
	    }

	    // Method to click the search button
	    public void clickSearch() {
	    	Log.info("Starting the process to click on 'Search' button...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for 'Region' field to be visible before proceeding...");
	        wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfigRepo.Regionfield));

	        Log.info("Waiting for 'Search' button to be visible...");
	        WebElement Searchbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfigRepo.Searchbutton));

	        Log.info("Clicking on 'Search' button...");
	        Searchbutton.click();

	        Log.info("Waiting for spinner to disappear after clicking 'Search'...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	        Log.info("'Search' button clicked successfully.");
	    }

	    // Method to verify the grid displays users
	    public boolean isUserGridDisplayed(String searchText) {
	    	Log.info("Starting the search for '" + searchText + "' in the user grid...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	        Log.info("Waiting for spinner to disappear after clicking 'Search'...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        while (true) {
	            Log.info("Locating all rows inside the table...");
	            List<WebElement> rows = driver.findElements(CoreCollectionOfficerConfigRepo.GridRows);

	            Log.info("Iterating through each row...");
	            for (WebElement row : rows) {
	                List<WebElement> columns = row.findElements(CoreCollectionOfficerConfigRepo.GridColumns);

	                for (WebElement column : columns) {
	                    String columnText = column.getText().trim();
	                    if (columnText.equalsIgnoreCase(searchText)) {
	                        Log.info("Match found: '" + columnText + "' matches the search text.");
	                        return true; // Value found
	                    }
	                }
	            }

	            Log.info("Search text not found on the current page. Checking for 'Next' button...");

	            List<WebElement> nextButton = driver.findElements(CoreCollectionOfficerConfigRepo.GridNextbutton);

	            if (nextButton.isEmpty() || !nextButton.get(0).isEnabled()) {
	                Log.info("No more pages left to search. Search text not found.");
	                return false; // Value not found, and no more pages left
	            }

	            Log.info("Clicking on the 'Next' button to navigate to the next page...");
	            nextButton.get(0).click();

	            Log.info("Waiting for spinner to disappear...");
	            wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	            try {
	                Thread.sleep(2000); // Temporary wait for stability, consider replacing with WebDriverWait
	            } catch (InterruptedException e) {
	                Log.error("Thread sleep interrupted: " + e.getMessage());
	            }
	        }
	    }
	    
	    // Method to click the Reset button
	    public void clickResetButton() throws InterruptedException {
	    	Log.info("Starting the process to click the 'Reset' button...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        Log.info("Scrolling to the top of the page before clicking 'Reset'...");
	        js.executeScript("window.scrollTo(0, 0);");

	        Log.info("Waiting for 5 seconds to stabilize the UI...");
	        Thread.sleep(5000); // Consider replacing with an explicit wait if possible

	        Log.info("Waiting for 'Reset' button to be visible...");
	        WebElement Resetbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfigRepo.Resetbutton));

	        Log.info("Clicking on 'Reset' button...");
	        Resetbutton.click();

	        Log.info("Waiting for spinner to disappear after clicking 'Reset'...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	        Log.info("'Reset' button clicked successfully.");
	    }

	    // Method to verify filters are reset to default state
	    public boolean areFiltersReset() {
	    	Log.info("Checking if filters have been reset...");

	        List<WebElement> rows = driver.findElements(CoreCollectionOfficerConfigRepo.GridRows);

	        Log.info("Number of rows found in the grid: " + rows.size());

	        // If no rows are found, assume the filters have been reset
	        if (rows.isEmpty()) {
	            Log.info("No rows found in the grid. Filters are reset.");
	            return true;
	        }

	        // Check if the first row contains the "No records to display." message
	        String firstRowText = rows.get(0).getText().trim();
	        Log.info("Text in the first row: '" + firstRowText + "'");

	        boolean isReset = firstRowText.equalsIgnoreCase("No records to display.");
	        Log.info("Filters reset status: " + (isReset ? "Yes" : "No"));

	        return isReset;
	    }

	    
	 // Method to fetch value from DB based on user ID
	    public String getDBValue(String userid) throws SQLException, ClassNotFoundException, IOException {
	    	Log.info("Fetching database value for user ID: " + userid);

	        String query = "SELECT is_bco FROM mst_employee WHERE employeeid='" + userid + "'";
	        Log.info("Executing SQL query: " + query);

	        dbValue = DBUtils.executeSQLStatement(query); // Store the value in a string

	        Log.info("Database Value Retrieved: " + dbValue);
	        return dbValue;
	    }

	    // Method to perform UI action
	    public void clickIsActiveCheckboxIfUserFound(String searchText) throws InterruptedException {
	    	Log.info("Starting search for user: " + searchText + " to click the 'Is Active' checkbox.");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        // Scroll to the Download in Excel button before proceeding
	        WebElement downloadInExcelButton = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfigRepo.DownloadinExcelbutton));
	        
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        Log.info("Scrolling to the 'Download in Excel' button...");
	        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'start'});", downloadInExcelButton);
	        
	        Thread.sleep(5000); // Consider replacing this with an explicit wait if possible

	        while (true) { // Loop to iterate through paginated results
	            List<WebElement> rows = driver.findElements(CoreCollectionOfficerConfigRepo.GridRows);
	            Log.info("Total rows found: " + rows.size());

	            boolean isFound = false;

	            for (int i = 0; i < rows.size(); i++) {
	                WebElement row = driver.findElements(CoreCollectionOfficerConfigRepo.GridRows).get(i);
	                Log.info("Processing Row " + (i + 1));

	                List<WebElement> columns = row.findElements(CoreCollectionOfficerConfigRepo.GridColumnsrelative);
	                Log.info("Total columns found in Row " + (i + 1) + ": " + columns.size());

	                if (columns.size() >= 2) { // Ensure it has at least 2 columns
	                    String columnText = columns.get(1).getText().trim();
	                    Log.info("Checking Row " + (i + 1) + " (Column 2 Value: " + columnText + ")");

	                    if (columnText.equalsIgnoreCase(searchText)) {
	                        Log.info("Match found in Row " + (i + 1) + ", clicking 'Is Active' checkbox...");

	                        columns.get(5).click();
	                        Log.info("Successfully clicked 'Is Active' checkbox in Row " + (i + 1) + " (Column 6).");

	                        isFound = true;
	                        break; // Exit after clicking the correct row
	                    }
	                }
	            }

	            if (isFound) {
	                Log.info("User found and checkbox clicked. Exiting method.");
	                return; // Stop execution if found
	            }

	            // Locate the 'Next' button
	            List<WebElement> nextButton = driver.findElements(CoreCollectionOfficerConfigRepo.GridNextbutton);

	            // Check if the 'Next' button is available for pagination
	            if (nextButton.isEmpty() || !nextButton.get(0).isEnabled()) {
	                Log.warn("Search text '" + searchText + "' not found in any page. Exiting method.");
	                return;
	            }

	            // Click the 'Next' button to continue searching in the next page
	            Log.info("Moving to the next page...");
	            nextButton.get(0).click();

	            // Ensure the old table is replaced before continuing
	            wait.until(ExpectedConditions.stalenessOf(rows.get(0)));
	            wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfigRepo.GridRows));
	        }
	    }
	    
	    // Method to click Save Configurations button
	    public void clickSaveConfigurationsButton() {
	    	Log.info("Initiating 'Save Configurations' process...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for 'Save Configuration' button to be visible...");
	        WebElement SaveConfigurationbutton = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfigRepo.SaveConfigurationbutton)
	        );

	        Log.info("Clicking 'Save Configuration' button...");
	        SaveConfigurationbutton.click();

	        Log.info("Waiting for confirmation popup...");
	        WebElement popup_yes_button = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfigRepo.popup_yes_button)
	        );

	        Log.info("Clicking 'Yes' on the confirmation popup...");
	        popup_yes_button.click();

	        Log.info("Waiting for success message to appear...");
	        wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfigRepo.successmsg));
	        
	        Log.info("Waiting for spinner to disappear...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	        Log.info("'Save Configurations' process completed successfully.");
	    }

	    // Method to verify if DB value has changed after UI update
	    public boolean verifyDBUpdate(String userid) throws SQLException, ClassNotFoundException, IOException {
	        
	    	Log.info("Verifying database update for User ID: " + userid);

	        // Step 1: Get value before UI action
	        String beforeValue = dbValue;
	        Log.info("Before UI Change: " + beforeValue);

	        // Step 2: Get value after UI action
	        String afterValue = getDBValue(userid);
	        Log.info("After UI Change: " + afterValue);

	        // Step 3: Compare values and return result
	        boolean isUpdated = beforeValue != null && afterValue != null && !beforeValue.equals(afterValue);
	        
	        if (isUpdated) {
	            Log.info("Database value updated successfully.");
	        } else {
	            Log.warn("Database value did not change.");
	        }

	        return isUpdated;
	    }
	    
	 // Method to click on My Desk main menu
	    public void clickMyDeskMainMenu() {
	    	Log.info("Initiating click action on 'My Desk' main menu...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for 'My Desk' main menu to be visible...");
	        WebElement myDeskMainMenu = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfigRepo.myDeskMainMenu)
	        );

	        Log.info("Clicking on 'My Desk' main menu...");
	        myDeskMainMenu.click();

	        Log.info("'My Desk' main menu clicked successfully.");
	    }

	    // Method to click on Dashboard sub menu
	    public void clickDashboardSubMenu() {
	    	Log.info("Initiating click action on 'Dashboard' sub-menu...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for 'Dashboard' sub-menu to be visible...");
	        WebElement dashboardSubMenu = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfigRepo.dashboardSubMenu)
	        );

	        Log.info("Clicking on 'Dashboard' sub-menu...");
	        dashboardSubMenu.click();

	        Log.info("Waiting for the loading spinner to disappear...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	        Log.info("'Dashboard' sub-menu clicked successfully.");
	    }

	    // Method to click on any tile of Unassigned accounts
	    public void clickUnassignedAccountsTile() throws InterruptedException {
	    	Log.info("Initiating click action on 'Unassigned Accounts' tile...");

	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        Log.info("Scrolling down to the bottom of the page...");
	        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

	        Thread.sleep(5000); // Consider replacing with explicit wait for better stability

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for 'Unassigned Accounts' tile to be visible...");
	        WebElement unassignedAccountsTile = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfigRepo.unassignedAccountsTile)
	        );

	        Log.info("Clicking on 'Unassigned Accounts' tile...");
	        unassignedAccountsTile.click();

	        Log.info("Waiting for loading spinner to disappear...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	        try {
	            Log.info("Waiting for 'ZoneCO' field to become visible...");
	            wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyDispositionRepo.ZoneCO));
	            Log.info("'ZoneCO' field is now visible. Page loaded successfully.");
	        } catch (TimeoutException e) {
	            Log.error("ZoneCO field not found. Page did not load completely.", e);
	            throw new RuntimeException("ZoneCO field not found. The page is not fully loaded. Please try again.", e);
	        }

	        Log.info("Final wait for spinner to disappear before proceeding...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	        Log.info("'Unassigned Accounts' tile clicked successfully, and the page loaded.");
	    }

	    // Method to select BCO value from 'Allocate To' dropdown
	    public void selectBCOFromAllocateTo() throws InterruptedException {
	    	Log.info("Initiating selection of BCO from 'Allocate To' dropdown...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        Log.info("Scrolling down to the bottom of the page...");
	        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

	        Thread.sleep(5000); // Consider replacing with explicit wait for better stability

	        try {
	            Log.info("Waiting for 'Allocate To' dropdown to become visible...");
	            WebElement allocateToDropdown = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfigRepo.allocateToDropdown)
	            );
	            
	            Log.info("Clicking on 'Allocate To' dropdown...");
	            allocateToDropdown.click();

	            Log.info("Waiting for dropdown value (BCO) to become visible...");
	            WebElement allocateToDropdownValue = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfigRepo.allocateToDropdownvalue)
	            );

	            Log.info("Selecting 'BCO' from the dropdown...");
	            allocateToDropdownValue.click();
	            
	            Log.info("BCO selection successful from 'Allocate To' dropdown.");
	        } catch (TimeoutException e) {
	            Log.error("Failed to locate 'Allocate To' dropdown or its value within the timeout.", e);
	            throw new RuntimeException("Dropdown element not found. Please check if the page is fully loaded.", e);
	        }
	    }

	    // Method to check if users are listed under BCO dropdown
	    public boolean areUsersListedUnderBCO() throws InterruptedException {
	    	Log.info("Verifying if users are listed under BCO...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        try {
	            // Fetch the username
	            Log.info("Retrieving the logged-in username...");
	            WebElement usernameElement = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfigRepo.username)
	            );
	            Username = usernameElement.getText().trim();
	            Log.info("Fetched Username: " + Username);

	            // Scroll to the dropdown
	            Log.info("Scrolling to the bottom of the page...");
	            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	            Thread.sleep(5000); // Consider using explicit waits instead of Thread.sleep

	            // Locate and click the dropdown
	            Log.info("Waiting for 'Select BCO' dropdown to be visible...");
	            WebElement selectBCODropdown = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfigRepo.selectBCODropdown)
	            );
	            Log.info("Clicking on 'Select BCO' dropdown...");
	            selectBCODropdown.click();

	            // Locate and select the user from the dropdown
	            Log.info("Waiting for user (" + Username + ") to appear in the dropdown...");
	            WebElement selectBCODropdownValue = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfigRepo.Branch_BCO_value(Username))
	            );

	            Log.info("Selecting user (" + Username + ") from the dropdown...");
	            selectBCODropdownValue.click();

	            // Verify if the correct username is displayed after selection
	            boolean isUserListed = selectBCODropdown.getText().trim().equals(Username);
	            Log.info("User " + (isUserListed ? "successfully" : "not") + " listed under BCO.");

	            return isUserListed;

	        } catch (TimeoutException e) {
	            Log.error("Timeout while waiting for an element. User not listed under BCO.", e);
	        } catch (NoSuchElementException e) {
	            Log.error("Element not found. Possibly an incorrect locator or missing data.", e);
	        } catch (Exception e) {
	            Log.error("Unexpected error occurred while checking users under BCO.", e);
	        }

	        return false; // Return false in case of any failure
	    }
	    
	 // Click on Branch Attention Required tile
	    public void clickBranchAttentionTile() {
	    	Log.info("Attempting to click on 'Branch Attention Required Accounts' tile...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        try {
	            // Wait for the tile to be clickable
	            WebElement branchAttentionTile = wait.until(
	                ExpectedConditions.elementToBeClickable(CoreCollectionOfficerConfigRepo.BranchAttentionRequiredAccounts));

	            Log.info("Branch Attention tile is clickable. Clicking now...");
	            branchAttentionTile.click();
	            Log.info("Successfully clicked on 'Branch Attention Required Accounts' tile.");

	        } catch (TimeoutException e) {
	            Log.error("Timeout: 'Branch Attention Required Accounts' tile did not become clickable within 180 seconds.", e);
	            throw new RuntimeException("Branch Attention tile not clickable.", e);
	        } catch (NoSuchElementException e) {
	            Log.error("Error: 'Branch Attention Required Accounts' tile not found on the page.", e);
	            throw new RuntimeException("Branch Attention tile not found.", e);
	        } catch (Exception e) {
	            Log.error("Unexpected error while clicking on 'Branch Attention Required Accounts' tile.", e);
	            throw new RuntimeException("Unexpected error while interacting with Branch Attention tile.", e);
	        }
	    }

	    // Click on Assign/Reassign dropdown
	    public void clickAssignReassignDropdown() throws InterruptedException {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        Log.info("Waiting for search button to be present...");
	        wait.until(ExpectedConditions.presenceOfElementLocated(CoreCollectionOfficerConfigRepo.searchbutton));

	        Log.info("Waiting for search button to become disabled...");
	        wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(CoreCollectionOfficerConfigRepo.searchbutton)));

	        WebElement searchbutton = driver.findElement(CoreCollectionOfficerConfigRepo.searchbutton);
	        if (!searchbutton.isEnabled()) {
	            Log.info("Search button is correctly disabled.");
	        } else {
	            Log.error("Search button is not disabled. The page might not be fully loaded.");
	            throw new RuntimeException("Search button is not disabled. Try again.");
	        }

	        Log.info("Waiting for spinner to disappear...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	        Log.info("Scrolling to the bottom of the page...");
	        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	        Thread.sleep(5000);

	        Log.info("Waiting for spinner to disappear again...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	        Log.info("Waiting for Assign/Reassign dropdown to be clickable...");
	        wait.until(ExpectedConditions.elementToBeClickable(CoreCollectionOfficerConfigRepo.AssignReassignTodropdown));

	        Log.info("Clicking Assign/Reassign dropdown...");
	        driver.findElement(CoreCollectionOfficerConfigRepo.AssignReassignTodropdown).click();
	        Log.info("Assign/Reassign dropdown clicked successfully.");

	    }

	    // Get the text of listed users in the dropdown
	    public boolean getListedUsers() {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	        try {
	            Log.info("Fetching Username: " + Username);

	            Log.info("Waiting for 'Assign/Reassign To' dropdown value to be visible for user: " + Username);
	            WebElement Branch_BCO_AssignReassignTo_value = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfigRepo.AssignReassignTo_value(Username))
	            );

	            Log.info("Clicking 'Assign/Reassign To' dropdown value...");
	            Branch_BCO_AssignReassignTo_value.click();
	            Log.info("Clicked successfully.");

	            Log.info("Waiting for 'Assign/Reassign To' dropdown to be visible...");
	            WebElement AssignReassignTodropdown = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfigRepo.AssignReassignTodropdown)
	            );

	            boolean isUserListed = AssignReassignTodropdown.getText().trim().equals(Username);
	            Log.info("Verification result: " + isUserListed);

	            return isUserListed;
	        } catch (TimeoutException | NoSuchElementException e) {
	            Log.error("Failed to find the expected element. Returning false.", e);
	            return false;
	        }
        }
	    
	    public void clickConfigurationMenu() {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for the spinner to disappear before clicking 'Configuration' menu...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        
	        Log.info("Waiting for 'Configuration' menu to be visible...");
	        WebElement Configurationsmainmenu = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfigRepo.Configurationsmainmenu)
	        );

	        Log.info("Clicking on 'Configuration' menu...");
	        Configurationsmainmenu.click();
	        Log.info("'Configuration' menu clicked successfully.");
	    }
	    
	    public boolean getListedUser() {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for the dropdown options to become visible...");
	        
	        List<WebElement> options = new ArrayList<>();
	        try {
	            options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(CoreCollectionOfficerConfigRepo.AssignReassignTo_Present_values));
	        } catch (TimeoutException e) {
	            Log.warn("No elements found in the dropdown within the timeout.");
	            return false;
	        }

	        Log.info("Dropdown options retrieved. Checking for username: " + Username);

	        // Check if the username is present in the dropdown options
	        for (WebElement option : options) {
	            Log.debug("Checking option: " + option.getText().trim());
	            if (option.getText().trim().equalsIgnoreCase(Username)) {
	                Log.info("Username found: " + Username);
	                return true; // Username exists in the dropdown
	            }
	        }

	        Log.warn("Username not found: " + Username);
	        return false; // Username not found
	    }
	    // Method to click on Download in Excel button
	    public void clickDownloadExcelButton() throws InterruptedException {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	        
	        Log.info("Waiting for spinner to disappear before proceeding.");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        
	        Log.info("Scrolling to the bottom of the page.");
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	        
	        Thread.sleep(5000); // Consider replacing this with an explicit wait for better stability
	        
	        Log.info("Ensuring the spinner is not visible before clicking the download button.");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	        Log.info("Waiting for the Download Excel button to be clickable.");
	        WebElement downloadButton = wait.until(ExpectedConditions.elementToBeClickable(CoreCollectionOfficerConfigRepo.DownloadinExcelbutton));
	        
	        Log.info("Clicking the Download Excel button.");
	        downloadButton.click();

	        Log.info("Waiting for the success message to appear.");
	        wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfigRepo.DownloadinExcelsuccessmsg));

	        Log.info("Waiting for the success message to disappear.");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreCollectionOfficerConfigRepo.DownloadinExcelsuccessmsg));

	        Log.info("Ensuring spinner is no longer visible after download action.");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	        Log.info("Excel download process completed successfully.");
	    }
	    
	    public void clickSaveConfigurationButton() {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for the Save Configuration button to be visible.");
	        WebElement saveConfigurationButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfigRepo.SaveConfigurationbutton));

	        Log.info("Clicking the Save Configuration button.");
	        saveConfigurationButton.click();

	        Log.info("Save Configuration button clicked successfully.");
	    }
	    
	 // Method to retrieve error message text
	    public String getErrorMessage() {
	    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	    	    Log.info("Waiting for the Save Configuration warning message to be visible.");
	    	    WebElement saveConfigurationWarningMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfigRepo.Saveconfigurationwarningmsg));

	    	    String errorMessage = saveConfigurationWarningMsg.getText();
	    	    Log.info("Captured error message: " + errorMessage);

	    	    return errorMessage;
	    }

}
