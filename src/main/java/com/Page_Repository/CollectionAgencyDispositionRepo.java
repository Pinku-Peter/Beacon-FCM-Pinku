package com.Page_Repository;

import org.openqa.selenium.By;

public class CollectionAgencyDispositionRepo {
	
	public static By collectionAgencyMenu = By.xpath("//span[text()='Collection Agency']");
	public static By agencyAccountAllocationLink = By.xpath("//a[@title='Agency Account Allocation']");
	public static By pageHeader = By.xpath("//div[@class='dvPageheadingCaption']");
	public static By ZoneCO = By.xpath("//label[text()='Zone/CO']");
	public static By OutstandingBalLimitOperatordropdownvalue(String value) {
        return By.xpath("(//li[@aria-label='>" + value + "'])[3]");
    }
}
