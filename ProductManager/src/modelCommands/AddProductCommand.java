package modelCommands;

import models.Manager;
import models.Product;

public class AddProductCommand {

	private Manager manager;

	public AddProductCommand(Manager manager) {
		this.manager = manager;
	}

	public void execute(String pId, String pName, String pPriceCost, String pPriceSell, String cName, String cPhone,
			boolean cPromotion) {
		manager.addProduct(pId, pName, pPriceCost, pPriceSell, cName, cPhone, cPromotion);
	}
}
