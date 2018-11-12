package view;

import java.util.Scanner;

public class Isint {

	public static boolean isInt(String a) {
		try {
			Integer.parseInt(a);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	

}
