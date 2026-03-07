package blackjack.domain;

import java.util.Arrays;
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

    public String getName() {
        return name;
    }

    public static GameResult pick(String name) {
        return Arrays.stream(values())
            .filter(gameResult -> Objects.equals(name, gameResult.name))
            .findFirst().orElse(DRAW);
    }
}
