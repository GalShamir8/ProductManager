package modelCommands;

import models.Manager;

public class ShowCustomerCommand {

	private Manager manager;

	public ShowCustomerCommand(Manager manager) {
		this.manager = manager;
	}

	public String execute() {
		return manager.showCustomers();
	}

}
