package blackjack.utils;

import java.util.List;
import java.util.stream.Stream;

public class InputParser {

    public static List<String> splitPlayerNames(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 이름을 정확하게 입력해주세요.");
        }
        String[] names = input.split(",");
        return Stream.of(names)
                .map(String::trim)
                .toList();
    }

    public static int convertNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자 이외의 값은 입력할 수 없습니다.");
        }
    }
}
