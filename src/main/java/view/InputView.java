package view;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class InputView {
    private static final String DELIMITER = ",";

    public static List<String> readNames(Supplier<String> input) {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
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

    public static String readAnswer(Supplier<String> input, String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", name);
        String result = input.get();
        validateEmpty(result);
        validateAnswer(result);
        return result;
    }

    private static void validateAnswer(String input) {
        if (!input.equals("y") && !input.equals("n")) {
            throw new IllegalArgumentException("y또는 n만 입력 받을 수 있습니다.");
        }
    }

}
