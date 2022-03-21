package blackjack.utils;

import java.util.List;

public class InputValidator {

    public static final String HIT = "y";
    public static final String MINIMUM_MONEY_ERROR_MESSAGE = "[Error]: 금액은 양수여야 합니다.";
    private static final String BLANK_ERROR_MESSAGE = "[Error]: 내용을 입력해주세요.";
    private static final String REQUEST_HIT_FORMAT_ERROR_MESSAGE = "[Error]: 대답은 y/n 형식으로 입력해주세요.";
    private static final String STOP_HIT = "n";
    private static final String HAS_DUPLICATE_NAME_ERROR_MESSAGE = "[Error]: 중복된 이름이 존재합니다.";
    private static final int LIMIT_NUMBER_OF_PLAYERS = 8;
    private static final String LIMIT_NUMBER_OF_PLAYERS_ERROR_MESSAGE = "[Error]: 게임에 참가할 수 있는 인원은 최대 8명입니다.";
    private static final String MONEY_IS_NOT_NUMBER_ERROR_MESSAGE = "[Error]: 배팅 금액은 숫자여야 합니다.";
    private static final String REGEX_NUMBER = "[0-9]+";
    private static final int MINIMUM_LIMIT_MONEY = 1;

    private InputValidator() {
    }

    public static void inputBlank(String number) {
        if (number == null || number.isBlank()) {
            throw new IllegalArgumentException(BLANK_ERROR_MESSAGE);
        }
    }

    public static void inputListBlank(List<String> names) {
        boolean isBlank = names.stream().
                anyMatch(String::isBlank);
        if (isBlank) {
            throw new IllegalArgumentException(BLANK_ERROR_MESSAGE);
        }
    }

    public static void isOverNumberOfPlayersLimit(List<String> names) {
        if (names.size() > LIMIT_NUMBER_OF_PLAYERS) {
            throw new IllegalArgumentException(LIMIT_NUMBER_OF_PLAYERS_ERROR_MESSAGE);
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

    public static void isAnswerFormat(String number) {
        if (!number.equals(HIT) && !number.equals(STOP_HIT)) {
            throw new IllegalArgumentException(REQUEST_HIT_FORMAT_ERROR_MESSAGE);
        }
    }

    public static void isDigit(String number) {
        if (!number.matches(REGEX_NUMBER)) {
            throw new IllegalArgumentException(MONEY_IS_NOT_NUMBER_ERROR_MESSAGE);
        }
    }

    public static void isBiggerThanZero(String name) {
        if (toInteger(name) < MINIMUM_LIMIT_MONEY) {
            throw new IllegalArgumentException(MINIMUM_MONEY_ERROR_MESSAGE);
        }
    }

    public static int toInteger(String number) {
        return Integer.parseInt(number);
    }
}
