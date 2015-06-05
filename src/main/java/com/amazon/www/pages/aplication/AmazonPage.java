package com.amazon.www.pages.aplication;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.amazon.www.pages.Page;

public class AmazonPage extends Page{

	/**
	 * This class contains footer and header. All other pages extends this class
	 */
	public AmazonPage(WebDriver webDriver) {
		super(webDriver);
		// TODO Auto-generated constructor stub
	}
	@FindBy (how = How.XPATH, using = "//*[contains(@class, 'nav-input') and @id]")
	public WebElement searchField;
	
	@FindBy (how = How.XPATH, using = "//*[contains(@class, 'nav-input') and contains(@type, 'submit')]")
	public WebElement searchButton;
	
	@FindBy (how = How.XPATH, using = "//*[@id='nav-cart']//*[@class='nav-line-2']")
	public WebElement cart;
	
	@FindBy (how = How.XPATH, using = "//a[contains(@id,'nav-cart-menu-button')]")
	public WebElement viewCartButton;
	
	public SearchPage searchProduct(String productName){
		waitForElementPresent(searchField);
		searchField.sendKeys(productName);
		searchButton.click();
		waitForElementPresent(searchField);
		return PageFactory.initElements(webDriver, SearchPage.class);
	}
	public CartPage clickOnCart(){
		waitForElementPresent(cart);
		((JavascriptExecutor) webDriver).executeScript("document.getElementById('nav-flyout-cart').style.display='block'");
		waitForElementPresent(viewCartButton);
		viewCartButton.click();
		waitForElementsPresent(PageFactory.initElements(webDriver, CartPage.class).productsInCart);
		return PageFactory.initElements(webDriver, CartPage.class);
	}

}
