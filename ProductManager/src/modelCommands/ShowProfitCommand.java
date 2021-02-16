package modelCommands;

import models.Manager;

public class ShowProfitCommand {

	private Manager manager;
	
	public ShowProfitCommand(Manager manager) {
		this.manager = manager;
	}
	
	public String execute() {
		return manager.showProfit();
	}
}
