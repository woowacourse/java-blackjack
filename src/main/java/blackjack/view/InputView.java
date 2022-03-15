package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {
	private static final String NULL_STRING_EXCEPTION = "문자열의 입력이 NULL 이면 안됩니다.";
	private static final String DECISION_INPUT_EXCEPTION = "입력은 Y,N(소문자 가능)만 입력해주세요.";
	private static final Scanner scanner = new Scanner(System.in);
	private static final Pattern YES_OR_NO = Pattern.compile("^[ynYN]$");

	public List<String> inputPlayerNames() {
		final String names = inputNames();
		return Arrays.asList(names.split(","));
	}

	private String inputNames() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
		String names = scanner.nextLine();
		try {
			validateNullString(names);
			return names;
		} catch (IllegalArgumentException exception) {
			return inputNames();
		}
	}

	private void validateNullString(final String names) {
		if (names == null) {
			throw new IllegalArgumentException(NULL_STRING_EXCEPTION);
		}
	}

	public boolean isHitDecision(final String name) {
		final String decision = inputDecision(name);
		if (decision.equals("N") || decision.equals("n")) {
			return false;
		}
		return true;
	}

	private String inputDecision(final String name) {
		System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
		String decision = scanner.nextLine();
		try {
			validateDecisionInput(decision);
			return decision;
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
			return inputDecision(name);
		}
	}

	private void validateDecisionInput(String decision) {
		if (!YES_OR_NO.matcher(decision).matches()) {
			throw new IllegalArgumentException(DECISION_INPUT_EXCEPTION);
		}
	}
}
