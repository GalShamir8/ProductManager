package controller;

import common.StatusType;

public interface ModelListener {

	void modelUpdateStatus(String status, StatusType statusType, boolean visibleFlag);
}
