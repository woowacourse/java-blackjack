package view;

import java.util.List;
import java.util.Scanner;

import domain.gamer.Player;

public class InputView {
	private static final String PLAYER_NAME_DELIMITER = ",";
	private static final String COMMAND_YES = "y";
	private static final String COMMAND_NO = "n";
	private final Scanner scanner;

	public InputView(Scanner scanner) {
		this.scanner = scanner;
	}

	public List<String> readPlayerNames() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
		String names = scanner.nextLine().replace(" ", "");

		if (names.startsWith(PLAYER_NAME_DELIMITER) || names.endsWith(PLAYER_NAME_DELIMITER)) {
			throw new IllegalArgumentException();
		}
		return List.of(names.split(PLAYER_NAME_DELIMITER));
	}

	public String readHitOrStand(Player player) {
		System.out.println(
			String.format("%s는 한장의 카드를 더 받겠습니까?(예는 %s, 아니오는 %s)", player.getName(), COMMAND_YES, COMMAND_NO));
		String command = scanner.nextLine();
		validateCommand(command);

		return command;
	}

	private void validateCommand(String command) {
		if (!List.of(COMMAND_YES, COMMAND_NO).contains(command)) {
			throw new IllegalArgumentException(String.format("%s 또는 %s만 입력할 수 있습니다.", COMMAND_YES, COMMAND_NO));
		}
	}

}
