package domain.game;

import java.util.Map;

public enum Result {
    WIN("승"),
    PUSH("무"),
    LOSE("패");

    private static final Map<Result, Result> opponentPair = Map.of(
            WIN, LOSE,
            LOSE, WIN,
            PUSH, PUSH
    );

    private final String name;

    Result(String name) {
        this.name = name;
    }

    public Result getResultOfOpponent() {
        return opponentPair.get(this);
    }

    public String getName() {
        return this.name;
    }
}
