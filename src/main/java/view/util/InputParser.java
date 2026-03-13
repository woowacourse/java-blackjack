package view.util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputParser {
    private static final String DELIMITER = ",";
    private static final String NUMBER_FORMAT_EXCEPTION_MESSAGE = "[ERROR] 숫자가 아닙니다.";

    public static List<String> separateBySeparator(String input) {

        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static BigDecimal parseBigDecimal(String input) {
        try {
            return new BigDecimal(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NUMBER_FORMAT_EXCEPTION_MESSAGE);
        }
    }
}
