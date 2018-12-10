package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EbayIndexPage {
	private By searchField;
	private By searchBtn;
	private WebDriver driver;
	
	public EbayIndexPage(WebDriver driver) {
		searchField = By.name("_nkw");
		searchBtn = By.id("gh-btn");
		this.driver = driver;
	}
	
	public void search(String searchQuery) {
		driver.navigate().to("http://ebay.com");
		driver.findElement(searchField).sendKeys(searchQuery); 
		driver.findElement(searchBtn).click();
		
	}
	
}
