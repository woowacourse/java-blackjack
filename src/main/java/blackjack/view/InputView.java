package blackjack.view;

import static blackjack.view.exceptionMessages.InputViewExceptionMessage.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import blackjack.view.exceptionMessages.InputViewExceptionMessage;

public class InputView {
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

	private void validateNullString(String names) {
		if (names == null) {
			throw new IllegalArgumentException(NULL_STRING_EXCEPTION.getMessage());
		}
	}

	public String inputYesOrNo(String name) {
		System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
		String decision = scanner.nextLine();
		if (!YES_OR_NO.matcher(decision).matches()) {
			throw new IllegalArgumentException(DECISION_INPUT_EXCEPTION.getMessage());
		}
		return decision;
	}
}
