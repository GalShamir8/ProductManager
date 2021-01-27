package modelCommands;

import models.Manager;

public class ShowProductsCommand {
	
	private Manager manager;
	
	public ShowProductsCommand(Manager manager) {
		this.manager = manager;
	}

	public String execute() {
		return manager.showProducts();
	}

}
