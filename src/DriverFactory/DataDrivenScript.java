package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utilities.ExcelFileUtil;
import applicationLayer.AddUserPage;
import applicationLayer.LoginPage;
import applicationLayer.LogoutPage;

public class DataDrivenScript {
WebDriver driver;
String inputpath="E:\\630batch\\Selenium_FrameWorks\\TestInput\\Adduser.xlsx";
String outputpath="E:\\630batch\\Selenium_FrameWorks\\TestOutput\\DataDrivenResults.xlsx";
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
public void addingUser()throws Throwable
{
	AddUserPage user= PageFactory.initElements(driver, AddUserPage.class);
	//access Excel methods
	ExcelFileUtil xl= new ExcelFileUtil(inputpath);
	//count no of rows in a sheet
	int rc=xl.rowCount("UserCreation");
	for(int i=1; i<=rc;i++)
	{
	String employeename=xl.getCellData("UserCreation", i, 0);
	String username=xl.getCellData("UserCreation", i, 1);
	String password=xl.getCellData("UserCreation", i, 2);
	String cpassword=xl.getCellData("UserCreation", i, 3);
	user.verifyAdddUser(employeename, username, password, cpassword);
	String expected="viewSystemUsers";
	String actual=driver.getCurrentUrl();
	if(actual.contains(expected))
	{
		Reporter.log("User added Success",true);
		//write ass pass into results cell
		xl.setCellData("UserCreation", i, 4, "Pass", outputpath);
	}
	else
	{
		Reporter.log("User added Fail",true);
		//write ass pass into results cell
		xl.setCellData("UserCreation", i, 4, "Fail", outputpath);
	}
	}
}
@AfterTest
public void tearDown()throws Throwable
{
	LogoutPage logout=PageFactory.initElements(driver, LogoutPage.class);
	logout.verifyLogout();
	driver.close();
}
}
