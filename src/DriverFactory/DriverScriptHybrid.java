package DriverFactory;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunctions.PBFunctions;
import Constant.PBConstant;
import Utilities.ExcelFileUtil;

public class DriverScriptHybrid extends PBConstant {
	String inputpath="E:\\630batch\\Selenium_FrameWorks\\TestInput\\HybridTest.xlsx";
	String outputpath="E:\\630batch\\Selenium_FrameWorks\\TestOutput\\HybridResults.xlsx";
	ExtentReports report;
	ExtentTest test;
	String TCSheet="TestCases";
	String TSSheet="TestSteps";
	@Test
	public void startTest()throws Throwable
	{
		report= new ExtentReports("./Reports/"+PBFunctions.generateDate()+"_"+"Hybrid.html");
	boolean res=false;
	String tcres="";
	//Access Excel Methods
	ExcelFileUtil xl = new ExcelFileUtil(inputpath);
	//count no of rows in TCSheet and TSSheet
	int TCCount=xl.rowCount(TCSheet);
	int TSCount=xl.rowCount(TSSheet);
	Reporter.log("No of rows in TCSheet::"+TCCount+"  "+"No of rows in TSSheet::"+TSCount,true);
	//iterate all rows in TCSheet
	for(int i=1;i<=TCCount; i++)
	{
		test=report.startTest("Primus");
		//Read Execute Cell
		String Execute=xl.getCellData(TCSheet, i, 2);
		if(Execute.equalsIgnoreCase("Y"))
		{
			//read tcid cell from TCsheet
			String tcid=xl.getCellData(TCSheet, i, 0);
			//iterate all rows in TSSheet
			for(int j=1;j<=TSCount;j++)
			{
				//read Description cell
				String Description=xl.getCellData(TSSheet, j, 2);
				//read tsid cell from TSSheet
				String tsid=xl.getCellData(TSSheet, j, 0);
				if(tcid.equalsIgnoreCase(tsid))
				{
					//Read Keyword Cell From TSSheet
					String Keyword=xl.getCellData(TSSheet, j, 4);
					//call methods
				if(Keyword.equalsIgnoreCase("AdminLogin"))
				{
				String username=xl.getCellData(TSSheet, j, 5);
				String password=xl.getCellData(TSSheet, j, 6);
				res=PBFunctions.verifyLogin(username, password);
					test.log(LogStatus.INFO, Description);
				}
				else if(Keyword.equalsIgnoreCase("NewBranchCreation"))
				{
					PBFunctions.clickBranches();
					String branchn=xl.getCellData(TSSheet, j, 5);
					String address1=xl.getCellData(TSSheet, j, 6);
					String address2=xl.getCellData(TSSheet, j, 7);
					String address3=xl.getCellData(TSSheet, j, 8);
					String area=xl.getCellData(TSSheet, j, 9);
					String zipcode=xl.getCellData(TSSheet, j, 10);
					String country=xl.getCellData(TSSheet, j, 11);
					String state=xl.getCellData(TSSheet, j, 12);
					String city=xl.getCellData(TSSheet, j, 13);
					res=PBFunctions.verifyNewBranch(branchn, address1, address2, address3, area, zipcode, country, state, city);
					test.log(LogStatus.INFO, Description);
				}
				else if(Keyword.equalsIgnoreCase("UpdateBranch"))
				{
					String branchnameu=xl.getCellData(TSSheet, j, 5);
					String address=xl.getCellData(TSSheet, j, 6);
					String zipcode=xl.getCellData(TSSheet, j, 10);
					PBFunctions.clickBranches();
					res=PBFunctions.verifyBranchUpdate(branchnameu, address, zipcode);
					test.log(LogStatus.INFO, Description);
				}
				else if(Keyword.equalsIgnoreCase("AdminLogout"))
				{
					res=PBFunctions.verifyLogout();
					test.log(LogStatus.INFO, Description);
				}
				String tsres="";
				if(res)
				{
					//if res is true write as pass into results cell in TSsheet
					tsres="Pass";
					xl.setCellData(TSSheet, j, 3, tsres, outputpath);
					test.log(LogStatus.PASS, Description);
				}
				else
				{
					//if res is false write as fail into results cell in TSsheet
					tsres="Fail";
					xl.setCellData(TSSheet, j, 3, tsres, outputpath);
					test.log(LogStatus.FAIL, Description);
				}
				tcres=tsres;
				}
				
				report.endTest(test);
				report.flush();
			}
			//write tcres in TCSheet
			xl.setCellData(TCSheet, i, 3, tcres, outputpath);
		}
		else
		{
			//write as Blocked Into Results Cell
			xl.setCellData(TCSheet, i, 3, "Blocked", outputpath);
		}
	}
	}
}
