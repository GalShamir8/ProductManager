package modelCommands;

import models.Manager;

public class GetPromotedCommand {
	
	private Manager manager;
	
	public GetPromotedCommand(Manager manager) {
		this.manager = manager;
	}
	
	public int execute() {
		return manager.getNumOfPromoted();
	}
}
