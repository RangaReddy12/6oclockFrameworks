package CommonFunctions;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import Constant.PBConstant;

public class PBFunctions extends PBConstant{
	
//method for login
public static boolean verifyLogin(String username,String password)throws Throwable
{
	driver.findElement(By.xpath(p.getProperty("Objuser"))).sendKeys(username);
	driver.findElement(By.xpath(p.getProperty("Objpass"))).sendKeys(password);
	driver.findElement(By.xpath(p.getProperty("OblLogin"))).click();
	Thread.sleep(5000);
	String expected="adminflow";
	String actual=driver.getCurrentUrl();
	if(actual.toLowerCase().contains(expected.toLowerCase()))
	{
		Reporter.log("Login Success::"+expected+"   "+actual,true);
		return true;
	}
	else
	{
		Reporter.log("Login Fail::"+expected+"   "+actual,true);
		return false;
	}
}
//method for click branches
public static void clickBranches()throws Throwable
{
	driver.findElement(By.xpath(p.getProperty("Objbranches"))).click();
	Thread.sleep(5000);
}
//method for new Branch Creation
public static boolean verifyNewBranch(String bname,String Address1,String Address2,
String Address3,String area,String zipcode,String country,String state,String city)
throws Throwable{
driver.findElement(By.xpath(p.getProperty("Objnewbranch"))).click();
Thread.sleep(4000);
driver.findElement(By.xpath(p.getProperty("Objnbranch"))).sendKeys(bname);
driver.findElement(By.xpath(p.getProperty("Objaddress1"))).sendKeys(Address1);
driver.findElement(By.xpath(p.getProperty("Objaddress2"))).sendKeys(Address2);
driver.findElement(By.xpath(p.getProperty("Objaddress3"))).sendKeys(Address3);
driver.findElement(By.xpath(p.getProperty("Objarea"))).sendKeys(area);
driver.findElement(By.xpath(p.getProperty("Objzcode"))).sendKeys(zipcode);
new Select(driver.findElement(By.xpath(p.getProperty("Objcountry")))).selectByVisibleText(country);
Thread.sleep(3000);
new Select(driver.findElement(By.xpath(p.getProperty("Objstate")))).selectByVisibleText(state);
Thread.sleep(3000);
new Select(driver.findElement(By.xpath(p.getProperty("Objcity")))).selectByVisibleText(city);
Thread.sleep(3000);
driver.findElement(By.xpath(p.getProperty("Objsubmit"))).click();
Thread.sleep(5000);
//capture alert
String newbranchalert=driver.switchTo().alert().getText();
Thread.sleep(5000);
driver.switchTo().alert().accept();
String expected="New Branch with";
if(newbranchalert.toLowerCase().contains(expected.toLowerCase()))
{
	Reporter.log(newbranchalert,true);
	return true;
}
else
{
	Reporter.log("New Branch Creation Fail",true);
	return true;
}
}
//method for branch Updation
public static boolean verifyBranchUpdate(String branchname,String Address,String zipcode)
throws Throwable{
	driver.findElement(By.xpath(p.getProperty("Objedit"))).click();
	Thread.sleep(5000);
	WebElement branch=driver.findElement(By.xpath(p.getProperty("Objubranch")));
	branch.clear();
	branch.sendKeys(branchname);
	Thread.sleep(3000);
	WebElement add=driver.findElement(By.xpath(p.getProperty("Objuaddress1")));
	add.clear();
	add.sendKeys(Address);
	Thread.sleep(3000);
	WebElement zip=driver.findElement(By.xpath(p.getProperty("Objuzcode")));
    zip.clear();
	zip.sendKeys(zipcode);
	Thread.sleep(3000);
	driver.findElement(By.xpath(p.getProperty("Objupdate"))).click();
	Thread.sleep(3000);
	String updatebranchalert=driver.switchTo().alert().getText();
	Thread.sleep(3000);
	driver.switchTo().alert().accept();
	String expected="Branch updated";
	if(updatebranchalert.toLowerCase().contains(expected.toLowerCase()))
	{
		Reporter.log(updatebranchalert,true);
		return true;
	}
	else
	{
		Reporter.log("Branch Update Fail",true);
		return false;
	}
}
//method for logout
public static boolean verifyLogout()throws Throwable
{
	driver.findElement(By.xpath(p.getProperty("Objlogout"))).click();
	Thread.sleep(5000);
if(driver.findElement(By.xpath(p.getProperty("OblLogin"))).isDisplayed())
{
	Reporter.log("Logout Success",true);
	return true;
}
else
{
	Reporter.log("Logout Fail",true);
	return false;
}
	
}
public static String generateDate()
{
	Date d=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("YYYY_MM_dd hh_mm_ss");
	return sdf.format(d);
}
}













