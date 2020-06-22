
  package com.flipkart;
  
  import org.testng.annotations.Test;
  
  import com.filpkart.Flipkart;
  
  import utility.excel;
  
  public class TestFlipkart extends Baseclass {
  
  
  @Test(groups = "testMethods", description =
  "this method is used to login in an application") 
  public void testLoginPage() throws InterruptedException { 
	  //excel Path 
	  String filePath = "../AutomationEcommerceWebsiteProject/test_Data/testData.xlsx";
  
	  //sheet Name 
	  String sheetName = "Login Details"; 
	  Flipkart flipkart = new Flipkart(driver); 
	  //Read cell data for username 
	  String user_N = excel.ReadExcel(filePath, sheetName, 1, 0); 
	  String pass_word = excel.ReadExcel(filePath, sheetName, 1, 1);
  
	  flipkart.loginPage(user_N,pass_word); 
	  }
  
	  @Test(dependsOnGroups = "testMethods", priority = 1, description =
	  "this method is used to verify logo of application") 
	  public void testVerifyLogo() { 
		  Flipkart flipkart = new Flipkart(driver);
		  flipkart.verifyLogo(); 
	  }
	  
	  @Test(dependsOnGroups = "testMethods", priority = 2, description =
	  "this method is used to select product from product page") 
	  public void testProductSelection() throws InterruptedException { 
		  //excel Path 
		  String filePath = "../AutomationEcommerceWebsiteFlipkart/test_Data/testData.xlsx";
	  
	  //product select sheets 
		  String productselectionSheetName = "Product Selection"; 
		  Flipkart flipkart = new Flipkart(driver); 
		  String searchText = excel.ReadExcel(filePath, productselectionSheetName, 1, 0);
		  flipkart.productSelection(searchText); 
	  }
	  
	  @Test(dependsOnMethods = "testProductSelection", description =
	  "this method is used to add a selected product to the basket") public void
	  testAddToBasket() throws InterruptedException{ Flipkart flipkart = new
	  Flipkart(driver); flipkart.addToBasket(); }
	  
	  @Test(dependsOnMethods = "testAddToBasket", description =
	  "this method is used to compare the price whether it is same or not") 
	  public void testComparePrice() throws InterruptedException { 
		  Flipkart flipkart = new Flipkart(driver); 
		  flipkart.comparePrice(); 
	  }
	  
	  @Test(dependsOnMethods = {"testLoginPage", "testProductSelection",
	  "testAddToBasket", "testComparePrice"}) 
	  public void detailsoftestcases() {
		  Flipkart flipkart = new Flipkart(driver); 
		  flipkart.detailsOfTestCases(); 
	  } 
	  
  }
	 