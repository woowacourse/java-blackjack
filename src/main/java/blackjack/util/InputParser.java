package blackjack.util;

import java.util.List;

public class InputParser {

    private static final String COMMA = ",";

    private InputParser() {
    }

    public static List<String> parseUser(String userName) {
        return List.of(userName.split(COMMA));
    }

    public static int parseBettingAmount(String bettingAmount) {
        return Integer.parseInt(bettingAmount);
    }
}
