package blackjack.util;

import blackjack.model.Player;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class PlayerParser {

    private static final String regrex = "^[a-zA-Z가-힣]*$";

    public static List<Player> parse(String names) {
        return Arrays.stream(names.split(","))
                .map(String::trim)
                .peek(PlayerParser::validateRegrex)
                .peek(PlayerParser::validateEmpty)
                .map(Player::new)
                .toList();
    }

    private static void validateRegrex(String parsedName) {
        if (!Pattern.matches(regrex, parsedName)) {
            throw new IllegalArgumentException("플레이어의 이름은 영어 or 한글로만 이루어질 수 있습니다.");
        }
    }

    private static void validateEmpty(String parsedName) {
        if (parsedName.isEmpty()) {
            throw new IllegalArgumentException("플레이어의 이름은 공백일 수 없습니다.");
        }
    }
}
