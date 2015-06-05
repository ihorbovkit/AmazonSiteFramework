package com.amazon.www.pages.aplication;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.amazon.www.pages.Page;

public class ProtectYourPurchasePage extends Page{

	public ProtectYourPurchasePage(WebDriver webDriver) {
		super(webDriver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy (how = How.XPATH, using = "//*[contains(@id,'siNoCoverage-announce')]")
	public WebElement notNowButtonProtectYourPurchase;
	
	public boolean isCloseButtonProtectYourPurchaseDisplayed(){
		return notNowButtonProtectYourPurchase.isDisplayed();
	}
	public ViewPage clickOnNotNowButtonProtectYourPurchase(){
		waitForElementPresent(notNowButtonProtectYourPurchase);
		notNowButtonProtectYourPurchase.click();
		return PageFactory.initElements(webDriver, ViewPage.class);
	}
}
