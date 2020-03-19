package blackjack.view;

import blackjack.domain.user.Name;
import blackjack.domain.user.Playable;

import java.util.Scanner;

public final class InputView {
	private static final Scanner scanner = new Scanner(System.in);
	private static final String NEW_LINE = System.lineSeparator();

	public static String inputPlayerNames() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
		return scanner.nextLine();
	}

	public static String inputYesOrNo(Playable player) {
		System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
				+ NEW_LINE, player.getName().getString());
		return scanner.nextLine();
	}

	public static String inputBettingMoney(Name name) {
		System.out.println(name.getString() + "의 배팅금액은?");
		return scanner.nextLine();
	}
}
