package blackjack.utils;

import java.util.List;

public class InputParser {

    public static List<String> splitPlayerNames(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 플레이어 이름을 입력해주세요.");
        }
        String[] names = input.split(",");
        for (String name : names) {
            validateNameEmpty(name);
        }
        return List.of(names).stream()
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

    private static void validateNameEmpty(String name) {
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 플레이어 이름은 공백이 될 수 없습니다.");
        }
    }
}
