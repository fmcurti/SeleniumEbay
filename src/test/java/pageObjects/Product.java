package pageObjects;

public class Product {
	public String title;
	public Float price;
	 
	public Product(String title, Float precio){
		this.title = title;
		this.price = precio;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Product [" + (title != null ? "title=" + title + ", " : "") + (price != null ? "price=" + price : "")
				+ "]";
	}
	
}
