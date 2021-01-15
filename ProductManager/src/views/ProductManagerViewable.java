package views;

import controller.ViewListener;
import models.eNums;
/**
 * 
 * @author galsh
 *
 */
public interface ProductManagerViewable extends eNums{
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

	void registerListener(ViewListener listner);
	/**
	 * updates the status; msg
	 * 					   by 	eStatusType
	 */
	void updateStatus(String msg, eStatusType type);
}
