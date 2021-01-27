package modelCommands;

import common.SortType;
import common.StatusType;
import controller.Controller;
import controller.ModelListener;
import models.Manager;

public class ModelCommand implements ModelCommandable{
	
	private AddProductCommand addProductCommand;
	private ShowProductsCommand showProductsCommand;
	private ShowCustomerCommand showCustomerCommand;
	private UpdateSortCommand updateSortCommand;
	private RegisterListenerCommand registerListenerCommand;
	private UndoCommand undoCommand;
	private SendPromotionCommand sendPromotionCommand;
	private SearchProductCommand searchProductCommand;
	private ShowProductCommand showProductCommand;
	private DeleteProductCommand deleteProductCommand;
	private DeleteAllCommand deleteAllCommand;
	
	
	public ModelCommand(Manager manager) {
		this.addProductCommand = new AddProductCommand(manager);
		this.showProductsCommand = new ShowProductsCommand(manager);
		this.showCustomerCommand = new ShowCustomerCommand(manager);
		this.updateSortCommand = new UpdateSortCommand(manager);
		this.registerListenerCommand = new RegisterListenerCommand(manager);
		this.undoCommand = new UndoCommand(manager);
		this.sendPromotionCommand = new SendPromotionCommand(manager);
		this.searchProductCommand = new SearchProductCommand(manager);
		this.showProductCommand = new ShowProductCommand(manager);
		this.deleteProductCommand = new DeleteProductCommand(manager);
		this.deleteAllCommand = new DeleteAllCommand(manager);
		
	}
	
	@Override
	public void addProduct(String pId, String pName, String pPriceCost, String pPriceSell, String cName, String cPhone,
			boolean cPromotion) {
		addProductCommand.execute(pId, pName, pPriceCost, pPriceSell, cName, cPhone, cPromotion);
	}

	@Override
	public String showProducts() {
		return showProductsCommand.execute();
	}

	@Override
	public String showCustomers() {
		return showCustomerCommand.execute();
	}

	@Override
	public void undo() {
		undoCommand.execute();
	}

	@Override
	public void updateSortType(SortType sortType) {
		updateSortCommand.execute(sortType);
	}

	@Override
	public void registerListener(ModelListener controller) {
		registerListenerCommand.execute(controller);
	}

	@Override
	public void SendPromotion() {
		sendPromotionCommand.execute();
	}

	@Override
	public boolean searchProduct(String ID) {
		return searchProductCommand.execute(ID);
	}

	@Override
	public String showProduct(String ID) {
		return showProductCommand.execute(ID);
	}

	@Override
	public void deleteProduct(String ID) {
		deleteProductCommand.execute(ID);
	}

	@Override
	public void deleteAll() {
		deleteAllCommand.execute();
	}

}
