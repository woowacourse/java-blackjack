package blackjack.domain.game;

import blackjack.domain.game.result.GameResult;
import blackjack.domain.participant.Name;

public class PlayerResult {
    private final Name name;
    private final GameResult gameResult;

    public PlayerResult(Name name, GameResult gameResult) {
        this.name = name;
        this.gameResult = gameResult;
    }

    public Name getName() {
        return name;
    }

    public GameResult getResult() {
        return gameResult;
    }
}
