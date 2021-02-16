package modelCommands;

import common.SortType;
import common.StatusType;
import controller.Controller;
import controller.ModelListener;

public interface ModelCommandable {
	
	/**
	 * 
	 * @param pId
	 * @param pName
	 * @param pPriceCost
	 * @param pPriceSell
	 * @param cName
	 * @param cPhone
	 * @param cPromotion
	 */
	void addProduct(String pId, String pName, String pPriceCost, String pPriceSell, String cName,
			String cPhone, boolean cPromotion);
	
	String showProducts();
	
	String showCustomers();
	
	void undo();
	
	void updateSortType(SortType sortType);
	
	void registerListener(ModelListener controller);

	String SendPromotion();

	boolean searchProduct(String ID);

	String showProduct(String ID);

	void deleteProduct(String ID);

	void deleteAll();

	String showProfit();

	int getNumOfPromoted();
}
