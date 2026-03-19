package util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NameParser {
    private NameParser() {

    }

    public static List<String> makeNameList(String input) {
        List<String> names = Arrays.stream(input.split(","))
                .map(String::trim)
                .toList();
        Set<String> uniqueNames = new HashSet<>(names);
        if (names.size() != uniqueNames.size()) {
            throw new IllegalArgumentException("중복된 이름은 허용하지 않습니다.");
        }
        return names;
    }
}
