package com.amazon.www.pages.aplication;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends AmazonPage{

	private static String productName;
	
	public String getProductName() {
		return productName;
	}

	public ProductPage(WebDriver webDriver) {
		super(webDriver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy (how = How.XPATH, using = "//*[@id='title']/*")
	public WebElement productNameTitle;
	
	@FindBy (how = How.XPATH, using = "//*[contains(@id,'add-to-cart-button') and @name='submit.add-to-cart']")
	public WebElement buttonAddToCart;
	
	public ViewPage addProductToCartAndRememberName(){
		waitForElementPresent(productNameTitle);
		productName = productNameTitle.getText();
		addProductToCart();
		return PageFactory.initElements(webDriver, ViewPage.class);
	}
	
	public ViewPage addProductToCart(){
		waitForElementPresent(productNameTitle);
		buttonAddToCart.click();
		ProtectYourPurchasePage pr;
		pr = PageFactory.initElements(webDriver, ProtectYourPurchasePage.class);
		webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		
		try{
			if (pr.isCloseButtonProtectYourPurchaseDisplayed() == true){
				pr.clickOnNotNowButtonProtectYourPurchase();
			}
		}catch (NoSuchElementException e){
			waitForElementPresent(PageFactory.initElements(webDriver, ViewPage.class).searchField);
		}
		return PageFactory.initElements(webDriver, ViewPage.class);
	}

}
