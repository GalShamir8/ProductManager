package modelCommands;

import models.Manager;

public class DeleteProductCommand {

	private Manager manager;

	public DeleteProductCommand(Manager manager) {
		this.manager = manager;
	}

	public void execute(String ID) {
		manager.deleteProduct(ID);
	}
}
