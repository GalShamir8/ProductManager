package modelCommands;

import models.Manager;

public class ShowProductCommand {

	private Manager manager;

	public ShowProductCommand(Manager manager) {
		this.manager = manager;
	}

	public String execute(String ID) {
		return manager.showProduct(ID);
	}
}
