package common;

import java.util.Comparator;

/**
 * @author galsh
 */

public class OrderByInsert implements Comparator<String>{

	/*
	 * Compare Strings by insert--> equals 0 else 1
	 */
	@Override
	public int compare(String s1, String s2) {
		if(s1.compareTo(s2) == 0)
			return 0;
		return 1;
	}

}
