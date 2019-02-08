package com.auto.testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.auto.base.ROOperation;

public class TestROOPeration {

	ROOperation ro = new ROOperation();
	
	@Test(priority=1 ,dataProvider = "loginRO")
	public void roCreation(String un,String pwd,String id) 
	{
		ro.setUp();
		ro.loginDetails(un,pwd,id);
		ro.clickLogin();
		ro.selectStore();
		ro.createNewRO();
		ro.searchCust();
		ro.clickSearchResults();
		ro.selectVehicle();
		ro.addService();
		ro.selectServiceDD();
		ro.selectFromDD();
		ro.enterDetails();
		ro.summary();
		ro.roSeq();
	}
	
	@DataProvider(name = "loginRO")
	public static Object[][] loginRO() 
	{
		return new Object[][] { { "aarige", "Xtime111","a"}};
	}


}

