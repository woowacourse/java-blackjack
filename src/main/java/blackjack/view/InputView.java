package blackjack.view;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

public class InputView {

	private static final Scanner scanner = new Scanner(System.in);
	private static final String PLAYER_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
	private static final String DRAW_ONE_MORE_CARD = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
	private static final String BETTING_AMOUNT = "의 배팅 금액은?";
	private static final String NAME_DISTRIBUTOR = ",";

	public static List<String> requestPlayerName() {
		final String rawNames = inputData(InputValidator::validatePlayerName,
			() -> System.out.println(PLAYER_NAME));
		return Arrays.asList(rawNames.split(NAME_DISTRIBUTOR));
	}

	public static String drawOneMoreCard(final String playerName) {
		return inputData(InputValidator::validateDrawChoice,
			() -> System.out.println(playerName + DRAW_ONE_MORE_CARD));
	}

	public static Map<String, Integer> betMoney(final List<String> playersName) {
		final Map<String, Integer> amounts = new HashMap<>();
		for (String name : playersName) {
			final String amount = inputData(InputValidator::validateBettingAmount,
				() -> System.out.println(name + BETTING_AMOUNT));
			amounts.merge(name, Integer.parseInt(amount), (key, value) -> value);
		}
		return amounts;
	}

	private static String inputData(final Consumer<String> validation, final Runnable inputMessage) {
		try {
			inputMessage.run();
			final String data = scanner.nextLine();
			validation.accept(data);
			return data;
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
			return inputData(validation, inputMessage);
		}
	}
}
