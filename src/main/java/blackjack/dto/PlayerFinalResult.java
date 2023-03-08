package blackjack.dto;

import blackjack.domain.result.GameResult;

public class PlayerFinalResult implements FinalResult<GameResult> {

    private final String name;
    private final GameResult result;

    public PlayerFinalResult(final String name, final GameResult result) {
        this.name = name;
        this.result = result;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public GameResult getResult() {
        return result;
    }
}
