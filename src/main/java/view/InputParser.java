package view;

import java.util.Arrays;
import java.util.List;

public class InputParser {
    public static List<String> parsePlayerNames(String inputNames) {
        return Arrays.stream(inputNames.split(","))
                .map(String::strip)
                .toList();
    }

    public static int parseMoney(String money) {
        try {
            return Integer.parseInt(money);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 숫자만 가능합니다.");
        }
    }
}
