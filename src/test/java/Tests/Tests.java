package Tests;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.GeckoDriverService;
import org.testng.annotations.Test;

import pageObjects.EbayIndexPage;
import pageObjects.EbaySearchPage;
import pageObjects.Product;

public class Tests {
	private WebDriver driver;
	@BeforeMethod 
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver");
		ChromeOptions ChromeOptions = new ChromeOptions();
	    ChromeOptions.addArguments("-incognito");
	    driver = new ChromeDriver();
	    
		
	}
	
	@Test
	public void buscarZapatos() {
		EbaySearchPage search = new EbaySearchPage(driver);
		search.searchByLowestPrice("shoes");
		search.sizeCheck();
		search.pumaCheck();
		search.freeShippingCheck();
		search.printElementCount();
		List<Product> lista = new ArrayList<Product>();
		lista = search.getProducts();
		AssertSortedByPrice(lista);
		lista.sort(new Comparator<Product>() {

			public int compare(Product producto1, Product producto2) {
				return producto1.title.compareTo(producto2.title);
			}
			
		});
		System.out.println(lista);
	}	
	public void AssertSortedByPrice(List<Product> lista) {
		for (int i = 0; i<lista.size()-1;i++) {
			Assert.assertTrue(lista.get(i).price <= lista.get(i+1).price);
		}
	}
}