package blackjack.util;

import java.util.List;

public class InputParser {

    private InputParser() {
    }

    public static List<String> parseUser(String userName) {
        return List.of(userName.split(","));
    }
}
