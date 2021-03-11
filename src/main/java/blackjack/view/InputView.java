package blackjack.view;

import blackjack.domain.participant.Name;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String HIT_VALUE_ERROR_MESSAGE = "Y 또는 N을 입력해 주세요.";
    private static final String COMMA = ",";
    private static final String NUMERIC_REGULAR_EXPRESSION = "\\d+";
    public static final String NOT_NUMERIC_INPUT_ERROR_MESSAGE = "숫자를 입력해주세요.";

    private InputView() {
    }

    public static List<String> getPlayerNameInput() {
        return Arrays.stream(scanner.nextLine().split(COMMA))
                .collect(Collectors.toList());
    }

    public static String getHitValue() {
        String value = scanner.nextLine().toUpperCase();
        validateHitValue(value);
        return value;
    }

    private static void validateHitValue(String value) {
        List<String> hitValues = Arrays.asList("Y", "N");
        if (!hitValues.contains(value)) {
            throw new IllegalArgumentException(HIT_VALUE_ERROR_MESSAGE);
        }
    }

    public static int getBettingMoney() {
        String value = scanner.nextLine();
        validateBettingMoney(value);
        return Integer.parseInt(value);
    }

    private static void validateBettingMoney(String value) {
        if (isNotNumeric(value)) {
            throw new IllegalArgumentException(NOT_NUMERIC_INPUT_ERROR_MESSAGE);
        }
    }

    private static boolean isNotNumeric(String value) {
        return !value.matches(NUMERIC_REGULAR_EXPRESSION);
    }
}
