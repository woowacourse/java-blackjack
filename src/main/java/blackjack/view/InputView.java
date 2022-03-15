package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

import blackjack.dto.PlayerTurnDto;

public class InputView {

	private static final String PLAYER_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
	private static final String DRAW_ONE_MORE_CARD = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
	private static final String NAME_DISTRIBUTOR = ",";

	private final Scanner scanner;

	public InputView(Scanner scanner) {
		this.scanner = scanner;
	}

	public List<String> requestPlayerName() {
		final String rawNames = inputData(InputValidator::validatePlayerName,
				() -> System.out.println(PLAYER_NAME));
		return Arrays.asList(rawNames.split(NAME_DISTRIBUTOR));
	}

	public String drawOneMoreCard(final PlayerTurnDto player) {
		return inputData(InputValidator::validateDrawChoice,
				() -> System.out.println(player.getName() + DRAW_ONE_MORE_CARD));
	}

	private String inputData(final Consumer<String> validation, final InputMessage inputMessage) {
		try {
			inputMessage.print();
			final String data = scanner.nextLine();
			validation.accept(data);
			return data;
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
			return inputData(validation, inputMessage);
		}
	}
}
