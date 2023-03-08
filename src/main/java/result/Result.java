package result;

import java.util.Map;

public enum Result {
    LOSE("패"),
    PUSH("무"),
    WIN("승");

    private static final Map<Result, Result> OpponentPair = Map.of(
            WIN, LOSE,
            LOSE, WIN,
            PUSH, PUSH
    );

    private final String name;

    Result(String name) {
        this.name = name;
    }

    public Result getResultOfOpponent() {
        return OpponentPair.get(this);
    }

    public String getName() {
        return this.name;
    }
}
