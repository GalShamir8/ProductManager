package modelCommands;

import models.Manager;

public class UndoCommand {

	private Manager manager;
	
	public UndoCommand(Manager manager) {
		this.manager = manager;
	}
	
	public void execute() {
		manager.undo();
	}
}
