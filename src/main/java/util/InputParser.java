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
}