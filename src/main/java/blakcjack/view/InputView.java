package blakcjack.view;

import blakcjack.domain.name.Name;
import blakcjack.domain.participant.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
	private static final int BETTING_AMOUNT_LOWER_BOUND = 0;
	private static final String YES = "y";
	private static final String NO = "n";
	private static final Scanner SCANNER = new Scanner(System.in);

	public static List<String> takePlayerNamesInput() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
		return Arrays.stream(SCANNER.nextLine().split(","))
				.map(String::trim)
				.collect(Collectors.toList());
	}

	public static int takeBettingAmountInput(final Name name) {
		System.out.printf("%s의 베팅 금액은?%n", name.getName());
		String bettingAmount = SCANNER.nextLine();
		validateInteger(bettingAmount);
		validateAmount(bettingAmount);
		return Integer.parseInt(bettingAmount);
	}

	private static void validateAmount(final String bettingAmount) {
		if (Integer.parseInt(bettingAmount) <= BETTING_AMOUNT_LOWER_BOUND) {
			throw new IllegalArgumentException("베팅 금액은 0 보다는 많아야 합니다.");
		}
	}

	private static void validateInteger(final String bettingAmount) {
		try {
			Integer.parseInt(bettingAmount);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("정수만 입력 가능합니다.");
		}
	}

	public static boolean isYes(Player player) {
		try {
			final String name = player.getName();
			System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
			final String input = SCANNER.nextLine().trim();
			validate(input);
			return YES.equals(input);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return isYes(player);
		}
	}

	private static void validate(final String input) {
		if (!YES.equals(input) && !NO.equals(input)) {
			throw new IllegalArgumentException(YES + "나 " + NO + "만 입력해주세요.");
		}
	}
}
