package com.Page_Repository;
import org.openqa.selenium.By;

public class UpdationofDispositionRepo {
	
	public static By spinner = By.xpath("//div[@class='spinner']");
	public static By dispositionMenu = By.xpath("//span[contains(text(), 'Disposition')]");
	public static By updationOfDispositionMenu = By.xpath("//a[@title='Updation of Disposition']");
	public static By accountNumberField = By.xpath("//input[@placeholder='Account Number']");
	public static By searchButton = By.xpath("//input[@value='Search']");
	public static By errorMessage = By.xpath("//p[contains(text(),'Invalid Account Number')]");
	public static By loginuserid = By.xpath("//span[@class='emailuser']");
	public static By customername = By.xpath("//th[contains(text(),'Customer Name')]");
	public static By savebutton = By.xpath("//button[contains(text(),'Save')]");
	public static By errorMessage2 = By.xpath("//span[contains(text(),'Please Enter All Fields In The Interaction Details')]");
}
