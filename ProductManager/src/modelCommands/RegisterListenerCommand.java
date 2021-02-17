package modelCommands;

import controller.ModelListener;
import models.Manager;

public class RegisterListenerCommand {

	private Manager manager;
	
	public RegisterListenerCommand(Manager manager) {
		this.manager = manager;
	}
	
	public void execute(ModelListener controller) {
		manager.registerListener(controller);
	}
}
