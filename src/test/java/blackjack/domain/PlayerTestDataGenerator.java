package blackjack.domain;

import java.util.Map;

public class PlayerTestDataGenerator {

    public static Map<String, Integer> generatePlayers() {
        return Map.of("mat", 10000, "sudal", 20000);
    }
}
