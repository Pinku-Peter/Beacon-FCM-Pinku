package com.Page_Repository;

import org.openqa.selenium.By;

public class AgencyAllocationSummaryRepo {
	
	public static By collectionAgencyMenu = By.xpath("//span[@class='text nav-text' and text()='Collection Agency']");
	public static By agencyAccountAllocationMenu = By.xpath("//a[@title='Agency Account Allocation']"); 
	public static By Zone_CO_dropdown_field = By.xpath("//label[text()='Zone/CO']//following-sibling::div");
	public static By accountTypeDropdown = By.xpath("//label[text()='Types of Account']//following-sibling::div");
	public static By assetCategoryDropdown = By.xpath("//label[text()='Asset Category']//following-sibling::div");
	public static By assetCategoryAllvaluesCheckbox = By.xpath("//div[@class='rz-multiselect-panel rz-popup']//div[@class='rz-chkbox']");
	public static By smaCategoryDropdown = By.xpath("//label[text()='SMA Category']//following-sibling::div"); 
	public static By npaCategoryDropdown = By.xpath("//label[text()='NPA Category']//following-sibling::div");
	public static By regionDropdown = By.xpath("//label[text()='Region']//following-sibling::div");
	public static By dpdDaysFinOpDropdown = By.xpath("//label[text()='DPD Days']//following-sibling::div");
	public static By dpdDaysTextbox = By.xpath("(//input[@class='searchinput form-control' and @maxlength='30'])[2]");
	public static By searchButton = By.xpath("//button[@type='submit' and text()='Search']");
	public static By totalAccountsSelected = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//tbody//td[1]");
	public static By totalOutstandingAmount = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//tbody//td[2]");
	public static By selectCollectionAgencyDropdown = By.xpath("//label[text()='Select Collection Agency']//following-sibling::div");
	public static By selectCollectionAgencyDropdownSearchfield = By.xpath("//input[@class='rz-dropdown-filter rz-inputtext   ']");
	public static By selectCollectionAgencyDropdownValues = By.xpath("(//div[@class='rz-dropdown-panel rz-popup'])[3]//li");
	public static By downloadButton = By.xpath("//button[@type='submit' and text()=' Download ']");
	public static By downloadSuccessMessage = By.xpath("//p[text()='File downloaded successfully']");
	public static By allocateButton = By.xpath("//button[@type='submit' and text()='Allocate']");
	public static By successAlert = By.xpath("//span[text()='Allocated Successfully. ']"); 
	public static By agencyManagementBtn = By.xpath("//span[@class='text nav-text' and text()='Agency Management']");
	public static By allocationSummaryBtn = By.xpath("//a[@title='Agency Allocation Summary']"); 
	public static By validationMessage = By.xpath("//p[text()='Please Select Agency']");
	
	
	public static By accountType_CollectionAgencyDropdownvalues(String value) {  
        return By.xpath("//li[@class='rz-dropdown-item ' and @aria-label='>" + value + "']");  
    }
	public static By smaCategory_npaCategory_region_Dropdownvalues(String value) {    
        return By.xpath("//li[@class='rz-multiselect-item ' and @aria-label='" + value + "']");   
    }
	public static By dpdDaysFinOp_Dropdownvalues(String value) {    
        return By.xpath("//div[@class='rz-dropdown-panel rz-popup']//li[@class='rz-dropdown-item ' and @aria-label='>" + value + "']");
      
    }
}
