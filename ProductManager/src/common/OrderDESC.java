package common;

import java.util.Comparator;

/**
 * 
 * @author galsh
 */
public class OrderDESC implements Comparator<String>{

	/*
	 * Compare string by descending order
	 */
	@Override
	public int compare(String s1, String s2) {
		
		return (-1) * (s1.compareTo(s2));
	}
}
