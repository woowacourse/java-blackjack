package view;

import java.util.Scanner;

import domains.user.Player;

public class InputView {
	private static Scanner scanner = new Scanner(System.in);

	public static String inputPlayerNames() {
		OutputView.printInputPlayerNames();
		return scanner.nextLine();
	}

	public static String inputBettingMoney(Player player) {
		OutputView.printInputBettingMoney(player);
		return scanner.nextLine();
	}

	public static String inputYesOrNo(Player player) {
		OutputView.printNeedMoreCard(player);
		return scanner.nextLine();
	}
}
