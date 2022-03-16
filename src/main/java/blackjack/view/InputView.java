package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {
	private static final Scanner scanner = new Scanner(System.in);
	private static final Pattern YES_OR_NO = Pattern.compile("^[ynYN]$");
	private static final Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]+$");
	private static final String YES = "y";
	private static final String DELIMITER = ",";
	private static final String NOT_VALID_DECISION_INPUT = "[ERROR] 입력은 Y,N(소문자 가능)만 입력해주세요.";
	private static final String NOT_VALID_NUMBER_INPUT = "[ERROR] 숫자만 입력 가능합니다";

	public List<String> inputPlayerNames() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
		return Arrays.asList(scanner.nextLine().split(DELIMITER));
	}

	public boolean inputYesOrNo(String name) {
		System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
		String decision = scanner.nextLine();
		if (!YES_OR_NO.matcher(decision).matches()) {
			throw new IllegalArgumentException(NOT_VALID_DECISION_INPUT);
		}
		return decision.equalsIgnoreCase(YES);
	}

	public int inputMoney(String name) {
		System.out.println(name + "의 배팅 금액은?");
		String money = scanner.nextLine();
		if (!NUMBER_PATTERN.matcher(money).matches()) {
			throw new IllegalArgumentException(NOT_VALID_NUMBER_INPUT);
		}
		return Integer.parseInt(money);
	}
}
