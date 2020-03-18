package view;

import java.util.Scanner;

public class InputView {
	private static Scanner scanner = new Scanner(System.in);

	public static String inputPlayerNames() {
		return scanner.nextLine();
	}

	public static String inputBettingMoney() {
		return scanner.nextLine();
	}

	public static String inputYesOrNo() {
		return scanner.nextLine();
	}
}
