package blackjack.utils;

import java.util.List;

public class InputValidator {

    private static final String BLANK_ERROR_MESSAGE = "[Error]: 내용을 입력해주세요.";
    private static final String REQUEST_MORE_CARD_FORMAT_ERROR_MESSAGE = "[Error]: 대답은 y/n 형식으로 입력해주세요.";
    public static final String MORE_CARD = "y";
    private static final String STOP_MORE_CARD = "n";
    private static final String HAS_DUPLICATE_NAME_ERROR_MESSAGE = "[Error]: 중복된 이름이 존재합니다.";

    private InputValidator() {
    }

    public static void inputBlank(String number) {
        if (number == null || number.isBlank()) {
            throw new IllegalArgumentException(BLANK_ERROR_MESSAGE);
        }
    }

    public static void isAnswerFormat(String number) {
        if (!number.equals(MORE_CARD) && !number.equals(STOP_MORE_CARD)) {
            throw new IllegalArgumentException(REQUEST_MORE_CARD_FORMAT_ERROR_MESSAGE);
        }
    }

    public static void inputListBlank(List<String> names) {
        boolean isBlank = names.stream().
                anyMatch(String::isBlank);
        if (isBlank) {
            throw new IllegalArgumentException(BLANK_ERROR_MESSAGE);
        }
    }

    public static void hasDuplicateName(List<String> names) {
        long namesSize = names.stream()
                .distinct()
                .count();
        if (namesSize != names.size()) {
            throw new IllegalArgumentException(HAS_DUPLICATE_NAME_ERROR_MESSAGE);
        }
    }
}
