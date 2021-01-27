package modelCommands;

import models.Manager;

public class DeleteAllCommand {

	private Manager manager;

	public DeleteAllCommand(Manager manager) {
		this.manager = manager;
	}

	public void execute() {
		manager.deleteAll();
	}
}
