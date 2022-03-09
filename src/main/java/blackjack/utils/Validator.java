package blackjack.utils;

public class Validator {

    public static final String NULL_OR_EMPTY_ERROR = "빈 값을 입력할 수 없습니다.";

    public static void validateNullOrEmpty(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException(NULL_OR_EMPTY_ERROR);
        }
    }
}
