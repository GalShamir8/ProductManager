package modelCommands;

import models.Manager;

public class SendPromotionCommand {
	 private Manager manager;
	 
	 public SendPromotionCommand(Manager manager) {
		 this.manager = manager;
	}
	 
	 public void execute() {
		 manager.SendPromotion();
	 }
	 
}
