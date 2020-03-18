package view;

import domain.user.Name;
import domain.user.Player;

import java.util.Scanner;

public class InputView {
	private static final Scanner SCANNER = new Scanner(System.in);

	private InputView() {}

	public static String inputPlayerNames() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
		return SCANNER.nextLine();
	}

	public static String inputPlayerBettingMoney(Name name) {
		System.out.println(name + "의 베팅 금액은?");
		return SCANNER.nextLine();
	}

	public static String inputPlayerIntention(Player player) {
		System.out.println(player + "는 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)");
		return SCANNER.nextLine();
	}
}
