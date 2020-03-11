package blackjack.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class NameParser {

    public static List<String> parse(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("이름은 최소 1개 이상이어야 합니다");
        }
        List<String> names = Arrays.asList(input.trim()
                .replace(" ", "")
                .split(","));

        if (names.size() != new HashSet<>(names).size()) {
            throw new IllegalArgumentException("중복된 이름이 있습니다");
        }
        return names;
    }
}
