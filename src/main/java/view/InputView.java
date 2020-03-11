package view;

import domain.gamer.Player;

import java.util.Scanner;

public class InputView {
	public static final Scanner scanner = new Scanner(System.in);

	public static String inputAsPlayerName() {
		OutputView.printPlayerNamesGuide();
		return scanner.nextLine();
	}

	public static String inputAsDrawable(Player player) {
		System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", player.getName());
		return scanner.next();
	}
}
