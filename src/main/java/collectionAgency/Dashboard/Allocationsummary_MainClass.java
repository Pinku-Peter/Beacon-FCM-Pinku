package collectionAgency.Dashboard;

import java.io.IOException;
import java.sql.Types;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.BasePackage.DBUtils;
import com.BasePackage.DownloadedExcelReader;
import com.Page_Repository.AllocationsummaryRepo;
import com.Page_Repository.BankAllocationSummaryRepo;
import com.Page_Repository.BankRegularizationSummaryRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Utility.Log;

public class Allocationsummary_MainClass {
	
private WebDriver driver;
	
	public Allocationsummary_MainClass(WebDriver driver) {   
	    
	    this.driver = driver;
	    
	    PageFactory.initElements(driver, this);
	    
    }
	
	public void navigateToAllocationSummary() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		WebElement dashboardMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(AllocationsummaryRepo.dashboardMenu)); 
        dashboardMenu.click();
        WebElement allocationSummaryLink = wait.until(ExpectedConditions.visibilityOfElementLocated(AllocationsummaryRepo.allocationSummaryLink));
        allocationSummaryLink.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }

    public boolean isAllocationSummaryPageDisplayed() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	WebElement allocationSummaryLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(AllocationsummaryRepo.allocationSummaryLabel));
    	WebElement grid = wait.until(ExpectedConditions.visibilityOfElementLocated(AllocationsummaryRepo.grid));
        return allocationSummaryLabel.isDisplayed() && grid.isDisplayed(); 
    }
    
    // Method to verify Date column is displayed
    public boolean isDateColumnDisplayed() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	WebElement dateColumn = wait.until(ExpectedConditions.visibilityOfElementLocated(AllocationsummaryRepo.dateColumn));
        return dateColumn.isDisplayed();
    }

    // Method to verify Total Accounts Received column is displayed
    public boolean isTotalAccountsReceivedColumnDisplayed() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	WebElement totalAccountsReceivedColumn = wait.until(ExpectedConditions.visibilityOfElementLocated(AllocationsummaryRepo.totalAccountsReceivedColumn));
        return totalAccountsReceivedColumn.isDisplayed();
    }

    // Method to verify Allocated to Agents column is displayed
    public boolean isAllocatedToAgentsColumnDisplayed() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	WebElement allocatedToAgentsColumn = wait.until(ExpectedConditions.visibilityOfElementLocated(AllocationsummaryRepo.allocatedToAgentsColumn));
        return allocatedToAgentsColumn.isDisplayed(); 
    }
    public boolean isGridValuesDisplayed() {  
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	WebElement gridValues = wait.until(ExpectedConditions.visibilityOfElementLocated(AllocationsummaryRepo.gridValues));
        return gridValues.isDisplayed();  
    }
}
