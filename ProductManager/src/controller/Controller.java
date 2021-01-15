package controller;

import models.eNums.eSortType;
import models.eNums.eStatusType;
import views.ProductManagerViewable;

public class Controller implements ViewListener{
	private ProductManagerViewable view;

	//temp c'tor
	public Controller(ProductManagerViewable view /*, model*/) {
		//View
		this.view = view;
		view.registerListener(this);
		/*
		 * model
		 */

	}

	@Override
	public void viewUpdateSortType(eSortType sortType) {
		//Test
		view.updateStatus("succsess", eStatusType.eSuccess);
	}

	@Override
	public void viewAskForUndo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void viewAskToAddProduct(String pId, String pName, String pPriceCost, String pPriceSell, 
			String cName, String cPhone, boolean cPromotion) {
		// TODO Auto-generated method stub

	}

	@Override
	public String viewAskForShowProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String viewAskForShowCustomers() {
		// TODO Auto-generated method stub
		return null;
	}
}
