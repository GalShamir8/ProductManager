package controller;

import common.StatusType;

public interface ModelListener {
	/**
	 * 
	 * @param status
	 * @param statusType
	 * 
	 * update the status in the view
	 */
	void modelUpdateStatus(String status, StatusType statusType);
}
