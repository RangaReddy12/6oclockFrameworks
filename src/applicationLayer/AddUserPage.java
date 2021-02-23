package applicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AddUserPage {
WebDriver driver;
Actions ac;
public AddUserPage(WebDriver driver)
{
	this.driver=driver;
}
@FindBy(id="menu_admin_viewAdminModule")
WebElement clickAdmin;
@FindBy(id="menu_admin_UserManagement")
WebElement clickusermgnt;
@FindBy(id="menu_admin_viewSystemUsers")
WebElement clickUsers;
@FindBy(xpath="//input[@id='btnAdd']")
WebElement clickAdd;
@FindBy(name="systemUser[employeeName][empName]")
WebElement ename;
@FindBy(name="systemUser[userName]")
WebElement username;
@FindBy(name="systemUser[password]")
WebElement password;
@FindBy(name="systemUser[confirmPassword]")
WebElement cpassword;
@FindBy(name="btnSave")
WebElement clickSave;
public void verifyAdddUser(String ename,String username,String password,String cpassword)
throws Throwable{
	ac= new Actions(driver);
	ac.moveToElement(clickAdmin).perform();
	Thread.sleep(3000);
	ac.moveToElement(clickusermgnt).perform();
	Thread.sleep(3000);
	ac.moveToElement(clickUsers).click().perform();
	Thread.sleep(3000);
	ac.moveToElement(clickAdd).click().perform();
	Thread.sleep(3000);
	this.ename.sendKeys(ename);
	this.username.sendKeys(username);
	this.password.sendKeys(password);
	Thread.sleep(3000);
	this.cpassword.sendKeys(cpassword);
	Thread.sleep(3000);
	clickSave.click();
	Thread.sleep(5000);
}

}
