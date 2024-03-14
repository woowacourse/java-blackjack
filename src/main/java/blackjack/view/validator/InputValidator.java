package blackjack.view.validator;

import blackjack.utils.Constants;
import blackjack.utils.Converter;

import java.util.List;
import java.util.regex.Pattern;

public class InputValidator {
    private static final List<String> INVALID_PLAYER_NAMES = List.of(
            Constants.DEFAULT_NAME_OF_DEALER,
            Constants.EXPRESSION_OF_YES,
            Constants.EXPRESSION_OF_NO);
    private static final Pattern ONLY_DIGIT_PATTERN = Pattern.compile("-?[0-9]+");

    public void validatePlayerNames(final String input) {
        validateInput(input);

        if (hasInvalidName(input)) {
            throw new IllegalArgumentException("딜러와 연관된 이름 또는 게임 명령어와 연관된 이름이 존재할 수 없습니다.");
        }
    }

    private boolean hasInvalidName(final String input) {
        final List<String> names = Converter.stringToList(input);

        return names.stream()
                .anyMatch(INVALID_PLAYER_NAMES::contains);
    }

    public void validateBattingAmount(final String input) {
        validateInput(input);

        if (isNotDigit(input)) {
            throw new IllegalArgumentException("배팅 금액에 대한 입력은 숫자여야 합니다.");
        }
    }

    private boolean isNotDigit(final String input) {
        return !ONLY_DIGIT_PATTERN.matcher(input)
                .matches();
    }

    private void validateInput(final String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("입력은 공백일 수 없습니다.");
        }
    }
}
