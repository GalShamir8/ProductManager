package common;

import java.util.Comparator;

/**
 * @author galsh
 */

public class OrderByInsert implements Comparator<String>{

	/*
	 * Compare Strings by insert--> always return 1
	 */
	@Override
	public int compare(String o1, String o2) {
		return 1;
	}

}
