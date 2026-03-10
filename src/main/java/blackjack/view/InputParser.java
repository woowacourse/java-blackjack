package blackjack.view;

import java.util.Arrays;
import java.util.List;

public class InputParser {

    public static List<String> parse(String names) {
        return Arrays.stream(names.split(","))
                .map(String::trim)
                .toList();
    }
}

