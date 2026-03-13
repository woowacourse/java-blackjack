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

    public static int parseBetAmount(String input) {
        try {
            int amount = Integer.parseInt(input);
            if (amount <= 0) {
                throw new IllegalArgumentException("[ERROR] 베팅 금액은 양수여야 합니다. 다시 입력해주세요.");
            }
            return amount;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효한 숫자를 입력해주세요. 다시 입력해주세요.");
        }
    }
}
