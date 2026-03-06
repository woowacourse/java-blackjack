package blackjack.domain;

import java.util.List;
import java.util.Objects;

public enum GameResult {

    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String name;

    GameResult(String name) {
        this.name = name;
    }

    private static List<GameResult> all() {
        return List.of(values());
    }

    public static GameResult pick(String name) {
        return all().stream()
            .filter(gameResult -> Objects.equals(name, gameResult.name))
            .findFirst().orElse(DRAW);
    }
}
