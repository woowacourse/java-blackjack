package utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtils {
    private static final String DELIMITER = ",";
    private static final String USER_INPUT_ERROR_MESSAGE = "사용자 입력이 잘못되었습니다.";
    private static final String NULL_OR_EMPTY_NAME_ERROR_MESSAGE = "이름이 비어있습니다.";

    private StringUtils() {
    }

    public static void checkNameNullAndEmpty(String name) {
        if (name == null || name.isEmpty()) {
            throw new NullPointerException(NULL_OR_EMPTY_NAME_ERROR_MESSAGE);
        }
    }

    public static void checkNameNullAndEmpty(List<String> names) {
        if (names == null || names.isEmpty()) {
            throw new NullPointerException(NULL_OR_EMPTY_NAME_ERROR_MESSAGE);
        }
    }

    public static List<String> splitIntoList(final String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new NullPointerException(USER_INPUT_ERROR_MESSAGE);
        }
        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
