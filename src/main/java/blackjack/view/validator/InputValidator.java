package blackjack.view.validator;

import blackjack.utils.Constants;
import blackjack.utils.Converter;

import java.util.List;

public class InputValidator {
    private static final List<String> INVALID_PLAYER_NAMES = List.of(
            Constants.DEFAULT_NAME_OF_DEALER,
            Constants.EXPRESSION_OF_YES,
            Constants.EXPRESSION_OF_NO);

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

    private void validateInput(final String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("입력은 공백일 수 없습니다.");
        }
    }
}
