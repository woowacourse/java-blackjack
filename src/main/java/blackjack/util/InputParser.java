package blackjack.util;

import blackjack.domain.Player;
import java.util.ArrayList;
import java.util.List;

public class InputParser {

    private InputParser() {
    }

    public static List<String> parsePlayer(String userName) {
        return List.of(userName.split(","));
    }

}
