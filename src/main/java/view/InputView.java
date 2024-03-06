package view;

import java.util.List;
import java.util.Scanner;

import domain.Player;

public class InputView {
	private final Scanner scanner;

	public InputView(Scanner scanner) {
		this.scanner = scanner;
	}

	public List<String> readPlayerNames() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
		String names = scanner.nextLine().replace(" ", "");

		if (names.startsWith(",") || names.endsWith(",")) {
			throw new IllegalArgumentException();
		}
		return List.of(names.split(","));
	}

	/**
	 * pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
	 * y
	 * pobi카드: 2하트, 8스페이드, A클로버
	 * pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
	 * n
	 * jason은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
	 * n
	 * jason카드: 7클로버, K스페이드
	 *
	 * 딜러는 16이하라 한장의 카드를 더 받았습니다.
	 */
	public String readHitOrStand(Player player) {
		System.out.println(
			String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", player.getName()));
		String command = scanner.nextLine();
		validateCommand(command);

		return command;
	}

	private void validateCommand(String command) {
		if (!List.of("y", "n").contains(command)) {
			throw new IllegalArgumentException("y 또는 n만 입력할 수 있습니다.");
		}
	}

}
