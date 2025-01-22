package com.Page_Repository;

import org.openqa.selenium.By;

public class CoreManualAllocationRepo {
	
	public static By manualallocationsubmenu = By.xpath("//a[@title='Manual Allocation']");
	public static By allocationName = By.xpath("//label[contains(text(),'Allocation Name')]//following-sibling::div[@onmousedown='Radzen.activeElement = null' and @tabindex='0']");
	public static By zone_co = By.xpath("//label[contains(text(),'Zone/CO')]//following-sibling::div[@onmousedown='Radzen.activeElement = null']");
	public static By region = By.xpath("//label[contains(text(),'Region')]//following-sibling::div[@onmousedown='Radzen.activeElement = null']");
	public static By branch = By.xpath("//label[contains(text(),'Branch')]//following-sibling::div[@onmousedown='Radzen.activeElement = null']");
	public static By branch_id = By.xpath("//label[contains(text(),'Branch ID')]//following-sibling::input[@name='Name']");
	public static By vertical = By.xpath("//label[contains(text(),'Vertical')]//following-sibling::div[@onmousedown='Radzen.activeElement = null']");
	public static By schemetype = By.xpath("//label[contains(text(),'Scheme Type')]//following-sibling::div[@onmousedown='Radzen.activeElement = null']");
	public static By producttype = By.xpath("//label[contains(text(),'Product Type')]//following-sibling::div[@onmousedown='Radzen.activeElement = null']");
	public static By schemecode = By.xpath("//label[contains(text(),'Scheme Code')]//following-sibling::div[@onmousedown='Radzen.activeElement = null']");
	public static By assettaggingtype = By.xpath("//label[contains(text(),'Asset Tagging Type')]//following-sibling::div[@onmousedown='Radzen.activeElement = null']");
	public static By assetcategory = By.xpath("//label[contains(text(),'Asset Category')]//following-sibling::div[@onmousedown='Radzen.activeElement = null']");
	public static By smacategory = By.xpath("//label[contains(text(),'SMA Category')]//following-sibling::div[@onmousedown='Radzen.activeElement = null']");
	public static By npacategory = By.xpath("//label[contains(text(),'NPA Category')]//following-sibling::div[@onmousedown='Radzen.activeElement = null']");
	public static By dpdoperatorsdropdown = By.xpath("//label[contains(text(),'DPD')]//following-sibling::div[@onmousedown='Radzen.activeElement = null']");
	public static By dpdtextfield = By.xpath("(//label[contains(text(),'DPD')]//following::input[@min='0' and @maxlength='35'])[1]");
	public static By osbalanceoperatorsdropdown = By.xpath("//label[contains(text(),'O/S Balance ')]//following-sibling::div[@onmousedown='Radzen.activeElement = null']");
	public static By OutstandingBalLimitOperatordropdownvalue(String value) {
        return By.xpath("(//li[@aria-label='>" + value + "'])[3]");
    }
	public static By osbalancetextfield = By.xpath("//label[contains(text(),'O/S Balance ')]//following::input[@min='0' and @maxlength='35']");
	public static By overdueemioperatorsdropdown = By.xpath("//label[contains(text(),'%Overdue to EMI')]//following-sibling::div[@onmousedown='Radzen.activeElement = null']");
	public static By overdueemiamounttextfield = By.xpath("//label[contains(text(),'%Overdue to EMI')]//following::input[@maxlength='3']");
	public static By actionowner = By.xpath("//label[contains(text(),'Action Owner')]//following-sibling::div[@onmousedown='Radzen.activeElement = null']");
	public static By ActionDateFrom = By.xpath("//label[contains(text(),'Action Date From')]//following-sibling::div[@class='valid']");
	public static By ActionDateto = By.xpath("//label[contains(text(),'Action Date to')]//following-sibling::div[@class='valid']");
	public static By TypesofAccount = By.xpath("//label[contains(text(),'Types of Account')]//following-sibling::div[@onmousedown='Radzen.activeElement = null']");
	public static By To = By.xpath("//label[(text()='To')]//following-sibling::div[@onmousedown='Radzen.activeElement = null']");
	public static By tovalue(String value) {
        return By.xpath("(//li[@aria-label='>" + value + "'])[2]");
    }
	public static By IsPFTNPA = By.xpath("//label[(text()='Is PFTNPA')]//following-sibling::div[contains(@class,'rz-chkbox')]");
	public static By IsFTNPA = By.xpath("//label[(text()='Is FTNPA')]//following-sibling::div[contains(@class,'rz-chkbox')]");
	public static By SaveThisAllocationCriteria = By.xpath("//label[(text()='Save This Allocation Criteria')]//following-sibling::div[contains(@class,'rz-chkbox')]");
	public static By Searchbtn = By.xpath("//button[@type='submit' and text()='Search']");
	public static By Resetbtn = By.xpath("//button[@type='reset' and text()='Reset']");
	public static By EditAllocationCriteriabtn = By.xpath("//button[@type='button' and text()='Edit Allocation Criteria']");
	public static By AllocateTo = By.xpath("//label[(text()='Allocate To')]//following-sibling::div[@onmousedown='Radzen.activeElement = null']");
	public static By ExpiryDate  = By.xpath("//label[contains(text(),'Expiry Date ')]//following::input[@name='ExpiryDate']");
	public static By Assignbtn = By.xpath("//button[@type='submit' and text()='Assign']");
	public static By  DownloadinExcel = By.xpath("//button[@type='submit' and text()=' Download in Excel']");
	public static By AssignedList = By.xpath("//a[@target='_blank']");
	public static By warningMessage = By.xpath("//span[@class='rz-growl-title']//following-sibling::p[text()='Asset Category Required']");
	public static By assetcategorysellectall = By.xpath("(//label[text()='Asset Category']//following::div[@class='rz-chkbox-box   '])[4]");
	public static By smacategorysellectall = By.xpath("(//label[text()='SMA Category']//following::div[contains(@class,' rz-multiselect-header')]//following-sibling::div[@class='rz-chkbox'])[4]");
	public static By npacategorysellectall = By.xpath("(//label[text()='NPA Category']//following::div[contains(@class,' rz-multiselect-header')]//following-sibling::div[@class='rz-chkbox'])[4]");
}
