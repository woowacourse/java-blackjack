package blackjack.view;

import static blackjack.domain.participant.Gamer.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import blackjack.domain.participant.Player;

public class InputView {
	private static final String ERROR_MESSAGE_OF_Y_OR_N = "y 혹은 n 만 입력하여 주십시오.";
	private static final String AGREE = "y";
	private static final String DISAGREE = "n";

	static Scanner scanner = new Scanner(System.in);

	public static boolean isContinueDraw(Player player) {
		System.out.println(player.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
		String input = scanner.nextLine();
		if (AGREE.equals(input)) {
			return true;
		}
		if (DISAGREE.equals(input)) {
			return false;
		}
		throw new IllegalArgumentException(ERROR_MESSAGE_OF_Y_OR_N);
	}

	public static List<String> enterNames() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
		return Arrays.asList(scanner.nextLine().split(COMMA_DELIMITER));
	}

	public static String enterBetting(Player player) {
		System.out.println(player.getName() + "의 배팅 금액은?");
		return scanner.nextLine();
	}
}
