package core.SummaryReports;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.BasePackage.DBUtils;
import com.BasePackage.DownloadedExcelReader;
import com.BasePackage.PropertiesFileUtil;
import com.Page_Repository.BankAllocationSummaryRepo;
import com.Page_Repository.BankRegularizationSummaryRepo;
import com.Page_Repository.CoreAlertsPlaceholderManagementRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Utility.Log;

public class BankRegularizationSummary_MainClass { 
	
private WebDriver driver;
	
	public BankRegularizationSummary_MainClass(WebDriver driver) {  
        
        this.driver = driver;
        
        PageFactory.initElements(driver, this);
        
    }
	
	public void navigateToBankRegularizationSummary() throws InterruptedException { 
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        WebElement SummaryReports = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.SummaryReports));

        SummaryReports.click();

        WebElement bankRegularizationSummaryLink = wait.until(ExpectedConditions.visibilityOfElementLocated(BankRegularizationSummaryRepo.bankRegularizationSummaryLink)); 

        bankRegularizationSummaryLink.click();
        
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.Branch));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }
	
	public List<Object> removeACMovementForMyDeskDashboard(String acMovementId) throws IOException {
	    // Prepare input and output parameters
	    List<Object> inputParams = Arrays.asList(acMovementId);
	    List<Integer> outputTypes = Arrays.asList(Types.VARCHAR, Types.VARCHAR, Types.VARCHAR);

	    // Call the stored procedure
	    List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure(
	        "SP_INSERT_TRN_AC_MOVEMENT_REMOVED_ForMyDeskDashBoard",
	        inputParams,
	        outputTypes
	    );

	    // Optionally print results (can remove if not needed)
	    System.out.println("Removed entry AC_MOVEMENT DATE: " + results.get(0));
	    System.out.println("Status Message: " + results.get(1));

	    // Return the results
	    return results;
	}
	
	public List<Object> runBranchRegularizationDashboardLoad() throws IOException {
	    // Input parameters (empty string in this case)
	    List<Object> inputParams = null; // No input parameters

	    // Output parameter types
	    List<Integer> outputTypes = Arrays.asList(Types.VARCHAR);

	    // Execute the stored procedure
	    List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure(
	        "SP_RUN_PACKAGE_ALLOCATION_DASHBOARD_DATA_LOAD_PKG_SPPROCESSBRANCHREGULARIZATIONDASHBOARD",
	        inputParams,
	        outputTypes
	    );

	    // Optionally print result (can be removed)
	    System.out.println("Procedure Execution Result: " + results.get(0));

	    // Return the result
	    return results;
	}
	
	public List<Object> checkBranchRegularizationDashboardData(String branchCode) throws IOException {
	    // Input parameters
	    List<Object> inputParams = Arrays.asList(branchCode);

	    // Output parameter types
	    List<Integer> outputTypes = Arrays.asList(Types.VARCHAR, Types.VARCHAR);

	    // Execute the stored procedure
	    List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure(
	        "SP_CHECK_ALLOCATION_DASHBOARD_DATA_LOAD_PKG_SPPROCESSBRANCHREGULARIZATIONDASHBOARD_DATA",
	        inputParams,
	        outputTypes
	    );

	    // Optionally print the result
	    System.out.println("DW_BRANCH_REGULARIZATION_SUMMARY table entry: " + results.get(0));
	    System.out.println("DW_BRANCH_REGULARIZATION_DETAILS table entry: " + results.get(1));

	    // Return the result
	    return results;
	}
	
	 // Method to select a region
    public void selectRegion(String region) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement Region = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.Region));
        Region.click();
        WebElement Dropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(BankRegularizationSummaryRepo.Dropdownvalues(region)));
        Dropdownvalues.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }

    // Method to select a branch
    public void selectBranch(String branch) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement Branch = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.Branch));
        Branch.click();
        WebElement branchsearchspace = wait.until(ExpectedConditions.visibilityOfElementLocated(BankRegularizationSummaryRepo.branchsearchspace));
        branchsearchspace.sendKeys(branch);
        WebElement Dropdownvaluesforbranch = wait.until(ExpectedConditions.visibilityOfElementLocated(BankRegularizationSummaryRepo.Dropdownvaluesforbranch(branch)));
        Dropdownvaluesforbranch.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }

    // Method to set 'From Date'
    public void setFromDate(String date) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement FromDate = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.FromDate));
        FromDate.click();
        WebElement DateDropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.DateDropdownvalues(date)));
        DateDropdownvalues.click();
    }

    // Method to set 'To Date'
    public void setToDate(String date) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement ToDate = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.ToDate));
        ToDate.click();
        WebElement DateDropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(BankRegularizationSummaryRepo.DateDropdownvalues(date)));
        DateDropdownvalues.click();
    }

    // Method to click the search button
    public void clickSearch() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(BankRegularizationSummaryRepo.searchButton));
        searchButton.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }

    // Method to get the number of accounts after search
    public String getdetailsfromgrid() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        
        // Wait for all matching elements to be visible (not just one!)
        List<WebElement> gridElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(BankRegularizationSummaryRepo.gridvalues));

        // Limit to 6 elements and collect their text
        List<String> texts = gridElements.stream()
            .limit(6)
            .map(WebElement::getText)
            .collect(Collectors.toList());

        // Join the texts with commas (or customize this)
        return String.join(", ", texts);
        
    }


}
