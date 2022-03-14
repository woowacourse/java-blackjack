package blackjack.view;

import java.util.Arrays;
import java.util.Locale;

public class InputValidator {

	private static final String NO_INPUT_ERROR = "입력 값이 없습니다.";
	private static final String INPUT_CONTAINS_SPACE_ERROR = "입력 값에 공백을 포함할 수 없습니다.";
	private static final String NAME_DUPLICATED_ERROR = "중복된 이름을 사용할 수 없습니다.";
	private static final String PROPER_CHOICE_ERROR = "선택하는 입력 값은 y 또는 n 이어야 합니다.";
	private static final String SPACE = " ";
	private static final String NAME_DISTRIBUTOR = ",";
	private static final String CHOICE_YES = "y";
	private static final String CHOICE_NO = "n";

	public static void validatePlayerName(String input) {
		validateEmpty(input);
		validateSpace(input);
		validateDuplication(input);
	}

	private static void validateEmpty(String input) {
		if (input.isEmpty()) {
			throw new IllegalArgumentException(NO_INPUT_ERROR);
		}
	}

	private static void validateSpace(String input) {
		if (input.contains(SPACE)) {
			throw new IllegalArgumentException(INPUT_CONTAINS_SPACE_ERROR);
		}
	}

	private static void validateDuplication(String input) {
		String[] parsedInput = input.split(NAME_DISTRIBUTOR);
		final long theNumberOfUniqueInput = Arrays.stream(parsedInput)
			.distinct()
			.count();
		if (parsedInput.length != theNumberOfUniqueInput) {
			throw new IllegalArgumentException(NAME_DUPLICATED_ERROR);
		}
	}

	public static void validateDrawChoice(String input) {
		validateEmpty(input);
		validateProperChoice(input);
	}

	private static void validateProperChoice(String input) {
		final String inputLowercase = input.toLowerCase(Locale.ROOT);
		if (!inputLowercase.equals(CHOICE_YES) && !inputLowercase.equals(CHOICE_NO)) {
			throw new IllegalArgumentException(PROPER_CHOICE_ERROR);
		}
	}
}
