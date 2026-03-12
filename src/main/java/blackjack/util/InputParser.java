package blackjack.util;

import static blackjack.util.constant.Constants.COMMA;

import java.util.List;

public class InputParser {

    private InputParser() {
    }

    public static List<String> parseUser(String userName) {
        return List.of(userName.split(COMMA));
    }

    public static int parseBettingAmount(String bettingAmount) {
        return Integer.parseInt(bettingAmount);
    }
}
