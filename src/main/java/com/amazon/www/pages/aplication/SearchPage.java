package com.amazon.www.pages.aplication;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SearchPage extends AmazonPage{

	public SearchPage(WebDriver webDriver) {
		super(webDriver);
		// TODO Auto-generated constructor stub
	}

	@FindBy (how = How.XPATH, using = "//*[contains(@id, 'refinements')]")
	public WebElement leftNavContainer;
	
	@FindBy (how = How.XPATH, using = "//*[contains(@class,'refinementLink')]")
	public List<WebElement> linksLeftNavContainer;
	
	@FindBy (how = How.XPATH, using = "//*[contains(@id,'centerMinus')]//*[contains(@*, 's-result-list-parent-container') and contains(@id, 'Results')]")
	public WebElement resultBlock;
	
	/*sorted product prices*/
	@FindBy (how = How.XPATH, using = "//*[contains(@class, 'a-spacing-none')][1]//*[contains(@class, 's-price') and contains(@class,'text-bold')]")
	public List<WebElement> productPrices;
	
	@FindBy (how = How.XPATH, using = "//*[contains(@class, '-table')]")
	public WebElement sponsoredBlock;
	
	@FindBy (how = How.XPATH, using = "//*[contains(@class, 'pa-sp-table')]//a[contains(@class, 'a-size-base')]")
	public List<WebElement> sponsoredProducts;
	
	public SearchPage clickOnLinkLeftContainer(String stringCriterion){
		waitForElementPresent(leftNavContainer);
		for (int i=0; i<linksLeftNavContainer.size(); i++){
			if (linksLeftNavContainer.get(i).getText().equalsIgnoreCase(stringCriterion)){
				linksLeftNavContainer.get(i).click();
				break;
			}
		}
		waitForElementPresent(PageFactory.initElements(webDriver, SearchPage.class).resultBlock);
		return PageFactory.initElements(webDriver, SearchPage.class);
	}
	public ProductPage chooseFirstProductWithCorrectPrice(double price){
		try{
			waitForElementsPresent(PageFactory.initElements(webDriver, SearchPage.class).productPrices);
		}catch(Exception f){
			f.printStackTrace();
		}
		for (int i=0; i<productPrices.size(); i++){
			String str = productPrices.get(i).getText();
			str = str.replace("$", "");
			if (Double.parseDouble(str) < price){
				productPrices.get(i).click();
				break;
			}
		}
		waitForElementPresent(PageFactory.initElements(webDriver, ProductPage.class).productNameTitle);
		return PageFactory.initElements(webDriver, ProductPage.class);
	}
	public SearchPage navigateBack(int count){
		for (int i=0; i<count; i++){
			webDriver.navigate().back();
			waitForElementPresent(PageFactory.initElements(getWebDriver(), SearchPage.class).searchField);
		}
		try {
			waitForElementPresent(PageFactory.initElements(getWebDriver(), SearchPage.class).sponsoredBlock);
		}catch (Exception n){
			n.printStackTrace();
		}
		return PageFactory.initElements(getWebDriver(), SearchPage.class);
	}
	public CartPage addSponsoredProduct(int countOfProduct){
		int countOfAddedProduct = 0;
		ProductPage pr = PageFactory.initElements(webDriver, ProductPage.class);
		for (int i=0; i<sponsoredProducts.size(); i++){
			sponsoredProducts.get(i).click();			
			pr.addProductToCart();
			navigateBack(2);
			countOfAddedProduct++;
			waitForElementPresent(sponsoredBlock);
			if (countOfAddedProduct == countOfProduct){
				break;
			}
		}
		return PageFactory.initElements(webDriver, CartPage.class);
	}
}
