package blackjack.util;

import java.util.Arrays;
import java.util.List;

public class InputParser {

    private InputParser() {
    }

    public static List<String> parseStringToList(String input) {
        return Arrays.asList(input.split(","));
    }
}
