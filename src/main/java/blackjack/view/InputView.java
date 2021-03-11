package blackjack.view;

import static blackjack.domain.participant.Gamer.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import blackjack.domain.participant.Player;

public class InputView {
	static Scanner scanner = new Scanner(System.in);

	public static String isContinueDraw(Player player) {
		System.out.println(player.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
		return scanner.nextLine();
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
