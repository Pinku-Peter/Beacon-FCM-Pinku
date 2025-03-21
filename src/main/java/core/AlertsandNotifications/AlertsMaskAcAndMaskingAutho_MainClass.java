package core.AlertsandNotifications;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Page_Repository.CoreAlertsMaskAcAndMaskingAuthoRepo;
import com.Page_Repository.CoreAlertsNotificationManagementRepo;
import com.Page_Repository.CoreAlertsPlaceholderManagementRepo;
import com.Page_Repository.CoreAlertsTemplateManagementRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Utility.Log;
import io.netty.handler.timeout.TimeoutException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AlertsMaskAcAndMaskingAutho_MainClass {
	
private WebDriver driver;
	
	public AlertsMaskAcAndMaskingAutho_MainClass(WebDriver driver) { 

	    this.driver = driver;

	    PageFactory.initElements(driver, this);

    }
	
	// Method to click on alerts and notifications
    public void clickOnAlertsAndNotifications() throws InterruptedException {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement alertsAndNotificationsMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsPlaceholderManagementRepo.alertsAndNotificationsMenu));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", alertsAndNotificationsMenu);
        Thread.sleep(5000);
        WebElement AlertsandNotificationsmainmenu = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsPlaceholderManagementRepo.AlertsandNotificationsmainmenu));
        AlertsandNotificationsmainmenu.click();
    }

    // Method to navigate to the mask account window
    public void clickOnMaskAccountWindow() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	WebElement maskAccountWindowLink = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsMaskAcAndMaskingAuthoRepo.maskAccountWindowLink));
    	maskAccountWindowLink.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }

    // Method to verify mask account window is displayed
    public boolean isMaskAccountWindowDisplayed() {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.contains("MaskAccount"); 
    }

}
