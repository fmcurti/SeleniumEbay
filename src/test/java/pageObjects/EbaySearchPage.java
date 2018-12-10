package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Product;

public class EbaySearchPage {
	private By sortByPrice;
	private By sortDropDown;
	private By prices;
	private By titles;
	private By sizeCheckBox;
	private By pumaCheckBox;
	private By freeShippingCheckBox;
	private By items;
	private By elementCount;
	private WebDriverWait wait;
	private WebDriver driver;
	
	
	public EbaySearchPage(WebDriver driver) {
		sortByPrice = By.xpath("//*[@id=\'w4-w1\']/div/div/ul/li[4]/a/span/text()");
		sortDropDown = By.xpath("//*[@id='DashSortByContainer']/ul[1]");		
		prices = By.className("s-item__price");
		titles = By.className("s-item__title");
		elementCount = By.xpath("//*[@id=\'w4\']/div[2]/div[1]/div[1]/h1");
		sizeCheckBox = By.xpath("//span[text()='10']");
		pumaCheckBox = By.xpath("//*[text()='PUMA']");
		freeShippingCheckBox = By.xpath("//span[text()[contains(.,'Envío internacional')]]");
		items = By.className("sresult gvresult");
		this.driver = driver;
		wait = new WebDriverWait(driver,10);
	}
	
	public void searchByLowestPrice(String product) {
		/*Actions action = new Actions(driver);
		//action.moveToElement(wait.until(ExpectedConditions.presenceOfElementLocated(sortDropDown))).perform();
		wait.until(ExpectedConditions.presenceOfElementLocated(sortDropDown)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(sortByPrice)).click();
		//return driver.findElement(price).getText();*/
		driver.navigate().to("https://www.ebay.com/sch/i.html?_from=R40&_trksid=m570.l1313&_nkw="+product+"&_sacat=0&_sop=15");		
	}
	
	public void sizeCheck() {
		wait.until(ExpectedConditions.presenceOfElementLocated(sizeCheckBox)).click();
	}
	
	public void pumaCheck() {
		wait.until(ExpectedConditions.presenceOfElementLocated(pumaCheckBox)).click();
	}
	
	public void freeShippingCheck() {
		wait.until(ExpectedConditions.presenceOfElementLocated(freeShippingCheckBox)).click();
	}
	public void printElementCount() {
		WebElement countElem = wait.until(ExpectedConditions.presenceOfElementLocated(elementCount));
		System.out.println("Resultados: " + countElem.getText());
	}
	public List<Product> getProducts() {
		List<WebElement> prices =  driver.findElements(this.prices);
		List<WebElement> titles = driver.findElements(this.titles);
		List<Product> productos = new ArrayList<Product>();
		float precioParseado;
		for (int i = 0;i<5;i++) {
			String precio = prices.get(i).getText();
			int index = precio.indexOf(" ");
			if (index != -1) {
				precioParseado = Float.valueOf(precio.substring(0, index).replaceAll("[^\\.0123456789]",""));
			} else {
				precioParseado = Float.valueOf(precio.replaceAll("[^\\.0123456789]",""));

			}
				
			productos.add(new Product(titles.get(i).getText(),precioParseado));
		}
		return productos;
		
	}

}


