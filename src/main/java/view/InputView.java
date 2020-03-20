package view;

import java.util.Scanner;

import domain.participant.Name;
import domain.participant.Player;

public class InputView {
	public static final String COMMA = ",";
	private static Scanner scanner = new Scanner(System.in);

	public static String[] inputUserNames() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표(,)기준으로 분리)");
		return scanner.nextLine().split(COMMA);

	}

	public static String inputHitOrNot(Player player) {
		System.out.println(String.format("%s은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", player.getName().getName()));
		return scanner.nextLine();
	}

	public static String inputBettingMoney(Name name) {
		System.out.println("\n" + name.getName() + "의 베팅 금액은?");
		return scanner.nextLine();
	}
}
