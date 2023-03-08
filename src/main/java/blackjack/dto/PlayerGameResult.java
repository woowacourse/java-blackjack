package blackjack.dto;

import blackjack.domain.GameResult;
import blackjack.domain.Name;

public class PlayerGameResult {
    private final String name;
    private final GameResult result;

    public PlayerGameResult(String name, GameResult result) {
        this.name = name;
        this.result = result;
    }

    public static PlayerGameResult of(Name name, GameResult gameResult) {
        return new PlayerGameResult(name.getName(), gameResult);
    }

    public String getName() {
        return name;
    }

    public GameResult getResult() {
        return result;
    }
}
