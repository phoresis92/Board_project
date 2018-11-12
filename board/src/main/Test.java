package main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.print("input number : ");
		
		/*try {
		int	a = sc.nextInt();
		
		}catch(InputMismatchException e) {
			System.out.println("숫자가 아닙니다!");
		}*/
		
		
		int b = 0;
		while(sc.hasNextInt()) {
			b = sc.nextInt();
		}
		System.out.println(b);
	}

}
