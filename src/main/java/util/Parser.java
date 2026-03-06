package util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {
    private static final String SEPARATOR_ERR_MSG = "구분자는 ,로 해야합니다.";

    public static List<String> separateBySeparator(String input, String separator) {
        if (!input.contains(separator)) {
            throw new IllegalArgumentException(SEPARATOR_ERR_MSG);
        }
        return Arrays.stream(input.split(separator))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
