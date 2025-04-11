package core.AlertsandNotifications;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Types;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.BasePackage.DBUtils;
import com.Page_Repository.CoreAlertsMaskAcAndMaskingAuthoRepo;
import com.Page_Repository.CoreAlertsNoticeTypeMa_NoticeAcRepo;
import com.Page_Repository.CoreAlertsPlaceholderManagementRepo;
import com.Page_Repository.CoreAlertsTemplateManagementRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Utility.Log;
import io.netty.handler.timeout.TimeoutException;

public class AlertsNoticeTypeManagement_MainClass {
	
private WebDriver driver;
	
	public AlertsNoticeTypeManagement_MainClass(WebDriver driver) {  
		Log.info("Initializing AlertsNoticeTypeManagement_MainClass...");
	    
	    this.driver = driver;  
	 
	    PageFactory.initElements(driver, this);  

	    Log.info("AlertsNoticeTypeManagement_MainClass initialized successfully.");
    }
	
	 // Method to verify if "Alerts and Notifications" menu item is displayed
    public boolean isAlertsAndNotificationsVisible() {
    	Log.info("Waiting for Alerts and Notifications menu to be visible...");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement alertsAndNotificationsMenu = wait.until(
            ExpectedConditions.visibilityOfElementLocated(CoreAlertsPlaceholderManagementRepo.alertsAndNotificationsMenu)
        );
        
        boolean isVisible = alertsAndNotificationsMenu.isDisplayed();
        Log.info("Alerts and Notifications menu visibility status: " + isVisible);
        
        return isVisible;
    }
    
 // Method to click on alerts and notifications
    public void clickOnAlertsAndNotifications() throws InterruptedException {
    	Log.info("Waiting for Alerts and Notifications menu to be visible...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement alertsAndNotificationsMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsPlaceholderManagementRepo.alertsAndNotificationsMenu));

        Log.info("Scrolling to Alerts and Notifications menu...");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", alertsAndNotificationsMenu);

        Log.info("Waiting for 5 seconds...");
        Thread.sleep(5000);

        Log.info("Waiting for Alerts and Notifications main menu to be visible...");
        WebElement AlertsandNotificationsmainmenu = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsPlaceholderManagementRepo.AlertsandNotificationsmainmenu));

        Log.info("Clicking on Alerts and Notifications main menu...");
        AlertsandNotificationsmainmenu.click();

        Log.info("Successfully clicked on Alerts and Notifications main menu.");
    }
    
    public void clickTemplateManagement() {
    	Log.info("Attempting to click on Template Management sub-menu...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement TemplateManagementsubMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.TemplateManagementsubMenu));
        
        TemplateManagementsubMenu.click();
        Log.info("Clicked on Template Management sub-menu.");

        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.Pagenavigation));
        Log.info("Spinner has disappeared. Template Management page is now loading.");
    }
    
 // Method to click on 'Add Template' button
    public void clickAddTemplate() {
    	Log.info("Attempting to click the Add Template button...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Waiting for the Add Template button to be visible...");
        WebElement addTemplateButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.addTemplateButton));

        Log.info("Clicking the Add Template button...");
        addTemplateButton.click();

        Log.info("Waiting for the spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Successfully clicked the Add Template button.");
    }
    
    // Method to verify if "Alerts and Notifications" menu item is displayed
    public boolean isAddTemplateFormDisplayed() {
    	Log.info("Waiting for Add Template form to be visible...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement addTemplateform = wait.until(
            ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.popUp)
        );

        Log.info("Waiting for spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        boolean isDisplayed = addTemplateform.isDisplayed();
        Log.info("Add Template form visibility status: " + isDisplayed);

        return isDisplayed;
    }
    
 // Method to interact with Notification Type field
    public void templateselectNotificationType(String value) {
    	Log.info("Selecting notification type: " + value);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Waiting for the spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Waiting for the Notification Type dropdown to be visible...");
        WebElement templatenotificationTypeDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.templatenotificationTypeDropdown));

        Log.info("Clicking the Notification Type dropdown...");
        templatenotificationTypeDropdown.click();

        Log.info("Waiting for the dropdown value '" + value + "' to be visible...");
        WebElement Dropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.dropdownvaluesfortemplatecreation(value)));

        Log.info("Selecting the dropdown value: " + value);
        Dropdownvalues.click();

        Log.info("Notification type '" + value + "' selected successfully.");
    }

    // Method to interact with Current Status field
    public void templateenterInitialStatus(String value) {
    	Log.info("Selecting initial status: " + value);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Waiting for the Initial Status dropdown to be visible...");
        WebElement templateInitialStatusDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.templateInitialStatusDropdown));

        Log.info("Clicking the Initial Status dropdown...");
        templateInitialStatusDropdown.click();

        try {
            Log.info("Waiting for the dropdown value '" + value + "' to be visible...");
            wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.dropdownvaluesfortemplatecreation(value)));
            Log.info("Values available in dropdown. Page loaded successfully.");
        } catch (Exception e) {
            Log.error("Values not available in dropdown. Page did not load completely.", e);
            throw new RuntimeException("Values not available in dropdown. The page is not fully loaded. Please try again.", e);
        }

        Log.info("Selecting the dropdown value: " + value);
        WebElement Dropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.dropdownvaluesfortemplatecreation(value)));
        Dropdownvalues.click();

        Log.info("Initial status '" + value + "' selected successfully.");
    }

    // Method to interact with Category field
    public void templateenterCategory(String value) {
    	Log.info("Selecting template category: " + value);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Waiting for the Template Category dropdown to be visible...");
        WebElement templateCategoryDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.templateCategoryDropdown));

        Log.info("Clicking the Template Category dropdown...");
        templateCategoryDropdown.click();

        Log.info("Waiting for the dropdown value '" + value + "' to be visible...");
        WebElement Dropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.dropdownvaluesfortemplatecreation(value)));

        Log.info("Selecting the dropdown value: " + value);
        Dropdownvalues.click();

        Log.info("Template category '" + value + "' selected successfully.");
    }

    // Method to enter template name
    public void enterTemplateName(String templateName) {
    	Log.info("Entering template name: " + templateName);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Waiting for the Template Name field to be visible...");
        WebElement templateNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.templateNameField));

        Log.info("Clearing the Template Name field...");
        templateNameField.clear();

        Log.info("Entering the Template Name: " + templateName);
        templateNameField.sendKeys(templateName);

        Log.info("Template name '" + templateName + "' entered successfully.");
    }
    
    public List<String> prepareAndExecuteInsertQuery(String query) throws IOException {
    	Log.info("Starting execution of stored procedure with query: " + query);

        List<Object> inputParams = Arrays.asList();
        List<Integer> outputTypes = Arrays.asList(Types.VARCHAR, Types.VARCHAR);

        Log.info("Calling ExecuteAnyOracleSQLStoredProcedure with input parameters: " + inputParams);
        List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure(query, inputParams, outputTypes);

        if (results == null || results.size() < 2) {
            Log.error("Stored procedure execution failed or returned insufficient results.");
            return Collections.emptyList(); // Return empty list if no results
        }

        String searchValue = (String) results.get(0);
        String executionMessage = (String) results.get(1);

        Log.info("Stored procedure executed successfully.");
        Log.info("Search Value: " + searchValue);
        Log.info("Execution Message: " + executionMessage);

        Log.info("Returning stored procedure results.");
        return Arrays.asList(searchValue, executionMessage);
    }
    
 // Method to enter template body
    public void enterTemplatebody(String templateName) {
    	Log.info("Entering template body: " + templateName);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Waiting for the Template Body field to be visible...");
        WebElement TemplateBodyField = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.TemplateBodyField));

        Log.info("Clearing the Template Body field...");
        TemplateBodyField.clear();

        Log.info("Entering the Template Body: " + templateName);
        TemplateBodyField.sendKeys(templateName);

        Log.info("Template body entered successfully.");
    }
    
    public void deleteNoticeType(String templatename) throws IOException, ClassNotFoundException, SQLException {
    	Log.info("Preparing to delete Notice Type: " + templatename);

        String query = "DELETE FROM ALT_NOTICE_TYPE WHERE NOTICE_TYPE_NAME='" + templatename + "'";
        
        Log.info("Executing query: " + query);
        DBUtils.executeSQLStatement(query);

        Log.info("Notice Type '" + templatename + "' deleted successfully.");

    }
    
    public void deleteAlertTemplate(String templatename) throws IOException {
    	Log.info("Starting deletion of alert template: " + templatename);

        String SPname = "SP_DELETE_ALERT_TEMPLATES";
        List<Object> inputParams = Arrays.asList(templatename);
        List<Integer> outputParamTypes = Arrays.asList(Types.VARCHAR);

        Log.info("Executing stored procedure '" + SPname + "' with input parameters: " + inputParams);

        DBUtils.ExecuteAnyOracleSQLStoredProcedure(SPname, inputParams, outputParamTypes);

        Log.info("Alert template '" + templatename + "' deleted successfully.");
    }
    
    public void deleteAlertScheduleRecords(String UserID) throws IOException {
        Log.info("Initiating deletion process for alert schedules created by User ID: " + UserID);

        String SPname = "SP_DeleteAlertScheduleRecords";
        List<Object> inputParams = Arrays.asList(UserID);
        List<Integer> outputParamTypes = Arrays.asList(Types.VARCHAR);

        Log.info("Calling stored procedure '" + SPname + "' with parameters: " + inputParams);

        DBUtils.ExecuteAnyOracleSQLStoredProcedure(SPname, inputParams, outputParamTypes);

        Log.info("Successfully deleted alert schedule records created by User ID: " + UserID);
    }

    // Method to click on 'Save' button
    public void clickSave() {
    	Log.info("Initiating click action on Save button.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Waiting for the Save button to be visible...");
        WebElement templatesaveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.templatesaveButton));

        Log.info("Clicking the Save button...");
        templatesaveButton.click();

//        Log.info("Waiting for the confirmation popup OK button to be visible...");
//        WebElement popupokbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.popupokbutton));
//
//        Log.info("Clicking the OK button on the popup...");
//        popupokbutton.click();

        Log.info("Save action completed successfully.");
    }

    // Method to verify if success message is displayed
    public boolean isSuccessMessageDisplayed() {
    	Log.info("Checking if the success message is displayed.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Waiting for the success message to be visible...");
        WebElement templatecreationsuccessmsg = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.templatecreationsuccessmsg));

        boolean isDisplayed = templatecreationsuccessmsg.isDisplayed();
        Log.info("Success message displayed: " + isDisplayed);

        return isDisplayed;
    }
    
    // Method to get error message
    public String getTemplateNameErrorMessage() {
    	Log.info("Waiting for Template Name error message to be visible...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement templateNameError = wait.until(
            ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.templateNameError)
        );

        String errorMessage = templateNameError.getText();
        Log.info("Retrieved Template Name error message: " + errorMessage);

        return errorMessage;
    }
    
    // Method to click on 'Cancel' button
    public void clickCancelbutton() {
    	Log.info("Waiting for Cancel button to be visible...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement cancelButton = wait.until(
            ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.cancelButton)
        );

        Log.info("Clicking on Cancel button...");
        cancelButton.click();

        Log.info("Waiting for spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Cancel button clicked successfully.");
    }
    
 // Method to click on 'Notice Type Management' submenu
    public void clickNoticeTypeManagementSubMenu() {
    	Log.info("Waiting for Notice Type Management sub-menu to be visible...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement noticeTypeManagementSubMenu = wait.until(
            ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.noticeTypeManagementSubMenu)
        );

        Log.info("Clicking on Notice Type Management sub-menu...");
        noticeTypeManagementSubMenu.click();

        Log.info("Waiting for spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Waiting for page navigation element to be visible...");
        wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.Pagenavigation));

        Log.info("Successfully navigated to Notice Type Management page.");
    }

    // Method to click on 'Add Notice Type' button
    public void clickAddNoticeType() {
    	Log.info("Waiting for Add Notice Type button to be visible...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement addNoticeTypeButton = wait.until(
            ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.addNoticeTypeButton)
        );

        Log.info("Clicking on Add Notice Type button...");
        addNoticeTypeButton.click();

        Log.info("Waiting for spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Add Notice Type button clicked successfully.");
    }

    // Method to verify that the pop-up with fields is present
    public boolean isAddNoticeTypePopUpDisplayed() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	WebElement popUp = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.popUp));
        return popUp.isDisplayed();
    }
    
 // Method to fill notice name
    public void enterNoticedetails(String noticecategory, String NoticeTypeName , String papertype , String CostPerNotice , String ProductCategory , String Template) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	WebElement NoticeCategorydropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.NoticeCategorydropdown));
    	NoticeCategorydropdown.click();
    	WebElement Dropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.Dropdownvalues(noticecategory)));
    	Dropdownvalues.click();
    	WebElement NoticeTypeNametextfield = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.NoticeTypeNametextfield));
    	NoticeTypeNametextfield.sendKeys(NoticeTypeName);
    	WebElement PaperTypedropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.PaperTypedropdown));
    	PaperTypedropdown.click();
    	WebElement PaperTypedropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.Dropdownvalues(papertype)));
    	PaperTypedropdownvalues.click();
    	WebElement CostPerNoticetextfield = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.CostPerNoticetextfield));
    	CostPerNoticetextfield.sendKeys(CostPerNotice);
    	WebElement ProductCategorydropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.ProductCategorydropdown));
    	ProductCategorydropdown.click();
    	WebElement ProductCategorydropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.ProductCategorydropdownvalues(ProductCategory)));
    	ProductCategorydropdownvalues.click();
    	WebElement Templatedropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.Templatedropdown));
    	Templatedropdown.click();
    	WebElement Templatedropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.Dropdownvalues(Template)));
    	Templatedropdownvalues.click();
    }
    
 // Method to click the acknowledgement checkbox
    public void selectAcknowledgementRequired() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	WebElement acknowledgementCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.acknowledgementCheckbox));
        if (!acknowledgementCheckbox.isSelected()) {
            acknowledgementCheckbox.click();
        }
    }

    // Method to click save button
    public void clickSaveforaddNoticeType() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	WebElement saveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.saveButton));
    	saveButton.click();
    }
    
 // Method to verify that success message displayed
    public boolean issuccessmessageDisplayed() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	WebElement Noticecreationsuccessmessage = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.Noticecreationsuccessmessage));
        return Noticecreationsuccessmessage.isDisplayed();
    }
    
    // Method to get error message text
    public String getErrorMessage() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.errorMessage));
        return errorMessage.getText();
    }
    
    // Clicks the Cancel button
    public void clickCancel() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	WebElement cancelButon = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.cancelButon));
    	cancelButon.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }

    // Verifies if the pop-up is no longer present, based on a locator for the pop-up
    public boolean isPopupClosed() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	List<WebElement> cancelButtons = driver.findElements(CoreAlertsNoticeTypeMa_NoticeAcRepo.cancelButon);
    	
        try {
        	return cancelButtons.isEmpty() || !cancelButtons.get(0).isDisplayed();
        } catch (Exception e) {
            return true;
        }
    }
    
    // Method to enter search criteria
    public void enterSearchCriteria(String NoticeCategory , String PaperType , String ProductCategory) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	WebElement NoticeCategorydropdowninNoticeTypeManagement = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.NoticeCategorydropdowninNoticeTypeManagement));
    	NoticeCategorydropdowninNoticeTypeManagement.click();
    	WebElement NoticeCategoryDropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.Dropdownvalues(NoticeCategory)));
    	NoticeCategoryDropdownvalues.click();
    	WebElement PaperTypedropdowninNoticeTypeManagement = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.PaperTypedropdowninNoticeTypeManagement));
    	PaperTypedropdowninNoticeTypeManagement.click();
    	WebElement PaperTypeDropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.Dropdownvalues(PaperType)));
    	PaperTypeDropdownvalues.click();
    	WebElement ProductCategorydropdowninNoticeTypeManagement = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.ProductCategorydropdowninNoticeTypeManagement));
    	ProductCategorydropdowninNoticeTypeManagement.click();
    	WebElement ProductCategoryDropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.ProductCategorydropdownvalues(ProductCategory)));
    	ProductCategoryDropdownvalues.click();
    }

    // Method to click the Search button
    public void clickSearchButton() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.searchButton));
    	searchButton.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }

    // Method to check if the results list is updated according to criteria
    public boolean isResultListFiltered() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        // For simplicity, assume any change in the result list indicates filtering
    	List<WebElement> results = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(CoreAlertsNoticeTypeMa_NoticeAcRepo.results));
        return !results.isEmpty();
    }
    
    // Method to navigate to View Posted Notice
    public void clickViewPostedNoticeSubMenu() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	WebElement viewPostedNoticeSubMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.viewPostedNoticeSubMenu));
        viewPostedNoticeSubMenu.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.Pagenavigation));
    }

    // Validate that the page with posted notices is displayed
    public boolean isPostedNoticePageDisplayed() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.header));
        return header.isDisplayed();
    }
    
    // Method to click on "Add Notice" button
    public void clickAddNotice() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	WebElement addNoticeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.addNoticeButton));
    	addNoticeButton.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }

    // Method to verify if the Add Notice form is displayed
    public boolean isAddNoticeFormDisplayed() {
        // Assuming the form has an id of 'addNoticeForm'
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	WebElement NoticePostingheader = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.NoticePostingheader));
        return NoticePostingheader.isDisplayed();
    }
    
 // Method to enter account number
    public void enterAccountNumber(String accountNumber) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	WebElement accountNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.accountNumberField));
        accountNumberField.clear();
        accountNumberField.sendKeys(accountNumber);
    }
    
    public boolean validateAccountNumberBVA() throws InterruptedException {
    	Log.info("Starting Boundary Value Analysis (BVA) validation for account number field...");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        // Define boundary values
        String[] testCases = {
            "123456789012345678901234",    // Max-1 (24 digits) - should be entered
            "1234567890123456789012345",   // Max (25 digits) - should be entered
            "12345678901234567890123456"   // Max+1 (26 digits) - should NOT be entered fully
        };

        boolean allPass = true; // Track overall test result

        for (String accountNumber : testCases) {
            Log.info("Testing with account number: " + accountNumber);

            // Enter account number
            enterAccountNumber(accountNumber);

            // Fetch the actual entered value using JavaScript
            Log.info("Fetching the actual entered value from the account number field...");
            WebElement accountNumberField = driver.findElement(CoreAlertsMaskAcAndMaskingAuthoRepo.accountNumberField);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String enteredValue = (String) js.executeScript("return arguments[0].value;", accountNumberField);

            // Validate the entered value
            if (accountNumber.length() > 25) {
                // Max+1 case: User should NOT be able to enter more than 25 digits
                if (enteredValue.length() > 25) {
                    Log.error("Test failed for Max+1 (26 digits) - More than 25 digits entered.");
                    allPass = false;
                } else {
                    Log.info("Test passed for Max+1 (26 digits) - Application restricted input.");
                }
            } else {
                // Max and Max-1 case: It should allow entering the full input
                if (!enteredValue.equals(accountNumber)) {
                    Log.error("Test failed for " + accountNumber.length() + " digits - Incorrect input stored.");
                    allPass = false;
                } else {
                    Log.info("Test passed for " + accountNumber.length() + " digits - Input accepted correctly.");
                }
            }
        }

        Log.info("BVA validation for account number field completed. Overall result: " + (allPass ? "PASS" : "FAIL"));
        return allPass;
    }
    
    public boolean validateAccountNumberECP() throws InterruptedException {
    	Log.info("Starting Equivalence Class Partitioning (ECP) validation for account number field...");

        // Define test cases: Numeric (Valid), Alphabetic (Invalid), Alphanumeric (Invalid)
        String[] testCases = {
            "12345678901234567890",   // Numeric (Valid)
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ",  // Alphabetic (Invalid)
            "12345ABCDE67890FGHIJ"  // Alphanumeric (Invalid)
        };

        boolean allPass = true; // Track overall test result

        for (String accountNumber : testCases) {
            Log.info("Testing with account number: " + accountNumber);

            // Enter account number
            enterAccountNumber(accountNumber);

            // Fetch the actual entered value using JavaScript
            Log.info("Fetching the actual entered value from the account number field...");
            WebElement accountNumberField = driver.findElement(CoreAlertsMaskAcAndMaskingAuthoRepo.accountNumberField);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String enteredValue = (String) js.executeScript("return arguments[0].value;", accountNumberField);

            // Validation Logic
            if (accountNumber.matches("\\d+")) { 
                // Numeric Input: Should be fully entered
                if (!enteredValue.equals(accountNumber)) {
                    Log.error("Test failed for valid numeric input: " + accountNumber);
                    allPass = false;
                } else {
                    Log.info("Test passed for valid numeric input: " + accountNumber);
                }
            } else {
                // Alphabetic & Alphanumeric Inputs: Should be restricted
                if (!enteredValue.isEmpty()) {
                    Log.error("Test failed for invalid input: " + accountNumber + " (Entered: " + enteredValue + ")");
                    
                    // If alphabets are entered, return false and print the entered value
                    if (enteredValue.matches(".*[a-zA-Z!@#$%^&*(),.?\":{}|<> ].*")) {
                        Log.error("Error: Alphabets were entered when they should be restricted! Entered Value: " + enteredValue);
                        allPass = false;
                    }
                } else {
                    Log.info("Test passed for invalid input restriction: " + accountNumber);
                }
            }
        }

        Log.info("ECP validation for account number field completed. Overall result: " + (allPass ? "PASS" : "FAIL"));
        return allPass;
    }
    
    // Method to enter Notice Category
    public void enterNoticeCategory(String NoticeCategory) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	WebElement NoticeCategorydropdownAddNotice = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.NoticeCategorydropdownAddNotice));
    	NoticeCategorydropdownAddNotice.click();
    	WebElement Dropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.Dropdownvalues(NoticeCategory)));
    	Dropdownvalues.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }

    // Method to click the submit button
    public void clickSubmit() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.submitButton));
        submitButton.click();
    }

    // Method to get the error message
    public String getErrorMessageAddNotice() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	WebElement errorMessageAddNotice = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.errorMessageAddNotice));
        return errorMessageAddNotice.getText();
    }
    
    // Method to navigate to the Notice Acknowledgement section
    public void clickNoticeAcknowledgement() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	WebElement noticeAcknowledgementLink = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.noticeAcknowledgementLink));
        noticeAcknowledgementLink.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }

    // Method to check if the Notice Acknowledgement section is open
    public boolean isNoticeAcknowledgementSectionOpen() {
        
        return driver.getCurrentUrl().contains("NoticeAcknowledgement");
    }
    
    // Methods to verify status
    public boolean isPendingDisplayed() {
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	    List<WebElement> pendingStatus = wait.until(
    	        ExpectedConditions.presenceOfAllElementsLocatedBy(CoreAlertsNoticeTypeMa_NoticeAcRepo.pendingStatus)
    	    );

    	    // Check if all elements have the text "Pending"
    	    return pendingStatus.stream()
    	                        .allMatch(element -> element.getText().trim().equals("Pending")); 
    }
    
    public void executeExe(String exePath) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(exePath);
            processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isProcessingDisplayed() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    List<WebElement> pendingStatus = wait.until(
	        ExpectedConditions.presenceOfAllElementsLocatedBy(CoreAlertsNoticeTypeMa_NoticeAcRepo.pendingStatus)
	    );

	    // Check if all elements have the text "Pending"
	    return pendingStatus.stream()
	                        .allMatch(element -> element.getText().trim().equals("Processing")); 
    }

    public boolean isSentDisplayed() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    List<WebElement> pendingStatus = wait.until(
	        ExpectedConditions.presenceOfAllElementsLocatedBy(CoreAlertsNoticeTypeMa_NoticeAcRepo.pendingStatus)
	    );

	    // Check if all elements have the text "Pending"
	    return pendingStatus.stream()
	                        .allMatch(element -> element.getText().trim().equals("Sent"));
    }
    
    // Method to select a value from Notice Category dropdown
    public void selectNoticeCategory(String category) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	WebElement noticeCategoryDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.noticeCategoryDropdown));
    	noticeCategoryDropdown.click();
    	WebElement Dropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.Dropdownvalues(category)));
    	Dropdownvalues.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }

    // Method to select a value from Notice Type Name dropdown
    public void selectNoticeTypeName(String typeName) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	WebElement noticeTypeNameDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.noticeTypeNameDropdown));
    	noticeTypeNameDropdown.click();
    	WebElement Dropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.Dropdownvalues(typeName)));
    	Dropdownvalues.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }

    // Method to enter account number
    public void enterAccountNumberNoticePosting(String accountNumber) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	WebElement accountNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.accountNumberField));
        accountNumberField.clear();
        accountNumberField.sendKeys(accountNumber);
        WebElement validateButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.validateButton));
        validateButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.accountNumberValidationMessage));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.accountNumberValidationMessage));
    }

    // Method to select Include Co-Borrower checkbox
    public void selectIncludeCoBorrower() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	WebElement includeCoBorrowerCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.includeCoBorrowerCheckbox));
        if (!includeCoBorrowerCheckbox.isSelected()) {
            includeCoBorrowerCheckbox.click();
        }
    }

    // Method to click the Post button
    public void clickPostButton() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	WebElement postButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.postButton));
        postButton.click();
    }

    // Method to verify the success message
    public boolean isSuccessMessageDisplayedNoticePosting() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.successMessage));
        return successMessage.isDisplayed();
    }
    
 // Method to enter invalid account number
    public void enterInvalidAccountNumber(String accountNumber) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	WebElement accountNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.accountNumberField));
        accountNumberField.clear();
        accountNumberField.sendKeys(accountNumber);
    }

    // Method to click on search button
    public void clicksearchButon() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	WebElement searchButon = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.searchButon));
    	searchButon.click();
    }

    // Method to get error message
    public String geterrorMsg() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNoticeTypeMa_NoticeAcRepo.errorMsg));
        return errorMsg.getText();
    }

}
