package model.result;

import exception.IllegalBlackjackStateException;
import java.util.Arrays;

public enum BettingOdds {
    WIN_ODDS(GameResult.WIN, +1.0),
    LOSE_ODDS(GameResult.LOSE, -1.0),
    DRAW_ODDS(GameResult.DRAW, 0.0),
    BLACKJACK_WIN_ODDS(GameResult.BLACKJACK_WIN, +1.5);

    private final GameResult gameResult;
    private final double odds;

    BettingOdds(final GameResult gameResult, final double odds) {
        this.gameResult = gameResult;
        this.odds = odds;
    }

    public double multiple(final int bet) {
        return bet * this.odds;
    }

    public static BettingOdds from(final GameResult gameResult) {
        return Arrays.stream(values())
                .filter(bettingOdds -> bettingOdds.gameResult == gameResult)
                .findFirst()
                .orElseThrow(IllegalBlackjackStateException::new);
    }
}
