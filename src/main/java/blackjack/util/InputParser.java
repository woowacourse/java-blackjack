package blackjack.util;

import java.math.BigDecimal;
import java.util.List;

public class InputParser {

    private static final String COMMA = ",";

    private InputParser() {
    }

    public static List<String> parseUser(String userName) {
        return List.of(userName.strip().split(COMMA));
    }

    public static BigDecimal parseBettingAmount(String bettingAmount) {
        return new BigDecimal(bettingAmount);
    }
}
