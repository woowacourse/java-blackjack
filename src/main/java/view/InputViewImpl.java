package view;

import common.ErrorMessage;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputViewImpl implements InputView {
    private static final String DELIMITER = ",";
    private static final String HIT_INPUT_VALUE = "y";
    private static final String STAND_INPUT_VALUE = "n";
    private static final List<String> HIT_OR_STAND = List.of(HIT_INPUT_VALUE, STAND_INPUT_VALUE);
    private static final Scanner sc = new Scanner(System.in);

    public List<String> readNames() {
        String line = sc.nextLine();
        validateIsBlank(line);
        return Arrays.stream(line.split(DELIMITER)).toList();
    }

    public int readBetAmountValue() {
        String line = sc.nextLine();
        validateIsBlank(line);
        int number = parseToNumber(line);
        validatePositiveNumber(number);
        return number;
    }

    public Boolean wantToHit() {
        String input = sc.nextLine().trim();
        validateIsBlank(input);
        validateHitOrStandValue(input);
        return input.equals(HIT_INPUT_VALUE);
    }

    private void validateIsBlank(String line) {
        if (line.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.NOT_ALLOW_EMPTY_INPUT.getMessage());
        }
    }

    private int parseToNumber(String line) {
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.ONLY_NUMBER.getMessage());
        }
    }

    private static void validatePositiveNumber(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException(ErrorMessage.ZERO_MINUS_MONEY.getMessage());
        }
    }

    private void validateHitOrStandValue(String input) {
        if (!HIT_OR_STAND.contains(input)) {
            throw new IllegalArgumentException(ErrorMessage.HIT_OR_STAND_VALUE_MIS_MATCH.getMessage());
        }
    }
}
