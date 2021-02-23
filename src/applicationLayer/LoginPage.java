package applicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
WebDriver driver;
public LoginPage(WebDriver driver)
{
	this.driver=driver;
}
//locator for login
@FindBy(name="txtUsername")
WebElement username;
@FindBy(xpath="//input[@id='txtPassword']")
WebElement password;
@FindBy(id="btnLogin")
WebElement loginbtn;
//method
public void verifyLogin(String username,String password)throws Throwable
{
	this.username.sendKeys(username);
	this.password.sendKeys(password);
	this.loginbtn.click();
	Thread.sleep(5000);
}
}
