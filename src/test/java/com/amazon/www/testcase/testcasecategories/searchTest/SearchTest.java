package com.amazon.www.testcase.testcasecategories.searchTest;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.amazon.www.pages.aplication.CartPage;
import com.amazon.www.pages.aplication.ProductPage;
import com.amazon.www.pages.aplication.SearchPage;
import com.amazon.www.testcase.TestBase;

public class SearchTest extends TestBase{
	
	@Test
	public void SearchForLaptopTest(){
		SearchPage search = home.searchProduct("DELL laptop").clickOnLinkLeftContainer("Under $500");
		search.chooseFirstProductWithCorrectPrice(500).addProductToCartAndRememberName();
		ProductPage pr = PageFactory.initElements(webDriver, ProductPage.class);
		search.navigateBack(3).addSponsoredProduct(2).clickOnCart().deleteProductFromCart(pr.getProductName());
		Assert.assertEquals(PageFactory.initElements(webDriver, CartPage.class).countOfProductInCart(), 2, "Count of product in cart");
	}
	
	
}
