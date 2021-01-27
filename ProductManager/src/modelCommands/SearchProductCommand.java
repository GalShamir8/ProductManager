package modelCommands;

import models.Manager;

public class SearchProductCommand {

	private Manager manager;

	public SearchProductCommand(Manager manager) {
		this.manager = manager;
	}

	public boolean execute(String ID) {
		return manager.searchProduct(ID);
	}
}
