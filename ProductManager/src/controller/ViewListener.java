package controller;

import common.*;
public interface ViewListener {
	/**
	 * 
	 * @param sortType
	 * 		eLexASC
	 * 		eLexDESC
	 * 		eInsert
	 */
	void viewUpdateSortType(SortType sortType);
	/**
	 * undo last insertion
	 */
	void viewAskForUndo();
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
	void viewAskToAddProduct(String pId, String pName, String pPriceCost, String pPriceSell, String cName,
			String cPhone, boolean cPromotion);
	/**
	 * 
	 * @return A String that contain all the products
	 */
	String viewAskForShowProducts();
	/**
	 * 
	 * @return A String that contain all the customers
	 */
	String viewAskForShowCustomers();
	/**
	 * @return all the promoted customers
	 * 
	 * @param promotion
	 * 		promotion->msg to be sent to all promoted customers
	 */
	String viewAskForUpdatePromotion(String promotion);
	/**
	 * @return true if the product exists
	 * @param ID
	 * product id to be searched
	 */
	boolean viewAskForSearchProduct(String ID);
	/**
	 * @return product toString
	 * @param ID
	 * product id to be searched
	 */
	String viewAskForShowProduct(String ID);
	/**
	 * @param ID
	 * product id to be searched
	 * delete this product
	 */
	void viewAskToDeleteProduct(String ID);
	/**
	 * Delete all the content in the model
	 */
	void viewAskToDeleteAll();
	/**
	 * 
	 * @return all product profit
	 */
	String viewAskForShowProfit();
	/**
	 * 
	 * @return num of customers promoted
	 */
	int viewAskForNumOfPromoted();
}
