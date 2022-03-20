package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import blackjack.domain.participant.Money;

public class InputView {
	private static final Scanner scanner = new Scanner(System.in);
	private static final String MESSAGE_ASK_PARTICIPANTS = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
	private static final String NAME_DELIMITER = ",";
	private static final int MAX_PLAYER_NUMBER = 8;
	private static final String ERROR_MESSAGE_PLAYER_NUMBER = "[ERROR] 참가자의 수는 8명을 초과할 수 없습니다.";
	private static final String ERROR_MESSAGE_BET_AMOUNT_TYPE = "[ERROR] 배팅 금액은 숫자로 입력해야 합니다";
	private static final String MESSAGE_ASK_HIT_CHOICE = "%s, 한장의 카드를 더 받겠습니까?(예는 Y/y, 아니오는 N/n)%n";
	private static final String CHOICE_YES = "y";
	private static final String CHOICE_NO = "n";
	private static final String ERROR_MESSAGE_ILLEGAL_CHOICE_FORMAT = "[ERROR] y 또는 n 으로 입력해야 합니다.";
	private static final String MESSAGE_ASK_BET_AMOUNT = "%s의 배팅 금액은?%n";

	public static List<String> askPlayerNames() {
		try {
			return askPlayerName();
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return askPlayerNames();
		}
	}

	private static List<String> askPlayerName() {
		System.out.println(MESSAGE_ASK_PARTICIPANTS);
		String[] names = scanner.nextLine().split(NAME_DELIMITER);
		checkPlayersNumber(names);
		return Arrays.asList(names);
	}

	private static void checkPlayersNumber(String[] names) {
		if (names.length > MAX_PLAYER_NUMBER) {
			throw new IllegalArgumentException(ERROR_MESSAGE_PLAYER_NUMBER);
		}
	}

	public static Money askBetAmounts(String playerName) {
		try {
			return new Money(askBetAmount(playerName));
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return askBetAmounts(playerName);
		}
	}

	private static int askBetAmount(String name) {
		System.out.printf(MESSAGE_ASK_BET_AMOUNT, name);
		return checkType(scanner.nextLine(), name);
	}

	private static int checkType(String input, String name) {
		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException e) {
			System.out.println(ERROR_MESSAGE_BET_AMOUNT_TYPE);
			return askBetAmount(name);
		}
	}

	public static Boolean askHit(String name) {
		System.out.printf(MESSAGE_ASK_HIT_CHOICE, name);
		try {
			String input = scanner.nextLine().toLowerCase(Locale.ROOT);
			checkValidChoice(input);
			return CHOICE_YES.equals(input);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return askHit(name);
		}
	}

	private static void checkValidChoice(String input) {
		if (!(input.equals(CHOICE_YES) || input.equals(CHOICE_NO))) {
			throw new IllegalArgumentException(ERROR_MESSAGE_ILLEGAL_CHOICE_FORMAT);
		}
	}
}
