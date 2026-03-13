package util;

import static constant.GameRule.YES_ANSWER;

import java.util.Arrays;
import java.util.List;

public class InputParser {

    private static final String DELIMITER = ",";

    public static List<String> parseNames(String input) {
        input = input.replace(" ", "");
        return Arrays.asList(input.split(DELIMITER));
    }

    public static boolean parseHitAnswer(String input) {
        return YES_ANSWER.contains(input);
    }

    public static int parseMoney(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 정수형 숫자만 입력 가능합니다.");
        }
    }
}
