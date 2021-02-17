package modelCommands;

import common.SortType;
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
	/**
	 * 
	 * @return A String that contain all the products
	 */
	String showProducts();
	/**
	 * 
	 * @return A String that contain all the customers
	 */
	String showCustomers();
	/**
	 * undo last insertion
	 */
	void undo();
	/**
	 * 
	 * @param sortType
	 * 		eLexASC
	 * 		eLexDESC
	 * 		eInsert
	 */
	void updateSortType(SortType sortType);
	
	void registerListener(ModelListener controller);
	/**
	 * @return String the contains all the promoted customers
	 * 
	 */
	String SendPromotion();
	/**
	 * @return true if the product exists
	 * @param ID
	 * product id to be searched
	 */
	boolean searchProduct(String ID);
	/**
	 * @return product toString
	 * @param ID
	 * product id to be searched
	 */
	String showProduct(String ID);
	/**
	 * @param ID
	 * product id to be searched
	 * delete this product
	 */
	void deleteProduct(String ID);
	/**
	 * Delete all the content in the model and file
	 */
	void deleteAll();
	/**
	 * 
	 * @return String that contains all products profit
	 */
	String showProfit();
	/**
	 * 
	 * @return number of customers promoted
	 */
	int getNumOfPromoted();
}
