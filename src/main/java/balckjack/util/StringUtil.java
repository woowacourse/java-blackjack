package balckjack.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtil {

    private StringUtil() {
    }

    public static List<String> split(String input, String delimiter) {
        validateInput(input);
        return Arrays.stream(input.split(delimiter)).collect(Collectors.toList());
    }

    private static void validateInput(String input) {
        if (input == null) {
            throw new IllegalArgumentException("아무 문자열도 입력되지 않았습니다.");
        }
    }
}
