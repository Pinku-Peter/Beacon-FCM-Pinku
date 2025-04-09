package core.SummaryReports;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.BasePackage.DBUtils;
import com.BasePackage.PropertiesFileUtil;
import com.Page_Repository.BankAllocationSummaryRepo;
import com.Page_Repository.CoreAlertsMaskAcAndMaskingAuthoRepo;
import com.Page_Repository.CoreAlertsNoticeTypeMa_NoticeAcRepo;
import com.Page_Repository.CoreAlertsPlaceholderManagementRepo;
import com.Page_Repository.CoreAlertsTemplateManagementRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Utility.Log;
import io.netty.handler.timeout.TimeoutException;

public class BankAllocationSummary_MainClass {
	
private WebDriver driver;
	
	public BankAllocationSummary_MainClass(WebDriver driver) {  
		
	    
	    this.driver = driver;  
	 
	    PageFactory.initElements(driver, this);  

	    
    }
	
	public List<Object> deleteZoneUserToBranchUserAccountAllocation(String branchname, String branch, String userId) throws IOException {
	    List<Object> inputParams = Arrays.asList(branchname, branch, userId);
	    List<Integer> outputTypes = Arrays.asList(Types.VARCHAR);
	    List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure("SP_DeleteDataOfZoneUserToBranchUserAccountAllocation",inputParams,outputTypes );
	    return results; 
	}
	
	public List<Object> insertBCOUser(String userid) throws IOException { 
		List<Object> inputParams = Arrays.asList(userid); 
	    List<Integer> outputTypes = Arrays.asList(Types.VARCHAR);
	    List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure("SP_InsertBCOUserIn_MSTEMPLOYEE", inputParams, outputTypes);
	    return results;
	}
	
	// Methods for interacting with the My Desk Page
    public void selectSmaTile() throws InterruptedException {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	WebElement alertsAndNotificationsMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsPlaceholderManagementRepo.alertsAndNotificationsMenu));
    	JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", alertsAndNotificationsMenu);
        Thread.sleep(5000);
        WebElement MyDesk = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.MyDesk));
        MyDesk.click();
        WebElement Dashboard = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.Dashboard));
        Dashboard.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
         Thread.sleep(5000);
         WebElement UnassignedAccounts = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.UnassignedAccounts));
         UnassignedAccounts.click();  
         wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
         wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.Download));
         wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
         WebElement Reset = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.Reset));
         Reset.click();
         wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }

    public void selectAssetCategory(String category) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	WebElement AssetCategory = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.AssetCategory));
    	AssetCategory.click();
    	WebElement Dropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.Dropdownvalues(category)));
    	Dropdownvalues.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }

    public void selectSmaCategory(String category) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	WebElement SMACategory = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.SMACategory));
    	SMACategory.click();
    	WebElement Dropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.Dropdownvalues(category)));
    	Dropdownvalues.click();
    }

    public void selectRegion(String region) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	WebElement Region = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.Region));
    	Region.click();
    	WebElement Dropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.Dropdownvalues(region)));
    	Dropdownvalues.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }

    public void selectBranch(String name) { 
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	WebElement Branch = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.Branch));
    	Branch.click();
    	WebElement Branchsearchspace = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.Branchsearchspace));
    	Branchsearchspace.clear();
    	Branchsearchspace.sendKeys(name); 
    	WebElement Dropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.Dropdownvalues(name)));
    	Dropdownvalues.click();
    }

    public void selectDpdFinancialOperation(String operator , String amount) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	WebElement OsBalance_Operatorsdropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.OsBalance_Operatorsdropdown));
    	OsBalance_Operatorsdropdown.click();
    	WebElement OsBalance_OperatorsdropdownDropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.OsBalance_OperatorsdropdownDropdownvalues(operator)));
    	OsBalance_OperatorsdropdownDropdownvalues.click();
    	WebElement OsBalance_textamountfield = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.OsBalance_textamountfield));
    	OsBalance_textamountfield.clear();
    	OsBalance_textamountfield.sendKeys(amount);
    }

    public void clickSearch() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	WebElement Search = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.Search));
    	Search.click();
    	wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.Download));
    }

    public void selectAllocateToBranch(String branch) throws InterruptedException {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(5000);
        WebElement AllocateTo = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.AllocateTo));
        AllocateTo.click();
    	WebElement AllocateToDropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.AllocateToDropdownvalues(branch)));
    	AllocateToDropdownvalues.click();
    }

    public void clickAllocate() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	WebElement Allocate = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.Allocate));
    	Allocate.click();
    }

    public boolean isConfirmationMessageDisplayed() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	WebElement Allocationsuccessmessage = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.Allocationsuccessmessage));
        return Allocationsuccessmessage.isDisplayed();
    }
	
	public List<Object> createBranchUser() throws IOException, InterruptedException, ClassNotFoundException, SQLException {

	    Log.info("Reading Branch User Credentials from properties file...");

	    String loginUserId; 
	    String loginPassword;
	    String statusMessage;
	    
	    String fileName = "CoreBranchUserCredentials_CoreUserManagement_Branch_User_Creation.properties";
	    Properties properties = PropertiesFileUtil.ReadFromPropertiesFile(fileName);
	    loginUserId = properties.getProperty("Branch_User_ID");

	    Log.info("Initializing input parameters for stored procedure...");
	    List<Object> inputParams = Arrays.asList(
	    	loginUserId,                   // User ID to be generated by the SP
	        "Amaravati",          // Branch name or location
	        "Manal metha",        // User full name
	        "metha@example.com",  // Email
	        1122889900            // Contact number
	    );

	    List<Integer> outputTypes = Arrays.asList(Types.VARCHAR, Types.VARCHAR, Types.VARCHAR);

	    Log.info("Executing stored procedure: SP_CoreBranchUserIDGenerator...");
	    List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure(
	        "SP_CoreBranchUserIDGenerator",
	        inputParams,
	        outputTypes
	    );

	    if (results == null || results.size() < 2) {
	        Log.warn("Stored procedure returned null or incomplete results.");
	        return results;
	    }

	    loginUserId = (String) results.get(0);
	    loginPassword = (String) results.get(1);
	    statusMessage = (String) results.get(2); // Optional logging

	    Log.info("Updating properties file with generated Branch user credentials...");
	    Map<String, String> updates = new HashMap<>();
	    updates.put("Branch_User_ID", loginUserId);
	    updates.put("Branch_User_Password", loginPassword);

	    if (properties.isEmpty()) {
	        Log.info("Properties file is empty, updating with new credentials...");
	        PropertiesFileUtil.updateProperties(fileName, updates);
	    } else {
	        Log.info("Properties file already contains values. Skipping update.");
	        loginUserId = properties.getProperty("Branch_User_ID");
	        loginPassword = properties.getProperty("Branch_User_Password");
	    }

	    Log.info("Branch User ID: " + loginUserId);
	    Log.info("Password: " + loginPassword);
	    Log.info("Status message: " + results.get(2));

	    Log.info("Returning generated credentials...");
	    return Arrays.asList(loginUserId, loginPassword, statusMessage);
	}
	
	public void navigateToMyDesk() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	WebElement alertsAndNotificationsMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsPlaceholderManagementRepo.alertsAndNotificationsMenu));
    	JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", alertsAndNotificationsMenu);
        Thread.sleep(5000);
        WebElement MyDesk = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.MyDesk));
        MyDesk.click();
        WebElement Dashboard = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.Dashboard));
        Dashboard.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }
	
	public void selectSmaFromBranchAttention() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	WebElement BranchAttentionRequiredAccounts = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.BranchAttentionRequiredAccounts));
    	BranchAttentionRequiredAccounts.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.Pagination));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }
	
	 public void selectAllAccountsFromFirstPage() throws InterruptedException {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		 JavascriptExecutor js = (JavascriptExecutor) driver;
	    	js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	        Thread.sleep(5000);
	    	WebElement Selectallfromgrid = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.Selectallfromgrid));
	    	Selectallfromgrid.click();
	    }
	 
	 public void assignToBranch(String username) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		 WebElement Assign_ReassignTo = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.Assign_ReassignTo));
		 Assign_ReassignTo.click();
		 WebElement Assign_ReassignTosearchspace = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.Assign_ReassignTosearchspace));
		 Assign_ReassignTosearchspace.clear();
		 Assign_ReassignTosearchspace.sendKeys(username); 
		 WebElement AllocateTo_and_AssignReassignToDropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.AssignReassignToDropdownvalues(username)));
		 AllocateTo_and_AssignReassignToDropdownvalues.click();
	    }
	    
	    public void clickAssign() {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
			 WebElement Assign = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.Assign));
			 Assign.click();
	    }
	    
	    public boolean isConfirmationDisplayed() {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
			 WebElement Assignsuccessmessage = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.Assignsuccessmessage));
	        return Assignsuccessmessage.isDisplayed();
	    }
	    
	    public List<String> getAccountNumbersFromGrid() {
	        List<WebElement> accountNumberElements = driver.findElements(BankAllocationSummaryRepo.AccountNumbersfromGrid);
	        List<String> accountNumbers = new ArrayList<>();

	        for (WebElement element : accountNumberElements) {
	            accountNumbers.add(element.getText().trim());
	        }

	        return accountNumbers;
	    } 
	    
	    public String insertAccountsForUser(String userId) throws IOException { 
	        // Prepare input parameters (here just userId)
	        List<Object> inputParams = Arrays.asList(userId);

	        // Prepare output parameters (expecting a VARCHAR status message)
	        List<Integer> outputTypes = Arrays.asList(Types.VARCHAR);

	        // Call the stored procedure
	        List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure("SP_INSERT_TRN_AC_MOVEMENT_ForMyDeskDashBoard",inputParams,outputTypes);

	        // Return the status message
	        return results.get(0).toString();
	    }
	    
	    public void openAllocationSummaryWindow() throws InterruptedException {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    	WebElement alertsAndNotificationsMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsPlaceholderManagementRepo.alertsAndNotificationsMenu));
	    	JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", alertsAndNotificationsMenu);
	        Thread.sleep(5000); 
	        WebElement SummaryReports = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.SummaryReports));
	        SummaryReports.click();
	        WebElement BankAllocationSummary = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.BankAllocationSummary));
	        BankAllocationSummary.click();
	        wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.Branch));
	    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    }

	    public void fillMandatoryFields(String region, String branch, String fromDate, String toDate) {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    	WebElement Region = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.Region));
	    	Region.click();
	    	WebElement Dropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.Dropdownvalues(region)));
	    	Dropdownvalues.click();
	    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    	WebElement Branch = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.Branch));
	    	Branch.click();
	    	WebElement Assign_ReassignTosearchspace = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.Assign_ReassignTosearchspace));
	    	Assign_ReassignTosearchspace.clear();
	    	Assign_ReassignTosearchspace.sendKeys(branch); 
	    	WebElement Dropdownvalue = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.AllocateToDropdownvalues(branch)));
	    	Dropdownvalue.click();
	    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    	WebElement FromDate = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.FromDate));
	    	FromDate.click();
	    	WebElement DateDropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.DateDropdownvalues(fromDate)));
	    	DateDropdownvalues.click();
	    	WebElement ToDate = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.ToDate));
	    	ToDate.click();
	    	WebElement DateDropdownvalue = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.DateDropdownvalues(fromDate)));
	    	DateDropdownvalue.click();
	    }

	    public void clickSearchButton() {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    	WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.search));
	    	search.click();
	    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    }

	    public boolean areTodaysAllocationsDisplayed(List<String> accountNumbers) {
	    	
	        int count = accountNumbers.size();

	        // Get current system date in format dd-MM-yyyy
	        String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        WebElement Datefromgrid = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.Datefromgrid));
	        String displayedDate = Datefromgrid.getText().trim();

	        WebElement CountofAccountsfromgrid = wait.until(ExpectedConditions.visibilityOfElementLocated(BankAllocationSummaryRepo.CountofAccountsfromgrid));
	        String displayedCountStr = CountofAccountsfromgrid.getText().trim();

	        // Parse displayedCount to int
	        int displayedCount;
	        try {
	            displayedCount = Integer.parseInt(displayedCountStr);
	        } catch (NumberFormatException e) {
	            System.out.println("Displayed account count is not a valid number: " + displayedCountStr);
	            return false;
	        }

	        // Compare both date and count
	        boolean isDateMatching = currentDate.equals(displayedDate);
	        boolean isCountMatching = count == displayedCount;

	        if (!isDateMatching) {
	            System.out.println("Date mismatch -> Expected: " + currentDate + ", Found: " + displayedDate);
	        }

	        if (!isCountMatching) {
	            System.out.println("Count mismatch -> Expected: " + count + ", Found: " + displayedCount);
	        }

	        return isDateMatching && isCountMatching;
	    }

}
