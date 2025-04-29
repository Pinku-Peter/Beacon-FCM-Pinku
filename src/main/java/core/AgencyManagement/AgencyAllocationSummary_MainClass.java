package core.AgencyManagement;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Types;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.BasePackage.DBUtils;
import com.BasePackage.DownloadedExcelReader;
import com.Page_Repository.AgencyAllocationSummaryRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Utility.Log;

public class AgencyAllocationSummary_MainClass {
	
private WebDriver driver;
	
	public AgencyAllocationSummary_MainClass(WebDriver driver) {  
		Log.info("Initializing AgencyAllocationSummary_MainClass...");

        this.driver = driver;

        Log.info("Initializing WebElements using PageFactory...");
        PageFactory.initElements(driver, this);

        Log.info("AgencyAllocationSummary_MainClass initialized successfully.");
    }
	
	 public boolean truncateTable() throws ClassNotFoundException, SQLException, IOException { 
		 Log.info("Starting truncateTable method...");

	        String query = "TRUNCATE TABLE mst_col_agency_acc_allocated";
	        Log.info("Executing SQL query: " + query);
	        String result = DBUtils.executeSQLStatement(query);

	        Log.info("SQL execution result: " + result);
	        System.out.println("Execution Result: " + result);

	        // Check if the result indicates successful truncation
	        if ("TRUNCATE command executed successfully.".equalsIgnoreCase(result.trim())) {
	            Log.info("Table truncated successfully.");
	            return true;
	        } else {
	            Log.warn("Table truncation failed.");
	            return false;
	        } 
	 }
	 
	 public List<Object> generateZoneUserDetails(String userId) throws IOException {  
		 Log.info("Starting generateZoneUserDetails method...");

	        Log.info("Preparing input parameters for stored procedure...");
	        List<Object> inputParams = Arrays.asList(
	            userId,              // userId passed as parameter
	            "Zone",
	            "Mumbai",
	            "",
	            "Niyas",
	            "niya@example.com",
	            "9876543210"
	        );

	        Log.info("Defining output parameter types...");
	        List<Integer> outputTypes = Arrays.asList(
	            Types.VARCHAR,  // User org checking
	            Types.VARCHAR,  // Zone UserID
	            Types.VARCHAR,  // Password
	            Types.VARCHAR   // Status Message
	        );

	        Log.info("Calling stored procedure: SP_ZONE_USERID_GENERATOR_WITH_MULTIPLE_VALIDATIONS");
	        List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure(
	            "SP_ZONE_USERID_GENERATOR_WITH_MULTIPLE_VALIDATIONS",
	            inputParams,
	            outputTypes
	        );

	        Log.info("Stored procedure executed. Processing results...");
	        System.out.println("User org checking: " + results.get(0));
	        System.out.println("Zone UserID: " + results.get(1));
	        System.out.println("Password: " + results.get(2));
	        System.out.println("Status Message: " + results.get(3));

	        Log.info("generateZoneUserDetails method completed successfully.");
	        return results; 
	    }
	 
	 public void navigateToAgencyAccountAllocation() throws InterruptedException {
		 Log.info("Starting navigation to Agency Account Allocation page...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for Collection Agency menu to be visible...");
	        WebElement collectionAgencyMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(AgencyAllocationSummaryRepo.collectionAgencyMenu));
	        Log.info("Clicking on Collection Agency menu...");
	        collectionAgencyMenu.click();

	        Log.info("Waiting for Agency Account Allocation menu to be visible...");
	        WebElement agencyAccountAllocationMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(AgencyAllocationSummaryRepo.agencyAccountAllocationMenu));
	        Log.info("Clicking on Agency Account Allocation menu...");
	        agencyAccountAllocationMenu.click();

	        Log.info("Waiting for Zone/CO dropdown field to be visible...");
	        try {
	            wait.until(ExpectedConditions.visibilityOfElementLocated(AgencyAllocationSummaryRepo.Zone_CO_dropdown_field));
	            Log.info("Zone/CO dropdown field is visible.");
	        } catch (Exception e) {
	            Log.error("Zone/CO dropdown field is not visible. Page did not load properly.");
	            throw new RuntimeException("The Zone/CO dropdown field is not displayed; hence, the page did not load properly. Please try again.", e);
	        }

	        Log.info("Waiting for any loading spinners to disappear...");
	        Thread.sleep(10000); 
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	        Log.info("Navigation to Agency Account Allocation page completed successfully.");
	    }
	 
	// Method to truncate 'mst_col_agency_acc_allocated' table
	    public String truncate_Table() throws SQLException, ClassNotFoundException, IOException {
	    	Log.info("Initializing truncate_Table method...");

	        String truncateQuery = "TRUNCATE TABLE mst_col_agency_acc_allocated";
	        Log.info("Preparing to execute SQL query: " + truncateQuery);

	        try {
	            Log.info("Executing SQL truncate query...");
	            String result = DBUtils.executeSQLStatement(truncateQuery);
	            Log.info("SQL query executed successfully. Table truncated.");
	            Log.info("truncate_Table method completed successfully.");
	            return result;
	        } catch (SQLException e) {
	            Log.error("An error occurred while truncating the table: " + e.getMessage());
	            throw new SQLException("Error occurred while truncating table", e);
	        }
	    }

	    // Method to delete records from 'trn_account_followup' where disposition_date is today
	    public String deleteRecordsWhereDispositionDateIsToday() throws SQLException, ClassNotFoundException, IOException {
	    	Log.info("Initializing deleteRecordsWhereDispositionDateIsToday method...");

	        String deleteQuery = "DELETE FROM trn_account_followup WHERE disposition_date = CURRENT_DATE";
	        Log.info("Preparing to execute SQL delete query: " + deleteQuery);

	        try {
	            Log.info("Executing SQL delete query...");
	            String result = DBUtils.executeSQLStatement(deleteQuery);
	            Log.info("SQL query executed successfully. Records deleted.");
	            Log.info("deleteRecordsWhereDispositionDateIsToday method completed successfully.");
	            return result;
	        } catch (SQLException e) {
	            Log.error("An error occurred while deleting records: " + e.getMessage());
	            throw new SQLException("Error occurred while deleting records", e);
	        }
	    }
	    
	    public String getAgencyName(String id) throws IOException {
	    	Log.info("Initializing getAgencyName method...");

	        Log.info("Preparing input and output parameters for stored procedure...");
	        List<Object> inputParams = Arrays.asList(id); 
	        List<Integer> outputTypes = Arrays.asList(Types.VARCHAR, Types.VARCHAR, Types.VARCHAR);

	        Log.info("Calling stored procedure: SP_QA_GET_COLLECTION_AGENCY_INFO");
	        List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure(
	            "SP_QA_GET_COLLECTION_AGENCY_INFO",
	            inputParams,
	            outputTypes
	        );

	        Log.info("Stored procedure executed successfully. Extracting agency name...");
	        String agencyName = (String) results.get(1);  // Index 1 corresponds to the agency name

	        Log.info("Agency name retrieved: " + agencyName);
	        Log.info("getAgencyName method completed successfully.");
	        return agencyName; 
	    }
	 
	 public void performSearch(String accountType, String smaCategory, String npaCategory,
             String region, String dpdFinOp, String dpdDays)  
	 {
		 Log.info("Starting performSearch method...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Selecting Account Type: " + accountType);
	        WebElement accountTypeDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(AgencyAllocationSummaryRepo.accountTypeDropdown));
	        accountTypeDropdown.click();
	        WebElement accountTypeDropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(AgencyAllocationSummaryRepo.accountType_CollectionAgencyDropdownvalues(accountType)));
	        accountTypeDropdownvalues.click();
	        Log.info("Account Type selected.");

	        Log.info("Selecting Asset Category: All values checkbox");
	        WebElement assetCategoryDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(AgencyAllocationSummaryRepo.assetCategoryDropdown));
	        assetCategoryDropdown.click();
	        WebElement assetCategoryAllvaluesCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(AgencyAllocationSummaryRepo.assetCategoryAllvaluesCheckbox));
	        assetCategoryAllvaluesCheckbox.click();
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        Log.info("Asset Category selected.");

	        Log.info("Selecting SMA Category: " + smaCategory);
	        WebElement smaCategoryDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(AgencyAllocationSummaryRepo.smaCategoryDropdown));
	        smaCategoryDropdown.click();
	        WebElement smaCategory_Dropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(AgencyAllocationSummaryRepo.smaCategory_npaCategory_region_Dropdownvalues(smaCategory)));
	        smaCategory_Dropdownvalues.click();
	        Log.info("SMA Category selected.");

	        Log.info("Selecting NPA Category: " + npaCategory);
	        WebElement npaCategoryDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(AgencyAllocationSummaryRepo.npaCategoryDropdown));
	        npaCategoryDropdown.click();
	        WebElement npaCategory_Dropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(AgencyAllocationSummaryRepo.smaCategory_npaCategory_region_Dropdownvalues(npaCategory)));
	        npaCategory_Dropdownvalues.click();
	        Log.info("NPA Category selected.");

	        Log.info("Selecting Region: " + region);
	        WebElement regionDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(AgencyAllocationSummaryRepo.regionDropdown));
	        regionDropdown.click();
	        WebElement region_Dropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(AgencyAllocationSummaryRepo.smaCategory_npaCategory_region_Dropdownvalues(region)));
	        region_Dropdownvalues.click();
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        Log.info("Region selected.");

	        Log.info("Selecting DPD Days FinOp: " + dpdFinOp);
	        WebElement dpdDaysFinOpDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(AgencyAllocationSummaryRepo.dpdDaysFinOpDropdown));
	        dpdDaysFinOpDropdown.click();
	        WebElement dpdDaysFinOp_Dropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(AgencyAllocationSummaryRepo.dpdDaysFinOp_Dropdownvalues(dpdFinOp)));
	        dpdDaysFinOp_Dropdownvalues.click();
	        Log.info("DPD Days FinOp selected.");

	        Log.info("Entering DPD Days: " + dpdDays);
	        WebElement dpdDaysTextbox = wait.until(ExpectedConditions.visibilityOfElementLocated(AgencyAllocationSummaryRepo.dpdDaysTextbox));
	        dpdDaysTextbox.clear();
	        dpdDaysTextbox.sendKeys(dpdDays);
	        Log.info("DPD Days entered.");

	        Log.info("Clicking Search button...");
	        WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(AgencyAllocationSummaryRepo.searchButton));
	        searchButton.click();
	        Log.info("Search initiated.");

	        Log.info("Waiting for search results to appear...");
	        wait.until(ExpectedConditions.visibilityOfElementLocated(AgencyAllocationSummaryRepo.totalAccountsSelected));
	        wait.until(ExpectedConditions.visibilityOfElementLocated(AgencyAllocationSummaryRepo.totalOutstandingAmount));
	        Log.info("Search results loaded successfully.");

	        Log.info("performSearch method completed successfully.");
}
	 
	 public void selectCollectionAgency(String agencyname) throws InterruptedException {
		 Log.info("Initializing selectCollectionAgency method...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        Log.info("Scrolling to bottom of the page...");
	        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	        Thread.sleep(5000);
	        Log.info("Scroll completed.");

	        Log.info("Waiting for and clicking on 'Select Collection Agency' dropdown...");
	        WebElement selectCollectionAgencyDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(AgencyAllocationSummaryRepo.selectCollectionAgencyDropdown));
	        selectCollectionAgencyDropdown.click();
	        Log.info("'Select Collection Agency' dropdown clicked.");

	        Log.info("Entering agency name into search field: " + agencyname);
	        WebElement selectCollectionAgencyDropdownSearchfield = wait.until(ExpectedConditions.visibilityOfElementLocated(AgencyAllocationSummaryRepo.selectCollectionAgencyDropdownSearchfield));
	        selectCollectionAgencyDropdownSearchfield.clear();
	        selectCollectionAgencyDropdownSearchfield.sendKeys(agencyname);
	        Log.info("Agency name entered.");

	        Log.info("Selecting agency from dropdown: " + agencyname);
	        WebElement accountType_CollectionAgencyDropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(AgencyAllocationSummaryRepo.accountType_CollectionAgencyDropdownvalues(agencyname)));
	        accountType_CollectionAgencyDropdownvalues.click();
	        Log.info("Agency selected from dropdown.");

	        Log.info("selectCollectionAgency method completed successfully.");
	    }

	 public Map<String, Object> downloadResults() throws IOException {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for visibility of Download button...");
	        WebElement downloadButton = wait.until(ExpectedConditions.visibilityOfElementLocated(AgencyAllocationSummaryRepo.downloadButton));
	        Log.info("Download button is visible. Clicking the button...");
	        downloadButton.click();
	        Log.info("Download button clicked.");

	        Log.info("Waiting for download success message to appear...");
	        wait.until(ExpectedConditions.visibilityOfElementLocated(AgencyAllocationSummaryRepo.downloadSuccessMessage));
	        Log.info("Download success message appeared.");

	        Log.info("Waiting for download success message to disappear...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(AgencyAllocationSummaryRepo.downloadSuccessMessage));
	        Log.info("Download success message disappeared.");

	        Log.info("Pausing for a few seconds to ensure file is saved...");
	        try {
	            Thread.sleep(3000); // Adjust if needed
	        } catch (InterruptedException e) {
	            Log.error("Thread sleep interrupted: " + e.getMessage());
	            e.printStackTrace();
	        }
	        Log.info("File save pause completed.");

	        Log.info("Reading downloaded Excel to extract account details...");
	        Map<String, Object> accountDetails = DownloadedExcelReader.getAccountNumberDetails();
	        Log.info("Account details extracted successfully from downloaded Excel.");

	        Log.info("downloadResults method completed successfully.");
	        return accountDetails; 
		}

	    public void allocateAccounts() {
	    	Log.info("Starting allocateAccounts method...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for visibility of 'Allocate' button...");
	        WebElement allocateButton = wait.until(ExpectedConditions.visibilityOfElementLocated(AgencyAllocationSummaryRepo.allocateButton));
	        Log.info("'Allocate' button is visible. Clicking the button...");
	        allocateButton.click();
	        Log.info("'Allocate' button clicked.");

	        Log.info("Switching to alert and accepting...");
	        driver.switchTo().alert().accept();
	        Log.info("Alert accepted.");

	        Log.info("allocateAccounts method completed successfully.");
	    }

	    public String getSuccessMessage() {
	    	Log.info("Starting getSuccessMessage method...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for visibility of success alert...");
	        WebElement successAlert = wait.until(ExpectedConditions.visibilityOfElementLocated(AgencyAllocationSummaryRepo.successAlert));
	        Log.info("Success alert is visible. Extracting text...");

	        String successMessage = successAlert.getText();
	        Log.info("Success message extracted: " + successMessage);

	        Log.info("getSuccessMessage method completed successfully.");
	        return successMessage;  
	    }
	    
	    public boolean validateNewEntriesPresent(String userId) { 
	    	Log.info("Starting validateNewEntriesPresent method...");

	        try {
	            Log.info("Preparing input parameters for stored procedure: SP_INSERT_TRN_AC_MOVEMENT_FROM_MST_COL_AGENCY_ACC_ALLOCATED_TABLE");
	            List<Object> inputParams = Arrays.asList(userId);
	            List<Integer> outputTypes = Arrays.asList(Types.VARCHAR);

	            Log.info("Calling stored procedure...");
	            List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure(
	                "SP_INSERT_TRN_AC_MOVEMENT_FROM_MST_COL_AGENCY_ACC_ALLOCATED_TABLE",
	                inputParams,
	                outputTypes
	            );
	            Log.info("Stored procedure executed successfully.");

	            String actualMessage = results.get(0).toString().trim();
	            String expectedMessage = "Accounts inserted into TRN_AC_MOVEMENT with  MOVEMENT TYPE=1 and MOVEMENT_TYPE_CATEGORY=NEW";

	            Log.info("Status Message: " + actualMessage);

	            Log.info("Comparing actual message with expected message...");
	            boolean isValid = actualMessage.equals(expectedMessage);
	            Log.info("Validation result: " + isValid);

	            return isValid;
	        } catch (Exception e) {
	            Log.error("Error occurred while validating new entries: " + e.getMessage());
	            e.printStackTrace();
	            return false;
	        } finally {
	            Log.info("validateNewEntriesPresent method completed.");
	        }
	    }
	    
	 // Method to click on agency management
	    public void clickAgencyManagement() {
	    	Log.info("Starting clickAgencyManagement method...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for visibility of 'Agency Management' button...");
	        WebElement agencyManagementBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(AgencyAllocationSummaryRepo.agencyManagementBtn));
	        Log.info("'Agency Management' button is visible. Clicking the button...");

	        agencyManagementBtn.click();
	        Log.info("'Agency Management' button clicked.");

	        Log.info("clickAgencyManagement method completed successfully.");
	    }

	    // Method to click on allocation summary
	    public void clickAllocationSummary() {
	    	Log.info("Starting clickAllocationSummary method...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for visibility of 'Allocation Summary' button...");
	        WebElement allocationSummaryBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(AgencyAllocationSummaryRepo.allocationSummaryBtn));
	        Log.info("'Allocation Summary' button is visible. Clicking the button...");
	        allocationSummaryBtn.click();
	        Log.info("'Allocation Summary' button clicked.");

	        Log.info("Waiting for the spinner to disappear...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        Log.info("Spinner disappeared. Allocation summary page is now fully loaded.");

	        Log.info("clickAllocationSummary method completed successfully.");
	    }
	    
	    // Method to click the Search button
	    public void clickSearchButton() {
	    	Log.info("Starting clickSearchButton method...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for visibility of 'Search' button...");
	        WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(AgencyAllocationSummaryRepo.searchButton));
	        Log.info("'Search' button is visible. Clicking the button...");
	        searchButton.click();
	        Log.info("'Search' button clicked.");

	        Log.info("clickSearchButton method completed successfully.");
	    }

	    // Method to get the validation message text
	    public String getValidationMessage() {
	    	Log.info("Starting getValidationMessage method...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for visibility of the 'Validation Message' element...");
	        WebElement validationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(AgencyAllocationSummaryRepo.validationMessage));
	        Log.info("'Validation Message' element is visible. Retrieving the message...");

	        String message = validationMessage.getText();
	        Log.info("Validation Message retrieved: " + message);

	        Log.info("getValidationMessage method completed successfully.");
	        return message; 
	    }

}
