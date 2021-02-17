package modelCommands;

import common.SortType;
import models.Manager;

public class UpdateSortCommand {

	private Manager manager;
	
	public UpdateSortCommand(Manager manager) {
		this.manager = manager;
	}
	
	public void execute(SortType sortType) {
		manager.updateSortType(sortType);
	}
}
