package applicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class LogoutPage {
WebDriver driver;
Actions ac;
public LogoutPage(WebDriver driver)
{
	this.driver=driver;
}
@FindBy(id="welcome")
WebElement clickWelcome;
@FindBy(linkText="Logout")
WebElement clicklogout;
public void verifyLogout()throws Throwable
{
	ac= new Actions(driver);
	ac.moveToElement(this.clickWelcome).click().perform();
	Thread.sleep(5000);
	ac.moveToElement(this.clicklogout).click().perform();
	Thread.sleep(5000);
}
}
