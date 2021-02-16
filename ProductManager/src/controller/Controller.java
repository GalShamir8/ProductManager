package controller;

import common.*;
import modelCommands.ModelCommand;
import modelCommands.ModelCommandable;
import models.Manager;
import views.ProductManagerViewable;

public class Controller implements ViewListener, ModelListener{
	private ProductManagerViewable view;
	private ModelCommandable allCommands;

	//c'tor
	public Controller(ProductManagerViewable view) {
		//View
		this.view = view;
		this.view.registerListener(this);
		//Model
		this.allCommands = new ModelCommand(Manager.getInstance());
		this.allCommands.registerListener(this);
	}

	@Override
	public void viewUpdateSortType(SortType sortType) {
		allCommands.updateSortType(sortType);
	}

	@Override
	public void viewAskForUndo() {
		allCommands.undo();
	}

	@Override
	public void viewAskToAddProduct(String pId, String pName, String pPriceCost, String pPriceSell, 
			String cName, String cPhone, boolean cPromotion) {
		allCommands.addProduct(pId, pName, pPriceCost, pPriceSell, cName, cPhone, cPromotion);
	}

	@Override
	public String viewAskForShowProducts() {
		return allCommands.showProducts();
	}

	@Override
	public String viewAskForShowCustomers() {
		return allCommands.showCustomers();
	}

	@Override
	public void modelUpdateStatus(String status, StatusType statusType, boolean visibleFlag) {
		this.view.updateStatus(status, statusType, visibleFlag);
	}

	@Override
	public String viewAskForUpdatePromotion(String promotion) {
		return allCommands.SendPromotion();
	}

	@Override
	public boolean viewAskForSearchProduct(String ID) {
		return allCommands.searchProduct(ID);
	}

	@Override
	public String viewAskForShowProduct(String ID) {
		return allCommands.showProduct(ID);
	}

	@Override
	public void viewAskToDeleteProduct(String ID) {
		allCommands.deleteProduct(ID);
	}

	@Override
	public void viewAskToDeleteAll() {
		allCommands.deleteAll();
	}

	@Override
	public String viewAskForShowProfit() {
		return allCommands.showProfit();
	}

	@Override
	public int viewAskForNumOfPromoted() {
		return allCommands.getNumOfPromoted();
	}

}
