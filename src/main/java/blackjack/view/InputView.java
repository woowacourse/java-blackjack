package blackjack.view;

import java.util.List;
import java.util.Scanner;

import blackjack.domain.gamer.Player;
import blackjack.view.command.Command;

public class InputView {
	private static final InputView INSTANCE = new InputView(new Scanner(System.in));

	private static final String PLAYER_NAME_DELIMITER = ",";
	private final Scanner scanner;

	private InputView(Scanner scanner) {
		this.scanner = scanner;
	}

	public static InputView getInstance() {
		return INSTANCE;
	}

	public List<String> readPlayerNames() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
		String names = scanner.nextLine().replace(" ", "");

		validatePlayerNamesFormat(names);
		return List.of(names.split(PLAYER_NAME_DELIMITER));
	}

	private void validatePlayerNamesFormat(String names) {
		if (names.startsWith(PLAYER_NAME_DELIMITER) || names.endsWith(PLAYER_NAME_DELIMITER)) {
			throw new IllegalArgumentException("플레이어의 이름은 쉼표(,)로 시작하거나 끝날 수 없습니다.");
		}
	}

	public Command readHitOrStand(Player player) {
		System.out.println(
			String.format("%s는 한장의 카드를 더 받겠습니까?(예는 %s, 아니오는 %s)", player.getName(), Command.YES, Command.NO));
		String command = scanner.nextLine();

		return Command.fromText(command);
	}
}
