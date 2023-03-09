package view;

import java.util.List;

public class InputValidator {

    private static final String DEALER_NAME = "딜러";
    private static final String YES = "y";
    private static final String NO = "n";

    public static void validatePlayerCount(final List<String> names) {
        if (names.size() == 0) {
            throw new IllegalArgumentException("[ERROR] 플레이어의 수는 1명이상 이어야합니다.");
        }
    }

    public static void validatePlayerNameDuplicated(final List<String> names) {
        long count = names.stream()
                .distinct()
                .count();
        if (count != names.size()) {
            throw new IllegalArgumentException("[ERROR] 중복된 이름을 입력하면 안됩니다.");
        }
    }

    public static void validatePlayerNameCannotBeSameAsDealerName(final List<String> names) {
        long count = names.stream()
                .filter(DEALER_NAME::equals)
                .count();

        if (count != 0) {
            throw new IllegalArgumentException("[ERROR] 딜러와 같은 이름일 수 없습니다.");
        }
    }

    public static void validateAnswerYesOrNo(final String input) {
        if (!input.equals(YES) && !input.equals(NO)) {
            throw new IllegalArgumentException("[ERROR] y/n만 입력 가능합니다.");
        }
    }

    public static void validateIsNumeric(final String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 숫자를 입력해주세요.");
        }
    }
}
