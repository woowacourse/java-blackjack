package view;

import static exception.ErrorMessage.MONEY_INVALID_FORMAT;

import java.util.Arrays;
import java.util.List;

public class InputParser {
    public static List<String> parsePlayerNames(String inputNames) {
        return Arrays.stream(inputNames.split(","))
                .map(String::strip)
                .toList();
    }

    public static int parseMoney(String money) {
        try {
            return Integer.parseInt(money);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(MONEY_INVALID_FORMAT.getMessage());
        }
    }
}
