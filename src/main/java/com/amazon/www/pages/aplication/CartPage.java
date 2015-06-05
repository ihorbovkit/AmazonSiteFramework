package com.amazon.www.pages.aplication;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends AmazonPage{

	public CartPage(WebDriver webDriver) {
		super(webDriver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy (how = How.XPATH, using = "//*[contains(@class,'a-declarative')]//input[@value = 'Save for later']")
	public List<WebElement> productsInCart;

	@FindBy (how = How.XPATH, using = "//*[contains(@value, 'Delete')]")
	public List<WebElement> deleteLinks; 
	
	public int countOfProductInCart(){
		return productsInCart.size();
	}
	
	public CartPage deleteProductFromCart(String nameOfProduct){
		waitForElementPresent(searchField);
		for (int i=0; i<deleteLinks.size(); i++){
			if (deleteLinks.get(i).getAttribute("aria-label").contains(nameOfProduct)){
				deleteLinks.get(i).click();
				waitForElementsPresent(PageFactory.initElements(webDriver, CartPage.class).productsInCart);
				break;
			}
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		waitForElementsPresent(productsInCart);
		return PageFactory.initElements(webDriver, CartPage.class);
	} 
}
