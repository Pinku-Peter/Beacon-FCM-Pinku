package com.Page_Repository;

import org.openqa.selenium.By;

public class CoreAlertsNoticeTypeMa_NoticeAcRepo {
	
	public static By popUp = By.xpath("//div[@class='rz-dialog']");
	public static By Pagenavigation = By.xpath("//nav[@aria-label='Page navigation example']");
	public static By templateNameError = By.xpath("//p[text()='Please enter template name.']");
	public static By noticeTypeManagementSubMenu = By.xpath("//a[@title='Notice Type Management']");
	public static By addNoticeTypeButton = By.xpath("//button[@type='button' and text()='Add Notice Type']");
	public static By cancelButton = By.xpath("//button[@type='reset' and text()='Cancel']");
	public static By NoticeCategorydropdown  = By.xpath("//div[@class='rz-dialog']//label[text()='Notice Category ']//following-sibling::div");
	public static By NoticeTypeNametextfield  = By.xpath("//div[@class='rz-dialog']//label[text()='Notice Type Name']//following-sibling::input");
	public static By PaperTypedropdown  = By.xpath("//div[@class='rz-dialog']//label[text()='Paper Type']//following-sibling::div");
	public static By CostPerNoticetextfield  = By.xpath("//div[@class='rz-dialog']//label[text()='Cost Per Notice']//following-sibling::input");
	public static By ProductCategorydropdown  = By.xpath("//div[@class='rz-dialog']//label[text()='Product Category']//following-sibling::div");
	public static By Templatedropdown  = By.xpath("//div[@class='rz-dialog']//label[text()='Template']//following-sibling::div");
	public static By acknowledgementCheckbox  = By.xpath("//div[@class='rz-chkbox valid']");
	public static By saveButton  = By.xpath("//input[@type='submit' and @value='Save']");
	public static By Noticecreationsuccessmessage  = By.xpath("//p[text()='Notice created successfully.']");
	public static By errorMessage  = By.xpath("//p[text()='Please enter a notice type name.']");
	public static By cancelButon  = By.xpath("//input[@type='button' and @value='Cancel']");
	public static By NoticeCategorydropdowninNoticeTypeManagement  = By.xpath("//label[text()='Notice Category ']//following-sibling::div");
	public static By PaperTypedropdowninNoticeTypeManagement  = By.xpath("//label[text()='Paper Type']//following-sibling::div");
	public static By ProductCategorydropdowninNoticeTypeManagement  = By.xpath("//label[text()='Product Category']//following-sibling::div");
	public static By searchButton = By.xpath("//button[@type='button' and text()='Search']");
	public static By results = By.xpath("//div[@class='rz-data-grid-data']//table//tbody//tr//td");
	public static By viewPostedNoticeSubMenu = By.xpath("//a[@title='View Posted Notice']");
	public static By header = By.xpath("//div[@class='dvPageheadingCaption' and contains(text(),'View Posted Notices Summary')]");
	public static By addNoticeButton = By.xpath("//button[@type='button' and text()='Add Notice']");
	public static By NoticePostingheader = By.xpath("//div[@class='dvPageheadingCaption' and contains(text(),'Notice Posting')]");
	public static By accountNumberField = By.xpath("//input[@type='number' and @placeholder='Account Number']");
	public static By NoticeCategorydropdownAddNotice = By.xpath("//label[text()='Notice Category']//following-sibling::div");
	public static By submitButton = By.xpath("//input[@type='button' and @value='View Template']");
	public static By errorMessageAddNotice = By.xpath("//p[text()='Please select a notice type name.']");
	public static By noticeAcknowledgementLink = By.xpath("//a[@title='Notice Acknowledgement']");
	public static By pendingStatus = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//tbody//tr//td[position()=4]");
	public static By noticeCategoryDropdown = By.xpath("//label[text()='Notice Category']//following-sibling::div");
	public static By noticeTypeNameDropdown = By.xpath("//label[text()='Notice Type Name']//following-sibling::div");
	public static By validateButton = By.xpath("//input[@type='button' and @value='Validate']");
	public static By accountNumberValidationMessage = By.xpath("//p[text()='Account Number validated successfully.']");
	public static By includeCoBorrowerCheckbox = By.xpath("//div[@class='form-button justify-content-center']//div[@class='rz-chkbox-box']");
	public static By postButton = By.xpath("//button[@type='submit' and text()='Post']");
	public static By successMessage = By.xpath("//p[text()='Notice posted successfully.']");
	public static By searchButon = By.xpath("//button[@type='submit' and text()='Search']");
	public static By errorMsg  = By.xpath("//p[text()='Invalid account number.']");
	
	
	public static By Dropdownvalues(String value) {
        return By.xpath("//div[@class='rz-dropdown-panel rz-popup']//li[@role='option' and @aria-label='>" + value + "']"); 
      
    }
	public static By ProductCategorydropdownvalues(String value) {
        return By.xpath("//div[@class='rz-multiselect-panel rz-popup']//li[@class='rz-multiselect-item ' and @aria-label='" + value + "']"); 
      
    }
}
