package blackjack.view;

import blackjack.domain.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
	private static final String NAME_DELIMITER = ",";
	private static final String INPUT_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
	private static final String INPUT_ASK_ADDITIONAL_CARD = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
	private static final String ANSWER_YES = "y";
	private static final String ANSWER_NO = "n";

	private static final String ANSWER_ERROR = "[ERROR] 답은 y, n 으로 해야 합니다.";

	private static final Scanner scanner = new Scanner(System.in);

	public static List<String> getPlayerNames() {
		System.out.println(INPUT_NAMES);

		return Arrays.stream(scanner.nextLine()
			.split(NAME_DELIMITER))
			.map(String::trim)
			.collect(Collectors.toList());
	}

	public static boolean askAdditionalCard(Player person) {
		System.out.println(person.getName() + INPUT_ASK_ADDITIONAL_CARD);

		String answer = scanner.nextLine();
		validateIsYesOrNo(answer);

		return answer.equals(ANSWER_YES);
	}

	private static void validateIsYesOrNo(String answer) {
		if (!(answer.equals(ANSWER_YES) || answer.equals(ANSWER_NO))) {
			throw new IllegalArgumentException(ANSWER_ERROR);
		}
	}
}
