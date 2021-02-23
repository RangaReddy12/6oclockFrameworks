package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import applicationLayer.AddUserPage;
import applicationLayer.LoginPage;
import applicationLayer.LogoutPage;

public class TestScript {
WebDriver driver;
@BeforeTest
public void setUp()throws Throwable
{
	System.setProperty("webdriver.chrome.driver", "E:\\630batch\\Selenium_FrameWorks\\CommonDrivers\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.get("http://orangehrm.qedgetech.com/");
	driver.manage().window().maximize();
	LoginPage login=PageFactory.initElements(driver, LoginPage.class);
	login.verifyLogin("Admin", "Qedge123!@#");
}
@Test
public void useradd()throws Throwable
{
	AddUserPage user =PageFactory.initElements(driver, AddUserPage.class);
	user.verifyAdddUser("Akhi Ranga", "Ranga1235", "Akhilesh@123", "Akhilesh@123");
}
@AfterTest
public void tearDown()throws Throwable
{
	LogoutPage logout= PageFactory.initElements(driver, LogoutPage.class);
	logout.verifyLogout();
	driver.close();
}
}
