package blackjack.dto;

import blackjack.domain.GameResult;

public class PlayerGameResult {
    private final String name;
    private final GameResult result;

    public PlayerGameResult(String name, GameResult result) {
        this.name = name;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public GameResult getResult() {
        return result;
    }
}
