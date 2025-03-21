package com.Page_Repository;

import org.openqa.selenium.By;

public class CoreAlertsMaskAcAndMaskingAuthoRepo {
	
	public static By maskAccountWindowLink = By.xpath("//a[@title='Mask Account']");
	
	public static By Dropdownvalues(String value) {
        return By.xpath("//li[@class='rz-dropdown-item ' and @aria-label='>" + value + "']"); 
    }

}
