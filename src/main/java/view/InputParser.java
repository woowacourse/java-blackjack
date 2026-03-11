package view;

import static message.ErrorMessage.BET_AMOUNT_OUT_OF_RANGE;
import static view.constant.ViewRule.YES_ANSWER;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import message.ErrorMessage;

public class InputParser {

    private static final String DELIMITER = ",";

    public static List<String> parseNames(String input) {
        input = input.replace(" ", "");
        return Arrays.asList(input.split(DELIMITER));
    }

    public static boolean parseHitAnswer(String input) {
        return YES_ANSWER.contains(input);
    }

    public static int parseBetAmount(String input) {
        try {
            validateBetAmountIntRange(input);
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(ErrorMessage.BET_AMOUNT_INVALID_FORMAT.getMessage());
        }
    }

    private static void validateBetAmountIntRange(String input) {
        BigInteger value = new BigInteger(input);
        if (value.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) < 0
                || value.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0) {
            throw new IllegalArgumentException(BET_AMOUNT_OUT_OF_RANGE.getMessage());
        }
    }
}