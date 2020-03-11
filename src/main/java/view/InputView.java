package view;

import java.util.Scanner;

import domain.participant.Player;

public class InputView {
	private static final String NEW_LINE = System.lineSeparator();
	private static final Scanner scanner = new Scanner(System.in);

	public static String inputUserNames() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
		return scanner.nextLine();
	}

	public static String inputReceiveMore(Player player) {
		StringBuilder sb = new StringBuilder();
		sb.append(player.getName())
			.append("는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
		System.out.println(sb);
		return scanner.nextLine();
	}
}