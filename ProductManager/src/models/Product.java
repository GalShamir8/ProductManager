package models;


public class Product {
	private final String defaultName = "Blank";
	private final int defaultPrice = 0;
	private Exception priceException = new Exception("Illegal price:\nPrice can not be negative");

	private String productId;
	private String name;
	private int costPrice;
	private int sellPrice;
	private Customer customer;

	/**
	 * 
	 * @param name
	 * @param costPrice
	 * @param sellPrice
	 * @throws Exception 
	 */
	public Product(String productId,String name, String costPrice, String sellPrice, Customer customer ) throws Exception {
		setId(productId);
		setName(name);
		setCostPrice(costPrice);
		setSellPrice(sellPrice);
		this.customer = customer;
	}
	/**
	 * 
	 * @param productId2
	 * @throws Exception 
	 */
	private void setId(String productId) throws Exception {
		if(productId.isBlank())
			throw new Exception("Product id can not be empty");
		
		this.productId = productId;
	}
	/**
	 * 
	 * @param name
	 * @throws Exception 
	 */
	private void setName(String name) throws Exception {
		if(name.isBlank())
			name = defaultName;
		else {
			for(int i = 0 ; i < name.length(); i++) {
				if(!Character.isLetter(name.charAt(i)) && !Character.isWhitespace(name.charAt(i)))
					throw new Exception("Invalid Product name:\nName must contains only letters");
			}

			this.name = name;
		}
	}

	/**
	 * 
	 * @param costPrice
	 * @throws Exception 
	 */
	private void setCostPrice(String costPrice) throws Exception {
		if(costPrice.isBlank())
			this.costPrice = defaultPrice;
		else {
			try {
				if(Integer.parseInt(costPrice) >= 0)
					this.costPrice = Integer.parseInt(costPrice);
				else
					throw priceException;

			}catch(Exception e) {
				throw new Exception("Illegal price:\nPrice must contains only digits");
			}
		}
	}

	/**
	 * 
	 * @param sellPrice
	 * @throws Exception 
	 */
	private void setSellPrice(String sellPrice) throws Exception {
		if(sellPrice.isBlank())
			this.sellPrice = defaultPrice;
		else {
			try {
				if(Integer.parseInt(sellPrice) >= 0)
					this.sellPrice = Integer.parseInt(sellPrice);
				else
					throw priceException;

			}catch(Exception e) {
				throw new Exception("Illegal price:\nPrice must contains only digits");
			}
		}
	}

	public String getId() {
		return productId;
	}

	public String getName() {
		return name;
	}

	public int getCostPrice() {
		return costPrice;
	}

	public int getSellPrice() {
		return sellPrice;
	}

	public Customer getCustomer() {
		return customer;
	}

	@Override
	public String toString(){
		return name + ", Cost price: " + costPrice + ", Sell price: " + sellPrice + ", Profit: " + (sellPrice - costPrice);
	}
}
