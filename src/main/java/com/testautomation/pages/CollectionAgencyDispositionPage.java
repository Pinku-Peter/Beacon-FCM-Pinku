package com.testautomation.pages;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.BasePackage.Base_Class;
import com.BasePackage.DBUtils;
import com.BasePackage.DownloadedExcelReader;
import com.BasePackage.DownloadedExcelReader.DataSummary;
import com.BasePackage.ExecuteStoredProcedure;
import com.Page_Repository.CollectionAgencyDispositionRepo;
import com.Page_Repository.CoreAutoAllocationRepo;
import com.Page_Repository.CoreManualAllocationRepo;
import com.Page_Repository.CoreRegularizationSummaryRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Utility.Log;
import java.sql.Connection;

import io.netty.handler.timeout.TimeoutException;

public class CollectionAgencyDispositionPage {
	
	private WebDriver driver;
	
	public CollectionAgencyDispositionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	// Method to navigate to Agency Account Allocation page
    public void navigateToAgencyAccountAllocation() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	WebElement collectionAgencyMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyDispositionRepo.collectionAgencyMenu));
        collectionAgencyMenu.click();
        WebElement agencyAccountAllocationLink = wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyDispositionRepo.agencyAccountAllocationLink));
        agencyAccountAllocationLink.click();
    }

    // Method to get page header text
    public String getPageHeaderText() throws InterruptedException {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyDispositionRepo.ZoneCO));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Thread.sleep(10000);
    	WebElement pageHeader = driver.findElement(CollectionAgencyDispositionRepo.pageHeader);
        return pageHeader.getText();
    }
    
 // Method to truncate 'mst_col_agency_acc_allocated' table
    public String truncateTable() throws SQLException, ClassNotFoundException, IOException {
        String truncateQuery = "Truncate TABLE mst_col_agency_acc_allocated";
        try {

        	String result = DBUtils.executeSQLStatement(truncateQuery);
        	return result;
        } catch (SQLException e) {
            throw new SQLException("Error occurred while truncating table", e);
        }
    }

    // Method to delete records from 'trn_account_followup' where disposition_date is today
    public String deleteRecordsWhereDispositionDateIsToday() throws SQLException, ClassNotFoundException, IOException {
        String deleteQuery = "DELETE FROM trn_account_followup WHERE disposition_date = CURRENT_DATE";
        try 
        {
        	String result = DBUtils.executeSQLStatement(deleteQuery);
        	return result;
        } catch (SQLException e) {
            throw new SQLException("Error occurred while deleting records", e);
        }
    }

}
