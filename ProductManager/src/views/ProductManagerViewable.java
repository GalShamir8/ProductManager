package views;

import common.*;
import controller.ViewListener;

/**
 * 
 * @author galsh
 *
 */
public interface ProductManagerViewable {
	/**
	 * Adding a new product to our model
	 */
	void addProductToModel();

	/**
	 * Shows all products in the system
	 */
	void showAllProducts();

	/**
	 * Shows all customers in the system
	 */
	void showAllCustomers();

	/**
	 * Undo the last insertion command by delete the last product
	 */
	void undoLastInsertion();

	/**
	 * updates the status; msg by eStatusType
	 * visible case->if there is need to leave the middle window as is
	 */
	void updateStatus(String msg, StatusType type, boolean visibleCase);

	/**
	 * Sending promotion to assign customers
	 */
	void customerPromotion();
	/**
	 * Show the profit of the store from all the product that been sell
	 */
	void showProfit();
	/**
	 * Searching a product by his id
	 */
	void searchProduct();
	/**
	 * Delete all the content
	 */
	void deleteAll();
	
	void registerListener(ViewListener listner);


}
