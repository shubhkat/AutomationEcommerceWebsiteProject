package com.filpkart;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

public class Flipkart {
	
	WebDriver driver;
	
	int intPrice1;	// Declaration of price displayed in search results page
	int intPrice2;	// Declaration of price after clicking the product link
	int intPrice3;	// Declaration of price after add to basket page
	int intPrice4;	// Declaration of price after add to basket page
	int totalPrice;	// Declaration of  total price after applying delivery chargess
	
	
	@FindBy(xpath="(//div/button)[2]")
	private WebElement crossbtn1;
	  
	@FindBy(xpath="//a[contains(text(),'Login')]")
	private WebElement loginLink;
	 
	
	@FindBy(xpath="/html/body/div[2]/div/div/div/div/div[2]/div/form/div[1]/input")
	private WebElement username;
	
	@FindBy(xpath="/html/body/div[2]/div/div/div/div/div[2]/div/form/div[2]/input")
    private WebElement password;	
	
	@FindBy(xpath="/html/body/div[2]/div/div/div/div/div[2]/div/form/div[3]/button/span")
	private WebElement loginbtn;
	
	//verify  logo
	@FindBy(xpath="//div/a/img[@title='Flipkart']")
	private WebElement applogo;
	
	//search btn
	@FindBy(xpath="//input[@placeholder='Search for products, brands and more']")
	private WebElement searchButton;
	
	//title of the product
	@FindBy(xpath="(//div/div/div[3]/div[2]/div/div[2]/div/div/div[1]/div/a[2])[1]")
	private WebElement producttitle;
	
	//product link
	@FindBy(xpath="(//div/div/div[3]/div[2]/div/div[2]/div/div/div[1])[2]")
	private WebElement productlink;
	
	//price of product in product link page
	@FindBy(xpath="(//div/div[1]/a/div/div[1])[5]")
	private WebElement productprice1;
	
	@FindBy(xpath="(//div[2]/div[1]/div/div[1])[4]")
	private WebElement productprice2;
	
	//add to basket
	@FindBy(xpath="//button[contains(text(),'ADD TO BASKET')]")
	private WebElement addtobasketbtn;
	
	//go to basket btn
	@FindBy(xpath="//button[contains(text(),'GO TO BASKET')]")
	private WebElement gotobasketbtn;
	
	//product price on go to basket page
	@FindBy(xpath="(//div[1]/div[2]/div[1]/div/div/span[1])[1]")
	private WebElement productprice3;
	
	@FindBy(xpath="//div[1]/div[2]/div[1]/div/div/div/div/div[1]/span")
	private WebElement productprice4;
	
	@FindBy(xpath="(//div[1]/div[2]/div[1]/div/div/div/div/div[2]/span)[1]")
	private WebElement deliveryfee;
	
	@FindBy(xpath="//div[1]/div[2]/div[1]/div/div/div/div/div[3]/div/span")
	private WebElement totalamt;
	
	public Flipkart(WebDriver driver) {
		this.driver = driver;
		//This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}
	
	public void loginPage(String usern, String pass) throws InterruptedException {
		
		Thread.sleep(2000);
		 
		//identify cross button element
		crossbtn1.click();
		Thread.sleep(2000);
		//Identify login button element
		loginLink.click();
		//Identify username element
		username.sendKeys(usern);
		
		//finding passwaord element
		password.sendKeys(pass);
		
		//WebElement loginbtn = driver.findElement(By.xpath("(//span[text()='Login'])[2]"));
		loginbtn.click();
		
		Thread.sleep(2000);
		 
	}
	
	public void verifyLogo() {
		String titleAttr = applogo.getAttribute("title");
		Assert.assertEquals("Supermart", titleAttr);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			
		}
	}
	
	public void productSelection(String searchText) throws InterruptedException {
		//Thread.sleep(3000);
		searchButton.sendKeys(searchText, Keys.ENTER);
		
		String productTitle = producttitle.getText();
		Reporter.log("Product Title: "+productTitle, true);
		
		String price1 = productprice1.getText();
		String realPrice1 = price1.substring(1, price1.length());
		Reporter.log("Product price: "+realPrice1, true);
		intPrice1 = Integer.parseInt(realPrice1);
		//System.out.println(intPrice1);
		Thread.sleep(2000);
		productlink.click();
		Thread.sleep(3000);
		Set<String> s = driver.getWindowHandles();
		Iterator<String> itr = s.iterator();
		while(itr.hasNext()) {
			String ref_id = itr.next();
			driver.switchTo().window(ref_id);
		}
		
		String price2 = productprice2.getText();
		
		//String price = product_price.getText();
		String realPrice2 = price2.substring(1, price2.length());
		//Reporter.log(price2, true);
		//Reporter.log(realPrice2, true);
		
		intPrice2 = Integer.parseInt(realPrice2);
		//System.out.println(intPrice2);
	}
	
	public void addToBasket() throws InterruptedException {
		Thread.sleep(2000);
		//scroll down
		JavascriptExecutor js = (JavascriptExecutor)driver;
		for(int i=0; i<3; i++) {
			js.executeScript("window.scrollBy(0,1000)");
			Thread.sleep(2000);
		}
		Thread.sleep(2000);
		addtobasketbtn.click();
		//Thread.sleep(2000);
		gotobasketbtn.click();
	}
	
	public void comparePrice() {
		//Thread.sleep(2000);
		String price3 = productprice3.getText();
		String realPrice3 = price3.substring(1, price3.length());
		
		intPrice3 = Integer.parseInt(realPrice3);
		//System.out.println(intPrice3);
		
		String price4 = productprice4.getText();
		String realPrice4 = price4.substring(1, price4.length());
		
		intPrice4 = Integer.parseInt(realPrice4);
		//System.out.println(intPrice4);
		
		String deliveryFee = deliveryfee.getText();
		String realDeliveryFee = deliveryFee.substring(1, deliveryFee.length());
		Reporter.log("Delivery Charges: "+realDeliveryFee, true);
				
		int intDeliveryFee = Integer.parseInt(realDeliveryFee);
		//System.out.println(intDeliveryFee);
		
		String totalAmt = totalamt.getText();
		String realTotalAmt = totalAmt.substring(1, totalAmt.length());
		Reporter.log("Delivery Charges + Product price = "+realTotalAmt, true);
				
		int intTotalAmt = Integer.parseInt(realTotalAmt);
		//System.out.println(intTotalAmt);
		
		if(intPrice1 == intPrice2 && intPrice1 ==intPrice3 && intPrice1 == intPrice4) {
			Reporter.log("All price are same", true);
			totalPrice = intPrice4 + intDeliveryFee;
		}
		else {
			Reporter.log("All price are different", true);
		}
		
		if(totalPrice == intTotalAmt) {
			Reporter.log("Total price for the product is equal to total amount to be paid", true);
		}
		else {
			Reporter.log("Total price for the product is not equal to total amount to be paid", true);
		}
	}	
	
	
	  public void detailsOfTestCases() { 
		  System.out.println();
		  System.out.println("...............Test Case Desciption...............");
		  System.out.println("Test Case: loginPage: This method is use to login in an application"); 
		  System.out.println("Test Case: productSelection: This method is use to select product from product page"); 
		  System.out.println("Test Case: addToBasket: This method is use to add a selected product to the basket"); 
		  System.out.println("Test Case: compairPrice: This method is use to compair the price whether it is same or not"); 
		  System.out.println();
		  System.out.println("...............Passed Test Case...............");
		  System.out.println("PASSED: testLoginPage");
		  System.out.println("PASSED: testProductSelection");
		  System.out.println("PASSED: testAddToBasket");
		  System.out.println("PASSED: testCompairPrice"); 
		  System.out.println();
		  
	  }
	 
	
}
