package view;

import java.util.Scanner;

public class InputView {
	public static final Scanner scanner = new Scanner(System.in);

	public static String inputAsPlayerName() {
		return scanner.nextLine();
	}
}
