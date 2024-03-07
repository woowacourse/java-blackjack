package view;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class InputView {
    private static final String DELIMITER = ",";

    public static List<String> readNames(Supplier<String> input) {
        String result = input.get();
        validateEmpty(result);
        validateDelimiter(result);
        return Arrays.stream(result.split(DELIMITER))
                .map(String::trim)
                .toList();
    }

    private static void validateEmpty(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("이름에 공백이나 null을 넣을 수 없습니다.");
        }
    }

    private static void validateDelimiter(String input) {
        if (input.endsWith(DELIMITER)) {
            throw new IllegalArgumentException("이름에 공백이나 null을 넣을 수 없습니다.");
        }
    }
}
