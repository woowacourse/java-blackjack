package blackjack.utils;

import java.util.List;
import java.util.stream.Stream;

public class InputParser {

    public static List<String> splitPlayerNames(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 플레이어 이름을 입력해주세요.");
        }
        String[] names = input.split(",", -1);
        for (String name : names) {
            if (name.trim().isEmpty()) {
                throw new IllegalArgumentException("[ERROR] 플레이어 이름은 공백이 될 수 없습니다. 형식을 다시 확인해주세요.");
            }
        }
        return Stream.of(names)
                .map(String::trim)
                .toList();
    }
}
