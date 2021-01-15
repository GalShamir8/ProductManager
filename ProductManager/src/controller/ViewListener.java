package controller;

import models.eNums.eSortType;

public interface ViewListener {
	/**
	 * 
	 * @param sortType
	 * 		eLexASC
	 * 		eLexDESC
	 * 		eInsert
	 */
	void viewUpdateSortType(eSortType sortType);
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
}
