package blackjack.utils;

import java.util.Arrays;
import java.util.List;

public class NameParser {
    public static List<String> parse(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("이름은 최소 1개 이상이어야 합니다");
        }
        return Arrays.asList(input.trim()
                .replace(" ", "")
                .split(","));
    }
}
